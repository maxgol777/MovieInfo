package work.maxgol.movieviewer.jv.data.rest.model;

import java.util.List;

/**
 * Created by maxgol on 19.04.2017.
 */

public class Resp {

    public class VideoResponse {
        public List<VideoRetrofit> results;
    }

    public class MovieResponse {
        public List<MovieRetrofit> results;
    }

}
