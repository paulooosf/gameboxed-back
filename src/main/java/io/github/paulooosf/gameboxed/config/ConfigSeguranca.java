package io.github.paulooosf.gameboxed.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class ConfigSeguranca {

    private final FiltroSeguranca filtroSeguranca;

    @Autowired
    public ConfigSeguranca(FiltroSeguranca filtroSeguranca) {
        this.filtroSeguranca = filtroSeguranca;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .cors(Customizer.withDefaults())
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.POST, "/autenticar/login").permitAll()
                        .requestMatchers(HttpMethod.POST, "/autenticar/esqueci-senha").permitAll()
                        .requestMatchers(HttpMethod.POST, "/autenticar/redefinir-senha").permitAll()
                        .requestMatchers(HttpMethod.GET, "/jogos/lista").permitAll()
                        .requestMatchers(HttpMethod.GET, "/jogos/{id}").permitAll()
                        .requestMatchers(HttpMethod.POST, "/jogos").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/jogos/editar").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/jogos/deletar").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/usuarios/lista").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/usuarios/{id}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/usuarios").permitAll()
                        .requestMatchers(HttpMethod.GET, "/avaliacoes/lista").permitAll()
                        .requestMatchers(HttpMethod.GET, "/avaliacoes/{id}").permitAll()
                        .anyRequest().authenticated()
                )
                .addFilterBefore(filtroSeguranca, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
