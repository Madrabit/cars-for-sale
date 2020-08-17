package ru.job4j.cars.dao.impl;

import ru.job4j.cars.dao.CommonDao;
import ru.job4j.cars.model.Advertisement;
import ru.job4j.cars.model.User;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author madrabit on 04.05.2020
 * @version 1$
 * @since 0.1
 */
public class DaoStub implements CommonDao<Advertisement> {
    private final Map<Integer, Advertisement> advertisementMap = new HashMap<>();

    private int advertisementIds = 0;

    @Override
    public Advertisement create(Advertisement advertisement) {
        advertisement.setId(advertisementIds++);
        advertisementMap.put(advertisement.getId(), advertisement);
        return advertisementMap.get(advertisement.getId());
    }

    @Override
    public void update(Advertisement model) {

    }

    @Override
    public List<Advertisement> findAll(Class<Advertisement> cl) {
        return new ArrayList<>(advertisementMap.values());
    }

    @Override
    public Advertisement findById(int id) {
        return null;
    }

    @Override
    public User findByEmail(String email) {
        return null;
    }

    @Override
    public void addUser(User user) {

    }

    @Override
    public void updateStatus(int id, boolean completed) {

    }
}
