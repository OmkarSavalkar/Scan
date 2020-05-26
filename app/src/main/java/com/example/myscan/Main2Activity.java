package com.example.myscan;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;



public class Main2Activity extends AppCompatActivity{

    public Button logbt;
    public Button regbt;
    public TextView skipbt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        /*logbt=findViewById(R.id.loginbt);
        regbt=findViewById(R.id.registerbt);*/
        skipbt=findViewById(R.id.skipbut);
    }

   /* @Override
    public void onClick(View view) {
        Intent i=new Intent(Main2Activity.this,loginscreen.class);
        startActivity(i);
        finish();
    }

    public void registerclick(View view)
    {
        Intent i=new Intent(Main2Activity.this,registerscreen.class);
        startActivity(i);
        finish();
    }*/

    public void skipclick(View view)
    {
        Intent i=new Intent(Main2Activity.this,Dashboard.class);
        startActivity(i);
        finish();
    }

    public void onBackPressed()
    {
        AlertDialog.Builder alertdialogbuilder=new AlertDialog.Builder(this);
        alertdialogbuilder.setTitle("Confirm Exit...");
        alertdialogbuilder.setIcon(R.drawable.ic_exit);
        alertdialogbuilder.setMessage("Do You really Want To Exit???");
        alertdialogbuilder.setCancelable(false);
        alertdialogbuilder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });
        alertdialogbuilder.setNegativeButton("no", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                // Toast.makeText(Main2Activity.this, "You clicked cancel!", Toast.LENGTH_SHORT).show();
            }
        });
        AlertDialog alertDialog=alertdialogbuilder.create();
        alertDialog.show();
    }



}
