package org.lojadediscos.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.lojadediscos.model.ItemVenda;
import org.lojadediscos.util.HibernateUtil;

import java.util.List;

/**
 * DAO para manipulação da entidade ItemVenda no banco de dados.
 */

public class ItemVendaDAO {

    public void salvar(ItemVenda item) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.persist(item);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    public List<ItemVenda> listarTodos() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from ItemVenda", ItemVenda.class).list();
        }
    }
}
