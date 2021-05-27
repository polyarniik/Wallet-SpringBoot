//package ru.kpfu.itis.safiullin.walletspringboot.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.format.FormatterRegistry;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//import ru.kpfu.itis.safiullin.walletspringboot.converters.DateConverter;
//
//@Configuration
//public class ApplicationConfig implements WebMvcConfigurer {
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Bean
//    public DateConverter dateConverter() {
//        return new DateConverter();
//    }
//
//    @Override
//    public void addFormatters(FormatterRegistry formatterRegistry) {
//        formatterRegistry.addConverter(dateConverter());
//    }
//}
