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