package au.com.telstra.simcardactivator.Service;


import au.com.telstra.simcardactivator.Entity.AcutatorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;


@Service
public class AcutatorService {

    public static final String AcutatorURl = "http://localhost:8444/acutate";
    public static final Logger log = LoggerFactory.getLogger(AcutatorService.class);

    public boolean activateSIM(String iccid) {

        RestTemplate restTemplate = new RestTemplate();

        try {
            Map<String,String> payload = new HashMap<String,String>();
            payload.put("ICCID",iccid);

            AcutatorResponse acutatorResponse = restTemplate.postForObject(AcutatorURl,payload,AcutatorResponse.class);

            if(acutatorResponse==null)log.info("SimCarAcutator Not Started");
            return acutatorResponse!=null && acutatorResponse.isSuccess();
        }
        catch(Exception e) {
            e.printStackTrace();
            return false;
        }

    }
}
