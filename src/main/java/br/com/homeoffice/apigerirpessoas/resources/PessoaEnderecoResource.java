package br.com.homeoffice.apigerirpessoas.resources;


import br.com.homeoffice.apigerirpessoas.domain.Endereco;
import br.com.homeoffice.apigerirpessoas.domain.dto.PessoaEnderecoDTO;
import br.com.homeoffice.apigerirpessoas.resources.util.URL;
import br.com.homeoffice.apigerirpessoas.services.EnderecoService;
import br.com.homeoffice.apigerirpessoas.services.PessoaEnderecoService;
import br.com.homeoffice.apigerirpessoas.services.PessoaService;
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

    @Autowired
    private PessoaService pessoaService;

    @Autowired
    private EnderecoService enderecoService;

    @GetMapping(value = ID)
    public ResponseEntity<PessoaEnderecoDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(mapper.map(pessoaEnderecoService.findById(id), PessoaEnderecoDTO.class));
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
    @RequestMapping(value = "/nomepessoa", method = RequestMethod.GET)
    public ResponseEntity<List<Endereco>>FindByPessoaNome(@RequestParam(value = "nome",
            defaultValue = "")String nome){
        nome = URL.decodeParam(nome);
        return ResponseEntity.ok().body(pessoaEnderecoService.FindByPessoaNome(nome));
    }

}
