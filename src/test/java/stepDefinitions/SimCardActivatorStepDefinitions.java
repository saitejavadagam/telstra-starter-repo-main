package stepDefinitions;

import au.com.telstra.simcardactivator.Entity.ActivationPayload;
import au.com.telstra.simcardactivator.Entity.Customer;
import au.com.telstra.simcardactivator.SimCardActivator;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootContextLoader;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;

@CucumberContextConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ContextConfiguration(classes = SimCardActivator.class, loader = SpringBootContextLoader.class)
public class SimCardActivatorStepDefinitions {

    Logger logger = LoggerFactory.getLogger(SimCardActivatorStepDefinitions.class);

    @Autowired
    private TestRestTemplate restTemplate;

    private String baseUrl =  "http://localhost:8080";
    private ResponseEntity<Customer> lateResponse;

    @Given("the SIM card acutator is running")
    public void theSimCardAcutatorIsRunning() {

    }

    @When("I submit and activation request with ICCID {string}")
    public void iSubmitAndActivationRequestWithICCID(String iccid) {
        String url = baseUrl+"/ActivateSIM";

        ActivationPayload payload = new ActivationPayload(iccid,"customer@example.com");
        lateResponse = restTemplate.postForEntity(url,payload,Customer.class);
    }

    @Then("the activation should be successful")
    public void theActivationShouldBeSuccessful() {
        assert  lateResponse.getStatusCode().is2xxSuccessful();
        assert lateResponse.getBody() != null;
        assert lateResponse.getBody().getIccid().equals("1255789453849037777");
    }

    @Then("the activation should be fail")
    public void theActivationShouldBeFail() {
        assert lateResponse.getStatusCode().isError();
    }

    @Then("I can verify the activation status from the database")
    public void iCanVerifyTheActivationStatusFromTheDatabase() {
        String iccid = "8944500102198304826";
        String url = baseUrl+"/getCustomer/"+iccid;
        ResponseEntity<Customer> response = restTemplate.getForEntity(url,Customer.class);

        assert response.getStatusCode().is2xxSuccessful();
        assert response.getBody() != null;
        assert response.getBody().getIccid().equals(iccid);
    }




}