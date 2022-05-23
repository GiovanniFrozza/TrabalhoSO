import java.util.Collections;
import java.util.List;

public class ProcessControlBlock {
    private Integer id;
    private List<Integer> pages;
    private int pc;
    private int[] reg;
    private Word ir;
    private InterruptorEnum interruptorEnum;

    private String programName;

    public ProcessControlBlock() {
        this.id = 0;
        this.pages = Collections.emptyList();
    }

    public ProcessControlBlock(Integer id, List<Integer> pages, int pc, int[] reg, Word ir, InterruptorEnum interruptorEnum, String programName) {
        this.id = id;
        this.pages = pages;
        this.pc = pc;
        this.reg = reg;
        this.ir = ir;
        this.interruptorEnum = interruptorEnum;
        this.programName = programName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Integer> getPages() {
        return pages;
    }

    public void setPages(List<Integer> pages) {
        this.pages = pages;
    }

    public int getPc() {
        return pc;
    }

    public void setPc(int pc) {
        this.pc = pc;
    }

    public int[] getReg() {
        return reg;
    }

    public void setReg(int[] reg) {
        this.reg = reg;
    }

    public Word getIr() {
        return ir;
    }

    public void setIr(Word ir) {
        this.ir = ir;
    }

    public InterruptorEnum getInterruptorEnum() {
        return interruptorEnum;
    }

    public void setInterruptorEnum(InterruptorEnum interruptorEnum) {
        this.interruptorEnum = interruptorEnum;
    }

    public String getProgramName() {
        return programName;
    }
}
