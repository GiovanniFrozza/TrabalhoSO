import java.util.*;

public class Sistema {
	public VM vm;
	public Monitor monitor;
	public static Programas progs;

	public Sistema(){   // a VM com tratamento de interrupções
		vm = new VM();
		monitor = new Monitor(vm);
		progs = new Programas();
	}

	public void roda(Word[] programa){
		monitor.carga(programa, vm.m);
		System.out.println("---------------------------------- programa carregado ");
		monitor.dump(vm.m, 0, programa.length);
		monitor.executa();
		System.out.println("---------------------------------- após execucao ");
		monitor.dump(vm.m, 0, vm.m.length);
	}

	public static void main(String args[]) {
		Sistema s = new Sistema();
//		s.roda(progs.fibonacci());
//		s.roda(progs.fatorial());
//		s.roda(progs.progMinimo());
	}
}

