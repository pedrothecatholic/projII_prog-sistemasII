import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/imoveis")
public class ImovelController {
    @Autowired
    private ImovelRepository imovelRepository;

    @GetMapping
    public List<Imovel> listarImoveis() {
        return imovelRepository.findAll();
    }

    @PostMapping
    public Imovel cadastrarImovel(@RequestBody Imovel imovel) {
        return imovelRepository.save(imovel);
    }

    @PutMapping("/{id}")
    public Imovel atualizarImovel(@PathVariable Long id, @RequestBody Imovel imovel) {
        imovel.setId(id);
        return imovelRepository.save(imovel);
    }

    @DeleteMapping("/{id}")
    public void excluirImovel(@PathVariable Long id) {
        imovelRepository.deleteById(id);
    }
}
