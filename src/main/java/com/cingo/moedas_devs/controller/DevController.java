package com.cingo.moedas_devs.controller;

import com.cingo.moedas_devs.dto.TransferenciaRequest;
import com.cingo.moedas_devs.model.Dev;
import com.cingo.moedas_devs.repository.DevRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller - Recebe as requisições HTTP e retorna as respostas
 */
@RestController
@RequestMapping("/api/devs")
@CrossOrigin(origins = "*")
public class DevController {

    @Autowired
    private DevRepository devRepository;

    // CREATE - Criar um novo dev
    // POST /api/devs
    @PostMapping
    public ResponseEntity<Dev> criarDev(@RequestBody Dev dev) {
        Dev novodev = devRepository.save(dev);
        return ResponseEntity.status(HttpStatus.CREATED).body(novodev);
    }

    // READ - Listar todos os devs
    // GET /api/devs
    @GetMapping
    public ResponseEntity<List<Dev>> listarTodos() {
        List<Dev> devs = devRepository.findAll();
        return ResponseEntity.ok(devs);
    }

    // READ - Buscar um dev específico por ID
    // GET /api/devs/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Dev> buscarPorId(@PathVariable Long id) {
        return devRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // UPDATE - Atualizar nome do dev
    // PUT /api/devs/{id}
    @PutMapping("/{id}")
    public ResponseEntity<Dev> atualizarDev(@PathVariable Long id, @RequestBody Dev devAtualizado) {
        return devRepository.findById(id)
                .map(dev -> {
                    dev.setNome(devAtualizado.getNome());
                    if (devAtualizado.getMoedas() != null) {
                        dev.setMoedas(devAtualizado.getMoedas());
                    }
                    Dev devSalvo = devRepository.save(dev);
                    return ResponseEntity.ok(devSalvo);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // TRANSFERIR MOEDAS - Um dev paga outro dev
    // POST /api/devs/transferir
    @PostMapping("/transferir")
    public ResponseEntity<?> transferirMoedas(@RequestBody TransferenciaRequest request) {
        // Buscar os devs
        Dev pagador = devRepository.findById(request.getDevPagadorId()).orElse(null);
        Dev recebedor = devRepository.findById(request.getDevRecebedorId()).orElse(null);

        // Validações
        if (pagador == null) {
            return ResponseEntity.badRequest().body("Dev pagador não encontrado");
        }

        if (recebedor == null) {
            return ResponseEntity.badRequest().body("Dev recebedor não encontrado");
        }

        if (pagador.getId().equals(recebedor.getId())) {
            return ResponseEntity.badRequest().body("Um dev não pode pagar a si mesmo");
        }

        // Realizar transferência
        pagador.removerMoeda();
        recebedor.adicionarMoeda();

        devRepository.save(pagador);
        devRepository.save(recebedor);

        return ResponseEntity.ok("Transferência realizada com sucesso!");
    }

    // DELETE - Deletar um dev
    // DELETE /api/devs/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarDev(@PathVariable Long id) {
        if (devRepository.existsById(id)) {
            devRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    // INCREMENTAR BATATA QUENTE
    // POST /api/devs/{id}/batata-quente
    @PostMapping("/{id}/batata-quente")
    public ResponseEntity<?> incrementarBatataQuente(@PathVariable Long id) {
        return devRepository.findById(id)
                .map(dev -> {
                    dev.incrementarBatataQuente();
                    Dev devSalvo = devRepository.save(dev);
                    return ResponseEntity.ok(devSalvo);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/batata-quente/menor")
    public ResponseEntity<?> buscarBatataQuenteMenor() {
        List<Dev> devs = devRepository.findAll();

        if (devs.isEmpty()) {
            return ResponseEntity.badRequest().body("Nenhum dev cadastrado");
        }

        // Encontrar o dev com menor batataQuente, usando id como critério de desempate
        Dev devComMenorBatata = devs.stream()
                .min((d1, d2) -> {
                    int comparacao = d1.getBatataQuente().compareTo(d2.getBatataQuente());
                    if (comparacao == 0) {
                        return d1.getId().compareTo(d2.getId());
                    }
                    return comparacao;
                })
                .orElse(null);

        return ResponseEntity.ok(devComMenorBatata);
    }
}