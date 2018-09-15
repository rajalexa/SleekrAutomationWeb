package sleekrPackage

import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import org.openqa.selenium.By
import org.openqa.selenium.Keys as Keys
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import java.text.DateFormatSymbols;

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUiBuiltInKeywords
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable

public class TestSteps {
	/**
	 * Custom Login, supporting for execute more than 1 test case. Will retry 3 times if login failed
	 */
	@Keyword
	def customLogin(def UserName, def Password, boolean continuou = GlobalVariable.Continuou) {
		if (GlobalVariable.RunAsTestSuite){
			if(!continuou)
				testSuiteContinuou(UserName, Password)
		}
		else {
			if(GlobalVariable.TryLogin < 2){
				testSuiteContinuou(UserName, Password)
				GlobalVariable.TryLogin += 1
			}
			else if(GlobalVariable.TryLogin > 1){
				if(!continuou)
					testSuiteContinuou(UserName, Password)
			}
		}
	}

	/**
	 * Custom Login support function to check login is success and will retry login 3 times if failed
	 */
	def testSuiteContinuou(def UserName, def Password){
		int retries = 0
		boolean succeed = false
		boolean result = false

		try{
			Login(UserName, Password)
			WebUI.verifyElementPresent(findTestObject('OrganisasiPage/organisasi_list_desktop'), 10)
		} catch(Exception e){
			while (!result && retries <=2){
				retries +=1
				Login(UserName, Password)
				KeywordUtil.logInfo("Retry to login " + retries)
				result = WebUI.verifyElementPresent(findTestObject('OrganisasiPage/organisasi_list_desktop'), 10, FailureHandling.OPTIONAL)
			}
		}
	}

	/**
	 * Raw Login
	 */
	def Login(def UserName, def Password){
		customSetText(findTestObject('LoginPage/login_input_email'), UserName)
		customSetText(findTestObject('LoginPage/login_input_password'), Password)
		WebUI.click(findTestObject('LoginPage/login_submit_button'))
		KeywordUtil.logInfo("Try to login")
	}

	/**
	 * User set text and check success to fill the field
	 */
	@Keyword
	def customSetText(TestObject to,String text){
		WebUI.sendKeys(to, Keys.chord(Keys.CONTROL, 'a'))
		WebUI.sendKeys(to, Keys.chord(Keys.DELETE))
		WebUI.setText(to, text)
		WebUI.delay(1)
		WebUI.verifyMatch(WebUI.getAttribute(to, "value"), text, false, FailureHandling.OPTIONAL)
	}

	/**
	 * Custom Click
	 */
	@Keyword
	def customClick(def Element) {
		try{
			WebUI.waitForPageLoad(0)
			WebUI.waitForAngularLoad(0)
			WebUI.waitForJQueryLoad(0)
			WebUI.waitForElementPresent(Element, 0)
			WebUI.waitForImagePresent(Element, 0, FailureHandling.OPTIONAL)
			WebUI.waitForElementVisible(Element, 0)
			WebUI.waitForElementClickable(Element, 0)
			WebUI.verifyElementClickable(Element, FailureHandling.OPTIONAL)
			WebUI.delay(1)
			WebUI.click(Element)
		} catch(Exception e){
		}
	}

	/**
	 * Select custom date
	 */
	@Keyword
	def selectDateWithValue(TestObject to, String value) {
		WebDriver driver = DriverFactory.getWebDriver()
		WebUI.scrollToElement(to, 0)
		WebUI.delay(1)
		WebUI.waitForElementClickable(to, 0)
		customClick(to)
		Map eDate = new HashMap()
		eDate.put("year", new Integer(value.split("[\\s+-/]")[2]))
		eDate.put("month", new Integer(value.split("[\\s+-/]")[1]))
		eDate.put("day", new Integer(value.split("[\\s+-/]")[0]))
		String sCalendarMonth
		String sEditorXpath = to.selectorCollection.entrySet()
		String cXpath = sEditorXpath.substring(sEditorXpath.indexOf("/")).split("]")[0] + "]"
		String cDate = driver.findElement(By.xpath(cXpath + "/..//input[@data-ref='inputEl']")).getAttribute("value")
		Map nDate = new HashMap()
		nDate.put("year", new Integer(cDate.split("-")[2]))
		nDate.put("month", new Integer(cDate.split("-")[1]))
		nDate.put("day", new Integer(cDate.split("-")[0]))

		int cMonth = ((eDate["year"] - nDate["year"]) * 12) + (eDate["month"] - nDate["month"])
		WebElement element

		if (cMonth < 0)
			element = driver.findElement(By.xpath("//*[@data-ref='prevEl']"))
		else if (cMonth > 0)
			element = driver.findElement(By.xpath("//*[@data-ref='nextEl']"))

		for(int i = 0 ; i < Math.abs(cMonth) ; i++){
			element.click()
		}

		try {
			int num = new Integer(eDate["month"]);

			if (num != null){
				sCalendarMonth = getMonth(num)
			}
		} catch(Exception ex) {
			println "month is not a number"
			sCalendarMonth = eDate["month"]
		}

		element = driver.findElement(By.xpath("//*[@class = 'x-datepicker-inner x-focus x-datepicker-focus x-datepicker-default-focus']//*[@aria-label = '"+sCalendarMonth+" "+eDate["day"]+"']"))
		WebUI.delay(1)
		element.click()
		WebUI.delay(1)
	}

	/**
	 * User set random value
	 */
	@Keyword
	def setRandom(String rand, double min, double max) {
		int range = (max - min) + 1;
		int random = ((int)(Math.random() * range)) + min

		switch(rand.toLowerCase()){
			case "int": return random
				break
			case "string": return random.toString()
				break
			default: "Type not found"
				break
		}
	}

	public String getMonth(int month) {
		return new DateFormatSymbols().getMonths()[month-1];
	}

	/**
	 * Select dropdown element
	 */
	@Keyword
	def selectDropdownWithValue(TestObject to, String value) {
		WebDriver driver = DriverFactory.getWebDriver()
		WebUI.scrollToElement(to, 0)
		WebUI.delay(3)
		WebUI.waitForElementClickable(to, 0)
		WebUI.click(to)
		String sEditorXpath = to.selectorCollection.entrySet()
		String cXpath = sEditorXpath.substring(sEditorXpath.indexOf("/")).split("]")[0] + "]"
		//		driver.findElement(By.xpath(cXpath)).sendKeys(Keys.ARROW_DOWN)
		WebElement element = driver.findElement(By.xpath("//*[text()='"+value+"']"))
		boolean clickable = isClickable(element, driver)
		boolean time = timeout(30000)

		//		if(WebUI.verifyElementPresent(findTestObject('JeniusContactsPage/jenius_contacts_input_search'), 2, FailureHandling.OPTIONAL)){
		//			WebUI.setText(findTestObject('JeniusContactsPage/jenius_contacts_input_search'), value)
		//			WebUI.delay(3)
		//		}

		while(!clickable){
			if(time){
				driver.findElement(By.xpath(cXpath)).sendKeys(Keys.ARROW_DOWN)
				clickable = isClickable(element, driver)
			} else
				break
		}

		WebUI.delay(1)
		element.click()
		WebUI.delay(1)
	}

	/**
	 * Timeout
	 */
	public timeout(int timeout) {
		long startTime = System.currentTimeMillis()
		long endTime = startTime + timeout

		if (startTime < endTime)
			return true
		else
			return false
	}

	public boolean isClickable(WebElement el, WebDriver driver) {
		try{
			WebDriverWait wait = new WebDriverWait(driver, 10)
			//			wait.until(ExpectedConditions.visibilityOf(el))
			wait.until(ExpectedConditions.elementToBeClickable(el))
			return true;
		}
		catch (Exception e){
			e.printStackTrace()
			return false;
		}
	}
}
