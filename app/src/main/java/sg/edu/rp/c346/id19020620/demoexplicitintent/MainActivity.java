package sg.edu.rp.c346.id19020620.demoexplicitintent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView tvSuperman, tvBatman;
    int requestCodeForSupermanStats = 1;
    int requestCodeForBatmanStats = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvSuperman = (TextView) findViewById(R.id.textViewSuperman);
        tvBatman = (TextView) findViewById(R.id.textViewBatman);

        tvSuperman.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Hero superman = new Hero("Superman", 100, 60);
                Intent i = new Intent(MainActivity.this,
                        HeroStatsActivity.class);
                // Put hero object in intent
                i.putExtra("hero", superman);

                startActivityForResult(i, requestCodeForSupermanStats);

            }
        });
        tvBatman.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Hero batman = new Hero("Batman", 60, 90);
                Intent i = new Intent(MainActivity.this,
                        HeroStatsActivity.class);
                // Put hero object in intent
                i.putExtra("hero", batman);
                // Start the activity
                startActivity(i);
                startActivityForResult(i, requestCodeForBatmanStats);

            }
        });


    }
    @Override
    protected void onActivityResult( int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        // Only handle when 2nd activity closed normally
        //  and data contains something
        if (resultCode == RESULT_OK) {
            if (data != null) {
                // Get data passed back from 2nd activity
                String like = data.getStringExtra("like");
                String statement = "";
                // If it is activity started by clicking 				//  Superman, create corresponding String
                if (requestCode == requestCodeForSupermanStats) {
                    statement = "You " + like + " Superman";
                }
                // If 2nd activity started by clicking
                //  Batman, create a corresponding String
                if (requestCode == requestCodeForBatmanStats) {
                    statement = "You " + like + " Batman";
                }

                Toast.makeText(MainActivity.this, statement,
                        Toast.LENGTH_LONG).show();
            }
        }


    }
}