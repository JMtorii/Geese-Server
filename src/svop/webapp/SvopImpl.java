package svop.webapp;

import org.apache.cxf.jaxrs.ext.multipart.Multipart;

import javax.jws.WebParam;
import javax.jws.WebService;
import javax.ws.rs.FormParam;
import javax.ws.rs.core.MultivaluedMap;
import java.util.LinkedList;

public class SvopImpl implements ISvop {
//    LinkedList<Card> cardQueue = new LinkedList<Card>();
    Card initialCard;

    public SvopImpl() {
        //cardQueue.add(new Card(0, "queue starter element"));
        initialCard = new Card();
    }

    @Override
    public String sayHi() {
        return "hei";
    }

    @Override
    public String postCard(Card card) {
        return "You posted a card with ID: " + card.cardId + " from company " + card.company.name;
        //return card.content;
    }

    /**
     * Basic implementation of swapping a card
     * @param c
     * @return
     */
/*    @Override
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
    }*/
}