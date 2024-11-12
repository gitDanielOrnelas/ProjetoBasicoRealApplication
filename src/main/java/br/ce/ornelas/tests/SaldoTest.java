package br.ce.ornelas.tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import br.ce.ornelas.core.BaseTest;
import br.ce.ornelas.core.Properties;
import br.ce.ornelas.pages.HomePage;
import br.ce.ornelas.pages.MenuPage;

public class SaldoTest extends BaseTest {
	
	HomePage homePage = new HomePage();
	MenuPage menuPage = new MenuPage();
	
	@Test
	public void testSaldoConta() throws InterruptedException {
		menuPage.acessarTelaPrincipal();
		
		//assertEquals("1500.00", homePage.obterSaldoConta(Properties.NOME_CONTA_ALTERADA));
		Thread.sleep(2000);
		assertEquals("534.00", homePage.obterSaldoConta("Conta para saldo"));  // massa paralelismo
	}

}
