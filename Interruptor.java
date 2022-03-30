public class Interruptor {
    private Trap trap;

    public Interruptor(Trap trap) {
        this.trap = trap;
    }

    public boolean validateInterrupt(InterruptorEnum interruptor, int[] reg) {
        boolean interrupted = false;
        if (interruptor != null) {
            switch (interruptor) {
                case OVERFLOW:
                    System.out.println("Overflow!");
                    interrupted = true;
                    break;

                case INVALID_INSTRUCTION:
                    System.out.println("Instrucao invalida!");
                    interrupted = true;
                    break;
                
                case INVALID_REGISTER:
                    System.out.println("Registrador invalido!");
                    interrupted = true;
                    break;

                case INVALID_ADDRESS:
                    System.out.println("Endereco de memoria invalido!");
                    interrupted = true;
                    break;

                case SYSTEM_CALL:
                    trap.execute();
                    break;
                    
                case END_PROGRAM:
                    System.out.println("Programa finalizado!");
                    interrupted = true;
                    break;
            }
        }
        return interrupted;
    }

}