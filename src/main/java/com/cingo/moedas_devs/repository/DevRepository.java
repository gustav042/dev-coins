package com.cingo.moedas_devs.repository;
import com.cingo.moedas_devs.model.Dev;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository é a camada que faz a comunicação com o banco de dados.
 * O JpaRepository já fornece métodos prontos como:
 * - save() para salvar/atualizar
 * - findAll() para listar todos
 * - findById() para buscar por ID
 * - deleteById() para deletar
 */
@Repository
public interface DevRepository extends JpaRepository<Dev, Long> {
    // Não precisa implementar nada aqui!
    // O Spring já cria automaticamente os métodos básicos do CRUD
}