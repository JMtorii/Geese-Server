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

    private final AtomicLong flockCounter = new AtomicLong();

    // TODO: More than just one sample flock
    Flock localTestFlock;

    @RequestMapping(value="/", method=RequestMethod.POST)
    public String createFlock(@RequestBody CreateFlockPayload flock) {
                            /*@RequestParam(value="name") String name,
                             @RequestParam(value="creatorId") long creatorId) {*/
        localTestFlock = new Flock(flock);
        long newId = localTestFlock.getId();

        return "{\"Result\": \"OK\", \"FlockId\": \"" + newId + "\"}"; // success
    }

    @RequestMapping(value="/", method=RequestMethod.GET)
    public ClientFlockPayload getFlock(@RequestParam(value="id") long id) {
        // TODO
        if (localTestFlock.getId() == id) {
            return localTestFlock.getClientPayload(); // success
        } else {
            return null; // failure TODO: better error handling
        }
    }
}
