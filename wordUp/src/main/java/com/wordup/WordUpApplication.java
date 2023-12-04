package com.wordup;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WordUpApplication {

	public static void main(String[] args) {
		SpringApplication.run(WordUpApplication.class, args);

		Jogador j1 = new Jogador("João");

	}

}
