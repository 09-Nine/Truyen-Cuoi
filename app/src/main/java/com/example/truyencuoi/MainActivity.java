package com.example.truyencuoi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.util.Log;

import com.example.truyencuoi.adapters.StoryAdapter;
import com.example.truyencuoi.listeners.NavListener;
import com.example.truyencuoi.listeners.RecycleViewListener;
import com.example.truyencuoi.model.Category;
import com.example.truyencuoi.model.Story;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity implements NavListener {

    private List<Category> categoryList = new ArrayList<>();
    private List<Story> stories = StoryStorage.stories;
    private RecyclerView recyclerView;
    private StoryAdapter storyAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);

        NavigationDrawerFragment drawerFragment = (NavigationDrawerFragment) getSupportFragmentManager()
                .findFragmentById(R.id.nav_frag);
        drawerFragment.setNavListener(this);
        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
        initCategory();
        initRecycleView();
        drawerFragment.init(drawerLayout, toolbar, categoryList);

    }

    private void initCategory(){
        try {
            AssetManager assetManager = getAssets();
            String[] files = assetManager.list("data");
            for (String file : files){
                String name = file.replace(".txt", "");
                String imageName = "icon/" + name + ".png";
                categoryList.add(new Category(name, imageName));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initStory(String filePath) {
        stories.clear();
        StringBuilder text = new StringBuilder();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(
                    new InputStreamReader(getAssets().open("data/" + filePath)));
            String mLine;
            while ((mLine = reader.readLine()) != null){
                text.append(mLine).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null){
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        String[] storyText = text.toString().split("','0'\\);\n");
        for (String t : storyText){
            String[] splitText = t.split("\n");
            stories.add((new Story(splitText[0].trim(),
                    String.join("\n", Arrays.copyOfRange(splitText, 1, splitText.length)))));
        }
    }

    private void initRecycleView(){
        recyclerView = findViewById(R.id.recycle_view_story);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        storyAdapter = new StoryAdapter(stories);
        storyAdapter.setRecycleViewListener(new RecycleViewListener() {
            @Override
            public void onClick(Story story) {
                Intent intent = new Intent(MainActivity.this, StoryDetailActivity.class);
                intent.putExtra("currentStoryIdx", StoryStorage.stories.indexOf(story));
                startActivity(intent);
            }
        });
        recyclerView.setAdapter(storyAdapter);
    }

    @Override
    public void setCategory(String category) {
        StoryStorage.currentCategory = category;
        initStory(category + ".txt");
        storyAdapter.notifyDataSetChanged();
    }
}