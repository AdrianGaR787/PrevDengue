package pe.edu.upc.prevdengue;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class PrevDengueApplication {

    public static void main(String[] args) {
        System.out.println("EL HASH PERFECTO PARA 'admin' ES: " + new BCryptPasswordEncoder().encode("admin"));
        SpringApplication.run(PrevDengueApplication.class, args);
    }
}