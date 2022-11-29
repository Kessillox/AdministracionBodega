package cl.desafiolatam.challengesecurity1.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity  //Anotacion indica que se aplicara EnableWebSecurity
@Configuration		//Anotacion que declara la configuracion 
public class WebSecurityConfig extends WebSecurityConfigurerAdapter { //Inicio de la clase WebSecurityConfig que extiende (hereda) laClase WebSecurityAdapter

    @Override //Anotacion de Sobreescribir.
    public void configure(AuthenticationManagerBuilder auth) throws Exception { //Metodo configure que crea dos usuarios  a traves del objeto de gestion de autenticacion
        auth.inMemoryAuthentication()
                .withUser("jbl@mail.com")//Crea con el usuario "jbl@gmail.com"
                .password(passwordEncoder().encode("bar"))//ingresa un contraseña codificada (bar)
                .roles("BODEGA")//asigna el rol de bodega al usuario 
        .and()//es un concatenador del metodo inMemoryAutentication
        .withUser("bose@mail.com") //asigana un nuevo usuario llamado "bose@gmail.com"
                .password(passwordEncoder().encode("msc"))//ingresa una contraseña 
                .roles("BODEGA");
    }

    @Override // sobreescribir el siguiente metodo
    public void configure(HttpSecurity http) throws Exception { // Metodo sin retorno con una extencion Exception
        http.csrf().disable()//
        .authorizeRequests()
                .antMatchers("/materiales/**").hasRole("BODEGA")// configura la ruta "/materiales**" solo para los que tengan como rol "Bodega"
                .antMatchers("/login").permitAll()//Se configura la ruta login a todos los usuarios que intenten acceder
                .anyRequest().authenticated()//
                .and()
                .formLogin()
                .loginPage("/login")
                .failureUrl("/login?error=true")
                .usernameParameter("email")
                .passwordParameter("password")
                .defaultSuccessUrl("/materiales");
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
