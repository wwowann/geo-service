package ru.netology.i18n;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.netology.entity.Country;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class LocalizationServiceImplTest {
    String value = "Добро пожаловать";

    @Test
    public void locateTestMock() {
        LocalizationServiceImpl localizationService = mock(LocalizationServiceImpl.class);
        when(localizationService.locale(Country.RUSSIA)).thenReturn("172.0.32.11");
        Mockito.verify(localizationService, Mockito.never()).locale(Country.USA);
    }

    @Test
    public void locateTest_Equals() {
        LocalizationServiceImpl localizationService = Mockito.spy(LocalizationServiceImpl.class);
        Assertions.assertEquals(localizationService.locale(Country.RUSSIA), value);
    }

    @Test
    public void locationTest_NotEquals() {
        LocalizationServiceImpl localizationService = Mockito.spy(LocalizationServiceImpl.class);
        Assertions.assertNotEquals(localizationService.locale(Country.USA), value);
    }

    @Test
    public void locationTest_NotNull() {
        LocalizationServiceImpl localizationService = Mockito.spy(LocalizationServiceImpl.class);
        Assertions.assertNotNull(localizationService.locale(Country.RUSSIA));
    }

}