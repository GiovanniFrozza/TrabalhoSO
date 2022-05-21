import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GerenteDeMemoria {
    
    public boolean[] frames;
    public List<Integer> pages;

    public GerenteDeMemoria() {
        this.resetMemory(0, VM.m.length);
        frames = new boolean[(VM.memSize / VM.pageSize)];
        this.pages = new ArrayList<>();
    }

    public boolean verifySpace(int programSize) {
        int nPages = getNumberPages(programSize);
        int count = 0;
        for(boolean frame:frames) {
            if(!frame) {
                count++;
            }
        }
        return count >= nPages;
    }

    public int getNumberPages(int programSize) {
        return programSize % VM.pageSize == 0 ? programSize / VM.pageSize : (programSize / VM.pageSize)+1;
    }

    public List<Integer> aloca(Word[] program) {
        this.pages.clear();
        int nPages = getNumberPages(program.length);
        int progIndex = 0;

        List<Integer> pages = new ArrayList<>();
        for (int i = 0; i<frames.length; i++) {
            if (nPages == 0) break;
            if (!frames[i]) {
                frames[i] = true;
                for (int j = (i*VM.pageSize); j<(i+1)*VM.pageSize; j++) {
                    if (progIndex >= program.length) break;
                    VM.m[j].opc = program[progIndex].opc;
                    VM.m[j].r1 = program[progIndex].r1;
                    VM.m[j].r2 = program[progIndex].r2;
                    VM.m[j].p = program[progIndex].p;
                    progIndex++;
                }
                pages.add(i);
                nPages--;
            }
        }
        this.pages = pages;
        return pages;
    }

    public void desaloca(List<Integer> pages){
        pages.forEach(page -> {
            for(int i = 0; i< frames.length; i++) {
                if (page == i) {
                    frames[i] = false;
                    resetMemory((i*VM.pageSize), (i +1)*VM.pageSize);
                }
            }
        });
    }

    private void resetMemory(int start, int end) {
        for (int j = start; j <end; j++) {
            VM.m[j] = new Word(Opcode.___, -1, -1, -1);
        }
    }

}
