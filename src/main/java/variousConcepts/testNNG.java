package variousConcepts;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class testNNG {

	WebDriver driver;
	String browser;
	String url;
	
	By LOGIN_USERNAME_FIELD = By.xpath("//input[@id='username']");
	By LOGIN_PASSWORD_FIELD = By.xpath("//input[@id='password']");
	By LOGIN_BUTTON_FIELD = By.xpath("//button[@name='login']");
	By DASHBOARD_HEADER_FIELD = By.xpath("//h2[contains(text(), 'Dashboard')]");
	
	By CUSTOMER_MENU_FIELD = By.xpath("//span[contains(text(), 'Customers')]");
	By ADD_CUSTOMER_MENU_FIELD = By.xpath("//a[contains(text(), 'Add Customer')]");
	By CONTACTS_HEADER_FIELD = By.xpath("//h2[contains(text(), 'Contacts')]");
	
	By FULL_NAME_FIELD = By.xpath("//input[@id='account']");
	By COMPANY_DROP_DOWN_FIELD = By.xpath("//select[@id='cid']");
	By EMAIL_FIELD = By.xpath("//input[@id='email']");
	By PHONE_FIELD = By.xpath("//input[@id='phone']");
	By ADDRESS_FIELD = By.xpath("//input[@id='address']");
	By CITY_FIELD = By.xpath("//input[@id='city']");
	By STATE_FIELD = By.xpath("//input[@id='state']");
	By ZIP_CODE_FIELD = By.xpath("//input[@id='zip']");
	By COUNTRY_DROP_DOWN_FIELD = By.xpath("//select[@id='country']");
	By TAGS_DROP_DOWN_FIELD = By.xpath("//select[@id='tags']");
	By CURRENCY_DROP_DOWN_FIELD = By.xpath("//select[@id='currency']");
	By GROUP_DROP_DOWN_FIELD = By.xpath("//select[@id='group']");
	By PASSWORD_FIELD = By.xpath("//select[@id='group']");
	By CONFIRM_PASSWORD_FIELD = By.xpath("//select[@id='group']");
	By WELCOME_BUTTON_FIELD = By.xpath("//select[@id='group']");
	By SAVE_BUTTON_FIELD = By.xpath("//button[@id='submit']");
	
	
	String loginUserName = "demo@techfios.com";
	String loginPassword = "abc123";
	
	String dashboardHeader = "Dashboard";
	String contactHeader = "Contacts";
	
	String fullName = "Dont Ask";
	String company = "Apple";
	String email = "dontask@apple.com";
	String phone = "8888888888";
	String address = "1 Home st";
	String city = "green";
	String state = "Washington";
	String zipCode = "44444";
	String country = "Norway";
	String tags = "Annn";
	String currency = "USD";
	String group = "Java";
	String password = "123456";
	String confirmPassword = "123456";
	
	@BeforeClass
	public void readConfig() {	//4 classes java offers to read a file (InputStream, BufferedReader, Scanner, FileReader)
		try {
			InputStream input = new FileInputStream("src\\main\\java\\config\\config.properties");
			Properties prop = new Properties();
			prop.load(input);
			browser = prop.getProperty("browser");
			url = prop.getProperty("url");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@BeforeMethod
	public void init() {
		
		if (browser.equalsIgnoreCase("Chrome")) {
			System.setProperty("webdriver.chrome.driver", "Driver\\chromedriver.exe");
			driver = new ChromeDriver();
		}
		
		else if(browser.equalsIgnoreCase("Firefox")) {
			System.setProperty("webdriver.gecko.driver", "Driver\\geckodriver.exe");
			driver = new FirefoxDriver();
		}
		
		else {
			System.out.println(browser + " is not a valid browser.");
		}
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.get("https://www.techfios.com/billing/?ng=admin/");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
}
	//@Test
	public void loginTest(){ 
		driver.findElement(LOGIN_USERNAME_FIELD).sendKeys(loginUserName);
		driver.findElement(LOGIN_PASSWORD_FIELD).sendKeys(loginPassword);
		driver.findElement(LOGIN_BUTTON_FIELD).click();
		Assert.assertEquals(driver.findElement(DASHBOARD_HEADER_FIELD).getText(), dashboardHeader, "Dashboard is not available.");
		String headerText = driver.findElement(DASHBOARD_HEADER_FIELD).getText();
		System.out.println("Header Text: " + headerText);
		
	}
	
	@Test
	public void addCustomer() throws InterruptedException {
		loginTest();
		
		driver.findElement(CUSTOMER_MENU_FIELD).click();
		driver.findElement(ADD_CUSTOMER_MENU_FIELD).click();
		Thread.sleep(3000);
		
		boolean contactHeaderField = driver.findElement(CONTACTS_HEADER_FIELD).isDisplayed();
		Assert.assertTrue(contactHeaderField, "Contacts page is not available.");
		
		driver.findElement(FULL_NAME_FIELD).sendKeys(fullName + generateReandomNo(999));
		selectFromDropdown(COMPANY_DROP_DOWN_FIELD, company);
		driver.findElement(EMAIL_FIELD).sendKeys(generateReandomNo(999) + email);
		driver.findElement(PHONE_FIELD).sendKeys(generateReandomNo(999) + phone);
		driver.findElement(ADDRESS_FIELD).sendKeys(address);
		driver.findElement(CITY_FIELD).sendKeys(city);
		driver.findElement(STATE_FIELD).sendKeys(state);
		driver.findElement(ZIP_CODE_FIELD).sendKeys(zipCode);
		selectFromDropdown(COUNTRY_DROP_DOWN_FIELD, country);
		driver.findElement(TAGS_DROP_DOWN_FIELD).sendKeys(tags);
		selectFromDropdown(CURRENCY_DROP_DOWN_FIELD, currency);
		selectFromDropdown(GROUP_DROP_DOWN_FIELD, group);
		driver.findElement(PASSWORD_FIELD).sendKeys(password);
		driver.findElement(CONFIRM_PASSWORD_FIELD).sendKeys(confirmPassword);
		driver.findElement(WELCOME_BUTTON_FIELD).click();
		driver.findElement(SAVE_BUTTON_FIELD).click();
		
//		WebDriverWait wait = new WebDriverWait(driver, 60);
//	 	wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.name(" "))));

		generateReandomNo(999);
		
		webDriverWait("");
	}
	
	
	
	


	private void webDriverWait(String string) {
		
		
	}

	public void selectFromDropdown(By byLocator, String visibleText) {
		Select sel = new Select(driver.findElement(byLocator));
		sel.selectByVisibleText(visibleText);
		
	}

	private int generateReandomNo(int boundryNo) {
		Random rnd = new Random();
		int randomNumber = rnd.nextInt(boundryNo);
		return randomNumber;
		
	}

	//@AfterMethod
	public void tearDown() {
		driver.close();
		driver.quit();
		
	}
}
