package br.com.homeoffice.apigerirpessoas.services.implementation;

import br.com.homeoffice.apigerirpessoas.domain.Pessoa;
import br.com.homeoffice.apigerirpessoas.domain.dto.PessoaDTO;
import br.com.homeoffice.apigerirpessoas.repositories.PessoaRepository;
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
class PessoasServiceImplTest {
    public static final long ID = 1L;
    public static final String NOME = "Maria Cruz";
    public static final LocalDate DATANASCIMENTO = LocalDate.parse("1987-01-28");
    public static final String OBJETO_NAO_ENCONTRADO_NO_ID = "Objeto não encontrado no id: ";
    public static final int INDEX = 0;

    @InjectMocks
    private PessoasServiceImpl pessoaServiceImpl;
    @Mock
    private PessoaRepository pessoaRepository;
    @Mock
    private ModelMapper mapper ;
    private Pessoa pessoa;
    private PessoaDTO pessoaDTO ;
    private Optional<Pessoa> optionalPessoa;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startUser();
    }
    @Test
    void whenFindByIdThenReturnPessoaInstance() {
        when(pessoaRepository.findById(anyLong())).thenReturn(optionalPessoa);
        Pessoa response = pessoaServiceImpl.findById(ID);
        assertNotNull(response); //assegura que não será null
        assertEquals(Pessoa.class, response.getClass()); //assegure que ambos são iguais
        assertEquals(ID, response.getId()); //assegure que ambos são iguais
        assertEquals(NOME, response.getNome()); //assegure que ambos são iguais
        assertEquals(DATANASCIMENTO, response.getDataNascimento());//assegure que ambos são iguais
    }
    @Test
    void whenFindByIdThenReturnAnObjectNotFoundException() {
        when(pessoaRepository.findById(anyLong())).thenThrow(new ObjectNotFoundException(OBJETO_NAO_ENCONTRADO_NO_ID));
        try {
            pessoaServiceImpl.findById(ID);
        } catch (Exception ex){
            assertEquals(ObjectNotFoundException.class, ex.getClass()); //assegure que ambos são iguais
            assertEquals(OBJETO_NAO_ENCONTRADO_NO_ID, ex.getMessage()); //assegure que as messagens de erro sejam iguais
        }
    }
    @Test
    void whenFindAllThenReturnAnListOfPessoas() {
        when(pessoaRepository.findAll()).thenReturn(List.of(pessoa));

        List<Pessoa> response = pessoaServiceImpl.findAll();
        assertNotNull((response)); //assegura que não será null
        assertEquals(1, response.size()); //assegure que o tamanho da lista seja 1
        assertEquals(Pessoa.class, response.get(INDEX).getClass()); //assegure que o objo 0 seja da classe pessoa
        assertEquals(ID, response.get(INDEX).getId());
        assertEquals(NOME, response.get(INDEX).getNome());
        assertEquals(DATANASCIMENTO, response.get(INDEX).getDataNascimento());
    }

    @Test
    void whenCreateThenReturnSuccess() {
        when(pessoaRepository.save(any())).thenReturn(pessoa);
        Pessoa response = pessoaServiceImpl.create(pessoaDTO);

        assertNotNull(response);
        assertEquals(Pessoa.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(NOME, response.getNome());
        assertEquals(DATANASCIMENTO, response.getDataNascimento());
    }

    @Test
    void whenCreateThenReturnDataIntegrityViolationException() {
        try {
            optionalPessoa.get().setNome(null);
            pessoaServiceImpl.create(pessoaDTO);
        } catch (Exception ex){
            assertEquals(DataIntegrityViolationException.class, ex.getClass());
        }
    }

    @Test
    void whenUpdateThenReturnSuccess() {
        when(pessoaRepository.save(any())).thenReturn(pessoa);
        Pessoa response = pessoaServiceImpl.update(pessoaDTO);

        assertNotNull(response);
        assertEquals(Pessoa.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(NOME, response.getNome());
        assertEquals(DATANASCIMENTO, response.getDataNascimento());
    }
    @Test
    void whenUpdateThenReturnDataIntegrityViolationException() {
        try {
            optionalPessoa.get().setNome(null);
            pessoaServiceImpl.create(pessoaDTO);
        } catch (Exception ex){
            assertEquals(DataIntegrityViolationException.class, ex.getClass());
        }
    }
    @Test
    void deleteWhitSuccess() {
        when(pessoaRepository.findById(anyLong())).thenReturn(optionalPessoa);
        doNothing().when(pessoaRepository).deleteById(anyLong()); //não faça nada quando o metodo for chamado c/ um Long
        pessoaServiceImpl.delete(ID);
        verify(pessoaRepository, times(1)).deleteById(anyLong());
        //verifica quantas vezes o metodo foi chamado e espera que o metodo delete seja chamado uma vez
    }
    @Test
    void deleteWhitObjectNotFoundException(){
        when(pessoaRepository.findById(anyLong()))
                .thenThrow(new ObjectNotFoundException(OBJETO_NAO_ENCONTRADO_NO_ID));
        try {
            pessoaServiceImpl.delete(ID);
            pessoaServiceImpl.delete(null);
        } catch (Exception ex) {
            assertEquals(ObjectNotFoundException.class, ex.getClass());
            assertEquals(OBJETO_NAO_ENCONTRADO_NO_ID, ex.getMessage());
        }
    }
    private void startUser() {
        pessoa = new Pessoa(ID, NOME, DATANASCIMENTO);
        pessoaDTO = new PessoaDTO(ID, NOME, DATANASCIMENTO);
        optionalPessoa =  Optional.of(new Pessoa(ID, NOME, DATANASCIMENTO));
    }
}