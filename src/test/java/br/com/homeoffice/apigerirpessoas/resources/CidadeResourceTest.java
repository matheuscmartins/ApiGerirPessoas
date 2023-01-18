package br.com.homeoffice.apigerirpessoas.resources;

import br.com.homeoffice.apigerirpessoas.domain.Cidade;
import br.com.homeoffice.apigerirpessoas.domain.dto.CidadeDTO;
import br.com.homeoffice.apigerirpessoas.services.CidadeService;
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

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
@SpringBootTest
class CidadeResourceTest {
    public static final long ID = 1L;
    public static final String NOME ="Presidente Prudente";
    public static final String UF = "SP";
    public static final String OBJETO_NAO_ENCONTRADO_NO_ID = "Objeto não encontrado no id: ";
    public static final int INDEX = 0;

    @InjectMocks
    private CidadeResource cidadeResource;
    @Mock
    private ModelMapper mapper;
    @Mock
    private CidadeService cidadeService;
    private Cidade cidade = new Cidade();
    private CidadeDTO cidadeDTO = new CidadeDTO();
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startCidade();
    }

    @Test
    void whenFindByIdThenReturnSuccess() {
        when(cidadeService.findById(anyLong())).thenReturn(cidade); //verifica se irá trazer um obj cidade
        when(mapper.map(any(), any())).thenReturn(cidadeDTO); // verifica se a conversão retornara um objCidadeDTO

        ResponseEntity<CidadeDTO> response = cidadeResource.findById(ID);
        assertNotNull(response); //assegura que a resposta não será nula
        assertNotNull(response.getBody()); //assegura que o corpo da resposta não é null
        assertEquals(ResponseEntity.class, response.getClass()); //assegura que srão o mesmo tipo de class

        assertEquals(CidadeDTO.class, response.getBody().getClass());
        //assegura que o body de retorno é o mesmo da Classe CidadeDTO

        assertEquals(ID, response.getBody().getId());
        assertEquals(NOME, response.getBody().getNome());
        assertEquals(UF, response.getBody().getUf());
    }
    @Test
    void whenFindAllThenReturnAListOfCidadeDTO() {
        when(cidadeService.findAll()).thenReturn(List.of(cidade));
        when(mapper.map(any(), any())).thenReturn(cidadeDTO); //assegura que a conversão trará obj CidadeDTO
        ResponseEntity<List<CidadeDTO>> response = cidadeResource.findAll();

        assertNotNull(response); //assegura que não será null
        assertNotNull(response.getBody()); //assegura que o body não sera null
        assertEquals(HttpStatus.OK, response.getStatusCode());//assegura que o Status do request seja OK
        assertEquals(ResponseEntity.class, response.getClass()); //assegura que o retorno será da classe ResponseEntity
        assertEquals(ArrayList.class, response.getBody().getClass()); //assegura que o body vira um arrayList

        assertEquals(CidadeDTO.class, response.getBody().get(INDEX).getClass());
        //assegura que o INDEX da lista será um objo CidadeDTO

        assertEquals(ID, response.getBody().get(INDEX).getId());
        assertEquals(NOME, response.getBody().get(INDEX).getNome());
        assertEquals(UF, response.getBody().get(INDEX).getUf());
        //assegura que os dados de retorno são iguais aos pasados por parametro
    }
    @Test
    void whenCreateThenReturnCreated() {
        when(cidadeService.create(any())).thenReturn((cidade));
        ResponseEntity<CidadeDTO> response = cidadeResource.create(cidadeDTO);

        assertEquals(ResponseEntity.class, response.getClass()); //assegura que é uma classe ResponseEntity
        assertEquals(HttpStatus.CREATED, response.getStatusCode()); //assegura se o status é CREATED
        assertNotNull(response.getHeaders().get("Location")); //assegura que no headers vem a chave valor Location
    }
    @Test
    void whenUpdateThenReturnSuccess() {
        when(cidadeService.update(cidadeDTO)).thenReturn(cidade);
        when(mapper.map(any(), any())).thenReturn(cidadeDTO); //assgura que a conversão sera de obj CidadeDTO

        ResponseEntity<CidadeDTO> response = cidadeResource.update(ID, cidadeDTO);
        assertNotNull(response); //assegura que não seja null
        assertNotNull(response.getBody()); //assegura que o body não sera null
        assertEquals(HttpStatus.OK, response.getStatusCode()); //assegura que o status é 200
        assertEquals(ResponseEntity.class, response.getClass()); //assegura que é uma classe de ResponseEntity
        assertEquals(CidadeDTO.class, response.getBody().getClass()); //assegura que o body é uma classe CidadeDTO

        assertEquals(ID, response.getBody().getId());
        assertEquals(NOME, response.getBody().getNome());
        assertEquals(UF, response.getBody().getUf());
        //assegura que os dados de retorno são iguais aos pasados por parametro
    }

    @Test
    void whenDeleteThenReturnSuccess() {
        doNothing().when(cidadeService).delete(anyLong()); //não faça nada quando o metodo chamar o delete
        ResponseEntity<CidadeDTO> response = cidadeResource.delete(ID);

        assertNotNull(response); //assegura que não seja null
        assertEquals(ResponseEntity.class, response.getClass()); //assegura que é uma classe de ResponseEntity
        verify(cidadeService, times(1)).delete(anyLong()); //assegura que o metedo seja chamado 1x
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode()); //assegura que o status é de No_Content
    }
    private void startCidade() {
        cidade = new Cidade(ID, NOME, UF);
        cidadeDTO = new CidadeDTO(ID, NOME, UF);
    }
}