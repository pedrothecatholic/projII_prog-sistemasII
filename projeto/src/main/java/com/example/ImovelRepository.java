package com.example;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface ImovelRepository extends CrudRepository<Imovel, Long>{
    Optional<Imovel> findByEndereço(String endereço);
}
