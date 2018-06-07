package info.philboyd.pheddit.model;

/**
 * Created by philb on 6/6/2018.
 */

public class RedditChild {
    private String kind;
    private RedditPost data;

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public RedditPost getData() {
        return data;
    }

    public void setData(RedditPost data) {
        this.data = data;
    }
}
