package svop.webapp;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by LocalEvan on 2015-04-18.
 */
/*
"user": {
        "fullName": "Billy Bee",
        "email": "not_today_mofo@gmail.com",
        "phoneNumber": 4151112222,
        },
*/
@XmlRootElement(name = "user")
public class User {
    @JsonProperty("fullName")
    public String fullName;

    @JsonProperty("email")
    public String email;

    @JsonProperty("phoneNumber")
    public long phoneNumber;

    public User() {}
}
