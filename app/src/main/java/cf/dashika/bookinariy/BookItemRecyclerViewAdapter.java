package cf.dashika.bookinariy;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import cf.dashika.bookinariy.Model.Item;

import java.util.List;

class BookItemRecyclerViewAdapter extends RecyclerView.Adapter<BookItemRecyclerViewAdapter.ViewHolder> {

    private Context context;
    private final List<Item> mValues;

    BookItemRecyclerViewAdapter(Context context, List<Item> items) {
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
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.mItem = mValues.get(position);
        holder.mTitleView.setText(mValues.get(position).volumeInfo.getTitle());
            Picasso.with(context).load(mValues.get(position).volumeInfo.getImageLinks() != null ?
                    mValues.get(position).volumeInfo.getImageLinks().thumbnail : "bad link")
                    .placeholder(R.drawable.placeholder)
                    .error(R.drawable.error)
                    .fit()
                    .into(holder.simpleDraweeView);
        holder.mTitleView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,ReadBookActivity.class);
                intent.putExtra(context.getString(R.string.webReaderLink), mValues.get(position).accessInfo.getWebReaderLink());
                context.startActivity(intent);
            }
        });
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
