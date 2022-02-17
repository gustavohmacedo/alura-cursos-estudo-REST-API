package br.com.alura.forum.controllers;

import br.com.alura.forum.dtos.topico.AtualizacaoTopicoForm;
import br.com.alura.forum.dtos.topico.DetalhesTopicoDto;
import br.com.alura.forum.dtos.topico.TopicoDto;
import br.com.alura.forum.dtos.topico.TopicoForm;
import br.com.alura.forum.models.Topico;
import br.com.alura.forum.repositories.CursoRepository;
import br.com.alura.forum.repositories.TopicoRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("/topicos")
public class TopicoController {

    private final TopicoRepository topicoRepository;
    private final CursoRepository cursoRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<TopicoDto> cadastraTopico(@RequestBody @Valid TopicoForm form, UriComponentsBuilder
            uriComponentsBuilder) {

        Topico topico = form.converter(cursoRepository);
        topicoRepository.save(topico);

        //uri location do recurso criado
        URI uri = uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();

        return ResponseEntity.created(uri).body(new TopicoDto(topico));
    }

    @GetMapping
    public List<TopicoDto> listaTopicos(String nomeCurso) {

        if (nomeCurso == null) {
            List<Topico> topicos = topicoRepository.findAll();

            return TopicoDto.converter(topicos);

        } else {
            List<Topico> topicos = topicoRepository.findByCursoNome(nomeCurso);

            return TopicoDto.converter(topicos);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalhesTopicoDto> buscaTopicoDetalhado(@PathVariable Long id) {

        Optional<Topico> topico = topicoRepository.findById(id);

        if (topico.isEmpty()) {

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(new DetalhesTopicoDto(topico.get()), HttpStatus.OK);

    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<TopicoDto> atualizaTopico(@RequestBody @Valid AtualizacaoTopicoForm form,
                                                    @PathVariable Long id) {

        Optional<Topico> topicoOptional = topicoRepository.findById(id);

        if (topicoOptional.isPresent()) {

            Topico topico = form.atualizar(id, topicoRepository);

            return new ResponseEntity<>(new TopicoDto(topico), HttpStatus.OK);

        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> removeTopico(@PathVariable Long id) {

        Optional<Topico> topico = topicoRepository.findById(id);

        if (topico.isPresent()) {
            topicoRepository.deleteById(id);

            return new ResponseEntity<>(HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);


    }
}