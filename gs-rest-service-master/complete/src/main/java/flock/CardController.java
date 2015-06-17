package flock;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class CardController {
    private final AtomicLong cardCounter = new AtomicLong();
    private final AtomicLong flockCounter = new AtomicLong();

    @RequestMapping("/flock/sample")
    public Card sampleCard() {
        return new Card(cardCounter.incrementAndGet(), 1337, new User("Billy Bee", "sample_email@gmail.com", 1416123456), new ImageLogo("Images/HappyBee.png", "Haps Bee"), new Company("Taktme", "Dictator-For-Life"));
    }

    @RequestMapping(value="/flock/create", method=RequestMethod.POST)
    public Flock createFlock(@RequestParam(value="name") String name,
                             @RequestParam(value="creatorId") long creatorId) {
        return new Flock(flockCounter.incrementAndGet(), name, creatorId);
    }

    @RequestMapping("/flock/get")
    public Flock getFlock(@RequestParam(value="id") long id) {
        // TODO
        return null;
    }
}
