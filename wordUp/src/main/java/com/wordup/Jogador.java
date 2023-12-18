package com.wordup;

public class Jogador {
    private String nome;
    private int pontuacao;
    private int tentativas;

    public Jogador(String nome) {
        this.nome = nome;
        this.pontuacao = 0;
        this.tentativas = 0;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getPontuacao() {
        return pontuacao;
    }

    public void setPontuacao(int pontuacao) {
        this.pontuacao = pontuacao;
    }

    public int getTentativas() {
        return tentativas;
    }

    public void setTentativas(int tentativas) {
        this.tentativas = tentativas;
    }
}
