import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.StringContains.containsString;

public class EndToEndTest {

    private WebDriver driver;
    private ApplicationServer server = new ApplicationServer(4567, new MyApplication());

    @Before
    public void setUp() throws Exception {
        server.start();
        WebDriverManager.chromedriver().version("74").setup();
        driver = new ChromeDriver();
    }

    @After
    public void tearDown() throws Exception {
        driver.close();
        server.stop();
    }

    @Test
    public void happyPath() {
        driver.get("http://localhost:4567/list");

        assertThat(driver.getPageSource(), containsString("empty"));
    }
}
