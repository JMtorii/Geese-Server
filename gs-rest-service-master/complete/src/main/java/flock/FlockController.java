package flock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import transitobjects.ClientFlockPayload;
import transitobjects.CreateFlockPayload;
import transitobjects.JoinFlockPayload;
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
        localTestFlock = new Flock(flock);
        long newId = localTestFlock.getId(); // move this to DB

        /* SQL ADD NEW FLOCK, WITH UNIQUE ID */

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

    @RequestMapping(value="/join", method=RequestMethod.POST)
    public String joinFlock(@RequestBody JoinFlockPayload membership) {

        /* SQL, CHECK MEMBERSHIP AUTHENTICATION AGAINST DATABASE */

        /* SQL ADD ENTRY FOR MEMBERSHIP RELATION TABLE */

        return "{\"Result\": \"OK\", \"FlockId\": \"" + membership.getFlockId() + "\"}"; // success
    }

    @RequestMapping(value="/leave", method=RequestMethod.POST)
    public String leaveFlock(@RequestBody JoinFlockPayload membership) {

        /* SQL, CHECK MEMBERSHIP AUTHENTICATION AGAINST DATABASE */

        /* SQL REMOVE ENTRY FOR MEMBERSHIP RELATION TABLE */

        return "{\"Result\": \"OK\", \"FlockId\": \"" + membership.getFlockId() + "\"}"; // success
    }
}
