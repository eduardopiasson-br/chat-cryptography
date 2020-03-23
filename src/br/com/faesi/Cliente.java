package br.com.faesi;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Cliente {
	
	private String nome;
	private String host;
	private int porta;

	public Cliente(String nome, String host, int porta) {
		this.nome = nome;
		this.host = host;
		this.porta = porta;
	}

	public void executa() throws UnknownHostException, IOException {
		try(Socket cliente = new Socket(this.host, this.porta); 				
				Scanner teclado = new Scanner(System.in);
				PrintStream saida = new PrintStream(cliente.getOutputStream())) {			
			System.out.println(this.nome + ", você está conectado ao Servidor-Chat!");
	
			RecebedorDeMensagemDoServidor r = new RecebedorDeMensagemDoServidor(cliente.getInputStream());
			new Thread(r).start();
	
			while (teclado.hasNextLine()) {
				saida.println(this.nome + ": " + teclado.nextLine());
			}
		}
	}
}
