package br.com.alura.forum.security;

import br.com.alura.forum.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
public class SecurityConfigurations extends WebSecurityConfigurerAdapter {

    @Autowired
    private AutenticaoService autenticaoService;

    @Autowired
    private TokennService tokennService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    @Bean
    //Para o Spring identificar que este método devolve authenticationManager para inj. de dependência no controller;
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    //Configurações de Autenticação: controle de acesso (login)
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //o método userDetailsService: recebe a classe que contém a lógica de autenticação
        auth.userDetailsService(autenticaoService).passwordEncoder(new BCryptPasswordEncoder());

    }

    //Configurações de Autorização: Liberação de perfil de acesso ao endpoint, Exe. Quem pode acessar determinada url
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(HttpMethod.GET, "/topicos").permitAll()
                .antMatchers(HttpMethod.GET, "/topicos/*").permitAll()
                .antMatchers(HttpMethod.POST, "/auth").permitAll()
                .antMatchers(HttpMethod.GET, "/actuator/**").permitAll()
                .antMatchers("/h2-forum/*").permitAll()
                .anyRequest().authenticated()
                .and().csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().addFilterBefore(new AutenticacaoViaTokenFilter(tokennService, usuarioRepository),
                        UsernamePasswordAuthenticationFilter.class);


    }

    //Configurações de recursos estáticos: (js, css, imagens, etc.)
    @Override
    public void configure(WebSecurity web) throws Exception {

    }


}
