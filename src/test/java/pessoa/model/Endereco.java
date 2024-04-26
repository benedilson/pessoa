package pessoa.model;

import br.com.pessoa.model.Endereco;
import br.com.pessoa.model.Pessoa;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class EnderecoTest {

    private Endereco endereco;

    @BeforeEach
    void setUp() {
        endereco = new Endereco();
        endereco.setEstado("SP");
        endereco.setCidade("São Paulo");
        endereco.setLogradouro("Avenida Paulista");
        endereco.setBairro("Bela Vista");
        endereco.setNumero(1000);
        endereco.setCep("01310-000");

        Pessoa pessoa = new Pessoa();
        pessoa.setNome("João da Silva");
        endereco.setPessoa(pessoa);
    }

    @Test
    void propriedadesEstaoCorretas() {
        assertAll("Propriedades devem ser retornadas corretamente",
                () -> assertEquals("SP", endereco.getEstado()),
                () -> assertEquals("São Paulo", endereco.getCidade()),
                () -> assertEquals("Avenida Paulista", endereco.getLogradouro()),
                () -> assertEquals("Bela Vista", endereco.getBairro()),
                () -> assertEquals(1000, endereco.getNumero()),
                () -> assertEquals("01310-000", endereco.getCep()),
                () -> assertNotNull(endereco.getPessoa(), "Pessoa não deve ser nula"),
                () -> assertEquals("João da Silva", endereco.getPessoa().getNome())
        );
    }
}
