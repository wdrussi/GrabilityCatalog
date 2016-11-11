package com.daniel.grabilitycatalog.fragments;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.daniel.grabilitycatalog.R;
import com.daniel.grabilitycatalog.activities.LaunchActivity;
import com.daniel.grabilitycatalog.activities.MainActivity;
import com.daniel.grabilitycatalog.adapters.PageAdapter;
import com.daniel.grabilitycatalog.repository.CategoryRepository;

/**
 * Created by Daniel on 5/11/2016.
 */

public class PagerFragment extends Fragment {

    CategoryRepository categoryRepository = CategoryRepository.getCateRepoInstance();

    public static int NUM_PAGES;
    public ViewPager mPagerHandler;
    private PageAdapter mPagerAdapter;
    int numberCategories;
    public static MainScreenFragment[] viewFragments;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
        if (LaunchActivity.IS_TABLET) {
            getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        } else {
            getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }
        NUM_PAGES = numberCategories;
        viewFragments = new MainScreenFragment[numberCategories];
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.pager_fragment, container, false);

        mPagerHandler = (ViewPager) rootView.findViewById(R.id.pager);

        PagerTabStrip pagerTabStrip = (PagerTabStrip) rootView.findViewById(R.id.pager_header);
        pagerTabStrip.setDrawFullUnderline(true);
        pagerTabStrip.setTabIndicatorColor(getResources().getColor(R.color.cardview_shadow_start_color));
        pagerTabStrip.setBackgroundColor(getResources().getColor(R.color.gray01));
        pagerTabStrip.setTextColor(getResources().getColor(R.color.white01));

        mPagerAdapter = new PageAdapter(getChildFragmentManager(), getActivity());
        for (int i = 0; i < NUM_PAGES; i++) {
            viewFragments[i] = new MainScreenFragment();
            viewFragments[i].setFragmentCategory(String.valueOf(i));
        }
        mPagerHandler.setAdapter(mPagerAdapter);
        mPagerHandler.setCurrentItem(MainActivity.current_fragment);
        return rootView;
    }

    public void init(){
        numberCategories = categoryRepository.getCategories(getActivity()).size();
    }

}
