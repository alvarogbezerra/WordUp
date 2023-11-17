package com.wordUp.wordUp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WordUpApplication {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(WordUpApplication.class, args);
		System.out.println("Hello, WordUp");

		Jogador j1 = new Jogador("João");

	}

}
