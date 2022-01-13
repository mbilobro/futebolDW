package dw.futebol.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import dw.futebol.model.Jogador;

public interface JogadorRepository extends JpaRepository<Jogador, Integer> {
    List<Jogador> findByTituloContaining(String nome);
}
