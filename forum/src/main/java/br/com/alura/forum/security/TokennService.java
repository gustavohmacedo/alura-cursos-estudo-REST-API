package br.com.alura.forum.security;

import br.com.alura.forum.models.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TokennService {

    //Injeção de dependências de propriedades do arquivo application.properties
    @Value("${forum.jwt.expiration}")
    private String expiration;

    @Value("${forum.jwt.secret}")
    private String secret;

    public String gerarToken(Authentication authentication) {

        //getPrincipal devolve o usuário logado do tipo Object
        Usuario usuarioLogado = (Usuario) authentication.getPrincipal();
        Date hoje = new Date();
        Date tempoExpiracao = new Date(hoje.getTime() + Long.parseLong(expiration));

        return Jwts.builder()
                //quem está gerando este token
                .setIssuer("API do Forúm Alura")
                //quem é o usuário autenticado a qual pertence este token
                .setSubject(usuarioLogado.getId().toString())
                //informar data de geração do token
                .setIssuedAt(hoje)
                //informar tempo de expiração do token
                .setExpiration(tempoExpiracao)
                //Deve passar o algoritmo de criptografia e a chave da aplicação para gerar o hash da criptografia
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    //o método parser() recebe um token, descriptografa e verifica a sua integridade e confiabilidade;
    //O métodp parseClaimsJws() recebe um token e caso esteja válido retorna as informações contidas nele,
    // do contrário é retornado uma exception
    public boolean isTokenValido(String token) {
        try {
            Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    public Long getIdUsuario(String token) {
        Claims body = Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody();
        return Long.parseLong(body.getSubject());
    }
}
