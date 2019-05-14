package com.example.studio1bgroup11.edu2;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.view.View;


import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.Arrays;

public class HomeActivity extends AppCompatActivity {

    private TextView mTextMessage;
    private ListView search_users_list;
    private Button signoutBtn;

    private FirebaseAuth mAuth;
    ArrayAdapter<String> adapter;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:

                    return true;
                case R.id.navigation_dashboard:
                    Intent intent2 = new Intent(HomeActivity.this, HistoryActivity.class);
                    startActivity(intent2);
                    return true;
                case R.id.navigation_notifications:
                    Intent intent3 = new Intent(HomeActivity.this, ProfileActivity.class);
                    startActivity(intent3);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        mAuth = FirebaseAuth.getInstance();

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        search_users_list = (ListView) findViewById(R.id.search_users);

        ArrayList<String> arrayUsers = new ArrayList<>();
        arrayUsers.addAll(Arrays.asList(getResources().getStringArray(R.array.users)));

        adapter = new ArrayAdapter<String>(

                HomeActivity.this,
                android.R.layout.simple_list_item_1,
                arrayUsers

        );

        search_users_list.setAdapter(adapter);
        search_users_list.setVisibility(View.INVISIBLE);
        searchUserBoies();

        Button bookingButton = findViewById(R.id.bookingButton);
        bookingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), BookingActivity.class);
                startActivity(intent);
            }
        });

        Button signoutBtn = findViewById(R.id.signoutBtn);

        search_users_list.setBackgroundColor(Color.WHITE);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search_menu,menu);
        MenuItem item = menu.findItem(R.id.search_users);
        SearchView searchView = (SearchView) menu.findItem(R.id.search_users).getActionView();


        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                search_users_list.setVisibility(View.INVISIBLE);
                return false;
            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                search_users_list.setVisibility(View.VISIBLE);

                adapter.getFilter().filter(newText);
                return false;
            }
        });


        return super.onCreateOptionsMenu(menu);

    }


    public void searchUserBoies(){



    }

    private void signOut() {
        Intent intent = new Intent(HomeActivity.this, LoginMainActivity.class);
        FirebaseAuth.getInstance().signOut();
        startActivity(intent);
    }

    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.signoutBtn) {
            signOut();
        }
    }


}