package pe.edu.upc.prevdengue;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder; // <-- Importante

@SpringBootApplication
public class PrevDengueApplication {

    public static void main(String[] args) {
        // ESTA LÍNEA ES MÁGICA: Imprimirá el hash exacto que Spring necesita
        System.out.println("EL HASH PERFECTO PARA 'admin' ES: " + new BCryptPasswordEncoder().encode("admin"));

        SpringApplication.run(PrevDengueApplication.class, args);
    }
}