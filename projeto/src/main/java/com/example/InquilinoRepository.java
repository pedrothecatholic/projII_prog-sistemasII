package com.example;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface InquilinoRepository extends CrudRepository<Inquilino, Long>{
    Optional<Inquilino> findByNome(String nome);
}
