package yoav.solar.toys;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import yoav.solar.model.Toy;
import yoav.solar.model.Toys;

public class ToyAdapter extends RecyclerView.Adapter<ToyAdapter.ToyViewHolder> {
    private Toys toys;
    private Context context;
    private int single_layout;

    public interface OnItemClickListener {
        public void onItemClicked(Toy toy);
    }

    public interface OnItemLongClickListener{
        public boolean OnItemLongClicked(Toy toy);
    }
    private OnItemClickListener listener;
    private OnItemLongClickListener longlistener;

    public ToyAdapter(Context context, Toys toy, int single_layout,OnItemClickListener listener,OnItemLongClickListener longlistener) {
        this.context = context;
        this.toys = toy;
        this.single_layout = single_layout;
        this.listener=listener;
        this.longlistener=longlistener;
    }
    public void setToys(Toys toys) {
        this.toys = toys;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public ToyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(single_layout, parent, false);
        return new ToyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ToyViewHolder holder, int position) {
        Toy toy = toys.get(position);
       if(toy!=null)
           holder.bind(toy, listener,longlistener);
    }

    @Override
    public int getItemCount() {
        return (toys == null) ? 0:toys.size();
    }

    public static class ToyViewHolder extends RecyclerView.ViewHolder {
        private ImageView ivToy;
        private TextView tvName, tvPrice;

        public ToyViewHolder(@NonNull View itemView) {
            super(itemView);
            ivToy = itemView.findViewById(R.id.ivCategory);
            tvName = itemView.findViewById(R.id.tvCName);
            tvPrice = itemView.findViewById(R.id.tvPrice);
        }
        public void bind(Toy toy,OnItemClickListener listener,OnItemLongClickListener longlistener){
            //ivToy.setImageBitmap(toy.getPicture());
            tvName.setText(toy.getName());
            tvPrice.setText(String.valueOf(toy.getPrice()));
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClicked(toy);
                }
            });
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    longlistener.OnItemLongClicked(toy);
                    return true;
                }
            });
        }
    }
}
