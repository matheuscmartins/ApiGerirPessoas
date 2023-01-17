package br.com.homeoffice.apigerirpessoas.resources;

import br.com.homeoffice.apigerirpessoas.domain.dto.CidadeDTO;
import br.com.homeoffice.apigerirpessoas.services.CidadeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/cidades")
public class CidadeResource {
    public static final String ID = "/{id}";
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private CidadeService cidadeService;

    @GetMapping(value = ID)
    public ResponseEntity<CidadeDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(mapper.map(cidadeService.findById(id), CidadeDTO.class));
    }
    @GetMapping
    public ResponseEntity<List<CidadeDTO>> findAll() {
        return ResponseEntity.ok().body(cidadeService.findAll().stream().map(
                x -> mapper.map(x, CidadeDTO.class)).collect(Collectors.toList()));
    }

    @PostMapping
    public ResponseEntity<CidadeDTO> create(@RequestBody CidadeDTO objDTO) {
        return ResponseEntity.created(
                ServletUriComponentsBuilder.fromCurrentRequest().path(ID).buildAndExpand(
                        cidadeService.create(objDTO).getId()).toUri()).build();
    }

    @PutMapping(value = ID)
    public ResponseEntity<CidadeDTO> update(@PathVariable Long id, @RequestBody CidadeDTO objDTO) {
        objDTO.setId(id);
        return ResponseEntity.ok().body(mapper.map(cidadeService.update(objDTO), CidadeDTO.class));
    }

    @DeleteMapping(value = ID)
    public ResponseEntity<CidadeDTO> delete(@PathVariable Long id) {
        cidadeService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
