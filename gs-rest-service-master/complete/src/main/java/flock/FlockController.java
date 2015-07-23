package flock;

import org.springframework.web.bind.annotation.*;
import transitobjects.ClientFlockPayload;
import transitobjects.CreateFlockPayload;
import transitobjects.JsonResponse;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class FlockController {
    private final AtomicLong cardCounter = new AtomicLong();
    private final AtomicLong flockCounter = new AtomicLong();

    @RequestMapping("/flock/sample")
    public Card sampleCard() {
        return new Card(cardCounter.incrementAndGet(), 1337, new User("Willy Bee", "sample_email@gmail.com", 1416123456), new ImageLogo("Images/HappyBee.png", "Haps Bee"), new Company("Taktme", "Dictator-For-Life"));
    }

    @RequestMapping(value="/flock/create", method=RequestMethod.POST)
    public JsonResponse createFlock(@RequestBody CreateFlockPayload flock
                            /*@RequestParam(value="name") String name,
                             @RequestParam(value="creatorId") long creatorId*/) {
        return new JsonResponse("OK", ""); // success
    }

    @RequestMapping("/flock/get")
    public JsonResponse getFlock(@RequestParam(value="id") long id) {
        // TODO
        return new JsonResponse("OK", ""); // success
    }
}
