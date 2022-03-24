package com.example.insecure.security;

import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import com.example.insecure.user.CustomUserDetailsService;
import com.example.insecure.user.User;
import com.example.insecure.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private DataSource dataSource;
    private final UserRepository userRepository;

    @Autowired
    public WebSecurityConfig(UserRepository userRepository, DataSource dataSource) {
        this.userRepository = userRepository;
        this.dataSource = dataSource;
    }


    @Bean
    public PasswordEncoder passwordEncoder(){
        return new DummyPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new CustomUserDetailsService();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/api/v1/login").authenticated()
                .anyRequest().permitAll()
                .and()
                .formLogin()
                .loginPage("/api/v1/login")
                .usernameParameter("name")
                .successHandler(
                    ( request, response, authentication ) -> {
                        String name = request.getParameter("name") ;
                        User user = userRepository.findUserByName(name);
                        response.setStatus( HttpServletResponse.SC_OK );
                        response.getWriter().write(user.toString());
                        response.getWriter().flush();
                    }
                )
                .failureHandler(
                    ( request, response, authenticationException ) -> {
                        response.setStatus( HttpServletResponse.SC_UNAUTHORIZED );
                    }
                )
                .and()
                .logout().logoutSuccessUrl("/").permitAll()
                .and()
                .csrf().disable()
                .cors();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
        return source;
    }


}