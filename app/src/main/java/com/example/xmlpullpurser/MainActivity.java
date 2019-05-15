package com.example.xmlpullpurser;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.IOException;
import java.util.List;
public class MainActivity extends AppCompatActivity {
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
      // we are calling our listview from mainactivity xml
        listView = (ListView) findViewById(R.id.list);

        List<Student> students = null;
        try {
            XMLPullParserHandler parser = new XMLPullParserHandler();
            //accessing and opening the content in it by opening
            students = parser.parse(getAssets().open("student.xml"));
            ArrayAdapter<Student> adapter =
                    new ArrayAdapter<Student>(this,R.layout.list_item, students);
            listView.setAdapter(adapter);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.webview:
                startActivity(new Intent(this, Webview.class));
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
