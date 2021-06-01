.text
.globl main
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
srl $1,$9,0x000null
slti 
