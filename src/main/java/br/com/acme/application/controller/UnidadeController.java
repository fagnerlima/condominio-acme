package br.com.acme.application.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.acme.application.factory.ResponseEntityFactory;
import br.com.acme.domain.model.unidade.Unidade;
import br.com.acme.domain.service.UnidadeService;
import br.com.acme.infrastructure.facade.ModelMapperFacade;
import br.com.acme.presentation.dto.shared.ResponseTO;
import br.com.acme.presentation.dto.unidade.UnidadeReducedResponseTO;
import br.com.acme.presentation.dto.unidade.UnidadeRequestTO;
import br.com.acme.presentation.dto.unidade.UnidadeResponseTO;

@RestController
@RequestMapping("/unidades")
public class UnidadeController {

    private UnidadeService unidadeService;

    private ModelMapperFacade modelMapperFacade;

    public UnidadeController(UnidadeService unidadeService, ModelMapperFacade modelMapperFacade) {
        this.unidadeService = unidadeService;
        this.modelMapperFacade = modelMapperFacade;
    }

    @GetMapping
    public ResponseEntity<ResponseTO<Page<UnidadeReducedResponseTO>>> findAll(Pageable pageable) {
        Page<Unidade> unidadesPage = unidadeService.findAll(pageable);

        return mapPageResponse(unidadesPage);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseTO<UnidadeResponseTO>> findById(@PathVariable Long id) {
        Unidade unidade = unidadeService.findById(id);
        UnidadeResponseTO unidadeResponseTO = modelMapperFacade.map(unidade, UnidadeResponseTO.class);

        return ResponseEntityFactory.ok(unidadeResponseTO);
    }

    @GetMapping("/com-multas")
    public ResponseEntity<ResponseTO<Page<UnidadeReducedResponseTO>>> findAllComMultas(Pageable pageable) {
        Page<Unidade> unidadesPage = unidadeService.findAllComMultas(pageable);

        return mapPageResponse(unidadesPage);
    }

    @GetMapping("/sem-multas")
    public ResponseEntity<ResponseTO<Page<UnidadeReducedResponseTO>>> findAllSemMultas(Pageable pageable) {
        Page<Unidade> unidadesPage = unidadeService.findAllSemMultas(pageable);

        return mapPageResponse(unidadesPage);
    }

    @PostMapping
    public ResponseEntity<ResponseTO<UnidadeResponseTO>> save(@RequestBody UnidadeRequestTO unidadeRequestTO) {
        Unidade unidade = modelMapperFacade.map(unidadeRequestTO, Unidade.class);
        Unidade unidadeSaved = unidadeService.save(unidade);
        UnidadeResponseTO unidadeResponseTO = modelMapperFacade.map(unidadeSaved, UnidadeResponseTO.class);

        return ResponseEntityFactory.created(unidadeResponseTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseTO<UnidadeResponseTO>> update(@PathVariable Long id, @RequestBody UnidadeRequestTO unidadeRequestTO) {
        Unidade unidade = modelMapperFacade.map(unidadeRequestTO, Unidade.class);
        Unidade unidadeUpdated = unidadeService.update(id, unidade);
        UnidadeResponseTO unidadeResponseTO = modelMapperFacade.map(unidadeUpdated, UnidadeResponseTO.class);

        return ResponseEntityFactory.ok(unidadeResponseTO);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        unidadeService.delete(id);
    }

    private ResponseEntity<ResponseTO<Page<UnidadeReducedResponseTO>>> mapPageResponse(Page<Unidade> unidadesPage) {
        Page<UnidadeReducedResponseTO> unidadesPageResponseTO = modelMapperFacade.map(unidadesPage, UnidadeReducedResponseTO.class);

        return ResponseEntityFactory.ok(unidadesPageResponseTO);
    }

}
