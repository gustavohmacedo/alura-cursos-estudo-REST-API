package br.com.alura.forum.security;

import br.com.alura.forum.models.Usuario;
import br.com.alura.forum.repositories.UsuarioRepository;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

//OncePerRequestFilter: A cada requisição é chamado apenas uma vez o filtro
//O Filtro é instanciado via método addFilterBefore na classe SecurityConfigurations,
// pois a classe Filter não é um bean gerenciado pelo Spring;
//Realizar IoD pelo construtor,
public class AutenticacaoViaTokenFilter extends OncePerRequestFilter {

    private final TokennService tokennService;
    private final UsuarioRepository usuarioRepository;

    public AutenticacaoViaTokenFilter(TokennService tokennService, UsuarioRepository usuarioRepository) {
        this.tokennService = tokennService;
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        //Objetivo: Recuperar o token do cabeçalho Authorization, validá-lo e autenticar o usuário.
        String token = recuperarToken(request);
        //Verifica se token é válido
        boolean tokenValido = tokennService.isTokenValido(token);
        //Auntenticando Usuário
        if (tokenValido) {
            autenticarCliente(token);
        }
        //Seguindo o fluxo da requisição
        filterChain.doFilter(request, response);
    }


    private String recuperarToken(HttpServletRequest request) {
        String header = request.getHeader("Authorization");

        if (header == null || !header.startsWith("Bearer ")) {
            return null;
        }

        return header.substring(7);

    }

    private void autenticarCliente(String token) {
        //Recuperando o Id do usuário através do token
        Long idUsuario = tokennService.getIdUsuario(token);
        //Buscando o usuário no BD através do id
        Usuario usuario = usuarioRepository.findById(idUsuario).get();

        //authentication: recebe informações do usuário logado
        UsernamePasswordAuthenticationToken
                authentication = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
        //Forçano a autenticação via SecurityContextHolder
        //O método setAuthentication() recebe os dados do usuário logado e determina ao Spring considerá-lo como autenticado.
        SecurityContextHolder.getContext().setAuthentication(authentication);

    }
}