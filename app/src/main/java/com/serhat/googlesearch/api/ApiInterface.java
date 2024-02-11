package com.serhat.googlesearch.api;

import com.serhat.googlesearch.data.model.ImageResult;
import com.serhat.googlesearch.data.model.SearchResult;
import com.serhat.googlesearch.data.model.VideoResult;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET("search/q={query}")
    Call<SearchResult> search(
            @Header("X-User-Agent") String xUserAgent,
            @Header("X-Proxy-Location") String xProxyLocation,
            @Header("X-RapidAPI-Key") String apiKey,
            @Header("X-RapidAPI-Host") String apiHost,
            @Path("query") String searchQuery
    );

    @GET("image/q={query}")
    Call<ImageResult> imageSearch(
            @Header("X-User-Agent") String xUserAgent,
            @Header("X-Proxy-Location") String xProxyLocation,
            @Header("X-RapidAPI-Key") String apiKey,
            @Header("X-RapidAPI-Host") String apiHost,
            @Path("query") String searchQuery
    );

    @GET("video/q={query}")
    Call<VideoResult> videoSearch(
            @Header("X-User-Agent") String xUserAgent,
            @Header("X-Proxy-Location") String xProxyLocation,
            @Header("X-RapidAPI-Key") String apiKey,
            @Header("X-RapidAPI-Host") String apiHost,
            @Path("query") String searchQuery
    );
}
