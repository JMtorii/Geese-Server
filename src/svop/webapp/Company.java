package svop.webapp;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by LocalEvan on 2015-04-18.
 */
/*
"company": {
        "name": "Lumosity",
        "position": "Useless"
*/

@XmlRootElement(name = "company")
public class Company {
    @JsonProperty("name")
    public String name;

    @JsonProperty("position")
    public String position;

    public Company() {}
}
