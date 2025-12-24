package br.com.Entity;

public class MovimentacaoEstoque{
    private String nome;
    private String tipo;
    private String descricao;
    private long quantidade;
    private double preco;

    public MovimentacaoEstoque(String nome, String tipo, String descricao, long quantidade, double preco) {
        this.nome = nome;
        this.tipo = tipo;
        this.descricao = descricao;
        this.quantidade = quantidade;
        this.preco = preco;
    }

    public String getNome() {
        return nome;
    }

    public String getTipo() {
        return tipo;
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
}
