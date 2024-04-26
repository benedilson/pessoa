package pessoa.model;

import br.com.pessoa.model.Pessoa;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import br.com.pessoa.model.Endereco;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PessoaTest {

    private Pessoa pessoa;

    @BeforeEach
    void setUp() {
        pessoa = new Pessoa();
        pessoa.setNome("João da Silva");
        pessoa.setIdade(new Date());
        pessoa.setSexo("M");

        List<Endereco> enderecos = new ArrayList<>();
        Endereco endereco = new Endereco();
        enderecos.add(endereco);

        pessoa.setEnderecos(enderecos);
    }

    @Test
    void getEnderecoPrincipal_ReturnsFirstEndereco_WhenEnderecosIsNotEmpty() {
        Endereco enderecoPrincipal = pessoa.getEnderecoPrincipal();

        assertNotNull(enderecoPrincipal, "Endereço principal deve ser não nulo quando a lista de endereços não está vazia");
        assertEquals(pessoa.getEnderecos().get(0), enderecoPrincipal, "Endereço principal deve ser o primeiro da lista de endereços");
    }

    @Test
    void getEnderecoPrincipal_ReturnsNull_WhenEnderecosIsEmpty() {
        pessoa.setEnderecos(new ArrayList<>());

        Endereco enderecoPrincipal = pessoa.getEnderecoPrincipal();

        assertNull(enderecoPrincipal, "Endereço principal deve ser nulo quando a lista de endereços está vazia");
    }
}
