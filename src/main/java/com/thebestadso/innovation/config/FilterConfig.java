package com.thebestadso.innovation.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.thebestadso.innovation.filter.JwtValidationFilter;

@Configuration // Fábrica de beans
public class FilterConfig {
    @Bean
    FilterRegistrationBean<JwtValidationFilter> jwtFilter(JwtValidationFilter jwtValidationFilter) {
        // Crea un contenedor (una instancia) del bean para el filtro
         FilterRegistrationBean<JwtValidationFilter> registrationBean = new FilterRegistrationBean<>();

         // Es decirle a spring que filtro vamos a utilizar
         registrationBean.setFilter(jwtValidationFilter);

         // A que rutas queremos que se aplique el filtro (todas)
         registrationBean.addUrlPatterns("/*");

         // En que orden de prioridad queremos que se ejecute el filtro
         registrationBean.setOrder(1);

         // Retornamos el registro del filtro (bean ya no está huerfano)
         return registrationBean;

    }
}
