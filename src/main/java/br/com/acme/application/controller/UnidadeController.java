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
import br.com.acme.domain.model.unidade.Unidade;
import br.com.acme.domain.service.UnidadeService;
import br.com.acme.infrastructure.facade.ModelMapperFacade;
import br.com.acme.presentation.dto.multa.MultaReducedResponseTO;
import br.com.acme.presentation.dto.multa.MultaResponseTO;
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

    public static void addSelfLink(UnidadeResponseTO unidade) {
        unidade.add(createSelfLink(unidade.getId()));

        List<MultaResponseTO> multas = unidade.getMultasUnidade();

        if (multas != null && !multas.isEmpty()) {
            multas.stream().forEach(multaUnidade -> multaUnidade.add(MultaController.createSelfLink(multaUnidade.getId())));
        }
    }

    public static void addSelfLink(UnidadeReducedResponseTO unidade) {
        unidade.add(createSelfLink(unidade.getId()));

        List<MultaReducedResponseTO> multas = unidade.getMultasUnidade();

        if (multas != null && !multas.isEmpty()) {
            multas.stream().forEach(multaUnidade -> multaUnidade.add(MultaController.createSelfLink(multaUnidade.getId())));
        }
    }

    public static Link createSelfLink(Long id) {
        return linkTo(methodOn(UnidadeController.class)
                .findById(id))
                .withSelfRel();
    }

    @GetMapping
    public ResponseEntity<Page<UnidadeReducedResponseTO>> findAll(Pageable pageable) {
        Page<Unidade> unidadesPage = unidadeService.findAll(pageable);

        return mapPageResponse(unidadesPage);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UnidadeResponseTO> findById(@PathVariable Long id) {
        Unidade unidade = unidadeService.findById(id);
        UnidadeResponseTO unidadeResponseTO = modelMapperFacade.map(unidade, UnidadeResponseTO.class);

        addSelfLink(unidadeResponseTO);

        return ResponseEntityFacade.ok(unidadeResponseTO);
    }

    @GetMapping("/com-multas")
    public ResponseEntity<Page<UnidadeReducedResponseTO>> findAllComMultas(Pageable pageable) {
        Page<Unidade> unidadesPage = unidadeService.findAllComMultas(pageable);

        return mapPageResponse(unidadesPage);
    }

    @GetMapping("/sem-multas")
    public ResponseEntity<Page<UnidadeReducedResponseTO>> findAllSemMultas(Pageable pageable) {
        Page<Unidade> unidadesPage = unidadeService.findAllSemMultas(pageable);

        return mapPageResponse(unidadesPage);
    }

    @PostMapping
    public ResponseEntity<UnidadeResponseTO> save(@RequestBody UnidadeRequestTO unidadeRequestTO) {
        Unidade unidade = modelMapperFacade.map(unidadeRequestTO, Unidade.class);
        Unidade unidadeSaved = unidadeService.save(unidade);
        UnidadeResponseTO unidadeResponseTO = modelMapperFacade.map(unidadeSaved, UnidadeResponseTO.class);

        addSelfLink(unidadeResponseTO);

        return ResponseEntityFacade.created(unidadeResponseTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UnidadeResponseTO> update(@PathVariable Long id, @RequestBody UnidadeRequestTO unidadeRequestTO) {
        Unidade unidade = modelMapperFacade.map(unidadeRequestTO, Unidade.class);
        Unidade unidadeUpdated = unidadeService.update(id, unidade);
        UnidadeResponseTO unidadeResponseTO = modelMapperFacade.map(unidadeUpdated, UnidadeResponseTO.class);

        addSelfLink(unidadeResponseTO);

        return ResponseEntityFacade.ok(unidadeResponseTO);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        unidadeService.delete(id);
    }

    private ResponseEntity<Page<UnidadeReducedResponseTO>> mapPageResponse(Page<Unidade> unidadesPage) {
        Page<UnidadeReducedResponseTO> unidadesPageResponseTO = modelMapperFacade.map(unidadesPage, UnidadeReducedResponseTO.class);

        unidadesPageResponseTO.stream().forEach(unidadeResponseTO -> {
            addSelfLink(unidadeResponseTO);

            List<MultaReducedResponseTO> multasUnidade = unidadeResponseTO.getMultasUnidade();

            if (multasUnidade != null && !multasUnidade.isEmpty()) {
                multasUnidade.stream().forEach(multaUnidade -> MultaController.addSelfLink(multaUnidade));
            }
        });

        return ResponseEntityFacade.ok(unidadesPageResponseTO);
    }

}
