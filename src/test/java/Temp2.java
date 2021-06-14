import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.bulatAPI.pojos.rebrandly.RebrandlyLink;

public class Temp2 {

    public static void main(String[] args) throws JsonProcessingException {

        int[] arr = {10,30,50,50,50};
        RebrandlyLink linkObj = new RebrandlyLink();
        linkObj.setDestination("www.ixbt.com");
        linkObj.setTitle("IXBT");

        ObjectMapper objectMapper = new ObjectMapper();

        String jsonStr = objectMapper.writeValueAsString(linkObj);

        RebrandlyLink linkObj1 = objectMapper.readValue(jsonStr,RebrandlyLink.class);

        System.out.println("\n\nthis is obj 2 deserialized:\n"+linkObj1);

    }

}
