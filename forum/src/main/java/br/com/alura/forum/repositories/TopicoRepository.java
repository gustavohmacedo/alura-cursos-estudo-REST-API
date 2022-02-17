package br.com.alura.forum.repositories;

import br.com.alura.forum.models.Topico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TopicoRepository extends JpaRepository<Topico, Long> {

    List<Topico> findByCursoNome(String titulo);

}
