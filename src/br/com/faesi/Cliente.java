package br.com.faesi;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.lang.AutoCloseable;

public class Cliente {

	private String host;
	private int porta;

	public Cliente(String host, int porta) {
		this.host = host;
		this.porta = porta;
	}

	public void executa() throws UnknownHostException, IOException {
		String none = "Puta";
		String resp = "*";
		try(Socket cliente = new Socket(this.host, this.porta); 				
				Scanner teclado = new Scanner(System.in);				
				PrintStream saida = new PrintStream(cliente.getOutputStream())) {
			System.out.println("Eduardo: Boa Noite com Classe para a Classe!");
	
			RecebedorDeMensagemDoServidor r = new RecebedorDeMensagemDoServidor(cliente.getInputStream());
			new Thread(r).start();
	
			while (teclado.hasNextLine()) {
				saida.println(teclado.nextLine());
			}
		}
	}
}
