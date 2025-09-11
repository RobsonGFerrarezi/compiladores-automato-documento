import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // Usando try-with-resources para garantir que o Scanner seja fechado
        try (Scanner scan = new Scanner(System.in)) {
            boolean continuar = true;

            // Laço principal do menu
            while (continuar) {
                exibirMenu();
                try {
                    int escolha = scan.nextInt();
                    scan.nextLine(); // Limpa o buffer do scanner

                    switch (escolha) {
                        case 1:
                            validarDocumentoCPF(scan);
                            scan.nextLine(); // Limpa o buffer após a validação
                            break;
                        case 2:
                            validarDocumentoRG(scan);
                            scan.nextLine(); // Limpa o buffer após a validação
                            break;
                        case 0:
                            continuar = false;
                            System.out.println("Encerrando o programa. Até logo!");
                            break;
                        default:
                            System.out.println("Opção inválida! Por favor, tente novamente.");
                            break;
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Erro: Por favor, digite um número válido para a opção.");
                    scan.nextLine(); // Limpa o buffer em caso de erro
                }
                System.out.println(); // Linha em branco para melhor formatação
            }
        }
    }

    //função para exibir o menu, mantendo o código organizado
    private static void exibirMenu() {
        System.out.println("--- Validador de Documentos ---");
        System.out.println("1 - Validar CPF (formato: 000.000.000-00)");
        System.out.println("2 - Validar RG (formato: 00.000.000-0)");
        System.out.println("0 - Sair");
        System.out.print("Escolha uma opção: ");
    }

    private static void validarDocumentoCPF(Scanner scan) {
        System.out.print("Digite o CPF para validação: ");
        String numeroCpf = scan.nextLine();
        
        Cpf cpf = new Cpf(numeroCpf); // Cria um objeto Cpf
        
        if (cpf.isValido()) {
            System.out.println("Resultado: CPF VÁLIDO!");
        } else {
            System.out.println("Resultado: CPF INVÁLIDO!");
        }
    }

    private static void validarDocumentoRG(Scanner scan) {
        System.out.print("Digite o RG para validação: ");
        String numeroRg = scan.nextLine();
        
        Rg rg = new Rg(numeroRg); // Cria um objeto Rg
        
        if (rg.isValido()) {
            System.out.println("Resultado: RG VÁLIDO!");
        } else {
            System.out.println("Resultado: RG INVÁLIDO!");
        }
    }
}