package week4.day2;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Nykaa {

	public static void main(String[] args) throws InterruptedException {
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver(options);
		driver.get("https://www.nykaa.com/");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5000));
		driver.manage().window().maximize();
		WebElement element = driver.findElement(By.xpath("//a[text()='brands']"));
		Actions builder = new Actions(driver);
		builder.moveToElement(element).perform();
		Thread.sleep(5000);
		driver.findElement(By.xpath("//img[contains(@src,'lorealparis')]")).click();
		String landingPage = driver.getTitle();
		if (landingPage.contains("L'Oreal Paris")) {
			System.out.println("Landed on correct Page");
		}

		driver.findElement(By.className("sort-name")).click();
		driver.findElement(By.xpath("//span[text()='customer top rated']/following::div[1]")).click();
		driver.findElement(By.id("category")).click();

		WebElement hair = driver.findElement(By.xpath("(//a[text()='hair'])[1]"));
		Thread.sleep(5000);
		builder.moveToElement(hair).perform();
		driver.findElement(By.linkText("Shampoo")).click();

		Set<String> handles = driver.getWindowHandles();
		List<String> handlesList = new ArrayList<String>();
		handlesList.addAll(handles);
		driver.switchTo().window(handlesList.get(1));

		driver.findElement(By.xpath("//span[text()='Concern']")).click();
		Thread.sleep(5000);
		driver.findElement(By.xpath("(//label[@for='checkbox_Color Protection_10764']//div)[2]")).click();

		String filterText = driver.findElement(By.xpath("//span[@class='filter-value']")).getText();
		if (filterText.contains("Color Protection")) {
			System.out.println("Filter applied correctly");
		}

		driver.findElement(By.xpath("//div[contains(text(),'Oreal Paris Colour Protect Shampoo')]")).click();

		Set<String> handles1 = driver.getWindowHandles();
		List<String> handlesList1 = new ArrayList<String>();
		handlesList1.addAll(handles1);
		driver.switchTo().window(handlesList1.get(2));

		WebElement size = driver.findElement(By.xpath("//select[@title='SIZE']"));
		Select s = new Select(size);
		s.selectByVisibleText("175ml");

		driver.findElement(By.xpath("//span[text()='ADD TO BAG']")).click();
		Thread.sleep(5000);

		driver.findElement(By.xpath("//span[@class='cart-count']")).click();
		Thread.sleep(5000);
		driver.switchTo().frame(0);
		String grandTotal = driver.findElement(By.xpath("//div[@class='name medium-strong']/following-sibling::div"))
				.getText();

		System.out.println("The Grand Total " + grandTotal);

		driver.findElement(By.xpath("//span[text()='PROCEED']")).click();

		driver.findElement(By.xpath("//button[text()='CONTINUE AS GUEST']")).click();

		String finalTotal = driver.findElement(By.xpath("//div[text()='Grand Total']/following-sibling::div"))
				.getText();
		System.out.println(finalTotal);

		if (grandTotal.equals(finalTotal)) {
			System.out.println("Grand Total is same");
		}

		driver.quit();

	}

}
