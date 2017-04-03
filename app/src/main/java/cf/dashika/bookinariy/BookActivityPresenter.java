package cf.dashika.bookinariy;

import android.app.Activity;
import android.app.ProgressDialog;
import android.widget.Toast;

import cf.dashika.bookinariy.Model.Volumes;
import cf.dashika.bookinariy.Util.Events;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by programer on 29.03.17.
 */

public class BookActivityPresenter {

    private Activity activity;


    BookActivityPresenter(Activity activity) {
        this.activity = activity;


        mProgressDialog = new ProgressDialog(activity);
        mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        mProgressDialog.setMessage(activity.getString(R.string.loading));
    }

    private ProgressDialog mProgressDialog;

    void performQuery(String query, boolean fromBegin) {
        if (!mProgressDialog.isShowing()) {
            if (fromBegin) BookingApplication.get().getItemArrayList().clear();
            mProgressDialog.show();
            Call<Volumes> call = BookingApplication.get().getmBooksVolumesService().getVolumes(query,
                   activity.getString(R.string.volumes_fields),
                    BookingApplication.get().getItemArrayList().size() + "");
            call.enqueue(new Callback<Volumes>() {
                @Override
                public void onResponse(Call<Volumes> call, Response<Volumes> response) {
                    if (response.isSuccessful())
                        BookingApplication.get().bus().send(new Events.setVolumes(response.body()));
                    mProgressDialog.dismiss();
                }

                @Override
                public void onFailure(Call<Volumes> call, Throwable t) {
                    Toast.makeText(activity, t.getMessage(), Toast.LENGTH_LONG).show();
                    mProgressDialog.dismiss();
                }
            });
        }
    }

}
