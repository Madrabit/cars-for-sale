package ru.job4j.cars.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.job4j.cars.dao.impl.CommonDaoHiber;
import ru.job4j.cars.model.Advertisement;
import ru.job4j.cars.model.Car;
import ru.job4j.cars.model.Engine;
import ru.job4j.cars.model.User;


import java.util.List;

/**
 * @author madrabit on 07.07.2020
 * @version 1$
 * @since 0.1
 * Class for Testing many-to-many in Hibernate.
 */
public class TestHibernate {

    private static final Logger LOG = LogManager.getLogger(TestHibernate.class.getName());

    public static void main(String[] args) {

        try {
            CommonDao dao = new CommonDaoHiber();
            Engine engine =  Engine.of(1.6, 200, "gas");
            Car car =  Car.of("bmw", "X5", engine, "Автомат", "Внедорожник", "Передний", 2015,
            100_000, 150_000, "some_url.jpg");
            dao.create(car);
            List<Car> cars = dao.findAll(Car.class);
//            cars.stream().map(Car::getBrand).forEach(System.out::println);
            User user = User.of("testmail", "12345test");
            Advertisement advertisement = Advertisement.of(car, user);
            dao.create(advertisement);
            List<Advertisement> advertisements = dao.findAll(Advertisement.class);
            advertisements.stream().map(Advertisement::getCar).forEach(System.out::println);
//            CommonDao adv = new AdvertisementDaoHiber().forEach(System.out::println);
        }  catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }

}
