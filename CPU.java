// -------------------------------------------------------------------------------------------------------
// --------------------- C P U  -  definicoes da CPU -----------------------------------------------------
public class CPU {
    // característica do processador: contexto da CPU ...
    private int pc; 			// ... composto de program counter,
    private Word ir; 			// instruction register,
    private int[] reg;       	// registradores da CPU

    private Word[] m;   // CPU acessa MEMORIA, guarda referencia 'm' a ela. memoria nao muda. ee sempre a mesma.

    public CPU(Word[] _m) {     // ref a MEMORIA e interrupt handler passada na criacao da CPU
        m = _m; 				// usa o atributo 'm' para acessar a memoria.
        reg = new int[8]; 		// aloca o espaço dos registradores
    }

    public void setContext(int _pc) {  // no futuro esta funcao vai ter que ser
        pc = _pc; // limite e pc (deve ser zero nesta versao)
    }

    private void dump(Word w) {
        System.out.print("[ ");
        System.out.print(w.opc); System.out.print(", ");
        System.out.print(w.r1);  System.out.print(", ");
        System.out.print(w.r2);  System.out.print(", ");
        System.out.print(w.p);  System.out.println("  ] ");
    }

    private void showState(){
        System.out.println("       " + pc);
        System.out.print("           ");
        for (int i=0; i<8; i++) { System.out.print("r"+i);   System.out.print(": "+reg[i]+"     "); };
        System.out.println("");
        System.out.print("           ");  dump(ir);
    }

    public void run() { 		// execucao da CPU supoe que o contexto da CPU, vide acima, esta devidamente setado
        while (true) { 			// ciclo de instrucoes. acaba cfe instrucao, veja cada caso.
            // FETCH
            ir = m[pc]; 	// busca posicao da memoria apontada por pc, guarda em ir
            //if debug
            showState();
            // EXECUTA INSTRUCAO NO ir
            switch (ir.opc) { // para cada opcode, sua execução

                case LDI: // Rd ← k
                    reg[ir.r1] = ir.p;
                    pc++;
                    break;

                case STD: // [A] ← Rs
                    m[ir.p].opc = Opcode.DATA;
                    m[ir.p].p = reg[ir.r1];
                    pc++;
                    break;

                case ADD: // Rd ← Rd + Rs
                    reg[ir.r1] = reg[ir.r1] + reg[ir.r2];
                    pc++;
                    break;

                case MULT: // Rd ← Rd * Rs
                    reg[ir.r1] = reg[ir.r1] * reg[ir.r2];
                    // gera um overflow
                    // -->  LIGA INT  (1)
                    pc++;
                    break;

                case ADDI: // Rd ← Rd + k
                    reg[ir.r1] = reg[ir.r1] + ir.p;
                    pc++;
                    break;

                case STX: // [Rd] ←Rs
                    m[reg[ir.r1]].opc = Opcode.DATA;
                    m[reg[ir.r1]].p = reg[ir.r2];
                    pc++;
                    break;

                case SUB: // Rd ← Rd - Rs
                    reg[ir.r1] = reg[ir.r1] - reg[ir.r2];
                    pc++;
                    break;

                case JMP: //  PC ← k
                    pc = ir.p;
                    break;

                case JMPIG: // If Rc > 0 Then PC ← Rs Else PC ← PC +1
                    if (reg[ir.r2] > 0) {
                        pc = reg[ir.r1];
                    } else {
                        pc++;
                    }
                    break;

                case JMPIE: // If Rc = 0 Then PC ← Rs Else PC ← PC +1
                    if (reg[ir.r2] == 0) {
                        pc = reg[ir.r1];
                    } else {
                        pc++;
                    }
                    break;

                case JMPIL: // If Rc < 0 Then PC ← Rs Else PC ← PC +1
                    if (reg[ir.r2] < 0) {
                        pc = reg[ir.r1];
                    } else {
                        pc++;
                    }
                    break;


                case STOP: // por enquanto, para execucao
                    break;
                default:
                    // opcode desconhecido
                    // liga interrup (2)
            }

            // VERIFICA INTERRUPÇÃO !!! - TERCEIRA FASE DO CICLO DE INSTRUÇÕES
            if (ir.opc==Opcode.STOP) {
                break; // break sai do loop da cpu

                // if int ligada - vai para tratamento da int
                //     desviar para rotina java que trata int
            }
        }
    }
}
// ------------------ C P U - fim ------------------------------------------------------------------------
// -------------------------------------------------------------------------------------------------------