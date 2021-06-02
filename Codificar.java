/*  ORGANIZAÇÃO E ARQUITETURA DE COMPUTADORES I
    Prof. Fabiano Passuelo Hessel
    Turma 128
    Gabriela Zorzo e Morgana Weber
    Trabalho Prático 2
*/

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Codificar {
    // Cria um array que vai armazenar as linhas de código codificado
    private static List<String> codeResult = new ArrayList<>();

    // Método para codificar o jump
    public static String jump(String s){
        // Parte do código que armazena o endereço
        String adress = s.substring(2);
        String binary = "";

        // Converte cada char do endereço para binário
        for(int i = 0; i<adress.length(); i++){
            binary += toBinary(adress.charAt(i));
        }
        
        // Acrescenta o opcode em binário
        binary = "000010" + binary.substring(4, 30);

        // Escreve o código em hexa
        String code = "0x"+ toHexa(binary.substring(0, 4))
                          + toHexa(binary.substring(4, 8))
                          + toHexa(binary.substring(8, 12))
                          + toHexa(binary.substring(12, 16))
                          + toHexa(binary.substring(16, 20))
                          + toHexa(binary.substring(20, 24))
                          + toHexa(binary.substring(24, 28))
                          + toHexa(binary.substring(28));

        // Adiciona o código no array de códigos codificados
        codeResult.add(code);

        return code;
    }

    // Método para codificar o jump register
    public static String jumpRegister(String s){
        // Parte do código que armazena o rs
        String rs = s.substring(1);
        // Converte o rs para binário
        rs = calculateToBinary(rs);

        // Escreve o comando todo em binário
        String binary = "000000" + rs + "0000000000000000" + "01000"; 

        // Escreve o código em hexa
        String code = "0x"  + toHexa(binary.substring(0, 4))
                            + toHexa(binary.substring(4, 8))
                            + toHexa(binary.substring(8, 12))
                            + toHexa(binary.substring(12, 16))
                            + toHexa(binary.substring(16, 20))
                            + toHexa(binary.substring(20, 24))
                            + toHexa(binary.substring(24, 28))
                            + toHexa(binary.substring(28));

        // Adiciona o código no array de códigos codificados
        codeResult.add(code);

        return code;
    }

    // Método para codificar o add
    public static String add(String s){
        String[] vetor = s.split(",");
        String rd = calculateToBinary(vetor[0].substring(1));
        String rs = calculateToBinary(vetor[1].substring(1));
        String rt = calculateToBinary(vetor[2].substring(1));

        // Escreve o comando todo em binário
        String binary = "000000" + rs + rt + rd + "00000" + "100000";

        // Escreve o código em hexa
        String code = "0x"  + toHexa(binary.substring(0, 4))
                            + toHexa(binary.substring(4, 8))
                            + toHexa(binary.substring(8, 12))
                            + toHexa(binary.substring(12, 16))
                            + toHexa(binary.substring(16, 20))
                            + toHexa(binary.substring(20, 24))
                            + toHexa(binary.substring(24, 28))
                            + toHexa(binary.substring(28));
        
        // Adiciona o código no array de códigos codificados
        codeResult.add(code);

        return code;
    }

    // Método para codificar o load word
    public static String loadWord(String s){
        // Quebra o cógido lido onde tem vírgula e parenteses
        String[] vetor = s.split(",|[(]|[)]");
        // Converte a parte referente ao rt para binário
        String rt = calculateToBinary(vetor[0].substring(1));
        // Converte a parte referente ao rs para binário
        String rs = calculateToBinary(vetor[2].substring(1));

        // Escreve o comando todo em binário
        String binary = "100011" + rs + rt + "0000000000000000";

        // Escreve o código em hexa
        String code = "0x"  + toHexa(binary.substring(0, 4))
                            + toHexa(binary.substring(4, 8))
                            + toHexa(binary.substring(8, 12))
                            + toHexa(binary.substring(12, 16))
                            + toHexa(binary.substring(16, 20))
                            + toHexa(binary.substring(20, 24))
                            + toHexa(binary.substring(24, 28))
                            + toHexa(binary.substring(28));

        // Adiciona o código no array de códigos codificados
        codeResult.add(code);

        return code;
    }

    // Método para codificar o branch on equal
    public static String branchEqual(String s){
        // Quebra o código lido onde tem vírgula
        String[] vetor = s.split(",");
        // Converte a parte referente ao rs para binário
        String rs = calculateToBinary(vetor[0].substring(1));
        // Converte a parte referente ao rt para binário
        String rt = calculateToBinary(vetor[1].substring(1));
        // Escreve os 4 últimos caracteres em hexa referentes ao offset
        String offset = vetor[2].substring(6);

        // Escreve o código todo em binário
        String binary = "000100" + rs + rt;

        // Escreve o código em hexa
        String code = "0x"  + toHexa(binary.substring(0, 4))
                            + toHexa(binary.substring(4, 8))
                            + toHexa(binary.substring(8, 12))
                            + toHexa(binary.substring(12))
                            + offset;

        // Adiciona o código no array de códigos codificados
        codeResult.add(code);

        return code;
    }

    // Método para codificar o branch not equal
    public static String branchNotEqual(String s){
        // Quebra o código lido onde tem vírgula
        String[] vetor = s.split(",");
        // Converte a parte referente ao rs para binário
        String rs = calculateToBinary(vetor[0].substring(1));
        // Converte a parte referente ao rt para binário
        String rt = calculateToBinary(vetor[1].substring(1));
        // Escreve os 4 últimos caracteres em hexa referentes ao offset
        String offset = vetor[2].substring(6);

        // Escreve o código todo em binário
        String binary = "000101" + rs + rt;

        // Escreve o código em hexa
        String code = "0x"  + toHexa(binary.substring(0, 4))
                            + toHexa(binary.substring(4, 8))
                            + toHexa(binary.substring(8, 12))
                            + toHexa(binary.substring(12))
                            + offset;

        // Adiciona o código no array de códigos codificados
        codeResult.add(code);

        return code;
    }

    // Método para codificar o or immediate
    public static String orImmediate(String s){
        // Quebra o código lido onde tem vírgula
        String[] vetor = s.split(",");
        // Converte a parte referente ao rt para binário
        String rt = calculateToBinary(vetor[0].substring(1));
        // Converte a parte referente ao rs para binário
        String rs = calculateToBinary(vetor[1].substring(1));
        // Escreve os 4 últimos caracteres em hexa referentes ao imm
        String imm = vetor[2].substring(6);

        // Escreve o código todo em binário
        String binary = "001101" + rs + rt;

        // Escreve o código todo em hexa
        String code = "0x"  + toHexa(binary.substring(0, 4))
                            + toHexa(binary.substring(4, 8))
                            + toHexa(binary.substring(8, 12))
                            + toHexa(binary.substring(12))
                            + imm;

        // Adiciona o código no array de códigos codificados
        codeResult.add(code);

        return code;
    }

    // Método para codificar o and
    public static String and(String s){
        // Quebra o código lido onde tem vírgula
        String[] vetor = s.split(",");
        // Converte a parte referente ao rd para binário
        String rd = calculateToBinary(vetor[0].substring(1));
        // Converte a parte referente ao rs para binário
        String rs = calculateToBinary(vetor[1].substring(1));
        // Converte a parte referente ao rt para binário
        String rt = calculateToBinary(vetor[2].substring(1));

        // Escreve o código todo em binário
        String binary = "000000" + rs + rt + rd + "00000" + "100100";

        // Escreve o código em hexa
        String code = "0x"  + toHexa(binary.substring(0, 4))
                            + toHexa(binary.substring(4, 8))
                            + toHexa(binary.substring(8, 12))
                            + toHexa(binary.substring(12, 16))
                            + toHexa(binary.substring(16, 20))
                            + toHexa(binary.substring(20, 24))
                            + toHexa(binary.substring(24, 28))
                            + toHexa(binary.substring(28));
        
        // Adiciona o código no array de códigos codificados                    
        codeResult.add(code);

        return code;
    }

    // Método para codificar o shift right logical
    public static String shiftRightLogical(String s){
        // Quebra o código lido onde tem vírgula
        String[] vetor = s.split(",");
        // Converte a parte referente ao rd para binário
        String rd = calculateToBinary(vetor[0].substring(1));
        // Converte a parte referente ao rt para binário
        String rt = calculateToBinary(vetor[1].substring(1));
        // Escreve a parte referente ao rs
        String rs = "00000";
        // Escreve os caracteres em hexa referentes ao shamt
        String shamt = vetor[2].substring(5);

        // Escreve o código todo em binário
        String binary = "000000" + rs + rt + rd + shamt + "000010";

        // Escreve o código em hexa
        String code = "0x"  + toHexa(binary.substring(0, 4))
                            + toHexa(binary.substring(4, 8))
                            + toHexa(binary.substring(8, 12))
                            + toHexa(binary.substring(12, 16))
                            + toHexa(binary.substring(16, 20))
                            + toHexa(binary.substring(20, 24))
                            + toHexa(binary.substring(24, 28))
                            + toHexa(binary.substring(28));

        // Adiciona o código no array de códigos codificados  
        codeResult.add(code);

        return code;
    }

    // Método para codificar o set less than immediate
    public static String setLessThanImmediate(String s){
        // Quebra o código lido onde tem vírgula
        String[] vetor = s.split(",");
        // Converte a parte referente ao rd para binário
        String rd = calculateToBinary(vetor[0].substring(1));
        // Converte a parte referente ao rs para binário
        String rs = calculateToBinary(vetor[1].substring(1));
        // Escreve os 4 últimos caracteres em hexa referentes ao imm
        String imm = vetor[2].substring(6);

        // Escreve o código todo em binário
        String binary = "001010" + rs + rd;

        // Escreve o código em hexa
        String code = "0x"  + toHexa(binary.substring(0, 4))
                            + toHexa(binary.substring(4, 8))
                            + toHexa(binary.substring(8, 12))
                            + toHexa(binary.substring(12))
                            + imm;

        // Adiciona o código no array de códigos codificados                              
        codeResult.add(code);

        return code;
    }

    
    // Calcula de decimal para binário
    public static String calculateToBinary(String s){

        int number = Integer.parseInt(s);

        String binary = Integer.toBinaryString(number);

        // Acrescenta os bits faltantes (caso seja necessário) para completar 5 bits
        if(binary.length() < 5){
            for(int i = binary.length(); i < 5; i++){
                binary = "0" + binary;
            }
        }

        return binary;
    }

    // Converte de binário para hexa
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

    // Converte de hexa para binário com 4 bits
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

    // Método para olhar o opcode e identificar o comando
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
                return setLessThanImmediate(code);

        }

        return "oops";
    }

    // Método principal para construir o código
    public static String buildCode() throws IOException{
        // realiza a leitura do arquivo Decodificado.asm
        List<String> file = LeituraArquivo.scan();

        // Cada linha lida
        String line = "";
        // Parte da linha referente ao opcode
        String opcode = "";
        // Parte da linha referente ao restante do código
        String code = "";

        for (int i = 0; i < file.size(); i++){
            // Cada linha lida do arquivo vai ser tratada separadamente
            line = file.get(i); 

            for(int j = 0; j < line.length(); j++){
                // Elimina os espaços desnecessários
                if(line.charAt(j) != ' '){
                    // Salva a primeira parte até o primeiro espaço no opcode
                    opcode += line.charAt(j);

                } else {
                    for(int k = j; k < line.length(); k++){
                        if (line.charAt(k) != ' '){
                            // Salva o restante do código após o primeiro espaço no code
                            code += line.charAt(k);
                        }
                    }
                    
                    // Chama o opcode para começar a codificar
                    opcode(opcode, code);

                    // Limpa os valores de opcode e code para tratar a próxima linha
                    opcode = "";
                    code = "";
                    break;

                }
            }

            
            
        }

        // Começa a string que vai ser escrita no arquivo
        String write = ""; 

        // Percorre o array com o código codificado, concatenando ele na string write
        for(int k=0; k < codeResult.size(); k++){
            write += codeResult.get(k) + "\n";
        }        

        // Retorna a string com o código codificado para ser escrito no arquivo
        return write;

    } 

    public static void main (String[] args) throws IOException{
        buildCode();
    }
    
}
