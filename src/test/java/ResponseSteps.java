import io.restassured.response.Response;
import net.thucydides.core.annotations.Step;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;

public class ResponseSteps {
    private ServiceHelper helper = new ServiceHelper();
    private SoftAssertions assertions;

    @Step("Get response Airline - Id {0}")
    public Response getAirLineById(String id) {
        return helper.getAirLineById(id);
    }

    @Step("Validate status code {1}")
    public void validateStatusCode(Response response, int statusCode) {
        Assert.assertEquals("", statusCode, response.getStatusCode());
    }

    @Step("Validate response headers")
    public void validateResponseHeadersAreNotNull(Response response) {
        Assert.assertNotNull("Response headers are null", response.getHeaders());
    }

    @Step("Validate response body")
    public void validateResponseBodyIsNotNull(Response response) {
        Assert.assertNotNull("Response body is null", response.getBody());
    }

    @Step("Validate response body fields")
    public void validateResponseBodyFieldsAreNotNull(Response response) {
        assertions = new SoftAssertions();
        assertions.assertThat(response.jsonPath().getInt("id")).as("id field not found").isNotNull();
        assertions.assertThat(response.jsonPath().getString("name")).as("name field not found").isNotNull();
        assertions.assertThat(response.jsonPath().getString("country")).as("country field not found").isNotNull();
        assertions.assertThat(response.jsonPath().getString("logo")).as("logo field not found").isNotNull();
        assertions.assertThat(response.jsonPath().getString("slogan")).as("slogan field not found").isNotNull();
        assertions.assertThat(response.jsonPath().getString("head_quaters")).as("head_quaters field not found").isNotNull();
        assertions.assertThat(response.jsonPath().getString("website")).as("website field not found").isNotNull();
        assertions.assertThat(response.jsonPath().getString("established")).as("established field not found").isNotNull();
        assertions.assertAll();
    }
}
