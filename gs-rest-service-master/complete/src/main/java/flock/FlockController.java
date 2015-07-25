package flock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import transitobjects.ClientFlockPayload;
import transitobjects.CreateFlockPayload;
import transitobjects.JsonResponse;

import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping(value = "/flock")
public class FlockController {
    static final Logger logger = LoggerFactory.getLogger(FlockController.class);

    private final AtomicLong cardCounter = new AtomicLong();
    private final AtomicLong flockCounter = new AtomicLong();

    @RequestMapping(value = "/demo", method=RequestMethod.POST)
    public String create(@RequestBody Message message) {
        return "hey";
    }

    @RequestMapping(value = "/demo", method=RequestMethod.GET)
    public String create(String h) {
        return h;
    }

    @RequestMapping("/sample")
    public Card sampleCard() {
        return new Card(cardCounter.incrementAndGet(), 1337, new User("Xilly Bee", "sample_email@gmail.com", 1416123456), new ImageLogo("Images/HappyBee.png", "Haps Bee"), new Company("Taktme", "Dictator-For-Life"));
    }

    @RequestMapping(value="/create", method=RequestMethod.POST, produces = "application/json; charset=utf-8")
    public JsonResponse createFlock(@RequestBody CreateFlockPayload flock) {
                            /*@RequestParam(value="name") String name,
                             @RequestParam(value="creatorId") long creatorId) {*/

        return new JsonResponse("OK", ""); // success
    }

    @RequestMapping("/get")
    public JsonResponse getFlock(@RequestParam(value="id") long id) {
        // TODO
        return new JsonResponse("OK", ""); // success
    }
}
