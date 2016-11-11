package com.daniel.grabilitycatalog.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.daniel.grabilitycatalog.R;
import com.daniel.grabilitycatalog.activities.LaunchActivity;
import com.daniel.grabilitycatalog.adapters.AppAdapter;
import com.daniel.grabilitycatalog.interfaces.ItemClickListener;
import com.daniel.grabilitycatalog.model.AppImage;
import com.daniel.grabilitycatalog.model.ApplicationData;
import com.daniel.grabilitycatalog.repository.AppRepository;
import com.daniel.grabilitycatalog.repository.ImageRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Daniel on 5/11/2016.
 */

public class MainScreenFragment extends Fragment {

    private final String LOG_TAG = MainScreenFragment.class.getSimpleName();

    private String[] fragmentCategory = new String[1];

    private RecyclerView myRecyclerView;
    private StaggeredGridLayoutManager mStaggeredLayoutManager;
    public static AppAdapter customListAdapter;

    List<ApplicationData> appsData;

    public MainScreenFragment() {
    }

    public void setFragmentCategory(String category) {
        fragmentCategory[0] = category;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             final Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        appsData = new ArrayList<>();

        if (fragmentCategory[0] == null) {
            if (savedInstanceState != null) {
                customListAdapter.clear();
                setFragmentCategory(String.valueOf(savedInstanceState.getInt("Pager_Current")));
            }
        }

        for (int j = 0; j < fragmentCategory.length; j++) {
            int number = Integer.parseInt(fragmentCategory[j]) + 1;
            getAppInfo(getActivity(), String.valueOf(number));
        }

        if (LaunchActivity.IS_TABLET) {
            mStaggeredLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        } else {
            mStaggeredLayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        }

        myRecyclerView = (RecyclerView) rootView.findViewById(R.id.app_list);
        myRecyclerView.setLayoutManager(mStaggeredLayoutManager);
        myRecyclerView.setHasFixedSize(true);

        customListAdapter = new AppAdapter(getActivity(), appsData);
        myRecyclerView.setAdapter(customListAdapter);

        customListAdapter.setOnItemClickListener(onItemClickListener);

        return rootView;
    }


    /**
     *
     */
    ItemClickListener onItemClickListener = new ItemClickListener() {
        @Override
        public void onItemClicked(View view, int position, ApplicationData data) {
            ApplicationData applicationData = data;
            showDetailInfoApp(data);
        }
    };

    private void getAppInfo(Context context, String categoryId) {
        ImageRepository imageRepository = ImageRepository.getImageRepoInstance();
        AppRepository appRepository = AppRepository.getAppRepoInstance();
        List<AppImage> imagesApp = imageRepository.getImagesByCategory(context, categoryId, context.getResources().getString(R.string.imageSize));
        List<ApplicationData> apps = new ArrayList<>();
        for (AppImage image : imagesApp) {
            ApplicationData app = new ApplicationData();
            app = appRepository.getAppById(context, image.getApplicationIdentifier());
            app.setApplicationImage(image);
            apps.add(app);
        }

        appsData.addAll(apps);
    }


    private void showDetailInfoApp(ApplicationData applicationData) {
        FragmentManager fm = getFragmentManager();
        DetailAppFragment dialogFragment = new DetailAppFragment();
        dialogFragment.setApplicationData(applicationData);
        dialogFragment.show(fm, getResources().getString(R.string.app_detail));
    }
}
