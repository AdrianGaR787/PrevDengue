package pe.edu.upc.prevdengue.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

@Configuration
public class FirebaseConfig {

    @PostConstruct
    public void initialize() {
        try {
            InputStream serviceAccount;

            // ☁️ 1. Buscamos la llave en las variables de entorno (Modo Render)
            String firebaseEnv = System.getenv("FIREBASE_CREDENTIALS");

            if (firebaseEnv != null && !firebaseEnv.trim().isEmpty()) {
                // Si la variable existe, la convertimos en un archivo virtual
                serviceAccount = new ByteArrayInputStream(firebaseEnv.getBytes(StandardCharsets.UTF_8));
            } else {
                // 💻 2. Si no existe, usamos el archivo físico (Modo Local)
                // ⚠️ IMPORTANTE: Cambia esta ruta por la que tú ya tenías en tu código
                serviceAccount = new FileInputStream("src/main/resources/firebase-service-account.json");
            }

            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .build();

            if (FirebaseApp.getApps().isEmpty()) {
                FirebaseApp.initializeApp(options);
            }

            System.out.println("🔥 Firebase inicializado correctamente");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}