package bd.edu.seu.academicserver;

import bd.edu.seu.academicserver.repository.ProgramRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@Configuration
@EnableEurekaClient
@RestController(value = "")
public class AcademicServerApplication {


    @GetMapping(value = "")
    public ResponseEntity<String> Hello(){
        String message = "Hello! Go to api/v1/programs to visit api";
        return ResponseEntity.ok().body(message);
    }

    public static void main(String[] args) {
        SpringApplication.run(AcademicServerApplication.class, args);
    }

}
