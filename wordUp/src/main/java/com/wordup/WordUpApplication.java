package com.wordup;

//import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/*
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
*/

@SpringBootApplication
public class WordUpApplication {

    public static void main(String[] args) {/*
        SpringApplication.run(WordUpApplication.class, args);
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        Jogo Jogo = new Jogo(null, null, null);

        //abertura
        System.out.println("Bem-vindo ao Jogo de Adivinhação de Palavras!");
        int modoJogo = 0;

        while (modoJogo != 1 && modoJogo != 2) {
            System.out.print("Escolha o modo de jogo (1 - Jogar sozinho, 2 - Jogar com outra pessoa): ");
            if (scanner.hasNextInt()) {
                modoJogo = scanner.nextInt();
            } else {
                System.out.println("Por favor, insira um número válido.");
                scanner.next();
            }
        }
        
        if (modoJogo == 1) {
            Jogador jogador1 = new Jogador(scanner.next()); 
        
            System.out.print("Digite o seu nome: ");
            jogador1.setNome(scanner.next()); 
        } else if (modoJogo == 2) {
            Jogador jogador1 = new Jogador(scanner.next());
            Jogador jogador2 = new Jogador(scanner.next());
        
            System.out.print("Digite o nome do Jogador 1: ");
            jogador1.setNome(scanner.next());
        
            System.out.print("Digite o nome do Jogador 2: ");
            jogador2.setNome(scanner.next());
        }
        
        List<String> palavras = carregarPalavrasDoArquivo("WordUp\\palavras.txt");

        String palavraEscolhida = palavras.get(random.nextInt(palavras.size())).toLowerCase();
        char[] palavraOculta = new char[palavraEscolhida.length()];

        for (int i = 0; i < palavraOculta.length; i++) {
            palavraOculta[i] = '_';
        }

        System.out.println("Tente adivinhar a palavra:");

        boolean palavraAdivinhada = false;
        //int tentativas = 0; //desabilitei para usar a OO

        System.out.println(palavraEscolhida);//para facilitar o teste de mesa

        while (!palavraAdivinhada) {
            System.out.println("Palavra: " + String.valueOf(palavraOculta));
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
                }
            } else if (opcao == 'p') {
                System.out.print("Chute a palavra: ");
                String chute = scanner.next().toLowerCase();
        
                if (chute.equals(palavraEscolhida)) {
                    palavraAdivinhada = true;
                } else {
                    System.out.println("Chute incorreto. Tente novamente.");
                }
            } else {
                System.out.println("Opção inválida. Tente novamente.");
            }

            tentativas++;

            palavraAdivinhada = palavraAdivinhada(palavraOculta);

            if (palavraAdivinhada) {
                System.out.println("Parabéns! Você adivinhou a palavra \"" + palavraEscolhida + "\" em " + tentativas + " tentativas.");
                atribuirPontuacao(nomeJogador1, nomeJogador2, tentativas);
                exibirPontuacao(nomeJogador1, nomeJogador2);
            }
        }

        scanner.close();
    }
    



    private static void atribuirPontuacao(String nomeJogador1, String nomeJogador2, int tentativas) {
        if (tentativas % 2 == 0) {
            System.out.println(nomeJogador1 + " ganhou 1 ponto!");
        } else {
            System.out.println(nomeJogador2 + " ganhou 1 ponto!");
        }
    }

    private static void exibirPontuacao(String nomeJogador1, String nomeJogador2) {
        // Exibir pontuação aqui, se desejar
    }
    */
}}
