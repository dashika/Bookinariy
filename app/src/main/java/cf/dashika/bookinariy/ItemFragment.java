package cf.dashika.bookinariy;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import cf.dashika.bookinariy.Model.Item;
import cf.dashika.bookinariy.Util.EndlessRecyclerViewScrollListener;
import cf.dashika.bookinariy.Util.Events;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

public class ItemFragment extends Fragment {

    private BookItemRecyclerViewAdapter adapter;

    public ItemFragment() {
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
        if (o instanceof Events.setVolumes) {
            ArrayList<Item> items = ((Events.setVolumes) o).volumes.items;
            if (items == null) return;
            BookingApplication.get().getItemArrayList().addAll(items);
            adapter.notifyDataSetChanged();
        }
    }

    private void autoUnsubBus() {
        if (busSubscription != null && !busSubscription.isUnsubscribed()) {
            busSubscription.unsubscribe();
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_list, container, false);

        if (view instanceof RecyclerView) {
            RecyclerView recyclerView = (RecyclerView) view;
            recyclerView.setHasFixedSize(true);
            StaggeredGridLayoutManager gaggeredGridLayoutManager = new StaggeredGridLayoutManager(3, 1);
            recyclerView.setLayoutManager(gaggeredGridLayoutManager);
            EndlessRecyclerViewScrollListener scrollListener = new EndlessRecyclerViewScrollListener(gaggeredGridLayoutManager) {
                @Override
                public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                    BookingApplication.get().bus().send(new Events.refresh());
                }
            };
            recyclerView.addOnScrollListener(scrollListener);
            adapter = new BookItemRecyclerViewAdapter(getActivity(), BookingApplication.get().getItemArrayList());
            recyclerView.setAdapter(adapter);
        }
        return view;
    }

}
