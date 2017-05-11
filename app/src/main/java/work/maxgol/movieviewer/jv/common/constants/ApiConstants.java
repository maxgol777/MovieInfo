package work.maxgol.movieviewer.jv.common.constants;

/**
 * Created by Maxgol on 18.01.2017.
 */

public class ApiConstants {

    public static class Video {
        public static final String results = "results";
        public static final String id = "id";
        public static final String name = "name";
        public static final String type = "type";
        public static final String site = "site";
        public static final String size = "size";
        public static final String key = "key";
    }


    public static class Movie {
        public static final String results = "results";
        public static final String poster_path = "poster_path";
        public static final String release_date = "release_date";
        public static final String id = "id"; // int
        public static final String original_title = "original_title";
        public static final String title = "title";
        public static final String backdrop_path = "backdrop_path";
        public static final String overview = "overview";
        public static final String vote_average = "vote_average";
        public static final String vote_count = "vote_count";
        public static final String popularity = "popularity";
    }


    public static class MovieDetailed {
        public static final String backdrop_path = "backdrop_path";
        public static final String budget = "budget";
        public static final String homepage = "homepage";
        public static final String id = "id";
        public static final String imdb_id = "imdb_id";
        public static final String original_language = "original_language";
        public static final String original_title = "original_title";
        public static final String overview = "overview";
        public static final String popularity = "popularity";
        public static final String poster_path = "poster_path";
        public static final String release_date = "release_date";
        public static final String revenue = "revenue";
        public static final String status = "status";
        public static final String tagline = "tagline";
        public static final String title = "title";
        public static final String video = "video";
        public static final String vote_average = "vote_average";
        public static final String vote_count = "vote_count";
        public static final String arrayContent = "name";

        public static final String genres = "genres";
        public static final String production_companies = "production_companies";
        public static final String production_countries = "production_countries";
        public static final String spoken_languages = "spoken_languages";

    }

    public static class Glide {
        public static final String imagePath154 = "https://image.tmdb.org/t/p/w154";
        public static final String imagePath185 = "https://image.tmdb.org/t/p/w185";
        public static final String imagePath342 = "https://image.tmdb.org/t/p/w342";
    }

    public static class Youtube {
        public static String videoPath = "http://www.youtube.com/watch?v=";
    }
}
