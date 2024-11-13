package com.example;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/alugueis")
public class AluguelController {
    @Autowired
    private AluguelRepository aluguelRepository;
    private ImovelRepository imovelRepository;
    private InquilinoRepository inquilinoRepository;

    public AluguelController(){}

    @PostMapping
    public ResponseEntity<?> cadastrarAluguel(@RequestBody Aluguel aluguel) {
        Optional<Imovel> imovelOpt = imovelRepository.findById(aluguel.getImovel().getId());
        Optional<Inquilino> inquilinoOpt = inquilinoRepository.findById(aluguel.getInquilino().getId());

        if (!imovelOpt.isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Imovel não encontrado com ID " + aluguel.getImovel().getId());
        }

        if (!inquilinoOpt.isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Inquilino não encontrado com ID " + aluguel.getInquilino().getId());
        }

        aluguel.setImovel(imovelOpt.get());
        aluguel.setInquilino(inquilinoOpt.get());

        Aluguel savedAluguel = aluguelRepository.save(aluguel);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedAluguel);
    }

    @GetMapping
    Iterable<Aluguel> getAlugueis(){
        return aluguelRepository.findAll();
    }

    @DeleteMapping("/{id}")
    void excluirAluguel(@PathVariable Long id){
        aluguelRepository.deleteById(id);
    }
}
