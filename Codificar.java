import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Codificar {
    private static List<String> codeResult = new ArrayList<>();

    public static String jump(String s){
        String adress = s.substring(2);
        String binary = "";

        for(int i = 0; i<adress.length(); i++){
            binary += toBinary(adress.charAt(i));
        }
        
        binary = "000010" + binary.substring(4, 30);

        String code = "0x"+ toHexa(binary.substring(0, 4))
                          + toHexa(binary.substring(4, 8))
                          + toHexa(binary.substring(8, 12))
                          + toHexa(binary.substring(12, 16))
                          + toHexa(binary.substring(16, 20))
                          + toHexa(binary.substring(20, 24))
                          + toHexa(binary.substring(24, 28))
                          + toHexa(binary.substring(28));

        codeResult.add(code);

        return code;
    }

    public static String jumpRegister(String s){
        String rs = s.substring(1);
        rs = calculateToBinary(rs);

        String binary = "000000" + rs + "0000000000000000" + "01000"; 

        String code = "0x"  + toHexa(binary.substring(0, 4))
                            + toHexa(binary.substring(4, 8))
                            + toHexa(binary.substring(8, 12))
                            + toHexa(binary.substring(12, 16))
                            + toHexa(binary.substring(16, 20))
                            + toHexa(binary.substring(20, 24))
                            + toHexa(binary.substring(24, 28))
                            + toHexa(binary.substring(28));

        codeResult.add(code);

        return code;
    }

    public static String add(String s){
        String[] vetor = s.split(",");
        String rd = calculateToBinary(vetor[0].substring(1));
        String rs = calculateToBinary(vetor[1].substring(1));
        String rt = calculateToBinary(vetor[2].substring(1));

        String binary = "000000" + rs + rt + rd + "00000" + "100000";


        String code = "0x"  + toHexa(binary.substring(0, 4))
                            + toHexa(binary.substring(4, 8))
                            + toHexa(binary.substring(8, 12))
                            + toHexa(binary.substring(12, 16))
                            + toHexa(binary.substring(16, 20))
                            + toHexa(binary.substring(20, 24))
                            + toHexa(binary.substring(24, 28))
                            + toHexa(binary.substring(28));
        
        codeResult.add(code);

        return code;
    }

    public static String loadWord(String s){
        String[] vetor = s.split(",|[(]|[)]");
        String rt = calculateToBinary(vetor[0].substring(1));
        String rs = calculateToBinary(vetor[2].substring(1));

        String binary = "100011" + rs + rt + "0000000000000000";

        String code = "0x"  + toHexa(binary.substring(0, 4))
                            + toHexa(binary.substring(4, 8))
                            + toHexa(binary.substring(8, 12))
                            + toHexa(binary.substring(12, 16))
                            + toHexa(binary.substring(16, 20))
                            + toHexa(binary.substring(20, 24))
                            + toHexa(binary.substring(24, 28))
                            + toHexa(binary.substring(28));

        codeResult.add(code);

        return code;
    }

    public static String branchEqual(String s){
        String[] vetor = s.split(",");
        String rs = calculateToBinary(vetor[0].substring(1));
        String rt = calculateToBinary(vetor[1].substring(1));
        String offset = vetor[2].substring(6);

        String binary = "000100" + rs + rt;

        String code = "0x"  + toHexa(binary.substring(0, 4))
                            + toHexa(binary.substring(4, 8))
                            + toHexa(binary.substring(8, 12))
                            + toHexa(binary.substring(12))
                            + offset;

        codeResult.add(code);

        return code;
    }

    public static String branchNotEqual(String s){
        String[] vetor = s.split(",");
        String rs = calculateToBinary(vetor[0].substring(1));
        String rt = calculateToBinary(vetor[1].substring(1));
        String offset = vetor[2].substring(6);

        String binary = "000101" + rs + rt;

        String code = "0x"  + toHexa(binary.substring(0, 4))
                            + toHexa(binary.substring(4, 8))
                            + toHexa(binary.substring(8, 12))
                            + toHexa(binary.substring(12))
                            + offset;

        codeResult.add(code);

        return code;
    }

    public static String orImmediate(String s){
        String[] vetor = s.split(",");
        String rt = calculateToBinary(vetor[0].substring(1));
        String rs = calculateToBinary(vetor[1].substring(1));
        String imm = vetor[2].substring(6);

        String binary = "001101" + rs + rt;

        String code = "0x"  + toHexa(binary.substring(0, 4))
                            + toHexa(binary.substring(4, 8))
                            + toHexa(binary.substring(8, 12))
                            + toHexa(binary.substring(12))
                            + imm;

        codeResult.add(code);

        return code;
    }

    public static String and(String s){
        String[] vetor = s.split(",");
        String rd = calculateToBinary(vetor[0].substring(1));
        String rs = calculateToBinary(vetor[1].substring(1));
        String rt = calculateToBinary(vetor[2].substring(1));

        String binary = "000000" + rs + rt + rd + "00000" + "100100";


        String code = "0x"  + toHexa(binary.substring(0, 4))
                            + toHexa(binary.substring(4, 8))
                            + toHexa(binary.substring(8, 12))
                            + toHexa(binary.substring(12, 16))
                            + toHexa(binary.substring(16, 20))
                            + toHexa(binary.substring(20, 24))
                            + toHexa(binary.substring(24, 28))
                            + toHexa(binary.substring(28));
        
        codeResult.add(code);

        return code;
    }

    public static String shiftRightLogical(String s){
        String[] vetor = s.split(",");
        String rd = calculateToBinary(vetor[0].substring(1));
        String rt = calculateToBinary(vetor[1].substring(1));
        String rs = "00000";
        String shamt = vetor[2].substring(5);

        String binary = "000000" + rs + rt + rd + shamt + "000010";

        String code = "0x"  + toHexa(binary.substring(0, 4))
                            + toHexa(binary.substring(4, 8))
                            + toHexa(binary.substring(8, 12))
                            + toHexa(binary.substring(12, 16))
                            + toHexa(binary.substring(16, 20))
                            + toHexa(binary.substring(20, 24))
                            + toHexa(binary.substring(24, 28))
                            + toHexa(binary.substring(28));

        codeResult.add(code);

        return code;
    }

    public static String setLessThanImmediat(String s){
        String[] vetor = s.split(",");
        String rd = calculateToBinary(vetor[0].substring(1));
        String rs = calculateToBinary(vetor[1].substring(1));
        String imm = vetor[2].substring(6);

        String binary = "001010" + rs + rd;

        String code = "0x"  + toHexa(binary.substring(0, 4))
                            + toHexa(binary.substring(4, 8))
                            + toHexa(binary.substring(8, 12))
                            + toHexa(binary.substring(12))
                            + imm;
                           
        codeResult.add(code);

        return code;
    }

    

    public static String calculateToBinary(String s){

        int number = Integer.parseInt(s);

        String binary = Integer.toBinaryString(number);

        if(binary.length() < 5){
            for(int i = binary.length(); i < 5; i++){
                binary = "0" + binary;
            }
        }

        return binary;
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


    public static String opcode(String opcode, String code){

        switch(opcode){
            case "j":
                return jump(code);
            case "jr":
                return jumpRegister(code);
            case "add":
                return add(code);
            case "lw":
                return loadWord(code);
            case "beq":
                return branchEqual(code);
            case "bne":
                return branchNotEqual(code);
            case "ori":
                return orImmediate(code);
            case "and":
                return and(code);
            case "srl":
                return shiftRightLogical(code);
            case "slti":
                return setLessThanImmediat(code);

        }

        return "oops";
    }

    public static String buildCode() throws IOException{
        List<String> file = LeituraArquivo.scan();

        String line = "";
        String opcode = "";
        String code = "";

        for (int i = 0; i < file.size(); i++){
            line = file.get(i); 

            for(int j = 0; j < line.length(); j++){
                if(line.charAt(j) != ' '){
                    opcode += line.charAt(j);

                } else {
                    for(int k = j; k < line.length(); k++){
                        if (line.charAt(k) != ' '){
                            code += line.charAt(k);
                        }
                    }
                    
                    opcode(opcode, code);

                    opcode = "";
                    code = "";
                    break;

                }
            }

            
            
        }

        String write = ""; 

        for(int k=0; k < codeResult.size(); k++){
            write += codeResult.get(k) + "\n";
        }        

        return write;

    } 

    public static void main (String[] args) throws IOException{
        buildCode();
    }
    
}
