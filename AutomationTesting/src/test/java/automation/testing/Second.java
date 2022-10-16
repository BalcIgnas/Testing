package automation.testing;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Second {
	
	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://demoqa.com/");
		
		driver.findElement(By.xpath("//div[@class='card-body']/h5[text()='Widgets']")).click();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,250)", "");
		driver.findElement(By.xpath("//span[text()='Progress Bar']")).click();
		driver.findElement(By.xpath("//button[@id='startStopButton']")).click();
		WebElement button = new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@id='resetButton']")));
		button.click();
		String procent = driver.findElement(By.xpath("//div[@role='progressbar' and text()='0%']")).getText();
		if(procent.equals("0%")) { System.out.println("DONE!"); }
		
		driver.quit();
	}

}
