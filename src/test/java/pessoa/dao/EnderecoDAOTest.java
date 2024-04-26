package pessoa.dao;

import br.com.pessoa.dao.EnderecoDAO;
import br.com.pessoa.model.Endereco;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class EnderecoDAOTest {

    @Mock
    private EntityManager em;

    @InjectMocks
    private EnderecoDAO enderecoDAO;

    @Test
    public void testSalvarEnderecoNovo() {
        Endereco novoEndereco = new Endereco();
        enderecoDAO.salvar(novoEndereco);
        verify(em).persist(novoEndereco);
    }

    @Test
    public void testSalvarEnderecoExistente() {
        Endereco enderecoExistente = new Endereco();
        enderecoExistente.setId(1);
        when(em.merge(enderecoExistente)).thenReturn(enderecoExistente);

        Endereco retorno = enderecoDAO.salvar(enderecoExistente);
        verify(em).merge(enderecoExistente);
        assertEquals(enderecoExistente, retorno);
    }

    @Test
    public void testBuscarPorId() {
        Endereco endereco = new Endereco();
        endereco.setId(1);
        when(em.find(Endereco.class, 1)).thenReturn(endereco);

        Endereco encontrado = enderecoDAO.buscarPorId(1);
        verify(em).find(Endereco.class, 1);
        assertEquals(endereco, encontrado);
    }

    @Test
    public void testBuscarTodos() {
        List<Endereco> enderecos = new ArrayList<>();
        enderecos.add(new Endereco());
        TypedQuery<Endereco> typedQueryMock = mock(TypedQuery.class);
        when(em.createQuery(anyString(), eq(Endereco.class))).thenReturn(typedQueryMock);
        when(typedQueryMock.getResultList()).thenReturn(enderecos);

        List<Endereco> resultado = enderecoDAO.buscarTodos();
        assertNotNull(resultado);
        assertFalse(resultado.isEmpty());
    }


    @Test
    public void testRemover() {
        Endereco endereco = new Endereco();
        endereco.setId(1);
        doNothing().when(em).remove(any(Endereco.class));

        enderecoDAO.remover(endereco);
        verify(em).remove(any(Endereco.class));
    }
}
