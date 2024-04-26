package br.com.pessoa.dao;

import br.com.pessoa.model.Pessoa;
import br.com.pessoa.util.FacesContextUtil;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class PessoaDAO {

    @PersistenceContext(name = "MyDS")
    private EntityManager em;

    @EJB
    private FacesContextUtil facesContextUtil;

    public Pessoa salvar(Pessoa pessoa) {
         if (pessoa.getId() == null) {
            em.persist(pessoa);
             facesContextUtil.addInfoMessage("Dados salvos com sucesso");
        } else {
            pessoa = em.merge(pessoa);
             facesContextUtil.addInfoMessage("Dados atualizados com sucesso");
        }
        return pessoa;
    }

    public Pessoa buscarPorId(Integer id) {
        return em.find(Pessoa.class, id);
    }

    public List<Pessoa> buscarTodos() {
        return em.createQuery("SELECT p FROM Pessoa p", Pessoa.class).getResultList();
    }

    public List<Pessoa> listarTodasComEnderecos() {
        return em.createQuery("SELECT p FROM Pessoa p LEFT JOIN FETCH p.enderecos", Pessoa.class).getResultList();
    }

    public Pessoa encontrarPorIdComEnderecos(Integer id) {
        try {
            return em.createQuery("SELECT p FROM Pessoa p LEFT JOIN FETCH p.enderecos WHERE p.id = :id", Pessoa.class)
                    .setParameter("id", id)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public boolean remover(Pessoa pessoa) {
        try {
            if (!em.contains(pessoa)) {
                pessoa = em.merge(pessoa);
            }
            em.remove(pessoa);
            em.flush();
            return true;
        }catch (Exception e) {
            return false;
        }
    }
}