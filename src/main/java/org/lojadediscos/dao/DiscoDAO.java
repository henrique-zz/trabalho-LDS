package org.lojadediscos.dao;

import org.lojadediscos.model.Disco;
import org.lojadediscos.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

/**
 * DAO para manipulação da entidade Disco no banco de dados.
 */

public class DiscoDAO {

    public void salvar(Disco disco) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.persist(disco); // insere um objeto "disco" no banco, persist = save
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    public List<Disco> listarTodos() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Disco", Disco.class).list();
        }
    }

    public void atualizar(Disco disco) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.merge(disco);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    public boolean excluir(int id) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Disco disco = session.get(Disco.class, id);
            if (disco == null) {
                return false;
            }

            transaction = session.beginTransaction();
            session.remove(disco);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
            return false;
        }
    }

}
