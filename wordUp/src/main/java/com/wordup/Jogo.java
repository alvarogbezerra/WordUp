package com.wordup;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Jogo {
    private List<String> palavras;
    private String palavraEscolhida;
    private char[] palavraOculta;
    private Jogador jogadorAtual;
    private Jogador jogador1;
    private Jogador jogador2;

    public Jogo(List<String> palavras, Jogador jogador1, Jogador jogador2) {
        this.palavras = palavras;
        this.jogador1 = jogador1;
        this.jogador2 = jogador2;
        this.jogadorAtual = jogador1;

        inicializarJogo();
    }

    private void inicializarJogo() {
        palavraEscolhida = palavras.get(new Random().nextInt(palavras.size())).toLowerCase();
        palavraOculta = new char[palavraEscolhida.length()];

        for (int i = 0; i < palavraOculta.length; i++) {
            palavraOculta[i] = '_';
        }
    }

    public void jogar(Scanner scanner) {
        boolean palavraAdivinhada = false;

        while (!palavraAdivinhada) {
            //As linhas abaixo servem para facilitar o teste de mesa
            System.out.println("---------- TESTE ------------");
            System.out.println("Palavra escolhida : " + palavraEscolhida);
            System.out.println("Nome e tentativas do jogador 1 " + jogador1.getNome() + jogador1.getTentativas());
            System.out.println("Nome e tentativas do jogador 2 " + jogador2.getNome() + jogador2.getTentativas());
            System.out.println("---------- TESTE ------------");

            System.out.println("Palavra: " + String.valueOf(palavraOculta));
            System.out.println("Jogador atual: " + jogadorAtual.getNome());
            //System.out.println("Prontuação : " + jogadorAtual.getPontuacao() + ".");
            System.out.print("Escolha uma opção (L para letra, P para chutar a palavra): ");
            char opcao = scanner.next().toLowerCase().charAt(0);

            if (opcao == 'l') {
                System.out.print("Digite uma letra: ");
                char letra = scanner.next().toLowerCase().charAt(0);

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
                }else {
                    jogadorAtual.setPontuacao(jogadorAtual.getPontuacao() + 10);
                }
                trocarJogador();
            } else if (opcao == 'p') {
                System.out.print("Chute a palavra completa: ");
                String chute = scanner.next().toLowerCase();

                if (chute.equals(palavraEscolhida)) {
                    jogadorAtual.setPontuacao(jogadorAtual.getPontuacao() + palavraEscolhida.length());
                    palavraAdivinhada = true;
                    imprimirMensagemParabens(jogadorAtual);
                    break;
                } else {
                    System.out.println("Chute incorreto. Tente novamente.");
                    jogadorAtual.setPontuacao(jogadorAtual.getPontuacao() - 5);
                    trocarJogador();
                }
            } else {
                System.out.println("Opção inválida. Tente novamente.");
            }

            palavraAdivinhada = palavraAdivinhada(palavraOculta);
            
            if (palavraAdivinhada) {
                imprimirMensagemParabens(jogadorAtual);
            }            
        }

        scanner.close();
    }

    private void trocarJogador() {
        jogadorAtual = (jogadorAtual == jogador1) ? jogador2 : jogador1;
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
                palavraEscolhida + " em " + jogadorAtual.getTentativas() + " tentativas." + "\nVocê fez");

                new Thread(() -> {
                    for (int i = 0; i < 3; i++) { // Vai imprimir 5 reticências (5 segundos)
                        System.out.print(".");  // Imprime uma reticência
                        try {
                            Thread.sleep(2000);  // Aguarda 1 segundo (1000 milissegundos)
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    // Após aguardar, imprime a pontuação final
                    System.out.println("\n"+jogadorAtual.getPontuacao() + " pontos !!!");
                }).start();
    }

   
    
}

