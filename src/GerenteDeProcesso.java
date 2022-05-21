import java.util.List;
import java.util.Objects;
import java.util.concurrent.ConcurrentLinkedQueue;

public class GerenteDeProcesso {
    private ConcurrentLinkedQueue<ProcessControlBlock> process;
    private Integer id;
    private CPU cpu;
    private ProcessControlBlock running;

    public GerenteDeProcesso(CPU cpu) {
        this.process = new ConcurrentLinkedQueue<>();
        this.id = 0;
        this.cpu = cpu;
    }

    public void allocatePCB(Word[] program) {
        if (VM.gerenteDeMemoria.verifySpace(program.length)) {
            List<Integer> pages = VM.gerenteDeMemoria.allocate(program);
            int progIndex = 0;

            for (Integer page : pages) {
                for (int j = (page * VM.pageSize); j < (page + 1) * VM.pageSize; j++) {
                    if (progIndex >= program.length) break;
                    VM.m[j].opc = program[progIndex].opc;
                    VM.m[j].r1 = program[progIndex].r1;
                    VM.m[j].r2 = program[progIndex].r2;
                    VM.m[j].p = program[progIndex].p;
                    progIndex++;
                }
            }

            ProcessControlBlock pcb = new ProcessControlBlock(id++, pages, cpu.pc, cpu.reg, cpu.ir, cpu.interruptorEnum);
            process.add(pcb);
        }
    }

    public void deallocatePCB(int id) {
        ProcessControlBlock pcb = this.getProcess(id);
        if (Objects.nonNull(pcb)) {
            VM.gerenteDeMemoria.deallocate(pcb.getPages());
        }
    }

    public ProcessControlBlock getProcess(int id) {
        return process.stream().filter(p -> p.getId() == id).findFirst().orElse(null);
    }

    public ProcessControlBlock getRunning() {
        return running;
    }

    public void setRunning(ProcessControlBlock running) {
        this.running = running;
    }
}
