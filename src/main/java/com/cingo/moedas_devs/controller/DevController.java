package com.cingo.moedas_devs.controller;

import com.cingo.moedas_devs.dto.TransferenciaRequest;
import com.cingo.moedas_devs.model.Dev;
import com.cingo.moedas_devs.service.DevService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/devs")
@CrossOrigin(origins = "*")
public class DevController {

    @Autowired
    private DevService devService;

    @PostMapping
    public ResponseEntity<Dev> criarDev(@RequestBody Dev dev) {
        Dev novoDev = devService.criarDev(dev);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoDev);
    }

    @GetMapping
    public ResponseEntity<List<Dev>> listarTodos() {
        return ResponseEntity.ok(devService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        Dev dev = devService.buscarPorId(id);
        return (dev != null)
                ? ResponseEntity.ok(dev)
                : ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarDev(@PathVariable Long id, @RequestBody Dev devAtualizado) {
        Dev atualizado = devService.atualizarDev(id, devAtualizado);
        return (atualizado != null)
                ? ResponseEntity.ok(atualizado)
                : ResponseEntity.notFound().build();
    }

    @PostMapping("/transferir")
    public ResponseEntity<?> transferir(@RequestBody TransferenciaRequest request) {
        String result = devService.transferirMoedas(request);
        if (result.contains("sucesso")) return ResponseEntity.ok(result);
        else return ResponseEntity.badRequest().body(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable Long id) {
        boolean removido = devService.deletarDev(id);
        return removido
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }

    @PostMapping("/{id}/batata-quente")
    public ResponseEntity<?> incrementarBatata(@PathVariable Long id) {
        Dev dev = devService.incrementarBatataQuente(id);
        return (dev != null)
                ? ResponseEntity.ok(dev)
                : ResponseEntity.notFound().build();
    }

    @GetMapping("/batata-quente/menor")
    public ResponseEntity<?> menorBatata() {
        Dev dev = devService.buscarMenorBatataQuente();
        return (dev != null)
                ? ResponseEntity.ok(dev)
                : ResponseEntity.badRequest().body("Nenhum dev cadastrado");
    }
}
