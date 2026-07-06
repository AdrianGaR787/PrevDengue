package pe.edu.upc.prevdengue.util;

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

        // 🚀 AQUÍ: Agregaremos tu URL de Vercel una vez que la plataforma te la dé.
        // Por ahora lo dejamos con localhost, y cuando tengas Vercel será algo como:
        // config.setAllowedOrigins(List.of("http://localhost:4200", "https://prevdengue-frontend.vercel.app"));
        config.setAllowedOrigins(List.of("http://localhost:4200"));

        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        config.setAllowedHeaders(List.of("*"));

        // 🛡️ REQUERIDO para JWT y Firebase Auth
        config.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);

        return new CorsFilter(source);
    }
}