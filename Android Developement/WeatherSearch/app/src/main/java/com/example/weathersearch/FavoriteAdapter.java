package com.example.weathersearch;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class FavoriteAdapter extends FragmentPagerAdapter {

    private final List<Fragment> listFragment= new ArrayList<>();
    private final List<String> listTitles = new ArrayList<>();

    public FavoriteAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    public FavoriteAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return listFragment.get(position);
    }

    @Override
    public int getCount() {
        return listTitles.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return null;
    }



    public void AddFragment(Fragment fragment,String position){
        listFragment.add(fragment);
        listTitles.add(position);
    }

    public void removeFragment(String position){
        listFragment.remove(position);
        listTitles.remove(position);

    }
}
