package br.com.pessoa.service;

import br.com.pessoa.dao.EnderecoDAO;
import br.com.pessoa.model.Endereco;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.List;

@Stateless
public class EnderecoService {

    @EJB
    private EnderecoDAO enderecoDAO;

    public Endereco salvarEndereco(Endereco endereco) {
        endereco.setCep(endereco.getCep().replace("-", ""));
        return enderecoDAO.salvar(endereco);
    }

    public Endereco buscarEnderecoPorId(Integer id) {
        return enderecoDAO.buscarPorId(id);
    }

    public List<Endereco> listarEnderecos() {
        return enderecoDAO.buscarTodos();
    }

    public void removerEndereco(Integer id) {
        Endereco endereco = enderecoDAO.buscarPorId(id);
        if (endereco != null) {
            enderecoDAO.remover(endereco);
        }
    }

    public Endereco preencherEnderecoAutomaticamente(String cep) {
        Endereco endereco = new Endereco();

        if (cep != null && !cep.isEmpty()) {
            try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
                HttpGet request = new HttpGet("https://viacep.com.br/ws/" + cep + "/json/");
                try (CloseableHttpResponse response = httpClient.execute(request)) {
                    if (response.getStatusLine().getStatusCode() == 200) {
                        String json = EntityUtils.toString(response.getEntity());
                        JsonReader jsonReader = Json.createReader(new StringReader(json));
                        JsonObject jsonObject = jsonReader.readObject();
                        endereco.setCep(cep);
                        endereco.setLogradouro(jsonObject.getString("logradouro", ""));
                        endereco.setBairro(jsonObject.getString("bairro", ""));
                        endereco.setCidade(jsonObject.getString("localidade", ""));
                        endereco.setEstado(jsonObject.getString("uf", ""));

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        return endereco;
    }
}