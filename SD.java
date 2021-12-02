package week4.day2;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SD {

	@SuppressWarnings("unused")
	public static void main(String[] args) throws InterruptedException, IOException {
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver(options);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5000));

		driver.get("https://www.snapdeal.com/");
		driver.manage().window().maximize();
		WebElement menFashion = driver.findElement(
				By.cssSelector("div#leftNavMenuRevamp>div>div:nth-of-type(2)>ul>li:nth-of-type(7)>a>span"));
		Actions builder = new Actions(driver);
		builder.moveToElement(menFashion).perform();
		driver.findElement(By.xpath("//span[text()='Sports Shoes']")).click();
		String total = driver.findElement(By.xpath("//span[@class='category-count']")).getText();
		System.out.println("Number of Sport Shoes: " + total.replaceAll("[^0-9]", ""));
		driver.findElement(By.xpath("//div[text()='Training Shoes']")).click();
		driver.findElement(By.xpath("//div[@class='sort-selected']")).click();
		driver.findElement(By.xpath("(//ul[@class='sort-value'])/li[2]")).click();
		Thread.sleep(5000);
		List<WebElement> price = driver.findElements(By.xpath("//span[@class='lfloat product-price']"));
		List<Integer> priceList = new ArrayList<Integer>();
		boolean isSorted = false;

		for (int i = 0; i <= price.size() - 1; i++) {
			String text = price.get(i).getText();
			String value = text.replaceAll("[^0-9]", "");
			int number = Integer.parseInt(value);
			priceList.add(number);

		}

		List<Integer> priceList1 = new ArrayList<Integer>();
		priceList1.addAll(priceList);

		Collections.sort(priceList1);
		boolean sorted = priceList1.equals(priceList);

		if (sorted) 
			System.out.println("The price is sorted from Low to High");
		 else 
			System.out.println("The price is not sorted correctly");
		

		System.out.println(priceList);
		driver.findElement(By.name("fromVal")).clear();
		driver.findElement(By.name("fromVal")).sendKeys("900");
		driver.findElement(By.name("toVal")).clear();
		driver.findElement(By.name("toVal")).sendKeys("1200");
		driver.findElement(By.xpath("//div[contains(text(),'GO')]")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//label[@for='Color_s-Red']")).click();
		Thread.sleep(3000);
		String filter1 = driver.findElement(By.xpath("(//div[@class='navFiltersPill']/a)[1]")).getText();
		String filter2 = driver.findElement(By.xpath("(//div[@class='navFiltersPill']/a)[2]")).getText();
		System.out.println(filter1);
		System.out.println(filter2);

		if ((filter1.contains("Rs. 900 - Rs. 1200")) && (filter2.contains("Black"))) {
			System.out.println("Filter is applied correctly");
		}

		WebElement firstElement = driver.findElement(By.xpath("//picture[@class='picture-elem']//img"));

		Actions builder1 = new Actions(driver);
		builder1.moveToElement(firstElement).perform();

		Thread.sleep(5000);
		driver.findElement(By.xpath("(//div[contains(text(),'Quick View')])[1]")).click();
		Thread.sleep(5000);
		String cost = driver.findElement(By.xpath("//span[@class='payBlkBig']")).getText();
		String percent = driver.findElement(By.xpath("//span[@class='percent-desc ']")).getText();
		System.out.println("The Cost the Shoe: " +cost);
		System.out.println("The Discount Percentage of the shoe: "+percent);

		WebElement element = driver.findElement(By.xpath("//img[@itemprop='image']"));
		File source = element.getScreenshotAs(OutputType.FILE);
		File dest = new File("screenshots/shoe.png");
		FileUtils.copyFile(source, dest);
		driver.quit();
		
	}

}
