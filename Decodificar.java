import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Decodificar {
    public static String jump(String s){
        String substring = s.substring(6);
        String sub = "0000" + substring + "00";

        String adress = "0x" + toHexa(sub.substring(0,4)) 
                        + toHexa(sub.substring(4,8)) 
                        + toHexa(sub.substring(8,12)) 
                        + toHexa(sub.substring(12,16)) 
                        + toHexa(sub.substring(16,20))
                        + toHexa(sub.substring(20,24))
                        + toHexa(sub.substring(24,28))
                        + toHexa(sub.substring(28));

        return ("j " + adress);
    }

    public static String jumpRegister(String s){
        String substring = s.substring(6,11);

        int register = toDecimal(substring);

        String adress = "$" + register;

        return ("jr " + adress);
    }

    public static String add(String s){
        int rs = toDecimal(s.substring(6,11));
        int rt = toDecimal(s.substring(11,16));  
        int rd = toDecimal(s.substring(16,21));
        
        String adress = "$"+rd+",$"+rs+",$"+rt;

        return ("add " + adress);
    }

    public static String funct(String s){
        String substring = s.substring(26);

        switch(substring){
            case "001000":
                return jumpRegister(s);
            case "100000":
                return add(s);
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
                return jump(s);
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
                return funct(s);

        }

        return s;
    }

    public static int toDecimal(String s){
        int value[] = new int[s.length()];
        
        for (int i = 0; i < s.length(); i++){
            value[i] = (int)(s.charAt(i))-48;
        }

        return ((int)(Math.pow(2, 0) * value[4] + Math.pow(2, 1) * value[3] + Math.pow(2, 2) * value[2] + Math.pow(2, 3) * value[1] + Math.pow(2, 4) * value[0]));
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

    public static String toHexa(String s) {
        switch(s){
            case "0000":
                return "0";
            case "0001":
                return "1";
            case "0010":
                return "2"; 
            case "0011":
                return "3";
            case "0100":
                return "4";
            case "0101":
                return "5";
            case "0110":
                return "6";
            case "0111":
                return "7";
            case "1000":
                return "8";
            case "1001":
                return "9";
            case "1010":
                return "a";
            case "1011":
                return "b";
            case "1100":
                return "c";
            case "1101":
                return "d";
            case "1110":
                return "e";
            case "1111":
                return "f";
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
