package br.ce.ornelas.tests;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import br.ce.ornelas.core.BaseTest;
import br.ce.ornelas.core.DriverFactory;
import br.ce.ornelas.pages.MenuPage;
import br.ce.ornelas.pages.ResumoPage;

@FixMethodOrder(MethodSorters.NAME_ASCENDING) //<-- sequenciamento de test jUnit por nome crescente
public class ResumoTest extends BaseTest{
	
	private MenuPage menuPage = new MenuPage();
	private ResumoPage resumoPage = new ResumoPage();
	
	@Test
	public void test1_ExcluirMovimentacao(){
		menuPage.acessarTelaResumo();
		
		resumoPage.ajustarFiltrosPraDataSelecionada("Outubro","2024");
		resumoPage.excluirMovimentacao();
		
		assertEquals("Movimentação removida com sucesso!", 
				resumoPage.obterMensagemSucesso());
	}
	
	@Test
	public void test2_ResumoMensal(){
		menuPage.acessarTelaResumo();
		
		assertEquals("Seu Barriga - Extrato", DriverFactory.getDriver().getTitle());
	}
	
	@Test
	public void test3_ValidarValorResumoMensal(){
		menuPage.acessarTelaResumo();
		
		String valorResumo = resumoPage.lerValoresMovimentacao("500.00");
		assertEquals("500.00", valorResumo);	
	}
	
	/*@Test
	public void test4_ResumoMensalVazio(){
		menuPage.acessarTelaResumo();
		
		assertEquals("Seu Barriga - Extrato", DriverFactory.getDriver().getTitle());
		
		List<WebElement> elementosEncontrados = 
				DriverFactory.getDriver().findElements(By.xpath("//*[@id='tabelaExtrato']/tbody/tr"));
		assertEquals(0, elementosEncontrados.size());
		
	}*/

}
