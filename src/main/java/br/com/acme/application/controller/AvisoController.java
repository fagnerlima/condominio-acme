package br.com.acme.application.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.Link;
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

import br.com.acme.application.facade.ResponseEntityFacade;
import br.com.acme.domain.model.aviso.Aviso;
import br.com.acme.domain.service.AvisoService;
import br.com.acme.infrastructure.facade.ModelMapperFacade;
import br.com.acme.presentation.dto.aviso.AvisoReducedResponseTO;
import br.com.acme.presentation.dto.aviso.AvisoRequestTO;
import br.com.acme.presentation.dto.aviso.AvisoResponseTO;
import br.com.acme.presentation.dto.condominio.CondominioResponseTO;
import br.com.acme.presentation.dto.unidade.UnidadeResponseTO;

@RestController
@RequestMapping("/avisos")
public class AvisoController {

    private AvisoService avisoService;

    private ModelMapperFacade modelMapperFacade;

    public AvisoController(AvisoService avisoService, ModelMapperFacade modelMapperFacade) {
        this.avisoService = avisoService;
        this.modelMapperFacade = modelMapperFacade;
    }

    public static void addSelfLink(AvisoResponseTO aviso) {
        aviso.add(createSelfLink(aviso.getId()));

        CondominioResponseTO condominio = aviso.getCondominioAvisos();
        UnidadeResponseTO unidade = aviso.getUnidadeAviso();

        if (condominio != null) {
            condominio.add(CondominioController.createSelfLink(condominio.getId()));
        }

        if (unidade != null) {
            unidade.add(UnidadeController.createSelfLink(unidade.getId()));
        }
    }

    public static void addSelfLink(AvisoReducedResponseTO aviso) {
        aviso.add(createSelfLink(aviso.getId()));
    }

    public static Link createSelfLink(Long id) {
        return linkTo(methodOn(AvisoController.class)
                .findById(id))
                .withSelfRel();
    }

    @GetMapping
    public ResponseEntity<Page<AvisoReducedResponseTO>> findAll(Pageable pageable) {
        Page<Aviso> avisosPage = avisoService.findAll(pageable);
        Page<AvisoReducedResponseTO> avisosPageResponseTO = modelMapperFacade.map(avisosPage, AvisoReducedResponseTO.class);

        avisosPageResponseTO.stream().forEach(avisoResponseTO -> addSelfLink(avisoResponseTO));

        return ResponseEntityFacade.ok(avisosPageResponseTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AvisoResponseTO> findById(@PathVariable Long id) {
        Aviso aviso = avisoService.findById(id);
        AvisoResponseTO avisoResponseTO = modelMapperFacade.map(aviso, AvisoResponseTO.class);

        addSelfLink(avisoResponseTO);

        return ResponseEntityFacade.ok(avisoResponseTO);
    }

    @PostMapping
    public ResponseEntity<AvisoResponseTO> save(@RequestBody AvisoRequestTO avisoRequestTO) {
        Aviso aviso = modelMapperFacade.map(avisoRequestTO, Aviso.class);
        Aviso avisoSaved = avisoService.save(aviso);
        AvisoResponseTO avisoResponseTO = modelMapperFacade.map(avisoSaved, AvisoResponseTO.class);

        addSelfLink(avisoResponseTO);

        return ResponseEntityFacade.created(avisoResponseTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AvisoResponseTO> update(@PathVariable Long id, @RequestBody AvisoRequestTO avisoRequestTO) {
        Aviso aviso = modelMapperFacade.map(avisoRequestTO, Aviso.class);
        Aviso avisoUpdated = avisoService.update(id, aviso);
        AvisoResponseTO avisoResponseTO = modelMapperFacade.map(avisoUpdated, AvisoResponseTO.class);

        addSelfLink(avisoResponseTO);

        return ResponseEntityFacade.ok(avisoResponseTO);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        avisoService.delete(id);
    }

}
