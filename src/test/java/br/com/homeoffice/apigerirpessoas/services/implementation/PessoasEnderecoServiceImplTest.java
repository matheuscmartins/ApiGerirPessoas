package br.com.homeoffice.apigerirpessoas.services.implementation;

import br.com.homeoffice.apigerirpessoas.domain.Cidade;
import br.com.homeoffice.apigerirpessoas.domain.Endereco;
import br.com.homeoffice.apigerirpessoas.domain.Pessoa;
import br.com.homeoffice.apigerirpessoas.domain.PessoaEndereco;
import br.com.homeoffice.apigerirpessoas.domain.dto.PessoaEnderecoDTO;
import br.com.homeoffice.apigerirpessoas.domain.enums.EnderecoStatus;
import br.com.homeoffice.apigerirpessoas.repositories.PessoaEnderecoRepository;
import br.com.homeoffice.apigerirpessoas.services.exceptions.DataIntegrityViolationException;
import br.com.homeoffice.apigerirpessoas.services.exceptions.ObjectNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@SpringBootTest
class PessoasEnderecoServiceImplTest {
    public static final long ID = 1L;
    public static final EnderecoStatus ENDERECO_STATUS = EnderecoStatus.valueOf(1);
    public static final String OBJETO_NAO_ENCONTRADO_NO_ID = "Objeto não encontrado no id: ";
    public static final int INDEX = 0;
    @InjectMocks
    private PessoasEnderecoServiceImpl pessoasEnderecoServiceImpl;
    @Mock
    private PessoaEnderecoRepository pessoaEnderecoRepository;
    @Mock
    private ModelMapper mapper;
    private Cidade cidade;
    private Optional<PessoaEndereco> optionalPessoaEndereco;
    private PessoaEndereco pessoaEndereco;
    private Endereco endereco;
    private PessoaEnderecoDTO pessoaEnderecoDTO;
    private Pessoa pessoa;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startPessoaEndereco();
    }
    @Test
    void whenFindByIdThenReturnPessoaEnderecoInstance() {
        when(pessoaEnderecoRepository.findById(anyLong())).thenReturn(optionalPessoaEndereco);
        PessoaEndereco response = pessoasEnderecoServiceImpl.findById(ID);
        assertNotNull(response); //assegura que não será null
        assertEquals(PessoaEndereco.class, response.getClass()); //assegure que ambos são iguais
        assertEquals(ID, response.getId()); //assegure que ambos são iguais
        assertEquals(Endereco.class, response.getEndereco().getClass()); //assegure que ambos são iguais
        assertEquals(endereco.getId(), response.getEndereco().getId());
        assertEquals(Pessoa.class, response.getPessoa().getClass());//assegure que ambos são iguais
        assertEquals(pessoa.getId(), response.getPessoa().getId());
        assertEquals(ENDERECO_STATUS, response.getEnderecoStatus());
    }
    @Test
    void whenFindByIdThenReturnAnObjectNotFoundException() {
        when(pessoaEnderecoRepository.findById(anyLong())).thenThrow(new ObjectNotFoundException(OBJETO_NAO_ENCONTRADO_NO_ID));
        try {
            pessoasEnderecoServiceImpl.findById(ID);
        } catch (Exception ex){
            assertEquals(ObjectNotFoundException.class, ex.getClass()); //assegure que ambos são iguais
            assertEquals(OBJETO_NAO_ENCONTRADO_NO_ID, ex.getMessage()); //assegure que as messagens de erro sejam iguais
        }
    }
    @Test
    void whenFindAllThenReturnAnListOfPessoaEnderecos() {
        when(pessoaEnderecoRepository.findAll()).thenReturn(List.of(pessoaEndereco));

        List<PessoaEndereco> response = pessoasEnderecoServiceImpl.findAll();
        assertNotNull((response)); //assegura que não será null
        assertEquals(1, response.size()); //assegure que o tamanho da lista seja 1
        assertEquals(PessoaEndereco.class, response.get(INDEX).getClass()); //assegure que ambos são iguais
        assertEquals(ID, response.get(INDEX).getId()); //assegure que ambos são iguais
        assertEquals(Endereco.class, response.get(INDEX).getEndereco().getClass()); //assegure que ambos são iguais
        assertEquals(endereco.getId(), response.get(INDEX).getEndereco().getId());
        assertEquals(Pessoa.class, response.get(INDEX).getPessoa().getClass());//assegure que ambos são iguais
        assertEquals(pessoa.getId(), response.get(INDEX).getPessoa().getId());
        assertEquals(ENDERECO_STATUS, response.get(INDEX).getEnderecoStatus());
    }

    @Test
    void whenCreateThenReturnSuccess() {
        when(pessoaEnderecoRepository.save(any())).thenReturn(pessoaEndereco);
        PessoaEndereco response = pessoasEnderecoServiceImpl.create(pessoaEnderecoDTO);

        assertNotNull(response);
        assertEquals(PessoaEndereco.class, response.getClass());
        assertEquals(ID, response.getId()); //assegure que ambos são iguais
        assertEquals(Endereco.class, response.getEndereco().getClass()); //assegure que ambos são iguais
        assertEquals(endereco.getId(), response.getEndereco().getId());
        assertEquals(Pessoa.class, response.getPessoa().getClass());//assegure que ambos são iguais
        assertEquals(pessoa.getId(), response.getPessoa().getId());
        assertEquals(ENDERECO_STATUS, response.getEnderecoStatus());
    }

    @Test
    void whenCreateThenReturnDataIntegrityViolationException() {
        try {
            pessoasEnderecoServiceImpl.create(pessoaEnderecoDTO);
        } catch (Exception ex){
            assertEquals(DataIntegrityViolationException.class, ex.getClass());
        }
    }

    @Test
    void whenUpdateThenReturnSuccess() {
        when(pessoaEnderecoRepository.save(any())).thenReturn(pessoaEndereco);
        PessoaEndereco response = pessoasEnderecoServiceImpl.update(pessoaEnderecoDTO);

        assertNotNull(response);
        assertEquals(PessoaEndereco.class, response.getClass());
        assertEquals(ID, response.getId()); //assegure que ambos são iguais
        assertEquals(Endereco.class, response.getEndereco().getClass()); //assegure que ambos são iguais
        assertEquals(endereco.getId(), response.getEndereco().getId());
        assertEquals(Pessoa.class, response.getPessoa().getClass());//assegure que ambos são iguais
        assertEquals(pessoa.getId(), response.getPessoa().getId());
        assertEquals(ENDERECO_STATUS, response.getEnderecoStatus());
    }
    @Test
    void whenUpdateThenReturnDataIntegrityViolationException() {
        try {
            pessoasEnderecoServiceImpl.create(pessoaEnderecoDTO);
        } catch (Exception ex){
            assertEquals(DataIntegrityViolationException.class, ex.getClass());
        }
    }
    @Test
    void deleteWhitSuccess() {
        when(pessoaEnderecoRepository.findById(anyLong())).thenReturn(optionalPessoaEndereco);
        doNothing().when(pessoaEnderecoRepository).deleteById(anyLong()); //não faça nada quando o metodo for chamado c/ um Long
        pessoasEnderecoServiceImpl.delete(ID);
        verify(pessoaEnderecoRepository, times(1)).deleteById(anyLong());
        //verifica quantas vezes o metodo foi chamado e espera que o metodo delete seja chamado uma vez
    }
    @Test
    void deleteWhitObjectNotFoundException(){
        when(pessoaEnderecoRepository.findById(anyLong()))
                .thenThrow(new ObjectNotFoundException(OBJETO_NAO_ENCONTRADO_NO_ID));
        try {
            pessoasEnderecoServiceImpl.delete(ID);
        } catch (Exception ex) {
            assertEquals(ObjectNotFoundException.class, ex.getClass());
            assertEquals(OBJETO_NAO_ENCONTRADO_NO_ID, ex.getMessage());
        }
    }
    private void startPessoaEndereco() {
        cidade = new Cidade(1L, "Presidente Prudente", "SP");
        pessoa = new Pessoa(1L, "Maria Cruz", LocalDate.parse("1987-01-28"));
        endereco = new Endereco(1L, "Joao Filho", "33",
                "", cidade, null);
        pessoaEndereco = new PessoaEndereco(ID, ENDERECO_STATUS,pessoa,endereco);
        pessoaEnderecoDTO = new PessoaEnderecoDTO(ID, ENDERECO_STATUS,pessoa,endereco);
        optionalPessoaEndereco =  Optional.of(new PessoaEndereco(ID, ENDERECO_STATUS,pessoa,endereco));
    }
}