package com.example.myscan;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.myscan.adapter.Myadapter;
import com.example.myscan.database.Dbase;
import com.example.myscan.modelscanstore.Listitem;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.util.ArrayList;

public class scanstore extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<Listitem> arrayList;
    Myadapter myadapter;
    Dbase dbase;
    Button fb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.storescan);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView=findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        dbase=new Dbase(this);

        arrayList=dbase.getinformation();

        if (arrayList.size()>0)
        {
            myadapter=new Myadapter(arrayList,this);
            recyclerView.setAdapter(myadapter);
        }
        else
        {
            Toast.makeText(this, "NO DATA FOUND", Toast.LENGTH_SHORT).show();
        }

        //on swipe left or right
        new ItemTouchHelper(new ItemTouchHelper.Callback() {
            @Override
            public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
                return 0;
            }

            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int pos=viewHolder.getAdapterPosition();
                Listitem listitem=arrayList.get(pos);

                //to remove data

                dbase.deleterow(listitem.getId());
                arrayList.remove(pos);
                myadapter.notifyItemRemoved(pos);
                myadapter.notifyItemRangeChanged(pos,arrayList.size());
            }
        }).attachToRecyclerView(recyclerView);

        final IntentIntegrator intentIntegrator=new IntentIntegrator(this);
        intentIntegrator.setBeepEnabled(true);
        intentIntegrator.setCameraId(0);

        fb=findViewById(R.id.fab);
        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intentIntegrator.initiateScan();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        IntentResult result=IntentIntegrator.parseActivityResult(requestCode,resultCode,data);
        if(result!=null)
        {
            if(result.getContents()==null)
            {
                Toast.makeText(this, "NO RESULT FOUND", Toast.LENGTH_SHORT).show();
            }
            else
            {
                boolean isinserted=dbase.insertdata(result.getFormatName(),result.getContents());

                if (isinserted)
                {
                    arrayList.clear();
                    arrayList=dbase.getinformation();
                    myadapter=new Myadapter(arrayList,this);
                    recyclerView.setAdapter(myadapter);
                    myadapter.notifyDataSetChanged();
                }
            }
        }
        else
        {
            super.onActivityResult(requestCode,resultCode,data);
        }
    }
}
