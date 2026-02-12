package com.cingo.moedas_devs.controller;

import com.cingo.moedas_devs.dto.TransferenciaRequest;
import com.cingo.moedas_devs.model.Dev;
import com.cingo.moedas_devs.service.DevService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/devs")
@CrossOrigin(origins = "*")
public class DevController {

    private final DevService devService;

    public DevController(DevService devService) {
        this.devService = devService;
    }

    @PostMapping
    public ResponseEntity<Dev> criarDev(@RequestBody Dev dev) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(devService.criarDev(dev));
    }

    @GetMapping
    public ResponseEntity<List<Dev>> listarTodos() {
        return ResponseEntity.ok(devService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Dev> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(devService.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Dev> atualizarDev(@PathVariable Long id,
                                            @RequestBody Dev devAtualizado) {
        return ResponseEntity.ok(devService.atualizarDev(id, devAtualizado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarDev(@PathVariable Long id) {
        devService.deletarDev(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/transferir")
    public ResponseEntity<?> transferir(@RequestBody TransferenciaRequest request) {
        try {
            devService.transferirMoedas(request);
            return ResponseEntity.ok("Transferência realizada com sucesso!");
        }
        catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/{id}/batata-quente")
    public ResponseEntity<Dev> incrementarBatataQuente(@PathVariable Long id) {
        return ResponseEntity.ok(devService.incrementarBatataQuente(id));
    }

    @GetMapping("/batata-quente/menor")
    public ResponseEntity<Dev> buscarMenorBatataQuente() {
        return ResponseEntity.ok(devService.buscarDevComMenorBatataQuente());
    }
}
