package org.lojadediscos.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.lojadediscos.model.Venda;
import org.lojadediscos.util.HibernateUtil;

import java.util.List;

/**
 * DAO para manipulação da entidade Venda no banco de dados.
 */

public class VendaDAO {

    public void salvar(Venda venda) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.persist(venda);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    public List<Venda> listarTodos() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Venda", Venda.class).list();
        }
    }
}
