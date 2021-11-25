package com.example.truyencuoi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toolbar;

import com.example.truyencuoi.adapters.StoryViewPaperAdapter;
import com.example.truyencuoi.model.Story;

public class StoryDetailActivity extends AppCompatActivity {

    private String pageNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story_detail);
        Intent intent = getIntent();
        int currentStoryIndex = intent.getIntExtra("currentStoryIdx", 0);
        ViewPager viewPager = findViewById(R.id.view_paper);
        viewPager.setAdapter(new StoryViewPaperAdapter(StoryStorage.stories, this));
        viewPager.setCurrentItem(currentStoryIndex);
        TextView cateName = findViewById(R.id.cate_name);
        TextView storyNumber = findViewById(R.id.story_number);
        cateName.setText(StoryStorage.currentCategory);
        pageNum = Integer.toString(currentStoryIndex + 1) + "/" + Integer.toString(StoryStorage.stories.size() + 1);
        storyNumber.setText(pageNum);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                pageNum = Integer.toString(position + 1) + "/" + Integer.toString(StoryStorage.stories.size() + 1);
                storyNumber.setText(pageNum);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}