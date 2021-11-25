package com.example.truyencuoi.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.truyencuoi.R;
import com.example.truyencuoi.listeners.RecycleViewListener;
import com.example.truyencuoi.model.Story;

import java.util.List;

public class StoryAdapter extends RecyclerView.Adapter<StoryAdapter.ViewHolder> {

    private List<Story> stories;
    private RecycleViewListener recycleViewListener;

    public void setRecycleViewListener(RecycleViewListener recycleViewListener) {
        this.recycleViewListener = recycleViewListener;
    }

    public StoryAdapter(List<Story> stories) {
        this.stories = stories;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.story_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Story story = stories.get(position);

        holder.storyName.setText(story.getName());
        holder.storyItemContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (recycleViewListener != null){
                    recycleViewListener.onClick(story);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return stories.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView storyName;
        public LinearLayout storyItemContainer;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            storyName = itemView.findViewById(R.id.story_name);
            storyItemContainer = itemView.findViewById(R.id.story_item_container);
        }
    }
}
