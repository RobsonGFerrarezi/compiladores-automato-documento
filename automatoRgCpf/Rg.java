public class Rg {

    private final String numero;

    public Rg(String numero) {
        this.numero = numero;
    }

    public boolean isValido() {
        // 1. Validação do formato ##.###.###-X
        //    ^             -> Início da string
        //    \\d{2}        -> Exatamente 2 dígitos numéricos
        //    \\.           -> Um ponto literal
        //    \\d{3}        -> Exatamente 3 dígitos numéricos
        //    -             -> Um hífen literal
        //    [0-9Xx]       -> Um dígito numérico ou X/x
        //    $             -> Fim da string
        if (!this.numero.matches("^\\d{2}\\.\\d{3}\\.\\d{3}-[0-9Xx]$")) {
            System.out.println("Formato inválido. O RG deve estar no formato 00.000.000-0.");
            return false;
        }

        // Remove a formatação para o cálculo
        String rgNumerico = this.numero.replaceAll("[^0-9Xx]", "");

        // O caractere 'X' só pode estar na última posição
        if (rgNumerico.substring(0, 8).toUpperCase().contains("X")) {
            return false;
        }

        // 2. Cálculo do Dígito Verificador (lógica original mantida)
        int total = 0;
        for (int i = 0; i < 8; i++) {
            // Converte o caractere em número
            int valor = Character.getNumericValue(rgNumerico.charAt(i));
            total += valor * (i + 2);
        }

        int resto = total % 11;
        int digitoCalculado = 11 - resto;

        char digitoFinal = rgNumerico.charAt(8);

        if (digitoCalculado == 10 && (digitoFinal == 'X' || digitoFinal == 'x')) {
            return true;
        }
        
        if (digitoCalculado == 11 && Character.getNumericValue(digitoFinal) == 0) {
             return true;
        }

        return digitoCalculado == Character.getNumericValue(digitoFinal);
    }
}