/*  ORGANIZAÇÃO E ARQUITETURA DE COMPUTADORES I
    Prof. Fabiano Passuelo Hessel
    Turma 128
    Gabriela Zorzo e Morgana Weber
    Trabalho Prático 2
*/

import java.io.FileWriter;   
import java.io.IOException;  

public class EscreverArquivoDecodificado {

    // Método para realizar a escrita do código decodificado em um arquivo.
    // Este novo arquivo será salvo na mesma pasta do projeto com o nome NovoDecodificado.asm.

    public void execute() {
        try {
            FileWriter myWriter = new FileWriter("NovoDecodificado.asm");

            // É chamado o método para quebrar o código a partir da classe Decodificar. 
      
            myWriter.write(Decodificar.breakCode());
            myWriter.close();
            System.out.println("Escrito com sucesso no arquivo!\nO seu arquivo deverá estar na mesma pasta deste programa com o nome 'NovoDecodificado.asm'.");
          } catch (IOException e) {
            System.out.println("Ocorreu um erro.");
            e.printStackTrace();
          }
    }
}
