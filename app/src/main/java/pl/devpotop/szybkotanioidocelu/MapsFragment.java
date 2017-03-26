package pl.devpotop.szybkotanioidocelu;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.osmdroid.api.IMapController;
import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;


public class MapsFragment extends Fragment {
    private OnAddressPicked mListener;

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
    }

    public interface OnAddressPicked {
        void getAddress();
    }

}
