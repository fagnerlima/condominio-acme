package br.com.acme.application.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.acme.application.factory.ResponseFactory;
import br.com.acme.domain.model.unidade.Unidade;
import br.com.acme.domain.service.UnidadeService;
import br.com.acme.presentation.dto.shared.ResponseTO;

@RestController
@RequestMapping("/unidades")
public class UnidadeController {

    private UnidadeService unidadeService;

    public UnidadeController(UnidadeService unidadeService) {
        this.unidadeService = unidadeService;
    }

    @GetMapping
    public ResponseEntity<ResponseTO<Page<Unidade>>> findAll(Pageable pageable) {
        Page<Unidade> unidadesPage = unidadeService.findAll(pageable);

        return ResponseFactory.ok(unidadesPage);
    }

    @PostMapping
    public ResponseEntity<ResponseTO<Unidade>> save(@RequestBody Unidade unidade) {
        Unidade unidadeSaved = unidadeService.save(unidade);

        return ResponseFactory.created(unidadeSaved);
    }

}
