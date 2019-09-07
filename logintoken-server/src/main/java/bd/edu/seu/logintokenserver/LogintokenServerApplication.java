package bd.edu.seu.logintokenserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Configuration;

@EnableEurekaClient
@Configuration
@SpringBootApplication
public class LogintokenServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(LogintokenServerApplication.class, args);
    }

}
