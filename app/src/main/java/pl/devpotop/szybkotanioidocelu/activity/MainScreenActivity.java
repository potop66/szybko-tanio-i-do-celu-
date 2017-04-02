package pl.devpotop.szybkotanioidocelu.activity;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import pl.devpotop.szybkotanioidocelu.fragments.MainScreenFragment;
import pl.devpotop.szybkotanioidocelu.fragments.MapsFragment;
import pl.devpotop.szybkotanioidocelu.R;
import pl.devpotop.szybkotanioidocelu.fragments.SettingsFragment;

public class MainScreenActivity extends AppCompatActivity implements MainScreenFragment.ButtonsClicked,MapsFragment.OnAddressPicked {
    Context context=this;
    FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);
        fragmentManager=getSupportFragmentManager();

        FragmentTransaction transaction;
        transaction=fragmentManager.beginTransaction();
        MainScreenFragment fragment=new MainScreenFragment();
        transaction.replace(R.id.fragment_frame,fragment);
        transaction.commit();
    }

    @Override
    public void getEnteredDate() {
        //navigate button clicked


    }

    @Override
    public void mapsButtonClicked(int number) {
        //maps button clicked
        FragmentTransaction transaction;
        transaction=fragmentManager.beginTransaction();
        MapsFragment fragment=new MapsFragment();
        transaction.replace(R.id.fragment_frame,fragment);
        transaction.addToBackStack(null);
        transaction.commit();


    }


    @Override
    public void getAddress() {

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_screen, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                FragmentTransaction transaction;
                transaction=fragmentManager.beginTransaction();
              SettingsFragment fragment=new SettingsFragment();
                transaction.replace(R.id.fragment_frame,fragment);
                transaction.addToBackStack(null);
                transaction.commit();




                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
