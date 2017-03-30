package cf.dashika.bookinariy;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import cf.dashika.bookinariy.Model.Item;

import java.util.List;

class MyItemRecyclerViewAdapter extends RecyclerView.Adapter<MyItemRecyclerViewAdapter.ViewHolder> {

    private Context context;
    private final List<Item> mValues;

    MyItemRecyclerViewAdapter(Context context, List<Item> items) {
        mValues = items;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mTitleView.setText(mValues.get(position).volumeInfo.getTitle());
        if (mValues.get(position).volumeInfo.getImageLinks() != null)
            Picasso.with(context).load(mValues.get(position).volumeInfo.getImageLinks().thumbnail)
                    .placeholder(R.drawable.placeholder)
                    .error(R.drawable.error)
                    .fit()
                    .into(holder.simpleDraweeView);
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        final View mView;
        final ImageView simpleDraweeView;
        final TextView mTitleView;
        Item mItem;

        ViewHolder(View view) {
            super(view);
            mView = view;
            simpleDraweeView = (ImageView) view.findViewById(R.id.srcBook);
            mTitleView = (TextView) view.findViewById(R.id.twTitleBook);
        }
    }
}
