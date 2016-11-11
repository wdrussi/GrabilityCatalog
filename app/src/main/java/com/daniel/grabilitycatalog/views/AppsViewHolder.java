package com.daniel.grabilitycatalog.views;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.daniel.grabilitycatalog.R;
import com.daniel.grabilitycatalog.activities.LaunchActivity;

/**
 * Created by Daniel on 5/11/2016.
 */

public class AppsViewHolder extends RecyclerView.ViewHolder {

    public LinearLayout placeHolder;

    public LinearLayout placeNameHolder;

    public ImageView imageView;

    public TextView textView;

    public AppsViewHolder(View v) {
        super(v);
        placeHolder = (LinearLayout) itemView.findViewById(R.id.mainHolder);
        placeNameHolder = (LinearLayout) itemView.findViewById(R.id.placeNameHolder);
        imageView = (ImageView) v.findViewById(R.id.image);
        textView = (TextView) itemView.findViewById(R.id.placeName);

        LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) textView.getLayoutParams();
        if (LaunchActivity.IS_TABLET){
            lp.setMargins(120,0,0,0);
        }else {
            lp.setMargins(100,0,0,0);
        }

        textView.setLayoutParams(lp);
    }
}
