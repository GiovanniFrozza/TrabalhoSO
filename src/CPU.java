import java.util.List;

// -------------------------------------------------------------------------------------------------------
// --------------------- C P U  -  definicoes da CPU -----------------------------------------------------
public class CPU {
    // característica do processador: contexto da CPU ...
    public int pc; 			// ... composto de program counter,
    public Word ir; 			// instruction register,
    public int[] reg;       	// registradores da CPU
    private Trap trap;
    private Interruptor interruptor;
    public InterruptorEnum interruptorEnum;
    private Word[] m;   // CPU acessa MEMORIA, guarda referencia 'm' a ela. memoria nao muda. ee sempre a mesma.
    private List<Integer> pages;

    public CPU(Word[] _m) {     // ref a MEMORIA e interrupt handler passada na criacao da CPU
        this.m = _m; 				// usa o atributo 'm' para acessar a memoria.
        this.reg = new int[10]; 		// aloca o espaço dos registradores
        this.trap = new Trap(m, reg);
        this.interruptor = new Interruptor(trap);
    }

    public void setContext(int _pc, List<Integer> pages, int[] reg, Word ir, InterruptorEnum interruptorEnum) {  // no futuro esta funcao vai ter que ser
        this.pc = _pc; // limite e pc (deve ser zero nesta versao)
        this.pages = pages;
        this.reg = reg;
        this.ir = ir;
        this.interruptorEnum = interruptorEnum;
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
        for (int i=0; i<10; i++) { System.out.print("r"+i);   System.out.print(": "+reg[i]+"     "); };
        System.out.println("");
        System.out.print("           ");  dump(ir);
    }

    private boolean validateIndexRegister(int r) {
        if (r >= 0 && r <= reg.length)
            return true;
        interruptorEnum = InterruptorEnum.INVALID_REGISTER;
        return false;
    }

    private boolean validateMemoryAddress(int address) {
        if (address >= 0 && address <= 1024)
            return true;
        interruptorEnum = InterruptorEnum.INVALID_ADDRESS;
        return false;
    }

    private boolean validateIntegerOverflow(int number) {
        if (number >= (100000 * -1) && number <= 100000)
            return true;
        interruptorEnum = InterruptorEnum.OVERFLOW;
        return false;
    }

    public int translate(int address) {
        try {
            return (this.pages.get((address / VM.pageSize)) * VM.pageSize) + (address % VM.pageSize);
        } catch (Exception e) {
            return 0;
        }
    }

    public void run() { 		// execucao da CPU supoe que o contexto da CPU, vide acima, esta devidamente setado
        while (true) { 			// ciclo de instrucoes. acaba cfe instrucao, veja cada caso.
            // FETCH
            interruptorEnum = null;
            ir = m[translate(pc)]; 	// busca posicao da memoria apontada por pc, guarda em ir
            //if debug
//            showState();
            // EXECUTA INSTRUCAO NO ir
            switch (ir.opc) { // para cada opcode, sua execução

                case LDI: // Rd <- k
                    if (validateIndexRegister(ir.r1) && validateIntegerOverflow(ir.p)) {
                        reg[ir.r1] = ir.p;
                        pc++;
                    }
                    break;

                case LDD: // Rd <- [A]
                    if (validateIndexRegister(ir.r1) && validateMemoryAddress(translate(ir.p)) && validateIntegerOverflow(m[ir.p].p)) {
                        reg[ir.r1] = m[translate(ir.p)].p;
                        pc++;
                    }
                    break;

                case LDX: // R1 <- [R2]
                    if (validateIndexRegister(ir.r1) && validateIndexRegister(ir.r2) && validateMemoryAddress(reg[ir.r2]) && validateIntegerOverflow(m[reg[ir.r2]].p)) {
                        reg[ir.r1] = m[reg[ir.r2]].p;
                        pc++;
                    }
                    break;

                case STD: // [A] <- Rs
                    if (validateIndexRegister(ir.r1) && validateMemoryAddress(translate(ir.p)) && validateIntegerOverflow(reg[ir.r1])) {
                        m[translate(ir.p)].opc = Opcode.DATA;
                        m[translate(ir.p)].p = reg[ir.r1];
                        pc++;
                    }
                    break;

                case ADD: // Rd <- Rd + Rs
                    if (validateIndexRegister(ir.r1) && validateIndexRegister(ir.r2) && validateIntegerOverflow(reg[ir.r1]) && validateIntegerOverflow(reg[ir.r2]) && validateIntegerOverflow(reg[ir.r1] + reg[ir.r2])) {
                        reg[ir.r1] = reg[ir.r1] + reg[ir.r2];
                        pc++;
                        break;
                    } else {
                        interruptorEnum = InterruptorEnum.OVERFLOW;
                        pc++;
                        break;
                    }

                case MULT: // Rd <- Rd * Rs
                    if (validateIndexRegister(ir.r2) && validateIndexRegister(ir.r1)) {
                        if (validateIntegerOverflow(reg[ir.r1] * reg[ir.r2]) && validateIntegerOverflow(reg[ir.r1]) && validateIntegerOverflow(reg[ir.r2])) {
                            reg[ir.r1] = reg[ir.r1] * reg[ir.r2];
                        }
                        pc++;
                    }
                    break;

                case ADDI: // Rd <- Rd + k
                    if (validateIndexRegister(ir.r1) && validateIntegerOverflow(reg[ir.r1]) && validateIntegerOverflow(ir.p) && validateIntegerOverflow(reg[ir.r1] + ir.p)) {
                        reg[ir.r1] = reg[ir.r1] + ir.p;
                        pc++;
                        break;
                    } else {
                        interruptorEnum = InterruptorEnum.OVERFLOW;
                        pc++;
                        break;
                    }

                case STX: // [Rd] <-Rs
                    if (validateIndexRegister(ir.r1) && validateIndexRegister(ir.r2) && validateMemoryAddress(translate(reg[ir.r1]))) {
                        m[translate(reg[ir.r1])].opc = Opcode.DATA;
                        m[translate(reg[ir.r1])].p = reg[ir.r2];
                        pc++;
                    }
                    break;

                case SUB: // Rd <- Rd - Rs
                    if (validateIndexRegister(ir.r1) && validateIndexRegister(ir.r2) && validateIntegerOverflow(reg[ir.r2]) && validateIntegerOverflow(reg[ir.r1]) && validateIntegerOverflow(reg[ir.r1] - reg[ir.r2])) {
                        reg[ir.r1] = reg[ir.r1] - reg[ir.r2];
                        pc++;
                        break;
                    } else {
                        interruptorEnum = InterruptorEnum.OVERFLOW;
                        pc++;
                        break;
                    }

                case SUBI: // Rd <- Rd – k
                    if (validateIndexRegister(ir.r1) && validateIntegerOverflow(reg[ir.r1]) && validateIntegerOverflow(ir.p) && validateIntegerOverflow(reg[ir.r1] - ir.p)) {
                        reg[ir.r1] = reg[ir.r1] - ir.p;
                        pc++;
                        break;
                    } else {
                        interruptorEnum = InterruptorEnum.OVERFLOW;
                        pc++;
                        break;
                    }

                case JMP: //  PC <- k
                    if (validateMemoryAddress(translate(ir.p))) {
                        pc = ir.p;
                        break;
                    }
                    break;

                case JMPI: // PC <- R1
                    if (validateIndexRegister(translate(ir.r1)) && validateMemoryAddress(translate(reg[ir.r1]))) {
                        pc = reg[ir.r1];
                        break;
                    }
                    break;

                case JMPIG: // If Rc > 0 Then PC <- Rs Else PC <- PC +1
                    if (validateIndexRegister(ir.r2) && validateIndexRegister(ir.r1) && validateMemoryAddress(translate(reg[ir.r1]))) {
                        if (reg[ir.r2] > 0) {
                            pc = reg[ir.r1];
                        } else {
                            pc++;
                        }
                        break;
                    }
                    break;

                case JMPIE: // If Rc = 0 Then PC <- Rs Else PC <- PC +1
                    if (validateIndexRegister(ir.r1) && validateIndexRegister(ir.r2) && validateMemoryAddress(translate(reg[ir.r1]))) {
                        if (reg[ir.r2] == 0) {
                            pc = reg[ir.r1];
                        } else {
                            pc++;
                        }
                        break;
                    }
                    break;

                case JMPIL: // If Rc < 0 Then PC <- Rs Else PC <- PC +1
                    if (validateIndexRegister(ir.r1) && validateIndexRegister(ir.r2) && validateMemoryAddress(translate(reg[ir.r1]))) {
                        if (reg[ir.r2] < 0) {
                            pc = reg[ir.r1];
                        } else {
                            pc++;
                        }
                        break;
                    }
                    break;

                case SWAP:
                    if (validateIndexRegister(ir.r1) && validateIndexRegister(ir.r2)) {
                        int aux = reg[ir.r1];
                        reg[ir.r1] = reg[ir.r2];
                        reg[ir.r2] = aux;
                    }
                    pc++;
                    break;

                case TRAP:
                    interruptorEnum = InterruptorEnum.SYSTEM_CALL;
                    pc ++;
                    break;

                case STOP:
                    interruptorEnum = InterruptorEnum.END_PROGRAM;
                    break;

                default:
                    interruptorEnum = InterruptorEnum.INVALID_INSTRUCTION;
            }

            if (interruptor.validateInterrupt(interruptorEnum, reg))
                break;
        }
    }


}
// ------------------ C P U - fim ------------------------------------------------------------------------
// -------------------------------------------------------------------------------------------------------