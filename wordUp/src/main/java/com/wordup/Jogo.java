package com.wordup;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.*;

public class Jogo {
    private List<String> palavras;
    private List<Character> letrasChutadas = new ArrayList<>();
    private String palavraEscolhida;
    private char[] palavraOculta;
    private Jogador jogador1;
    private Jogador jogador2;
    private Jogador jogadorAtual;
    private Jogador proximoJogador;
    private ScheduledExecutorService timerExecutor;
    private Semaphore semaphore;

    public Jogo(List<String> palavras, Jogador jogador1, Jogador jogador2) {
        this.palavras = palavras;
        this.jogador1 = jogador1;
        this.jogador2 = jogador2;
        this.jogadorAtual = jogador1;
        this.proximoJogador = jogador2;
        this.semaphore = new Semaphore(0);  // Inicializa o semáforo com 0 permissões


        inicializarJogo();
    }

    private void inicializarJogo() {
        palavraEscolhida = palavras.get(new Random().nextInt(palavras.size())).toLowerCase();
        palavraOculta = new char[palavraEscolhida.length()];

        for (int i = 0; i < palavraOculta.length; i++) {
            palavraOculta[i] = '_';
        }
    }

    private void iniciarTimer() {
        timerExecutor = Executors.newSingleThreadScheduledExecutor();
        timerExecutor.schedule(this::tempoEsgotado, 15, TimeUnit.SECONDS);
    }

    public void jogar(Scanner scanner) {
        boolean palavraAdivinhada = false;

        while (!palavraAdivinhada) {
            // System.out.println("---------- TESTE ------------");
            // System.out.println("Palavra escolhida : " );
            // System.out.println("Nome e tentativas do jogador 1 " + jogador1.getNome() + " " + jogador1.getTentativas());
            // System.out.println("Nome e tentativas do jogador 2 " + jogador2.getNome() + " " + jogador2.getTentativas());
            // System.out.println("---------- TESTE ------------");

            System.out.println("|----------------------------------------------| ");
            System.out.println("|Palavra: " + String.valueOf(palavraOculta));
            System.out.println("|Jogador atual: " + jogadorAtual.getNome());
            System.out.println("|letras chutadas: " + letrasChutadas);
            System.out.println("|----------------------------------------------|");


            System.out.print("Escolha uma opção | L | para letra OU | P | para chutar a palavra): ");
            char opcao = scanner.next().toLowerCase().charAt(0);

            if (opcao == 'l' || opcao == 'p') {
                iniciarTimer();  // Iniciar o tempo apenas quando escolher letra ou palavra
            }

            if (opcao == 'l') {
                System.out.print("Digite uma letra: ");
                char letra = scanner.next().toLowerCase().charAt(0);
                letrasChutadas.add(letra);
                processarEscolhaLetra(letra);
            } else if (opcao == 'p') {
                System.out.print("Chute a palavra completa: ");
                String chute = scanner.next().toLowerCase();
                processarEscolhaPalavra(chute);
            } else {
                System.out.println("Opção inválida. Tente novamente.");
            }

            palavraAdivinhada = palavraAdivinhada(palavraOculta);

            if (palavraAdivinhada) {
                imprimirMensagemParabens(jogadorAtual);
                jogadorAtual.setTentativas(jogadorAtual.getTentativas() + 1);
            } else {
                jogadorAtual = proximoJogador;
                proximoJogador = (jogadorAtual == jogador1) ? jogador2 : jogador1;
                proximoJogador.setTentativas(proximoJogador.getTentativas() + 1);
            }

            pararTimer();  // Parar o tempo no final de cada iteração
        }

        scanner.close();
    }

    private void processarEscolhaLetra(char letra) {
        boolean letraRevelada = false;
        for (int i = 0; i < palavraEscolhida.length(); i++) {
            if (palavraEscolhida.charAt(i) == letra) {
                palavraOculta[i] = letra;
                letraRevelada = true;
            }
        }

        if (!letraRevelada) {
            System.out.println("Letra não encontrada. Tente novamente.");
            jogadorAtual.setPontuacao(jogadorAtual.getPontuacao() - 5);
        } else {
            jogadorAtual.setPontuacao(jogadorAtual.getPontuacao() + 10);
        }
        trocarJogador();
    }

    private void processarEscolhaPalavra(String chute) {
        if (chute.equals(palavraEscolhida)) {
            jogadorAtual.setPontuacao(jogadorAtual.getPontuacao() + palavraEscolhida.length());
            imprimirMensagemParabens(jogadorAtual);
            System.exit(0); // Saia do jogo após acertar a palavra
        } else {
            System.out.println("Chute incorreto. Tente novamente.");
            jogadorAtual.setPontuacao(jogadorAtual.getPontuacao() - 5);
        }
    }

    private void trocarJogador() {
        semaphore.release();
        jogadorAtual = (jogadorAtual == jogador1) ? jogador2 : jogador1;
    }

    private void tempoEsgotado() {
        System.out.println("Tempo esgotado! O jogador " + jogadorAtual.getNome() + " não fez uma escolha a tempo. Chance para " + proximoJogador.getNome());
        proximoJogador = (jogadorAtual == jogador1) ? jogador2 : jogador1;
        trocarJogador();
    }

    private void pararTimer() {
        if (timerExecutor != null) {
            timerExecutor.shutdownNow();  //  interrompe imediatamente
        }
    }

    private boolean palavraAdivinhada(char[] palavraOculta) {
        for (char c : palavraOculta) {
            if (c == '_') {
                return false;
            }
        }
        return true;
    }

    private void imprimirMensagemParabens(Jogador jogadorAtual) {
        System.out.println("Parabéns " + jogadorAtual.getNome() + "! Você adivinhou a palavra " +
                palavraEscolhida + " em " + jogadorAtual.getTentativas() + " tentativas.");
        System.out.println("Você fez");

        Thread mensagemThread = new Thread(() -> {
            try {
                semaphore.acquire();  // Aguarda a permissão para imprimir a mensagem
                for (int i = 0; i < 3; i++) {
                    System.out.print(".");  // Imprime uma reticência
                    Thread.sleep(2000);  // Aguarda 2 segundos (2000 milissegundos)
                }
                System.out.println("\n" + jogadorAtual.getPontuacao() + " pontos !!!");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                semaphore.release();  // Libera a permissão após imprimir a mensagem
            }
        });
        mensagemThread.start();

        try {
            mensagemThread.join();  // Aguarda a thread da mensagem terminar
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
