package Automato_rg_cpf;

import java.util.Scanner;

public class automato {

    // Método para validar RG. Recebe uma string e retorna true se o RG for válido.
    public static boolean validarRG(String doc) {
        int total = 0;
        // Laço para multiplicar cada dígito do RG por um peso e somar ao total
        for (int i = 0; i < 8; i++) {
            int valor = Character.getNumericValue(doc.charAt(i));
            total += valor * (i + 2);
        }
        // Calcula o dígito verificador
        int conta = 11 - (total % 11);
        char digitoFinal = doc.charAt(8);

        // Se o dígito final for X/x e a conta for 10, retorna true
        if (conta == 10 && (digitoFinal == 'X' || digitoFinal == 'x')) {
            return true;
        } else
            // Retorna true se o dígito final for igual ao calculado
            return conta == Character.getNumericValue(digitoFinal);
    }

    // Método para validar CPF. Recebe uma string e retorna true se o CPF for válido.
    public static boolean validarCPF(String doc) {
        int total1 = 0, total2 = 0;
        // Calcula o primeiro dígito verificador
        for (int i = 0; i < 9; i++) {
            int valor = Character.getNumericValue(doc.charAt(i));
            total1 += valor * (10 - i);
        }
        int digito1 = 11 - (total1 % 11);
        if (digito1 >= 10) {
            digito1 = 0;
        }
        // Calcula o segundo dígito verificador
        for (int i = 1; i < 10; i++) {
            int valor = Character.getNumericValue(doc.charAt(i));
            total2 += valor * (11 - i);
        }
        int digito2 = 11 - (total2 % 11);
        if (digito2 >= 10) {
            digito2 = 0;
        }
        // Retorna true se os dígitos calculados forem iguais aos informados
        return digito1 == Character.getNumericValue(doc.charAt(9))
                && digito2 == Character.getNumericValue(doc.charAt(10));
    }

    public static void main(String[] args) {
        // Utiliza try-with-resources para garantir o fechamento do Scanner
        try (Scanner scan = new Scanner(System.in)) {
            String sair;
            // Laço principal: repete até o usuário digitar 'N' ou 'n' para sair
            do {
                System.out.print("Digite seu RG ou CPF: ");
                String Documento = scan.next();
                // Remove tudo que não seja número ou 'X'/'x'
                Documento = Documento.replaceAll("[^0-9Xx]", "");
                int tamanho = Documento.length();
                String tipoDocu;
                // Define o tipo de documento pelo tamanho
                switch (tamanho) {
                    case 9:
                        tipoDocu = "rg";
                        break;
                    case 11:
                        tipoDocu = "cpf";
                        break;
                    default:
                        tipoDocu = "inválido";
                        break;
                }
                boolean docuValido = false;
                boolean erroX = false;
                // Validação de X fora do dígito final do RG
                if (tipoDocu.equals("rg")) {
                    // Se houver 'X' nos 8 primeiros dígitos, erro
                    if (Documento.substring(0, 8).toUpperCase().contains("X")) {
                        erroX = true;
                        System.out.println("Erro: O caractere 'X' só pode ser usado no dígito final do RG!");
                    } else {
                        docuValido = validarRG(Documento);
                    }
                } else if (tipoDocu.equals("cpf")) {
                    // Se houver 'X' em qualquer posição do CPF, erro
                    if (Documento.toUpperCase().contains("X")) {
                        erroX = true;
                        System.out.println("Erro: O caractere 'X' não é permitido em CPF!");
                    } else {
                        docuValido = validarCPF(Documento);
                    }
                } else {
                    // Documento inválido (tamanho diferente de 9 ou 11)
                    System.out.println("Documento inválido!!");
                }
                // Exibe resultado se não houver erro de X e tipo válido
                if (!tipoDocu.equals("inválido") && !erroX) {
                    String documentoValido = docuValido ? "Documento VÁLIDO" : "Documento INVÁLIDO";
                    System.out.println(tipoDocu + ": " + Documento + " | " + documentoValido);
                }
                // Pergunta se deseja continuar
                System.out.print("Deseja testar outro documento? (N para sair): ");
                sair = scan.next();
            } while (!sair.equalsIgnoreCase("N"));
        } catch (Exception e) {
            // Captura qualquer exceção e exibe mensagem de erro
            System.out.println("Erro ao processar o documento: " + e.getMessage());
        }
    }
}