package br.com.homeoffice.apigerirpessoas.services.implementation;

import br.com.homeoffice.apigerirpessoas.domain.Cidade;
import br.com.homeoffice.apigerirpessoas.domain.Endereco;
import br.com.homeoffice.apigerirpessoas.domain.dto.EnderecoDTO;
import br.com.homeoffice.apigerirpessoas.repositories.EnderecoRepository;
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
class EnderecoServiceImplTest {
    public static final long ID = 1L;
    public static final String LOGRADOURO ="Joao Filho";
    public static final String NUMERO = "333";
    public static final String COMPLEMENTO = "B";
    public static final String OBJETO_NAO_ENCONTRADO_NO_ID = "Objeto não encontrado no id: ";
    public static final int INDEX = 0;
    @InjectMocks
    private EnderecoServiceImpl enderecoServiceImpl;
    @Mock
    private EnderecoRepository enderecoRepository;
    @Mock
    private ModelMapper mapper;
    private Endereco endereco;
    private EnderecoDTO enderecoDTO;
    private Optional<Endereco> optionalEndereco;
    private Cidade cidade;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startUser();
    }
    @Test
    void whenFindByIdThenReturnEnderecoInstance() {
        when(enderecoRepository.findById(anyLong())).thenReturn(optionalEndereco);
        Endereco response = enderecoServiceImpl.findById(ID);
        assertNotNull(response); //assegura que não será null
        assertEquals(Endereco.class, response.getClass()); //assegure que ambos são iguais
        assertEquals(ID, response.getId()); //assegure que ambos são iguais
        assertEquals(LOGRADOURO, response.getLogradouro()); //assegure que ambos são iguais
        assertEquals(NUMERO, response.getNumero());//assegure que ambos são iguais
        assertEquals(COMPLEMENTO, response.getComplemento());//assegure que ambos são iguais
        assertEquals(cidade.getId(), response.getCidade().getId());//assegure que ambos são iguais
        assertEquals(Cidade.class, response.getCidade().getClass());//assegure que ambos são iguais

    }
    @Test
    void whenFindByIdThenReturnAnObjectNotFoundException() {
        when(enderecoRepository.findById(anyLong())).thenThrow(new ObjectNotFoundException(OBJETO_NAO_ENCONTRADO_NO_ID));
        try {
            enderecoServiceImpl.findById(ID);
        } catch (Exception ex){
            assertEquals(ObjectNotFoundException.class, ex.getClass()); //assegure que ambos são iguais
            assertEquals(OBJETO_NAO_ENCONTRADO_NO_ID, ex.getMessage()); //assegure que as messagens de erro sejam iguais
        }
    }
    @Test
    void whenFindAllThenReturnAnListOfEnderecos() {
        when(enderecoRepository.findAll()).thenReturn(List.of(endereco));

        List<Endereco> response = enderecoServiceImpl.findAll();
        assertNotNull((response)); //assegura que não será null
        assertEquals(1, response.size()); //assegure que o tamanho da lista seja 1
        assertEquals(Endereco.class, response.get(INDEX).getClass()); //assegure que o objo 0 seja da classe Endereco
        assertEquals(ID, response.get(INDEX).getId()); //assegure que ambos são iguais
        assertEquals(LOGRADOURO, response.get(INDEX).getLogradouro()); //assegure que ambos são iguais
        assertEquals(NUMERO, response.get(INDEX).getNumero());//assegure que ambos são iguais
        assertEquals(COMPLEMENTO, response.get(INDEX).getComplemento());//assegure que ambos são iguais
        assertEquals(cidade.getId(), response.get(INDEX).getCidade().getId());//assegure que ambos são iguais
        assertEquals(Cidade.class, response.get(INDEX).getCidade().getClass());//assegure que ambos são iguais
    }

    @Test
    void whenCreateThenReturnSuccess() {
        when(enderecoRepository.save(any())).thenReturn(endereco);
        Endereco response = enderecoServiceImpl.create(enderecoDTO);

        assertNotNull(response);
        assertEquals(Endereco.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(LOGRADOURO, response.getLogradouro());
        assertEquals(NUMERO, response.getNumero());
        assertEquals(COMPLEMENTO, response.getComplemento());
        assertEquals(cidade.getId(), response.getCidade().getId());
        assertEquals(Cidade.class, response.getCidade().getClass());
    }

    @Test
    void whenCreateThenReturnDataIntegrityViolationException() {
        try {
            optionalEndereco.get().setLogradouro(null);
            optionalEndereco.get().setNumero(null);
            enderecoServiceImpl.create(enderecoDTO);
        } catch (Exception ex){
            assertEquals(DataIntegrityViolationException.class, ex.getClass());
        }
    }

    @Test
    void whenUpdateThenReturnSuccess() {
        when(enderecoRepository.save(any())).thenReturn(endereco);
        Endereco response = enderecoServiceImpl.update(enderecoDTO);

        assertNotNull(response);
        assertEquals(Endereco.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(LOGRADOURO, response.getLogradouro());
        assertEquals(NUMERO, response.getNumero());
        assertEquals(COMPLEMENTO, response.getComplemento());
        assertEquals(cidade.getId(), response.getCidade().getId());
        assertEquals(Cidade.class, response.getCidade().getClass());
    }
    @Test
    void whenUpdateThenReturnDataIntegrityViolationException() {
        try {
            optionalEndereco.get().setLogradouro(null);
            optionalEndereco.get().setNumero(null);
            enderecoServiceImpl.create(enderecoDTO);
        } catch (Exception ex){
            assertEquals(DataIntegrityViolationException.class, ex.getClass());
        }
    }
    @Test
    void deleteWhitSuccess() {
        when(enderecoRepository.findById(anyLong())).thenReturn(optionalEndereco);
        doNothing().when(enderecoRepository).deleteById(anyLong()); //não faça nada quando o metodo for chamado c/ um Long
        enderecoServiceImpl.delete(ID);
        verify(enderecoRepository, times(1)).deleteById(anyLong());
        //verifica quantas vezes o metodo foi chamado e espera que o metodo delete seja chamado uma vez
    }
    @Test
    void deleteWhitObjectNotFoundException(){
        when(enderecoRepository.findById(anyLong()))
                .thenThrow(new ObjectNotFoundException(OBJETO_NAO_ENCONTRADO_NO_ID));
        try {
            enderecoServiceImpl.delete(ID);
        } catch (Exception ex) {
            assertEquals(ObjectNotFoundException.class, ex.getClass());
            assertEquals(OBJETO_NAO_ENCONTRADO_NO_ID, ex.getMessage());
        }
    }
    private void startUser() {
        cidade = new Cidade(1L, "Presidente Prudente", "SP");
        endereco = new Endereco(ID, LOGRADOURO,NUMERO,COMPLEMENTO,cidade,null);
        enderecoDTO = new EnderecoDTO(ID, LOGRADOURO,NUMERO,COMPLEMENTO,cidade,null);
        optionalEndereco =  Optional.of(new Endereco(ID, LOGRADOURO,NUMERO,COMPLEMENTO,cidade,null));
    }

}