package pl.pollub.lab2;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import pl.pollub.lab2.databinding.ActivityPhoneBinding;

public class PhoneActivity extends AppCompatActivity {
    public static final String MANUFACTURER_KEY = "MANUFACTURER_KEY";
    public static final String MODEL_KEY = "MODEL_KEY";
    public static final String ANDROID_VERSION_KEY = "ANDROID_VERSION_KEY";
    public static final String WEB_SITE_KEY = "WEB_SITE_KEY";
    public static final String ID_KEY = "ID_KEY";
    private  long phoneID;

    private ActivityPhoneBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
       this.binding = ActivityPhoneBinding.inflate(this.getLayoutInflater());
       setContentView(this.binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main_tl), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        this.binding.saveBtn.setOnClickListener(view -> savePhone());

        Intent initent = this.getIntent();
                if(initent != null){
                    Bundle bundle = initent.getExtras();
                    if(bundle != null){
                        this.phoneID = bundle.getLong(ID_KEY,0);
                        final String manufacturer = bundle.getString(MANUFACTURER_KEY);
                        final String model = bundle.getString(MODEL_KEY);
                        final String and_v = bundle.getString(ANDROID_VERSION_KEY);
                        final String webSite = bundle.getString(WEB_SITE_KEY);
                        this.binding.manufacturerEt.setText(manufacturer);
                        this.binding.modelEt.setText(model);
                        this.binding.WebsiteEt.setText(webSite);
                        this.binding.androidVersionEt.setText(and_v);






                    }
                }


                this.binding.cancelBtn.setOnClickListener( view -> cencelPnone());
                
                this.binding.webSiteBtn.setOnClickListener( view -> goToSite());
    }
    private boolean isDataValid() {
        boolean isValid = true;


        if (binding.WebsiteEt.getText().toString().trim().isEmpty()) {
            binding.WebsiteEt.setError("Pole WWW jest wymagane!");
            isValid = false;
        }


        if (binding.androidVersionEt.getText().toString().trim().isEmpty()) {
            binding.androidVersionEt.setError("Wersja Android jest wymagana!");
            isValid = false;
        }


        if (binding.modelEt.getText().toString().trim().isEmpty()) {
            binding.modelEt.setError("Model jest wymagany!");
            isValid = false;
        }


        if (binding.manufacturerEt.getText().toString().trim().isEmpty()) {
            binding.manufacturerEt.setError("Producent jest wymagany!");
            isValid = false;
        }

        return isValid;
    }
    private void goToSite() {
        if (!isDataValid()) {
            return;
        }
       String URL = binding.WebsiteEt.getText().toString().trim();

       if(!URL.isEmpty()){
           if(!URL.startsWith("http://") && !URL.startsWith("https://" )){
               URL = "http://" + URL;
           }

           Intent browser = new Intent(Intent.ACTION_VIEW, Uri.parse(URL));
           startActivity(browser);

       }
    }

    private void  cencelPnone() {
        Intent returnIntent = new Intent();
        setResult(RESULT_CANCELED, returnIntent);
        finish();
    }

    private void savePhone() {
        if (!isDataValid()) {
            return;
        }
        Bundle savePhoneBundle = new Bundle();
        savePhoneBundle.putLong(ID_KEY, this.phoneID);
        savePhoneBundle.putString(MANUFACTURER_KEY, binding.manufacturerEt.getText().toString());
        savePhoneBundle.putString(MODEL_KEY, binding.modelEt.getText().toString());
        savePhoneBundle.putString(ANDROID_VERSION_KEY, binding.androidVersionEt.getText().toString());
        savePhoneBundle.putString(WEB_SITE_KEY, binding.WebsiteEt.getText().toString());
        Intent resultIntent = new Intent();
        resultIntent.putExtras(savePhoneBundle);
        this.setResult(RESULT_OK, resultIntent);
        this.finish();

    }
}