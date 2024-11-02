package br.ce.ornelas.core;

import java.io.File;
import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TestName;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.google.common.io.Files;

import br.ce.ornelas.pages.LoginPage;

public class BaseTest {
	
	//private LoginPage page = new LoginPage();

	@Rule
	public TestName testName = new TestName();
	
	/*@Before
	public void inicializa(){
		page.acessarTelaInicial();
		page.setEmail("ornelas@ornelas.com");
		page.setSenha("159159");
		page.entrar();
		
	}*/

	@After
	public void finaliza() throws IOException {
		// criar screenshot, antes do navegador fechar
		TakesScreenshot ss = (TakesScreenshot) DriverFactory.getDriver();
		File arquivo = ss.getScreenshotAs(OutputType.FILE);
		Files.copy(arquivo, new File(
				"target" + File.separator + "screenshot" + File.separator + testName.getMethodName() + ".jpg"));

		if (Properties.FECHAR_BROWSER) {
			DriverFactory.killDriver();
		}
	}

}
