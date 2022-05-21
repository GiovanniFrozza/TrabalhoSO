import java.util.Scanner;

public class Trap {
    private int[] reg;
    private Word[] m;

    public Trap(Word[] m, int[] reg) {
        this.m = m;
        this.reg = reg;
    }

    public void execute() {
        if (reg[8] == 1) {
            try (Scanner in = new Scanner(System.in)) {
                System.out.println("IN: ");
                reg[9] = in.nextInt();
            }
        }

        if (reg[8] == 2) {
            System.out.println("OUT: " + reg[9]);
        }
    }
}