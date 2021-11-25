package com.example.truyencuoi;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.truyencuoi.adapters.NavAdapter;
import com.example.truyencuoi.listeners.AdapterListener;
import com.example.truyencuoi.listeners.NavListener;
import com.example.truyencuoi.model.Category;

import java.util.List;

public class NavigationDrawerFragment extends Fragment {

    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawerLayout;
    private RecyclerView recyclerView;
    private NavListener navListener;

    public void setNavListener(NavListener navListener) {
        this.navListener = navListener;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.navigation_drawer_fragment, container, false);
        recyclerView = v.findViewById(R.id.drawer_list);
        return v;
    }

    public void init(DrawerLayout drawerLayout, Toolbar toolbar, List<Category> categories){
         mDrawerToggle = new ActionBarDrawerToggle(getActivity(), drawerLayout,
                toolbar, R.string.app_name, R.string.app_name){
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                requireActivity().invalidateOptionsMenu();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                requireActivity().invalidateOptionsMenu();
            }
        };

         drawerLayout.addDrawerListener(mDrawerToggle);
         drawerLayout.post(new Runnable() {
             @Override
             public void run() {
                 mDrawerToggle.syncState();
             }
         });
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        NavAdapter adapter = new NavAdapter(categories);
        recyclerView.setAdapter(adapter);
        adapter.setListener(new AdapterListener() {
            @Override
            public void onClick(String category) {
                navListener.setCategory(category);
                drawerLayout.closeDrawers();
            }
        });
    }
}
