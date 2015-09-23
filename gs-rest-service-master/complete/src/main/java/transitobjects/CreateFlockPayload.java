package transitobjects;

import com.fasterxml.jackson.annotation.JsonProperty;
import flock.FlockCore;

/**
 * Created by LocalEvan on 2015-07-22.
 */
public class CreateFlockPayload extends FlockCore {
    private String auth;

    public String getAuthentication() {
        return this.auth;
    }

    public String setAuthentication(String auth) {
        return this.auth;
    }
}
