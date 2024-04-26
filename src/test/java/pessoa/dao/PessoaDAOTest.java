package pessoa.dao;

import br.com.pessoa.dao.PessoaDAO;
import br.com.pessoa.util.FacesContextUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import br.com.pessoa.model.Pessoa;
import javax.persistence.EntityManager;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PessoaDAOTest {

    @Mock
    private EntityManager em;

    @Mock
    private FacesContextUtil facesContextUtil;

    @InjectMocks
    private PessoaDAO pessoaDAO;

    @Test
    public void testSalvarNovaPessoa() {
        Pessoa pessoa = new Pessoa();
        pessoa.setNome("Teste");

        doNothing().when(em).persist(any(Pessoa.class));
        doNothing().when(facesContextUtil).addInfoMessage(anyString());

        Pessoa savedPessoa = pessoaDAO.salvar(pessoa);

        assertNotNull(savedPessoa);
        verify(em).persist(any(Pessoa.class));
        verify(facesContextUtil).addInfoMessage("Dados salvos com sucesso");
    }
}
