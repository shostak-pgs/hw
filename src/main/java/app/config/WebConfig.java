package app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@EnableWebMvc
@ComponentScan()
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/WEB-INF/views/**").addResourceLocations("/views/");
    }

    @Bean
    public InternalResourceViewResolver setupViewResolver() {
        final InternalResourceViewResolver resolver = new InternalResourceViewResolver("/WEB-INF/views/", ".jsp");
        resolver.setViewClass(JstlView.class);
        return resolver;
    }
}
