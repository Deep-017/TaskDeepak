package TestScripts;

import Initialization.BaseClass;
import PageObjects.App_Page;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;
import java.text.ParseException;

public class App_Navigate extends BaseClass{

	
	@Test
	public void Admin1() throws InterruptedException, ParseException, FileNotFoundException {
		App_Page ap = PageFactory.initElements( driver , App_Page.class);
		ap.appData_TopFree();
		ap.appData_TopPaid();
		ap.appData_TopGrossing();
		ap.appData_TopFreeGames();
		ap.appData_TopPaidGames();
		ap.appData_TopGrossingGames();
	}
}
