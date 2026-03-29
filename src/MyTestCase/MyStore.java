package MyTestCase;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.google.common.annotations.VisibleForTesting;

public class MyStore {
	
	WebDriver driver=new ChromeDriver();
	
	@BeforeTest
	public void MySetUP() {
		driver.get("http://localhost:3000/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
	}
	@Test(priority = 1)
	public void OpenSingUpPage() throws InterruptedException {
		WebElement DropDown=driver.findElement(By.className("dropdown"));
		DropDown.click();
		Thread.sleep(2000);
		List<WebElement> SingUP=driver.findElements(By.className("dropdown-item"));
		SingUP.get(1).click();
	}
	@Test(priority = 2)
	public void CreateUser() throws InterruptedException {
		//WebElement
		WebElement Name= driver.findElement(By.id("signup-name"));
		WebElement Email=driver.findElement(By.id("signup-email"));
		WebElement Password=driver.findElement(By.id("signup-password"));
		WebElement ConfirmPassword=driver.findElement(By.id("signup-confirm-password"));
		Thread.sleep(3000);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0,500)");
		WebElement CustomerRole=driver.findElement(By.id("label-role-customer"));
		WebElement SingUpSubmit=driver.findElement(By.xpath("//button[@data-testid='testid-signup-submit']"));
		
		//Action
		Name.sendKeys("TestUser1");
		Email.sendKeys("test1@gmail.com");
		Password.sendKeys("Password123@");
		ConfirmPassword.sendKeys("Password123@");
		CustomerRole.click();
		SingUpSubmit.click();
		
	}

}
