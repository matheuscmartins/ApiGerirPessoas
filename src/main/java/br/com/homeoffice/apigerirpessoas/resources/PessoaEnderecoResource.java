package br.com.homeoffice.apigerirpessoas.resources;


import br.com.homeoffice.apigerirpessoas.domain.Pessoa;
import br.com.homeoffice.apigerirpessoas.domain.dto.PessoaEnderecoDTO;
import br.com.homeoffice.apigerirpessoas.services.PessoaEnderecoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/pessoaendereco")
public class PessoaEnderecoResource {
    public static final String ID = "/{id}";
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private PessoaEnderecoService pessoaEnderecoService;

    @GetMapping(value = ID)
    public ResponseEntity<PessoaEnderecoDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(mapper.map(pessoaEnderecoService.findById(id), PessoaEnderecoDTO.class));
    }
    @GetMapping(value = ID)
    public ResponseEntity<List<PessoaEnderecoDTO>> findByIdPessoa(@PathVariable Long id, @RequestBody PessoaEnderecoDTO objDTO) {
        objDTO.setPessoa(objDTO.setId(id)) ;
        return ResponseEntity.ok().body(pessoaEnderecoService.findByIdPessoa(objDTO).stream().map(
                x -> mapper.map(x, PessoaEnderecoDTO.class)).collect(Collectors.toList()));

    }
    @GetMapping
    public ResponseEntity<List<PessoaEnderecoDTO>> findAll() {
        return ResponseEntity.ok().body(pessoaEnderecoService.findAll().stream().map(
                x -> mapper.map(x, PessoaEnderecoDTO.class)).collect(Collectors.toList()));
    }

    @PostMapping
    public ResponseEntity<PessoaEnderecoDTO> create(@RequestBody PessoaEnderecoDTO objDTO) {
        return ResponseEntity.created(
                ServletUriComponentsBuilder.fromCurrentRequest().path(ID).buildAndExpand(
                        pessoaEnderecoService.create(objDTO).getId()).toUri()).build();
    }

    @PutMapping(value = ID)
    public ResponseEntity<PessoaEnderecoDTO> update(@PathVariable Long id, @RequestBody PessoaEnderecoDTO objDTO) {
        objDTO.setId(id);
        return ResponseEntity.ok().body(mapper.map(pessoaEnderecoService.update(objDTO), PessoaEnderecoDTO.class));
    }

    @DeleteMapping(value = ID)
    public ResponseEntity<PessoaEnderecoDTO> delete(@PathVariable Long id) {
        pessoaEnderecoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
