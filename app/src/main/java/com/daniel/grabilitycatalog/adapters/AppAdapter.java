package com.daniel.grabilitycatalog.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.daniel.grabilitycatalog.R;
import com.daniel.grabilitycatalog.interfaces.ItemClickListener;
import com.daniel.grabilitycatalog.model.ApplicationData;
import com.daniel.grabilitycatalog.utils.Util;
import com.daniel.grabilitycatalog.views.AppsViewHolder;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Daniel on 5/11/2016.
 */

public class AppAdapter extends RecyclerView.Adapter<AppsViewHolder> {

    private final String LOG_TAG = AppAdapter.class.getSimpleName();
    private Context mContext;
    private final List<ApplicationData> appsData;
    private ItemClickListener mListener;

    public void setOnItemClickListener(ItemClickListener listener) {
        mListener = listener;
    }

    public AppAdapter(Context context, List<ApplicationData> appsData) {
        this.appsData = appsData;
        this.mContext = context;
    }

    @Override
    public AppsViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.
                from(viewGroup.getContext()).
                inflate(R.layout.apps_list_item, viewGroup, false);
        return new AppsViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final AppsViewHolder appsViewHolder, final int position) {
        final ApplicationData appData = appsData.get(position);
        Util.isConnect(mContext,mContext.getApplicationContext().getResources().getInteger(R.integer.time_toast));
        String appImageURL = appData.getApplicationImage().getImagerUrl();
        String appName = Util.getShortAppName(appData.getApplicationName());
        Context context = appsViewHolder.imageView.getContext();
        Picasso.with(context).load(appImageURL).placeholder(R.drawable.placeholder).error(R.drawable.dont_image).into(appsViewHolder.imageView);
        appsViewHolder.textView.setText(appName);
        appsViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onItemClicked(v, position, appData);
            }
        });
    }

    @Override
    public int getItemCount() {
        return appsData.size();
    }

    private ApplicationData getItem(int position) {
        return appsData.get(position);
    }

    public void add(ApplicationData item) {
        appsData.add(item);
        this.notifyDataSetChanged();
    }

    public void clear() {
        int size = this.appsData.size();
        if (size > 0) {
            for (int i = 0; i < size; i++) {
                this.appsData.remove(0);
            }
            this.notifyItemRangeRemoved(0, size);
        }
    }

}


