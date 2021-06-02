# Codificação e Decodificação

Este programa realiza a codificação e decodificação de comandos do MARS em Assembly MIPS.

## Execução

Para executar o programa, basta acessar a pasta pelo terminal e entrar com a seguinte linha de comando:

```bash
java -jar CodDecod.jar
```

## Funcionamento

Ao executar o programa, você verá duas opções:

```
Olá! Você gostaria de:
1. Ler um arquivo codificado (.txt) e gerar um arquivo em Assembly (.asm).
2. Ler um arquivo em Assembly (.asm) e gerar um arquivo codificado (.txt).
```

Você deve selecionar a opção desejada através do input do teclado.

Após isso, você deve escrever o nome do arquivo que deseja ler. Lembre-se de colocar a extensão do arquivo que vai ser lido (.txt para um arquivo codificado e .asm para um arquivo decodificado):

```
Nome do arquivo a ser lido (colocar extensão no final): 
```

É importante que os arquivos que você gostaria de ler estejam na mesma pasta deste programa.

Quando o programa terminar de realizar a codificação ou decodificação, ele irá exibir uma mensagem informando:

```
Escrito com sucesso no arquivo!
O seu arquivo deverá estar na mesma pasta deste programa com o nome 'NomeDoArquivo.asm'.
```
O novo arquivo gerado deverá estar na mesma pasta deste programa com o nome informado no terminal.

## Arquivos de entrada

### Código .asm

Este programa funciona usando o código da coluna basic como input, na forma demonstrada abaixo:

```mips
j  0x00400020        
jr $31 
add $8,$9, $10            
lw $8,0x00000000($9)  
beq $8,$9,0xfffffffb
bne $9,$18, 0xfffffffa    
ori $9,$9,0x00000002
and $8,$9,$10 
srl $9,$10,0x00000001
slti $8,$9,0x0000000a 
```
E ao gerar o arquivo de saída em asm ele ficará com os labels necessários para rodar no MIPS:

```mips
.text
.globl l0
l0:
j l8
jr $31
add $8,$9,$10
lw $8,0x00000000($9)
beq $8,$9,l0
bne $9,$18,l0
ori $9,$9,0x00000002
and $8,$9,$10
l8:
srl $9,$10,0x00000001
slti $8,$9,0x0000000a
```

### Arquivo .txt

Sobre o arquivo codificado, tanto a entrada como a saída acontecem no mesmo formato:

```
0x08100008
0x03e00008
0x012a4020
0x8d280000
0x1109fffb
0x1532fffa
0x35290002
0x012a4024
0x000a4842
0x2928000a
```

## Exemplos

Para fins de teste deste programa, foram usados dois arquivos de entrada que estão disponíveis nesta mesma pasta: 

1. Codificado.txt
2. Decodificado.asm

Para testar a decodificação de um arquivo a partir de um código codificado, pode ser usado como entrada o arquivo 1. E para testar a codificação de um arquivo a partir de um código decodificado, pode ser usado o arquivo 2. 

## Desenvolvimento

Este programa foi desenvolvido pelas alunas Gabriela Zorzo e Morgana Weber como parte da disciplina de Organização e Arquitetura de Computadores I da PUCRS.

