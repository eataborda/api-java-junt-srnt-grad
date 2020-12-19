import io.restassured.response.Response;
import net.serenitybdd.junit.runners.SerenityParameterizedRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.junit.annotations.Qualifier;
import net.thucydides.junit.annotations.TestData;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RunWith(SerenityParameterizedRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AirLineValidationsTest {
    private String airlineId;
    private String airlineName;
    private String airlineEstablishedYear;

    @Steps
    ResponseSteps steps;

    @Qualifier
    public String qualifier() {
        return "- Id " + airlineId;
    }

    @TestData(columnNames = "Id, Name, Established")
    public static List<Object[]> testData() {
        return getTestData();
    }

    @BeforeClass
    public static void setup() {

    }

    public AirLineValidationsTest(String airlineId, String airlineName, String airlineEstablishedYear) {
        this.airlineId = airlineId;
        this.airlineName = airlineName;
        this.airlineEstablishedYear = airlineEstablishedYear;
    }

    @Test
    public void getAirlineById() {
        Response response = steps.getAirLineById(airlineId);
        steps.validateStatusCode(response, 200);
        steps.validateResponseHeadersAreNotNull(response);
        steps.validateResponseBodyIsNotNull(response);
        steps.validateResponseBodyFieldsAreNotNull(response);

    }

    public static List<Object[]> getTestData() {
        List<List<String>> testData = new ArrayList<>();
        testData.add(Arrays.asList("1", "Quatar Airways", "1994"));
        testData.add(Arrays.asList("3", "Cathay Pacific", "1946"));
        testData.add(Arrays.asList("4", "Emirates", "1985"));
        testData.add(Arrays.asList("7", "Deutsche Lufthansa AG", "1953"));
        testData.add(Arrays.asList("8", "Thai Airways", "1960"));
        return testData.stream().map(List::toArray).collect(Collectors.toList());
    }
}
