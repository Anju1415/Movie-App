package com.example.android.popularmovies.utilities;

import android.net.Uri;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class NetworkUtils {
    private final static String DB_BASE_URL = "https://api.themoviedb.org/3/movie";
    private final static String PARAM_API_KEY = "api_key";
    private final static String apiKey = "a604a49994276678c9ea890e88e0ee95";
    private final static String PARAM_LANGUAGE = "language";
    private final static String language = "en-US";
    private final static String videos = "videos";
    private final static String reviews = "reviews";

    public static URL buildUrl(String theMovieDbSearchQuery){
        Uri builtUri = Uri.parse(DB_BASE_URL).buildUpon()
                .appendEncodedPath(theMovieDbSearchQuery)
                .appendQueryParameter(PARAM_API_KEY, apiKey)
                .appendQueryParameter(PARAM_LANGUAGE, language)
                .build();

        URL url = null;
        try{
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e){
            e.printStackTrace();
        }
        return url;
    }
    // build another url for trailers
    public static URL buildTrailerUrl(int specificMovieId){
        Uri builtUri = Uri.parse(DB_BASE_URL).buildUpon()
                .appendEncodedPath(String.valueOf(specificMovieId))
                .appendEncodedPath(videos)
                .appendQueryParameter(PARAM_API_KEY, apiKey)
                .appendQueryParameter(PARAM_LANGUAGE, language)
                .build();

        URL url = null;
        try{
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e){
            e.printStackTrace();
        }
        return url;
    }

    // build another url for reviews
    public static URL buildReviewUrl(int specificMovieId){
        Uri builtUri = Uri.parse(DB_BASE_URL).buildUpon()
                .appendEncodedPath(String.valueOf(specificMovieId))
                .appendEncodedPath(reviews)
                .appendQueryParameter(PARAM_API_KEY, apiKey)
                .appendQueryParameter(PARAM_LANGUAGE, language)
                .build();

        URL url = null;
        try{
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e){
            e.printStackTrace();
        }
        return url;
    }

    public static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }
}
