package rise_mike.finance;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Mike on 23.12.2017.
 */

public class StartUp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.startup);

        new Handler().postDelayed(() -> {
            Intent intent = new Intent(StartUp.this, Content.class);
            startActivity(intent);
            finish();
        }, 2500);
    }
}