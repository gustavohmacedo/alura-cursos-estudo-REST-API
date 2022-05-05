package br.com.alura.forum.dtos.login;

import br.com.alura.forum.models.Usuario;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Setter
@Getter
public class LoginForm {
    @Email(message = "Email inválido")
    @NotBlank(message = "Email não pode está em branco")
    @Length(min = 10)
    private String email;

    @NotBlank(message = "Senha não pode está em branco")
    @Length(min = 6, max = 8)
    private String senha;

    public UsernamePasswordAuthenticationToken converter() {
        return new UsernamePasswordAuthenticationToken(email, senha);
    }
}
