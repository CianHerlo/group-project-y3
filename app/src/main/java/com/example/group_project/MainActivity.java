package com.example.group_project;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.group_project.databinding.ActivityMainBinding;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;
    private TextView emailNavText;
    FirebaseAuth fireAuth;
    FirebaseFirestore db;
    int userExists = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        db = FirebaseFirestore.getInstance();
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarMain.toolbar);

        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_about, R.id.nav_portfolio, R.id.nav_stocks, R.id.nav_crypto, R.id.nav_contact, R.id.nav_support)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);


        FirebaseUser logged_user = FirebaseAuth.getInstance().getCurrentUser();
        assert logged_user != null;
        String logged_email = logged_user.getEmail();

        View headerView = navigationView.getHeaderView(0);
        emailNavText = headerView.findViewById(R.id.emailNavText);
        emailNavText.setText(logged_email);


        CollectionReference usersRef = db.collection("customer_wallets");
        Query query = usersRef.whereEqualTo("Email", logged_email);
        query.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                for (DocumentSnapshot documentSnapshot : task.getResult()) {
                    String email = documentSnapshot.getString("Email");

                    assert email != null;
                    if (email.equals(logged_email)) {
                        Log.d(TAG, "Welcome Back");
                        userExists = 1;
                        break;
                    }
                }

                if (userExists != 1) {
                    Log.d(TAG, "New User");

                    // Create a new user
                    Map<String, Object> user = new HashMap<>();
                    user.put("Email", logged_email);
                    user.put("Wallet", 0);
                    user.put("Total", 0);
                    user.put("Adobe", 0);
                    user.put("Amazon", 0);
                    user.put("Apple", 0);
                    user.put("Google", 0);
                    user.put("Microsoft", 0);
                    user.put("Bitcoin", 0);
                    user.put("Ethereum", 0);

                    // Add a new document with a generated ID
                    db.collection("customer_wallets")
                            .add(user)
                            .addOnSuccessListener(documentReference -> Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId()))
                            .addOnFailureListener(e -> Log.w(TAG, "Error adding document", e));
                }
            }
        });


//        addFundsBTN.setOnClickListener(view1 -> {
//            Intent intent = new Intent(view1.getContext(), Payment.class);
//            view1.getContext().startActivity(intent);
//        });

        // Logout in Navigation Menu
        navigationView.getMenu().findItem(R.id.nav_logout).setOnMenuItemClickListener(menuItem -> {
           logout();
           return true;
        });

    }

    // Logs User out of Application and sends them to Login Page
    private void logout() {
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(getApplicationContext(), Login.class));
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}