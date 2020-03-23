package br.com.faesi;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Servidor {

	private int porta;
	private List<Socket> clientes;

	public Servidor(int porta) {
		this.porta = porta;
		this.clientes = new ArrayList<>();
	}

	public void executa() throws IOException  {
		try(ServerSocket servidor = new ServerSocket(this.porta)){
			System.out.println("O servidor está Online!");
	
			while (true) {
				Socket cliente = servidor.accept();
				System.out.println("Nova conexão com o cliente " + 
						cliente.getInetAddress().getHostAddress());
	
				this.clientes.add(cliente);
	
				TratadorDeMensagemDoCliente tc = new TratadorDeMensagemDoCliente(cliente, this);
				new Thread(tc).start();
			}
		}
	}
	
	/*public static String encriptar(int chave, String texto) {
		StringBuilder textoCifrado = new StringBuilder();
		int tamanhoTexto = texto.length();
		
		for (int c = 0; c < tamanhoTexto; c++) {
			int letraCifradaASCII = ((int) texto.charAt(c)) + chave;
			
			while (letraCifradaASCII > 126) {
				letraCifradaASCII -= 94;
			}
			
			textoCifrado.append((char) letraCifradaASCII);
		}
		return textoCifrado.toString();
	}*/

	public void distribuiMensagem(Socket clienteQueEnviou, String msg) {
		for (Socket cliente : this.clientes) {
			if(!cliente.equals(clienteQueEnviou)){
				try {
					PrintStream ps = new PrintStream(cliente.getOutputStream());
					if(msg.contains("bosta")) {
						ps.println("Palavrão é proibido");
					} else if(msg.contains("merda")) {
						ps.println("Palavrão é proibido");
					} else if(msg.contains("cu")) {
						ps.println("Palavrão é proibido");
					} else if(msg.contains("filho-da-puta")) {
						ps.println("Palavrão é proibido");
					} else if(msg.contains("corno")) {
						ps.println("Palavrão é proibido");
					} else if(msg.contains("petista")) {
						ps.println("Palavrão é proibido");
					} else if(msg.contains("colorado")) {
						ps.println("Palavrão é proibido");
					} else if(msg.contains("milico")) {
						ps.println("Palavrão é proibido");
					} else if(msg.contains("louco")) {
						ps.println("Palavrão é proibido");
					} else if(msg.contains("puto")) {
						ps.println("Palavrão é proibido");
					} else {
						ps.println(msg);
						//ps.println(encriptar(1,msg));
					}
					
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
