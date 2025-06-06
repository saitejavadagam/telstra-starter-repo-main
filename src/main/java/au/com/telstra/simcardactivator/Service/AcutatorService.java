package au.com.telstra.simcardactivator.Service;


import au.com.telstra.simcardactivator.Entity.AcutatorResponse;
import au.com.telstra.simcardactivator.Entity.Customer;
import au.com.telstra.simcardactivator.Repository.SIMActivatorRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;


@Service
public class AcutatorService {

    public static final String AcutatorURl = "http://localhost:8444/acutate";
    public static final Logger log = LoggerFactory.getLogger(AcutatorService.class);

    @Autowired
    SIMActivatorRepository simActivatorRepository;

    public Customer activateSIM(String iccid, String customerEmail) {

        RestTemplate restTemplate = new RestTemplate();

        Map<String, String> payload = new HashMap<>();
        payload.put("iccid", iccid);
        boolean success = restTemplate.postForObject(AcutatorURl, payload, AcutatorResponse.class).isSuccess();

        Customer customer = new Customer(iccid,customerEmail,success);

        return simActivatorRepository.save(customer);

    }

    public Customer getCustomerById(String iccid) {
        return simActivatorRepository.getByIccid(iccid);
    }

}
