package sender;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.i18n.LocalizationService;
import ru.netology.i18n.LocalizationServiceImpl;
import ru.netology.sender.MessageSender;
import ru.netology.sender.MessageSenderImpl;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class MessageSenderImplTest {

    @ParameterizedTest
    @MethodSource("methodSource")
    public void parametrizedsendTest(String ip, Location location, String expected) {

        GeoService geoService = Mockito.mock(GeoService.class);
        Mockito.when(geoService.byIp(ip))
                .thenReturn(location);

        LocalizationService localizationService = Mockito.mock(LocalizationService.class);
        Mockito.when(localizationService.locale(location.getCountry()))
                .thenReturn(expected);

        MessageSender messageSender = new MessageSenderImpl(geoService, localizationService);
        Map<String, String> headers = new HashMap<>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, ip);
        System.out.println();
        String msg = messageSender.send(headers);

        Assertions.assertEquals(expected, msg);
    }

    public static Stream<Arguments> methodSource() {
        String[] ip = {"172.0.32.11", "96.44.183.149"};
        Location[] location = {new Location("Moscow", Country.RUSSIA, "Lenina", 15),
                new Location("New York", Country.USA, " 10th Avenue", 32)};
        String[] expected = {"Добро пожаловать", "Welcome"};

        return Stream.of(Arguments.of(ip[0], location[0], expected[0]),
                Arguments.of(ip[1], location[1], expected[1]));
    }

    @Test
    void sendRussiaTest(){
        String ip = "172.0.32.11";
        Location location = new Location("Moscow", Country.RUSSIA, "Lenina", 15);
        String expected = "Добро пожаловать";

        GeoService geoService = Mockito.mock(GeoService.class);
        Mockito.when(geoService.byIp(ip))
                .thenReturn(location);

        LocalizationService localizationService = Mockito.mock(LocalizationService.class);
        Mockito.when(localizationService.locale(location.getCountry()))
                .thenReturn(expected);

        MessageSender messageSender = new MessageSenderImpl(geoService,localizationService);
        Map<String,String>headers = new HashMap<>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, ip);
        System.out.println();
        String msg = messageSender.send(headers);

        Assertions.assertEquals(expected,msg);

    }

    @Test
    void sendNotRussiaTest(){
        String ip = "96.44.183.149";
        Location location = new Location("New York", Country.USA, " 10th Avenue", 32);
        String expected = "Welcome";

        GeoService geoService = Mockito.mock(GeoService.class);
        Mockito.when(geoService.byIp(ip))
                .thenReturn(location);

        LocalizationService localizationService = Mockito.mock(LocalizationService.class);
        Mockito.when(localizationService.locale(location.getCountry()))
                .thenReturn(expected);

        MessageSender messageSender = new MessageSenderImpl(geoService,localizationService);
        Map<String,String>headers = new HashMap<>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, ip);
        System.out.println();
        String msg = messageSender.send(headers);

        Assertions.assertEquals(expected,msg);

    }

}
