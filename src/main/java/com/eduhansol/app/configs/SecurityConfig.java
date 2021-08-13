package com.eduhansol.app.configs;

import com.eduhansol.app.services.AdminService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final AdminService _adminService;

    @Autowired
    public SecurityConfig(AdminService adminService) {
        _adminService = adminService;
    }

    @Bean
    public CookieCsrfTokenRepository csrfTokenRepository() {
        return new CookieCsrfTokenRepository();
    }

    // @Autowired
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/css/**", "/js/**", "/images/**",
                "/lib/**", "/api/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().csrfTokenRepository(csrfTokenRepository()).and().authorizeRequests().antMatchers("/admin/**")
                .hasAnyRole("ADMIN", "HQ", "REGION", "DPT")
                .antMatchers("/group/**").hasAnyRole("ADMIN", "HQ", "REGION", "DPT").antMatchers("/tester/**")
                .hasAnyRole("ADMIN", "HQ", "REGION", "DPT").antMatchers("/hq/**").hasRole("ADMIN")
                .antMatchers("/region/**").hasRole("ADMIN").antMatchers("/department/**").hasRole("ADMIN")
                .antMatchers("/user/**").hasRole("ADMIN").antMatchers("/**").permitAll().and()
                .formLogin().loginPage("/admin/login").defaultSuccessUrl("/group/index").permitAll().and()
                .logout().logoutRequestMatcher(new AntPathRequestMatcher("/admin/logout"))
                .logoutSuccessUrl("/admin/login").invalidateHttpSession(true).and()
                .exceptionHandling().accessDeniedPage("/admin/denied");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(_adminService).passwordEncoder(passwordEncoder());
    }
}