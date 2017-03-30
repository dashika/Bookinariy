package cf.dashika.bookinariy.Model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * ImageLinks in Book data.
 */
public class ImageLinks implements Parcelable {
    public String smallThumbnail;
    public String thumbnail;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.smallThumbnail);
        dest.writeString(this.thumbnail);
    }

    public ImageLinks() {
    }

    protected ImageLinks(Parcel in) {
        this.smallThumbnail = in.readString();
        this.thumbnail = in.readString();
    }

    public static final Parcelable.Creator<ImageLinks> CREATOR = new Parcelable.Creator<ImageLinks>() {
        @Override
        public ImageLinks createFromParcel(Parcel source) {
            return new ImageLinks(source);
        }

        @Override
        public ImageLinks[] newArray(int size) {
            return new ImageLinks[size];
        }
    };
}
