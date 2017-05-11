package work.maxgol.movieviewer.jv.data.rest;

import retrofit2.Retrofit;
import rx.Observable;
import work.maxgol.movieviewer.jv.data.rest.model.MovieDetailedRetrofit;
import work.maxgol.movieviewer.jv.data.rest.model.Resp;

/**
 * Created by Maxgol on 17.03.2017.
 */

public class RestManager {

    private static RestManager manager;
    private Retrofit retrofit;
    private API service;

    public RestManager() {
        retrofit = ApiClient.getClient();
        service = retrofit.create(API.class);
    }


    public static synchronized RestManager getInstance() {
        if (manager == null)
            manager = new RestManager();
        return manager;
    }

    public Observable<Resp.VideoResponse> getVideos(String movieId, String lang) {
        return service.getMovieVideos(movieId, ApiClient.API_KEY, lang);
    }

    public Observable<Resp.MovieResponse> getMovies(String request, int page, String lang) {
        return service.getMovies(ApiClient.API_KEY, request, page, lang);
    }

    public Observable<MovieDetailedRetrofit> getDetailedInfo(String movieId, String lang) {
        return service.getMovieDetailedInfo(movieId, ApiClient.API_KEY, lang);
    }

}
