import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import net.serenitybdd.rest.SerenityRest;

public class ServiceHelper {
    private final String baseUrl = "https://api.instantwebtools.net/v1/";
    private final String airlinesPath = "airlines/";

    public Response getAirLineById(String id) {
        RequestSpecification request = SerenityRest.given();
        request.pathParams("id", id);
        return request.get(baseUrl + airlinesPath + "{id}");
    }
}
