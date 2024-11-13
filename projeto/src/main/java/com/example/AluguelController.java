package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/alugueis")
public class AluguelController {
    @Autowired
    private AluguelRepository aluguelRepository;

    public AluguelController(){}

    @PostMapping
    Aluguel cadastrarAluguel(@RequestBody Aluguel aluguel){
        return aluguelRepository.save(aluguel);
    }

    @GetMapping
    Iterable<Aluguel> getAlugueis(){
        return aluguelRepository.findAll();
    }

    @DeleteMapping("/{id}")
    void excluirAluguel(Long id){
        aluguelRepository.deleteById(id);
    }
}
