package automation.testing;

import java.time.Duration;
import java.util.UUID;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class First {
	
	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://demowebshop.tricentis.com/");
		
		driver.findElement(By.xpath("//a[text()='Log in']")).click();
		driver.findElement(By.xpath("//input[@value='Register']")).click();
		driver.findElement(By.xpath("//input[@name='FirstName']")).sendKeys("Vardenis");
		driver.findElement(By.xpath("//input[@name='LastName']")).sendKeys("Pavardenis");
		driver.findElement(By.xpath("//input[@name='Email']")).sendKeys(UUID.randomUUID()+"@gmail.com");
		driver.findElement(By.xpath("//input[@name='Password']")).sendKeys("Slaptazodis123");
		driver.findElement(By.xpath("//input[@name='ConfirmPassword']")).sendKeys("Slaptazodis123");
		driver.findElement(By.xpath("//input[@name='register-button']")).click();
		driver.findElement(By.xpath("//input[@value='Continue']")).click();
		driver.findElement(By.xpath("//ul[@class='list']/li/a[contains(text(),'Computers')]")).click();
		driver.findElement(By.xpath("//ul[@class='list']/li/ul/li/a[contains(text(),'Desktops')]")).click();
		driver.findElement(By.xpath("//div[@class='add-info' and .//div[@class='prices' and .//span[@class='price actual-price' and text()>1500]]]/div[@class='buttons']/input[@value='Add to cart']")).click();
		WebElement button = new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='add-to-cart-button-74']")));
		button.click();
		WebElement button1 = new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@class='ico-cart']/span[text()='(1)']")));
		button1.click();
		driver.findElement(By.xpath("//input[@name='removefromcart']")).click();
		driver.findElement(By.xpath("//input[@name='updatecart']")).click();
		String text = driver.findElement(By.xpath("//div[contains(text(), 'Your Shopping Cart is empty!')]")).getText();
		if(text.contains("empty")) { System.out.print("DONE!"); }
		
		driver.quit();
	}

}
