package cf.dashika.bookinariy.Model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Items in Book data.
 */
public class Item implements Parcelable {
    public VolumeInfo volumeInfo;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.volumeInfo, flags);
    }

    public Item() {
    }

    protected Item(Parcel in) {
        this.volumeInfo = in.readParcelable(VolumeInfo.class.getClassLoader());
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
