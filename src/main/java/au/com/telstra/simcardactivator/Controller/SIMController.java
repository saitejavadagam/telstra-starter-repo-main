package au.com.telstra.simcardactivator.Controller;


import au.com.telstra.simcardactivator.Entity.ActivationPayload;
import au.com.telstra.simcardactivator.Entity.Customer;
import au.com.telstra.simcardactivator.Service.AcutatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class SIMController {

    @Autowired
    private AcutatorService acutatorService;

    @PostMapping("/ActivateSIM")
    public ResponseEntity<Customer> activateString(@RequestBody ActivationPayload activationPayload){

       Customer customer = acutatorService.activateSIM(activationPayload.getIccid(),activationPayload.getCustomerEmail());

       return ResponseEntity.ok(customer);

    }

    @GetMapping("/getCustomer/{iccid}")
    public ResponseEntity<Customer> getCustomer(@PathVariable String iccid){
        Customer customer = acutatorService.getCustomerById(iccid);

        return ResponseEntity.ok(customer);
    }


}
