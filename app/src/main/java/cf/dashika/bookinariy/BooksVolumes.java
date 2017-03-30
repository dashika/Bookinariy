package cf.dashika.bookinariy;

import cf.dashika.bookinariy.Model.Volumes;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by programer on 29.03.17.
 */

public interface BooksVolumes {
    @GET("volumes")
    Call<Volumes> getVolumes(
            @Query("q") String query,
            @Query("fields") String fields,
            @Query("startIndex") String startIndex);
}
