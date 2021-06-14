package org.bulatAPI.pojos.rebrandly;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Creator {

    private String fullName;

}
