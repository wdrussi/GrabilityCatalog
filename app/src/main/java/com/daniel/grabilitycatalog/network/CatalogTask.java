package com.daniel.grabilitycatalog.network;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.daniel.grabilitycatalog.R;
import com.daniel.grabilitycatalog.activities.MainActivity;
import com.daniel.grabilitycatalog.interfaces.ICatalogResponse;
import com.daniel.grabilitycatalog.interfaces.ICatalogService;
import com.daniel.grabilitycatalog.model.CatalogModel;
import com.daniel.grabilitycatalog.model.Entry;
import com.daniel.grabilitycatalog.repository.AppRegistration;
import com.daniel.grabilitycatalog.repository.ImageRegistration;
import com.daniel.grabilitycatalog.utils.Util;

import java.util.List;

import retrofit2.Call;
import retrofit2.GsonConverterFactory;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by Daniel on 5/11/2016.
 */

public class CatalogTask extends AsyncTask<Object, Void, CatalogModel> {

    private final static String TAG = CatalogTask.class.getName();

    private ICatalogResponse iCatalogResponse;


    boolean existData ;
    private CatalogModel catalogModel;
    AppRegistration appRegistration = AppRegistration.getAppRegistrationInstance();
    ImageRegistration imageRegistration = ImageRegistration.getImageRegistrationInstance();
    Context context;

    public CatalogTask(Context context, ICatalogResponse catalogResponse) {
        this.iCatalogResponse = catalogResponse;
        this.context = context;
    }



    @Override
    protected CatalogModel doInBackground(Object... params) {
        try {
            Thread.sleep(context.getApplicationContext().getResources().getInteger(R.integer.splash_time));
            CatalogModel catalogModel = getCatalog((String)params[0], (Boolean)params[1]);
            return catalogModel;
        } catch (InterruptedException e) {
            Log.e(TAG, e.getMessage());
            return null;
        }
    }

    /**
     * Get apps catalogModel
     *
     * @return
     */
    private CatalogModel getCatalog(String url, boolean validCatalog) {
        if (!validCatalog){
            try {
                Retrofit retrofit = new Retrofit.Builder().baseUrl(url).addConverterFactory(GsonConverterFactory.create()).build();
                ICatalogService iCatalogService = retrofit.create(ICatalogService.class);
                Call<CatalogModel> call = iCatalogService.getCatalog(context.getApplicationContext().getResources().getString(R.string.cantidad_service));
                Response<CatalogModel> response = call.execute();

                if (response.isSuccess()) {
                    catalogModel = response.body();
                    existData = true;
                }else{
                    existData = false;
                }

            } catch (Exception e) {
                Log.e(TAG, e.getMessage());
                Log.e(TAG, e.toString());
            }
        }else if (validCatalog){
            existData = true;
        }

        return catalogModel;
    }


    private boolean saveCatalog(List<Entry> apps){
        boolean catalogSaved = false;
        try {
            for (Entry app:apps) {
                appRegistration.createApp(context, app);
                imageRegistration.createImage(context, app);
            }
            catalogSaved = true;
        }catch (Exception e){
            Log.e(TAG, e.getMessage());
            catalogSaved = false;
        }
        return catalogSaved;
    }


    @Override
    protected void onPostExecute(CatalogModel catalogModel) {
        if (catalogModel != null && existData) {
            if (saveCatalog(catalogModel.getFeed().getEntry())){
                iCatalogResponse.responseCatalog(true);
            }else {
                iCatalogResponse.responseCatalog(false);
            }
        }else if (catalogModel ==null && existData){
            iCatalogResponse.responseCatalog(true);
        }else {
            iCatalogResponse.responseCatalog(false);
        }
    }
}
