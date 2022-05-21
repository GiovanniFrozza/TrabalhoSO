public class Sistema {
	public VM vm;
	public Monitor monitor;
	public static Programas progs;

	public Sistema(){   // a VM com tratamento de interrupções
		vm = new VM();
		monitor = new Monitor(vm);
		progs = new Programas();
	}

//	public void roda(Word[] programa){
////		monitor.carga(programa, vm.m);
//		VM.gerenteDeProcesso.allocatePCB(programa);
//
//		System.out.println("---------------------------------- programa carregado ");
//		monitor.dump(vm.m, 0, programa.length);
//		monitor.executa();
//		System.out.println("---------------------------------- apos execucao ");
//		// monitor.dump(vm.m, 0, programa.length);
//		monitor.dump(vm.m, 0, vm.m.length);
//	}

	public void load(Word[] program) {
		VM.gerenteDeProcesso.allocatePCB(program);

		System.out.println("---------------------------------- programa carregado ");
		monitor.dump(vm.m, 0, vm.m.length);
	}

	public void execute(int processId) {
		monitor.executa(processId);

		System.out.println("---------------------------------- apos execucao ");
		monitor.dump(vm.m, 0, vm.m.length);
	}

	public static void main(String args[]) {
		Sistema s = new Sistema();
		// FIBONACCI
		// s.roda(progs.fibonacci());
		// s.roda(progs.fibonacciComEntrada());

		// FATORIAL
//		 s.roda(progs.fatorial());
		//	s.roda(progs.fatorialComEntrada());

		// BUBBLE SORT
		// s.roda(progs.bubbleSort());

		s.load(progs.fatorial());
		s.load(progs.fatorial());
		s.load(progs.fatorial());
		s.load(progs.fatorial());

		s.execute(1);
		s.execute(2);
	}
}

