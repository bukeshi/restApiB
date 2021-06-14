package org.bulatAPI.pojos.rebrandly;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class RebrandlyLink {
    private String id;
    private String title;
    private String destination;
    private String status;
    private boolean isPublic;
    private String domainId;
    private String domainName;
    private int clicks;
    @JsonProperty("integrated")
    private boolean isIntegrated;

    private Creator creator;

}
