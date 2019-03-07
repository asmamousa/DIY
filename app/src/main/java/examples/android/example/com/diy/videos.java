package examples.android.example.com.diy;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;

public class videos implements Parcelable {

    private String id;
    private String name;
    private String video;
    private String image;
    private ArrayList<tools> toolsList=new ArrayList<>();


    private videos (Parcel in) {


        id=in.readString();
        name=in.readString();
        video =in.readString();
        image =in.readString();

       in.readList(toolsList,tools.class.getClassLoader());

    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(id);
        dest.writeString(name);
        dest.writeString(video);
        dest.writeString(image);

        dest.writeList(toolsList);

    }


    public static final Parcelable.Creator<videos> CREATOR = new Parcelable.Creator<videos>()
    {
        public videos createFromParcel(Parcel in)
        {
            return new videos(in);
        }
        public videos[] newArray(int size)
        {
            return new videos[size];
        }
    };



    public videos (String id, String name, String video, String image,ArrayList<tools> toolsList){

        this.id=id;
        this.name=name;
        this.video = video;
        this.image =image;
        this.toolsList=toolsList;
    }


    public String getName() { return name; }
    public String getVideo()
    {
        return video;
    }
    public String getImage() {
        return image;
    }
    public String getId() { return id;}
    public ArrayList<tools> getToolsList(){return toolsList;}


}
