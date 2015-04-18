package svop.webapp;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by LocalEvan on 2015-04-18.
 */
@XmlRootElement(name = "Card")
public class Card {
    long id;
    String content;

    public Card(long id, String content) {
        this.id = id;
        this.content = content;
    }
}
