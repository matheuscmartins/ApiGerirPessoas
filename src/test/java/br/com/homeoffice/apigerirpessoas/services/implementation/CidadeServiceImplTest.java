package br.com.homeoffice.apigerirpessoas.services.implementation;

import br.com.homeoffice.apigerirpessoas.domain.Cidade;
import br.com.homeoffice.apigerirpessoas.domain.dto.CidadeDTO;
import br.com.homeoffice.apigerirpessoas.repositories.CidadeRepository;
import br.com.homeoffice.apigerirpessoas.services.exceptions.DataIntegrityViolationException;
import br.com.homeoffice.apigerirpessoas.services.exceptions.ObjectNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@SpringBootTest
class CidadeServiceImplTest {

    public static final long ID = 1L;
    public static final String NOME ="Presidente Prudente";
    public static final String UF = "SP";
    public static final String OBJETO_NAO_ENCONTRADO_NO_ID = "Objeto não encontrado no id: ";
    public static final int INDEX = 0;

    @InjectMocks
    private CidadeServiceImpl cidadeServiceImpl;
    @Mock
    private CidadeRepository cidadeRepository;
    @Mock
    private ModelMapper mapper;
    private Cidade cidade;
    private CidadeDTO cidadeDTO;
    private Optional<Cidade> optionalCidade;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startUser();
    }

    @Test
    void whenFindByIdThenReturnCidadeInstance() {
        when(cidadeRepository.findById(anyLong())).thenReturn(optionalCidade);
        Cidade response = cidadeServiceImpl.findById(ID);
        assertNotNull(response); //assegura que não será null
        assertEquals(Cidade.class, response.getClass()); //assegure que ambos são iguais
        assertEquals(ID, response.getId()); //assegure que ambos são iguais
        assertEquals(NOME, response.getNome()); //assegure que ambos são iguais
        assertEquals(UF, response.getUf());//assegure que ambos são iguais
    }
    @Test
    void whenFindByIdThenReturnAnObjectNotFoundException() {
        when(cidadeRepository.findById(anyLong())).thenThrow(new ObjectNotFoundException(OBJETO_NAO_ENCONTRADO_NO_ID));
        try {
            cidadeServiceImpl.findById(ID);
        } catch (Exception ex){
            assertEquals(ObjectNotFoundException.class, ex.getClass()); //assegure que ambos são iguais
            assertEquals(OBJETO_NAO_ENCONTRADO_NO_ID, ex.getMessage()); //assegure que as messagens de erro sejam iguais
        }
    }
    @Test
    void whenFindAllThenReturnAnListOfCidades() {
        when(cidadeRepository.findAll()).thenReturn(List.of(cidade));

        List<Cidade> response = cidadeServiceImpl.findAll();
        assertNotNull((response)); //assegura que não será null
        assertEquals(1, response.size()); //assegure que o tamanho da lista seja 1
        assertEquals(Cidade.class, response.get(INDEX).getClass()); //assegure que o objo 0 seja da classe cidade
        assertEquals(ID, response.get(INDEX).getId());
        assertEquals(NOME, response.get(INDEX).getNome());
        assertEquals(UF, response.get(INDEX).getUf());
    }

    @Test
    void whenCreateThenReturnSuccess() {
        when(cidadeRepository.save(any())).thenReturn(cidade);
        Cidade response = cidadeServiceImpl.create(cidadeDTO);

        assertNotNull(response);
        assertEquals(Cidade.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(NOME, response.getNome());
        assertEquals(UF, response.getUf());
    }

    @Test
    void whenCreateThenReturnDataIntegrityViolationException() {
        try {
            optionalCidade.get().setNome(null);
            optionalCidade.get().setUf(null);
            cidadeServiceImpl.create(cidadeDTO);
        } catch (Exception ex){
            assertEquals(DataIntegrityViolationException.class, ex.getClass());
        }
    }

    @Test
    void whenUpdateThenReturnSuccess() {
        when(cidadeRepository.save(any())).thenReturn(cidade);
        Cidade response = cidadeServiceImpl.update(cidadeDTO);

        assertNotNull(response);
        assertEquals(Cidade.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(NOME, response.getNome());
        assertEquals(UF, response.getUf());
    }
    @Test
    void whenUpdateThenReturnDataIntegrityViolationException() {
        try {
            optionalCidade.get().setNome(null);
            optionalCidade.get().setUf(null);
            cidadeServiceImpl.create(cidadeDTO);
        } catch (Exception ex){
            assertEquals(DataIntegrityViolationException.class, ex.getClass());
        }
    }
    @Test
    void deleteWhitSuccess() {
        when(cidadeRepository.findById(anyLong())).thenReturn(optionalCidade);
        doNothing().when(cidadeRepository).deleteById(anyLong()); //não faça nada quando o metodo for chamado c/ um Long
        cidadeServiceImpl.delete(ID);
        verify(cidadeRepository, times(1)).deleteById(anyLong());
        //verifica quantas vezes o metodo foi chamado e espera que o metodo delete seja chamado uma vez
    }
    @Test
    void deleteWhitObjectNotFoundException(){
        when(cidadeRepository.findById(anyLong()))
                .thenThrow(new ObjectNotFoundException(OBJETO_NAO_ENCONTRADO_NO_ID));
        try {
            cidadeServiceImpl.delete(ID);
        } catch (Exception ex) {
            assertEquals(ObjectNotFoundException.class, ex.getClass());
            assertEquals(OBJETO_NAO_ENCONTRADO_NO_ID, ex.getMessage());
        }
    }
    private void startUser() {
        cidade = new Cidade(ID, NOME, UF);
        cidadeDTO = new CidadeDTO(ID, NOME, UF);
        optionalCidade =  Optional.of(new Cidade(ID, NOME, UF));
    }
}