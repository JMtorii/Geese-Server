package transitobjects;

import flock.FlockCore;

/**
 * Created by LocalEvan on 2015-07-22.
 */
public class DeletePostPayload extends FlockCore {
    private String auth;
    private int postId;

    public String getAuthentication() {
        return this.auth;
    }

    public void setAuthentication(String auth) {
        this.auth = auth;
    }

    public int getPostId() {
        return this.postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }
}
