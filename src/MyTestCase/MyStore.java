package MyTestCase;

import java.time.Duration;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.google.common.annotations.VisibleForTesting;

import net.bytebuddy.build.Plugin.Factory.UsingReflection.Priority;

public class MyStore {

//	WebDriver driver = new ChromeDriver();
	WebDriver driver = new EdgeDriver();
	Random rand = new Random();

	@BeforeTest
	public void MySetUP() {
		driver.get("http://localhost:3000/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
	}

	@Test(priority = 1, enabled = false)
	public void OpenSingUpPage() throws InterruptedException {
		WebElement DropDown = driver.findElement(By.className("dropdown"));
		DropDown.click();
		Thread.sleep(2000);
		List<WebElement> SingUP = driver.findElements(By.className("dropdown-item"));
		SingUP.get(1).click();
	}

	@Test(priority = 2, enabled = false)
	public void CreateUser() throws InterruptedException {
		// WebElement
		WebElement Name = driver.findElement(By.id("signup-name"));
		WebElement Email = driver.findElement(By.id("signup-email"));
		WebElement Password = driver.findElement(By.id("signup-password"));
		WebElement ConfirmPassword = driver.findElement(By.id("signup-confirm-password"));
		Thread.sleep(3000);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0,500)");
		WebElement CustomerRole = driver.findElement(By.id("label-role-customer"));
		WebElement SingUpSubmit = driver.findElement(By.xpath("//button[@data-testid='testid-signup-submit']"));

		// Action
		Name.sendKeys("TestUser1");
		Email.sendKeys("test1@gmail.com");
		Password.sendKeys("Password123@");
		ConfirmPassword.sendKeys("Password123@");
		CustomerRole.click();
		SingUpSubmit.click();

	}

	@Test(priority = 3, enabled = false)
	public void LoginOnSellerPage() throws InterruptedException {
		WebElement DropDown = driver.findElement(By.className("dropdown"));
		DropDown.click();
		Thread.sleep(2000);
		List<WebElement> Login = driver.findElements(By.className("dropdown-item"));
		Login.get(0).click();
		// Login With Sellar account
		// webelement
		WebElement EmailInput = driver.findElement(By.id("login-email"));
		WebElement PasswordInput = driver.findElement(By.id("login-password"));
		WebElement LoginButton = driver.findElement(By.id("login-submit-btn"));

		// Action
		EmailInput.sendKeys("seller1@gmail.com");
		PasswordInput.sendKeys("Password123@");
		LoginButton.click();

		// Assertion
		Assert.assertEquals(driver.findElement(By.id("seller-welcome")).getText(), "Welcome, Seller1");
	}

	@Test(priority = 4, enabled = false)
	public void AddProduct() throws InterruptedException {
		// WebElement
		WebElement MyProduct = driver.findElement(By.id("nav-products"));
		MyProduct.click();

		String[] productNames = { "Laptop", "Mobile Phone", "Headphones", "Keyboard", "Mouse", "Monitor", "Tablet",
				"Smartwatch", "Printer", "USB Flash" };
		String[] productPrices = { "1200", "800", "150", "70", "40", "300", "500", "200", "250", "25" };

		String[] productStocks = { "5", "12", "25", "40", "60", "10", "8", "18", "7", "100" };

		for (int i = 0; i <= productNames.length; i++) {
			int randomeInfo = rand.nextInt(productNames.length);
			Thread.sleep(1000);
			WebElement NameProduct = driver.findElement(By.id("input-product-name"));
			WebElement PriceProduct = driver.findElement(By.id("input-product-price"));
			WebElement StockProduct = driver.findElement(By.id("input-product-stock"));
			WebElement AddButton = driver.findElement(By.id("add-product-btn"));
			// Action
			Thread.sleep(1000);
			NameProduct.sendKeys(productNames[randomeInfo]);
			PriceProduct.sendKeys(productPrices[randomeInfo]);
			StockProduct.sendKeys(productStocks[randomeInfo]);
			AddButton.click();
		}
		// Assertion
		Assert.assertEquals(driver.findElement(By.className("message")).getText(), "Product created successfully");
	}

	@Test(priority = 5, enabled = false)
	public void DeleteProductRandom() throws InterruptedException {
		WebElement MyProduct = driver.findElement(By.id("nav-products"));
		MyProduct.click();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement TheTable = driver.findElement(By.id("products-list-table"));
		boolean flag = true;
		while (flag) {
			List<WebElement> deleteButton = driver.findElements(By.className("delete-btn"));
			if (deleteButton.size() > 0) {
				Thread.sleep(2000);
				js.executeScript("arguments[0].scrollIntoView(true);", deleteButton.get(0));
				Thread.sleep(500);
				deleteButton.get(0).click();
				Alert alert = driver.switchTo().alert();
				alert.accept();
				Thread.sleep(3000);
				System.out.println("Is Removed");
			} else {
				flag = false;
			}
		}
		Thread.sleep(1500);
		// Assertion
		Assert.assertEquals(driver.findElement(By.className("message")).getText(), "Product deleted successfully");
	}

	// Normal User And Add Items
	@Test(priority = 6, enabled = true)
	public void LoginOnCustomerPage() throws InterruptedException {
		WebElement DropDown = driver.findElement(By.className("dropdown"));
		DropDown.click();
		Thread.sleep(2000);
		List<WebElement> Login = driver.findElements(By.className("dropdown-item"));
		Login.get(0).click();
		// Login With Sellar account
		// webelement
		WebElement EmailInput = driver.findElement(By.id("login-email"));
		WebElement PasswordInput = driver.findElement(By.id("login-password"));
		WebElement LoginButton = driver.findElement(By.id("login-submit-btn"));

		// Action
		EmailInput.sendKeys("test1@gmail.com");
		PasswordInput.sendKeys("Password123@");
		LoginButton.click();
		Thread.sleep(1000);
		WebElement Shop = driver.findElement(By.linkText("SHOP"));
		Shop.click();
	}

	@Test(priority = 7, enabled = false)
	public void AddItems() throws InterruptedException {
		WebElement WomenCollection = driver.findElement(By.id("cat-pill-women"));
		WomenCollection.click();
		Thread.sleep(2000);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		Thread.sleep(1000);
		
		List<WebElement>AddButton=driver.findElements(By.cssSelector(".add-cart-btn.test-add-btn"));
		int randomeItem= rand.nextInt(AddButton.size());
		
		WebElement selectedButton = AddButton.get(randomeItem);
		System.out.println("Adding: " + selectedButton.getAttribute("aria-label"));
		js.executeScript("arguments[0].scrollIntoView(true);",  selectedButton);
		Thread.sleep(500);
		js.executeScript("window.scrollBy(0, -100);");
		Thread.sleep(500);
		js.executeScript("arguments[0].click();", selectedButton);

	}
	@Test(priority = 8,enabled = true)
	public void  viewCart() throws InterruptedException {
		WebElement Cart=driver.findElement(By.xpath("//a[@aria-label='Shopping cart']"));
		Cart.click();
		Thread.sleep(1000);
		Assert.assertEquals(driver.findElement(By.tagName("h1")).getText(), "Shopping Cart");
	}
	@Test(priority = 9,enabled = true)
	public void Logout() throws InterruptedException {
		WebElement DropDown = driver.findElement(By.className("dropdown"));
		DropDown.click();
	
		Thread.sleep(1000);
		WebElement logout= driver.findElement(By.xpath("//button[text()='Logout']"));
		logout.click();
		Assert.assertEquals(driver.findElement(By.id("login-header")).getText(),"Login to Your Account" );
	}
	@AfterTest
	public void CloseTest() {
		driver.quit();
	}
	}
