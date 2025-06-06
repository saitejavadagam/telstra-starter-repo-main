package au.com.telstra.simcardactivator.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ActivationPayload {

    private String iccid;
    private String customerEmail;

    public ActivationPayload(String iccid, String customerEmail) {
        this.iccid = iccid;
        this.customerEmail = customerEmail;
    }

}
