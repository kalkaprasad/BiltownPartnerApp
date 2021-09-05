package com.cscodetech.partner.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.cscodetech.partner.R;
import com.cscodetech.partner.fragment.CompleteFragment;
import com.cscodetech.partner.fragment.EarningFragment;
import com.cscodetech.partner.fragment.HomeFragment;
import com.cscodetech.partner.fragment.OngoingFragment;
import com.cscodetech.partner.fragment.ProfileFragment;
import com.cscodetech.partner.utils.SessionManager;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    SessionManager sessionManager;
    BottomNavigationView navigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        setContentView(R.layout.activity_home);
        sessionManager = new SessionManager(HomeActivity.this);
        navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(this);
        navigation.setSelectedItemId(R.id.page_1);

    }

    private boolean loadFragment(Fragment fragment) {
        //switching fragment
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .commit();
            return true;
        }
        return false;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;

        switch (item.getItemId()) {
            case R.id.page_1:
                item.setChecked(true);
                loadFragment(new HomeFragment());
                break;
            case R.id.page_2:
                item.setChecked(true);
                loadFragment(new OngoingFragment());
                break;
            case R.id.page_3:
                item.setChecked(true);
                loadFragment(new EarningFragment());
                break;
            case R.id.page_4:
                item.setChecked(true);
                loadFragment(new ProfileFragment());
                break;
            case R.id.page_5:
                item.setChecked(true);
                loadFragment(new CompleteFragment());
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + item.getItemId());
        }

        return loadFragment(fragment);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.top_option, menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.logout) {

            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(HomeActivity.this);
            alertDialogBuilder.setMessage(getResources().getString(R.string.logoutmessege));
            alertDialogBuilder.setPositiveButton("yes",
                    (arg0, arg1) -> {
                        sessionManager.logoutUser();
                        startActivity(new Intent(HomeActivity.this, LoginActivity.class)
                                .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));
                    });

            alertDialogBuilder.setNegativeButton("No", (dialog, which) -> dialog.cancel());
            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();

        }
        return true;
    }
}