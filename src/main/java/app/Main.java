package app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;


@SpringBootApplication(scanBasePackages = "app")
@PropertySource("classpath:application.properties")
public class Main {

    public static void main(String[] args) {

        SpringApplication.run(Main.class,args);

        long startTime = System.currentTimeMillis();
        try {
            do {
                System.out.println("I'm here");
                Thread.sleep(10 * 1000);
            } while (System.currentTimeMillis() < startTime + 10 * 60 * 1000);
        } catch (InterruptedException e) {
            System.out.println("I'm stopped");
        }
        System.out.println("Time's up");
    }
}
