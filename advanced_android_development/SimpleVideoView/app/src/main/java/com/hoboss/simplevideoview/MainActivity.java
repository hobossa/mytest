package com.hoboss.simplevideoview;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {
    private static final String VIDEO_SAMPLE = "tacoma_narrows";
    private static final String PLAYBACK_TIME = "play_time";
    private VideoView mVideoView;
    private int mCurrentPosition = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mVideoView = findViewById(R.id.videoview);
        if (savedInstanceState != null) {
            mCurrentPosition = savedInstanceState.getInt(PLAYBACK_TIME);
        }
        MediaController controller = new MediaController(this);
        controller.setMediaPlayer(mVideoView);
        mVideoView.setMediaController(controller);
    }

    @Override
    protected void onStart() {
        super.onStart();

        initializePlayer();
    }

    @Override
    protected void onStop() {
        super.onStop();

        releasePlayer();
    }

    @Override
    protected void onPause() {
        super.onPause();

        //This test is required because the behavior of onPause() and onStop() changed in
        // Android N (7.0, API 24). In older versions of Android, onPause() was the end of
        // the visual lifecycle of your app, and you could start releasing resources when
        // the app was paused.
        //
        //In newer versions of Android, your app may be paused but still visible on the
        // screen, as with multi-window or picture-in-picture (PIP) mode. In those cases
        // the user likely wants the video to continue playing in the background. If the
        // video is being played in multi-window or PIP mode, then it is onStop() that
        // indicates the end of the visible life cycle of the app, and your video playback
        // should indeed stop at that time.
        //
        //If you only stop playing your video in onStop(), as in the previous step, then on
        // older devices there may be a few seconds where even though the app is no longer
        // visible on screen, the video's audio track continues to play while onStop() catches
        // up. This test for older versions of Android pauses the actual playback in onPause()
        // to prevent the sound from playing after the app has disappeared from the screen.
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
            mVideoView.pause();
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt(PLAYBACK_TIME, mVideoView.getCurrentPosition());
    }

    private Uri getMedia(String mediaName) {
        return Uri.parse("android.resource://" + getPackageName() +
                "/raw/" + mediaName);
    }

    private void initializePlayer() {
        Uri videoUri = getMedia(VIDEO_SAMPLE);
        mVideoView.setVideoURI(videoUri);
        if (mCurrentPosition > 0) {
            mVideoView.seekTo(mCurrentPosition);
        } else {
            // Skipping to 1 shows the first frame of the video.
            mVideoView.seekTo(1);
        }
        mVideoView.start();
        mVideoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                Toast.makeText(MainActivity.this, "Playback completed",
                        Toast.LENGTH_SHORT).show();
                mVideoView.seekTo(1);
            }
        });
    }

    private void releasePlayer() {
        mVideoView.stopPlayback();
    }
}
