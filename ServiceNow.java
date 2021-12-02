package week4.day2;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ServiceNow {

	public static void main(String[] args) throws InterruptedException, IOException {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.get("https://dev100121.service-now.com/navpage.do");
		driver.manage().window().maximize();
		driver.switchTo().frame("gsft_main");
		driver.findElement(By.id("user_name")).sendKeys("admin");
		driver.findElement(By.id("user_password")).sendKeys("Servicenow@123");
		driver.findElement(By.id("sysverb_login")).click();
		driver.findElement(By.id("filter")).sendKeys("incident");
		Thread.sleep(7000);
		driver.findElement(By.xpath("(//div[text()='All'])[2]")).click();
		driver.switchTo().frame("gsft_main");
		driver.findElement(By.id("sysverb_new")).click();
		driver.findElement(By.id("lookup.incident.caller_id")).click();

		Set<String> handles = driver.getWindowHandles();
		List<String> handlesList = new ArrayList<String>();
		handlesList.addAll(handles);
		driver.switchTo().window(handlesList.get(1));
		driver.findElement(By.className("glide_ref_item_link")).click();
		driver.switchTo().window(handlesList.get(0));
		driver.switchTo().frame("gsft_main");
		driver.findElement(By.id("incident.short_description")).sendKeys("Added Description");
		String incident = driver.findElement(By.id("incident.number")).getAttribute("value");
		driver.findElement(By.id("sysverb_insert_bottom")).click();
		Thread.sleep(4000);
		driver.findElement(By.xpath("(//label[text()='Search']/following::input)[1]")).sendKeys(incident);
		driver.findElement(By.xpath("(//label[text()='Search']/following::input)[1]")).sendKeys(Keys.ENTER);
		Thread.sleep(3000);
		String retunIncident = driver.findElement(By.xpath("//a[@class='linked formlink']")).getText();

		if (retunIncident.equals(incident)) {
			System.out.println("Incident Created Successfully");
		} else
			System.out.println("Incident Not Created");

		File source = driver.getScreenshotAs(OutputType.FILE);
		File dest = new File("screenshots/incident.png");
		FileUtils.copyFile(source, dest);

	}

}
