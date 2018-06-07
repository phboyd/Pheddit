package info.philboyd.pheddit.model;

/**
 * Created by philb on 6/6/2018.
 */

public class RedditParent {
    private String kind;
    private RedditPage data;

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public RedditPage getData() {
        return data;
    }

    public void setData(RedditPage data) {
        this.data = data;
    }
}
