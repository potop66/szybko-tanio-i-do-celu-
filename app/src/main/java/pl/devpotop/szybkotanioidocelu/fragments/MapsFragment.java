package pl.devpotop.szybkotanioidocelu.fragments;

import android.app.Activity;
import android.content.Context;
import android.location.Address;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import org.osmdroid.api.IMapController;
import org.osmdroid.bonuspack.location.GeocoderNominatim;
import org.osmdroid.config.Configuration;
import org.osmdroid.events.MapEventsReceiver;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.MapEventsOverlay;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import pl.devpotop.szybkotanioidocelu.GPStracker;
import pl.devpotop.szybkotanioidocelu.R;


public class MapsFragment extends Fragment implements MapEventsReceiver {
    public OnAddressPicked mListener;
    List<Address> addressList=new ArrayList<>();

    Context context;




    public MapsFragment() {
    }
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        context=getActivity();
        try {
            mListener = (OnAddressPicked) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnMessageLinksClickListener");
        }

    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.maps_fragment, container, false);
        Configuration.getInstance().load(context, PreferenceManager.getDefaultSharedPreferences(context));
        MapView map=(MapView) view.findViewById(R.id.map);
        mapsSettings(map);




        return  view;

    }
    public void mapsSettings(MapView map){
        map.setBuiltInZoomControls(true);
        map.setMultiTouchControls(true);
        map.setTileSource(TileSourceFactory.MAPNIK);
        IMapController mapController = map.getController();
        mapController.setZoom(11);
        GPStracker gpStracker=new GPStracker(context);
        Location location=gpStracker.getLocation();
        GeoPoint startPoint = new GeoPoint(location.getLatitude(), location.getLongitude());
        mapController.setCenter(startPoint);
        MapEventsOverlay mapEventsOverlay = new MapEventsOverlay(context, this);
        map.getOverlays().add(0, mapEventsOverlay);

    }
    @Override
    public boolean singleTapConfirmedHelper(GeoPoint p) {



        return false;
    }

    @Override
    public boolean longPressHelper(GeoPoint p) {
        new GeoCoder(p).execute();


        return false;
    }




    public interface OnAddressPicked {
        void getAddress();
    }



    public class GeoCoder extends AsyncTask<String,Integer,Integer> {
        GeoPoint geoPoint;


        public GeoCoder(GeoPoint geoPoint){
            this.geoPoint=geoPoint;
        }





        @Override
        protected Integer doInBackground(String... params) {

            GeocoderNominatim geocoderNominatim=new GeocoderNominatim("aw8431@gmail.com");
            geocoderNominatim.setService(geocoderNominatim.NOMINATIM_SERVICE_URL);

            try {
                addressList=geocoderNominatim.getFromLocation(geoPoint.getLatitude(),geoPoint.getLongitude(),1);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Integer i) {
            super.onPostExecute(i);
            Log.d("asdasda",addressList.get(0).getCountryName()+"");
            mListener.getAddress();
        }



    }

}
