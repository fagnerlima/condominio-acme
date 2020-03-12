package br.com.acme.application.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.acme.application.factory.ResponseEntityFactory;
import br.com.acme.domain.model.multas.Multa;
import br.com.acme.domain.service.MultaService;
import br.com.acme.infrastructure.facade.ModelMapperFacade;
import br.com.acme.presentation.dto.multa.MultaReducedResponseTO;
import br.com.acme.presentation.dto.multa.MultaResponseTO;
import br.com.acme.presentation.dto.shared.ResponseTO;

@RestController
@RequestMapping("/multas")
public class MultaController {

    private MultaService multaService;

    private ModelMapperFacade modelMapperFacade;

    public MultaController(MultaService multaService, ModelMapperFacade modelMapperFacade) {
        this.multaService = multaService;
        this.modelMapperFacade = modelMapperFacade;
    }

    @GetMapping
    public ResponseEntity<ResponseTO<Page<MultaReducedResponseTO>>> findAll(Pageable pageable) {
        Page<Multa> multasPage = multaService.findAll(pageable);
        Page<MultaReducedResponseTO> multasPageResponseTO = modelMapperFacade.map(multasPage, MultaReducedResponseTO.class);

        return ResponseEntityFactory.ok(multasPageResponseTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseTO<MultaResponseTO>> findById(@PathVariable Long id) {
        Multa multa = multaService.findById(id);
        MultaResponseTO multaResponseTO = modelMapperFacade.map(multa, MultaResponseTO.class);

        return ResponseEntityFactory.ok(multaResponseTO);
    }

    @PostMapping
    public ResponseEntity<ResponseTO<MultaResponseTO>> save(@RequestBody Multa multa) {
        Multa multaSaved = multaService.save(multa);
        MultaResponseTO multaResponseTO = modelMapperFacade.map(multaSaved, MultaResponseTO.class);

        return ResponseEntityFactory.created(multaResponseTO);
    }

}
