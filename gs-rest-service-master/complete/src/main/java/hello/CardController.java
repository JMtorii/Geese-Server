package hello;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class CardController {
    private final AtomicLong cardCounter = new AtomicLong();

    @RequestMapping("/flock/sample")
    public Card sampleCard() {
        return new Card(cardCounter.incrementAndGet(), "sampleCard");
    }
}
