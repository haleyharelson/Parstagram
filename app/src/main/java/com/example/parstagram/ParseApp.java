package com.example.parstagram;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseObject;

public class ParseApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        ParseObject.registerSubclass(Post.class);

        //sets up Parse server
        final Parse.Configuration configuration = new Parse.Configuration.Builder(this)
                .applicationId("this-is-my-app-id")
                .clientKey("this-is-my-master-key")
                .server("https://haleyharelson-fbu-parstagram.herokuapp.com/parse")
                .build();
        Parse.initialize(configuration);
    }
}
