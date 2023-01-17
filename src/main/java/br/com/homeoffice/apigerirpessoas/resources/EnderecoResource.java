package br.com.homeoffice.apigerirpessoas.resources;


import br.com.homeoffice.apigerirpessoas.domain.dto.EnderecoDTO;
import br.com.homeoffice.apigerirpessoas.services.EnderecoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/enderecos")
public class EnderecoResource {
    public static final String ID = "/{id}";
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private EnderecoService enderecoService;

    @GetMapping(value = ID)
    public ResponseEntity<EnderecoDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(mapper.map(enderecoService.findById(id), EnderecoDTO.class));
    }

    @GetMapping
    public ResponseEntity<List<EnderecoDTO>> findAll() {
        return ResponseEntity.ok().body(enderecoService.findAll().stream().map(
                x -> mapper.map(x, EnderecoDTO.class)).collect(Collectors.toList()));
    }

    @PostMapping
    public ResponseEntity<EnderecoDTO> create(@RequestBody EnderecoDTO objDTO) {
        return ResponseEntity.created(
                ServletUriComponentsBuilder.fromCurrentRequest().path(ID).buildAndExpand(
                        enderecoService.create(objDTO).getId()).toUri()).build();
    }

    @PutMapping(value = ID)
    public ResponseEntity<EnderecoDTO> update(@PathVariable Long id, @RequestBody EnderecoDTO objDTO) {
        objDTO.setId(id);
        return ResponseEntity.ok().body(mapper.map(enderecoService.update(objDTO), EnderecoDTO.class));
    }

    @DeleteMapping(value = ID)
    public ResponseEntity<EnderecoDTO> delete(@PathVariable Long id) {
        enderecoService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
