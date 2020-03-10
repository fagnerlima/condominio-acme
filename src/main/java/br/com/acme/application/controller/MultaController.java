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
import br.com.acme.domain.model.multas.Multa;
import br.com.acme.domain.service.MultaService;
import br.com.acme.presentation.dto.shared.ResponseTO;

@RestController
@RequestMapping("/multas")
public class MultaController {

    private MultaService multaService;

    public MultaController(MultaService multaService) {
        this.multaService = multaService;
    }

    @GetMapping
    public ResponseEntity<ResponseTO<Page<Multa>>> findAll(Pageable pageable) {
        Page<Multa> multasPage = multaService.findAll(pageable);

        return ResponseFactory.ok(multasPage);
    }

    @PostMapping
    public ResponseEntity<ResponseTO<Multa>> save(@RequestBody Multa multa) {
        Multa multaSaved = multaService.save(multa);

        return ResponseFactory.created(multaSaved);
    }

}
