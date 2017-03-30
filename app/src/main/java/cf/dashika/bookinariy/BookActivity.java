package cf.dashika.bookinariy;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import java.util.ArrayList;

import cf.dashika.bookinariy.Model.Item;
import cf.dashika.bookinariy.Util.Events;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

public class BookActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {

    private BookActivityPresenter bookActivityPresenter;

    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        searchView = ((SearchView) findViewById(R.id.searchView));
        searchView.setOnQueryTextListener(this);
    }


    private Subscription busSubscription;

    @Override
    public void onResume() {
        super.onResume();

        autoUnsubBus();
        busSubscription = BookingApplication.get().bus().toObserverable()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        new Action1<Object>() {
                            @Override
                            public void call(Object o) {
                                handlerBus(o);
                            }
                        }
                );

    }

    private void handlerBus(Object o) {
        if (o instanceof Events.refresh) {
            bookActivityPresenter.performQuery(searchView.getQuery().toString(), false);
        }
    }

    private void autoUnsubBus() {
        if (busSubscription != null && !busSubscription.isUnsubscribed()) {
            busSubscription.unsubscribe();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        bookActivityPresenter = new BookActivityPresenter(this);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        bookActivityPresenter.performQuery(query, true);
        return false;
    }

    @Override
    public boolean onQueryTextChange(String query) {
        return true;
    }
}
