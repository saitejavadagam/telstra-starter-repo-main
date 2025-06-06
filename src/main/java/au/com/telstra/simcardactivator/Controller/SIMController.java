package au.com.telstra.simcardactivator.Controller;


import au.com.telstra.simcardactivator.Entity.ActivationPayload;
import au.com.telstra.simcardactivator.Service.AcutatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SIMController {

    @Autowired
    private AcutatorService acutatorService;

    @PostMapping("/ActivateSIM")
    public ResponseEntity<String> activateString(@RequestBody ActivationPayload activationPayload){

        boolean isSuccess = acutatorService.activateSIM(activationPayload.getIccid());

        return ResponseEntity.ok("SIM ACTIVATION"+(isSuccess?"Successful":"Failed"));

    }

}
