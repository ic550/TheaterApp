package com.example.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.example.app.login.UserService;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor // Lombokでコンストラクタインジェクション
public class WebSecurityConfig {

    private final UserService userService;
 // 作成したSuccessHandlerをインジェクション
    private final AuthenticationSuccessHandler successHandler;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    	http.formLogin(login -> login
                .loginPage("/login")
                .loginProcessingUrl("/login")
                .successHandler(successHandler) // ここでSuccessHandlerを適用
                .failureUrl("/login_error")
                .permitAll()
        ).logout(logout -> logout
                .logoutSuccessUrl("/logout_success")
        ).authorizeHttpRequests(authz -> authz
                // 管理者向けページへのアクセスはADMIN権限のみに制限
                .requestMatchers("/admin", "/admin/**").hasRole("ADMIN")
                // その他のURLは認証済みユーザーなら誰でもアクセス可能
                .anyRequest().authenticated()
        );
        return http.build();
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
