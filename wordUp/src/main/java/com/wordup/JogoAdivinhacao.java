package com.wordup;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class JogoAdivinhacao {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        //System.out.println("Bem-vindo ao Jogo de Adivinhação de Palavras!");

        //System.out.print("Digite o nome do Jogador 1: ");
        Jogador jogador1 = new Jogador("Kedna");//Ao finalizar os testes colocar "scanner.next()" no lugar do nome entre parênteses

        //System.out.print("Digite o nome do Jogador 2: ");
        Jogador jogador2 = new Jogador("Emileny");//Ao finalizar os testes colocar "scanner.next()" no lugar do nome entre parênteses

        List<String> palavras = carregarPalavrasDoArquivo("WordUp\\palavras.txt");

        Jogo jogo = new Jogo(palavras, jogador1, jogador2);
        jogo.jogar(scanner);
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
