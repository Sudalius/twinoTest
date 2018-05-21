import com.sudyarov.urls.CityUrls;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class WeatherTest {


    @Test
    public void compareWeatherTest() {
        Response responseBangkok = given().header("Content-Type", "application/json")
                .get(CityUrls.BANGKOK);

        Response responseMoscow = given().header("Content-Type", "application/json")
                .get(CityUrls.MOSCOW);

        Assert.assertTrue(getCItyTemperature(responseBangkok)
                > getCItyTemperature(responseMoscow));
    }


    private Float getCItyTemperature(Response response) {
        Map<String, Float> main = response.jsonPath().getMap("main");
        return main.get("temp");
    }
}