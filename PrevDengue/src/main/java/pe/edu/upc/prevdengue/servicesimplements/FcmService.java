package pe.edu.upc.prevdengue.servicesimplements;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import org.springframework.stereotype.Service;
import pe.edu.upc.prevdengue.repositories.IUserRepository; // ✅ importa tu repo

@Service
public class FcmService {

    private final IUserRepository userRepository; // ✅ final para inyección por constructor

    // Constructor injection
    public FcmService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void sendPushNotification(String fcmToken, String title, String body) {
        try {
            // 1. Construimos el diseño de la notificación
            Notification notification = Notification.builder()
                    .setTitle(title)
                    .setBody(body)
                    .build();

            // 2. Adjuntamos la notificación al "sobre" con la dirección del celular (Token)
            Message message = Message.builder()
                    .setToken(fcmToken)
                    .setNotification(notification)
                    .build();

            // 3. ¡Lo enviamos!
            String response = FirebaseMessaging.getInstance().send(message);
            System.out.println("✅ Mensaje Push enviado con éxito al token. ID: " + response);

        } catch (Exception e) {
            System.err.println("❌ Error al enviar notificación push: " + e.getMessage());
            // Si el error dice "not found" o "unregistered", significa que el token murió.
            if (e.getMessage().contains("Requested entity was not found")
                    || e.getMessage().contains("registration-token-not-registered")) {

                System.out.println("🧹 Limpiando token muerto de la base de datos...");
                userRepository.clearDeadFcmToken(fcmToken); // ✅ método directo en el repo
            }
        }
    }
}
