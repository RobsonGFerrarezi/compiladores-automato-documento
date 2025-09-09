public class Cpf {

    private final String numero;

    public Cpf(String numero) {
        this.numero = numero;
    }

    public boolean isValido() {
        // 1. Validação do formato ###.###.###-##
        if (!this.numero.matches("^\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}$")) {
            System.out.println("Formato inválido. O CPF deve estar no formato 000.000.000-00.");
            return false;
        }

        // Remove a formatação para o cálculo
        String cpfNumerico = this.numero.replaceAll("[^0-9]", "");

        // 2. Verifica se todos os dígitos são iguais (ex: 111.111.111-11)
        if (cpfNumerico.matches("(\\d)\\1{10}")) {
            return false;
        }

        // 3. Cálculo dos Dígitos Verificadores
        try {
            // Cálculo do primeiro dígito
            int soma1 = 0;
            for (int i = 0; i < 9; i++) {
                soma1 += Character.getNumericValue(cpfNumerico.charAt(i)) * (10 - i);
            }
            int digito1 = 11 - (soma1 % 11);
            if (digito1 >= 10) {
                digito1 = 0;
            }

            // Cálculo do segundo dígito
            int soma2 = 0;
            for (int i = 0; i < 10; i++) { // CORREÇÃO: o loop vai até 10 agora
                soma2 += Character.getNumericValue(cpfNumerico.charAt(i)) * (11 - i);
            }
            int digito2 = 11 - (soma2 % 11);
            if (digito2 >= 10) {
                digito2 = 0;
            }

            // 4. Compara os dígitos calculados com os dígitos do CPF informado
            return digito1 == Character.getNumericValue(cpfNumerico.charAt(9)) &&
                   digito2 == Character.getNumericValue(cpfNumerico.charAt(10));

        } catch (Exception e) {
            return false;
        }
    }
}