package dw.futebol.control;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
import org.springframework.web.bind.annotation.RestController;

import dw.futebol.model.*;
import dw.futebol.repository.*;

@RestController
@RequestMapping("api")
public class PagamentoController {

    @Autowired
    PagamentoRepository rep;

    // Método GET /api/pagamentos para listar os pagamentos cadastrados no banco de dados
    @GetMapping("/pagamentos")
    public ResponseEntity<List<Pagamento>> getAllPagamentos() {

        try {

            List<Pagamento> la = new ArrayList<Pagamento>();

            rep.findAll().forEach(la::add);
           
            if (la.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(la, HttpStatus.OK);

        } catch (Exception e){
            // TODO: handle exception
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Método POST /api/pagamentos para criar um novo pagamento
    @PostMapping("/pagamentos")
    public ResponseEntity<Pagamento> createPagamento(@RequestBody Pagamento pg) {

        try {
            Pagamento p = rep.save(new Pagamento(pg.getAno(), pg.getMes(), pg.getValor(), pg.getCod_jogador()));

            return new ResponseEntity<>(p, HttpStatus.CREATED);

        } catch (Exception e){
            // TODO: handle exception
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Método PUT /api/pagamentos para editar um pagamento
    @PutMapping("/pagamentos/{cod_pagamento}")
    public ResponseEntity<Pagamento> updatePagamento(@PathVariable("cod_pagamento") int cod_pagamento, @RequestBody Pagamento p)
    {
        Optional<Pagamento> data = rep.findById(cod_pagamento);

        if (data.isPresent())
        {
            Pagamento pg = data.get();
            pg.setAno(p.getAno());
            pg.setMes(p.getMes());
            pg.setValor(p.getValor());
            pg.setCod_jogador(p.getCod_jogador());

            return new ResponseEntity<>(rep.save(pg), HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    // Método DELETE /api/pagamentos para deletar um codigo de um pagamentos especifico
    @DeleteMapping("/pagamentos/{cod_pagamento}")
    public ResponseEntity<HttpStatus> deletePagamento(@PathVariable("cod_pagamento") int cod_pagamento) {
        try {
            rep.deleteById(cod_pagamento);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            
        } catch (Exception e) {
            //TODO: handle exception
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
    }

    // Método DELETE /api/pagamentos para deletar todos os registros do BD
    @DeleteMapping("/pagamentos")
    public ResponseEntity<HttpStatus> deleteAllPagamentos() {
        try {
            rep.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            
        } catch (Exception e) {
            //TODO: handle exception
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
    }
    
}
