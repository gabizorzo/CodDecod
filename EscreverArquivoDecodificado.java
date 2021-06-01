import java.io.FileWriter;   // Import the FileWriter class
import java.io.IOException;  // Import the IOException class to handle errors

public class EscreverArquivoDecodificado {
    public void execute() {
        try {
            FileWriter myWriter = new FileWriter("NovoDecodificado.asm");
      
            myWriter.write(Decodificar.breakCode());
            myWriter.close();
            System.out.println("Escrito com sucesso no arquivo!\nO seu arquivo deverá estar na mesma pasta deste programa com o nome 'NovoDecodificado.asm'.");
          } catch (IOException e) {
            System.out.println("Ocorreu um erro.");
            e.printStackTrace();
          }
    }
}
