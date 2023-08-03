package codestate.solotodo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class SoloTodoApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(SoloTodoApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) { // (2)
		return builder.sources(SoloTodoApplication.class);
	}

}
