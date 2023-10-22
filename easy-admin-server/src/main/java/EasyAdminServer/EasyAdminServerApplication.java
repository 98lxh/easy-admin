package EasyAdminServer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class EasyAdminServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(EasyAdminServerApplication.class, args);
    }

    @GetMapping("/")
    public String index(){
        return "ok";
    }
}
