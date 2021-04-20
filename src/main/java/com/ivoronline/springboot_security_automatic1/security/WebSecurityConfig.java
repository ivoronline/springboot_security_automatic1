package com.ivoronline.springboot_security_automatic1.security;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  //=================================================================
  // USER DETAILS SERVICE
  //=================================================================
  @Bean
  @Override
  protected UserDetailsService userDetailsService() {

    //CREATE USERS
    UserDetails myuser  = User.withUsername("myuser" ).password("myuserpassword" ).roles("USER" ).build();
    UserDetails myadmin = User.withUsername("myadmin").password("myadminpassword").roles("ADMIN").build();

    //STORE USER
    return new InMemoryUserDetailsManager(myuser);

  }

  //=======================================================================
  // PASSWORD ENCODER
  //=======================================================================
  @Bean
  PasswordEncoder passwordEncoder() {
    return NoOpPasswordEncoder.getInstance();
  }

  //=======================================================================
  // CONFIGURE
  //=======================================================================
  @Override
  protected void configure(HttpSecurity httpSecurity) throws Exception {
    httpSecurity.authorizeRequests().antMatchers("/Authenticate").permitAll(); //ANONYMOUS ACCESS
    httpSecurity.formLogin();                                                  //DEFAULT LOGIN FORM
  }

}
