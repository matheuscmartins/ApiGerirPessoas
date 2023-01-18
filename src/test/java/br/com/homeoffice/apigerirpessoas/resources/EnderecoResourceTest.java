package br.com.homeoffice.apigerirpessoas.resources;

import br.com.homeoffice.apigerirpessoas.domain.Cidade;
import br.com.homeoffice.apigerirpessoas.domain.Endereco;
import br.com.homeoffice.apigerirpessoas.domain.dto.EnderecoDTO;
import br.com.homeoffice.apigerirpessoas.services.EnderecoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@SpringBootTest
class EnderecoResourceTest {
    public static final long ID = 1L;
    public static final String LOGRADOURO ="Joao Filho";
    public static final String NUMERO = "333";
    public static final String COMPLEMENTO = "B";
    public static final String OBJETO_NAO_ENCONTRADO_NO_ID = "Objeto não encontrado no id: ";
    public static final int INDEX = 0;

    @InjectMocks
    private EnderecoResource enderecoResource;
    @Mock
    private ModelMapper mapper;
    @Mock
    private EnderecoService enderecoService;
    private Cidade cidade = new Cidade();
    private Endereco endereco= new Endereco();
    private EnderecoDTO enderecoDTO = new EnderecoDTO();
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startEndereco();
    }
    @Test
    void whenFindByIdThenReturnSuccess() {
        when(enderecoService.findById(anyLong())).thenReturn(endereco); //verifica se irá trazer um obj endereco
        when(mapper.map(any(), any())).thenReturn(enderecoDTO); // verifica se a conversão retornara um enderecoDTO

        ResponseEntity<EnderecoDTO> response = enderecoResource.findById(ID);
        assertNotNull(response); //assegura que a resposta não será nula
        assertNotNull(response.getBody()); //assegura que o corpo da resposta não é null
        assertEquals(ResponseEntity.class, response.getClass()); //assegura que srão o mesmo tipo de class

        assertEquals(EnderecoDTO.class, response.getBody().getClass());
        //assegura que o body de retorno é o mesmo da Classe EnderecoDTO

        assertEquals(ID, response.getBody().getId());
        assertEquals(LOGRADOURO, response.getBody().getLogradouro());
        assertEquals(NUMERO, response.getBody().getNumero());
        assertEquals(COMPLEMENTO, response.getBody().getComplemento());
        assertEquals(cidade.getId(), response.getBody().getCidade().getId());
        assertEquals(Cidade.class, response.getBody().getCidade().getClass());
    }
    @Test
    void whenFindAllThenReturnAListOfEnderecoDTO() {
        when(enderecoService.findAll()).thenReturn(List.of(endereco));
        when(mapper.map(any(), any())).thenReturn(enderecoDTO); //assegura que a conversão trará obj enderecoDTO
        ResponseEntity<List<EnderecoDTO>> response = enderecoResource.findAll();

        assertNotNull(response); //assegura que não será null
        assertNotNull(response.getBody()); //assegura que o body não sera null
        assertEquals(HttpStatus.OK, response.getStatusCode());//assegura que o Status do request seja OK
        assertEquals(ResponseEntity.class, response.getClass()); //assegura que o retorno será da classe ResponseEntity
        assertEquals(ArrayList.class, response.getBody().getClass()); //assegura que o body vira um arrayList

        assertEquals(EnderecoDTO.class, response.getBody().get(INDEX).getClass());
        //assegura que o INDEX da lista será um objo EnderecoDTO

        assertEquals(ID, response.getBody().get(INDEX).getId());
        assertEquals(LOGRADOURO, response.getBody().get(INDEX).getLogradouro());
        assertEquals(NUMERO, response.getBody().get(INDEX).getNumero());
        assertEquals(COMPLEMENTO, response.getBody().get(INDEX).getComplemento());
        assertEquals(Cidade.class, response.getBody().get(INDEX).getCidade().getClass());
        assertEquals(cidade.getId(), response.getBody().get(INDEX).getCidade().getId());
        //assegura que os dados de retorno são iguais aos pasados por parametro
    }
    @Test
    void whenCreateThenReturnCreated() {
        when(enderecoService.create(any())).thenReturn((endereco));
        ResponseEntity<EnderecoDTO> response = enderecoResource.create(enderecoDTO);

        assertEquals(ResponseEntity.class, response.getClass()); //assegura que é uma classe ResponseEntity
        assertEquals(HttpStatus.CREATED, response.getStatusCode()); //assegura se o status é CREATED
        assertNotNull(response.getHeaders().get("Location")); //assegura que no headers vem a chave valor Location
    }
    @Test
    void whenUpdateThenReturnSuccess() {
        when(enderecoService.update(enderecoDTO)).thenReturn(endereco);
        when(mapper.map(any(), any())).thenReturn(enderecoDTO); //assgura que a conversão sera de obj enderecoDTO

        ResponseEntity<EnderecoDTO> response = enderecoResource.update(ID, enderecoDTO);
        assertNotNull(response); //assegura que não seja null
        assertNotNull(response.getBody()); //assegura que o body não sera null
        assertEquals(HttpStatus.OK, response.getStatusCode()); //assegura que o status é 200
        assertEquals(ResponseEntity.class, response.getClass()); //assegura que é uma classe de ResponseEntity
        assertEquals(EnderecoDTO.class, response.getBody().getClass()); //assegura que o body é uma classe EnderecoDTO

        assertEquals(ID, response.getBody().getId());
        assertEquals(LOGRADOURO, response.getBody().getLogradouro());
        assertEquals(NUMERO, response.getBody().getNumero());
        assertEquals(COMPLEMENTO, response.getBody().getComplemento());
        assertEquals(cidade.getId(), response.getBody().getCidade().getId());
        assertEquals(Cidade.class, response.getBody().getCidade().getClass());
        //assegura que os dados de retorno são iguais aos pasados por parametro
    }

    @Test
    void whenDeleteThenReturnSuccess() {
        doNothing().when(enderecoService).delete(anyLong()); //não faça nada quando o metodo chamar o delete
        ResponseEntity<EnderecoDTO> response = enderecoResource.delete(ID);

        assertNotNull(response); //assegura que não seja null
        assertEquals(ResponseEntity.class, response.getClass()); //assegura que é uma classe de ResponseEntity
        verify(enderecoService, times(1)).delete(anyLong()); //assegura que o metedo seja chamado 1x
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode()); //assegura que o status é de No_Content
    }
    private void startEndereco() {
        cidade = new Cidade(1L, "Presidente Prudente", "SP");
        endereco = new Endereco(ID, LOGRADOURO,NUMERO,COMPLEMENTO,cidade,null);
        enderecoDTO = new EnderecoDTO(ID, LOGRADOURO,NUMERO,COMPLEMENTO,cidade,null);
    }
}