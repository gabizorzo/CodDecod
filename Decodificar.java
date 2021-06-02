/*  ORGANIZAÇÃO E ARQUITETURA DE COMPUTADORES I
    Prof. Fabiano Passuelo Hessel
    Turma 128
    Gabriela Zorzo e Morgana Weber
    Trabalho Prático 2
*/

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Decodificar {

    // hasLabel é responsável por 'ligar' os labels nos endereços necessários
    private static Boolean[] hasLabel;

    // Converte um endereço em um label
    public static String convertToLabel(String s){
        int indexLabel = (((int)(s.charAt(s.length()-1))-48) + 16*((int)(s.charAt(s.length()-2))-48))/4;

        hasLabel[indexLabel] = true;

        return "l"+indexLabel;
    }

    // Método para decodificar o jump 
    public static String jump(String s){
        String substring = s.substring(6);

        // Escreve o endereço em binário
        String sub = "0000" + substring + "00"; 

        // Escreve o endereço em hexadecimal
        String adress = "0x" + toHexa(sub.substring(0,4)) 
                        + toHexa(sub.substring(4,8)) 
                        + toHexa(sub.substring(8,12)) 
                        + toHexa(sub.substring(12,16)) 
                        + toHexa(sub.substring(16,20))
                        + toHexa(sub.substring(20,24))
                        + toHexa(sub.substring(24,28))
                        + toHexa(sub.substring(28));

        // Converte o endereço para um label
        String label = convertToLabel(adress); 

        // Retorna o comando
        return ("j " + label);
    }

    // Método para decodificar o jump register
    public static String jumpRegister(String s){
        String substring = s.substring(6,11);

        // Converte o register para decimal
        int register = toDecimal(substring);

        // Escreve o endereço
        String adress = "$" + register;

        // Retorna o comando
        return ("jr " + adress);
    }

    // Método para decodificar o add
    public static String add(String s){
        // Converte a parte do código em binário que representa o rs para decimal
        int rs = toDecimal(s.substring(6,11));
        // Converte a parte do código em binário que representa o rt para decimal
        int rt = toDecimal(s.substring(11,16));  
        // Converte a parte do código em binário que representa o rd para decimal
        int rd = toDecimal(s.substring(16,21));
        
        // Escreve os endereços
        String adress = "$"+rd+",$"+rs+",$"+rt;

        // Retorna o comando
        return ("add " + adress);
    }

    // Método para decodificar o load word
    public static String loadWord(String s){
        // Converte a parte do código em binário que representa o rs para decimal
        int rs = toDecimal(s.substring(6,11));
        // Converte a parte do código em binário que representa o rt para decimal
        int rt = toDecimal(s.substring(11,16));

        // Escreve os endereços
        String adress = "$"+rt+",0x00000000($"+rs+")";

        // Retorna o comando
        return ("lw " + adress);
    }

    // Método para decodificar o branch on equal
    public static String branchEqual(String s, int k){
        // Converte a parte do código em binário que representa o rs para decimal
        int rs = toDecimal(s.substring(6,11));
        // Converte a parte do código em binário que representa o rt para decimal
        int rt = toDecimal(s.substring(11,16));

        // Calcula a posição onde o label precisa ser gerado
        int indexLabel = toDecimal2s(s.substring(16))+k+1;

        // Liga o label na posição indexLabel
        hasLabel[indexLabel] = true;

        // Escreve os endereços
        String adress = "$"+rs+",$"+rt+",l"+indexLabel;

        // Retorna o comando
        return ("beq " + adress);
    }

    // Método para decodificar o branch not equal
    public static String branchNotEqual(String s, int k){
        // Converte a parte do código em binário que representa o rs para decimal
        int rs = toDecimal(s.substring(6,11));
        // Converte a parte do código em binário que representa o rt para decimal
        int rt = toDecimal(s.substring(11,16));

        // Calcula a posição onde o label precisa ser gerado
        int indexLabel = toDecimal2s(s.substring(16))+k+1;

        // Liga o label na posição indexLabel
        hasLabel[indexLabel] = true;
        
        // Escreve os endereços
        String adress = "$"+rs+",$"+rt+",l"+indexLabel;

        // Retorna o comando
        return ("bne " + adress);
    }

    // Método para decodificar o or immediate
    public static String orImmediate(String s){
        // Converte a parte do código em binário que representa o rs para decimal
        int rs = toDecimal(s.substring(6,11));
        // Converte a parte do código em binário que representa o rt para decimal
        int rt = toDecimal(s.substring(11,16));
        // Converte as partes do código em binário que representam o imm para hexa
        String imm1 = toHexa(s.substring(16, 20));
        String imm2 = toHexa(s.substring(20, 24));
        String imm3 = toHexa(s.substring(24, 28));
        String imm4 = toHexa(s.substring(28));

        // Escreve os endereços
        String adress = "$"+rt+",$"+rs+",0x0000"+imm1+imm2+imm3+imm4; 

        // Retorna o comando
        return ("ori "+ adress);
    }

    // Método para decodificar o and
    public static String and(String s){
        // Converte a parte do código em binário que representa o rs para decimal
        int rs = toDecimal(s.substring(6,11));
        // Converte a parte do código em binário que representa o rt para decimal
        int rt = toDecimal(s.substring(11, 16));
        // Converte a parte do código em binário que representa o rd para decimal
        int rd = toDecimal(s.substring(16, 21));

        // Escreve o endereço
        String adress = "$"+rd+",$"+rs+",$"+rt;

        // Retorna o comando
        return("and " + adress);
    }

    // Método para decodificar o shift right logical
    public static String shiftRightLogical(String s){
        // Converte a parte do código em binário que representa o rt para decimal
        int rt = toDecimal(s.substring(11,16));
        // Converte a parte do código em binário que representa o rd para decimal
        int rd = toDecimal(s.substring(16, 21));
        // Converte a parte do código em binário que representa o shamt para hexa
        String shamt = toHexa(s.substring(22, 26)); 
        
        // Escreve o endereço
        String adress = "$"+rd+",$"+rt+",0x0000000"+shamt;

        // Retorna o comando
        return("srl " + adress);
    }

    // Método para decodificar o set less than immediate
    public static String setLessThanImmediate(String s){
        // Converte a parte do código em binário que representa o rd para decimal
        int rd = toDecimal(s.substring(11, 16));
        // Converte a parte do código em binário que representa o rs para decimal
        int rs = toDecimal(s.substring(6, 11)); 
        // Converte as partes do código em binário que representam o imm para hexa
        String imm1 = toHexa(s.substring(16, 20));
        String imm2 = toHexa(s.substring(20, 24));
        String imm3 = toHexa(s.substring(24, 28));
        String imm4 = toHexa(s.substring(28));

        // Escreve o endereço
        String adress = "$"+rd+",$"+rs+",0x0000"+imm1+imm2+imm3+imm4;

        // Retorna o comando
        return("slti " + adress);
    }

    // Método para olhar o funct e identificar o comando no caso do opcode ser 0
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

        // Retorna o comando
        return s;
    }

    // Método para olhar o opcode e identificar o comando
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
                // Caso o opcode seja 0, é necessário olhar os últimos 6 dígitos
                return funct(s);

        }

        // Retorna o comando
        return s;
    }

    // Converte de binário para decimal
    public static int toDecimal(String s){
        int value[] = new int[s.length()];
        
        for (int i = 0; i < s.length(); i++){
            value[i] = (int)(s.charAt(i))-48;
        }

        return ((int)(Math.pow(2, 0) * value[4] + Math.pow(2, 1) * value[3] + Math.pow(2, 2) * value[2] + Math.pow(2, 3) * value[1] + Math.pow(2, 4) * value[0]));
    }

    // Converte de binário para decimal com complemento de 2
    public static int toDecimal2s(String s){
        int value = 0;
        String sAux = "";
        String sAux2 = "";
        Boolean negative = false;

        // Confere se o número em binário é negativo
        if (s.charAt(0) == '1'){
            negative = true;

            // Inverte todos os bits
            for (int i = 0; i < s.length(); i++){
                if (s.charAt(i) == '1'){
                    sAux += "0";
                } else {
                    sAux += "1";
                }
            }

            // Soma +1
            for (int i = s.length()-1; i >= 0; i--){
                if (sAux.charAt(i) == '1'){
                    sAux2 += "0";
                } else {
                    sAux2 += "1";
                    // Copia o restante do número
                    for (int j = i-1; j >=0; j--){
                        sAux2 += sAux.charAt(j);
                    }

                    // Cria um StringBuilder para inverter a ordem de sAux2, que foi montada de trás para frente
                    StringBuilder sb = new StringBuilder(sAux2);
                    sb.reverse();
                    s = sb.toString();

                    break;
                }
            }
        }

        // Transforma número em binário para decimal
        for (int i = 0; i < s.length(); i++){
            value += ((int)(s.charAt(i))-48) * (int)(Math.pow(2, s.length()-1-i));
        }

        // Sinal de negativo
        if (negative) {
            value = -value;
        }

        // Retorna o valor em decimal
        return value;
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

    // Método principal para quebrar o código
    public static String breakCode() throws IOException{
        // Leitura do arquivo Codificado.txt
        List<String> file = LeituraArquivo.scan();

        // Inicializa o hasLabel com o mesmo tamanho que o arquivo
        hasLabel = new Boolean[file.size()];

        // Cada linha lida
        String line;
        // Array com as linhas lidas em binário
        List<String> binary = new ArrayList<String>();
        // Array com o código decodificado
        List<String> code = new ArrayList<String>();
        // Nova linha sem espaços desnecessários
        String newLine = "";

        for (int i = 0; i < file.size(); i++){
            // Inicializa todos os labels desligados
            hasLabel[i] = false;

            // hasLabel na posição l0 é o "main"
            hasLabel[0] = true;

            // Cada linha do arquivo vai ser tratada separadamente
            line = file.get(i);
            
            // Elimina os espaços desnecessários
            for(int j = 2; j < line.length(); j++){
                if(line.charAt(j) != ' '){
                    // Converte a nova linha sem espaços para binário
                    newLine += toBinary(line.charAt(j));
                }
            }
            // Adiciona a nova linha no Array de binários
            binary.add(newLine);
            // Limpa a nova linha para receber a linha seguinte lida do arquivo
            newLine = "";
        }

        // Percorre o Array de binários buscando o opcode de cada comando para decodificar o código
        for(int k=0; k < binary.size(); k++){
            code.add(opcode(binary.get(k), k));
        }

        // Começa a string que vai ser escrita no arquivo
        String write = ".text\n.globl l0\n"; 

        // Percorre o array com o código decodificado, concatenando ele na string write
        for(int k=0; k < code.size(); k++){
            if (hasLabel[k]){
                write += "l" + k + ":\n";
            }

            write += code.get(k) + "\n";
        }        

        // Retorna a string com o código decodificado para ser escrito no arquivo
        return write;
    } 

    public static void main (String[] args) throws IOException{
        breakCode();
    }


}
