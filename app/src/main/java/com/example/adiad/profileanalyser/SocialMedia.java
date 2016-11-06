package com.example.adiad.profileanalyser;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.facebook.login.widget.ProfilePictureView;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

import java.util.ArrayList;
import java.util.List;

import pl.droidsonroids.gif.GifImageView;


public class SocialMedia extends Fragment  implements ConnectivityReceiver.ConnectivityReceiverListener{

    private GifImageView gif;
    public GoogleApiClient client;
    CallbackManager callbackManager;
    TextView status;
    Button likes,images;
    Button posts;
    LoginButton loginButton;
    private ProfilePictureView profilePictureView;
    AccessTokenTracker access_tracker;
    ProfileTracker profile_tracker;
    Profile currentProfile;
    AccessToken currentAccessToken;

    FacebookCallback<LoginResult> callback = new FacebookCallback<LoginResult>() {
        @Override
        public void onSuccess(LoginResult loginResult) {
            currentAccessToken = loginResult.getAccessToken();
            currentProfile = Profile.getCurrentProfile();
            Log.i("messageerror","chal gaya");
            displayinfo(currentProfile);
        }

        @Override
        public void onCancel() {
            Log.i("messageerror","cancel ho gaya");
        }

        @Override
        public void onError(FacebookException error) {

            Log.i("messageerror","error aa gaya");
        }
    };
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client2;

    protected void displayinfo(Profile profile) {
        if (profile != null) {
            //status.setText("welcome: " + profile.getName());
            profilePictureView.setProfileId(profile.getId());
            likes.setEnabled(true);
            posts.setEnabled(true);
            likes.setVisibility(View.VISIBLE);
            posts.setVisibility(View.VISIBLE);
            profilePictureView.setVisibility(View.VISIBLE);
          //  status.setVisibility(View.VISIBLE);
        } else {
            profilePictureView.setProfileId(null);
           // status.setText("Logged Out!, Please Log In");
            likes.setEnabled(false);
            posts.setEnabled(false);
            likes.setVisibility(View.GONE);
            posts.setVisibility(View.GONE);
            profilePictureView.setVisibility(View.GONE);
           // status.setVisibility(View.GONE);
        }

    }

    private static TextView tvfb;
    private static TextView tvtw;

    public SocialMedia() {

    }

    public SocialMedia(CallbackManager managern) {
        this.callbackManager =managern;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getContext());
        callbackManager = CallbackManager.Factory.create();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client2 = new GoogleApiClient.Builder(getContext()).addApi(AppIndex.API).build();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        final View rootView = inflater.inflate(R.layout.fragment_social_media, container, false);
//        tvfb=(TextView)rootView.findViewById(R.id.tvfb);
//        tvtw=(TextView)rootView.findViewById(R.id.tvtw);
//c
        loginButton = (LoginButton) rootView.findViewById(R.id.login_button);
        checkConnection(rootView);
        List<String> permissions = new ArrayList<String>();
        permissions.add("user_likes");
        permissions.add("user_posts");
        permissions.add("user_birthday");
        loginButton.setReadPermissions(permissions);
        loginButton.registerCallback(callbackManager, callback);
       // status = (TextView) rootView.findViewById(R.id.status);
        likes = (Button) rootView.findViewById(R.id.likes);
        posts = (Button) rootView.findViewById(R.id.posts);
        images = (Button)rootView.findViewById(R.id.lavish);
        profilePictureView = (ProfilePictureView) rootView.findViewById(R.id.user_pic);
        displayinfo(Profile.getCurrentProfile());
        BottomBar bottomBar = (BottomBar) rootView.findViewById(R.id.bottomBar);
        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                if (tabId == R.id.facebook) {
                    checkConnection(rootView);
                    loginButton.setVisibility(View.VISIBLE);
                    likes.setVisibility(View.VISIBLE);
                    posts.setVisibility(View.VISIBLE);

                    profilePictureView.setVisibility(View.VISIBLE);
                   // status.setVisibility(View.VISIBLE);
                }
                if(tabId == R.id.twitter)
                {
                    checkConnection(rootView);
                    loginButton.setVisibility(View.GONE);
                    likes.setVisibility(View.GONE);
                    posts.setVisibility(View.GONE);
                    profilePictureView.setVisibility(View.GONE);
                    //status.setVisibility(View.GONE);
                }
            }
        });

        images.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(getActivity(),Lavish.class);
                startActivity(i);

            }
        });



        profilePictureView.setCropped(true);

        if(Profile.getCurrentProfile()==null)
        {

        }
        posts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i2 = new Intent(getContext(), ShowPosts.class);
                i2.putExtra("currentAccessToken", AccessToken.getCurrentAccessToken());
                startActivity(i2);
            }
        });
        likes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1 = new Intent(getContext(), ShowLikes.class);
                i1.putExtra("currentAccessToken", AccessToken.getCurrentAccessToken());
                startActivity(i1);
            }
        });
        access_tracker = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken newtAccessToken) {
                currentAccessToken = newtAccessToken;
            }
        };
        profile_tracker = new ProfileTracker() {
            @Override
            protected void onCurrentProfileChanged(Profile oldProfile, Profile newProfile) {
                currentProfile = newProfile;
                displayinfo(currentProfile);
            }
        };
//        // ATTENTION: This was auto-generated to implement the App Indexing API.
//        // See https://g.co/AppIndexing/AndroidStudio for more information.

        client = new GoogleApiClient.Builder(getContext()).addApi(AppIndex.API).build();

        return rootView;

    }


//
    @Override
    public void onDestroy() {
        super.onDestroy();
        access_tracker.stopTracking();
        profile_tracker.stopTracking();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);

    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client2.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "SocialMedia Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.example.adiad.profileanalyser/http/host/path")
        );
        AppIndex.AppIndexApi.start(client2, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "SocialMedia Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.example.adiad.profileanalyser/http/host/path")
        );
        AppIndex.AppIndexApi.end(client2, viewAction);
        client2.disconnect();
    }

    private void checkConnection(View rootView) {
        boolean isConnected = ConnectivityReceiver.isConnected();
        showSnack(isConnected, rootView);
    }

    // Showing the status in Snackbar
    private void showSnack(boolean isConnected,View rootView) {

        String message;
        int color;
        likes = (Button) rootView.findViewById(R.id.likes);
        posts = (Button) rootView.findViewById(R.id.posts);
        loginButton = (LoginButton) rootView.findViewById(R.id.login_button);
        profilePictureView = (ProfilePictureView) rootView.findViewById(R.id.user_pic);
        gif=(GifImageView) rootView.findViewById(R.id.gif);
        if (isConnected) {
            message = "Good! Connected to Internet";
           gif.setVisibility(View.GONE);
            likes.setVisibility(View.VISIBLE);
            posts.setVisibility(View.VISIBLE);
            loginButton.setVisibility(View.VISIBLE);
            profilePictureView.setVisibility(View.VISIBLE);
            color = Color.WHITE;


        } else {
            message = "Sorry! Not connected to internet";

            color = Color.RED;


            gif.setVisibility(View.VISIBLE);
            likes.setVisibility(View.GONE);
            posts.setVisibility(View.GONE);
            loginButton.setVisibility(View.GONE);
            profilePictureView.setVisibility(View.GONE);
        }

    }

    @Override
    public void onResume() {
        super.onResume();

        // register connection status listener
        MyApplication.getInstance().setConnectivityListener(this);
    }

    /**
     * Callback will be triggered when there is change in
     * network connection
     */
    @Override
    public void onNetworkConnectionChanged(boolean isConnected) {
        showSnack(isConnected, getView());
    }
}
