package info.philboyd.pheddit.model;

import java.util.List;

/**
 * Created by philb on 6/6/2018.
 */

public class RedditPage {

    private String after;
    private String before;
    private List<RedditChild> children;
    private int dist;

    public String getAfter() {
        return after;
    }

    public void setAfter(String after) {
        this.after = after;
    }

    public String getBefore() {
        return before;
    }

    public void setBefore(String before) {
        this.before = before;
    }

    public List<RedditChild> getChildren() {
        return children;
    }

    public void setChildren(List<RedditChild> children) {
        this.children = children;
    }

    public int getDist() {
        return dist;
    }

    public void setDist(int dist) {
        this.dist = dist;
    }
}
