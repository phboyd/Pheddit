package info.philboyd.pheddit.viewmodel;


import android.content.Context;
import android.support.annotation.NonNull;

import java.util.Observable;

/**
 * Created by philb on 6/6/2018.
 */

public class PostWebViewModel extends Observable {

    private String webUrl;
    private Context context;

    public PostWebViewModel(Context context) {
        this.context = context;
    }

    public String getWebUrl() {
        return webUrl;
    }

    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl;
        setChanged();
        notifyObservers();
    }
}
