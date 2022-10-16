package automation.testing;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.base.Function;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.time.Duration;
import java.util.Scanner;
import java.util.UUID;


public class FourthTest {
	
public static String email;
	
	@BeforeClass
	public static void SignUp() {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://demowebshop.tricentis.com/");
		
		email= UUID.randomUUID()+"@gmail.com";
		driver.findElement(By.xpath("//a[text()='Register']")).click();
		driver.findElement(By.xpath("//input[@name='FirstName']")).sendKeys("Vardenis");
		driver.findElement(By.xpath("//input[@name='LastName']")).sendKeys("Pavardenis");
		driver.findElement(By.xpath("//input[@name='Email']")).sendKeys(email);
		driver.findElement(By.xpath("//input[@name='Password']")).sendKeys("Slaptazodis123");
		driver.findElement(By.xpath("//input[@name='ConfirmPassword']")).sendKeys("Slaptazodis123");
		driver.findElement(By.xpath("//input[@name='register-button']")).click();
		driver.findElement(By.xpath("//input[@value='Continue']")).click();
		
		driver.quit();
	}
	
	
	@Test
	public void Test1() {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		FluentWait<ChromeDriver> wait = new FluentWait<ChromeDriver>(driver).withTimeout(Duration.ofSeconds(5)).pollingEvery(Duration.ofNanos(300));
		WebDriverWait wWait = new WebDriverWait(driver, Duration.ofSeconds(20));
		driver.manage().window().maximize();
		driver.get("https://demowebshop.tricentis.com/");
		
		driver.findElement(By.xpath("//a[text()='Log in']")).click();
		driver.findElement(By.xpath("//input[@id='Email']")).sendKeys(email);
		driver.findElement(By.xpath("//input[@id='Password']")).sendKeys("Slaptazodis123");
		driver.findElement(By.xpath("//input[@class='button-1 login-button']")).click();
		driver.findElement(By.xpath("//ul[@class='list']/li/a[contains(text(),'Digital downloads')]")).click();
		
		Function<WebDriver, Boolean> function = new Function<WebDriver, Boolean>(){
			public Boolean apply(WebDriver driver) {
				WebElement element = driver.findElement(By.xpath("//div[@class='ajax-loading-block-window']"));
				return !element.isDisplayed();
			}
		};
		
		try(InputStream in = new FileInputStream("C:\\test\\data1.txt")){
			Scanner scanner = new Scanner(in);
			while(scanner.hasNextLine()) {
				String product = scanner.nextLine();
				wait.until(function);
				driver.findElement(By.xpath("//a[text()='" + product + "']/ancestor::div[@class='details']/descendant::input[@value='Add to cart']")).click();
			}
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		driver.findElement(By.xpath("//span[text()='Shopping cart']")).click();
		driver.findElement(By.xpath("//input[@id='termsofservice']")).click();
		driver.findElement(By.xpath("//button[@id='checkout']")).click();
		
		boolean addressExists;
		try {
			driver.findElement(By.xpath("//select[@name='billing_address_id']"));
			addressExists = true;
		} catch (NoSuchElementException e) {
			   addressExists = false;
		}
		if(!addressExists)
		{
			driver.findElement(By.xpath("//select[@id='BillingNewAddress_CountryId']")).click();
			driver.findElement(By.xpath("//option[text()='Canada']")).click();
			driver.findElement(By.xpath("//input[@name='BillingNewAddress.City']")).sendKeys("aaccdd");
			driver.findElement(By.xpath("//input[@name='BillingNewAddress.Address1']")).sendKeys("aaccdd");
			driver.findElement(By.xpath("//input[@name='BillingNewAddress.ZipPostalCode']")).sendKeys("1526");
			driver.findElement(By.xpath("//input[@name='BillingNewAddress.PhoneNumber']")).sendKeys("15258947951");
		}
		driver.findElement(By.xpath("//input[@title='Continue']")).click();
		WebElement button1 = wWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@class='button-1 payment-method-next-step-button']")));
		button1.click();
		WebElement button2 = wWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@class='button-1 payment-info-next-step-button']")));
		button2.click();
		WebElement button3 = wWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@class='button-1 confirm-order-next-step-button']")));
		button3.click();
		
		wWait.until(ExpectedConditions.visibilityOfElementLocated((By.xpath("//strong[contains(text(), successfully)]"))));
		String text = driver.findElement(By.xpath("//strong[contains(text(), successfully)]")).getText();
		Assert.assertEquals(text, "Your order has been successfully processed!");
		
		driver.quit();
	}
	
	@Test
	public void Test2() {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		FluentWait<ChromeDriver> wait = new FluentWait<ChromeDriver>(driver).withTimeout(Duration.ofSeconds(5)).pollingEvery(Duration.ofNanos(300));
		WebDriverWait wWait = new WebDriverWait(driver, Duration.ofSeconds(20));
		driver.manage().window().maximize();
		driver.get("https://demowebshop.tricentis.com/");
		
		driver.findElement(By.xpath("//a[text()='Log in']")).click();
		driver.findElement(By.xpath("//input[@id='Email']")).sendKeys(email);
		driver.findElement(By.xpath("//input[@id='Password']")).sendKeys("Slaptazodis123");
		driver.findElement(By.xpath("//input[@class='button-1 login-button']")).click();
		driver.findElement(By.xpath("//ul[@class='list']/li/a[contains(text(),'Digital downloads')]")).click();
		
		Function<WebDriver, Boolean> function = new Function<WebDriver, Boolean>(){
			public Boolean apply(WebDriver driver) {
				WebElement element = driver.findElement(By.xpath("//div[@class='ajax-loading-block-window']"));
				return !element.isDisplayed();
			}
		};
		
		try(InputStream in = new FileInputStream("C:\\test\\data2.txt")){
			Scanner scanner = new Scanner(in);
			while(scanner.hasNextLine()) {
				String product = scanner.nextLine();
				wait.until(function);
				driver.findElement(By.xpath("//a[text()='" + product + "']/ancestor::div[@class='details']/descendant::input[@value='Add to cart']")).click();
			}
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		driver.findElement(By.xpath("//span[text()='Shopping cart']")).click();
		driver.findElement(By.xpath("//input[@id='termsofservice']")).click();
		driver.findElement(By.xpath("//button[@id='checkout']")).click();
		
		boolean addressExists;
		try {
			driver.findElement(By.xpath("//select[@name='billing_address_id']"));
			addressExists = true;
		} catch (NoSuchElementException e) {
			   addressExists = false;
		}
		if(!addressExists)
		{
			driver.findElement(By.xpath("//select[@id='BillingNewAddress_CountryId']")).click();
			driver.findElement(By.xpath("//option[text()='Canada']")).click();
			driver.findElement(By.xpath("//input[@name='BillingNewAddress.City']")).sendKeys("aaccdd");
			driver.findElement(By.xpath("//input[@name='BillingNewAddress.Address1']")).sendKeys("aaccdd");
			driver.findElement(By.xpath("//input[@name='BillingNewAddress.ZipPostalCode']")).sendKeys("1526");
			driver.findElement(By.xpath("//input[@name='BillingNewAddress.PhoneNumber']")).sendKeys("15258947951");
		}
		driver.findElement(By.xpath("//input[@title='Continue']")).click();
		WebElement button1 = wWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@class='button-1 payment-method-next-step-button']")));
		button1.click();
		WebElement button2 = wWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@class='button-1 payment-info-next-step-button']")));
		button2.click();
		WebElement button3 = wWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@class='button-1 confirm-order-next-step-button']")));
		button3.click();
		
		wWait.until(ExpectedConditions.visibilityOfElementLocated((By.xpath("//strong[contains(text(), successfully)]"))));
		String text = driver.findElement(By.xpath("//strong[contains(text(), successfully)]")).getText();
		Assert.assertEquals(text, "Your order has been successfully processed!");
		
		driver.quit();
	}

}
