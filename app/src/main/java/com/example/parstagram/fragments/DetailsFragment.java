package com.example.parstagram.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.parstagram.Post;
import com.example.parstagram.R;
import com.parse.ParseFile;

public class DetailsFragment extends Fragment {

    //the post to display
    Post post;

    //the view objects
    TextView tvHandle;
    ImageView ivImage;
    TextView tvDescription;
    TextView tvTimestamp;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        post = (Post) getArguments().getSerializable("anything");
        return inflater.inflate(R.layout.fragment_details, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tvHandle = (TextView) view.findViewById(R.id.tvHandle);
        ivImage = (ImageView) view.findViewById(R.id.ivImage);
        tvDescription = (TextView) view.findViewById(R.id.tvDescription);
        tvTimestamp = (TextView) view.findViewById(R.id.tvTimestamp);

        tvHandle.setText(post.getUser().getUsername());
        ParseFile image = post.getImage();

        if (image != null) {
            Glide.with(getContext()).load(image.getUrl()).into(ivImage);
        }

        tvDescription.setText(post.getDescription());
        String timeAgo = post.getRelativeTimeAgo();
        tvTimestamp.setText(timeAgo);
    }
}
