package com.example;
import java.util.Optional;

import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.*;


@RestController
@RequestMapping("/inquilinos")
public class InquilinoController {
    @Autowired
    private InquilinoRepository inquilinoRepository;

    public InquilinoController(){}

    @PostMapping("/cadastro")
    public ResponseEntity<String> cadastrarInquilino(@RequestBody Inquilino inquilino) {
        Optional<Inquilino> inquilinoExistente = inquilinoRepository.findByNome(inquilino.getNome());
        
        if (inquilinoExistente.isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Já existe um inquilino com esse nome.");
        }

        inquilinoRepository.save(inquilino);
        return ResponseEntity.status(HttpStatus.CREATED).body("Inquilino cadastrado com sucesso.");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Inquilino login) {
        if (autenticar(login.getNome(), login.getSenha())) {
            return ResponseEntity.ok("Login bem-sucedido");
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuário ou senha incorretos");
    }

    public boolean autenticar(String nome, String senha) {
        Inquilino inquilino = inquilinoRepository.findByNome(nome)
                .orElse(null);
        
        if (inquilino != null && inquilino.getSenha().equals(senha)) {
            return true;
        }
        
        return false;
    }


    @DeleteMapping("/{id}")
    void excluirInquilino(@PathVariable Long id) {
        inquilinoRepository.deleteById(id);
    }
}
