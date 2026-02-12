package com.cingo.moedas_devs.service;

import com.cingo.moedas_devs.dto.TransferenciaRequest;
import com.cingo.moedas_devs.model.Dev;
import com.cingo.moedas_devs.repository.DevRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class DevService {

    private final DevRepository devRepository;

    public DevService(DevRepository devRepository) {
        this.devRepository = devRepository;
    }

    public Dev criarDev(Dev dev) {
        return devRepository.save(dev);
    }

    public List<Dev> listarTodos() {
        return devRepository.findAll();
    }

    public Dev buscarPorId(Long id) {
        return devRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Dev não encontrado"));
    }

    public Dev atualizarDev(Long id, Dev devAtualizado) {
        Dev dev = buscarPorId(id);

        dev.setNome(devAtualizado.getNome());

        if (devAtualizado.getMoedas() != null) {
            dev.setMoedas(devAtualizado.getMoedas());
        }

        return devRepository.save(dev);
    }

    public void deletarDev(Long id) {
        if (!devRepository.existsById(id)) {
            throw new RuntimeException("Dev não encontrado");
        }
        devRepository.deleteById(id);
    }

    public void transferirMoedas(TransferenciaRequest request) {

        Dev pagador = buscarPorId(request.getDevPagadorId());
        Dev recebedor = buscarPorId(request.getDevRecebedorId());

        if (pagador.getId().equals(recebedor.getId())) {
            throw new IllegalArgumentException("Um dev não pode pagar a si mesmo");
        }

        if (pagador.getMoedas() == 0) {
            throw new IllegalArgumentException("Dev pagador não possui moedas");
        }

        pagador.removerMoeda();
        recebedor.adicionarMoeda();

        devRepository.save(pagador);
        devRepository.save(recebedor);
    }


    public Dev incrementarBatataQuente(Long id) {
        Dev dev = buscarPorId(id);
        dev.incrementarBatataQuente();
        return devRepository.save(dev);
    }

    public Dev buscarDevComMenorBatataQuente() {
        List<Dev> devs = devRepository.findAll();

        if (devs.isEmpty()) {
            throw new RuntimeException("Nenhum dev cadastrado");
        }

        return devs.stream()
                .min(Comparator
                        .comparing(Dev::getBatataQuente)
                        .thenComparing(Dev::getId))
                .orElseThrow();
    }
}
