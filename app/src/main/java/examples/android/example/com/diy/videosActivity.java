package examples.android.example.com.diy;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;


import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import butterknife.BindView;
import butterknife.ButterKnife;
import examples.android.example.com.diy.Adapters.SmallHacksAdapter;
import examples.android.example.com.diy.NetworkUtilities.JsonUtils;
import examples.android.example.com.diy.NetworkUtilities.NetworkUtils;

public class videosActivity extends AppCompatActivity implements SmallHacksAdapter.ListItemClickListener {

    private static final String LOG_TAG=Constants.error;
    List<videos> JSONResult=new ArrayList<>();
    Context context=this;
    SmallHacksAdapter adapter;

    @BindView(R.id.recycler)
    RecyclerView Recycler;

    int test;

    public static final String BUNDLE = "bundle";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.videos_activity);
        ButterKnife.bind(this);

        URL u = NetworkUtils.buildUrl();
        try {
            JSONResult = new myAsyncTask().execute(u).get();

        } catch (ExecutionException e) {

            Log.e(LOG_TAG, e.getMessage(), e);

        } catch (InterruptedException e) {

            Log.e(LOG_TAG, e.getMessage(), e);

        }


        adapter = new SmallHacksAdapter(JSONResult, this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        Recycler.setLayoutManager(linearLayoutManager);
        Recycler.setAdapter(adapter);


    }


        @SuppressLint("StaticFieldLeak")
        private class myAsyncTask extends AsyncTask<URL, Void, List<videos>> {

            protected List<videos> doInBackground(URL... urls) {

                URL u = urls[0];

                String RecipeResults = null;

                try {
                    RecipeResults = NetworkUtils.getResponseFromHttpUrl(u);
                    JSONResult= JsonUtils.parseHacksJson(RecipeResults);



                } catch (IOException e) {
                    Log.e(LOG_TAG, e.getMessage(), e);
                }

                return JSONResult;

            }


            protected void onPostExecute(List<videos> result) {

                if(result==null || result.size()==0){
                    AlertDialog.Builder builder = new AlertDialog.Builder(videosActivity.this,R.style.MyDialogTheme);
                    builder.setMessage(R.string.noData);

                    builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            finish();
                        }
                    });
                }
                super.onPostExecute(result);
            }
        }







    @Override
    public void onListItemClick(int clickedItemIndex) {

        Intent intent=new Intent(videosActivity.this,DetailsActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable(Constants.data, JSONResult.get(clickedItemIndex));
        intent.putExtras(bundle);
        startActivity(intent);

        test=clickedItemIndex;

        startWidgetService();


    }

    void startWidgetService()
    {
        if(JSONResult!=null ){
            Intent i = new Intent(this, WidgetUpdateService.class);
            Bundle bundle = new Bundle();

            bundle.putString("videoName", JSONResult.get(test).getName());
            i.putExtra(videosActivity.BUNDLE, bundle);
            i.setAction(WidgetUpdateService.WIDGET_UPDATE_ACTION);
            startService(i);
        }

    }
}
