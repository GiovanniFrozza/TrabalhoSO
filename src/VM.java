// ------------------- V M  - constituida de CPU e MEMORIA -----------------------------------------------
// -------------------------- atributos e construcao da VM -----------------------------------------------
public class VM {
    public CPU cpu;
    public static int memSize;
    public static int pageSize;
    public static Word[] m;
    public static GerenteDeMemoria gerenteDeMemoria;
    public static GerenteDeProcesso gerenteDeProcesso;

    public VM(){
        // memória
        memSize = 1024;
        pageSize = 16;
        m = new Word[memSize]; // m ee a memoria
        for (int i = 0; i< memSize; i++) {
            m[i] = new Word(Opcode.___,-1,-1,-1);
        }
        // cpu
        cpu = new CPU(m);   // cpu acessa memória
        gerenteDeMemoria = new GerenteDeMemoria();
        gerenteDeProcesso = new GerenteDeProcesso(cpu);
    }
}
// ------------------- V M  - fim ------------------------------------------------------------------------
// -------------------------------------------------------------------------------------------------------