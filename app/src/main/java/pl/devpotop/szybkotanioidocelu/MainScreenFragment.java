package pl.devpotop.szybkotanioidocelu;

import android.app.Activity;
import android.content.Context;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;


public class MainScreenFragment extends Fragment {
    private ButtonsClicked mListener;

    Context context;
    EditText fromWhere,destination;


    public MainScreenFragment() {    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        context=getActivity();
        try {
            mListener = (ButtonsClicked) activity;
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
        View view= inflater.inflate(R.layout.main_screen_fragment, container, false);
        fromWhere=(EditText) view.findViewById(R.id.editText);
        destination=(EditText) view.findViewById(R.id.editText2);
        Button map1=(Button) view.findViewById(R.id.button);
        Button map2=(Button) view.findViewById(R.id.button2);
        Button navigate=(Button) view.findViewById(R.id.button3);



        map1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.mapsButtonClicked(1);
            }
        });

        map2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            mListener.mapsButtonClicked(2);
            }
        });

        navigate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.getEnteredDate();
            }
        });



        return  view;

    }
    public interface ButtonsClicked {
        // TODO: Update argument type and name
        void getEnteredDate();
        void mapsButtonClicked(int number);
    }

}
