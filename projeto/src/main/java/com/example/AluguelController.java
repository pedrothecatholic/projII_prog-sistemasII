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
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/alugueis")
public class AluguelController {
    @Autowired
    private AluguelRepository aluguelRepository;

    @Autowired
    private ImovelRepository imovelRepository;

    @Autowired
    private InquilinoRepository inquilinoRepository;

    public AluguelController(){}

    @PostMapping
    public ResponseEntity<String> cadastrarAluguel(@RequestBody Aluguel aluguel) {
        try {
            Optional<Imovel> imovelOpt = imovelRepository.findById(aluguel.getImovel().getId());
            if (!imovelOpt.isPresent()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Imóvel não encontrado com ID " + aluguel.getImovel().getId());
            }

            Optional<Inquilino> inquilinoOpt = inquilinoRepository.findById(aluguel.getInquilino().getId());
            if (!inquilinoOpt.isPresent()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Inquilino não encontrado com ID " + aluguel.getInquilino().getId());
            }

            aluguel.setImovel(imovelOpt.get());
            aluguel.setInquilino(inquilinoOpt.get());

            aluguelRepository.save(aluguel);
            return ResponseEntity.status(HttpStatus.CREATED).body("Aluguel cadastrado com sucesso.");

        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao cadastrar aluguel.", e);
        }
    }

    @GetMapping
    public Iterable<Aluguel> getAlugueis() {
        try {
            return aluguelRepository.findAll();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao recuperar os aluguéis.", e);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> excluirAluguel(@PathVariable Long id) {
        try {
            Optional<Aluguel> aluguelOpt = aluguelRepository.findById(id);
            if (!aluguelOpt.isPresent()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Aluguel não encontrado com ID " + id);
            }

            aluguelRepository.deleteById(id);
            return ResponseEntity.ok("Aluguel excluído com sucesso.");

        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao excluir aluguel.", e);
        }
    }
}
