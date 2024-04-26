package pessoa.service;

import br.com.pessoa.service.PessoaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import br.com.pessoa.dao.PessoaDAO;
import br.com.pessoa.model.Pessoa;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class PessoaServiceTest {

    @Mock
    private PessoaDAO pessoaDAO;

    @InjectMocks
    private PessoaService pessoaService;

    private Pessoa pessoa;

    @BeforeEach
    void setUp() {
        pessoa = new Pessoa();
        pessoa.setId(1);
        pessoa.setNome("João");
    }

    @Test
    void testSalvarPessoa() {
        // Configurando o comportamento do mock
        when(pessoaDAO.salvar(any(Pessoa.class))).thenReturn(pessoa);

        // Ação
        Pessoa resultado = pessoaService.salvarPessoa(pessoa);

        // Verificação
        assertNotNull(resultado);
        assertEquals(pessoa.getNome(), resultado.getNome());
        verify(pessoaDAO).salvar(pessoa);
    }
}
