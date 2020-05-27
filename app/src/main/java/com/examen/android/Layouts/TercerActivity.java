package com.examen.android.Layouts;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.examen.android.Models.MyButton;
import com.examen.android.R;
import com.examen.android.StateSQLiteHelper.SQLHelper;

public class TercerActivity extends AppCompatActivity implements MyListener {

    MyButton m;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tercer);

        m = new MyButton(this);
        LinearLayout linearLayoutButton = findViewById(R.id.linearLayoutButton);

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        Button button = new Button(this);
        button.setBackground(getDrawable(R.drawable.border_button));
        button.setTextColor(getColor(R.color.White));
        button.setText(getString(R.string.clic_button));
        button.setLayoutParams(layoutParams);
        linearLayoutButton.addView(button);
        //button.set
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                threads();
            }
        });

        SQLHelper dataProducts = new SQLHelper(this);
        SQLiteDatabase db = dataProducts.getWritableDatabase();
        dataProducts.getTableUsersInformation().AddInformationTableUser(db, 1, "Miguel Cervantes", "08-Dic-1990", "Desarrollador");
        dataProducts.getTableUsersInformation().AddInformationTableUser(db, 2, "Juan Morales", "03-Jul-1990","Desarrollador");
        dataProducts.getTableUsersInformation().AddInformationTableUser(db, 3, "Roberto Méndez", "14-Oct-1990","Desarrollador");
        dataProducts.getTableUsersInformation().AddInformationTableUser(db, 4, "Miguel Cuevas","08-Dic-1990","Desarrollador");

        db.close();

    }


    private void thread() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void threads() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i <= 10; i++) {
                    thread();
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        Toast.makeText(TercerActivity.this, "el tiempo ha concluido", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        }).start();
    }

    @Override
    public void callback( String result) {
        //m.MyLogicToIntimateOthers();
        threads();
    }
}
