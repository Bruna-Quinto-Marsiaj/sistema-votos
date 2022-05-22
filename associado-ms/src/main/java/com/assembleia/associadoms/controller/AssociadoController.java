package com.assembleia.associadoms.controller;

import com.assembleia.associadoms.domain.Associado;
import com.assembleia.associadoms.exception.AssociadoCadastradoException;
import com.assembleia.associadoms.service.AssociadoService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "/associado")
@AllArgsConstructor
public class AssociadoController {

    @Autowired
    private final AssociadoService service;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Associado> cadastrar(@RequestParam String cpf) throws AssociadoCadastradoException {
        Associado associado = service.findByCpf(cpf);

        if (associado == null) {
            Associado novoAssociado = new Associado(null, cpf, null);
            return ResponseEntity.ok(service.cadastrar(novoAssociado));
        } else {
            throw new AssociadoCadastradoException();
        }
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Associado> findByCpf(@RequestParam String cpf) {
        return ResponseEntity.ok().body(service.findByCpf(cpf));
    }
}
