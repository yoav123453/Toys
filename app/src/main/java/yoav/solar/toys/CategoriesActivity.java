package yoav.solar.toys;



import static android.view.View.GONE;
import static android.view.View.VISIBLE;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import yoav.solar.model.Category;
import yoav.solar.viewmodel.ToyCategoryViewModel;

public class CategoriesActivity extends AppCompatActivity {
    private RecyclerView rvCategories;
    private CategoriesAdapter adapter;
    private ToyCategoryViewModel toyCategoriesViewModel;
    private FloatingActionButton AddCategory;
    private ConstraintLayout constraintLayout;
    private EditText etCategory;
    private ImageView ivCheckMark;
    private ImageView ivX;
    private Category oldCategory;
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
        AddCategory = findViewById(R.id.fabAddCategory);
        constraintLayout = findViewById(R.id.constraintLayout);
        etCategory = findViewById(R.id.etAdd);
        ivCheckMark= findViewById(R.id.ivCheckMark);
        ivX= findViewById(R.id.ivX);
        constraintLayout.setVisibility(GONE);
        setOnListeners();
    }
    private void setOnListeners() {
        AddCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                constraintLayout.setVisibility(VISIBLE);
            }
        });
        ivCheckMark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                String categoryName = etCategory.getText().toString().trim();
                if (validate(categoryName)) {
                    if (oldCategory != null) {
                        toyCategoriesViewModel.update(oldCategory, new Category(categoryName));
                        oldCategory = null;
                    } else {
                        toyCategoriesViewModel.add(new Category(categoryName));
                    }
                    etCategory.setText("");
                } else {
                    etCategory.setError("Invalid category");
                }
            }
        });
        ivX.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                constraintLayout.setVisibility(View.GONE);
                oldCategory = null;
                etCategory.setText("");
            }
        });
    }
    private boolean validate(String category) {
        return category.length() >= 2;
    }
    private void setupViewModel() {
        toyCategoriesViewModel = new ViewModelProvider(this).get(ToyCategoryViewModel.class);
        toyCategoriesViewModel.getToyCategoriesLiveData().observe(this, toyCategories -> {
            adapter.setToyCategories(toyCategories);
        });
    }
    private void setRecyclerView() {
        CategoriesAdapter.OnItemClickListener listener = category -> {
            oldCategory = category;
            etCategory.setText(category.getName());
            constraintLayout.setVisibility(View.VISIBLE);
        };

        CategoriesAdapter.OnItemLongClickListener longListener = category -> {
            new AlertDialog.Builder(this)
                    .setTitle("Delete Category")
                    .setMessage("Are you sure you want to delete this category?")
                    .setPositiveButton("Yes", (dialog, which) -> {
                        toyCategoriesViewModel.delete(category);
                        Toast.makeText(this, "Category deleted", Toast.LENGTH_SHORT).show();
                    })
                    .setNegativeButton("No", null)
                    .show();
            return true;
        };
        adapter = new CategoriesAdapter(this, null, R.layout.category_single_layout,listener, longListener);
        rvCategories.setLayoutManager(new LinearLayoutManager(this));
        rvCategories.setAdapter(adapter);
    }
}