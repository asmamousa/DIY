package examples.android.example.com.diy;

import android.os.Parcel;
import android.os.Parcelable;

public class tools implements Parcelable {

    private String number;
    private String name;



    private tools (Parcel in) {


        number=in.readString();
        name=in.readString();

    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(number);
        dest.writeString(name);

    }


    public static final Parcelable.Creator<tools> CREATOR = new Parcelable.Creator<tools>()
    {
        public tools createFromParcel(Parcel in)
        {
            return new tools(in);
        }
        public tools[] newArray(int size)
        {
            return new tools[size];
        }
    };



    public tools (String number, String name){

        this.number=number;
        this.name=name;

    }


    public String getName() { return name; }
    public String getNumber()
    {
        return number;
    }

}
