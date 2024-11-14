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
        try {
            Optional<Inquilino> inquilinoExistente = inquilinoRepository.findByNome(inquilino.getNome());
            
            if (inquilinoExistente.isPresent()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Já existe um inquilino com esse nome.");
            }

            inquilinoRepository.save(inquilino);
            return ResponseEntity.status(HttpStatus.CREATED).body("Inquilino cadastrado com sucesso.");

        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,"Erro ao cadastrar inquilino", e);
        }
    }

    @PostMapping("/login")
    ResponseEntity<String> login(@RequestBody Inquilino login) {
        try {
            if (autenticar(login.getNome(), login.getSenha())) {
                return ResponseEntity.ok("Login bem-sucedido");
            }
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuário ou senha incorretos");
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,"Erro ao tentar autenticar o usuário", e);
        }
    }

    boolean autenticar(String nome, String senha) {
        Inquilino inquilino = inquilinoRepository.findByNome(nome).orElse(null);
        
        if (inquilino != null && inquilino.getSenha().equals(senha)) {
            return true;
        }
        
        return false;
    }

    @GetMapping
    Iterable<Inquilino> getInquilinos(){
        try {
            return inquilinoRepository.findAll();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,"Erro ao recuperar a lista de inquilinos", e);
        }
    }

    @GetMapping("/{id}")
    Optional<Inquilino> getInquilino(@PathVariable Long id){
        try {
            Optional<Inquilino> inquilino = inquilinoRepository.findById(id);
            if (inquilino.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Inquilino com o ID " + id + " não encontrado.");
            }
            return inquilino;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,"Erro ao recuperar o inquilino com o ID " + id, e);
        }
    }

    @PutMapping("/{id}")
    ResponseEntity<String> atualizarInquilino(@PathVariable Long id, @RequestBody Inquilino inquilinoRequest) {
        try {
            Optional<Inquilino> opt = this.getInquilino(id);
            if (opt.isPresent()) {
                Inquilino inquilino = opt.get();
                if (inquilinoRequest.getId().equals(inquilino.getId())) {
                    inquilino.setNome(inquilinoRequest.getNome());
                    inquilino.setSenha(inquilinoRequest.getSenha());
                    inquilinoRepository.save(inquilino);
                    return ResponseEntity.ok("Inquilino atualizado com sucesso.");
                }
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Inquilino não encontrado ou dados incorretos.");
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,"Erro ao atualizar dados do inquilino", e);
        }
    }

    @DeleteMapping("/{id}")
    ResponseEntity<String> excluirInquilino(@PathVariable Long id) {
        try {
            Optional<Inquilino> inquilino = this.getInquilino(id);
            if (inquilino.isPresent()) {
                inquilinoRepository.deleteById(id);
                return ResponseEntity.ok("Inquilino excluído com sucesso.");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Inquilino com o ID " + id + " não encontrado.");
            }
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,"Erro ao excluir inquilino", e);
        }
    }
}
