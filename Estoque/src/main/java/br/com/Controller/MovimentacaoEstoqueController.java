package br.com.Controller;

import br.com.Entity.Estoque;
import br.com.Entity.MovimentacaoEstoque;

import java.util.ArrayList;
import java.util.List;

public class MovimentacaoEstoqueController {
    List<MovimentacaoEstoque> listaMovimentacao = new ArrayList();
    MovimentacaoEstoque movimentacaoEstoque;

    public void adicionarMovimentacao(String nome, boolean tipo, String descricao, long quantidade, double preco){
        String tipoString;
        if(tipo == true){
            tipoString = "Adicionando";
        } else{
            tipoString = "Removendo";
        }
        movimentacaoEstoque = new MovimentacaoEstoque(nome, tipoString, descricao, quantidade, preco);
        listaMovimentacao.add(movimentacaoEstoque);
    }

    public void mostrarMovimentacao(){
        for(MovimentacaoEstoque movimentacao : listaMovimentacao){
            System.out.println(movimentacao.getTipo()+" o produto: "+movimentacao.getNome()+" na quantidade de "+movimentacao.getQuantidade()+", com o preço unitário de "+movimentacao.getPreco());
        }
    }
}
