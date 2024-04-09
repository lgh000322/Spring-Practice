package hello.servlet.web.MyCrawler.crawler;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.Duration;

@Controller
public class MyCrawler {
    @GetMapping("/Crawl")
    public String getCrawlContent() {
        // 크롬 드라이버 설정
        System.setProperty("webdriver.chrome.driver", "C:/Users/pc/Desktop/Spring Practice/스프링 MVC 1편/servlet/servlet/chromedriver.exe"); // 윈도우

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--headless");
        chromeOptions.addArguments("--lang=ko");
        chromeOptions.addArguments("--no-sandbox");
        chromeOptions.addArguments("--disable-dev-shm-usage");
        chromeOptions.addArguments("--disable-gpu");

        WebDriver driver = new ChromeDriver(chromeOptions);
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));

        // Google 홈페이지 열기
        driver.get("https://www.google.com");

        // 페이지 제목 출력
        System.out.println("Page title: " + driver.getTitle());

        // WebDriver 종료
        driver.quit();

        return "members";
    }
}
