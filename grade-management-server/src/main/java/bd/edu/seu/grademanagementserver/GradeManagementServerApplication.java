package bd.edu.seu.grademanagementserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableEurekaClient
@SpringBootApplication
public class GradeManagementServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(GradeManagementServerApplication.class, args);
    }

}
