package hello.servlet.web.MyCrawler.crawler;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;

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

        WebElement htmlElement = driver.findElement(By.tagName("html"));
        List<WebElement> allElements = htmlElement.findElements(By.xpath("//*"));

        // 모든 요소를 순회하면서 출력합니다.
        for (WebElement element : allElements) {
            System.out.println(element.getTagName() + ": " + element.getText());
        }
        // 태그명으로 요소 찾기

        try {
            WebElement h1Element = driver.findElement(By.tagName("h1"));
            System.out.println("h1 태그의 텍스트: " + h1Element.getText());
        } catch (NoSuchElementException e) {
            System.out.println("MyCrawler.getCrawlContent_E1");
        }

        // 클래스명으로 요소 찾기
        try {
            WebElement exampleElement = driver.findElement(By.className("example"));
            System.out.println(".example 클래스를 가진 요소의 텍스트: " + exampleElement.getText());
        } catch (NoSuchElementException e) {
            System.out.println("MyCrawler.getCrawlContent_E2");
        }

        // 네임명으로 요소 찾기
        try {
            WebElement inputElement = driver.findElement(By.name("username"));
            System.out.println("name 속성이 'username'인 요소의 태그명: " + inputElement.getTagName());
        } catch (NoSuchElementException e) {
            System.out.println("MyCrawler.getCrawlContent_E3");
        }


        // 페이지 제목 출력
        System.out.println("Page title: " + driver.getTitle());

        // WebDriver 종료
        driver.quit();

        return "members";
    }
}
