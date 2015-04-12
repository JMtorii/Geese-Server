package svop.webapp;

import org.apache.cxf.jaxrs.ext.multipart.Multipart;

import javax.jws.WebService;
import javax.ws.rs.FormParam;
import java.util.LinkedList;

@WebService
public class SvopImpl implements ISvop {
    LinkedList<Card> cardQueue = new LinkedList<Card>();

    public SvopImpl() {
        cardQueue.add(new Card(0, "queue starter element"));
    }

    /**
     * Basic implementation of swapping a card
     * @param c
     * @return
     */
    @Override
    public Card swapCard(@Multipart("card") Card c) {
        cardQueue.add(c);
        return cardQueue.remove();
    }

    @Override
    public String swapRaw(@FormParam("id") long id, @FormParam("content") String test) {
        cardQueue.add(new Card(id, test));
        return cardQueue.remove().content;
    }

    @Override
    public String debugPost() {
        return "{\"test\"}";
    }
}