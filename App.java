/*  ORGANIZAÇÃO E ARQUITETURA DE COMPUTADORES I
    Prof. Fabiano Passuelo Hessel
    Turma 128
    Gabriela Zorzo e Morgana Weber
    Trabalho Prático 2
*/


import java.util.Scanner;

public class App {

    // Main onde o programa é executado
    public static void main (String[] args){
        Scanner in = new Scanner(System.in);

        // Menu de opções para o usuário escolher o que deseja fazer: codificar ou decodificar um arquivo
        System.out.println("Olá! Você gostaria de:");
        System.out.println("1. Ler um arquivo codificado (.txt) e gerar um arquivo em Assembly (.asm).");
        System.out.println("2. Ler um arquivo em Assembly (.asm) e gerar um arquivo codificado (.txt).");

        int command = in.nextInt();

        if (command == 1){
            EscreverArquivoDecodificado decodificar = new EscreverArquivoDecodificado();

            decodificar.execute();
        } else if (command == 2){
            EscreverArquivoCodificado codificar = new EscreverArquivoCodificado();

            codificar.execute();
        }

        in.close();

    }
}
