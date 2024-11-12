package br.ce.ornelas.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import br.ce.ornelas.core.BaseTest;
import br.ce.ornelas.core.Properties;
import br.ce.ornelas.pages.MenuPage;
import br.ce.ornelas.pages.MovimentacaoPage;
import br.ce.ornelas.utils.DataUtils;

@FixMethodOrder(MethodSorters.NAME_ASCENDING) //<-- sequenciamento de test jUnit por nome crescente

public class MovimentacaoTest extends BaseTest {

	private MenuPage menuPage = new MenuPage();
	private MovimentacaoPage movimentacaoPage = new MovimentacaoPage();

	@Test
	public void testInserirMovimentacao() {
		//Random random = new Random();
		
		for (int i = 0; i < 3; i++) { //executará o test 3x
			//int valorAleatorio = random.nextInt(2001); //gera um valor de 0 à 2000
			
			menuPage.acessarTelaMovimentacao();
	
			movimentacaoPage.setDataMovimentacao(DataUtils.obterDataFormatada(new Date()));
			movimentacaoPage.setDataPagamento("01/11/2024");
			movimentacaoPage.setDescricao("Movimentação Ornelas");
			movimentacaoPage.setInteressado("Roberto");
			movimentacaoPage.setValor("500");
	        //movimentacaoPage.setValor(String.valueOf(valorAleatorio)); // Define o valor como uma string aleatória
			movimentacaoPage.setConta(Properties.NOME_CONTA_ALTERADA);
			movimentacaoPage.setStatusPago();
			movimentacaoPage.salvar();

			assertEquals("Movimentação adicionada com sucesso!", movimentacaoPage.obterMensagemSucesso());
		}
	}
	
	@Test
	public void testCamposObrigatorios() {
		menuPage.acessarTelaMovimentacao();

		movimentacaoPage.salvar();
		List<String> erros = movimentacaoPage.obterErros();

			// ordenaria por posição no array
		// assertEquals("Data da Movimentação é obrigatório", erros.get(0));
		// assertEquals("Data do pagamento é obrigatório", erros.get(1)); 
			// ou faria um assert pra cada erro
		//assertTrue(erros.contains("Data da Movimentação é obrigatório"));
		//assertTrue(erros.contains("Data do pagamento é obrigatório"));
			// ou usar contaisAll e chamar o array todo e verificará se estão lá dentro
		assertTrue(erros.containsAll(Arrays.asList("Data da Movimentação é obrigatório",
				"Data do pagamento é obrigatório", "Descrição é obrigatório", "Interessado é obrigatório",
				"Valor é obrigatório", "Valor deve ser um número")));
			//ou validar se a quantidade de erros conhecidas estão presentes
		//assertEquals(6, erros.size());
	}
	
	@Test
	public void testInserirMovimentacaoFutura() {
		menuPage.acessarTelaMovimentacao();
		
		Date dataFutura = DataUtils.obterDataComDiferencaDias(5);
		
		movimentacaoPage.setDataMovimentacao(DataUtils.obterDataFormatada(dataFutura));
		movimentacaoPage.setDataPagamento("30/10/2030");
		movimentacaoPage.setDescricao("Movimentação Ornelas");
		movimentacaoPage.setInteressado("Roberto");
		movimentacaoPage.setValor("1500");
		//movimentacaoPage.setConta(Properties.NOME_CONTA_ALTERADA);
		movimentacaoPage.setConta("Conta para movimentacoes");// massa paralelismo
		movimentacaoPage.setStatusPago();
		movimentacaoPage.salvar();
		
		List<String> erros = movimentacaoPage.obterErros();
		assertTrue(erros.containsAll(Arrays.asList("Data da Movimentação deve ser menor ou igual à data atual")));
	}
}
