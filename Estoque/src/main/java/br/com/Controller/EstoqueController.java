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

    private long gerarId() {
        return id++;
    }

    public void adicionarEstoque(String nome, long quantidade, String descricao, double preco) {
        Estoque estoque = new Estoque(gerarId(), nome, descricao, quantidade, preco);
        estoqueGeral.add(estoque);

        registrarMovimentacao(nome, true, descricao, quantidade, preco);
        System.out.println("Produto adicionado com sucesso!");
    }

    public void removerEstoque(long id) {
        Estoque estoque = buscarPorId(id);

        registrarMovimentacao(
                estoque.getNome(),
                false,
                estoque.getDescricao(),
                estoque.getQuantidade(),
                estoque.getPreco()
        );

        estoqueGeral.remove(estoque);
        System.out.println("Produto removido com sucesso!");
    }

    public void removerQuantidade(long id, long quantidadeRemover) {
        Estoque estoque = buscarPorId(id);

        if (quantidadeRemover > estoque.getQuantidade()) {
            throw new RuntimeException("Estoque insuficiente");
        }

        long novaQtd = estoque.getQuantidade() - quantidadeRemover;
        estoque.setQuantidade(novaQtd);

        registrarMovimentacao(
                estoque.getNome(),
                false,
                estoque.getDescricao(),
                quantidadeRemover,
                estoque.getPreco()
        );

        if (novaQtd == 0) {
            estoqueGeral.remove(estoque);
            System.out.println("Produto zerado e removido do estoque.");
        } else {
            System.out.println("Quantidade retirada com sucesso!");
        }
    }

    public void mostrarEstoque() {
        if (estoqueGeral.isEmpty()) {
            System.out.println("Nenhum produto foi encontrado!");
            return;
        }

        for (Estoque estoque : estoqueGeral) {
            System.out.println(
                    "ID: " + estoque.getId() +
                            " | Nome: " + estoque.getNome() +
                            " | Descrição: " + estoque.getDescricao() +
                            " | Quantidade: " + estoque.getQuantidade() +
                            " | Preço: " + estoque.getPreco()
            );
        }
    }

    private Estoque buscarPorId(long id) {
        return estoqueGeral.stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));
    }

    private void registrarMovimentacao(String nome, boolean entrada, String desc, long qtd, double preco) {
        if (movimentacao != null) {
            movimentacao.adicionarMovimentacao(nome, entrada, desc, qtd, preco);
        }
    }
}
