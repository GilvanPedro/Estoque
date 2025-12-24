package br.com;

import br.com.Controller.EstoqueController;
import br.com.Controller.MovimentacaoEstoqueController;

import java.util.Scanner;

public class GerenciarEstoque {
    public void controleEstoque(){
        Scanner sc = new Scanner(System.in);
        MovimentacaoEstoqueController movimentacaoController = new MovimentacaoEstoqueController();
        EstoqueController controller = new EstoqueController(movimentacaoController);

        String opc = "";
        System.out.println("================= GERENCIAR ESTOQUE =================");

        while(!opc.equals("SAIR")){
            System.out.println("O que deseja fazer?\nADICIONAR - adiciona um item no estoque\nEXIBIR - exibe os produtos cadastrados no estoque\nREMOVER - remove um item do estoque\nDIMINUIR - diminui a quantidade de um dos item do estoque\nSAIR - encerra a aplicação");
            opc = sc.nextLine().toUpperCase();

            if(opc.equals("ADICIONAR")){
                System.out.print("\nNome do produto: ");
                String nome = sc.nextLine();
                System.out.print("Quantidade do produto: ");
                long quantidade = sc.nextLong();
                System.out.print("Preço Unitário: ");
                double preco = sc.nextDouble();
                System.out.print("Descrição do Produto: ");
                String descricao = sc.nextLine();
                sc.nextLine();

                controller.adicionarEstoque(nome, quantidade, descricao, preco);
            } else if(opc.equals("EXIBIR")){
                System.out.println("\n================= ESTOQUE =================");
                controller.mostrarEstoque();
            } else if(opc.equals("REMOVER")){
                System.out.print("\nDigite o ID do produto que deseja remover: ");
                long remover = sc.nextLong();
                sc.nextLine();

                controller.removerEstoque(remover);
            } else if(opc.equals("DIMINUIR")){
                System.out.print("\nDigite o ID do produto que deseja diminuir a quantidade: ");
                long diminuirId = sc.nextLong();
                System.out.print("Digite a quantidade que retirou: ");
                long retirar = sc.nextLong();
                controller.removerQuantidade(diminuirId, retirar);
                sc.nextLine();
            } else if(opc.equals("SAIR")){
                System.out.println("Saindo.......");
            }
            System.out.println();
        }
    }
}
