package com.br.automacaocob;



import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import junit.framework.Assert;


public class AppTest 
{
	static WebDriver driver;

    @Test
    public void validarUrl()
    {
        
        System.setProperty("webdriver.chrome.driver", "D:/User/Beatriz/Desktop/AutomacaoTeste/driver/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://tst.contatoseguro.io/pt/cob");
        driver.manage().window().maximize();
        String URL = driver.getCurrentUrl();
        Assert.assertEquals(URL, "https://tst.contatoseguro.io/pt/cob" );
    }
}
