/*  ORGANIZAÇÃO E ARQUITETURA DE COMPUTADORES I
    Prof. Fabiano Passuelo Hessel
    Turma 128
    Gabriela Zorzo e Morgana Weber
    Trabalho Prático 2
*/

import java.io.FileWriter;  
import java.io.IOException;  

public class EscreverArquivoCodificado {

    // Método para realizar a escrita do código codificado em um arquivo.
    // Este novo arquivo será salvo na mesma pasta do projeto com o nome NovoCodificado.txt.

    public void execute() {
        try {
            FileWriter myWriter = new FileWriter("NovoCodificado.txt");
      
            // É chamado o método para construir o código a partir da classe Codificar. 
            
            myWriter.write(Codificar.buildCode());
            myWriter.close();
            System.out.println("Escrito com sucesso no arquivo!\nO seu arquivo deverá estar na mesma pasta deste programa com o nome 'NovoCodificado.txt'.");
          } catch (IOException e) {
            System.out.println("Ocorreu um erro.");
            e.printStackTrace();
         }
    }
}
