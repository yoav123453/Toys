package yoav.solar.toys;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import yoav.solar.model.Categories;
import yoav.solar.model.Category;
import yoav.solar.viewmodel.ToyCategoryViewModel;

public class CategoriesActivity extends AppCompatActivity {
    private RecyclerView rvCategories;
    private CategoriesAdapter adapter;
    private ToyCategoryViewModel toyCategoriesViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_categories);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        initializeViews();
        setupViewModel();
        setRecyclerView();
    }
    private void initializeViews() {
        rvCategories = findViewById(R.id.rvCategories);
    }
    private void setupViewModel() {
        toyCategoriesViewModel = new ViewModelProvider(this).get(ToyCategoryViewModel.class);
        toyCategoriesViewModel.getToyCategoriesLiveData().observe(this, toyCategories -> {
            adapter.setToyCategories(toyCategories);
        });
    }
    private void setRecyclerView() {
        adapter = new CategoriesAdapter(this, null, R.layout.category_single_layout);
        rvCategories.setLayoutManager(new LinearLayoutManager(this));
        rvCategories.setAdapter(adapter);
    }
}