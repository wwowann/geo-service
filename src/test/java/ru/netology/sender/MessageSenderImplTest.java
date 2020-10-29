package ru.netology.sender;

import org.hamcrest.MatcherAssert;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoServiceImpl;
import ru.netology.i18n.LocalizationServiceImpl;

import java.util.HashMap;
import java.util.Map;

public class MessageSenderImplTest {

    @Test
    public void sendTest() {
        GeoServiceImpl geoService = Mockito.mock(GeoServiceImpl.class);
        Mockito.when(geoService.byIp(Mockito.any(String.class))).thenReturn(new Location("Москва", Country.RUSSIA,
                null, 0));

        LocalizationServiceImpl localizationService = Mockito.mock(LocalizationServiceImpl.class);
        Mockito.when(localizationService.locale(Mockito.<Country>any())).thenReturn("Добро пожаловать!");
        MessageSender messageSender = new MessageSenderImpl(geoService, localizationService);
        String expected = "Добро пожаловать!";
        Map<String, String> headers = new HashMap<String, String>();
        headers.put(geoService.byIp("172.0.32.11").toString(), localizationService.locale(Country.USA));
        messageSender.send(headers);
        Assert.assertEquals(expected, messageSender.send(headers));
    }
}
