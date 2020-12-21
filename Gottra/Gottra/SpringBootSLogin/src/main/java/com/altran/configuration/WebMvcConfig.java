package com.altran.configuration;


import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.rememberme.InMemoryTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		return bCryptPasswordEncoder;
	}
	
	// Token stored in Memory (Of Web Server).
	// for remmber me checkbox
	@Bean
	public PersistentTokenRepository persistentTokenRepository() {
	    InMemoryTokenRepositoryImpl memory = new InMemoryTokenRepositoryImpl();
	    return memory;
	}
	
	
	@Bean
	public LocaleResolver localeResolver() {
	  SessionLocaleResolver localeResolver = new SessionLocaleResolver();
	  localeResolver.setDefaultLocale(Locale.FRENCH);	  
	  return localeResolver;
	}
	 
	
	@Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("i18n/messages");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }
}
