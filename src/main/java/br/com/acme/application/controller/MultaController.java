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
import br.com.acme.domain.model.multas.Multa;
import br.com.acme.domain.service.MultaService;
import br.com.acme.infrastructure.facade.ModelMapperFacade;
import br.com.acme.presentation.dto.condominio.CondominioResponseTO;
import br.com.acme.presentation.dto.multa.MultaReducedResponseTO;
import br.com.acme.presentation.dto.multa.MultaRequestTO;
import br.com.acme.presentation.dto.multa.MultaResponseTO;
import br.com.acme.presentation.dto.unidade.UnidadeReducedResponseTO;
import br.com.acme.presentation.dto.unidade.UnidadeResponseTO;

@RestController
@RequestMapping("/multas")
public class MultaController {

    private MultaService multaService;

    private ModelMapperFacade modelMapperFacade;

    public MultaController(MultaService multaService, ModelMapperFacade modelMapperFacade) {
        this.multaService = multaService;
        this.modelMapperFacade = modelMapperFacade;
    }

    public static void addSelfLink(MultaResponseTO multa) {
        multa.add(createSelfLink(multa.getId()));

        UnidadeResponseTO unidade = multa.getUnidadeMulta();
        CondominioResponseTO condominio = multa.getCondominioMulta();

        if (unidade != null) {
            unidade.add(UnidadeController.createSelfLink(unidade.getId()));
        }

        if (condominio != null) {
            condominio.add(CondominioController.createSelfLink(condominio.getId()));
        }
    }

    public static void addSelfLink(MultaReducedResponseTO multa) {
        multa.add(createSelfLink(multa.getId()));

        UnidadeReducedResponseTO unidade = multa.getUnidadeMulta();

        if (unidade != null) {
            unidade.add(UnidadeController.createSelfLink(unidade.getId()));
        }
    }

    public static Link createSelfLink(Long id) {
        return linkTo(methodOn(MultaController.class)
                .findById(id))
                .withSelfRel();
    }

    @GetMapping
    public ResponseEntity<Page<MultaReducedResponseTO>> findAll(Pageable pageable) {
        Page<Multa> multasPage = multaService.findAll(pageable);
        Page<MultaReducedResponseTO> multasPageResponseTO = modelMapperFacade.map(multasPage, MultaReducedResponseTO.class);

        multasPageResponseTO.stream().forEach(multaResponseTO -> addSelfLink(multaResponseTO));

        return ResponseEntityFacade.ok(multasPageResponseTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MultaResponseTO> findById(@PathVariable Long id) {
        Multa multa = multaService.findById(id);
        MultaResponseTO multaResponseTO = modelMapperFacade.map(multa, MultaResponseTO.class);

        addSelfLink(multaResponseTO);

        return ResponseEntityFacade.ok(multaResponseTO);
    }

    @PostMapping
    public ResponseEntity<MultaResponseTO> save(@RequestBody MultaRequestTO multaRequestTO) {
        Multa multa = modelMapperFacade.map(multaRequestTO, Multa.class);
        Multa multaSaved = multaService.save(multa);
        MultaResponseTO multaResponseTO = modelMapperFacade.map(multaSaved, MultaResponseTO.class);

        addSelfLink(multaResponseTO);

        return ResponseEntityFacade.created(multaResponseTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MultaResponseTO> update(@PathVariable Long id, @RequestBody MultaRequestTO multaRequestTO) {
        Multa multa = modelMapperFacade.map(multaRequestTO, Multa.class);
        Multa multaUpdated = multaService.update(id, multa);
        MultaResponseTO multaResponseTO = modelMapperFacade.map(multaUpdated, MultaResponseTO.class);

        addSelfLink(multaResponseTO);

        return ResponseEntityFacade.ok(multaResponseTO);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        multaService.delete(id);
    }

}
