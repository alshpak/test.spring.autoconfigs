package test.spring.autoconfig.sampleapp.resttest;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.handler.SimpleUrlHandlerMapping;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableWebMvc
public class BeansConfig {

    @Bean
    public SimpleUrlHandlerMapping welcomeCtrlOne() {
        SimpleUrlHandlerMapping mapping = new SimpleUrlHandlerMapping();

        Map<String, Object> params = new HashMap<>();
        params.put("/simpleUrl", welcomeController());
        mapping.setUrlMap(params);

        return mapping;
    }

    @Bean
    public WelcomeController welcomeController() {
        return new WelcomeController();
    }

}
