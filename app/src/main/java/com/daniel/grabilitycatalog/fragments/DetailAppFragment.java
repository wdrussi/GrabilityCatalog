package com.daniel.grabilitycatalog.fragments;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.daniel.grabilitycatalog.R;
import com.daniel.grabilitycatalog.model.ApplicationData;
import com.daniel.grabilitycatalog.utils.Util;
import com.squareup.picasso.Picasso;

/**
 * Created by Daniel on 5/11/2016.
 */

public class DetailAppFragment extends DialogFragment {

    ImageView imageViewApp;
    TextView textViewTitle;
    TextView textViewSummary;
    View rootView;
    private ApplicationData applicationData;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_app_detail, container, false);
        init();
        return rootView;
    }

    public ApplicationData getApplicationData() {
        return applicationData;
    }

    public void setApplicationData(ApplicationData applicationData) {
        this.applicationData = applicationData;
    }

    private void init (){
        getDialog().setTitle(Util.getShortAppName(applicationData.getApplicationTitle()));

        imageViewApp = (ImageView) rootView.findViewById(R.id.appImageDetail);
        textViewTitle = (TextView) rootView.findViewById(R.id.appTitle);
        textViewSummary = (TextView) rootView.findViewById(R.id.appSummary);
        Picasso.with(getActivity()).load(applicationData.getApplicationImage().getImagerUrl()).placeholder(R.drawable.placeholder).error(R.drawable.dont_image).into(imageViewApp);
        textViewTitle.setText(applicationData.getApplicationTitle());
        textViewSummary.setText(applicationData.getApplicationSummary());
        Button dismiss = (Button) rootView.findViewById(R.id.close);
        dismiss.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }
}
