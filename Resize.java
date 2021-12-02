package week4.day2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Resize{

	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.get("https://jqueryui.com/resizable");
		driver.switchTo().frame(0);
		WebElement resize = driver.findElement(By.xpath("//div[@id='resizable']/div[3]"));
		Actions builder = new Actions(driver);
		builder.dragAndDropBy(resize, 100, 30).perform();

	}

}
