package br.com.alura.forum.controllers;

import br.com.alura.forum.dtos.TokenDto;
import br.com.alura.forum.dtos.login.LoginForm;
import br.com.alura.forum.security.TokennService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AutenticacaoController {

    //Dispara o processo de autenticação via username/password
    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private TokennService tokenService;

    @PostMapping
    public ResponseEntity<?> autenticar(@RequestBody @Valid LoginForm form) {

        UsernamePasswordAuthenticationToken dadosLogin = form.converter();

        try {
            //Chamará a clase do autenticaoService para consulta dos dados de login no BD
            Authentication authentication = authManager.authenticate(dadosLogin);

            //O método gerarToken recebe as credenciais do usuário logado
            String token = tokenService.gerarToken(authentication);
            return new ResponseEntity<>(new TokenDto(token, "Bearer"),HttpStatus.OK);

        } catch (AuthenticationException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
