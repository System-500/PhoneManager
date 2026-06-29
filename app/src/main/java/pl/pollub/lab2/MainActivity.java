package pl.pollub.lab2;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import pl.pollub.lab2.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private PhoneViewModel phoneViewModel;
    private PhoneListAdapter adapter;
    private ActivityResultLauncher<Intent> launcher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        this.binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(this.binding.getRoot());


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        this.adapter = new PhoneListAdapter(this, this::editPhone);
        this.binding.phoneListRv.setAdapter(this.adapter);
        this.binding.phoneListRv.setLayoutManager(new LinearLayoutManager(this));


        this.phoneViewModel = new ViewModelProvider(this).get(PhoneViewModel.class);
        this.phoneViewModel.getPhoneList().observe(this, phones -> this.adapter.setPhoneList(phones));


        this.launcher = this.registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                this::upsetPhone
        );

        this.binding.addPhoneFab.setOnClickListener(view -> add_phone());

        setupSwipeToDelete();
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_delete_all) {
            this.phoneViewModel.deleteAllPhones();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void upsetPhone(ActivityResult result) {
        if (result.getResultCode() == RESULT_OK && result.getData() != null) {
            Bundle extras = result.getData().getExtras();
            if (extras != null) {
                long phoneID = extras.getLong(PhoneActivity.ID_KEY, 0);
                String manufacturer = extras.getString(PhoneActivity.MANUFACTURER_KEY);
                String model = extras.getString(PhoneActivity.MODEL_KEY);
                String androidVersion = extras.getString(PhoneActivity.ANDROID_VERSION_KEY);
                String webSite = extras.getString(PhoneActivity.WEB_SITE_KEY);

                Phone phone = new Phone(manufacturer, model, androidVersion, webSite);

                if (phoneID != 0) {
                    phone.setId(phoneID);
                    this.phoneViewModel.updatePhone(phone);
                } else {
                    this.phoneViewModel.insertPhone(phone);
                }
            }
        }
    }

    private void add_phone() {
        Intent addNewPhoneIntent = new Intent(this, PhoneActivity.class);
        this.launcher.launch(addNewPhoneIntent);
    }

    private void editPhone(Phone phone) {
        Bundle editBundle = new Bundle();
        editBundle.putLong(PhoneActivity.ID_KEY, phone.getId());
        editBundle.putString(PhoneActivity.MANUFACTURER_KEY, phone.getManufacturer());
        editBundle.putString(PhoneActivity.MODEL_KEY, phone.getModel());
        editBundle.putString(PhoneActivity.ANDROID_VERSION_KEY, phone.getAndroidVersion());
        editBundle.putString(PhoneActivity.WEB_SITE_KEY, phone.getWebSite());

        Intent editIntent = new Intent(this, PhoneActivity.class);
        editIntent.putExtras(editBundle);
        this.launcher.launch(editIntent);
    }

    private void setupSwipeToDelete() {
        ItemTouchHelper.SimpleCallback swipeCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAbsoluteAdapterPosition();
                Phone phoneToDelete = adapter.getPhoneAtPosition(position);
                phoneViewModel.deletePhone(phoneToDelete);
            }
        };
        new ItemTouchHelper(swipeCallback).attachToRecyclerView(this.binding.phoneListRv);
    }
}