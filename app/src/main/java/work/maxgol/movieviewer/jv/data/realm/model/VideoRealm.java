package work.maxgol.movieviewer.jv.data.realm.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import work.maxgol.movieviewer.jv.domain.model.Video;

/**
 * Created by maxgol on 12.04.2017.
 */

public class VideoRealm extends RealmObject implements Cloneable,Video {

    @PrimaryKey
    private String videoId;
    private String name;
    private String type;
    private String key;
    private String site;
    private int size;

    public String getId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "\n" + "videoId = " + videoId + "\n" +
                "name = " + name + "\n" +
                "type = " + type + "\n" +
                "key = " + key + "\n" +
                "site = " + site + "\n" +
                "size = " + size + "\n";
    }
}
