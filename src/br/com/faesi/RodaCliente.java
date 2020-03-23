package br.com.faesi;

import java.io.IOException;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;

public class RodaCliente {
	public static void main(String[] args) 
			throws UnknownHostException,	IOException {
		String nome = JOptionPane.showInputDialog("Digite seu nome:");
		new Cliente(nome, "localhost", 12345).executa();
	}
}
