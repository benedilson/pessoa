package br.com.pessoa.service;

import br.com.pessoa.dao.PessoaDAO;
import br.com.pessoa.model.Pessoa;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class PessoaService {

    @EJB
    private PessoaDAO pessoaDAO;

    public Pessoa salvarPessoa(Pessoa pessoa) {
        return pessoaDAO.salvar(pessoa);
    }

    public Pessoa buscarPessoaPorId(Integer id) {
        return pessoaDAO.buscarPorId(id);
    }

    public List<Pessoa> listarPessoas() {
        return pessoaDAO.buscarTodos();
    }

    public boolean removerPessoa(Integer id) {
        Pessoa pessoa = pessoaDAO.buscarPorId(id);
        if (pessoa != null) {
            return pessoaDAO.remover(pessoa);
        }
        return false;
    }

    public List<Pessoa> listarTodasComEnderecos() {
        return pessoaDAO.listarTodasComEnderecos();
    }

    public Pessoa encontrarPorIdComEnderecos(Integer id) {
        return pessoaDAO.encontrarPorIdComEnderecos(id);
    }
}