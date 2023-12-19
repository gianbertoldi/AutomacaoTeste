package com.br.automacaocob;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import junit.framework.Assert;

public class FormularioDenunciaTest {
	static WebDriver driver;

	@BeforeClass
	public static void setup() {
		System.setProperty("webdriver.chrome.driver", "./driver/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
	}
	
	public void clicaBanner() {
		try {
			WebElement banner = driver.findElement(By.xpath("/html/body/div[1]/div/div/div/div/div[1]/div[1]/span[2]"));
			banner.click();
		}catch (ElementNotInteractableException ex){
			
		}
	}
	
	public void aceitaTermosEAvanca() {
		WebElement checkBox = driver.findElement(By.xpath("//*[@id=\"term_agree\"]"));
		WebElement button = driver.findElement(By.xpath("//*[@id=\"submit\"]"));
		checkBox.click();
		button.click();
	}

	@Test
	public void validarUrl() {
		driver.get("https://tst.contatoseguro.io/pt/cob/relato/denuncia");
		String URL = driver.getCurrentUrl();
		Assert.assertEquals(URL, "https://tst.contatoseguro.io/pt/cob/relato/denuncia");
	}

	@Test
	public void validarTelefoneFormularioDenuncia() {
		driver.get("https://tst.contatoseguro.io/pt/cob/relato/denuncia");
		String telefone = driver.findElement(By.xpath("/html/body/main/div[2]/div[1]/div[2]/div")).getText();
		Assert.assertEquals(telefone.contains("0800 343 7231"), true);
	}

	@Test
	public void validarNaoPreencherComposObrigatorios() throws InterruptedException {
		driver.get("https://tst.contatoseguro.io/pt/cob/relato/denuncia");
		
		clicaBanner();
		aceitaTermosEAvanca();
		
		boolean alertExiste;
		try {
			String mainWindow = driver.getWindowHandle();
            Alert alert = driver.switchTo().alert();
            alertExiste = true;
            alert.accept();
            driver.switchTo().window(mainWindow);
		} catch (NoAlertPresentException ex) {
			alertExiste = false;
		}
		Assert.assertTrue(alertExiste);
		driver.get("https://tst.contatoseguro.io/pt/cob/relato/denuncia");
		driver.switchTo().alert().accept();

	}

	
	@Test
	public void validarPreencherComposObrigatorios() throws InterruptedException {
		driver.get("https://tst.contatoseguro.io/pt/cob/relato/denuncia");
		WebElement campoAreaSetor = driver.findElement(By.id("pergunta[5643]"));
		
		
		clicaBanner();
		campoAreaSetor.sendKeys("Area/Setor");
		aceitaTermosEAvanca();
		Thread.sleep(4000);
		String URL = driver.getCurrentUrl();
		Assert.assertTrue(URL.contains("https://tst.contatoseguro.io/pt/cob/relato/denuncia/anexos"));

	} 

	@Test
	public void validarTodosOsCampos() throws InterruptedException {
		driver.get("https://tst.contatoseguro.io/pt/cob/relato/denuncia");
		
		clicaBanner();

		WebElement nomeCompleto = driver.findElement(By.xpath("//*[@id=\"denunciante\"]"));
		WebElement cpf = driver.findElement(By.xpath("//*[@id=\"cpf_denunciante\"]"));
		WebElement email = driver.findElement(By.xpath("//*[@id=\"email_gostaria_identificar\"]"));
		WebElement dataNasc = driver.findElement(By.xpath("//*[@id=\"data_nascimento_denunciante\"]"));
		WebElement telefone = driver.findElement(By.xpath("//*[@id=\"telefone_denunciante\"]"));
		WebElement celular = driver.findElement(By.xpath("//*[@id=\"celular_denunciante\"]"));
		WebElement checkBoxIdentificarSim = driver.findElement(By.xpath("//*[@id=\"gostaria_de_se_identificar_s\"]"));
		WebElement indiqueNomes1 = driver.findElement(By.xpath("//*[@id=\"pergunta[5648]\"]"));
		WebElement oQueDenunciar = driver.findElement(By.xpath("//*[@id=\"pergunta[5651]\"]"));
		WebElement areaSetor = driver.findElement(By.xpath("//*[@id=\"pergunta[5643]\"]"));
		WebElement indiqueNomes2 = driver.findElement(By.xpath("//*[@id=\"pergunta[5650]\"]"));
		WebElement existemEvidencias = driver.findElement(By.xpath("//*[@id=\"pergunta[5652]\"]"));
		WebElement valorFinanceiro = driver.findElement(By.xpath("//*[@id=\"pergunta[5653]\"]"));
		WebElement sugestao = driver.findElement(By.xpath("//*[@id=\"pergunta[5654]\"]"));
		WebElement selectFuncionario = driver.findElement(By.xpath("//*[@id=\"pergunta[5640]\"]"));
		WebElement selectTipoDenuncia = driver.findElement(By.xpath("//*[@id=\"pergunta[5641]\"]"));
		WebElement selectFilialUnidade = driver.findElement(By.xpath("//*[@id=\"pergunta[5642]\"]"));
		WebElement selectEspecifique = driver.findElement(By.xpath("//*[@id=\"pergunta[5670]\"]"));
		WebElement selectMotivoDenuncia = driver.findElement(By.xpath("//*[@id=\"pergunta[7781]\"]"));
		WebElement selectFatoDenuncia = driver.findElement(By.xpath("//*[@id=\"pergunta[5644]\"]"));
		WebElement selectCiente = driver.findElement(By.xpath("//*[@id=\"pergunta[5645]\"]"));
		WebElement selectEnvolvidos = driver.findElement(By.xpath("//*[@id=\"pergunta[5647]\"]"));
		WebElement selectEsconderProblema = driver.findElement(By.xpath("//*[@id=\"pergunta[5649]\"]"));

		checkBoxIdentificarSim.click();
		Select select1 = new Select(selectFuncionario);
		select1.selectByValue("Não");
		Select select2 = new Select(selectTipoDenuncia);
		select2.selectByValue("Não se aplica");
		Select select3 = new Select(selectFilialUnidade);
		select3.selectByValue("Acre (AC)");
		Select select4 = new Select(selectEspecifique);
		select4.selectByValue("Alegre");
		Select select5 = new Select(selectMotivoDenuncia);
		select5.selectByValue("Assédio Moral");
		Select select6 = new Select(selectCiente);
		select6.selectByValue("Sim");
		Select select7 = new Select(selectFatoDenuncia);
		select7.selectByValue("Suspeita");
		Select select8 = new Select(selectEnvolvidos);
		select8.selectByValue("Sim");
		Select select9 = new Select(selectEsconderProblema);
		select9.selectByValue("Sim");

		String test = "teste";
		nomeCompleto.sendKeys(test);
		cpf.sendKeys(test);
		email.sendKeys(test);
		dataNasc.sendKeys(test);
		telefone.sendKeys(test);
		celular.sendKeys(test);
		checkBoxIdentificarSim.sendKeys(test);
		indiqueNomes1.sendKeys(test);
		oQueDenunciar.sendKeys(test);
		areaSetor.sendKeys(test);
		indiqueNomes2.sendKeys(test);
		existemEvidencias.sendKeys(test);
		valorFinanceiro.sendKeys(test);
		sugestao.sendKeys(test);
		
		aceitaTermosEAvanca();
		Thread.sleep(4000);
		String URL = driver.getCurrentUrl();
		Assert.assertTrue(URL.contains("https://tst.contatoseguro.io/pt/cob/relato/denuncia/anexos"));

	}

	@AfterClass
	public static void finaliza() throws InterruptedException {
		Thread.sleep(3000);
		driver.close();
	}

}
