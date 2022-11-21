package geo;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.geo.GeoServiceImpl;

import static org.junit.jupiter.api.Assertions.*;

import java.util.stream.Stream;

public class GeoServiceImplTest {

    @ParameterizedTest
    @MethodSource("methodSource")
    public void parametrizedByIpTest(String ip, Location expected) {

        GeoService geoService = new GeoServiceImpl();


        Location location = geoService.byIp(ip);

        assertEquals(expected.toString(), location.toString());

    }

    public static Stream<Arguments> methodSource() {
        Location expected1 = new Location("New York", Country.USA, null, 0);
        Location expected2 = new Location("Moscow", Country.RUSSIA, null, 0);
        Location expected3 = new Location("Moscow", Country.RUSSIA, "Lenina", 15);
        Location expected4 = new Location("New York", Country.USA, " 10th Avenue", 32);
        Location expected5 = new Location(null, null, null, 0);
        String MOSCOW_IP = "172.0.32.11";
        String NEW_YORK_IP = "96.44.183.149";
        String LOCALHOST = "127.0.0.1";
        return Stream.of(Arguments.of("96.", expected1),
                Arguments.of("172.", expected2),
                Arguments.of(MOSCOW_IP, expected3),
                Arguments.of(NEW_YORK_IP, expected4),
                Arguments.of(LOCALHOST, expected5));
    }
}
