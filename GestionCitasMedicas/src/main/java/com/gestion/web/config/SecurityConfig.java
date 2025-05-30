package com.gestion.web.config;

import com.gestion.web.services.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final UsuarioServicio usuarioServicio;


    @Autowired
    public SecurityConfig(@Lazy UsuarioServicio usuarioServicio) {
        this.usuarioServicio = usuarioServicio;

    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(userDetailsService());
        auth.setPasswordEncoder(passwordEncoder());
        auth.setHideUserNotFoundExceptions(false);
        return auth;
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> {

            org.springframework.security.core.userdetails.User userDetails = 
                (org.springframework.security.core.userdetails.User) usuarioServicio.loadUserByUsername(username);
            
            return userDetails;
        };
    }

    @Bean
    public AuthenticationFailureHandler authenticationFailureHandler() {
        return (request, response, exception) -> {
            String errorMessage = "";

            if (exception instanceof BadCredentialsException) {
                if ("CUENTA_INACTIVA".equals(exception.getMessage())) {
                    errorMessage = "Tu cuenta no está activada. Revisa tu correo electrónico.";
                } else if ("INVALID_CREDENTIALS".equals(exception.getMessage())) {
                    errorMessage = "DNI/Email o contraseña incorrectos.";
                } else {
                    errorMessage = "Credenciales incorrectas.";
                }
            } else if (exception instanceof UsernameNotFoundException) {
                errorMessage = "Usuario no encontrado.";
            } else {
                errorMessage = "Error de inicio de sesión.";
            }


            response.sendRedirect("/Public/Login?error=" + URLEncoder.encode(errorMessage, StandardCharsets.UTF_8));
        };
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authz -> authz
                        .requestMatchers("/",
                                "/register",
                                "/procesarRegistro", // AGREGAR ESTA LÍNEA
                                "/Nosotros**", "/registroexitoso**", "/login**",
                                "/Js/**", "/css/**", "/Imagenes/**", "/uploads/**",
                                "/confirmar-cuenta/**").permitAll()

                .requestMatchers("/dashboard", "/dashboard/**")
                    .hasAnyAuthority("Moderador", "Administrador")

                .requestMatchers("/agendar").authenticated()

                .anyRequest().authenticated()
        )
        .formLogin(login -> login
                .loginPage("/Public/Login")
                .loginProcessingUrl("/login")
                .usernameParameter("username") // Mantener username como parámetro
                .passwordParameter("password")
                .failureHandler(authenticationFailureHandler())
                .defaultSuccessUrl("/", true)
                .permitAll()
        )
        .logout(logout -> logout
                .invalidateHttpSession(true)
                .clearAuthentication(true)
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login?logout")
                .permitAll()
        );
        return http.build();
    }
}
