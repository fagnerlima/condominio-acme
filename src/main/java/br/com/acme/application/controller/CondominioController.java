package br.com.acme.application.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;

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
import br.com.acme.domain.model.condominio.Condominio;
import br.com.acme.domain.service.CondominioService;
import br.com.acme.infrastructure.facade.ModelMapperFacade;
import br.com.acme.presentation.dto.aviso.AvisoReducedResponseTO;
import br.com.acme.presentation.dto.aviso.AvisoResponseTO;
import br.com.acme.presentation.dto.condominio.CondominioReducedResponseTO;
import br.com.acme.presentation.dto.condominio.CondominioRequestTO;
import br.com.acme.presentation.dto.condominio.CondominioResponseTO;
import br.com.acme.presentation.dto.multa.MultaReducedResponseTO;
import br.com.acme.presentation.dto.multa.MultaResponseTO;

@RestController
@RequestMapping("/condominios")
public class CondominioController {

    private CondominioService condominioService;

    private ModelMapperFacade modelMapperFacade;

    public CondominioController(CondominioService condominioService, ModelMapperFacade modelMapperFacade) {
        this.condominioService = condominioService;
        this.modelMapperFacade = modelMapperFacade;
    }

    public static void addSelfLink(CondominioResponseTO condominio) {
        condominio.add(createSelfLink(condominio.getId()));

        List<AvisoResponseTO> avisos = condominio.getAvisos();
        List<MultaResponseTO> multas = condominio.getMultasAplicadas();

        if (avisos != null && !avisos.isEmpty()) {
            avisos.stream().forEach(aviso -> aviso.add(AvisoController.createSelfLink(aviso.getId())));
        }

        if (multas != null && !multas.isEmpty()) {
            multas.stream().forEach(multa -> multa.add(MultaController.createSelfLink(multa.getId())));
        }
    }

    public static void addSelfLink(CondominioReducedResponseTO condominio) {
        condominio.add(createSelfLink(condominio.getId()));

        List<AvisoReducedResponseTO> avisos = condominio.getAvisos();
        List<MultaReducedResponseTO> multas = condominio.getMultasAplicadas();

        if (avisos != null && !avisos.isEmpty()) {
            avisos.stream().forEach(aviso -> aviso.add(AvisoController.createSelfLink(aviso.getId())));
        }

        if (multas != null && !multas.isEmpty()) {
            multas.stream().forEach(multa -> multa.add(MultaController.createSelfLink(multa.getId())));
        }
    }

    public static Link createSelfLink(Long id) {
        return linkTo(methodOn(CondominioController.class)
                .findById(id))
                .withSelfRel();
    }

    @GetMapping
    public ResponseEntity<Page<CondominioReducedResponseTO>> findAll(Pageable pageable) {
        Page<Condominio> condominiosPage = condominioService.findAll(pageable);
        Page<CondominioReducedResponseTO> condominiosPageResponseTO = modelMapperFacade.map(condominiosPage,
                CondominioReducedResponseTO.class);

        condominiosPageResponseTO.stream().forEach(condominioResponseTO -> {
            addSelfLink(condominioResponseTO);

            List<AvisoReducedResponseTO> avisos = condominioResponseTO.getAvisos();

            if (avisos != null && !avisos.isEmpty()) {
                avisos.stream().forEach(aviso -> AvisoController.addSelfLink(aviso));
            }
        });

        return ResponseEntityFacade.ok(condominiosPageResponseTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CondominioResponseTO> findById(@PathVariable Long id) {
        Condominio condominio = condominioService.findById(id);
        CondominioResponseTO condominioResponseTO = modelMapperFacade.map(condominio, CondominioResponseTO.class);

        addSelfLink(condominioResponseTO);

        return ResponseEntityFacade.ok(condominioResponseTO);
    }

    @PostMapping
    public ResponseEntity<CondominioResponseTO> save(@RequestBody CondominioRequestTO condominioRequestTO) {
        Condominio condominio = modelMapperFacade.map(condominioRequestTO, Condominio.class);
        Condominio condominioSaved = condominioService.save(condominio);
        CondominioResponseTO condominioResponseTO = modelMapperFacade.map(condominioSaved, CondominioResponseTO.class);

        addSelfLink(condominioResponseTO);

        return ResponseEntityFacade.created(condominioResponseTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CondominioResponseTO> update(@PathVariable Long id,
            @RequestBody CondominioRequestTO condominioRequestTO) {
        Condominio condominio = modelMapperFacade.map(condominioRequestTO, Condominio.class);
        Condominio condominioUpdated = condominioService.update(id, condominio);
        CondominioResponseTO condominioResponseTO = modelMapperFacade.map(condominioUpdated, CondominioResponseTO.class);

        addSelfLink(condominioResponseTO);

        return ResponseEntityFacade.ok(condominioResponseTO);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        condominioService.delete(id);
    }

}
