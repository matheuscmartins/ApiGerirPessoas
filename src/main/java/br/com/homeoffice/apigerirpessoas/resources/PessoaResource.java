package br.com.homeoffice.apigerirpessoas.resources;


import br.com.homeoffice.apigerirpessoas.domain.dto.PessoaDTO;
import br.com.homeoffice.apigerirpessoas.services.PessoaService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/pessoas")
public class PessoaResource {
    public static final String ID = "/{id}";
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private PessoaService pessoaService;

    @GetMapping(value = ID)
    public ResponseEntity<PessoaDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(mapper.map(pessoaService.findById(id), PessoaDTO.class));
    }
    @GetMapping
    public ResponseEntity<List<PessoaDTO>> findAll() {
        return ResponseEntity.ok().body(pessoaService.findAll().stream().map(
                x -> mapper.map(x, PessoaDTO.class)).collect(Collectors.toList()));
    }

    @PostMapping
    public ResponseEntity<PessoaDTO> create(@RequestBody PessoaDTO objDTO) {
        return ResponseEntity.created(
                ServletUriComponentsBuilder.fromCurrentRequest().path(ID).buildAndExpand(
                        pessoaService.create(objDTO).getId()).toUri()).build();
    }

    @PutMapping(value = ID)
    public ResponseEntity<PessoaDTO> update(@PathVariable Long id, @RequestBody PessoaDTO objDTO) {
        objDTO.setId(id);
        return ResponseEntity.ok().body(mapper.map(pessoaService.update(objDTO), PessoaDTO.class));
    }

    @DeleteMapping(value = ID)
    public ResponseEntity<PessoaDTO> delete(@PathVariable Long id) {
        pessoaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
