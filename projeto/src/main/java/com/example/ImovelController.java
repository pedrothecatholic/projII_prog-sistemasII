package com.example;
import java.util.Optional;

import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.*;


@RestController
public class ImovelController {

    @Autowired
    private ImovelRepository imovelRepository;

    public ImovelController(){}

    @RequestMapping("/imoveis")
    @GetMapping
    Iterable<Imovel> getImoveis(){
        return imovelRepository.findAll();
    }

    @GetMapping("/{id}")
    Optional<Imovel> getImovel(@PathVariable Long id){
        return imovelRepository.findById(id);
    }

    @GetMapping("/{endereço}")
    Optional<Imovel> getImovel(@PathVariable String endereço){
        return imovelRepository.findByEndereço(endereço);
    }

    @PostMapping
    Imovel cadastrarImovel(@RequestBody Imovel imovel) {
        return imovelRepository.save(imovel);
    }

    @PutMapping("/{id}")
    Optional<Imovel> atualizarImovel(@PathVariable Long id, @RequestBody Imovel imovelRequest) {
        Optional<Imovel> opt = this.getImovel(id);
        if (opt.isPresent()) {
            Imovel imovel = opt.get();
            if (imovelRequest.getId() == imovel.getId()) {
                imovel.setTipo(imovelRequest.getTipo());
                imovel.setEndereco(imovelRequest.getEndereco());
                imovel.setAreaUtil(imovelRequest.getAreaUtil());
                imovel.setDisponibilidade(imovelRequest.getDisponibilidade());
                imovel.setPrecoAluguel(imovelRequest.getPrecoAluguel());
                imovel.setQuartos(imovelRequest.getQuartos());
                imovelRepository.save(imovel);
                return opt;
            }
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND,
        "Erro ao alterar dados do imóvel com id " + id);
    }

    @DeleteMapping("/{id}")
    void excluirImovel(@PathVariable Long id) {
        imovelRepository.deleteById(id);
    }
}
