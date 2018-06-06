package com.example.android.whatsappclone;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseACL;

public class StarterApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Parse.enableLocalDatastore(this);

        Parse.initialize(new Parse.Configuration.Builder(getApplicationContext())
                .applicationId("25928717055f8a8d1be907c0b6d39ddfdc84dd9b")
                .clientKey("1cfcff1b33a368b0315c9b5f9b8fd467af6a7152")
                .server("http://18.217.58.69:80/parse/")
                .build()
        );

        //http://http://18.217.58.69:80/apps
        //user 7Riz19wr4W8N

        //ParseUser.enableAutomaticUser();
        ParseACL defaultACL = new ParseACL();
        defaultACL.setPublicReadAccess(true);
        defaultACL.setPublicWriteAccess(true);
        ParseACL.setDefaultACL(defaultACL, true);

    }
}
