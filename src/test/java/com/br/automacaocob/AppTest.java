package com.br.automacaocob;



import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class AppTest 
{
	static WebDriver driver;

    @Test
    public void shouldAnswerWithTrue()
    {
        
        System.setProperty("webdriver.chrome.driver", "D:/User/Beatriz/Desktop/AutomacaoTeste/driver/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://www.google.com.br");
        driver.manage().window().maximize();
    }
}
