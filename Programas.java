//  -------------------------------------------- programas aa disposicao para copiar na memoria (vide carga)
public class Programas {
    public Word[] progMinimo() {
        //       OPCODE      R1  R2  P         :: VEJA AS COLUNAS VERMELHAS DA TABELA DE DEFINICAO DE OPERACOES
        //                                     :: -1 SIGNIFICA QUE O PARAMETRO NAO EXISTE PARA A OPERACAO DEFINIDA
        return new Word[] {
                new Word(Opcode.LDI, 0, -1, 999),
                new Word(Opcode.STD, 0, -1, 10),
                new Word(Opcode.STD, 0, -1, 11),
                new Word(Opcode.STD, 0, -1, 12),
                new Word(Opcode.STD, 0, -1, 13),
                new Word(Opcode.STD, 0, -1, 14),
                new Word(Opcode.STOP, -1, -1, -1)
        };
    }

    public Word[] fibonacci() {
        return new Word[]{
                new Word(Opcode.LDI, 0, -1, 5), //alterar o valor de P para negativo ou positivo
                new Word(Opcode.STD, 0, -1, 25),
                new Word(Opcode.LDD, 1, -1, 25),
                new Word(Opcode.LDI, 7, -1, 8),
                new Word(Opcode.JMPIG, 7, 1, -1),
                new Word(Opcode.LDI, 3, -1, -1),
                new Word(Opcode.STD, 3, -1, 26),
                new Word(Opcode.STOP, -1, -1, -1), // 7
                new Word(Opcode.LDI, 0, -1, 0),
                new Word(Opcode.LDI, 1, -1, 1),
                new Word(Opcode.LDI, 2, -1, 27), // posicao para escrita
                new Word(Opcode.LDD, 3, -1, 25), // reg para n de fibo
                new Word(Opcode.LDI, 7, -1, 7), // 12  //reg para stop
                new Word(Opcode.STX, 2, 0, -1), //inicio loop
                new Word(Opcode.ADDI, 2, -1, 1),
                new Word(Opcode.SUBI, 3, -1, 1),
                new Word(Opcode.JMPIE, 7, 3, -1),
                new Word(Opcode.STX, 2, 1, -1),
                new Word(Opcode.ADDI, 2, -1, 1),
                new Word(Opcode.SUBI, 3, -1, 1),
                new Word(Opcode.JMPIE, 7, 3, -1),
                new Word(Opcode.ADD, 0, 1, -1),
                new Word(Opcode.ADD, 1, 0, -1),
                new Word(Opcode.JMP, -1, -1, 13)    //volta pro loop
        };
    };

    public Word[] fibonacciComEntrada() {
        return new Word[]{
                new Word(Opcode.LDI, 8, -1, 1),      // 0
                new Word(Opcode.TRAP, -1, -1, -1),   // 1
                new Word(Opcode.STX, 0, 9, -1),
                new Word(Opcode.LDI, 0, -1, 5), //alterar o valor de P para negativo ou positivo
                new Word(Opcode.STD, 0, -1, 25),
                new Word(Opcode.LDD, 1, -1, 25),
                new Word(Opcode.LDI, 7, -1, 8),
                new Word(Opcode.JMPIG, 7, 1, -1),
                new Word(Opcode.LDI, 3, -1, -1),
                new Word(Opcode.STD, 3, -1, 26),
                new Word(Opcode.STOP, -1, -1, -1), // 7
                new Word(Opcode.LDI, 0, -1, 0),
                new Word(Opcode.LDI, 1, -1, 1),
                new Word(Opcode.LDI, 2, -1, 27), // posicao para escrita
                new Word(Opcode.LDD, 3, -1, 25), // reg para n de fibo
                new Word(Opcode.LDI, 7, -1, 7), // 12  //reg para stop
                new Word(Opcode.STX, 2, 0, -1), //inicio loop
                new Word(Opcode.ADDI, 2, -1, 1),
                new Word(Opcode.SUBI, 3, -1, 1),
                new Word(Opcode.JMPIE, 7, 3, -1),
                new Word(Opcode.STX, 2, 1, -1),
                new Word(Opcode.ADDI, 2, -1, 1),
                new Word(Opcode.SUBI, 3, -1, 1),
                new Word(Opcode.JMPIE, 7, 3, -1),
                new Word(Opcode.ADD, 0, 1, -1),
                new Word(Opcode.ADD, 1, 0, -1),
                new Word(Opcode.JMP, -1, -1, 13)    //volta pro loop
        };
    };

    public Word[] fatorial() {
        return new Word[] {
                new Word(Opcode.LDI, 0, -1, 2000),      // 0   r0 é valor a calcular fatorial
                new Word(Opcode.LDI, 1, -1, 1),      // 1   r1 é 1 para multiplicar (por r0)
                new Word(Opcode.LDI, 5, -1, 10),     // 2   r7 tem posicao do programa = 9
                new Word(Opcode.LDI, 6, -1, 1),      // 3   r6 é 1 para ser o decremento
                new Word(Opcode.LDI, 7, -1, 11),     // 4   r7 tem posicao de stop do programa = 9
                new Word(Opcode.JMPIL, 5, 0, 0),     // 5   se r0<0 pula para r5(=10)
                new Word(Opcode.JMPIE, 7, 0, 0),     // 6   se r0=0 pula para r7(=11)
                new Word(Opcode.MULT, 1, 0, -1),     // 7   r1 = r1 * r0
                new Word(Opcode.SUB, 0, 6, -1),      // 8   decrementa r0 1
                new Word(Opcode.JMP, -1, -1, 5),     // 9   vai p posicao 5
                new Word(Opcode.LDI, 1, -1, -1),     // 10  r1 é -1
                new Word(Opcode.STD, 1, -1, 13),     // 11  coloca valor de r1 na posição 13
                new Word(Opcode.STOP, -1, -1, -1),   // 12  stop
                new Word(Opcode.DATA, -1, -1, -1)    // 13  resultado
        };
    }

    public Word[] fatorialComEntrada() {
        return new Word[] {
                new Word(Opcode.LDI, 8, -1, 1),      // 0
                new Word(Opcode.TRAP, -1, -1, -1),   // 1
                new Word(Opcode.STX, 0, 9, -1),      // 2
                new Word(Opcode.LDI, 1, -1, 1),      // 3   r1 é 1 para multiplicar (por r0)
                new Word(Opcode.LDI, 5, -1, 12),     // 4   r7 tem posicao do programa = 9
                new Word(Opcode.LDI, 6, -1, 1),      // 5   r6 é 1 para ser o decremento
                new Word(Opcode.LDI, 7, -1, 13),     // 6   r7 tem posicao de stop do programa = 9
                new Word(Opcode.JMPIL, 5, 0, 0),     // 7   se r0<0 pula para r5(=10)
                new Word(Opcode.JMPIE, 7, 0, 0),     // 8   se r0=0 pula para r7(=11)
                new Word(Opcode.MULT, 1, 0, -1),     // 9   r1 = r1 * r0
                new Word(Opcode.SUB, 0, 6, -1),      // 10   decrementa r0 1
                new Word(Opcode.JMP, -1, -1, 8),     // 11   vai p posicao 5
                new Word(Opcode.LDI, 1, -1, -1),     // 12  r1 é -1
                new Word(Opcode.STD, 1, -1, 15),     // 13  coloca valor de r1 na posição 13
                new Word(Opcode.STOP, -1, -1, -1),   // 14  stop
                new Word(Opcode.DATA, -1, -1, -1)    // 15  resultado
        };
    }

    public Word[] bubbleSort() {
        return new Word[] {
                new Word(Opcode.LDI, 0, -1, 12),
                new Word(Opcode.STD, 0, -1, 40),
                new Word(Opcode.LDI, 0, -1, 20),
                new Word(Opcode.STD, 0, -1, 41),
                new Word(Opcode.LDI, 0, -1, 12),
                new Word(Opcode.STD, 0, -1, 42),
                new Word(Opcode.LDI, 0, -1, 1),
                new Word(Opcode.STD, 0, -1, 43),
                new Word(Opcode.LDI, 0, -1, 29),
                new Word(Opcode.STD, 0, -1, 44),
                new Word(Opcode.LDI, 0, -1, -12),
                new Word(Opcode.STD, 0, -1, 45),
                new Word(Opcode.LDI, 0, -1, 0),
                new Word(Opcode.STD, 0, -1, 46),
                new Word(Opcode.LDI, 3, -1, 6),
                new Word(Opcode.LDI, 4, -1, 6),
                new Word(Opcode.LDI, 5, -1, 20),
                new Word(Opcode.LDI, 6, -1, 33),
                new Word(Opcode.LDI, 7, -1, 38),
                new Word(Opcode.LDI, 0, -1, 40),
                new Word(Opcode.JMPIE, 6, 3, -1),
                new Word(Opcode.SUBI, 3, -1, 1),
                new Word(Opcode.LDX, 1, 0, -1),
                new Word(Opcode.ADDI, 0, -1, 1),
                new Word(Opcode.LDX, 2, 0, -1),
                new Word(Opcode.SUB, 2, 1, -1),
                new Word(Opcode.JMPIG, 5, 2, -1),
                new Word(Opcode.LDX, 2, 0, -1),
                new Word(Opcode.STX, 0, 1, -1),
                new Word(Opcode.SUBI, 0, -1, 1),
                new Word(Opcode.STX, 0, 2, -1),
                new Word(Opcode.ADDI, 0, -1, 1),
                new Word(Opcode.JMPI, 5, 0 , -1),
                new Word(Opcode.JMPIE, 7, 4, -1),
                new Word(Opcode.SUBI, 4, -1, 1),
                new Word(Opcode.LDI, 0, -1, 40),
                new Word(Opcode.LDI, 3, -1, 6),
                new Word(Opcode.JMPIG, 5, 0, -1),
                new Word(Opcode.STOP, -1, -1, -1)
        };
    }
}