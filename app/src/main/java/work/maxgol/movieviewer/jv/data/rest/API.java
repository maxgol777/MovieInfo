package work.maxgol.movieviewer.jv.data.rest;


import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;
import work.maxgol.movieviewer.jv.data.rest.model.MovieDetailedRetrofit;
import work.maxgol.movieviewer.jv.data.rest.model.Resp;

/**
 * Created by Maxgol on 17.03.2017.
 */

public interface API {


    @GET("search/movie")
    Observable<Resp.MovieResponse> getMovies(
            @Query("api_key") String apiKey,
            @Query("query") String query,
            @Query("page") int page,
            @Query("language") String lang
    );


    @GET("movie/{movie_id}")
    Observable<MovieDetailedRetrofit> getMovieDetailedInfo(
            @Path("movie_id") String movieId,
            @Query("api_key") String apiKey,
            @Query("language") String lang
    );

    @GET("movie/{movie_id}/videos")
    Observable<Resp.VideoResponse> getMovieVideos(
            @Path("movie_id") String movieId,
            @Query("api_key") String apiKey,
            @Query("language") String lang
    );


}
