package week4.day2;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Amazon {

	public static void main(String[] args) throws IOException, InterruptedException {
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver(options);
		driver.get("https://www.amazon.in/");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5000));
		driver.manage().window().maximize();
		driver.findElement(By.id("twotabsearchtextbox")).sendKeys("oneplus 9 pro");
		driver.findElement(By.id("nav-search-submit-button")).click();
		String price = driver.findElement(By.xpath("(//span[@class='a-price-whole'])[1]")).getText();
		System.out.println("The Price of the first Product " + price);
		String ratings = driver.findElement(By.xpath("(//span[@class='a-size-base'])[1]")).getText();
		System.out.println("No.of.Ratings for the first Product " + ratings);
		driver.findElement(By.xpath("//a[@class='a-popover-trigger a-declarative']//i")).click();
		String percent = driver.findElement(By.xpath("(//a[contains(@title,'have 5 stars')])[3]")).getText();
		System.out.println("Percentage of ratings for the 5 star: " + percent);
		driver.findElement(By.xpath("(//a[@class = 'a-link-normal a-text-normal'])[1]")).click();
		Set<String> handles = driver.getWindowHandles();
		List<String> handlesList = new ArrayList<String>();
		handlesList.addAll(handles);
		driver.switchTo().window(handlesList.get(1));
		WebElement phoneImage = driver.findElement(By.xpath("//div[@id='imgTagWrapperId']/img"));
		File source = phoneImage.getScreenshotAs(OutputType.FILE);
		File dest = new File("screenshots/phone.png");
		FileUtils.copyFile(source, dest);
		Thread.sleep(5000);
		driver.findElement(By.xpath("//input[@title='Add to Shopping Cart']")).click();
		driver.findElement(By.xpath("//span[@id='attach-sidesheet-view-cart-button']")).click();
		String subTotal = driver.findElement(By.xpath("//span[contains(@id,'sc-subtotal-amount-activecart')]")).getText();
		System.out.println(subTotal);
		subTotal=subTotal.replaceAll(".","");
		if (price.contains(subTotal)) {
			System.out.println("Validation Complete");
		} else
			System.out.println("Failed");

	}

}