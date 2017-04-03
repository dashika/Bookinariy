package cf.dashika.bookinariy.Model;

import android.os.Parcel;
import android.os.Parcelable;

 public class VolumeInfo implements Parcelable {
     public String getTitle() {
         return title;
     }

     public void setTitle(String title) {
         this.title = title;
     }


     public ImageLinks getImageLinks() {
         return imageLinks;
     }

     public void setImageLinks(ImageLinks imageLinks) {
         this.imageLinks = imageLinks;
     }

     private String title;
     private ImageLinks imageLinks;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeParcelable(this.imageLinks, flags);
    }

    public VolumeInfo() {
    }

    protected VolumeInfo(Parcel in) {
        this.title = in.readString();
        this.imageLinks = in.readParcelable(ImageLinks.class.getClassLoader());
    }

    public static final Parcelable.Creator<VolumeInfo> CREATOR = new Parcelable.Creator<VolumeInfo>() {
        @Override
        public VolumeInfo createFromParcel(Parcel source) {
            return new VolumeInfo(source);
        }

        @Override
        public VolumeInfo[] newArray(int size) {
            return new VolumeInfo[size];
        }
    };
}
