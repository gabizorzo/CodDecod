import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Decodificar {
    private static Boolean[] hasLabel;

    public static String convertToLabel(String s){
        int indexLabel = (((int)(s.charAt(s.length()-1))-48) + 16*((int)(s.charAt(s.length()-2))-48))/4;

        hasLabel[indexLabel] = true;

        return "l"+indexLabel;
    }

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

        String label = convertToLabel(adress);

        return ("j " + label);
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

    public static String loadWord(String s){
        int rs = toDecimal(s.substring(6,11));
        int rt = toDecimal(s.substring(11,16));

        String adress = "$"+rt+",0x00000000($"+rs+")";

        return ("lw " + adress);
    }

    public static String branchEqual(String s, int k){
        int rs = toDecimal(s.substring(6,11));
        int rt = toDecimal(s.substring(11,16));
        // String label1 = toHexa(s.substring(16, 20));
        // String label2 = toHexa(s.substring(20, 24));
        // String label3 = toHexa(s.substring(24, 28));
        // String label4 = toHexa(s.substring(28));

        int indexLabel = toDecimal2s(s.substring(16))+k+1;

        hasLabel[indexLabel] = true;

        String adress = "$"+rs+",$"+rt+",l"+indexLabel;

        return ("beq " + adress);
    }

    public static String branchNotEqual(String s, int k){
        int rs = toDecimal(s.substring(6,11));
        int rt = toDecimal(s.substring(11,16));
        // String label1 = toHexa(s.substring(16, 20));
        // String label2 = toHexa(s.substring(20, 24));
        // String label3 = toHexa(s.substring(24, 28));
        // String label4 = toHexa(s.substring(28));

        int indexLabel = toDecimal2s(s.substring(16))+k+1;

        hasLabel[indexLabel] = true;

        String adress = "$"+rs+",$"+rt+",l"+indexLabel;

        return ("bne " + adress);
    }

    public static String orImmediate(String s){
        int rs = toDecimal(s.substring(6,11));
        int rt = toDecimal(s.substring(11,16));
        String imm1 = toHexa(s.substring(16, 20));
        String imm2 = toHexa(s.substring(20, 24));
        String imm3 = toHexa(s.substring(24, 28));
        String imm4 = toHexa(s.substring(28));

        String adress = "$"+rt+",$"+rs+",0x0000"+imm1+imm2+imm3+imm4; 

        return ("ori "+ adress);
    }

    public static String and(String s){
        int rs = toDecimal(s.substring(6,11));
        int rt = toDecimal(s.substring(11, 16));
        int rd = toDecimal(s.substring(16, 21));

        String adress = "$"+rd+",$"+rs+",$"+rt;

        return("and " + adress);
    }

    public static String shiftRightLogical(String s){
        int rt = toDecimal(s.substring(11,16));
        int rd = toDecimal(s.substring(16, 21));
        String shamt = toHexa(s.substring(22, 26)); 
        
        String adress = "$"+rd+",$"+rt+",0x0000000"+shamt;

        return("srl " + adress);
    }

    public static String setLessThanImmediate(String s){
        int rd = toDecimal(s.substring(11, 16));
        int rs = toDecimal(s.substring(6, 11)); 
        String imm1 = toHexa(s.substring(16, 20));
        String imm2 = toHexa(s.substring(20, 24));
        String imm3 = toHexa(s.substring(24, 28));
        String imm4 = toHexa(s.substring(28));

        String adress = "$"+rd+",$"+rs+",0x0000"+imm1+imm2+imm3+imm4;

        return("slti " + adress);
    }

    public static String funct(String s){
        String substring = s.substring(26);

        switch(substring){
            case "001000":
                return jumpRegister(s);
            case "100000":
                return add(s);
            case "100100":
                return and(s);
            case "000010":
                return shiftRightLogical(s);
            
        }
        return s;
    }

    public static String opcode(String s, int k){

        String substring = s.substring(0,6);

        switch(substring){
            case "000010":
                return jump(s);
            case "000100":
                return branchEqual(s, k);
            case "000101":
                return branchNotEqual(s, k);
            case "001010":
                return setLessThanImmediate(s);
            case "001101":
                return orImmediate(s);
            case "100011":
                return loadWord(s);
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

    public static int toDecimal2s(String s){
        int value = 0;
        String sAux = "";
        String sAux2 = "";
        Boolean negative = false;


        if (s.charAt(0) == '1'){
            negative = true;

            for (int i = 0; i < s.length(); i++){
                if (s.charAt(i) == '1'){
                    sAux += "0";
                } else {
                    sAux += "1";
                }
            }

            for (int i = s.length()-1; i >= 0; i--){
                if (sAux.charAt(i) == '1'){
                    sAux2 += "0";
                } else {
                    sAux2 += "1";
                    for (int j = i-1; j >=0; j--){
                        sAux2 += sAux.charAt(j);
                    }

                    StringBuilder sb = new StringBuilder(sAux2);
                    sb.reverse();
                    s = sb.toString();

                    break;
                }
            }
        }


        for (int i = 0; i < s.length(); i++){
            value += ((int)(s.charAt(i))-48) * (int)(Math.pow(2, s.length()-1-i));
        }

        if (negative) {
            value = -value;
        }

        return value;
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

    public static String breakCode() throws IOException{
        List<String> file = LeituraArquivo.scan();

        hasLabel = new Boolean[file.size()];
        hasLabel[0] = true;

        String line;
        List<String> binary = new ArrayList<String>();
        List<String> code = new ArrayList<String>();
        String newLine = "";

        for (int i = 0; i < file.size(); i++){
            hasLabel[i] = false;

            line = file.get(i);
            
            for(int j = 2; j < line.length(); j++){
                if(line.charAt(j) != ' '){
                    newLine += toBinary(line.charAt(j));
                }
            }
            binary.add(newLine);
            newLine = "";
        }

        for(int k=0; k < binary.size(); k++){
            code.add(opcode(binary.get(k), k));
        }

        // for(int k=0; k < code.size(); k++){
        //     System.out.println(code.get(k));
        // }

        String write = ".text\n.globl l0\n"; 

        for(int k=0; k < code.size(); k++){
            if (hasLabel[k]){
                write += "l" + k + ":\n";
            }

            write += code.get(k) + "\n";
        }        

        return write;
    } 

    public static void main (String[] args) throws IOException{
        breakCode();
    }


}
