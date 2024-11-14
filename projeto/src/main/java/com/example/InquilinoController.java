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
    ResponseEntity<String> cadastrarInquilino(@RequestBody Inquilino inquilino) {
        Optional<Inquilino> inquilinoExistente = inquilinoRepository.findByNome(inquilino.getNome());
        
        if (inquilinoExistente.isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Já existe um inquilino com esse nome.");
        }

        inquilinoRepository.save(inquilino);
        return ResponseEntity.status(HttpStatus.CREATED).body("Inquilino cadastrado com sucesso.");
    }

    @PostMapping("/login")
    ResponseEntity<String> login(@RequestBody Inquilino login) {
        if (autenticar(login.getNome(), login.getSenha())) {
            return ResponseEntity.ok("Login bem-sucedido");
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuário ou senha incorretos");
    }

    boolean autenticar(String nome, String senha) {
        Inquilino inquilino = inquilinoRepository.findByNome(nome)
                .orElse(null);
        
        if (inquilino != null && inquilino.getSenha().equals(senha)) {
            return true;
        }
        
        return false;
    }

    @GetMapping
    Iterable<Inquilino> getInquilinos(){
        return inquilinoRepository.findAll();
    }

    @GetMapping("/{id}")
    Optional<Inquilino> getInquilino(@PathVariable Long id){
        return inquilinoRepository.findById(id);
    }

    @PutMapping("/{id}")
    Optional<Inquilino> atualizarInquilino(@PathVariable Long id, @RequestBody Inquilino inquilinoRequest) {
        Optional<Inquilino> opt = this.getInquilino(id);
        if (opt.isPresent()) {
            Inquilino inquilino = opt.get();
            if (inquilinoRequest.getId() == inquilino.getId()) {
                inquilino.setNome(inquilinoRequest.getNome());
                inquilino.setSenha(inquilinoRequest.getSenha());
                inquilinoRepository.save(inquilino);
                return opt;
            }
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND,
        "Erro ao alterar dados do inquilino com id " + id);
    }

    @DeleteMapping("/{id}")
    void excluirInquilino(@PathVariable Long id) {
        inquilinoRepository.deleteById(id);
    }
}
