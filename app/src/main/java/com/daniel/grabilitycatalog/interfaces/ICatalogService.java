package com.daniel.grabilitycatalog.interfaces;

import com.daniel.grabilitycatalog.model.CatalogModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Daniel on 5/11/2016.
 */

public interface ICatalogService {

    @GET("us/rss/topfreeapplications/limit={cantidad}/json")
    Call<CatalogModel> getCatalog(@Path("cantidad") String cantidad);

}
