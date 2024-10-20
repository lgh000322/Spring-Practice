package hello.external;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.DefaultApplicationArguments;

import java.util.List;
import java.util.Set;

@Slf4j
public class CommandLineV2 {


    /**
     * 커맨트라인  옵션인수
     * --url=devdb --username=dev_user --password=dev_pw mode=on
     */
    public static void main(String[] args) {
        for (String arg : args) {
            log.info("arg = {}", arg);
        }

        ApplicationArguments applicationArguments = new DefaultApplicationArguments(args);
        log.info("SourceArgs = {}", List.of(applicationArguments.getSourceArgs()));
        log.info("NonOptionsArgs = {}", applicationArguments.getNonOptionArgs());
        log.info("OptionNames = {}", applicationArguments.getOptionNames());

        Set<String> optionNames = applicationArguments.getOptionNames();
        for (String optionName : optionNames) {
            log.info("option arg {}={}", optionName, applicationArguments.getOptionValues(optionName));
        }
        List<String> url = applicationArguments.getOptionValues("url");
        List<String> username = applicationArguments.getOptionValues("username");
        List<String> password = applicationArguments.getOptionValues("password");
        List<String> mode = applicationArguments.getOptionValues("mode");

        log.info("url = {}", url);
        log.info("username = {}", username);
        log.info("password = {}", password);
        log.info("mode = {}", mode);


    }
}
