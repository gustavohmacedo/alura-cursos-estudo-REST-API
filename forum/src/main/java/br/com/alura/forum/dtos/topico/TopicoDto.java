package br.com.alura.forum.dtos.topico;

import br.com.alura.forum.models.Topico;
import lombok.Getter;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;

@Getter
public class TopicoDto {

    private Long id;
    private String titulo;
    private String mensagem;
    private LocalDateTime dataCriacao;

    //não precisa do set, pois ao chamar esse construtor seus atributos terão acesso aos seus respectivos sets da classe Topico.
    public TopicoDto(Topico topico) {
        this.id = topico.getId();
        this.titulo = topico.getTitulo();
        this.mensagem = topico.getMensagem();
        this.dataCriacao = topico.getDataCriacao();

    }

    public static Page<TopicoDto> converter(Page<Topico> topicos) {
        return topicos.map(TopicoDto::new);
    }


}
