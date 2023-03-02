package com.example.conversor;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LongitudItem#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LongitudItem extends Fragment implements AdapterView.OnItemSelectedListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public LongitudItem() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LongitudItem.
     */
    // TODO: Rename and change types and number of parameters
    public static LongitudItem newInstance(String param1, String param2) {
        LongitudItem fragment = new LongitudItem();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_longitud_item, container, false);
    }

    private EditText txt;
    private TextView txt1,txt2,txt3,txt4,txt5;
    private Spinner spn;
    private int selecte = 0;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //Spinner
        spn = (Spinner) getView().findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this.getContext(),R.array.opcionesLongitud, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spn.setAdapter(adapter);
        spn.setOnItemSelectedListener(this);

        txt = (EditText) getView().findViewById(R.id.input);
        txt1 = (TextView) getView().findViewById(R.id.input1);
        txt2 = (TextView) getView().findViewById(R.id.input2);
        txt3 = (TextView) getView().findViewById(R.id.input3);
        txt4 = (TextView) getView().findViewById(R.id.input4);
        txt5 = (TextView) getView().findViewById(R.id.input5);

        txt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable editable) {
                float valor = 0;
                if(txt.getText().toString().length()!=0){
                    valor = Float.parseFloat(txt.getText().toString());
                }

                if(selecte==0){
                    txt1.setText(String.valueOf(valor));
                    txt2.setText(String.valueOf(valor* 1000));
                    txt3.setText(String.valueOf(valor* 100000));
                    txt4.setText(String.valueOf(valor* 1000000));
                    txt5.setText(String.valueOf(valor/1.609));
                }else if(selecte==1){
                    txt1.setText(String.valueOf(valor/ 1000));
                    txt2.setText(String.valueOf(valor));
                    txt3.setText(String.valueOf(valor* 100));
                    txt4.setText(String.valueOf(valor* 1000));
                    txt5.setText(String.valueOf(valor/1609));
                }else if(selecte==2){
                    txt1.setText(String.valueOf(valor/ 100000));
                    txt2.setText(String.valueOf(valor/ 100));
                    txt3.setText(String.valueOf(valor));
                    txt4.setText(String.valueOf(valor* 10));
                    txt5.setText(String.valueOf(valor/160900));
                }else if(selecte==3){
                    txt1.setText(String.valueOf(valor/ 1000000));
                    txt2.setText(String.valueOf(valor/ 1000));
                    txt3.setText(String.valueOf(valor/ 10));
                    txt4.setText(String.valueOf(valor));
                    txt5.setText(String.valueOf(valor/0.000001609));
                }else if(selecte==4){
                    txt1.setText(String.valueOf(valor* 1.609));
                    txt2.setText(String.valueOf(valor* 1609));
                    txt3.setText(String.valueOf(valor* 160900));
                    txt4.setText(String.valueOf(valor*1609000));
                    txt5.setText(String.valueOf(valor));
                }
            }
        });


    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        selecte = i;
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}