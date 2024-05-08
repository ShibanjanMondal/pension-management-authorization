package dh.pensionmanagement.webservices.auth.util.jwt.config;


import dh.pensionmanagement.webservices.auth.service.MyUserDetailsService;
import dh.pensionmanagement.webservices.auth.util.jwt.filter.JwtRequestFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.header.writers.StaticHeadersWriter;
import org.springframework.web.cors.CorsConfiguration;

@EnableWebSecurity
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Autowired
    private MyUserDetailsService myUserDetailsService;
    @Autowired
    private JwtRequestFilter jwtRequestFilter;


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(myUserDetailsService);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS, "/ProcessPension/**").permitAll()
                .antMatchers("/h2-console/**",
                        "/sign-up",
                        "/login",
                        "/authenticate",
                        "/swagger-ui/**").permitAll()
                .anyRequest().authenticated()
                .and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.headers().addHeaderWriter(
                        new StaticHeadersWriter("Access-Control-Allow-Origin", "http://localhost:4200"))
                .defaultsDisabled()
                .cacheControl();
        http.cors().configurationSource(request -> new CorsConfiguration().applyPermitDefaultValues());


        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }


}
