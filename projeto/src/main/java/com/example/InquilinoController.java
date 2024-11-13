package com.example;
import java.util.Optional;

import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.*;


@RestController
public class InquilinoController {
    @Autowired
    private InquilinoRepository inquilinoRepository;

    public InquilinoController(){}

    @RequestMapping("/inquilino")
    @PostMapping
    Inquilino cadastrarInquilino(@RequestBody Inquilino inquilino) {
        return inquilinoRepository.save(inquilino);
    }

    @DeleteMapping("/{id}")
    void excluirInquilino(@PathVariable Long id) {
        inquilinoRepository.deleteById(id);
    }
}
