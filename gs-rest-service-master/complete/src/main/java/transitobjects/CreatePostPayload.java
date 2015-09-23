package transitobjects;

import flock.FlockCore;

/**
 * Created by LocalEvan on 2015-07-22.
 */
public class CreatePostPayload extends FlockCore {
    private String auth;
    private PostPayload payload;

    public String getAuthentication() {
        return this.auth;
    }

    public void setAuthentication(String auth) {
        this.auth = auth;
    }

    public PostPayload getPostPayload() {
        return this.payload;
    }

    public void setPostPayload(PostPayload payload) {
        this.payload = payload;
    }
}
