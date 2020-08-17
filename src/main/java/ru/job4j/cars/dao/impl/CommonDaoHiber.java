package ru.job4j.cars.dao.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import ru.job4j.cars.dao.CommonDao;
import ru.job4j.cars.model.Advertisement;
import ru.job4j.cars.model.User;
import ru.job4j.cars.util.HibernateUtil;

import java.util.List;
import java.util.function.Function;

/**
 * @author madrabit on 22.07.2020
 * @version $
 * @since 0.1
 */
public class CommonDaoHiber<T> implements CommonDao<T> {
    private static final Logger LOG = LogManager.getLogger(ru.job4j.cars.dao.impl.CommonDaoHiber.class.getName());

    private static final CommonDaoHiber INST = new CommonDaoHiber();

    public static CommonDao instOf() {
        return INST;
    }

    private static final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    /**
     * Wrapper. Open session -> execute Function -> commit
     *
     * @param command Function for execution: create, update, delete, etc.
     * @return Return query result.
     */
    protected <T> T tx(final Function<Session, T> command) {
        T rsl = null;
        Transaction tx = null;
        try (final Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            rsl = command.apply(session);
            tx.commit();
            return rsl;
        } catch (final Exception e) {
            LOG.error(e.getMessage(), e);
            if (tx != null) {
                tx.rollback();
            }
            LOG.error(e.getMessage(), e);
        }
        return rsl;
    }

    @Override
    public T create(T model) {
        return this.tx(session -> {
            session.saveOrUpdate(model);
            return model;
        });
    }


    @Override
    public void update(T model) {
        this.tx(session -> {
            session.update(model);
            return null;
        });
    }

    @Override
    public void updateStatus(int id, boolean completed) {
        this.tx(session -> {
            Advertisement adv = findById(id);
            adv.setStatus(completed);
            session.update(adv);
            return null;
        });
    }


    @Override
    public List<T> findAll(Class<T> cl) {
        return this.tx(session -> session.createQuery("from " + cl.getName(), cl).list());
    }

    @Override
    public Advertisement findById(int id) {
        return this.tx(
                session -> session.get(Advertisement.class, id)
        );
    }


//    @Override
//    public List<Advertisement> findAll(String email) {
//        return this.tx(
//                session -> {
//                    Query<Advertisement> query = session.createQuery("from Advertisement t join fetch t.user u where u.email = :email");
//                    query.setParameter("email", email);
//                    return (List<Advertisement>) query.list();
//                }
//        );
//    }

    @Override
    public User findByEmail(String email) {
        return this.tx(
                session -> {
                    Query<User> query = session.createQuery("from User u where u.email=:email", User.class);
                    query.setParameter("email", email);
                    User user = query.uniqueResult();
                    if (user == null) {
                        user = User.of("", "");
                    }
                    return user;
                }
        );
    }

    @Override
    public void addUser(User user) {
        this.tx(
                session -> session.save(user)
        );
    }
}
