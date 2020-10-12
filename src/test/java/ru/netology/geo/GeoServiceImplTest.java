package ru.netology.geo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.entity.Location;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class GeoServiceImplTest {
    private final String ip = "172.0.32.11";
    Location location = new Location("Moscow", Country.RUSSIA, null, 0);

    @Test
    public void byIp() {
        GeoServiceImpl geoService = mock(GeoServiceImpl.class);
        when(geoService.byIp(ip)).thenReturn(location);
    }

    @Test
    void byIp_ip() {
        GeoServiceImpl geoService = Mockito.spy(GeoServiceImpl.class);
        Assertions.assertNotNull(geoService.byIp(ip));
    }

    @Test
    void byIp_NotNull() {
        GeoServiceImpl geoService = Mockito.spy(GeoServiceImpl.class);
        Assertions.assertNotNull(geoService.byIp(ip).getStreet());
    }

    @Test
    void byIp_Type() {
        GeoServiceImpl geoService = Mockito.spy(GeoServiceImpl.class);
        Assertions.assertEquals(geoService.byIp(ip).getBuiling(), 15);
    }

}