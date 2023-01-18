package br.com.homeoffice.apigerirpessoas.resources;

import br.com.homeoffice.apigerirpessoas.domain.Cidade;
import br.com.homeoffice.apigerirpessoas.domain.Endereco;
import br.com.homeoffice.apigerirpessoas.domain.Pessoa;
import br.com.homeoffice.apigerirpessoas.domain.PessoaEndereco;
import br.com.homeoffice.apigerirpessoas.domain.dto.PessoaEnderecoDTO;
import br.com.homeoffice.apigerirpessoas.domain.enums.EnderecoStatus;
import br.com.homeoffice.apigerirpessoas.services.PessoaEnderecoService;
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
class PessoaEnderecoResourceTest {
    public static final long ID = 1L;
    public static final EnderecoStatus ENDERECO_STATUS = EnderecoStatus.valueOf(1);
    public static final String OBJETO_NAO_ENCONTRADO_NO_ID = "Objeto não encontrado no id: ";
    public static final int INDEX = 0;
    @InjectMocks
    private PessoaEnderecoResource pessoaEnderecoResource;
    @Mock
    private ModelMapper mapper;
    @Mock
    private PessoaEnderecoService pessoaEnderecoService;
    private Cidade cidade = new Cidade();
    private Pessoa pessoa = new Pessoa();
    private Endereco endereco = new Endereco();
    private PessoaEndereco pessoaEndereco = new PessoaEndereco();
    private PessoaEnderecoDTO pessoaEnderecoDTO = new PessoaEnderecoDTO();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startPessoaEndereco();
    }
    @Test
    void whenFindByIdThenReturnSuccess() {
        when(pessoaEnderecoService.findById(anyLong())).thenReturn(pessoaEndereco); //verifica se irá trazer um obj pessoaEndereco
        when(mapper.map(any(), any())).thenReturn(pessoaEnderecoDTO); // verifica se a conversão retornara um pessoaEnderecoDTO

        ResponseEntity<PessoaEnderecoDTO> response = pessoaEnderecoResource.findById(ID);
        assertNotNull(response); //assegura que a resposta não será nula
        assertNotNull(response.getBody()); //assegura que o corpo da resposta não é null
        assertEquals(ResponseEntity.class, response.getClass()); //assegura que srão o mesmo tipo de class

        assertEquals(PessoaEnderecoDTO.class, response.getBody().getClass());
        //assegura que o body de retorno é o mesmo da Classe CidadeDTO

        assertEquals(ID, response.getBody().getId());
        assertEquals(ENDERECO_STATUS, response.getBody().getEnderecoStatus());
        assertEquals(endereco.getId(), response.getBody().getEndereco().getId());
        assertEquals(pessoa.getId(), response.getBody().getPessoa().getId());
        assertEquals(Pessoa.class, response.getBody().getPessoa().getClass());
        assertEquals(Endereco.class, response.getBody().getEndereco().getClass());
    }
    @Test
    void whenFindAllThenReturnAListOfPessoaEnderecoDTO() {
        when(pessoaEnderecoService.findAll()).thenReturn(List.of(pessoaEndereco));
        when(mapper.map(any(), any())).thenReturn(pessoaEnderecoDTO); //assegura que a conversão trará obj pessoaEnderecoDTO
        ResponseEntity<List<PessoaEnderecoDTO>> response = pessoaEnderecoResource.findAll();

        assertNotNull(response); //assegura que não será null
        assertNotNull(response.getBody()); //assegura que o body não sera null
        assertEquals(HttpStatus.OK, response.getStatusCode());//assegura que o Status do request seja OK
        assertEquals(ResponseEntity.class, response.getClass()); //assegura que o retorno será da classe ResponseEntity
        assertEquals(ArrayList.class, response.getBody().getClass()); //assegura que o body vira um arrayList

        assertEquals(PessoaEnderecoDTO.class, response.getBody().get(INDEX).getClass());
        //assegura que o INDEX da lista será um objo CidadeDTO

        assertEquals(ID, response.getBody().get(INDEX).getId());
        assertEquals(ENDERECO_STATUS, response.getBody().get(INDEX).getEnderecoStatus());
        assertEquals(endereco.getId(), response.getBody().get(INDEX).getEndereco().getId());
        assertEquals(pessoa.getId(), response.getBody().get(INDEX).getPessoa().getId());
        assertEquals(Endereco.class, response.getBody().get(INDEX).getEndereco().getClass());
        assertEquals(Pessoa.class, response.getBody().get(INDEX).getPessoa().getClass());
        //assegura que os dados de retorno são iguais aos pasados por parametro
    }
    @Test
    void whenCreateThenReturnCreated() {
        when(pessoaEnderecoService.create(any())).thenReturn((pessoaEndereco));
        ResponseEntity<PessoaEnderecoDTO> response = pessoaEnderecoResource.create(pessoaEnderecoDTO);

        assertEquals(ResponseEntity.class, response.getClass()); //assegura que é uma classe ResponseEntity
        assertEquals(HttpStatus.CREATED, response.getStatusCode()); //assegura se o status é CREATED
        assertNotNull(response.getHeaders().get("Location")); //assegura que no headers vem a chave valor Location
    }
    @Test
    void whenUpdateThenReturnSuccess() {
        when(pessoaEnderecoService.update(pessoaEnderecoDTO)).thenReturn(pessoaEndereco);
        when(mapper.map(any(), any())).thenReturn(pessoaEnderecoDTO); //assgura que a conversão sera de obj pessoaEnderecoDTO

        ResponseEntity<PessoaEnderecoDTO> response = pessoaEnderecoResource.update(ID, pessoaEnderecoDTO);
        assertNotNull(response); //assegura que não seja null
        assertNotNull(response.getBody()); //assegura que o body não sera null
        assertEquals(HttpStatus.OK, response.getStatusCode()); //assegura que o status é 200
        assertEquals(ResponseEntity.class, response.getClass()); //assegura que é uma classe de ResponseEntity
        assertEquals(PessoaEnderecoDTO.class, response.getBody().getClass()); //assegura que o body é uma classe PessoaEnderecoDTO

        assertEquals(ID, response.getBody().getId());
        assertEquals(ENDERECO_STATUS, response.getBody().getEnderecoStatus());
        assertEquals(endereco.getId(), response.getBody().getEndereco().getId());
        assertEquals(pessoa.getId(), response.getBody().getPessoa().getId());
        assertEquals(Pessoa.class, response.getBody().getPessoa().getClass());
        assertEquals(Endereco.class, response.getBody().getEndereco().getClass());
        //assegura que os dados de retorno são iguais aos pasados por parametro
    }

    @Test
    void whenDeleteThenReturnSuccess() {
        doNothing().when(pessoaEnderecoService).delete(anyLong()); //não faça nada quando o metodo chamar o delete
        ResponseEntity<PessoaEnderecoDTO> response = pessoaEnderecoResource.delete(ID);

        assertNotNull(response); //assegura que não seja null
        assertEquals(ResponseEntity.class, response.getClass()); //assegura que é uma classe de ResponseEntity
        verify(pessoaEnderecoService, times(1)).delete(anyLong()); //assegura que o metedo seja chamado 1x
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode()); //assegura que o status é de No_Content
    }
    private void startPessoaEndereco() {
        cidade = new Cidade(1L, "Presidente Prudente", "SP");
        pessoa = new Pessoa(1L, "Maria Cruz", LocalDate.parse("1987-01-28"));
        endereco = new Endereco(1L, "Joao Filho", "33",
                "", cidade, null);
        pessoaEndereco = new PessoaEndereco(ID, ENDERECO_STATUS,pessoa,endereco);
        pessoaEnderecoDTO = new PessoaEnderecoDTO(ID, ENDERECO_STATUS,pessoa,endereco);
    }
}