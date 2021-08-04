package authentification.Securites;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	@Autowired
	public UserDetailsService userDetailsService;
	@Autowired
	public BCryptPasswordEncoder bCryptPasswordEncoder;
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		/*authorisation par user sur la memoire
		auth.inMemoryAuthentication()
		.withUser("admin").password("1234").roles("ADMIN","USER")
		.and()
		.withUser("user").password("1234").roles("USER");
		*/
		auth.userDetailsService(userDetailsService)
		.passwordEncoder(bCryptPasswordEncoder);
		
	}
	/*
	@Bean
	public PasswordEncoder getNoPasswordEncoder() {
		return NoOpPasswordEncoder.getInstance();
		
	}
	*/
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		//http.formLogin();
		
		//definir son propre formulaire d'authentification
		//http.formLogin().loginPage("/myLoginForm.html");
		
		//Desactivation de la session
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
		//donner la permission à tous les utilisateurs de s'authentifier ou de s'enregistrer 
		http.authorizeRequests().antMatchers("/login/**","/enregistrerutilisateur/**").permitAll();
		
		//donner la permission qu'à l'admin d'enregistrer une application
		http.authorizeRequests().antMatchers(HttpMethod.POST,"/applications/**").hasAuthority("admin");
		http.authorizeRequests().antMatchers(HttpMethod.GET,"/roles/**").hasAuthority("admin");
		http.authorizeRequests().antMatchers(HttpMethod.POST,"/role/**").hasAuthority("admin");
		http.authorizeRequests().antMatchers(HttpMethod.GET,"/applications/**").hasAuthority("admin");
		http.authorizeRequests().antMatchers(HttpMethod.POST,"/applications/**").hasAuthority("admin");
		http.authorizeRequests().antMatchers(HttpMethod.GET,"/utilisateurs/**").hasAuthority("admin");
		http.authorizeRequests().anyRequest().authenticated();
		http.addFilter(new JWTAuthenticationFilter(authenticationManager()));
		http.addFilterBefore(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
		
	}
	
}
