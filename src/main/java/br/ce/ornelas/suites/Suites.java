package br.ce.ornelas.suites;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import br.ce.ornelas.core.DriverFactory;
import br.ce.ornelas.pages.LoginPage;
import br.ce.ornelas.tests.ContaTest;
import br.ce.ornelas.tests.MovimentacaoTest;
import br.ce.ornelas.tests.RemoverMovimentacaoContaTest;
import br.ce.ornelas.tests.ResumoTest;
import br.ce.ornelas.tests.SaldoTest;

@RunWith(Suite.class)
@SuiteClasses({
	ContaTest.class,
	MovimentacaoTest.class,
	RemoverMovimentacaoContaTest.class,
	SaldoTest.class,
	ResumoTest.class
})
	
/*para tornar a exec mais rápida eu incluiria o @BeforeClass com o login e senha, 
mudaria nas propriedades para não fechar o navegador e ao final @AfterClass fechando o navegador*/

public class Suites {
	
	private static LoginPage page = new LoginPage();
	
	@BeforeClass
	public static void inicializa() {
		page.acessarTelaInicial();
		
		page.setEmail("ornelas@ornelas.com");
		page.setSenha("159159");
		page.entrar();
	}
	
	@AfterClass
	public static void finaliza() {
		DriverFactory.killDriver();
	}
	
}
