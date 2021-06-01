import java.io.FileWriter;   // Import the FileWriter class
import java.io.IOException;  // Import the IOException class to handle errors

public class EscreverArquivo {
  public static void main(String[] args) {
    try {
      FileWriter myWriter = new FileWriter("NovoDecodificado.asm");

      myWriter.write(Decodificar.breakCode());
      myWriter.close();
      System.out.println("Successfully wrote to the file.");
    } catch (IOException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
  }
}
