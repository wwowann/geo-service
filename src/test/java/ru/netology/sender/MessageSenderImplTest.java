package ru.netology.sender;

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
    GeoServiceImpl geoService = Mockito.mock(GeoServiceImpl.class);
    LocalizationServiceImpl localizationService = Mockito.mock(LocalizationServiceImpl.class);
    private final String IP = "172.0.32.11";
    Location location = new Location("Moscow", Country.RUSSIA, null, 0);

    @BeforeEach
    void preinstallation() {
        Mockito.when(geoService.byIp(Mockito.any(String.class))).thenReturn(new Location("Москва", Country.RUSSIA,
                null, 0));
        Mockito.when(localizationService.locale(Mockito.<Country>any()))
                .thenReturn("Добро пожаловать");
        Mockito.when(geoService.byIp(Mockito.<String>any()))
                .thenReturn(location);
    }

    @Test
    public void this_method_returns_the_name_of_the_Russian_capital() {
        Assert.assertEquals("Moscow", geoService.byIp(IP).getCity());
        System.out.println(geoService.byIp(IP).getCity());
    }

    @Test
    public void the_method_returns_Null_street_names() {
        Assert.assertNull(geoService.byIp(Mockito.<String>any()).getStreet());
        System.out.println(geoService.byIp(Mockito.<String>any()).getStreet());
    }

    @Test
    public void the_method_returns_the_class_type_of_the_value() {
        Assert.assertEquals(geoService.byIp(IP).getClass(), location.getClass());
        System.out.println(location.getClass());

    }

    @Test
    public void the_method_returns_the_intercepted_value_of_the_method_argument() {
        GeoServiceImpl geoService = Mockito.mock(GeoServiceImpl.class);
        geoService.byIp(IP);
        ArgumentCaptor<String> argumentCaptor = ArgumentCaptor.forClass(String.class);
        Mockito.verify(geoService).byIp(argumentCaptor.capture());
        Assertions.assertEquals(IP, argumentCaptor.getValue());
        System.out.println(argumentCaptor.getValue());
    }

    @Test
    public void metod_returns_the_location_of_Russia() {
        Assert.assertEquals("Добро пожаловать", localizationService.locale(Country.RUSSIA));
        Mockito.verify(localizationService).locale(Country.RUSSIA);
        System.out.println(localizationService.locale(Country.RUSSIA));
    }

    @Test
    public void sendTest() {
        MessageSender messageSender = new MessageSenderImpl(geoService, localizationService);
        Map<String, String> headers = new HashMap<String, String>();
        headers.put(geoService.byIp("любой IP").getCountry().toString(), localizationService.locale(Country.RUSSIA));
        messageSender.send(headers);
        Assert.assertEquals("Добро пожаловать", messageSender.send(headers));
        System.out.println(messageSender.send(headers));
    }
}



