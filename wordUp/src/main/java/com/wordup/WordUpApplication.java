package com.wordup;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

@SpringBootApplication
public class WordUpApplication {

    public static void main(String[] args) {
        SpringApplication.run(WordUpApplication.class, args);
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

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

        String nomeJogador1 = "";
        String nomeJogador2 = "";

        scanner.nextLine();

        if (modoJogo == 1) {
            System.out.print("Digite o seu nome: ");
            nomeJogador1 = scanner.nextLine();
        } else {
            System.out.print("Digite o nome do Jogador 1: ");
            nomeJogador1 = scanner.nextLine();

            System.out.print("Digite o nome do Jogador 2: ");
            nomeJogador2 = scanner.nextLine();
        }

        List<String> palavras = carregarPalavrasDoArquivo("WordUp\\palavras.txt");


        String palavraEscolhida = palavras.get(random.nextInt(palavras.size())).toLowerCase();
        char[] palavraOculta = new char[palavraEscolhida.length()];

        for (int i = 0; i < palavraOculta.length; i++) {
            palavraOculta[i] = '_';
        }

        System.out.println("Tente adivinhar a palavra:");

        boolean palavraAdivinhada = false;
        int tentativas = 0;

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

    private static boolean palavraAdivinhada(char[] palavraOculta) {
        for (char c : palavraOculta) {
            if (c == '_') {
                return false; 
            }
        }
        return true; 
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

    private static List<String> carregarPalavrasDoArquivo(String nomeArquivo) {
        List<String> palavras = new ArrayList<>();

        try (Scanner scanner = new Scanner(new File(nomeArquivo))) {
            while (scanner.hasNextLine()) {
                palavras.add(scanner.nextLine().trim());
            }
        } catch (FileNotFoundException e) {
            System.err.println("Erro ao carregar o arquivo de palavras: " + e.getMessage());
        }

        return palavras;
    }
}
