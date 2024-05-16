package br.senac.rj.empresa.teste;

import br.senac.rj.empresa.modelo.Material;

import java.util.Scanner;

public final class TesteMaterial1 {
    public static int pegarInt() {
        Scanner sc = new Scanner(System.in);
        String respS = sc.nextLine();
        int respI;
        try {
            respI = Integer.parseInt(respS);
        } catch (Exception e) {
            System.out.println("Erro: valor inválido. Digite um número: ");
            return pegarInt();
        }
        return respI;
    }

    public static void main(String[] args) {
        final String msg = """
                1 - Cadastrar material
                2 - Entrada de material
                3 - Saída de material
                4 - Consultar saldo em estoque
                5 - Encerrar
                """;
        Material mat1 = new Material();
        try (Scanner sc = new Scanner(System.in)) {
            System.out.println("Sistema Iniciando...");
            while (true) {
                System.out.printf("Escolha dentre as opções: \n%s", msg);
                int respInt = pegarInt();
                switch (respInt) {
                    case 1: {
                        System.out.println("Digite o código do material a ser adicionado: ");
                        int valor = Math.abs(pegarInt());
                        mat1.setCodMaterial(valor);
                        System.out.println("Digite a Descrição do material:");
                        String desc = sc.nextLine();
                        mat1.setDescMaterial(desc);
                        break;
                    }
                    case 2: {
                        System.out.println("Digite a quantidade de material a ser inserida: ");
                        int valor = pegarInt();
                        mat1.entrarMaterial(valor);
                        break;
                    }
                    case 3: {
                        System.out.println("Digite a quantidade de material a ser removida: ");
                        int valor = pegarInt();
                        mat1.sairMaterial(valor);
                        break;
                    }
                    case 4: {
                        System.out.printf("\n\nCódigo: %d\nDescrição: %s\nQuantidade em estoque do material: %d\n\n",
                                mat1.getCodMaterial(), mat1.getDescMaterial(), mat1.getQtEstoque());
                        break;
                    }
                    case 5: {
                        return;
                    }
                    default:
                        System.out.println("Opção incorreta!");
                        break;
                }
            }
        }
    }
}
