package br.ce.ornelas.tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import br.ce.ornelas.core.BaseTest;
import br.ce.ornelas.core.Properties;
import br.ce.ornelas.pages.ContasPage;
import br.ce.ornelas.pages.MenuPage;

public class RemoverMovimentacaoContaTest extends BaseTest{
	
	MenuPage menuPage = new MenuPage();
	ContasPage contasPage = new ContasPage();
	
	@Test
	public void testExcluirContaComMovimentacao(){
		menuPage.acessarTelaListarConta();
		
		contasPage.clicarExcluirConta(Properties.NOME_CONTA_ALTERADA);
		contasPage.clicarExcluirConta("Conta com movimentacao"); // massa paralelismo

		assertEquals("Conta em uso na movimentações", contasPage.obterMensagemErro());		
	}

}
