package br.ce.ornelas.pages;

import br.ce.ornelas.core.BasePage;

public class HomePage extends BasePage {
	
	public String obterSaldoConta(String nome) {
		return obterCelula("Conta", nome, "Saldo", "tabelaSaldo").getText();
	}

}
