package fr.axelallain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Bean
	public AuthenticationProvider authProvider() {
		
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(userDetailsService);
		provider.setPasswordEncoder(new BCryptPasswordEncoder());
		return provider;
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http
			.csrf().disable()
			.authorizeRequests()
			.antMatchers("/dashboard-admin",
					"/dashboard-admin/admin-spots", "/dashboard-admin/admin-spots/delete/{id}", "/dashboard-admin/admin-spots/edit/{id}",
					"/dashboard-admin/admin-comments", "/dashboard-admin/admin-comments/delete/{id}", "/dashboard-admin/admin-comments/edit/{id}").fullyAuthenticated()
			.antMatchers(
					"/dashboard", "/dashboard/my-spots", "/dashboard/my-spots/delete/{id}", "/dashboard/my-spots/add-spot", "/dashboard/my-spots/edit/{id}", "/dashboard/my-spots/more-spot/{id}", "/dashboard/my-spots/more-spot/{spotid}/delete/{voieid}", "/dashboard/my-spots/more-spot/{spotid}/add-voie", "/dashboard/my-spots/more-spot/{spotid}/edit-voie/{voieid}", "/dashboard/my-spots/more-spot/{spotid}/more-voie/{voieid}", "/dashboard/my-spots/more-spot/{spotid}/more-voie/{voieid}/add-longueur", 
					"/dashboard/my-topos", "/dashboard/my-topos/delete/{id}", "/dashboard/my-topos/add-topo", "/dashboard/my-topos/edit/{id}", "/dashboard/my-topos/more-topo/{id}", "/dashboard/my-topos/more-topo/{topoid}/delete/{spotid}", 
					"/reserver/{topoid}", "/dashboard/reservation-received", "/dashboard/reservation-received/refuse/{id}", "/dashboard/reservation-received/accept/{id}", "/dashboard/reservation-sent", "/dashboard/reservation-sent/delete/{id}").authenticated()
			.antMatchers("/login", "/signup", "/search-form", "/search-spots", "/search-topos", "/spots", "/topos", "/details-topo/{topoid}", "/details-spot/{spotid}", "/css/**", "/js/**", "/img/**", "/font/**").permitAll()
			.anyRequest().permitAll()
			.and()
			.formLogin()
			.loginPage("/login").permitAll()
			.and()
			.logout().invalidateHttpSession(true)
			.clearAuthentication(true)
			.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
			.logoutSuccessUrl("/").permitAll();
	}
	
}
