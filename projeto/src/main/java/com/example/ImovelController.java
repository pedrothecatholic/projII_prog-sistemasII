package com.example;
import java.util.Optional;

import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.*;


@RestController
@RequestMapping("/imoveis")
public class ImovelController {

    @Autowired
    private ImovelRepository imovelRepository;

    public ImovelController(){}

    @GetMapping
    Iterable<Imovel> getImoveis() {
        try {
            return imovelRepository.findAll();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao recuperar imóveis.", e);
        }
    }

    @GetMapping("/id/{id}")
    Optional<Imovel> getImovel(@PathVariable Long id) {
        try {
            Optional<Imovel> imovel = imovelRepository.findById(id);
            if (imovel.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Imóvel não encontrado com ID " + id);
            }
            return imovel;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao recuperar imóvel com ID " + id, e);
        }
    }

    @GetMapping("/endereco/{endereco}")
    Optional<Imovel> getImovelByEndereco(@PathVariable String endereco) {
        try {
            Optional<Imovel> imovel = imovelRepository.findByEndereco(endereco);
            if (imovel.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Imóvel não encontrado com endereço " + endereco);
            }
            return imovel;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao recuperar imóvel por endereço.", e);
        }
    }


    @PostMapping
    ResponseEntity<String> cadastrarImovel(@RequestBody Imovel imovel) {
        try {
            imovelRepository.save(imovel);
            return ResponseEntity.status(HttpStatus.CREATED).body("Imóvel cadastrado com sucesso.");
        } catch (ResponseStatusException e) {
            throw e; 
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao cadastrar imóvel.", e);
        }
    }

    @PutMapping("/id/{id}")
    ResponseEntity<String> atualizarImovel(@PathVariable Long id, @RequestBody Imovel imovelRequest) {
        try {
            Optional<Imovel> opt = this.getImovel(id);
            if (opt.isPresent()) {
                Imovel imovel = opt.get();
                imovel.setEndereco(imovelRequest.getEndereco());
                imovel.setAreaUtil(imovelRequest.getAreaUtil());
                imovel.setDisponibilidade(imovelRequest.getDisponibilidade());
                imovel.setPrecoAluguel(imovelRequest.getPrecoAluguel());
                imovel.setQuartos(imovelRequest.getQuartos());

                if (imovel instanceof Casa && imovelRequest instanceof Casa) {
                    Casa casa = (Casa) imovel;
                    Casa casaRequest = (Casa) imovelRequest;
                    casa.setQuintal(casaRequest.getQuintal());
                    casa.setGaragem(casaRequest.getGaragem());
                } else if (imovel instanceof Apartamento && imovelRequest instanceof Apartamento) {
                    Apartamento apartamento = (Apartamento) imovel;
                    Apartamento apartamentoRequest = (Apartamento) imovelRequest;
                    apartamento.setAndar(apartamentoRequest.getAndar());
                    apartamento.setCondominio(apartamentoRequest.getCondominio());
                }

                imovelRepository.save(imovel);
                return ResponseEntity.ok("Imóvel atualizado com sucesso.");
            } else {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Imóvel não encontrado com ID " + id);
            }
        } catch (ResponseStatusException e) {
            throw e; 
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao atualizar imóvel.", e);
        }
    }

    @DeleteMapping("/id/{id}")
    ResponseEntity<String> excluirImovel(@PathVariable Long id) {
        try {
            Optional<Imovel> imovelExistente = this.getImovel(id);
            if (imovelExistente.isPresent()) {
                imovelRepository.deleteById(id);
                return ResponseEntity.ok("Imóvel excluído com sucesso.");
            } else {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Imóvel não encontrado com ID " + id);
            }
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao excluir imóvel.", e);
        }
    }
}
