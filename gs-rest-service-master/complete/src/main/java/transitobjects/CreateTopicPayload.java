package transitobjects;

import flock.FlockCore;

/**
 * Created by LocalEvan on 2015-07-22.
 */
public class CreateTopicPayload extends FlockCore {
    private String auth;
    private TopicPayload payload;

    public String getAuthentication() {
        return this.auth;
    }

    public void setAuthentication(String auth) {
        this.auth = auth;
    }

    public TopicPayload getTopicPayload() {
        return this.payload;
    }

    public void setTopicPayload(TopicPayload payload) {
        this.payload = payload;
    }
}
