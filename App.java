import java.util.Scanner;

public class App {
    public static void main (String[] args){
        Scanner in = new Scanner(System.in);

        System.out.println("Olá! Você gostaria de:");
        System.out.println("1. Ler um arquivo codificado (.txt) e gerar um arquivo em Assembly (.asm).");
        System.out.println("2. Ler um arquivo em Assembly (.asm) e gerar um arquivo codificado (.txt).");

        int command = in.nextInt();

        if (command == 1){
            EscreverArquivoDecodificado decodificar = new EscreverArquivoDecodificado();

            decodificar.execute();
        }

        in.close();

    }
}
