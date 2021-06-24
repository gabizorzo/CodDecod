
/*  ORGANIZAÇÃO E ARQUITETURA DE COMPUTADORES I
    Prof. Fabiano Passuelo Hessel
    Turma 128
    Gabriela Zorzo e Morgana Weber
    Trabalho Prático 2
*/

import java.io.IOException;
import java.util.Scanner;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class LeituraArquivo {

    // Método para realizar a leitura dos arquivos .txt e .asm

    public static List<String> scan() throws IOException{
        Scanner in = new Scanner(System.in);

        // O nome do arquivo será inserido via teclado pelo usuário, para que ele não precise alterar isso no código

        System.out.print("Nome do arquivo a ser lido (colocar extensão no final): ");
        String file = in.next();
        Scanner scanner = new Scanner(new File(file));

        // Criação de um ArrayList para armazenar cada linha lida do arquivo em uma posição

        List<String> line = new ArrayList<String>();

        scanner.useDelimiter("\n");

        while(scanner.hasNext()){
            String next = scanner.next();
            line.add(next);
        }
        scanner.close();

        in.close();

        // Retorna o vetor com todas as linhas do arquivo lidas
        
        return line;
    }

    public static void main(String[] args) throws IOException{
        scan();
    }


}