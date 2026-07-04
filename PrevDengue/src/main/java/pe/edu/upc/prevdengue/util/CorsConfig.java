package pe.edu.upc.prevdengue.util;

// 🟢 IMPORTANTE: Asegúrate de usar el import de Spring Web, no el de Apache Catalina
import org.springframework.web.filter.CorsFilter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
public class CorsConfig {
    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(List.of("http://localhost:4200"));
        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        config.setAllowedHeaders(List.of("*"));

        // 🛡️ REQUERIDO: Si usas tokens (Authorization header), esto debe ser true
        config.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);

        // 🟢 AHORA SÍ: Le pasamos tu configuración al filtro
        return new CorsFilter(source);
    }
}