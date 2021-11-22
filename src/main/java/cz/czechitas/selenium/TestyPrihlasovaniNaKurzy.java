package cz.czechitas.selenium;

import com.google.errorprone.annotations.Var;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class TestyPrihlasovaniNaKurzy {

    public static final String WEB_URL = "https://cz-test-jedna.herokuapp.com/";
    public static final String KURZ_PROGRAMOVANI = "(//div[@class = 'card'] )[1]//a[text()='Více informací']";
    public static final String KURZ_TESTOVANI = "( //div[@class = 'card'] )[3]//a[text()='Více informací']";
    public static final String KURZ_WEBU = "( //div[@class = 'card'] )[2]//a[text()='Více informací']";
    WebDriver prohlizec;

    @BeforeEach
    public void setUp() {
        System.setProperty("webdriver.gecko.driver", "C:\\Java-Training\\Selenium\\geckodriver.exe");
        prohlizec = new FirefoxDriver();
        prohlizec.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Test
    @DisplayName("Case1")
    public void rodicSExistujicimUctemSeMusiBytSchopenPrihlasitDoWeboveAplikace() {
        prohlizec.navigate().to(WEB_URL + "prihlaseni");
        vyplnUsername("laihgd@post.cz");
        vyplnPassword("TakNejak1");
        klikniNaPrihlasit();
        pockejNaPrihlaseni();
    }

    @Test
    @DisplayName("Case2")
    public void rodicMusiBytSchopenVybratKurzPrihlasitSeDoAplikaceAPrihlasitNaKurzSvojeDite() {
        prohlizec.navigate().to(WEB_URL);

        vyberKurz(KURZ_TESTOVANI);
        vyplnUsername("laihgd@post.cz");
        vyplnPassword("TakNejak1");
        klikniNaPrihlasit();
        vyberTermin();
        vyplnJmeno("Marsal");
        vyplnPrijmeni("Rokky");
        vyplDatumNarozenin("1.1.2001");
        vyberPlatbu();
        souhlasSPodminkami();
        vytvorPrihlasku();
        overPrihlaseni();
    }

    @Test
    @DisplayName("Case3")
    public void RodicSeMusBytSchopenPrihlasitDoAplikaceVyhledatKurzAPrihlasitNaNejSvojeDite() {
        prohlizec.navigate().to(WEB_URL);

        prihlasit();
        vyplnUsername("laihgd@post.cz");
        vyplnPassword("TakNejak1");
        klikniNaPrihlasit();
        vyhledejKurz();
        vyberKurz(KURZ_PROGRAMOVANI);
        vyberTermin();
        vyplnJmeno("Vitecek");
        vyplnPrijmeni("Vitamvas");
        vyplDatumNarozenin("15.1.1985");
        vyberPlatbu();
        souhlasSPodminkami();
        vytvorPrihlasku();
        overPrihlaseni();
    }

    @Test
    @DisplayName("Case4")

    public void RodicSeMusBytSchopenPrihlasitDoAplikaceVyhledatKurzAPrihlasitNaNejSvojeDiteAOdhlasitSe() {
        prohlizec.navigate().to(WEB_URL);

        prihlasit();
        vyplnUsername("laihgd@post.cz");
        vyplnPassword("TakNejak1");
        klikniNaPrihlasit();
        vytvorNovouPrihlasku();
        vyberKurz(KURZ_WEBU);
        vyberTermin();
        vyplnJmeno("Tomas");
        vyplnPrijmeni("Tomas");
        vyplDatumNarozenin("1.1.2001");
        vyberPlatbu();
        souhlasSPodminkami();
        vytvorPrihlasku();
        overPrihlaseni();
        logOut();

    }

    public void vyplnInputBox(String xPath, String hodnota) {
        WebElement vyplnInputBox = prohlizec.findElement(By.id(xPath));
        vyplnInputBox.sendKeys(hodnota);
    }

    public void vyplnUsername(String userName) {
        vyplnInputBox("email", userName);
    }

    public void vyplnPassword(String password) {
        vyplnInputBox("password", password);
    }

    public void vyplnJmeno(String jmenoZaka) {
        vyplnInputBox("forename", jmenoZaka);
    }

    public void vyplnPrijmeni(String prijmeniZaka) {
        vyplnInputBox("surname", prijmeniZaka);
    }

    public void vyplDatumNarozenin(String datumNarozenin) {
        vyplnInputBox("birthday", datumNarozenin);
    }

    public void klikniNaPrihlasit() {
        WebElement tlacitkoPrihlasit = prohlizec.findElement(By.xpath("//div[3]/div/button"));
        tlacitkoPrihlasit.click();
    }

    public WebElement pockejNaPrihlaseni() {
        return prohlizec.findElement(By.xpath("//h1[text() = '  Přihlášky  ']"));
    }

    public void vyberKurz(String xPath) {
        WebElement tlacitkoViceInfo = prohlizec.findElement(By.xpath(xPath));
        tlacitkoViceInfo.click();

        WebElement tlacitkoVytvoritPrihlasku = prohlizec.findElement(By.xpath("//div[2]/div/div/div[2]/a"));
        tlacitkoVytvoritPrihlasku.click();
    }

    public void vyberTermin() {
        rozbalTermin();
        vyplnTermin();
    }

    public void rozbalTermin() {
        WebElement rozbalTermin = prohlizec.findElement(By.xpath("//div [text() = 'Vyberte termín...']"));
        rozbalTermin.click();
    }

    public void vyplnTermin() {
        WebElement vyplnTermin = prohlizec.findElement(By.id("bs-select-1-0"));
        vyplnTermin.click();
    }

    public void vyberPlatbu() {
        WebElement vyberPlatbu = prohlizec.findElement(By.xpath("//tr[8]/td[2]/span[1]/label"));
        vyberPlatbu.click();
    }

    public void souhlasSPodminkami() {
        WebElement vyberPlatbu = prohlizec.findElement(By.xpath("//tr[11]/td[2]/span/label"));
        vyberPlatbu.click();
    }

    public void vytvorPrihlasku() {
        WebElement vyberPlatbu = prohlizec.findElement(By.xpath("//tr[11]/td[2]/input"));
        vyberPlatbu.click();
    }

    public void prihlasit() {
        WebElement prihlasit = prohlizec.findElement(By.xpath("//*[@id='navbarSupportedContent']/div[2]/a"));
        prihlasit.click();
    }

    public void vyhledejKurz() {
        WebElement vyhledejKurz = prohlizec.findElement(By.xpath("//*[@id='navbarSupportedContent']/div[1]/div/a"));
        vyhledejKurz.click();
        WebElement zmackniVytvoritPrihlasku = prohlizec.findElement(By.xpath("//*[@id='navbarSupportedContent']/div[1]/div/div/a[2]"));
        zmackniVytvoritPrihlasku.click();
    }

    public void vytvorNovouPrihlasku() {
        WebElement vytvorNovouPrihlasku = prohlizec.findElement(By.xpath("//div[1]/a"));
        vytvorNovouPrihlasku.click();
    }

    public void logOut() {
        WebElement dropDown = prohlizec.findElement(By.xpath("//*[@id='navbarSupportedContent']/div[2]/div/a"));
        dropDown.click();
        WebElement logOut = prohlizec.findElement(By.id("logout-link"));
        logOut.click();
    }

    public void overPrihlaseni() {
        prohlizec.navigate().to(WEB_URL + "zaci");

        List<WebElement> bunkyKategorie = prohlizec.findElements(By.xpath("//table[@id = 'DataTables_Table_0']//td[2]"));
        int nalezenyRadek = 0;
        for (int i = 0; i < bunkyKategorie.size(); i++) {
            WebElement bunka = bunkyKategorie.get(i);
            if (bunka.getText().equals("JavaScript")) {
                nalezenyRadek = i;
            }
        }
        Assertions.assertTrue(nalezenyRadek > 0);
    }

    @AfterEach
    public void tearDown() {
        prohlizec.quit();
    }
}
