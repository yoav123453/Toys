package yoav.solar.toys;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import yoav.solar.model.Categories;
import yoav.solar.model.Category;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.CategoryViewHolder> {
    private Categories categories;
    private Context context;
    private int single_layout;

    public interface OnItemClickListener {
        void onItemClicked(Category category);
    }

    public interface OnItemLongClickListener {
        boolean OnItemLongClicked(Category category);
    }

    private OnItemClickListener listener;
    private OnItemLongClickListener longListener;

    public CategoriesAdapter(Context context,Categories categories, int single_layout, OnItemClickListener listener, OnItemLongClickListener longListener) {
        this.context = context;
        this.categories = categories;
        this.single_layout = single_layout;
        this.listener = listener;
        this.longListener = longListener;
    }

    public void setToyCategories(Categories categories) {
        this.categories = categories;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(single_layout, parent, false);
        return new CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        Category category = categories.get(position);
        if (category != null)
            holder.bind(category, listener, longListener);
    }

    @Override
    public int getItemCount() {
        return (categories == null) ? 0:categories.size();
    }

    public static class CategoryViewHolder extends RecyclerView.ViewHolder {
        TextView tvCategoryName;

        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            tvCategoryName = itemView.findViewById(R.id.tvCName);
        }
        public void bind(Category category, OnItemClickListener listener, OnItemLongClickListener longListener) {
            tvCategoryName.setText(category.getName());
            itemView.setOnClickListener(v -> listener.onItemClicked(category));
            itemView.setOnLongClickListener(v -> longListener.OnItemLongClicked(category));
        }
    }
}