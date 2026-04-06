package MyTestCase;

import java.time.Duration;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class MyStore {

	WebDriver driver = new EdgeDriver();
	Random rand = new Random();

	JavascriptExecutor js;
	Actions actions;

	@BeforeTest
	public void MySetUP() {
		driver.get("http://localhost:3000/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
	}

	@Test(priority = 1, enabled = true)
	public void OpenSingUpPage() throws InterruptedException {
		Thread.sleep(800);
		WebElement DropDown = driver.findElement(By.className("dropdown"));
		DropDown.click();
		Thread.sleep(500);
		List<WebElement> SingUP = driver.findElements(By.className("dropdown-item"));
		SingUP.get(1).click();
		Thread.sleep(500);
	}

	@Test(priority = 2, enabled = true)
	public void CreateSeller() throws InterruptedException {
		Thread.sleep(800);
		WebElement Name = driver.findElement(By.id("signup-name"));
		WebElement Email = driver.findElement(By.id("signup-email"));
		WebElement Password = driver.findElement(By.id("signup-password"));
		WebElement ConfirmPassword = driver.findElement(By.id("signup-confirm-password"));

		WebElement CustomerRole = driver.findElement(By.id("label-role-seller"));
		WebElement SingUpSubmit = driver.findElement(By.xpath("//button[@data-testid='testid-signup-submit']"));
		js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0,500)");
		Thread.sleep(500);
		Name.sendKeys("Seller1");
		Thread.sleep(300);
		Email.sendKeys("seller1@gmail.com");
		Thread.sleep(300);
		Password.sendKeys("Password123@");
		Thread.sleep(300);
		ConfirmPassword.sendKeys("Password123@");
		Thread.sleep(600);
		CustomerRole.click();
		Thread.sleep(600);
		SingUpSubmit.click();
		Thread.sleep(800);
	}

	@Test(priority = 3, enabled = true)
	public void LoginOnSellerPage() throws InterruptedException {
		Thread.sleep(800);
		WebElement DropDown = driver.findElement(By.className("dropdown"));
		DropDown.click();
		Thread.sleep(500);
		List<WebElement> Login = driver.findElements(By.className("dropdown-item"));
		Login.get(0).click();
		Thread.sleep(500);
		driver.findElement(By.id("login-email")).sendKeys("seller1@gmail.com");
		Thread.sleep(300);
		driver.findElement(By.id("login-password")).sendKeys("Password123@");
		Thread.sleep(600);
		driver.findElement(By.id("login-submit-btn")).click();
		Thread.sleep(800);
		Assert.assertEquals(driver.findElement(By.id("seller-welcome")).getText(), "Welcome, Seller1");
	}

	@Test(priority = 4, enabled = true)
	public void AddProduct() throws InterruptedException {
		Thread.sleep(600);
		WebElement MyProduct = driver.findElement(By.id("nav-products"));
		MyProduct.click();
//		String[] productNames = { "Laptop", "Mobile Phone", "Headphones", "Keyboard", "Mouse", "Monitor", "Tablet",
//				"Smartwatch", "Printer", "USB Flash" };
//		String[] productPrices = { "1200", "800", "150", "70", "40", "300", "500", "200", "250", "25" };
//		String[] productStocks = { "5", "12", "25", "40", "60", "10", "8", "18", "7", "100" };
		String[] productNames = { "Laptop", "Mobile Phone" };
		String[] productPrices = { "1200", "800"};
		String[] productStocks = { "5", "12" };

		for (int i = 0; i < productNames.length; i++) {
			int randomeInfo = rand.nextInt(productNames.length);
			Thread.sleep(1000);
			WebElement NameProduct = driver.findElement(By.id("input-product-name"));
			WebElement PriceProduct = driver.findElement(By.id("input-product-price"));
			WebElement StockProduct = driver.findElement(By.id("input-product-stock"));
			WebElement AddButton = driver.findElement(By.id("add-product-btn"));

			Thread.sleep(600);
			NameProduct.sendKeys(productNames[randomeInfo]);
			Thread.sleep(300);
			PriceProduct.sendKeys(productPrices[randomeInfo]);
			Thread.sleep(300);
			StockProduct.sendKeys(productStocks[randomeInfo]);
			Thread.sleep(600);
			AddButton.click();
			Thread.sleep(800);
		}
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
				Thread.sleep(1000);
				js.executeScript("arguments[0].scrollIntoView(true);", deleteButton.get(0));
				Thread.sleep(800);
				deleteButton.get(0).click();
				Thread.sleep(600);
				Alert alert = driver.switchTo().alert();
				alert.accept();
				Thread.sleep(1200);
				System.out.println("Is Removed");
			} else {
				flag = false;
			}
		}
		Thread.sleep(800);
		Assert.assertEquals(driver.findElement(By.className("message")).getText(), "Product deleted successfully");
	}

	@Test(priority = 6, enabled = true)
	public void LogoutFromSeller() throws InterruptedException {
		Thread.sleep(800);
		WebElement LogoutButton = driver.findElement(By.id("seller-logout-btn"));
		LogoutButton.click();
		Thread.sleep(600);
		Assert.assertEquals(driver.findElement(By.id("login-header")).getText(), "Login to Your Account");
	}

	@Test(priority = 7, enabled = true)
	public void OpenSingUpPageForUser() throws InterruptedException {
		Thread.sleep(800);
		WebElement DropDown = driver.findElement(By.className("dropdown"));
		DropDown.click();
		Thread.sleep(500);
		List<WebElement> SingUP = driver.findElements(By.className("dropdown-item"));
		SingUP.get(1).click();
		Thread.sleep(500);
	}

	@Test(priority = 8, enabled = true)
	public void CreateUser() throws InterruptedException {
		WebElement Name = driver.findElement(By.id("signup-name"));
		WebElement Email = driver.findElement(By.id("signup-email"));
		WebElement Password = driver.findElement(By.id("signup-password"));
		WebElement ConfirmPassword = driver.findElement(By.id("signup-confirm-password"));
		Thread.sleep(1200);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0,500)");
		Thread.sleep(500);
		WebElement CustomerRole = driver.findElement(By.id("label-role-customer"));
		WebElement SingUpSubmit = driver.findElement(By.xpath("//button[@data-testid='testid-signup-submit']"));

		Name.sendKeys("TestUser1");
		Thread.sleep(300);
		Email.sendKeys("test1@gmail.com");
		Thread.sleep(300);
		Password.sendKeys("Password123@");
		Thread.sleep(300);
		ConfirmPassword.sendKeys("Password123@");
		Thread.sleep(600);
		CustomerRole.click();
		Thread.sleep(600);
		SingUpSubmit.click();
		Thread.sleep(800);
	}

	@Test(priority = 9, enabled = true)
	public void LoginOnCustomerPage() throws InterruptedException {
		Thread.sleep(600);
		WebElement DropDown = driver.findElement(By.className("dropdown"));
		DropDown.click();
		Thread.sleep(1000);
		List<WebElement> Login = driver.findElements(By.className("dropdown-item"));
		Login.get(0).click();
		Thread.sleep(500);
		WebElement EmailInput = driver.findElement(By.id("login-email"));
		WebElement PasswordInput = driver.findElement(By.id("login-password"));
		WebElement LoginButton = driver.findElement(By.id("login-submit-btn"));
		EmailInput.sendKeys("test1@gmail.com");
		Thread.sleep(300);
		PasswordInput.sendKeys("Password123@");
		Thread.sleep(600);
		LoginButton.click();
		Thread.sleep(1500);
		WebElement Shop = driver.findElement(By.linkText("SHOP"));
		Shop.click();
		Thread.sleep(600);
	}

	@Test(priority = 10, enabled = true)
	public void AddItemsToCart() throws InterruptedException {
		Thread.sleep(800);
		WebElement WomenCollection = driver.findElement(By.id("cat-pill-women"));
		WomenCollection.click();
		Thread.sleep(1000);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		Thread.sleep(500);
		List<WebElement> AddButton = driver.findElements(By.cssSelector(".add-cart-btn.test-add-btn"));
		int randomeItem = rand.nextInt(AddButton.size());
		WebElement selectedButton = AddButton.get(randomeItem);
		System.out.println("Adding: " + selectedButton.getAttribute("aria-label"));
		js.executeScript("arguments[0].scrollIntoView(true);", selectedButton);
		Thread.sleep(800);
		js.executeScript("window.scrollBy(0, -100);");
		Thread.sleep(600);
		js.executeScript("arguments[0].click();", selectedButton);
		Thread.sleep(800);
	}

	@Test(priority = 11, enabled = true)
	public void viewCart() throws InterruptedException {
		Thread.sleep(600);
		WebElement Cart = driver.findElement(By.xpath("//a[@aria-label='Shopping cart']"));
		Cart.click();
		Thread.sleep(600);
		driver.navigate().refresh();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0, 0)");
		Thread.sleep(600);
		Assert.assertTrue(driver.findElement(By.tagName("h1")).getText().contains("Shopping Cart"));
	}

	@Test(priority = 12, enabled = true)
	public void Logout() throws InterruptedException {
		Thread.sleep(800);
		driver.findElement(By.className("dropdown")).click();
		Thread.sleep(600);
		driver.findElement(By.xpath("//button[text()='Logout']")).click();
		Thread.sleep(600);
		Assert.assertEquals(driver.findElement(By.id("login-header")).getText(), "Login to Your Account");
	}

	@AfterTest
	public void CloseTest() {
		driver.quit();
	}
}