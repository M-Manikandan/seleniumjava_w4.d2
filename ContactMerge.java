package week4.day2;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ContactMerge {

	public static void main(String[] args) throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.get("http://leaftaps.com/opentaps/control/login");
		driver.manage().window().maximize();
		driver.findElement(By.id("username")).sendKeys("DemoSalesManager");
		driver.findElement(By.id("password")).sendKeys("crmsfa");
		driver.findElement(By.className("decorativeSubmit")).click();
		driver.findElement(By.linkText("CRM/SFA")).click();
		driver.findElement(By.linkText("Contacts")).click();
		driver.findElement(By.xpath("//a[text()='Merge Contacts']")).click();
		driver.findElement(By.xpath("//table[@name='ComboBox_partyIdFrom']/following-sibling::a")).click();
		Set<String> handles = driver.getWindowHandles();
		List<String> handlesList = new ArrayList<String>();
		handlesList.addAll(handles);
		driver.switchTo().window(handlesList.get(1));
		Thread.sleep(5000);
		driver.findElement(By.xpath("//div[@class = 'x-grid3-cell-inner x-grid3-col-partyId']/a")).click();
		driver.switchTo().window(handlesList.get(0));
		driver.findElement(By.xpath("//table[@name='ComboBox_partyIdTo']/following-sibling::a")).click();
		Set<String> handles1 = driver.getWindowHandles();
		List<String> handlesList1 = new ArrayList<String>();
		handlesList1.addAll(handles1);
		driver.switchTo().window(handlesList1.get(1));
		Thread.sleep(5000);
		driver.findElement(By.xpath("(//div[@class='x-grid3-cell-inner x-grid3-col-partyId'])[2]/a")).click();
		driver.switchTo().window(handlesList1.get(0));
		driver.findElement(By.xpath("//a[@class='buttonDangerous']")).click();
		driver.switchTo().alert().accept();
		System.out.println(driver.getTitle());

	}

}
