package transitobjects;

import flock.FlockCore;

/**
 * Created by LocalEvan on 2015-07-22.
 */
public class DeleteTopicPayload extends FlockCore {
    private String auth;
    private int topicId;

    public String getAuthentication() {
        return this.auth;
    }

    public void setAuthentication(String auth) {
        this.auth = auth;
    }

    public int getTopicId() {
        return this.topicId;
    }

    public void setTopicId(int topicId) {
        this.topicId = topicId;
    }
}
