package TestDemo;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CricketScoreBoard {

	public static void main(String[] args) {

		WebDriverManager.chromedriver().setup();

		ChromeOptions co = new ChromeOptions();
		co.addArguments("--headless");
		WebDriver driver = new ChromeDriver(co);
		 driver.get(args[0]);
//		driver.get(
//				"http://www.espncricinfo.com/series/8048/scorecard/1181768/mumbai-indians-vs-chennai-super-kings-final-indian-premier-league-2019");

		if (!driver.findElement(By.xpath("(//div[@class='cell batsmen' and text()='BATSMEN'])[1]")).isDisplayed()) {
			driver.findElement(By.xpath(
					"(//article[@class='sub-module scorecard'])[1]" + "//a[@role='button' and @class='collapsed']"))
					.click();
		}

		String s1 = driver.findElement(By.xpath("//a[@aria-controls='gp-inning-00']")).getText();
		String s2 = driver.findElement(By.xpath("//a[@aria-controls='gp-inning-01']")).getText();

		System.out.println("Match: "+ s1.substring(0, s1.indexOf("Innings")) + " v/s " + s2.substring(0, s2.indexOf("Innings")));
		System.out.println();
		

		System.out
				.println("1st inning: " + driver.findElement(By.xpath("//a[@aria-controls='gp-inning-00']")).getText());
		
		List<WebElement> firstInnings = driver.findElements(By.xpath(""
				+ "//div[@id='gp-inning-00']//div[@class='cell batsmen']//a[@name='&lpos=cricket:game:scorecard:player']"));

		System.out.println("Total batsmen played: " + firstInnings.size());
		String batsmen = driver.findElement(By.xpath(""
				+ "//div[@id='gp-inning-00']//div[@class='wrap header']/div[@class='cell batsmen']")).getText();
		List<WebElement> headers = driver.findElements(By.xpath(""
				+ "//div[@id='gp-inning-00']//div[@class='wrap header']/div[@class='cell runs']"));
		System.out.print(batsmen +"          ");
		for(int i=0; i<headers.size(); i++){
			System.out.print(headers.get(i).getText() + "\t");
		}
		for (int i = 0; i < firstInnings.size(); i++) {
			System.out.println();
			getPlayerScroreCard(driver, firstInnings.get(i).getText());
		}

		System.out.println();
		System.out.println("--------------------------------------------------------------------");

		System.out.println(
				"2nd inning: " + driver.findElement(By.xpath("//a[@aria-controls='gp-inning-01']/h2")).getText());
		List<WebElement> secondInnings = driver.findElements(By.xpath(
				"//div[@id='gp-inning-01']//div[@class='cell batsmen']//a[@name='&lpos=cricket:game:scorecard:player']"));

		System.out.println("Total batsmen played: " + secondInnings.size());
		List<WebElement> headers1 = driver.findElements(By.xpath(""
				+ "//div[@id='gp-inning-00']//div[@class='wrap header']/div[@class='cell runs' or @class='cell batsmen']"));
		for(int i=0; i<headers1.size(); i++){
			System.out.print(headers1.get(i).getText() + "\t");
		}
		for (int i = 0; i < secondInnings.size(); i++) {
			System.out.println();
			getPlayerScroreCard(driver, secondInnings.get(i).getText());
		}
		
		driver.quit();
	}

	public static void getPlayerScroreCard(WebDriver driver, String playerName) {
		
		List<WebElement> playerScoreInfoList = driver.findElements(By.xpath(
				"//a[contains(text(),'" + playerName + "')]//parent::div//following-sibling::div[@class='cell runs']"));

		if (playerName.length() <= 8) {
			System.out.print(playerName + "\t" + "\t");
		} else {
			System.out.print(playerName + "\t");
		}

		for (int i = 0; i < playerScoreInfoList.size(); i++) {
			System.out.print(playerScoreInfoList.get(i).getText() + "\t");

		}

	}

}
