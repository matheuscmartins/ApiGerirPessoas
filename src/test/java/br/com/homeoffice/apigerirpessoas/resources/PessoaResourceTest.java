package br.com.homeoffice.apigerirpessoas.resources;

import br.com.homeoffice.apigerirpessoas.domain.Pessoa;
import br.com.homeoffice.apigerirpessoas.domain.dto.PessoaDTO;
import br.com.homeoffice.apigerirpessoas.services.PessoaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@SpringBootTest
class PessoaResourceTest {
    public static final long ID = 1L;
    public static final String NOME = "Maria Cruz";
    public static final LocalDate DATANASCIMENTO = LocalDate.parse("1987-01-28");
    public static final String OBJETO_NAO_ENCONTRADO_NO_ID = "Objeto não encontrado no id: ";
    public static final int INDEX = 0;

    @InjectMocks
    private PessoaResource pessoaResource;
    @Mock
    private ModelMapper mapper;
    @Mock
    private PessoaService pessoaService;
    private Pessoa pessoa = new Pessoa();
    private PessoaDTO pessoaDTO = new PessoaDTO();
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startPessoa();
    }
    @Test
    void whenFindByIdThenReturnSuccess() {
        when(pessoaService.findById(anyLong())).thenReturn(pessoa); //verifica se irá trazer um obj pessoa
        when(mapper.map(any(), any())).thenReturn(pessoaDTO); // verifica se a conversão retornara um objpessoaDTODTO

        ResponseEntity<PessoaDTO> response = pessoaResource.findById(ID);
        assertNotNull(response); //assegura que a resposta não será nula
        assertNotNull(response.getBody()); //assegura que o corpo da resposta não é null
        assertEquals(ResponseEntity.class, response.getClass()); //assegura que srão o mesmo tipo de class

        assertEquals(PessoaDTO.class, response.getBody().getClass());
        //assegura que o body de retorno é o mesmo da Classe PessoaDTO

        assertEquals(ID, response.getBody().getId());
        assertEquals(NOME, response.getBody().getNome());
        assertEquals(DATANASCIMENTO, response.getBody().getDataNascimento());
    }
    @Test
    void whenFindAllThenReturnAListOfPessoaDTO() {
        when(pessoaService.findAll()).thenReturn(List.of(pessoa));
        when(mapper.map(any(), any())).thenReturn(pessoaDTO); //assegura que a conversão trará obj pessoaDTO
        ResponseEntity<List<PessoaDTO>> response = pessoaResource.findAll();

        assertNotNull(response); //assegura que não será null
        assertNotNull(response.getBody()); //assegura que o body não sera null
        assertEquals(HttpStatus.OK, response.getStatusCode());//assegura que o Status do request seja OK
        assertEquals(ResponseEntity.class, response.getClass()); //assegura que o retorno será da classe ResponseEntity
        assertEquals(ArrayList.class, response.getBody().getClass()); //assegura que o body vira um arrayList

        assertEquals(PessoaDTO.class, response.getBody().get(INDEX).getClass());
        //assegura que o INDEX da lista será um objo PessoaDTO

        assertEquals(ID, response.getBody().get(INDEX).getId());
        assertEquals(NOME, response.getBody().get(INDEX).getNome());
        assertEquals(DATANASCIMENTO, response.getBody().get(INDEX).getDataNascimento());
        //assegura que os dados de retorno são iguais aos pasados por parametro
    }
    @Test
    void whenCreateThenReturnCreated() {
        when(pessoaService.create(any())).thenReturn((pessoa));
        ResponseEntity<PessoaDTO> response = pessoaResource.create(pessoaDTO);

        assertEquals(ResponseEntity.class, response.getClass()); //assegura que é uma classe ResponseEntity
        assertEquals(HttpStatus.CREATED, response.getStatusCode()); //assegura se o status é CREATED
        assertNotNull(response.getHeaders().get("Location")); //assegura que no headers vem a chave valor Location
    }
    @Test
    void whenUpdateThenReturnSuccess() {
        when(pessoaService.update(pessoaDTO)).thenReturn(pessoa);
        when(mapper.map(any(), any())).thenReturn(pessoaDTO); //assgura que a conversão sera de obj pessoaDTO

        ResponseEntity<PessoaDTO> response = pessoaResource.update(ID, pessoaDTO);
        assertNotNull(response); //assegura que não seja null
        assertNotNull(response.getBody()); //assegura que o body não sera null
        assertEquals(HttpStatus.OK, response.getStatusCode()); //assegura que o status é 200
        assertEquals(ResponseEntity.class, response.getClass()); //assegura que é uma classe de ResponseEntity
        assertEquals(PessoaDTO.class, response.getBody().getClass()); //assegura que o body é uma classe pessoaDTO

        assertEquals(ID, response.getBody().getId());
        assertEquals(NOME, response.getBody().getNome());
        assertEquals(DATANASCIMENTO, response.getBody().getDataNascimento());
        //assegura que os dados de retorno são iguais aos pasados por parametro
    }

    @Test
    void whenDeleteThenReturnSuccess() {
        doNothing().when(pessoaService).delete(anyLong()); //não faça nada quando o metodo chamar o delete
        ResponseEntity<PessoaDTO> response = pessoaResource.delete(ID);

        assertNotNull(response); //assegura que não seja null
        assertEquals(ResponseEntity.class, response.getClass()); //assegura que é uma classe de ResponseEntity
        verify(pessoaService, times(1)).delete(anyLong()); //assegura que o metedo seja chamado 1x
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode()); //assegura que o status é de No_Content
    }
    private void startPessoa() {
        pessoa = new Pessoa(ID, NOME, DATANASCIMENTO);
        pessoaDTO = new PessoaDTO(ID, NOME, DATANASCIMENTO);
    }
}