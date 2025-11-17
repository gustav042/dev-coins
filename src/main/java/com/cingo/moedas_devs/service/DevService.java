package com.cingo.moedas_devs.service;

import com.cingo.moedas_devs.dto.TransferenciaRequest;
import com.cingo.moedas_devs.model.Dev;
import com.cingo.moedas_devs.repository.DevRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DevService {

    @Autowired
    private DevRepository devRepository;

    @CacheEvict(value = "devs", allEntries = true)
    public Dev criarDev(Dev dev) {
        return devRepository.save(dev);
    }

    @Cacheable(value = "devs")
    public List<Dev> listarTodos() {
        return devRepository.findAll();
    }

    public Dev buscarPorId(Long id) {
        return devRepository.findById(id).orElse(null);
    }

    @CacheEvict(value = "devs", allEntries = true)
    public Dev atualizarDev(Long id, Dev devAtualizado) {
        return devRepository.findById(id)
                .map(dev -> {
                    dev.setNome(devAtualizado.getNome());
                    if (devAtualizado.getMoedas() != null) {
                        dev.setMoedas(devAtualizado.getMoedas());
                    }
                    return devRepository.save(dev);
                })
                .orElse(null);
    }

    @CacheEvict(value = "devs", allEntries = true)
    public String transferirMoedas(TransferenciaRequest req) {

        Dev pagador = devRepository.findById(req.getDevPagadorId()).orElse(null);
        Dev recebedor = devRepository.findById(req.getDevRecebedorId()).orElse(null);

        if (pagador == null) return "Dev pagador não encontrado";
        if (recebedor == null) return "Dev recebedor não encontrado";
        if (pagador.getId().equals(recebedor.getId())) return "Um dev não pode pagar a si mesmo";

        pagador.removerMoeda();
        recebedor.adicionarMoeda();

        devRepository.save(pagador);
        devRepository.save(recebedor);

        return "Transferência realizada com sucesso!";
    }

    @CacheEvict(value = "devs", allEntries = true)
    public boolean deletarDev(Long id) {
        if (devRepository.existsById(id)) {
            devRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @CacheEvict(value = "devs", allEntries = true)
    public Dev incrementarBatataQuente(Long id) {
        return devRepository.findById(id)
                .map(dev -> {
                    dev.incrementarBatataQuente();
                    return devRepository.save(dev);
                })
                .orElse(null);
    }

    public Dev buscarMenorBatataQuente() {
        List<Dev> devs = devRepository.findAll();

        if (devs.isEmpty()) return null;

        return devs.stream()
                .min((dev1, dev2) -> {
                    int valorComparacao = dev1.getBatataQuente().compareTo(dev2.getBatataQuente());
                    return (valorComparacao != 0) ? valorComparacao : dev1.getId().compareTo(dev2.getId());
                })
                .orElse(null);
    }
}
