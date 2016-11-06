package com.example.adiad.profileanalyser;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;


public class HealthFragment extends Fragment {
    Spinner smokespinner, ftspinner, inchspinner, activespinner, dietspinner, diabeticspinner;
    ArrayAdapter<CharSequence> smokeadapter;
    ArrayAdapter<CharSequence> ftadapter, inchadapter, activeadapter, dietadapter,
            diabeticadapter;
    heartage ha=new heartage();
    EditText mass, highbp, lowbp,cholesterol;
    Button hicalculate,heartage;

    health h=new health();
    int a;
    int q, w=0, y=0;
    float w1 = 0, y1 = 0, z=0;
    private static final String TAG=MainActivity.class.getSimpleName();

    public HealthFragment()
    {

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView=inflater.inflate(R.layout.fragment_health, container, false);

        mass = (EditText) rootView.findViewById(R.id.mass);
        highbp = (EditText) rootView.findViewById(R.id.highbp);
        lowbp = (EditText) rootView.findViewById(R.id.lowbp);

        hicalculate = (Button) rootView.findViewById(R.id.hicalculate);
        cholesterol=(EditText)rootView.findViewById(R.id.cholesterol);
        heartage=(Button)rootView.findViewById(R.id.heartage);
        smokespinner = (Spinner) rootView.findViewById(R.id.smokespinner);
        ftspinner = (Spinner) rootView.findViewById(R.id.ftspinner);
        inchspinner = (Spinner) rootView.findViewById(R.id.inchspinner);
        activespinner = (Spinner) rootView.findViewById(R.id.activespinner);
        dietspinner = (Spinner) rootView.findViewById(R.id.dietspinner);
        diabeticspinner = (Spinner) rootView.findViewById(R.id.diabeticspinner);
        smokeadapter = ArrayAdapter.createFromResource(getContext(), R.array.smoking_category,
                R.layout.support_simple_spinner_dropdown_item);
        smokeadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        smokespinner.setAdapter(smokeadapter);
        ftadapter = ArrayAdapter.createFromResource(getContext(), R.array.ft_category,
                R.layout.support_simple_spinner_dropdown_item);
        ftadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ftspinner.setAdapter(ftadapter);
        inchadapter = ArrayAdapter.createFromResource(getContext(), R.array.inch_category,
                R.layout.support_simple_spinner_dropdown_item);
        inchadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        inchspinner.setAdapter(inchadapter);
        activeadapter = ArrayAdapter.createFromResource(getContext(), R.array.active_category,
                R.layout.support_simple_spinner_dropdown_item);
        activeadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        activespinner.setAdapter(activeadapter);
        dietadapter = ArrayAdapter.createFromResource(getContext(), R.array.diet_category,
                R.layout.support_simple_spinner_dropdown_item);
        dietadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dietspinner.setAdapter(dietadapter);
        diabeticadapter = ArrayAdapter.createFromResource(getContext(), R.array.diabetic_category,
                R.layout.support_simple_spinner_dropdown_item);
        diabeticadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        diabeticspinner.setAdapter(diabeticadapter);


        smokespinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                a = i;
                float b = 0;
                if (a == 0)
                    b = 1;
                else if (a == 1)
                    b = (float) 0.22;
                else if (a == 2)
                    b = (float) 0.33;
                else if (a == 3)
                    b = (float) 0.66;
                else if (a == 4)
                    b = 0;
                h.smoke = b;

            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        ftspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                h.heightft = i + 1;

            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        inchspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {


                h.heightinch = i + 1;

            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        activespinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                float c = 0;
                if (i == 0 || i == 1)
                    c = 1;
                else if (i == 2 || i == 3)
                    c = (float) 0.5;
                h.active = c;

            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        dietspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                float d = 0;
                if (i == 0)
                    d = 1;
                else if (i == 1)
                    d = (float) 0.6;
                else if (i == 2)
                    d = (float) 0.2;
                else if (i == 3)
                    d = 0;
                h.diet = d;

            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        diabeticspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                if (i == 0)
                    h.diabetic = 1;
                else
                    h.diabetic = 0;
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        hicalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    q = Integer.parseInt(mass.getText().toString());
// q=Integer.getInteger(mass.getText().toString());
                }catch (NumberFormatException e){
                }
                h.bmi = h.bmical(q, h.heightft, h.heightinch);
                try {
                    w = Integer.parseInt(highbp.getText().toString());
                }catch (NumberFormatException e){}
                try {
                    y = Integer.parseInt(lowbp.getText().toString());
                }catch (NumberFormatException e){}
                if (w <= 100)
                    w1 = (float) 0.4;
                else if (w > 100 && w <= 120)
                    w1 = 1;
                else if (w > 120 && w <= 140)
                    w1 = (float) 0.4;
                else if (w > 140 && w <= 160)
                    w1 = (float) 0.7;
                else if (w > 160)
                    w1 = 0;
                if (y <= 70)
                    y1 = (float) 0.4;
                else if (y > 70 && y <= 80)
                    y1 = 1;
                else if (y > 80 && y <= 90)
                    y1 = (float) 0.4;
                else if (y > 90 && y <= 100)
                    y1 = (float) 0.7;
                else if (y > 100)
                    y1 = 0;
                z = (w1 + y1) / 2;
                h.bp =z;

                float hi = h.smoke + h.active + h.diet + h.bmi + h.diabetic + h.bp;
                Toast.makeText(getContext(), "Your health index is " + hi,
                        Toast.LENGTH_LONG).show();

            }
        });
        heartage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
        return rootView;
    }


    }



