package com.example.training;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;

@Configuration //Creates object for current class
@EnableWebSecurity //Enables Spring Security
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
    //Creating users for Authentication purposes.
   
	@Bean
	UserDetailsManager users(DataSource datasource) {
		UserDetails user=User.builder().username("james").password(passwordEncoder().encode("welcome1")).roles("USER").build();
		UserDetails admin=User.builder().username("steven").password(passwordEncoder().encode("welcome1")).roles("USER","ADMIN").build();
		
		JdbcUserDetailsManager users = new JdbcUserDetailsManager(datasource);
		users.createUser(user);
		users.createUser(admin);
		return users;

	
	}
    
    //Configuring Authorization rules
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
        .authorizeRequests()
        .antMatchers("/mvc/employees/all").hasRole("USER")
        .antMatchers("/mvc/employees/create").hasRole("ADMIN")
        .and()
        .formLogin();
    }
    
    //Configuring how to encode the password.
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    } 
    @Bean
	javax.sql.DataSource dataSource() {
		return new EmbeddedDatabaseBuilder()
			.setType(EmbeddedDatabaseType.H2)
			.addScript("classpath:org/springframework/security/core/userdetails/jdbc/users.ddl")
			.build();
	}

}
