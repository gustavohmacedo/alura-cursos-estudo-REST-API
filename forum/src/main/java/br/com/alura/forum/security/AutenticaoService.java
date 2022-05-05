package br.com.alura.forum.security;

import br.com.alura.forum.models.Usuario;
import br.com.alura.forum.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

//Classe responsável para executar a lógica de autenticação
@Service
public class AutenticaoService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    //username: nome de usuário informado na tela de login
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Usuario> usuario = usuarioRepository.findByEmail(username);
        if (usuario.isEmpty()) {
            throw new UsernameNotFoundException("Dados inválidos!");
        }
        return usuario.get();
    }
}
