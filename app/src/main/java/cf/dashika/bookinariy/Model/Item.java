package cf.dashika.bookinariy.Model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Items in Book data.
 */
public class Item implements Parcelable {
    public VolumeInfo volumeInfo;


    public AccessInfo accessInfo;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.volumeInfo, flags);

        dest.writeParcelable(this.accessInfo,flags);
    }

    public Item() {
    }

    protected Item(Parcel in) {
        this.volumeInfo = in.readParcelable(VolumeInfo.class.getClassLoader());

        this.accessInfo = in.readParcelable(AccessInfo.class.getClassLoader());
    }

    public static final Parcelable.Creator<Item> CREATOR = new Parcelable.Creator<Item>() {
        @Override
        public Item createFromParcel(Parcel source) {
            return new Item(source);
        }

        @Override
        public Item[] newArray(int size) {
            return new Item[size];
        }
    };
}
