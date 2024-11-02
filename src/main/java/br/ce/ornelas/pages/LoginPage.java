package br.ce.ornelas.pages;

import static br.ce.ornelas.core.DriverFactory.getDriver;

import br.ce.ornelas.core.BasePage;

public class LoginPage extends BasePage {

	public void acessarTelaInicial() {
		getDriver().get("https://seubarriga.wcaquino.me/");
	}

	public void setEmail(String email) {
		escrever("email", email);
	}

	public void setSenha(String senha) {
		escrever("senha", senha);
	}

	public void entrar() {
		clicarBotaoPorTexto("Entrar");
	}

	public void logar(String email, String senha) {
		setEmail(email);
		setSenha(senha);
		entrar();
	}
}
