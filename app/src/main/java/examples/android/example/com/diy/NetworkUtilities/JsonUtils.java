package examples.android.example.com.diy.NetworkUtilities;

import android.text.TextUtils;
import android.util.Log;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;
import examples.android.example.com.diy.Constants;
import examples.android.example.com.diy.tools;
import examples.android.example.com.diy.videos;


final public class JsonUtils {


    private JsonUtils() {
    }


    public static List<videos> parseHacksJson(String HacksJson) {


        String number,name;
        tools toolsObject=null;
        videos videosObject=null;
        List<videos> videos = new ArrayList<>();

        if (TextUtils.isEmpty(HacksJson)) {
            return null;
        }

        try {

            JSONArray baseJsonArray = new JSONArray(HacksJson);

            for(int i=0;i<baseJsonArray.length();i++){

                ArrayList<tools> toolsArrayList=new ArrayList<>();

                JSONObject resulti = baseJsonArray.getJSONObject(i);

                String id=resulti.optString("id");
                String Hackname=resulti.optString("name");
                String video=resulti.optString("video");
                String image=resulti.optString("image");


                JSONArray toolsArray = resulti.getJSONArray("tools");



                for (int j=0;j<toolsArray.length();j++){

                    JSONObject result2i = toolsArray.getJSONObject(j);

                    number=result2i.optString("number");
                    name=result2i.optString("name");


                   toolsObject =new tools(number,name);
                   toolsArrayList.add(toolsObject);

                }



                videosObject = new videos(id,Hackname,video,image,toolsArrayList);
                videos.add(videosObject);



            }
        } catch (JSONException e) {

            Log.e(Constants.JsonUtils, Constants.JsonErrorMsg, e);
        }

        return videos;
    }
}
