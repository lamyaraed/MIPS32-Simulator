# MIPS32 Simulator

MIPS simulator that consists of assembler and virtual machine.
- The assembler is responsible for findif an instruction is valid or not, (2) what each instruction is and (3) the labels that accompany some instructions.
that is implemented using ad-hoc parsing using Java regular expressions.
- The virtual machine simulates the structure of MIPS-32 processor and can run the machine language program produced by the assembler. 

### Prerequisites

You need to know a basic knowledge about assembly language.
Example:
```
start:
addi s0 $0 5
addi $s1 $0 0
loop:
beq $s1 $s0 end
addi $s1 $0 5
j loop
end:
addi $s1 $0 7 
```

## Running the tests

to run any MIPS-32 instruction you need to follow these steps follow the steps descriped in the user manual file :

* [User Manual](https://github.com/Nada-Nasser/MIPS32-Simulator-/blob/master/MIPS32%20Simulator/User%20Manual.docx)- screenshots descripe all steps and details you need.

## Authors

* **Nada Nasser** -  [Nada-Nasser](https://github.com/Nada-Nasser)

* **Farah Afifi** -  [farah-afifi](https://github.com/farah-afifi)

* **Lamya Raed**  -  [lamyaraed](https://github.com/lamyaraed)

* **Yousef Ahmed** - [yousefahmed98](https://github.com/yousefahmed98)



