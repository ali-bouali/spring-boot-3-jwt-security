package com.alibou.security.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import static com.alibou.security.user.Permission.ADMIN_CREATE;
import static com.alibou.security.user.Permission.ADMIN_DELETE;
import static com.alibou.security.user.Permission.ADMIN_READ;
import static com.alibou.security.user.Permission.ADMIN_UPDATE;
import static com.alibou.security.user.Permission.MANAGER_CREATE;
import static com.alibou.security.user.Permission.MANAGER_DELETE;
import static com.alibou.security.user.Permission.MANAGER_READ;
import static com.alibou.security.user.Permission.MANAGER_UPDATE;
import static com.alibou.security.user.Role.ADMIN;
import static com.alibou.security.user.Role.MANAGER;
import static org.springframework.http.HttpMethod.DELETE;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.HttpMethod.PUT;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
public class SecurityConfiguration {

  private static final String MANAGEMENT_URL = "/api/v1/management/**";

  private final JwtAuthenticationFilter jwtAuthFilter;
  private final AuthenticationProvider authenticationProvider;
  private final LogoutHandler logoutHandler;

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
        .csrf(AbstractHttpConfigurer::disable)
        .authorizeHttpRequests(a ->
          a.requestMatchers(
                  "/api/v1/auth/**",
                  "/v2/api-docs",
                  "/v3/api-docs",
                  "/v3/api-docs/**",
                  "/swagger-resources",
                  "/swagger-resources/**",
                  "/configuration/ui",
                  "/configuration/security",
                  "/swagger-ui/**",
                  "/webjars/**",
                  "/swagger-ui.html"
          ).permitAll()


          .requestMatchers(MANAGEMENT_URL).hasAnyRole(ADMIN.name(), MANAGER.name())


          .requestMatchers(GET, MANAGEMENT_URL).hasAnyAuthority(ADMIN_READ.name(), MANAGER_READ.name())
          .requestMatchers(POST, MANAGEMENT_URL).hasAnyAuthority(ADMIN_CREATE.name(), MANAGER_CREATE.name())
          .requestMatchers(PUT, MANAGEMENT_URL).hasAnyAuthority(ADMIN_UPDATE.name(), MANAGER_UPDATE.name())
          .requestMatchers(DELETE, MANAGEMENT_URL).hasAnyAuthority(ADMIN_DELETE.name(), MANAGER_DELETE.name())


         /* .requestMatchers("/api/v1/admin/**").hasRole(ADMIN.name())

          .requestMatchers(GET, "/api/v1/admin/**").hasAuthority(ADMIN_READ.name())
          .requestMatchers(POST, "/api/v1/admin/**").hasAuthority(ADMIN_CREATE.name())
          .requestMatchers(PUT, "/api/v1/admin/**").hasAuthority(ADMIN_UPDATE.name())
          .requestMatchers(DELETE, "/api/v1/admin/**").hasAuthority(ADMIN_DELETE.name())*/


          .anyRequest().authenticated())
        .sessionManagement(s ->
          s.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .authenticationProvider(authenticationProvider)
        .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
        .logout(l -> l.logoutUrl("/api/v1/auth/logout")
          .addLogoutHandler(logoutHandler)
          .logoutSuccessHandler((request, response, authentication) -> SecurityContextHolder.clearContext()))
    ;

    return http.build();
  }
}
