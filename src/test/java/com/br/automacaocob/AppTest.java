package com.br.automacaocob;

import static org.junit.Assert.assertEquals;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import junit.framework.Assert;

public class AppTest {
	static WebDriver driver;

	@BeforeClass
	public static void setup() {
		System.setProperty("webdriver.chrome.driver", "D:/User/Beatriz/Desktop/AutomacaoTeste/driver/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
	}
	@Test
	public void validarUrl() {
		driver.get("https://tst.contatoseguro.io/pt/cob");
		String URL = driver.getCurrentUrl();
		Assert.assertEquals(URL, "https://tst.contatoseguro.io/pt/cob");
	}

	@Test
	public void validarTelefone() {
		driver.get("https://tst.contatoseguro.io/pt/cob");
		String telefone = driver.findElement(By.xpath("/html/body/main/header/div/div/div/div[1]/a")).getAttribute("href");
		String textoTelefone = driver.findElement(By.xpath("/html/body/main/div[4]/div[2]/div/div")).getText();
		Assert.assertEquals(telefone, "tel:0800 343 7231");
		Assert.assertEquals(textoTelefone.contains("0800 343 7231"), true);
	}
	
	@Test
	public void validarIdioma() {
		
		driver.get("https://tst.contatoseguro.io/pt/cob");
		WebElement button = driver.findElement(By.id("dropdownMenuButton"));
		button.click();
		String idiomaEspanhol = driver.findElement(By.xpath("//*[@id=\"navbar-dropdown\"]/ul/li[4]/div/div/a[2]/img")).getAttribute("title");
		String idiomaPortugues = driver.findElement(By.xpath("//*[@id=\"navbar-dropdown\"]/ul/li[4]/div/div/a[1]/img")).getAttribute("title");
		Assert.assertEquals(idiomaEspanhol, "Espanhol");
		Assert.assertEquals(idiomaPortugues, "Português");
	}
	
	@Test
	public void validarNomeEmpresa() {
		driver.get("https://tst.contatoseguro.io/pt/cob");
		String nomeEmpresa = driver.findElement(By.xpath("/html/body/main/header/div/div/div/div[1]/h2")).getText();
		Assert.assertEquals(nomeEmpresa.contains("COB - Comitê Olimpico Brasileiro"), true);
		
	}
	@AfterClass
	public static void finaliza() throws InterruptedException{
		Thread.sleep(3000);
		driver.close();
	}
	
	
}
