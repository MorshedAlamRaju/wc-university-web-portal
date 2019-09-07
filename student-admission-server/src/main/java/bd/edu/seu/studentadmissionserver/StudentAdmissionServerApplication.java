package bd.edu.seu.studentadmissionserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Configuration;

@EnableEurekaClient
@SpringBootApplication
@Configuration
public class StudentAdmissionServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(StudentAdmissionServerApplication.class, args);
    }

}
