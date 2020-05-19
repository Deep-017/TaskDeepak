package PageObjects;

import CommonParts.Path;
import Initialization.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class App_Page extends BaseClass {

    int count = 0;
    int tempNum = 0;
    static int index = 1;
    int score = 0;

    @FindBy(xpath = "(//span[@class='AYi5wd TBRnV']/span)[1]")
    private static WebElement rating_App;

    @FindBy(xpath = "(//span[@class='htlgb'])[2]")
    private static WebElement dateUpdated;

    @FindBy(xpath = "(//span[@class='htlgb'])[4]")
    private static WebElement dateUpdated2;

    @FindBy(xpath = "(//a[@class='LkLjZd ScJHi U8Ww7d xjAeve nMZKrb  id-track-click '])[1]")
    private static WebElement btMore1;
    @FindBy(xpath = "(//a[@class='LkLjZd ScJHi U8Ww7d xjAeve nMZKrb  id-track-click '])[2]")
    private static WebElement btMore2;
    @FindBy(xpath = "(//a[@class='LkLjZd ScJHi U8Ww7d xjAeve nMZKrb  id-track-click '])[3]")
    private static WebElement btMore3;
    @FindBy(xpath = "(//a[@class='LkLjZd ScJHi U8Ww7d xjAeve nMZKrb  id-track-click '])[4]")
    private static WebElement btMore4;
    @FindBy(xpath = "(//a[@class='LkLjZd ScJHi U8Ww7d xjAeve nMZKrb  id-track-click '])[5]")
    private static WebElement btMore5;
    @FindBy(xpath = "(//a[@class='LkLjZd ScJHi U8Ww7d xjAeve nMZKrb  id-track-click '])[6]")
    private static WebElement btMore6;

    @FindBy(xpath = "(//div[@jsname='O2DNWb'])[7]/*//*[@class='WsMG1c nnK0zc']")
    private static List<WebElement> appList1;
    @FindBy(xpath = "(//div[@jsname='O2DNWb'])[7]/*//*[@class='WsMG1c nnK0zc']")
    private static List<WebElement> appList2;
    @FindBy(xpath = "(//div[@jsname='O2DNWb'])[7]/*//*[@class='WsMG1c nnK0zc']")
    private static List<WebElement> appList3;
    @FindBy(xpath = "(//div[@jsname='O2DNWb'])[7]/*//*[@class='WsMG1c nnK0zc']")
    private static List<WebElement> appList4;
    @FindBy(xpath = "(//div[@jsname='O2DNWb'])[7]/*//*[@class='WsMG1c nnK0zc']")
    private static List<WebElement> appList5;
    @FindBy(xpath = "(//div[@jsname='O2DNWb'])[7]/*//*[@class='WsMG1c nnK0zc']")
    private static List<WebElement> appList6;

    @FindBy(xpath = "//h1[@itemprop='name']/span")
    private static WebElement appName;

    public void getFullList() throws InterruptedException {
        Thread.sleep(3000);
        try {
            Object lastHeight = ((JavascriptExecutor) driver).executeScript("return document.body.scrollHeight");

            while (true) {
                ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight);");
                Thread.sleep(2000);

                Object newHeight = ((JavascriptExecutor) driver).executeScript("return document.body.scrollHeight");
                if (newHeight.equals(lastHeight)) {
                    break;
                }
                lastHeight = newHeight;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void calculateAll() throws ParseException, FileNotFoundException {
        ArrayList<String> tabs2 = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs2.get(1));

        String ratings = rating_App.getText();
        String ratigsNew = ratings.replace(",", "");
        int rating = Integer.parseInt(ratigsNew);
        String dd = dateUpdated.getText();

        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        dateFormat.format(date);

        SimpleDateFormat formatter4 = new SimpleDateFormat("MMM dd, yyyy");
        Date date4 = formatter4.parse(dd);
        dateFormat.format(date4);

        long difference = date.getTime() - date4.getTime();
        float daysBetween = (difference / (1000 * 60 * 60 * 24));
        int days = (int) daysBetween;
        if (days == 0) {
            score = rating;
        } else {
            score = rating / days;
        }
        System.out.println("App Name: " + appName.getText() + " | Ratings: " + rating + " | Last Updated on: " + dateFormat.format(date4) + " | Score: " + score);
        driver.close();
        driver.switchTo().window(tabs2.get(0));
    }

    public void appData_TopFree() throws InterruptedException, ParseException, FileNotFoundException {
        PrintStream o = new PrintStream(new File(Path.tfurl));
        System.setOut(o);
        btMore1.click();
        getFullList();
        for (WebElement we : appList1) {
            count++;
        }
        System.out.println("Count of Top Free: " + count);
        while (tempNum != count) {
            Actions actions = new Actions(driver);
            actions.keyDown(Keys.LEFT_CONTROL)
                    .moveToElement(driver.findElement(By.xpath("((//div[@jsname='O2DNWb'])[7]/*//*[@class='WsMG1c nnK0zc'])[" + index + "]")))
                    .click()
                    .keyUp(Keys.LEFT_CONTROL)
                    .build()
                    .perform();
            calculateAll();
            tempNum++;
            index = index + 1;
        }
    }

    public void appData_TopPaid() throws ParseException, InterruptedException, FileNotFoundException {
        PrintStream o = new PrintStream(new File(Path.tpurl));
        System.setOut(o);
        driver.navigate().to(Path.urlToNavigate);
        btMore2.click();
        getFullList();
        index = 1;
        tempNum = 0;
        count = 0;

        for (WebElement we : appList2) {
            count++;
        }
        System.out.println("Count of Top Paid: " + count);
        while (tempNum != count) {
            Actions actions = new Actions(driver);
            actions.keyDown(Keys.LEFT_CONTROL)
                    .moveToElement(driver.findElement(By.xpath("((//div[@jsname='O2DNWb'])[7]/*//*[@class='WsMG1c nnK0zc'])[" + index + "]")))
                    .click()
                    .keyUp(Keys.LEFT_CONTROL)
                    .build()
                    .perform();
            ArrayList<String> tabs2 = new ArrayList<String>(driver.getWindowHandles());
            driver.switchTo().window(tabs2.get(1));

            String ratings = rating_App.getText();
            String ratigsNew = ratings.replace(",", "");
            int rating = Integer.parseInt(ratigsNew);
            String dd = dateUpdated2.getText();

            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            Date date = new Date();
            dateFormat.format(date);

            SimpleDateFormat formatter4 = new SimpleDateFormat("MMM dd, yyyy");
            Date date4 = formatter4.parse(dd);
            dateFormat.format(date4);

            long difference = date.getTime() - date4.getTime();
            float daysBetween = (difference / (1000 * 60 * 60 * 24));
            int days = (int) daysBetween;
            if (days == 0) {
                System.out.println("Days is zero, hence score = rating");
                score = rating;
            } else {
                score = rating / days;
            }
            System.out.println("App Name: " + appName.getText() + " | Ratings: " + rating + " | Last Updated on: " + dateFormat.format(date4) + " | Score: " + score);
            driver.close();
            driver.switchTo().window(tabs2.get(0));
            tempNum++;
            index = index + 1;
        }
    }


    public void appData_TopGrossing() throws ParseException, InterruptedException, FileNotFoundException {
        PrintStream o = new PrintStream(new File(Path.tgurl));
        System.setOut(o);
        driver.navigate().to(Path.urlToNavigate);
        btMore3.click();
        getFullList();
        index = 1;
        tempNum = 0;
        count = 0;

        for (WebElement we : appList3) {
            count++;
        }
        System.out.println("Count of Top Grossing: " + count);
        while (tempNum != count) {
            Actions actions = new Actions(driver);
            actions.keyDown(Keys.LEFT_CONTROL)
                    .moveToElement(driver.findElement(By.xpath("((//div[@jsname='O2DNWb'])[7]/*//*[@class='WsMG1c nnK0zc'])[" + index + "]")))
                    .click()
                    .keyUp(Keys.LEFT_CONTROL)
                    .build()
                    .perform();
            ArrayList<String> tabs2 = new ArrayList<String>(driver.getWindowHandles());
            driver.switchTo().window(tabs2.get(1));

            String ratings = rating_App.getText();
            String ratigsNew = ratings.replace(",", "");
            int rating = Integer.parseInt(ratigsNew);
            String dd = dateUpdated.getText();

            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            Date date = new Date();
            dateFormat.format(date);

            SimpleDateFormat formatter4 = new SimpleDateFormat("MMM dd, yyyy");
            Date date4 = formatter4.parse(dd);
            dateFormat.format(date4);

            long difference = date.getTime() - date4.getTime();
            float daysBetween = (difference / (1000 * 60 * 60 * 24));
            int days = (int) daysBetween;
            if (days == 0) {
                System.out.println("Days is zero, hence score = rating");
                score = rating;
            } else {
                score = rating / days;
            }
            System.out.println("App Name: " + appName.getText() + " | Ratings: " + rating + " | Last Updated on: " + dateFormat.format(date4) + " | Score: " + score);
            driver.close();
            driver.switchTo().window(tabs2.get(0));
            tempNum++;
            index = index + 1;
        }
    }

    public void appData_TopFreeGames() throws ParseException, InterruptedException, FileNotFoundException {
        PrintStream o = new PrintStream(new File(Path.tfgurl));
        System.setOut(o);
        driver.navigate().to(Path.urlToNavigate);
        btMore4.click();
        getFullList();
        index = 1;
        tempNum = 0;
        count = 0;

        for (WebElement we : appList4) {
            count++;
        }
        System.out.println("Count of Top Free Games: " + count);
        while (tempNum != count) {
            Actions actions = new Actions(driver);
            actions.keyDown(Keys.LEFT_CONTROL)
                    .moveToElement(driver.findElement(By.xpath("((//div[@jsname='O2DNWb'])[7]/*//*[@class='WsMG1c nnK0zc'])[" + index + "]")))
                    .click()
                    .keyUp(Keys.LEFT_CONTROL)
                    .build()
                    .perform();
            ArrayList<String> tabs2 = new ArrayList<String>(driver.getWindowHandles());
            driver.switchTo().window(tabs2.get(1));

            String ratings = rating_App.getText();
            String ratigsNew = ratings.replace(",", "");
            int rating = Integer.parseInt(ratigsNew);
            String dd = dateUpdated.getText();

            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            Date date = new Date();
            dateFormat.format(date);

            SimpleDateFormat formatter4 = new SimpleDateFormat("MMM dd, yyyy");
            Date date4 = formatter4.parse(dd);
            dateFormat.format(date4);

            long difference = date.getTime() - date4.getTime();
            float daysBetween = (difference / (1000 * 60 * 60 * 24));
            int days = (int) daysBetween;
            if (days == 0) {
                System.out.println("Days is zero, hence score = rating");
                score = rating;
            } else {
                score = rating / days;
            }
            System.out.println("App Name: " + appName.getText() + " | Ratings: " + rating + " | Last Updated on: " + dateFormat.format(date4) + " | Score: " + score);
            driver.close();
            driver.switchTo().window(tabs2.get(0));
            tempNum++;
            index = index + 1;
        }
    }

    public void appData_TopPaidGames() throws ParseException, InterruptedException, FileNotFoundException {
        PrintStream o = new PrintStream(new File(Path.tpgurl));
        System.setOut(o);
        driver.navigate().to(Path.urlToNavigate);
        btMore5.click();
        getFullList();
        index = 1;
        tempNum = 0;
        count = 0;

        for (WebElement we : appList4) {
            count++;
        }
        System.out.println("Count of Top Paid Games: " + count);
        while (tempNum != count) {
            Actions actions = new Actions(driver);
            actions.keyDown(Keys.LEFT_CONTROL)
                    .moveToElement(driver.findElement(By.xpath("((//div[@jsname='O2DNWb'])[7]/*//*[@class='WsMG1c nnK0zc'])[" + index + "]")))
                    .click()
                    .keyUp(Keys.LEFT_CONTROL)
                    .build()
                    .perform();
            ArrayList<String> tabs2 = new ArrayList<String>(driver.getWindowHandles());
            driver.switchTo().window(tabs2.get(1));

            String ratings = rating_App.getText();
            String ratigsNew = ratings.replace(",", "");
            int rating = Integer.parseInt(ratigsNew);
            String dd = dateUpdated2.getText();

            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            Date date = new Date();
            dateFormat.format(date);

            SimpleDateFormat formatter4 = new SimpleDateFormat("MMM dd, yyyy");
            Date date4 = formatter4.parse(dd);
            dateFormat.format(date4);

            long difference = date.getTime() - date4.getTime();
            float daysBetween = (difference / (1000 * 60 * 60 * 24));
            int days = (int) daysBetween;
            if (days == 0) {
                System.out.println("Days is zero, hence score = rating");
                score = rating;
            } else {
                score = rating / days;
            }
            System.out.println("App Name: " + appName.getText() + " | Ratings: " + rating + " | Last Updated on: " + dateFormat.format(date4) + " | Score: " + score);
            driver.close();
            driver.switchTo().window(tabs2.get(0));
            tempNum++;
            index = index + 1;
        }
    }

    public void appData_TopGrossingGames() throws ParseException, InterruptedException, FileNotFoundException {
        PrintStream o = new PrintStream(new File(Path.tggurl));
        System.setOut(o);
        driver.navigate().to(Path.urlToNavigate);
        btMore6.click();
        getFullList();
        index = 1;
        tempNum = 0;
        count = 0;

        for (WebElement we : appList4) {
            count++;
        }
        System.out.println("Count of Top Grossing Games: " + count);
        while (tempNum != count) {
            Actions actions = new Actions(driver);
            actions.keyDown(Keys.LEFT_CONTROL)
                    .moveToElement(driver.findElement(By.xpath("((//div[@jsname='O2DNWb'])[7]/*//*[@class='WsMG1c nnK0zc'])[" + index + "]")))
                    .click()
                    .keyUp(Keys.LEFT_CONTROL)
                    .build()
                    .perform();
            ArrayList<String> tabs2 = new ArrayList<String>(driver.getWindowHandles());
            driver.switchTo().window(tabs2.get(1));

            String ratings = rating_App.getText();
            String ratigsNew = ratings.replace(",", "");
            int rating = Integer.parseInt(ratigsNew);
            String dd = dateUpdated.getText();

            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            Date date = new Date();
            dateFormat.format(date);

            SimpleDateFormat formatter4 = new SimpleDateFormat("MMM dd, yyyy");
            Date date4 = formatter4.parse(dd);
            dateFormat.format(date4);

            long difference = date.getTime() - date4.getTime();
            float daysBetween = (difference / (1000 * 60 * 60 * 24));
            int days = (int) daysBetween;
            if (days == 0) {
                System.out.println("Days is zero, hence score = rating");
                score = rating;
            } else {
                score = rating / days;
            }
            System.out.println("App Name: " + appName.getText() + " | Ratings: " + rating + " | Last Updated on: " + dateFormat.format(date4) + " | Score: " + score);
            driver.close();
            driver.switchTo().window(tabs2.get(0));
            tempNum++;
            index = index + 1;
        }
    }
}
