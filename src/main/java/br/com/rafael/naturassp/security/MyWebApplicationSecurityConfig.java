package br.com.rafael.naturassp.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class MyWebApplicationSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception{
		System.out.println(" ---->Configurando acessos --- ");
		
		httpSecurity.csrf().disable()
					.authorizeRequests()
					.antMatchers("/produto").permitAll()
					.antMatchers(HttpMethod.POST,"/login").permitAll()
					.anyRequest().authenticated();
		
		httpSecurity.addFilterBefore(new TokenFilter(),UsernamePasswordAuthenticationFilter.class);
		
	}
	
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/v2/api-docs", "/configuration/ui", "/swagger-resources/**", "/configuration/**",
		"/swagger-ui.html", "/webjars/**");
		}

}
