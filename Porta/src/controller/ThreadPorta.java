package controller;

import java.util.concurrent.Semaphore;

public class ThreadPorta extends Thread {

	private int idPessoa;
	private static int posiChegada;
	private static int	posiSaida;
	private Semaphore semaforo;
	 
	public ThreadPorta(int idPessoa, Semaphore semaforo) {
		this.idPessoa = idPessoa;
		this.semaforo= semaforo;
	}
	
	public void run() {
		pessoaAndando();
		// P (Acquire)
		try {
			semaforo.acquire();
			cruzandoPorta();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			// V (Release)
			semaforo.release();
			saiudaPorta();
		}
	}
		
	private void pessoaAndando() {
		int distTotal = 200;
		int distPercorrida = 0;
		int deslocamento = (int) ((Math.random() * 3)+4);
		int tempo = 1;
			while ( distPercorrida  < distTotal){
				distPercorrida += deslocamento;
				try {
					sleep(tempo*1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("a " +idPessoa + "ª pessoa andou " + distPercorrida + " metros;");
			}
		posiChegada++;
		System.out.println("a " + idPessoa + "ª pessoa foi o " + posiChegada + "º a chegar ao final do corredor.");	
	}

	private void cruzandoPorta() {
		int tempo = (int) ((Math.random() * 2) + 1);
		try {
			sleep(tempo*1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("a " + idPessoa + "ª pessoa cruzou a porta.");
	}

	private void saiudaPorta() {
		posiSaida++;
		System.out.println("a " + idPessoa + "ª pessoa foi o " +posiSaida +"º a sair da porta.");
	}
}
