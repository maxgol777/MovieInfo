package work.maxgol.movieviewer.jv.data.rest.model;

import work.maxgol.movieviewer.jv.domain.model.Video;

/**
 * Created by maxgol on 19.04.2017.
 */

public class VideoRetrofit implements Video {

    private String id;
    private String key;
    private String name;
    private String site;
    private int size;
    private String type;

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getKey() {
        return key;
    }

    public String getSite() {
        return site;
    }

    public int getSize() {
        return size;
    }

    public String getId() {
        return id;
    }

}
