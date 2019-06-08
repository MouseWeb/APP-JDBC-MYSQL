package util;

import java.util.Random;

public abstract class GerarSenha {

    // Random é uma classe do Java que gera números aleatórios
    private static Random rand = new Random ();

    // Gera uma senha de 10 Caracteres esses caracteres podem ser digitos ou letras.

    // Método auxiliar que gera uma senha Aleatória
    public static String newPassword() {
        char[] vet = new char[10];
        for (int i = 0; i < 10; i++) {
            vet[i] = randomChar();
        }
        return new String(vet);
    }

    // Função que gera um caracter aleatório que pode ser digito ou letra
    // Códigos unicode: https://unicode-table.com/pt/#control-character
    private static char randomChar() {
        int opt = rand.nextInt(3);
        if (opt == 0) { // gera um digito
            return (char) (rand.nextInt(10) + 48);
        }
        else if (opt == 1) { // gera letra maiuscula
            return (char) (rand.nextInt(26) + 65);
        }
        else { // gera letra minuscula
            return (char) (rand.nextInt(26) + 97);
        }

    }

}
