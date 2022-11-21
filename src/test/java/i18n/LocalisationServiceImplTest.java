package i18n;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.netology.entity.Country;
import ru.netology.i18n.LocalizationService;
import ru.netology.i18n.LocalizationServiceImpl;

import java.util.stream.Stream;


public class LocalisationServiceImplTest {

    @ParameterizedTest
    @MethodSource("methodSource")
    public void parametrizedLocalTest(Country country, String expected) {
        LocalizationService localizationService = new LocalizationServiceImpl();

        String msg = localizationService.locale(country);

        Assertions.assertEquals(expected, msg);
    }

    public static Stream<Arguments> methodSource() {
        Country[] country = {Country.RUSSIA, Country.BRAZIL, Country.USA, Country.GERMANY};

        return Stream.of(Arguments.of(country[0], "Добро пожаловать"),
                Arguments.of(country[1], "Welcome"),
                Arguments.of(country[2], "Welcome"),
                Arguments.of(country[3], "Welcome"));
    }
}
