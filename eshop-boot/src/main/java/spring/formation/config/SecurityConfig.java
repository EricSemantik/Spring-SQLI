package spring.formation.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("asma").password("{noop}123456").roles("USER").and().withUser("benjamin")
				.password("{noop}123456").roles("ADMIN").and().withUser("wafa").password("{noop}123456")
				.roles("USER", "ADMIN");

	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests().antMatchers("/data-rest/**").hasRole("ADMIN").antMatchers("/**").authenticated().and().httpBasic();
//		http.csrf().disable().authorizeRequests().antMatchers("/**").permitAll();
	}
}
