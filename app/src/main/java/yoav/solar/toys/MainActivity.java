package yoav.solar.toys;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import yoav.solar.model.Toy;
import yoav.solar.model.Toys;
import yoav.solar.viewmodel.ToyViewModel;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rvToys;
    private ToyAdapter adapter;
    private ToyViewModel toyViewModel;
    private FloatingActionButton fabAddToy;
    private ActivityResultLauncher<Intent> toyActivityLauncher;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initializeViews();
        setupViewModel();
        setRecyclerView();
        setupFloatingButton();
    }
    private void initializeViews() {
        rvToys = findViewById(R.id.rvToys);
        fabAddToy = findViewById(R.id.fabAddToy);
    }
    private void setupViewModel() {
        toyViewModel = new ViewModelProvider(this).get(ToyViewModel.class);
        toyViewModel.getToysLiveData().observe(this, toys -> {
            adapter.setToys(toys);
        });
    }
    private void setRecyclerView() {
        ToyAdapter.OnItemClickListener listener = new ToyAdapter.OnItemClickListener() {
            @Override
            public void onItemClicked(Toy toy) {
                Toast.makeText(MainActivity.this, "Name: " + toy.getName(), Toast.LENGTH_LONG).show();
            }
        };
        ToyAdapter.OnItemLongClickListener longListener = new ToyAdapter.OnItemLongClickListener() {
            @Override
            public boolean OnItemLongClicked(Toy toy) {
                Toast.makeText(MainActivity.this, "Price: " + String.valueOf(toy.getPrice()), Toast.LENGTH_LONG).show();
                return true;
            }
        };
        adapter = new ToyAdapter(this, null, R.layout.toy_single_layout, listener, longListener);
        rvToys.setAdapter(adapter);
        rvToys.setLayoutManager(new LinearLayoutManager(this));
    }
    private void setupFloatingButton() {
        toyActivityLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>()
                {
                    @Override
                    public void onActivityResult(ActivityResult o) {
                        if (o.getResultCode() == RESULT_OK) {
                            Intent data = o.getData();
                        }
                    }
                });
        fabAddToy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ToyActivity.class);
                toyActivityLauncher.launch(intent);
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_categories) {
            Intent intent = new Intent(this, CategoriesActivity.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.action_exit) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}