package examples.android.example.com.diy;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import examples.android.example.com.diy.Adapters.toolsAdapter;

public class DetailsActivity extends YouTubeBaseActivity {

    private final String API_KEY= Constants.api;
       private videos clickedVideo;

    @BindView(R.id.playerView)
    YouTubePlayerView youTubePlayerView;

    @BindView(R.id.toolsInformation)
    RecyclerView toolsList;
    toolsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details_activity);
        ButterKnife.bind(this);

        Bundle bundle = getIntent().getExtras();

        if (bundle != null) {
          clickedVideo = bundle.getParcelable(Constants.data);
        }

        adapter = new toolsAdapter(clickedVideo.getToolsList());


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        toolsList.setLayoutManager(linearLayoutManager);
        toolsList.setAdapter(adapter);

        youTubePlayerView.initialize(API_KEY, new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {

                if(!b){
                    youTubePlayer.loadVideo(clickedVideo.getVideo());
                    youTubePlayer.setPlayerStyle(YouTubePlayer.PlayerStyle.DEFAULT);
                }
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

                Toast.makeText(getApplicationContext(), Constants.error, Toast.LENGTH_LONG).show();

            }
        });

    }

    }
