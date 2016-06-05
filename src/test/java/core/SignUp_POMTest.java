package core;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;

public class SignUp_POMTest {
	
	SignUpPage signUpPage;
	ConfirmationPage confirmationPage;
	String expectedTitle = "Welcome to Sign Up v1";
	String expectedConfirmationTitle = "Confirmation";
	
	@Before
	public void setUp() {
		WebDriver driver = new FirefoxDriver();
		signUpPage = new SignUpPage(driver);
		confirmationPage = new ConfirmationPage(driver);
		signUpPage.loadPage();
		
		PageFactory.initElements(driver, signUpPage);
	}
	
	@Test
	public void testVerifyTitle() {
		Assert.assertEquals("Title is wrong!", true, signUpPage.verifyTitle(expectedTitle));
	}
	
	@Test
	public void testVerifyLinkFacebook() {
		Assert.assertEquals("No Facebook link", true, signUpPage.verifyLink("facebook"));
	}
	
	@Test
	public void testVerifyLinkTwitter() {
		Assert.assertEquals("No Twitter link", true, signUpPage.verifyLink("twitterrr"));
	}
	
	@Test
	public void testVerifyLinkFlickr() {
		Assert.assertEquals("No Flickr link", true, signUpPage.verifyLink("flickr"));
	}
	
	@Test
	public void testVerifyLinkYoutube() {
		Assert.assertEquals("No Youtube link", true, signUpPage.verifyLink("youtube"));
	}
	
	@Test
	public void testVerifyErrorHandlingFirstName() {
		Assert.assertEquals("No error message for First Name", true, signUpPage.verifyErrorHandling("firstName", "Please enter First Name"));
	}
	
	@Test
	public void testVerifyErrorHandlingLastName() {
		Assert.assertEquals("No error message for Last Name", true, signUpPage.verifyErrorHandling("lastName", "Please enter Last Name"));
	}
	
	@Test
	public void testVerifyErrorHandlingEmailAddress() {
		Assert.assertEquals("No error message for Email Address", true, signUpPage.verifyErrorHandling("emailAddress", "Please enter Email Address"));
	}
	
	@Test
	public void testVerifyErrorHandlingPhoneNumber() {
		Assert.assertEquals("No error message for Phone Number", true, signUpPage.verifyErrorHandling("phoneNumber", "Please enter Phone Number"));
	}
	
	@Test
	public void testVerifySubmitForm() {
		signUpPage.submitForm("Iryna", "Kaleniuk", "oseznotest@gmail.com", "111-222-3333", "Female", "CA", true);
		Assert.assertEquals("This is not a Confirmation page!", true, confirmationPage.verifyTitle(expectedConfirmationTitle));
	}
	
	@Test
	public void testVerifyContentQuotes() {
		Assert.assertEquals("Quote hasn't changed!", true, signUpPage.verifyContent("quotes", null));
	}
	
	@Test
	public void testVerifyContentCity() {
		Assert.assertEquals("City is wrong!", true, signUpPage.verifyContent("currentCity", "San Francisco, CA"));
	}
	
	@Test
	public void testVerifyContentWeather() {
		Assert.assertEquals("Weather is wrong!", true, signUpPage.verifyContent("currentWeather", "/partlycloudy.gif"));
	}
	
	@Test
	public void testVerifyContentDate() {
		String realDate = LocalDate.now().format(DateTimeFormatter.ofPattern("MMMM d, yyyy"));
		Assert.assertEquals("The date is wrong!", true, signUpPage.verifyContent("currentDate", realDate));
	}
	
	@Test
	public void testVerifyContentOs() {
		Assert.assertEquals("OS is wrong!", true, signUpPage.verifyContent("currentOs", "OS X 10.10 Yosemite"));
	}
	
	@Test
	public void testVerifyContentBrowser() {
		Assert.assertEquals("Browser is wrong!", true, signUpPage.verifyContent("currentOs", "Firefox"));
	}
	
	@After
	public void tearDown() {
		signUpPage.closeBrowser();
	}

}
