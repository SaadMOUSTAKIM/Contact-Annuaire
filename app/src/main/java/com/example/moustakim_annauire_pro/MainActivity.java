package com.example.moustakim_annauire_pro;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.Menu;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.view.MenuItem;
import android.widget.SearchView;


import com.example.moustakim_annauire_pro.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    RecyclerView recyclerView;
    List<UserContact> dataList=new ArrayList<>();
    LinearLayoutManager linearLayoutManager;
    RoomDB database;
    MainAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);
        binding.addIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myInent=new Intent(view.getContext(),MainActivity2.class);
                startActivity(myInent);
            }
        });
        recyclerView=findViewById(R.id.recyclerV);


        database=RoomDB.getInstance(this);
//        database.userContactDao().insert(new UserContact("yasin","Kabboura","Etudiant Ingenieur","yasin@gmail.com","0634527865"));
//        database.userContactDao().insert(new UserContact("Saad","Moustakim","Etudiant INgenieur","saad@gmail.com","0638032444"));
//        database.userContactDao().insert(new UserContact("achraf","MOUTTA","Etudiant INgenieur","saad@gmail.com","0643527636"));
//        database.userContactDao().insert(new UserContact("Akram","Kssiba","Etudiant INgenieur","saad@gmail.com","0676382938"));
//        database.userContactDao().insert(new UserContact("Hassna","SAHLE","Etudiant INgenieur","saad@gmail.com","0637489823"));
//        database.userContactDao().insert(new UserContact("Tarik","OFKIR","Etudiant INgenieur","saad@gmail.com","0673879034"));



        dataList=database.userContactDao().getAll();

        linearLayoutManager=new LinearLayoutManager(this);

        recyclerView.setLayoutManager(linearLayoutManager);

        adapter=new MainAdapter(MainActivity.this,dataList);//adapte les donnees a la vue

        recyclerView.setAdapter(adapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        MenuItem search = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) search.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }
            @Override
            public boolean onQueryTextChange(String s) {
                adapter.searching(s);
                return false;
            }
        });
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.


        return super.onOptionsItemSelected(item);
    }


}