package br.ce.ornelas.pages;

import org.openqa.selenium.By;

import br.ce.ornelas.core.BasePage;

public class ResumoPage extends BasePage {
	
	public void ajustarFiltrosPraDataSelecionada(String mes, String ano) {
		selecionarCombo("mes", mes);
		selecionarCombo("ano", ano);
		
		clicarBotao(By.xpath("//input[@class='btn btn-primary']"));
	}
	
	public void excluirMovimentacao() {
		clicarBotao(By.xpath("//span[@class='glyphicon glyphicon-remove-circle']"));
	}
	
	public String obterMensagemSucesso() {
		return obterTexto(By.xpath("//div[@class='alert alert-success']"));
	}
	
	public String lerValoresMovimentacao(String string) {
		return obterCelula("Valor", string, "Valor", "tabelaExtrato").getText();
	}
}



