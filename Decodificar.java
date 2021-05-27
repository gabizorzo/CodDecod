import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Decodificar {
    public static String funct(String s){
        switch(s){
            case "001000":
                return "jr ";
            case "100000":
                return "add ";
            case "100100":
                return "and ";
            case "000010":
                return "srl ";
            
        }
        return s;
    }

    public static String opcode(String s){

        String substring = s.substring(0,6);

        switch(substring){
            case "000010":
                return "j ";
            case "000100":
                return "beq ";
            case "000101":
                return "bne ";
            case "001010":
                return "slti ";
            case "001101":
                return "ori ";
            case "100011":
                return "lw ";
            case "000000":
                return funct(s.substring(26));

        }

        return s;
    }

    public static String toBinary(char c) {
        switch(c){
            case '0':
                return "0000";
            case '1':
                return "0001";
            case '2':
                return "0010"; 
            case '3':
                return "0011";
            case '4':
                return "0100";
            case '5':
                return "0101";
            case '6':
                return "0110";
            case '7':
                return "0111";
            case '8':
                return "1000";
            case '9':
                return "1001";
            case 'a':
                return "1010";
            case 'b':
                return "1011";
            case 'c':
                return "1100";
            case 'd':
                return "1101";
            case 'e':
                return "1110";
            case 'f':
                return "1111";
        }
        return null;
    }

    public static void breakCode() throws IOException{
        List<String> file = LeituraArquivo.scan();

       // System.out.println(file.get(0));

        String line;
        List<String> binary = new ArrayList<String>();
        List<String> code = new ArrayList<String>();
        String newLine = "";

        for (int i = 0; i < file.size(); i++){
            line = file.get(i);
            
            for(int j = 2; j < line.length(); j++){
                if(line.charAt(j) != ' '){
                    newLine += toBinary(line.charAt(j));
                }
            }
            binary.add(newLine);
            newLine = "";
        }

        //String substring = "";

        for(int k=0; k < binary.size(); k++){
           // substring = aux.get(k).substring(0,6);
            code.add(opcode(binary.get(k)));
          //  System.out.println(aux.get(k));
        }

        for(int k=0; k < code.size(); k++){
            System.out.println(code.get(k));
        }

        
        
    } 


    public static void main (String[] args) throws IOException{
        breakCode();
    }


}
