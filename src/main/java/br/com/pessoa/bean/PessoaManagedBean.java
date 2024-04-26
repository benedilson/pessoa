package br.com.pessoa.bean;

import br.com.pessoa.model.Endereco;
import br.com.pessoa.model.Pessoa;
import br.com.pessoa.service.EnderecoService;
import br.com.pessoa.service.PessoaService;
import lombok.Data;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.List;

@Data
@ManagedBean
@ViewScoped
public class PessoaManagedBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<Pessoa> pessoas;
    private Pessoa pessoa = new Pessoa();
    private Endereco endereco = new Endereco();

    @EJB
    private PessoaService pessoaService;
    @EJB
    private EnderecoService enderecoService;

    @PostConstruct
    public void init() {
        pessoas = pessoaService.listarTodasComEnderecos();
    }

    public void buscarEnderecoPeloCep() {
        Endereco fetchedEndereco = enderecoService.preencherEnderecoAutomaticamente(endereco.getCep());
        if (fetchedEndereco != null) {
            endereco.setLogradouro(fetchedEndereco.getLogradouro());
            endereco.setBairro(fetchedEndereco.getBairro());
            endereco.setCidade(fetchedEndereco.getCidade());
            endereco.setEstado(fetchedEndereco.getEstado());
        }
    }

    public void salvar() {
        endereco.setPessoa(pessoa);
        pessoa.setEnderecos(null);
        pessoaService.salvarPessoa(pessoa);
        enderecoService.salvarEndereco(endereco);
        pessoa = new Pessoa();
        endereco = new Endereco();
    }

    public void deletar(Pessoa pessoa) {
        if (pessoaService.removerPessoa(pessoa.getId())) {
            pessoas.remove(pessoa);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Pessoa deletada com sucesso!"));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "Falha ao deletar pessoa!"));
        }
    }

    public String editar(Pessoa pessoa) {
        this.pessoa = pessoa;
        this.endereco = pessoa.getEnderecoPrincipal();

        return "cadastro.xhtml";
    }


}