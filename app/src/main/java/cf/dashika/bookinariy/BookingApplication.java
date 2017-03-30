package cf.dashika.bookinariy;

import android.app.Application;

import com.hwangjr.rxbus.Bus;

import java.util.ArrayList;

import cf.dashika.bookinariy.Model.Item;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.subjects.PublishSubject;
import rx.subjects.SerializedSubject;
import rx.subjects.Subject;

/**
 * Created by programer on 29.03.17.
 */

public class BookingApplication extends Application {

    private static BookingApplication instance;

    public static BookingApplication get() {
        return instance;
    }

    public ArrayList<Item> getItemArrayList() {
        return itemArrayList;
    }

    private final ArrayList<Item> itemArrayList = new ArrayList<>();

    public BooksVolumes getmBooksVolumesService() {
        return mBooksVolumesService;
    }

    private BooksVolumes mBooksVolumesService;

    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;

        Retrofit mRetrofit = new Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_GOOGLE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        mBooksVolumesService = mRetrofit.create(BooksVolumes.class);

        bus = new RxBus();
    }

    private RxBus bus;

    public RxBus bus() {
        return bus;
    }

    static final class RxBus {
        private static Bus sBus;

        static synchronized Bus get() {
            if (sBus == null) {
                sBus = new Bus();
            }
            return sBus;
        }

        private final Subject<Object, Object> bus = new SerializedSubject<>(PublishSubject.create());

        void send(Object o) {
            bus.onNext(o);
        }

        Observable<Object> toObserverable() {
            return bus;
        }

        public boolean hasObservers() {
            return bus.hasObservers();
        }
    }

}
