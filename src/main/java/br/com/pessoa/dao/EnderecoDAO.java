package br.com.pessoa.dao;

import br.com.pessoa.model.Endereco;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class EnderecoDAO {

    @PersistenceContext
    private EntityManager em;

    public Endereco salvar(Endereco endereco) {
        if (endereco.getId() == null) {
            em.persist(endereco);
        } else {
            endereco = em.merge(endereco);
        }
        return endereco;
    }

    public Endereco buscarPorId(Integer id) {
        return em.find(Endereco.class, id);
    }

    public List<Endereco> buscarTodos() {
        return em.createQuery("SELECT e FROM Endereco e", Endereco.class).getResultList();
    }

    public void remover(Endereco endereco) {
        em.remove(endereco);
    }
}
