package pl.ski;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import pl.ski.management_file.ManagementFile;

@SpringBootApplication
public class SkiApplication {

	public static void main(String[] args) {
		ManagementFile.runServerPHP();
		SpringApplication.run(SkiApplication.class, args);
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/api/offer-ski").allowedOrigins("*");
			}
		};
	}

}
