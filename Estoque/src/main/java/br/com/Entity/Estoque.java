package br.com.Entity;

public class Estoque {
    private long id;
    private String nome;
    private String descricao;
    private long quantidade;
    private double preco;

    public Estoque(long id, String nome, String descricao, long quantidade, double preco) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.quantidade = quantidade;
        this.preco = preco;
    }

    public long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public long getQuantidade() {
        return quantidade;
    }

    public double getPreco() {
        return preco;
    }

    public void setQuantidade(long quantidade) {
        this.quantidade = quantidade;
    }
}
