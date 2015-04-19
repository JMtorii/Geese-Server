package svop.webapp;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by LocalEvan on 2015-04-18.
 */
/*
"imageLogo": {
    "src": "Images/HappyBee.png",
            "name": "Haps Bee"
},
 */

@XmlRootElement(name = "imageLogo")
public class ImageLogo {
    @JsonProperty("src")
    public String src;

    @JsonProperty("name")
    public String name;

    public ImageLogo() {}
}
