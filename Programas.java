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

    public Word[] fibonacci10() { // mesmo que prog exemplo, so que usa r0 no lugar de r8
        return new Word[] {
            new Word(Opcode.LDI, 1, -1, 0),
            new Word(Opcode.STD, 1, -1, 20),    // 20 posicao de memoria onde inicia a serie de fibonacci gerada
            new Word(Opcode.LDI, 2, -1, 1),
            new Word(Opcode.STD, 2, -1, 21),
            new Word(Opcode.LDI, 0, -1, 22),
            new Word(Opcode.LDI, 6, -1, 6),
            new Word(Opcode.LDI, 7, -1, 30),
            new Word(Opcode.LDI, 3, -1, 0),
            new Word(Opcode.ADD, 3, 1, -1),
            new Word(Opcode.LDI, 1, -1, 0),
            new Word(Opcode.ADD, 1, 2, -1),
            new Word(Opcode.ADD, 2, 3, -1),
            new Word(Opcode.STX, 0, 2, -1),
            new Word(Opcode.ADDI, 0, -1, 1),
            new Word(Opcode.SUB, 7, 0, -1),
            new Word(Opcode.JMPIG, 6, 7, -1),
            new Word(Opcode.STOP, -1, -1, -1),   // POS 16
            new Word(Opcode.DATA, -1, -1, -1),
            new Word(Opcode.DATA, -1, -1, -1),
            new Word(Opcode.DATA, -1, -1, -1),
            new Word(Opcode.DATA, -1, -1, -1),   // POS 20
            new Word(Opcode.DATA, -1, -1, -1),
            new Word(Opcode.DATA, -1, -1, -1),
            new Word(Opcode.DATA, -1, -1, -1),
            new Word(Opcode.DATA, -1, -1, -1),
            new Word(Opcode.DATA, -1, -1, -1),
            new Word(Opcode.DATA, -1, -1, -1),
            new Word(Opcode.DATA, -1, -1, -1),
            new Word(Opcode.DATA, -1, -1, -1),
            new Word(Opcode.DATA, -1, -1, -1) // ate aqui - serie de fibonacci ficara armazenada
        };
    }

    public Word[] fatorial() { 	 // este fatorial so aceita valores positivos.   nao pode ser zero
        return new Word[] {
            new Word(Opcode.LDI, 0, -1, 6),      // 0   	r0 é valor a calcular fatorial
            new Word(Opcode.LDI, 1, -1, 1),      // 1   	r1 é 1 para multiplicar (por r0)
            new Word(Opcode.LDI, 6, -1, 1),      // 2   	r6 é 1 para ser o decremento
            new Word(Opcode.LDI, 7, -1, 8),      // 3   	r7 tem posicao de stop do programa = 8
            new Word(Opcode.JMPIL, 7, 0, 0),     // 4   	se r0=0 pula para r7(=8)
            new Word(Opcode.JMPIE, 7, 0, 0),     // 5   	se r0=0 pula para r7(=8)
            new Word(Opcode.MULT, 1, 0, -1),     // 6   	r1 = r1 * r0
            new Word(Opcode.SUB, 0, 6, -1),      // 7   	decrementa r0 1
            new Word(Opcode.JMP, -1, -1, 4),     // 8   	vai p posicao 4
            new Word(Opcode.STD, 1, -1, 10),     // 9   	coloca valor de r1 na posição 10
            new Word(Opcode.STOP, -1, -1, -1),    // 10   	stop
            new Word(Opcode.DATA, -1, -1, -1)
        };
    }  // 10   ao final o valor do fatorial estará na posição 10 da memória
}