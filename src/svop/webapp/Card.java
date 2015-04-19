package svop.webapp;

import javax.xml.bind.annotation.XmlRootElement;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by LocalEvan on 2015-04-18.
 */
/*
public class CardParams {
    @JsonProperty("user")
    private string user;

    //ctor
    public CardParam(@JsonProperty("user") string user){}*/

/*{
"card": {
        "cardId": "UUID-GOES-H3RE",
        "templateId": 1,
        "user": {
        "fullName": "Billy Bee",
        "email": "not_today_mofo@gmail.com",
        "phoneNumber": 4151112222,
        },
        "imageLogo": {
        "src": "Images/HappyBee.png",
        "name": "Haps Bee"
        },
        "company": {
        "name": "Lumosity",
        "position": "Useless"
        }
        }
        }*/

@XmlRootElement(name = "card")
public class Card {
    @JsonProperty("cardId")
    public String cardId;

    @JsonProperty("templateId")
    public long templateId;

    @JsonProperty("fullName")
    public String fullName;

    @JsonProperty("user")
    public User user;

    @JsonProperty("imageLogo")
    public ImageLogo imageLogo;

    @JsonProperty("company")
    public Company company;

    public Card() {}
}
