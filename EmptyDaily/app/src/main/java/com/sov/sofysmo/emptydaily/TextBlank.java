package com.sov.sofysmo.emptydaily;

import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.JsonWriter;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


import net.rdrei.android.dirchooser.DirectoryChooserConfig;
import net.rdrei.android.dirchooser.DirectoryChooserFragment;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TextBlank extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_blank);
        Calendar calendar=Calendar.getInstance();
        SimpleDateFormat sdf=new SimpleDateFormat("dd MMM yyyy");
        TextView textDate=(TextView)findViewById(R.id.textDate);
        textDate.setText(sdf.format(calendar.getTime()));


        findViewById(R.id.btnSave)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        saveArtikl("ttt");
                        // mDialog.show(getFragmentManager(), null);
                    }
                });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

        getMenuInflater().inflate(R.menu.menu_text_blank, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    // Метод для сохранения файла
    private void saveArtikl(String fileName) {
        try {
            OutputStream outputStream = openFileOutput(fileName, 0);
            JsonWriter writer = new JsonWriter(new OutputStreamWriter(outputStream, "UTF-8"));
            writer.setIndent("  ");
            writer.beginObject();
            writer.name("date").value(((TextView) findViewById(R.id.textDate)).getText().toString());
            writer.name("content").value(((TextView) findViewById(R.id.textContent)).getText().toString());
            writer.endObject();
            writer.close();
            Toast.makeText(getApplicationContext(), "Saved", Toast.LENGTH_SHORT).show();

        } catch (Throwable t) {
            Toast.makeText(getApplicationContext(),
                    "Exception: " + t.toString(), Toast.LENGTH_LONG).show();
        }
    }
}
