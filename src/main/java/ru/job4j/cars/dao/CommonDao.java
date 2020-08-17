package ru.job4j.cars.dao;

import ru.job4j.cars.model.Advertisement;
import ru.job4j.cars.model.User;

import java.util.List;

/**
 * @author madrabit on 22.07.2020
 * @version 1$
 * @since 0.1
 */
public interface CommonDao<T> {
    T create (T model);

    void update(T model);


    /**
     * Update advertisment status. Car sold or not.
     * @param id
     * @param completed
     */
    void updateStatus(int id, boolean completed);


    List<T> findAll(Class<T> cl);

    /**
     * Find Advertisement by Id.
     * @param id
     * @return
     */
    Advertisement findById(int id);

    /**
     * Find user by Email.
     * @param email
     * @return
     */
    User findByEmail(String email);

    /**
     * Add new User.
     * @param user
     */
    void addUser(User user);
}
