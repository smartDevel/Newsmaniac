package eu.ways4.newsmaniac.ui;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import eu.ways4.newsmaniac.R;
import eu.ways4.newsmaniac.databinding.ActivityMainBinding;
import eu.ways4.newsmaniac.remote.ApiUtil;

public class MainActivity extends AppCompatActivity {
    NavController navController;
    ActivityMainBinding binding;
    private static SharedPreferences sharedP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView navigationView = binding.navView;
        navController = Navigation.findNavController(this,R.id.nav_host_fragment);
        NavigationUI.setupWithNavController(navigationView, navController);

        Activity currActivity = this;
        SharedPreferences sharedPref = currActivity.getPreferences(Context.MODE_PRIVATE);
        sharedP = sharedPref;
        setAPI_key();

    }
    private void setAPI_key() {

        if(!(sharedP == null)){
            ApiUtil.API_KEY = sharedP.getString(getString(R.string.saved_apikey),"");
        }
    }
}
