package com.restfulservices.rest;

import com.restfulservices.rest.repository.JpaUserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

import java.util.Locale;

@SpringBootApplication
public class RestfulApp implements CommandLineRunner {
    private static final Logger logger = LoggerFactory.getLogger(RestfulApp.class);

//    @Autowired
//    private JpaUserService jpaUserService;

    @Autowired
    private JpaUserRepository jpaUserRepository;

    public static void main(String[] args) {
        SpringApplication.run(RestfulApp.class, args);
    }

    @Bean
    public LocaleResolver localResolver() {
        AcceptHeaderLocaleResolver localeResolver = new AcceptHeaderLocaleResolver();
        localeResolver.setDefaultLocale(Locale.US);

        return localeResolver;
    }

    @Override
    public void run(String... args) throws Exception {
//        JpaUser user = new JpaUser("Jill", "Admin");
//        jpaUserRepository.save(user);
//        jpaUserService.insert(user);
//        logger.info("New user is created: " + user);
    }

//    @Bean
//    public ResourceBundleMessageSource bundleMessageSource() {
//        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
//        messageSource.setBasename("messages");
//
//        return messageSource;
//    }
}
