package lingchuang.try_0811_video_youtube;

import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.google.android.youtube.player.YouTubeThumbnailLoader;
import com.google.android.youtube.player.YouTubeThumbnailView;


public class MainActivity extends YouTubeBaseActivity {
    public static final String key="AIzaSyBxn0UL0DtligJ4EfBgfqoHZYfsSERowps";
    private YouTubeThumbnailView youtube_thumbnail;
    private YouTubePlayerView youtube_view;
    private Context context=MainActivity.this;
    private YouTubePlayer Yplayer;
    private String vid ="NEsPDgSrbXU";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        youtube_view = (YouTubePlayerView) findViewById(R.id.view2);
        youtube_view.initialize(key, new YoutubeOnInitializedListener());
        youtube_thumbnail = (YouTubeThumbnailView) findViewById(R.id.view);
        youtube_thumbnail.initialize(key, new YoutubeThumbnailOnInitializedListener());
        youtube_thumbnail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Play !", Toast.LENGTH_SHORT).show();
                Yplayer.cueVideo(vid);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
//新增自制class & 改寫thumbnailview內部方法
    private class YoutubeThumbnailOnInitializedListener implements YouTubeThumbnailView.OnInitializedListener {
                @Override
                public void onInitializationSuccess(YouTubeThumbnailView youTubeThumbnailView, YouTubeThumbnailLoader youTubeThumbnailLoader) {
                    youTubeThumbnailLoader.setVideo(vid);
                }
                @Override
                public void onInitializationFailure(YouTubeThumbnailView youTubeThumbnailView, YouTubeInitializationResult youTubeInitializationResult) {
                    Toast.makeText(context,"Initialize Failure!!\n"+youTubeInitializationResult,Toast.LENGTH_SHORT).show();
                }
            }
//改寫youtube player Oninitializedlistener內部方法
    private class YoutubeOnInitializedListener implements YouTubePlayer.OnInitializedListener {
                @Override
                public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult result) {
                    Toast.makeText(context, "Youtube onInitializationFailure !"+result.toString(), Toast.LENGTH_SHORT).show();
                }
                @Override
                public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer player, boolean wasRestored) {
                    if (!wasRestored) {
                        Yplayer=player;
                        player.loadVideo(vid);
                    }
                }
            }

}
