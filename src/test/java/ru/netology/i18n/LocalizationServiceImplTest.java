package ru.netology.i18n;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.netology.entity.Country;

import static ru.netology.entity.Country.GERMANY;

class LocalizationServiceImplTest {

    @Test
    void locale() {
        Country country = GERMANY;
        LocalizationServiceImpl localizationService = Mockito.mock(LocalizationServiceImpl.class);
        Mockito.when(localizationService.locale(country)).thenReturn("Welcome");
        String expected = "Добро пожаловать!";
        Assert.assertNotEquals(expected, localizationService.locale(country));
    }
}