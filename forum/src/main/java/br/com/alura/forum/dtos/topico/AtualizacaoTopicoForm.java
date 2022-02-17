package br.com.alura.forum.dtos.topico;

import br.com.alura.forum.models.Topico;
import br.com.alura.forum.repositories.TopicoRepository;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Setter
@Getter
public class AtualizacaoTopicoForm {

    @NotNull
    @NotEmpty(message = "Título não pode está em branco")
    @Length(min = 5)
    private String titulo;

    @NotNull
    @NotEmpty(message = "Mensagem não pode está em branco")
    @Length(min = 10)
    private String mensagem;


    public Topico atualizar(Long id, TopicoRepository topicoRepository) {

        Topico topico = topicoRepository.getById(id);

        topico.setTitulo(this.titulo);
        topico.setMensagem(this.mensagem);

        return topico;

    }
}
