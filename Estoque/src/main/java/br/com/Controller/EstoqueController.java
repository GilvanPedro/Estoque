package br.com.Controller;


import br.com.Entity.Estoque;

import java.util.ArrayList;
import java.util.List;

public class EstoqueController {

    private long id = 1;
    private List<Estoque> estoqueGeral = new ArrayList<>();
    private MovimentacaoEstoqueController movimentacao;

    public EstoqueController(MovimentacaoEstoqueController movimentacao) {
        this.movimentacao = movimentacao;
    }

    public long gerarId() {
        return id++;
    }

    public void adicionarEstoque(String nome, long quantidade, String descricao, double preco) {
        Estoque estoque = new Estoque(gerarId(), nome, descricao, quantidade, preco);
        estoqueGeral.add(estoque);

        movimentacao.adicionarMovimentacao(nome, true, descricao, quantidade, preco);
        System.out.println("Produto adicionado com sucesso!");
    }

    public void removerEstoque(long id) {
        Estoque estoque = estoqueGeral.stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        movimentacao.adicionarMovimentacao(
                estoque.getNome(),
                false,
                estoque.getDescricao(),
                estoque.getQuantidade(),
                estoque.getPreco()
        );

        estoqueGeral.remove(estoque);
        System.out.println("Produto removido com sucesso!");
    }

    public void mostrarEstoque() {
        for (Estoque estoque : estoqueGeral) {
            System.out.println("ID: " + estoque.getId() +
                    " | Nome: " + estoque.getNome() +
                    " | Descrição: " + estoque.getDescricao() +
                    " | Quantidade: " + estoque.getQuantidade() +
                    " | Preço: " + estoque.getPreco());
        }
        if(estoqueGeral.isEmpty()){
            System.out.println("Nenhum produto foi encontrado!");
        }
    }

    public void removerQuantidade(long id, long quantidadeRemover) {
        Estoque estoque = estoqueGeral.stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        if (quantidadeRemover > estoque.getQuantidade()) {
            throw new RuntimeException("Estoque insuficiente");
        }
        estoque.setQuantidade(estoque.getQuantidade() - quantidadeRemover);
        System.out.println("Quantidade retirada com sucesso!");

        movimentacao.adicionarMovimentacao(
                estoque.getNome(),
                false,
                estoque.getDescricao(),
                quantidadeRemover,
                estoque.getPreco()
        );
    }

}
