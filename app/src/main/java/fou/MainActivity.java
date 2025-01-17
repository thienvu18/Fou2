package fou;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import ch.ethz.inf.vs.a1.rubfisch.p2pchat.R;

import static android.Manifest.permission.ACCESS_COARSE_LOCATION;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        checkForPermissions();

        View bv = findViewById(R.id.mainActivity);
        bv.setBackgroundColor(getResources().getColor(R.color.colorLightGrey));

        EditText editText = (EditText) findViewById(R.id.editText);
        final int greyColor =  getResources().getColor(R.color.colorGrey);
        editText.setBackgroundColor(greyColor);

        final Button button = (Button) findViewById(R.id.joinButton);
        final int orangeColor =  getResources().getColor(R.color.colorOrange);
        button.setBackgroundColor(greyColor);
        WifiManager wifi = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        wifi.setWifiEnabled(true);
        editText.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if(s.toString().trim().length()==0){
                    button.setEnabled(false);
                    button.setBackgroundColor(greyColor);
                } else {
                    button.setEnabled(true);
                    button.setBackgroundColor(orangeColor);
                }


            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
                // TODO Auto-generated method stub

            }

            @Override
            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub

            }
        });
    }

    public void joinPressed(View view) {
        Intent intent = new Intent(MainActivity.this, BroadcastReceiverActivity.class);
        EditText editText = (EditText) findViewById(R.id.editText);
        String message = editText.getText().toString();
        intent.putExtra("nameText", message);
        startActivity(intent);

    }

    private void checkForPermissions() {
        if (checkSelfPermission(ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{ACCESS_COARSE_LOCATION}, 1);
        }
    }
}
