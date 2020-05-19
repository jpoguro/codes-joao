package view;

import java.util.concurrent.Semaphore;

import controller.ThreadPorta;

public class Principal {

	public static void main(String[] args) {
		
		int perm = 1;
		Semaphore semaforo= new Semaphore(perm);
	
		for (int idPessoa = 1; idPessoa < 5; idPessoa++) {
			Thread tPessoa= new ThreadPorta(idPessoa, semaforo);
			tPessoa.start();
		}
	}

}
