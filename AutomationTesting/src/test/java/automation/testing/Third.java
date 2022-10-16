package automation.testing;

import java.time.Duration;
import java.util.Random;
import java.util.UUID;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.FluentWait;

import com.google.common.base.Function;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Third {
	
	public static void main(String[] args) {
		final Random random = new Random();
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://demoqa.com/");
	
		driver.findElement(By.xpath("//div[@class='card-body']/h5[text()='Elements']")).click();
		driver.findElement(By.xpath("//span[text()='Web Tables']")).click();
		String firstName = driver.findElement(By.xpath("//div[@class='rt-td']")).getText();
		FluentWait<ChromeDriver> wait = new FluentWait<ChromeDriver>(driver).withTimeout(Duration.ofSeconds(10)).pollingEvery(Duration.ofNanos(300));
		
		Function<WebDriver, Boolean> function = new Function<WebDriver, Boolean>(){
			public Boolean apply(WebDriver driver) {
				int max, min, salary, age;
				driver.findElement(By.xpath("//button[@id='addNewRecordButton']")).click();
				driver.findElement(By.xpath("//input[@id='firstName']")).sendKeys(UUID.randomUUID().toString().substring(10));
				driver.findElement(By.xpath("//input[@id='lastName']")).sendKeys(UUID.randomUUID().toString().substring(10));
				driver.findElement(By.xpath("//input[@id='userEmail']")).sendKeys(UUID.randomUUID()+"@gmail.com");
				min = 18; max = 64;
				age = min + random.nextInt(max);
				driver.findElement(By.xpath("//input[@id='age']")).sendKeys(String.valueOf(age));
				min = 700; max = 4500;
				salary = min + random.nextInt(max);
				driver.findElement(By.xpath("//input[@id='salary']")).sendKeys(String.valueOf(salary));
				driver.findElement(By.xpath("//input[@id='department']")).sendKeys(UUID.randomUUID().toString().substring(10));
				driver.findElement(By.xpath("//button[text()='Submit']")).click();
				WebElement element = driver.findElement(By.xpath("//button[text()='Next']"));
				return element.isEnabled();
			}
		};
		wait.until(function);
		 JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,150)", "");
		driver.findElement(By.xpath("//button[text()='Next']")).click();
		driver.findElement(By.xpath("//span[@title='Delete']")).click();
		String newFirstName = driver.findElement(By.xpath("//div[@class='rt-td']")).getText();
		String totalPages = driver.findElement(By.xpath("//span[@class='-totalPages']")).getText();
		
		if(firstName.equals(newFirstName) && totalPages.equals("1")) { System.out.println("DONE!"); }
		
		driver.quit();
	}

}
