package ru.job4j.cars;

import org.junit.Before;
import org.junit.Test;
import ru.job4j.cars.dao.CommonDao;
import ru.job4j.cars.dao.impl.CommonDaoHiber;
import ru.job4j.cars.model.Advertisement;
import ru.job4j.cars.model.Car;
import ru.job4j.cars.model.Engine;
import ru.job4j.cars.model.User;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * @author madrabit on 02.09.2020
 * @version 1$
 * @since 0.1
 */
public class IntegrationTest {

    CommonDao dao;
    Car car;
    Advertisement advertisement;

    @Before
    public void fillWithData() {
        dao = new CommonDaoHiber();
        Engine engine =  Engine.of(1.6, 200, "gas");
        car =  Car.of("bmw", "X5", engine, "Автомат", "Внедорожник", "Передний", 2015,
                100_000, 150_000, "some_url.jpg");
        User user = User.of("testmail", "12345test");
        advertisement = Advertisement.of(car, user);
        dao.create(car);
        dao.create(advertisement);
    }


    @Test
    public void whenFindCarByBrandThenReturnBmw() {
        List<Advertisement> cars = dao.findCarByBrand("bmw");
        assertEquals(advertisement, cars.get(0));
    }

    @Test
    public void whenUpdateStatusThenTrue() {
        advertisement.setStatus(true);
        dao.updateStatus(1, true);
        Advertisement adv = dao.findById(1);
        assertEquals(true, adv.getStatus());
    }

}
