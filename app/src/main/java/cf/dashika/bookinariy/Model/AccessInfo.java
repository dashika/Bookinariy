package cf.dashika.bookinariy.Model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by dashika on 03/04/17.
 */

public class AccessInfo implements Parcelable {

    private String webReaderLink;

    public AccessInfo(){}

    public String getWebReaderLink() {
        return webReaderLink;
    }

    public void setWebReaderLink(String webReaderLink) {
        this.webReaderLink = webReaderLink;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.webReaderLink);
    }

    protected AccessInfo(Parcel in) {
        this.webReaderLink = in.readString();
    }

    public static final Parcelable.Creator<AccessInfo> CREATOR = new Parcelable.Creator<AccessInfo>() {
        @Override
        public AccessInfo createFromParcel(Parcel source) {
            return new AccessInfo(source);
        }

        @Override
        public AccessInfo[] newArray(int size) {
            return new AccessInfo[size];
        }
    };
}
