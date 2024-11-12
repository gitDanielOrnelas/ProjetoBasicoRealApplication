package br.ce.ornelas.tests;

import static org.junit.Assert.assertEquals;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import br.ce.ornelas.core.BaseTest;
import br.ce.ornelas.core.Properties;
import br.ce.ornelas.pages.ContasPage;
import br.ce.ornelas.pages.MenuPage;

@FixMethodOrder(MethodSorters.NAME_ASCENDING) //<-- sequenciamento de test jUnit por nome crescente

public class ContaTest extends BaseTest{
	
	MenuPage menuPage = new MenuPage();
	ContasPage contasPage = new ContasPage();
	
	@Test
	public void test1_InserirConta() {
		menuPage.acessarTelaInserirConta();
		
		contasPage.setNome("Conta Teste Ornelas");
		contasPage.salvar();
		
		assertEquals("Conta adicionada com sucesso!", contasPage.obterMensagemSucesso());
	}
	
	@Test
	public void test2_AlterarConta() throws InterruptedException {
		menuPage.acessarTelaListarConta();
		
		contasPage.clicarAlterarConta("Conta para alterar");
		Thread.sleep(1000);
		//contasPage.setNome(Properties.NOME_CONTA_ALTERADA);
		contasPage.setNome("Conta alterada"); // massa paralelismo
		Thread.sleep(1000);
		contasPage.salvar();
		
		assertEquals("Conta alterada com sucesso!", contasPage.obterMensagemSucesso());		
	}
	
	@Test
	public void test3_InserirContaMesmoNome() throws InterruptedException {
		menuPage.acessarTelaInserirConta();

		//contasPage.setNome(Properties.NOME_CONTA_ALTERADA);
		contasPage.setNome("Conta mesmo nome"); // massa paralelismo
		contasPage.salvar();
		
		assertEquals("Já existe uma conta com esse nome!", contasPage.obterMensagemErro());		
	}
	
	

	//enviado para classe RemoverMovimentacaoContaTest
	/*@Test
	public void testExcluirContaComMovimentacao(){
		menuPage.acessarTelaListarConta();
		
		contasPage.clicarExcluirConta(Properties.NOME_CONTA_ALTERADA);

		assertEquals("Conta em uso na movimentações", contasPage.obterMensagemErro());		
	}*/

}
