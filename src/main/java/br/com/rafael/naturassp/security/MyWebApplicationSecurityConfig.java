package br.com.rafael.naturassp.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AnyRequestMatcher;

@Configuration
@EnableWebSecurity
public class MyWebApplicationSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception{
		System.out.println(" ---->Configurando acessos --- ");
		
		httpSecurity.csrf().disable()
					.authorizeRequests()
					//requisições liberadas
					.antMatchers(HttpMethod.GET,"/categorias").permitAll()
					.antMatchers(HttpMethod.GET,"/categorias/search").permitAll()
					.antMatchers(HttpMethod.GET,"/cliente/*").permitAll()
					
					.antMatchers(HttpMethod.POST,"/pedido").permitAll()
					
					.antMatchers("/produto").permitAll()
					
					.antMatchers(HttpMethod.POST,"/login").permitAll()
					
					.antMatchers(HttpMethod.GET,"/produto").permitAll()
					.antMatchers(HttpMethod.GET,"/produto/categoria/*").permitAll()
					.antMatchers(HttpMethod.GET,"/produto/*").permitAll()
					.antMatchers(HttpMethod.GET,"/produto/busca").permitAll()
					//.anyRequest().permitAll();
					.anyRequest().authenticated().and().cors();
		
		httpSecurity.addFilterBefore(new TokenFilter(),UsernamePasswordAuthenticationFilter.class);
		
	}
	
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/v2/api-docs", "/configuration/ui", "/swagger-resources/**", "/configuration/**",
		"/swagger-ui.html", "/webjars/**");
		}

}
