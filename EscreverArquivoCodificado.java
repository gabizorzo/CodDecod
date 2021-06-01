import java.io.FileWriter;   // Import the FileWriter class
import java.io.IOException;  // Import the IOException class to handle errors

public class EscreverArquivoCodificado {
    public void execute() {
        try {
            FileWriter myWriter = new FileWriter("NovoCodificado.txt");
      
            myWriter.write(Decodificar.breakCode());
            myWriter.close();
            System.out.println("Escrito com sucesso no arquivo!\nO seu arquivo deverá estar na mesma pasta deste programa com o nome 'NovoCodificado.txt'.");
          } catch (IOException e) {
            System.out.println("Ocorreu um erro.");
            e.printStackTrace();
          }
    }
}
