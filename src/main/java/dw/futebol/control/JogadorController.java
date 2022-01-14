package dw.futebol.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import dw.futebol.model.Jogador;
import dw.futebol.repository.JogadorRepository;

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
                rep.findByNomeContaining(nome).forEach(la::add);
            }

            if (la.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(la, HttpStatus.OK);

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

    // Método PUT /api/jogadores para editar um jogador
    @PutMapping("/jogadores/{cod_jogador}")
    public ResponseEntity<Jogador> updateJogador(@PathVariable("cod_jogador") int cod_jogador, @RequestBody Jogador j)
    {
        Optional<Jogador> data = rep.findById(cod_jogador);

        if (data.isPresent())
        {
            Jogador jg = data.get();
            jg.setNome(j.getNome());
            jg.setEmail(j.getEmail());
            jg.setData(j.getData());

            return new ResponseEntity<>(rep.save(jg), HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    // Método DELETE /api/jogadores para deletar um codigo de um jogador especifico
    @DeleteMapping("/jogadores/{cod_jogador}")
    public ResponseEntity<HttpStatus> deleteJogador(@PathVariable("cod_jogador") int cod_jogador) {
        try {
            rep.deleteById(cod_jogador);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            
        } catch (Exception e) {
            //TODO: handle exception
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
    }

    // Método DELETE /api/jogadores para deletar todos os registros do BD
    @DeleteMapping("/jogadores")
    public ResponseEntity<HttpStatus> deleteAllJogadores() {
        try {
            rep.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            
        } catch (Exception e) {
            //TODO: handle exception
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
    }
}
