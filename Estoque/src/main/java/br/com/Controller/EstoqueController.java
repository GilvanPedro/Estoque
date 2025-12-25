package br.com.Controller;

import br.com.Entity.Estoque;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class EstoqueController {

    private List<Estoque> estoqueGeral = new ArrayList<>();
    private final String path = "estoque.txt";
    private MovimentacaoEstoqueController movimentacao;

    public EstoqueController(MovimentacaoEstoqueController movimentacao) {
        this.movimentacao = movimentacao;
        carregarEstoque();
    }

    // ================== CADASTRAR ==================
    public void adicionarEstoque(String nome, long quantidade, String descricao, double preco) {

        if (produtoJaExiste(nome)) {
            System.out.println("Produto já cadastrado!");
            return;
        }

        long id = gerarId();
        Estoque estoque = new Estoque(id, nome, descricao, quantidade, preco);
        estoqueGeral.add(estoque);

        escreverEstoque(id, nome, descricao, quantidade, preco);
        registrarMovimentacao(nome, true, descricao, quantidade, preco);

        System.out.println("Produto cadastrado com sucesso!");
    }

    // ================== MOSTRAR ==================
    public void mostrarEstoque() {
        carregarEstoque();

        if (estoqueGeral.isEmpty()) {
            System.out.println("Nenhum produto cadastrado.");
            return;
        }

        System.out.println("\n=-=-=-=-=-=- ESTOQUE =-=-=-=-=-=-");
        for (Estoque e : estoqueGeral) {
            System.out.println(
                    "ID: " + e.getId() +
                            " | Nome: " + e.getNome() +
                            " | Descrição: " + e.getDescricao() +
                            " | Quantidade: " + e.getQuantidade() +
                            " | Preço: " + e.getPreco()
            );
        }
    }

    // ================== REMOVER PRODUTO ==================
    public void removerEstoque(long id) {
        carregarEstoque();

        Estoque e = buscarPorId(id);
        estoqueGeral.remove(e);
        reescreverArquivo();

        registrarMovimentacao(e.getNome(), false, e.getDescricao(), e.getQuantidade(), e.getPreco());
        System.out.println("Produto removido com sucesso!");
    }

    // ================== REMOVER QUANTIDADE ==================
    public void removerQuantidade(long id, long qtd) {
        carregarEstoque();

        Estoque e = buscarPorId(id);

        if (qtd > e.getQuantidade()) {
            System.out.println("Estoque insuficiente!");
            return;
        }

        e.setQuantidade(e.getQuantidade() - qtd);

        registrarMovimentacao(e.getNome(), false, e.getDescricao(), qtd, e.getPreco());

        if (e.getQuantidade() == 0) {
            estoqueGeral.remove(e);
        }

        reescreverArquivo();
        System.out.println("Quantidade retirada!");
    }

    // ================== UTIL ==================
    private long gerarId() {
        long maior = 0;
        for (Estoque e : estoqueGeral) {
            if (e.getId() > maior) maior = e.getId();
        }
        return maior + 1;
    }

    private boolean produtoJaExiste(String nome) {
        carregarEstoque();
        return estoqueGeral.stream().anyMatch(p -> p.getNome().equalsIgnoreCase(nome));
    }

    private Estoque buscarPorId(long id) {
        return estoqueGeral.stream()
                .filter(e -> e.getId() == id)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));
    }

    // ================== ARQUIVO ==================
    private void escreverEstoque(long id, String nome, String desc, long qtd, double preco) {
        try (FileWriter fw = new FileWriter(path, true)) {
            fw.write(id + ";" + nome + ";" + desc + ";" + qtd + ";" + preco + "\n");
        } catch (IOException e) {
            System.out.println("Erro ao escrever estoque!");
        }
    }

    private void carregarEstoque() {
        estoqueGeral.clear();
        File file = new File(path);
        if (!file.exists()) return;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String linha;
            while ((linha = br.readLine()) != null) {

                if (linha.trim().isEmpty()) continue;

                String[] d = linha.split(";");
                if (d.length < 5) continue;

                long id = Long.parseLong(d[0]);
                String nome = d[1];
                String desc = d[2];
                long qtd = Long.parseLong(d[3]);
                double preco = Double.parseDouble(d[4]);

                estoqueGeral.add(new Estoque(id, nome, desc, qtd, preco));
            }
        } catch (IOException e) {
            System.out.println("Erro ao carregar estoque!");
        }
    }

    private void reescreverArquivo() {
        try (FileWriter fw = new FileWriter(path, false)) {
            for (Estoque e : estoqueGeral) {
                fw.write(e.getId() + ";" + e.getNome() + ";" + e.getDescricao() + ";" +
                        e.getQuantidade() + ";" + e.getPreco() + "\n");
            }
        } catch (IOException e) {
            System.out.println("Erro ao reescrever estoque!");
        }
    }

    private void registrarMovimentacao(String nome, boolean entrada, String desc, long qtd, double preco) {
        if (movimentacao != null) {
            movimentacao.adicionarMovimentacao(nome, entrada, desc, qtd, preco);
        }
    }
}
