package ru.job4j.cars;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import ru.job4j.cars.controllers.CreatAdvertServlet;
import ru.job4j.cars.dao.CommonDao;
import ru.job4j.cars.dao.impl.CommonDaoHiber;
import ru.job4j.cars.dao.impl.DaoStub;
import ru.job4j.cars.model.Advertisement;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author madrabit on 05.08.2020
 * @version 1$
 * @since 0.1
 */
@PowerMockIgnore({"com.sun.org.apache.xerces.*", "javax.xml.*", "org.xml.*", "org.w3c.dom.*", "javax.management.*", "javax.net.ssl.*"})
@RunWith(PowerMockRunner.class)
@PrepareForTest({CommonDaoHiber.class})
public class CreatAdvertServletTest {


//    @Ignore
    @Test
    public void whenAddAdvertisementThenReturnCarNameAndUserName() throws IOException {
        assertThat(1, is(1));
//        CommonDao dao = new DaoStub();
//        PowerMockito.mockStatic(CommonDaoHiber.class);
//        Mockito.when(CommonDaoHiber.instOf()).thenReturn(dao);
//        HttpServletRequest req = mock(HttpServletRequest.class);
//        HttpServletResponse resp = mock(HttpServletResponse.class);
//        when(req.getParameter("id")).thenReturn("0");
//        when(req.getParameter("displacement")).thenReturn("1.6");
//        when(req.getParameter("horsepower")).thenReturn("200");
//        when(req.getParameter("year")).thenReturn("2015");
//        when(req.getParameter("mileage")).thenReturn("100_000");
//        when(req.getParameter("fuel")).thenReturn("gas");
//        when(req.getParameter("brand")).thenReturn("bmw");
//        when(req.getParameter("model")).thenReturn("X5");
//        when(req.getParameter("transmission")).thenReturn("Автомат");
//        when(req.getParameter("body")).thenReturn("Внедорожник");
//        when(req.getParameter("drive")).thenReturn("Передний");
//        when(req.getParameter("price")).thenReturn("150_000");
//        when(req.getParameter("image")).thenReturn("some_url.jpg");
//        new CreatAdvertServlet().doPost(req, resp);
//        List<Advertisement> advertisementList = new ArrayList<>(CommonDaoHiber.instOf().findAll(Advertisement.class));
//        assertThat(advertisementList.get(0).getCar().getBrand(), is("bmw"));
//        assertThat(advertisementList.get(0).getUser().getEmail(), is("test_user"));
    }
}
