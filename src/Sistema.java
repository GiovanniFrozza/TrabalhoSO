import java.util.Scanner;

public class Sistema {
	public VM vm;
	public Monitor monitor;
	public static Programas progs;

	public Sistema(){   // a VM com tratamento de interrupções
		vm = new VM();
		monitor = new Monitor(vm);
		progs = new Programas();
	}

	public void load(Word[] program, String programName) {
		VM.gerenteDeProcesso.allocatePCB(program, programName);

		System.out.println("---------------------------------- programa carregado ");
	}

	public void execute(int processId) {
		monitor.executa(processId);

		System.out.println("---------------------------------- programa executado");
	}

	public static void main(String args[]) {
		Sistema s = new Sistema();
		menu(s);
	}

	private static void menu(Sistema s) {
		Scanner teclado = new Scanner(System.in);
		int resultado;

		do {
			System.out.println("1: Carregar um programa \n2: Listar programas carregados \n3: Executar um programa \n4: Dump \n-1: Sair" );
			switch (resultado = teclado.nextInt()) {
				case 1:
					loadProgram(s);
					break;
				case 2:
					listPrograms();
					break;
				case 3:
					executeProgram(s);
					break;
				case 4:
					memoryDump(s.monitor);
					break;
				case -1:
					break;
			}
		} while (resultado > 0 && resultado < 5);

	}

	private static void loadProgram(Sistema s) {
		Scanner teclado = new Scanner(System.in);
		int resultado;

		do {
			System.out.println("1: Fatorial \n2: Fibonacci \n3: Bubble Sort \n-1: Voltar" );
			switch (resultado = teclado.nextInt()) {
				case 1:
					s.load(progs.fatorial(), "Fatorial");
					break;
				case 2:
					s.load(progs.fibonacci(), "Fibonacci");
					break;
				case 3:
					s.load(progs.bubbleSort(), "Bubble Sort");
					break;
				case -1:
					break;
			}
		} while (resultado > 0  && resultado < 4);

	}

	private static void listPrograms() {
		VM.gerenteDeProcesso.getProcess().forEach(process -> System.out.println(process.getId() + ": " + process.getProgramName()));
		System.out.println();
	}

	private static void executeProgram(Sistema s) {
		Scanner teclado = new Scanner(System.in);
		int processId;

		do {
			System.out.println("Informe o Id do processo que deseja executar ou digite -1 para voltar:" );
			processId = teclado.nextInt();
			s.execute(processId);
		} while (processId > -1);

	}

	private static void memoryDump(Monitor monitor) {
		monitor.dump(VM.m, 0, VM.m.length);
	}
}

