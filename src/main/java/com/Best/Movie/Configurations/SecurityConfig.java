package com.Best.Movie.Configurations;

import java.io.IOException;

import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.OncePerRequestFilter;


import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableWebSecurity
@Configuration
@Order(1) 
public class SecurityConfig extends WebSecurityConfiguration {
 
    @SuppressWarnings({ "removal", "deprecation" })
	@Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http
	    	.headers(headers -> headers
	            .frameOptions(frameOptions -> frameOptions
	                .disable()
	            )
	        )
        	.cors().configurationSource(corsConfigurationSource())
	        .and()    
	        .authorizeRequests(requests -> requests
                    .requestMatchers(new AntPathRequestMatcher("/menu/**")).permitAll()
                    .requestMatchers(new AntPathRequestMatcher("/checkAnswer/**")).permitAll()
                    .requestMatchers(new AntPathRequestMatcher("/lost")).permitAll()
                    .requestMatchers(new AntPathRequestMatcher("/infos")).permitAll()
                    .requestMatchers(new AntPathRequestMatcher("/languages")).permitAll()
                    .requestMatchers(new AntPathRequestMatcher("/css/**")).permitAll()
                    .requestMatchers(new AntPathRequestMatcher("/images/**")).permitAll()
                    .requestMatchers(new AntPathRequestMatcher("/level/**")).permitAll()
                    .anyRequest().permitAll())
                .formLogin()
                    .successHandler(new SavedRequestAwareAuthenticationSuccessHandler())
      	          .permitAll()
      	          .and()
      	      .logout()
      	      	  .logoutSuccessUrl("/login?logout")
      	          .permitAll()
      	          .and()
      	      .exceptionHandling()
                .authenticationEntryPoint((request, response, authException) -> {
                    authException.printStackTrace();
                    response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Error de autenticación: " + authException.getMessage());
                })
                .accessDeniedHandler((request, response, accessDeniedException) -> {
                    accessDeniedException.printStackTrace();
                    response.sendError(HttpServletResponse.SC_FORBIDDEN, "Acceso denegado: " + accessDeniedException.getMessage());
                });


            return http.build();
          }


    @Bean
    private static CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedOrigin("https://peculiaridadesdelmundo.blogspot.com");
        configuration.addAllowedOrigin("https://peculiaridadesdomundoblog.blogspot.com");
        configuration.addAllowedOrigin("https://besonderheitenderwelt.blogspot.com");
        configuration.addAllowedOrigin("https://peculiaritiesoftheworld.blogspot.com");
        configuration.addAllowedOrigin("https://trivia-game-zjet.onrender.com/");
        configuration.addAllowedOrigin("http://localhost:8080"); 
        configuration.addAllowedOrigin("http://localhost:8443"); 
        configuration.addAllowedMethod("GET");
        configuration.addAllowedMethod("POST");
        configuration.addAllowedHeader("Access-Control-Allow-Headers");
        configuration.addAllowedHeader("Content-Type");
        configuration.addAllowedHeader("X-Token");

        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}   
    