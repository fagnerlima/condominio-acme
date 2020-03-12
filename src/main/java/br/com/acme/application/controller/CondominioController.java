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
import br.com.acme.domain.model.condominio.Condominio;
import br.com.acme.domain.service.CondominioService;
import br.com.acme.infrastructure.facade.ModelMapperFacade;
import br.com.acme.presentation.dto.condominio.CondominioReducedResponseTO;
import br.com.acme.presentation.dto.condominio.CondominioRequestTO;
import br.com.acme.presentation.dto.condominio.CondominioResponseTO;
import br.com.acme.presentation.dto.shared.ResponseTO;

@RestController
@RequestMapping("/condominios")
public class CondominioController {

    private CondominioService condominioService;

    private ModelMapperFacade modelMapperFacade;

    public CondominioController(CondominioService condominioService, ModelMapperFacade modelMapperFacade) {
        this.condominioService = condominioService;
        this.modelMapperFacade = modelMapperFacade;
    }

    @GetMapping
    public ResponseEntity<ResponseTO<Page<CondominioReducedResponseTO>>> findAll(Pageable pageable) {
        Page<Condominio> condominiosPage = condominioService.findAll(pageable);
        Page<CondominioReducedResponseTO> condominiosPageResponseTO = modelMapperFacade.map(condominiosPage,
                CondominioReducedResponseTO.class);

        return ResponseEntityFactory.ok(condominiosPageResponseTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseTO<CondominioResponseTO>> findById(@PathVariable Long id) {
        Condominio condominio = condominioService.findById(id);
        CondominioResponseTO condominioResponseTO = modelMapperFacade.map(condominio, CondominioResponseTO.class);

        return ResponseEntityFactory.ok(condominioResponseTO);
    }

    @PostMapping
    public ResponseEntity<ResponseTO<CondominioResponseTO>> save(@RequestBody CondominioRequestTO condominioRequestTO) {
        Condominio condominio = modelMapperFacade.map(condominioRequestTO, Condominio.class);
        Condominio condominioSaved = condominioService.save(condominio);
        CondominioResponseTO condominioResponseTO = modelMapperFacade.map(condominioSaved, CondominioResponseTO.class);

        return ResponseEntityFactory.created(condominioResponseTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseTO<CondominioResponseTO>> update(@PathVariable Long id,
            @RequestBody CondominioRequestTO condominioRequestTO) {
        Condominio condominio = modelMapperFacade.map(condominioRequestTO, Condominio.class);
        Condominio condominioUpdated = condominioService.update(id, condominio);
        CondominioResponseTO condominioResponseTO = modelMapperFacade.map(condominioUpdated, CondominioResponseTO.class);

        return ResponseEntityFactory.ok(condominioResponseTO);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        condominioService.delete(id);
    }

}
