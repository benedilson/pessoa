package pessoa.bean;

import br.com.pessoa.bean.PessoaManagedBean;
import br.com.pessoa.model.Endereco;
import br.com.pessoa.model.Pessoa;
import br.com.pessoa.service.EnderecoService;
import br.com.pessoa.service.PessoaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PessoaManagedBeanTest {

    @Mock
    private PessoaService pessoaService;

    @Mock
    private EnderecoService enderecoService;

    @InjectMocks
    private PessoaManagedBean pessoaManagedBean;

    @BeforeEach
    void setUp() {
        pessoaManagedBean.setPessoa(new Pessoa());
        pessoaManagedBean.setEndereco(new Endereco());
    }

    @Test
    void testSalvar() {
        // Configurando os mocks
        when(pessoaService.salvarPessoa(any(Pessoa.class))).thenReturn(new Pessoa());
        when(enderecoService.salvarEndereco(any(Endereco.class))).thenReturn(new Endereco());

        // Executando o método a ser testado
        pessoaManagedBean.salvar();

        // Verificações
        verify(pessoaService).salvarPessoa(any(Pessoa.class));
        verify(enderecoService).salvarEndereco(any(Endereco.class));

        // Verifique se a pessoa e o endereço foram redefinidos após salvar
        assertAll("Deve limpar a pessoa e o endereço após salvar",
                () -> assertNull(pessoaManagedBean.getPessoa().getId()),
                () -> assertNull(pessoaManagedBean.getEndereco().getId())
        );
    }
}
