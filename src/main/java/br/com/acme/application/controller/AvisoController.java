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
import br.com.acme.domain.model.aviso.Aviso;
import br.com.acme.domain.service.AvisoService;
import br.com.acme.infrastructure.facade.ModelMapperFacade;
import br.com.acme.presentation.dto.aviso.AvisoReducedResponseTO;
import br.com.acme.presentation.dto.aviso.AvisoRequestTO;
import br.com.acme.presentation.dto.aviso.AvisoResponseTO;
import br.com.acme.presentation.dto.shared.ResponseTO;

@RestController
@RequestMapping("/avisos")
public class AvisoController {

    private AvisoService avisoService;

    private ModelMapperFacade modelMapperFacade;

    public AvisoController(AvisoService avisoService, ModelMapperFacade modelMapperFacade) {
        this.avisoService = avisoService;
        this.modelMapperFacade = modelMapperFacade;
    }

    @GetMapping
    public ResponseEntity<ResponseTO<Page<AvisoReducedResponseTO>>> findAll(Pageable pageable) {
        Page<Aviso> avisosPage = avisoService.findAll(pageable);
        Page<AvisoReducedResponseTO> avisosPageResponseTO = modelMapperFacade.map(avisosPage, AvisoReducedResponseTO.class);

        return ResponseEntityFactory.ok(avisosPageResponseTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseTO<AvisoResponseTO>> findById(@PathVariable Long id) {
        Aviso aviso = avisoService.findById(id);
        AvisoResponseTO avisoResponseTO = modelMapperFacade.map(aviso, AvisoResponseTO.class);

        return ResponseEntityFactory.ok(avisoResponseTO);
    }

    @PostMapping
    public ResponseEntity<ResponseTO<AvisoResponseTO>> save(@RequestBody AvisoRequestTO avisoRequestTO) {
        Aviso aviso = modelMapperFacade.map(avisoRequestTO, Aviso.class);
        Aviso avisoSaved = avisoService.save(aviso);
        AvisoResponseTO avisoResponseTO = modelMapperFacade.map(avisoSaved, AvisoResponseTO.class);

        return ResponseEntityFactory.created(avisoResponseTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseTO<AvisoResponseTO>> update(@PathVariable Long id, @RequestBody AvisoRequestTO avisoRequestTO) {
        Aviso aviso = modelMapperFacade.map(avisoRequestTO, Aviso.class);
        Aviso avisoUpdated = avisoService.update(id, aviso);
        AvisoResponseTO avisoResponseTO = modelMapperFacade.map(avisoUpdated, AvisoResponseTO.class);

        return ResponseEntityFactory.ok(avisoResponseTO);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        avisoService.delete(id);
    }

}
