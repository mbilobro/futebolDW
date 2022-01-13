package dw.futebol.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import dw.futebol.model.Jogador;
import dw.futebol.repository.JogadorRepository;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api")

public class JogadorController {

    @Autowired
    JogadorRepository rep;

    // Método GET /api/jogadores para listar os jogadores cadastrados no banco de dados
    @GetMapping("/jogadores")
    public ResponseEntity<List<Jogador>> getAllJogadores(@RequestParam(required = false) String nome) {

        try {

            List<Jogador> la = new ArrayList<Jogador>();

            if (nome == null){
                rep.findAll().forEach(la::add);
            } else {
                rep.findByTituloContaining(nome).forEach(la::add);
            }

            if (la.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(la, HttpStatus.OK);
            }

        } catch (Exception e){
            // TODO: handle exception
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Método POST /api/jogadores para criar um novo jogador
    @PostMapping("/jogadores")
    public ResponseEntity<Jogador> createJogador(@RequestBody Jogador jg) {

        try {
            Jogador j = rep.save(new Jogador(jg.getNome(), jg.getEmail(), jg.getData()));

            return new ResponseEntity<>(j, HttpStatus.CREATED);

        } catch (Exception e){
            // TODO: handle exception
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
