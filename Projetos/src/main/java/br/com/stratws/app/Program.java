package br.com.stratws.app;

import br.com.stratws.model.AnaliseProjetos;

public class Program {

	public static void main(String[] args) throws Exception {
		
		try {

		AnaliseProjetos analises = new AnaliseProjetos();

		analises.atribuiStatus();
		
		analises.enviaEmailExchange();
		
		System.exit(1);
		
		}
		catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}

	}
}