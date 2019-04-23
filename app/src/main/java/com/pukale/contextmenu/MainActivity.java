package com.pukale.contextmenu;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button clickbtn;
    View view;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        clickbtn=(Button) findViewById(R.id.clickbtn);
        registerForContextMenu(clickbtn);
        view=this.getWindow().getDecorView();

        sharedPreferences=getSharedPreferences("sharedpref",MODE_PRIVATE);

        if(sharedPreferences.contains("colourid")) {
            view.setBackgroundResource(sharedPreferences.getInt("colourid", 0));
        }
        else{
            view.setBackgroundResource(sharedPreferences.getInt("imageid", 0));
        }
    }

    public void  onCreateContextMenu(ContextMenu menu,View v,ContextMenu.ContextMenuInfo menuInfo){
        super.onCreateContextMenu(menu, v,menuInfo);
        getMenuInflater().inflate(R.menu.menu,menu);

    }

    public  boolean onContextItemSelected(MenuItem item){
        switch (item.getItemId()){

            case R.id.sb:
                int id1=R.color.colorPrimary;
                view.setBackgroundResource(id1);
                setColor(id1);
                Toast.makeText(this, "colour id saved", Toast.LENGTH_SHORT).show();
                return true;


            case R.id.sf:
                int id2=R.drawable.ic_launcher_background;
                view.setBackgroundResource(id2);
                setImage(id2);
                Toast.makeText(this, "Image id saved", Toast.LENGTH_SHORT).show();
                return true;

                default:
                    return super.onContextItemSelected(item);
        }
    }


    void setColor(int color){
        sharedPreferences=getSharedPreferences("sharedpref",MODE_PRIVATE);
        SharedPreferences.Editor editor= sharedPreferences.edit();
        editor.putInt("colourid",color);
        editor.apply();


    }

    void setImage(int image){
        sharedPreferences=getSharedPreferences("sharedpref",MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putInt("imageid",image);
        editor.apply();

    }






}
