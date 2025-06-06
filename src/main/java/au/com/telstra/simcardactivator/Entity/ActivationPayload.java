package au.com.telstra.simcardactivator.Entity;

import lombok.Data;

@Data
public class ActivationPayload {

    private String iccid;
    private String customerEmail;

}
