package br.com.alura.forum.dtos.topico;

import br.com.alura.forum.models.Curso;
import br.com.alura.forum.models.Topico;
import br.com.alura.forum.repositories.CursoRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TopicoForm {

    @NotNull @NotEmpty(message = "Título não pode está em branco") @Length(min = 5)
    private String titulo;

    @NotNull @NotEmpty(message = "Mensagem não pode está em branco") @Length(min = 10)
    private String mensagem;

    @NotNull @NotEmpty(message = "Curso não pode está em branco") @Length(min = 5)
    private String nomeCurso;


    public Topico converter(CursoRepository cursoRepository) {

        Curso curso = cursoRepository.findByNome(nomeCurso);

        return new Topico(titulo, mensagem, curso);

    }
}
