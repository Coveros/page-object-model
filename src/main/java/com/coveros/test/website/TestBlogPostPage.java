package com.coveros.test.website;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.coveros.test.selenium.pom.PageObjectFactory;

public class TestBlogPostPage {

	private CoverosHomePage homePage;
	private WebDriver driver;

	@BeforeClass
	public static void beforeClass() {
		String os = "mac";
		String geckodriver = "geckodriver";
		if (os.equals("windows")) {
			geckodriver += ".exe";
		}
		// System.setProperty("webdriver.gecko.driver",
		// "src/main/resources/geckodriver/" + os + "/" + geckodriver);
		System.setProperty("webdriver.chrome.driver",
				"src/main/resources/" + "chromedriver" + "/" + os + "/" + "chromedriver");
	}

	@Before
	public final void setUp() throws Exception {

		DesiredCapabilities capabilities = DesiredCapabilities.chrome();

		driver = new ChromeDriver(capabilities);
		driver.manage().deleteAllCookies();
		// driver.manage().window().setSize(new Dimension(375, 1000));

		PageObjectFactory factory = PageObjectFactory.newInstance(driver, "https://coveros.com");

		homePage = factory.newPage(CoverosHomePage.class);
		new WebDriverWait(driver, 10000).until(
				ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/header/div[2]/div/div/div[1]/h1")));
	}

	@Test
	public void testBlogPostJourney() {
		SecureCiProductPage productPage = homePage.clickSecureCi();
		BlogPostPage blogPostPage = productPage.clickOnBlogPost("Setting Up Selenified Using Maven");
		assertEquals("Setting Up Selenified Testing Framework Using Maven | Coveros", blogPostPage.getPageTitle());
		assertEquals("Setting Up Selenified Using Maven", blogPostPage.getBlogPostTitle());
	}

	@After
	public void shutDown() {
		driver.close();
	}

}