package com.example.baptcv2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class SignUp2nd extends AppCompatActivity {

    //Variables
    Button next, login;
    TextView titleText, slideText;
    LinearLayout clinearprovince, clinearmunicipality, clinearbarangay;

    String _province, _municipality, _barangay, _cprovince, _cmunicipality, _cbarangay, _current, _origin, _currentp;

    Spinner province, municipality, barangay, cprovince, cmunicipality, cbarangay;

    //Array adapter for parent(province)
    ArrayList<String> arrayList_parent;
    ArrayAdapter<String> arrayAdapter_parent;

    //Array adapter for child(municipality)
    ArrayList<String> arrayList_abra, arrayList_apayao, arrayList_benguet,arrayList_ifugao;
    ArrayAdapter<String> arrayAdapter_child;

    //Array adapter for subchild(barangay)
    ArrayList<String> arrayList_Bangued,arrayList_Boliney,arrayList_Bucay,arrayList_Bucloc,arrayList_Daguioman; //Abra
    ArrayList<String> arrayList_Calanasan,arrayList_Conner,arrayList_Flora,arrayList_Kabugao,arrayList_Luna; //Apayao
    ArrayList<String> arrayList_Atok,arrayList_Bakun,arrayList_Bokod,arrayList_Buguias,arrayList_Itogon; //Benguet
    ArrayList<String> arrayList_Aguinaldo,arrayList_AlfonsoLista,arrayList_Asipulo,arrayList_Banaue,arrayList_Hingyon; // Ifugao
    ArrayAdapter<String> arrayAdapter_subchild;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_sign_up2nd);

        province = findViewById(R.id.signup_province);
        municipality = findViewById(R.id.signup_municipality);
        barangay = findViewById(R.id.signup_barangay);
        cprovince = findViewById(R.id.current_province);
        cmunicipality = findViewById(R.id.current_municipality);
        cbarangay = findViewById(R.id.current_barangay);
        clinearprovince = findViewById(R.id.clinearprovince);
        clinearmunicipality = findViewById(R.id.clinearmunicipality);
        clinearbarangay = findViewById(R.id.clinearbarangay);

        //Hooks for animation
        next = findViewById(R.id.signup_next_button);
        login = findViewById(R.id.signup_login_button);
        titleText = findViewById(R.id.signup_title_text);
        slideText = findViewById(R.id.signup_slide_text);

        //Array parent option
        arrayList_parent=new ArrayList<>();
        arrayList_parent.add("Abra");
        arrayList_parent.add("Apayao");
        arrayList_parent.add("Benguet");
        arrayList_parent.add("Ifugao");

        arrayAdapter_parent=new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_spinner_item,arrayList_parent);
        province.setAdapter(arrayAdapter_parent);
        cprovince.setAdapter(arrayAdapter_parent);

        //List of municipality per Provinces
        arrayList_abra=new ArrayList<>();
        arrayList_abra.add("Bangued");
        arrayList_abra.add("Boliney");
        arrayList_abra.add("Bucay");
        arrayList_abra.add("Bucloc");
        arrayList_abra.add("Daguioman");

        arrayList_apayao=new ArrayList<>();
        arrayList_apayao.add("Calanasan");
        arrayList_apayao.add("Conner");
        arrayList_apayao.add("Flora");
        arrayList_apayao.add("Kabugao");
        arrayList_apayao.add("Luna");

        arrayList_benguet=new ArrayList<>();
        arrayList_benguet.add("Atok");
        arrayList_benguet.add("Bakun");
        arrayList_benguet.add("Bokod");
        arrayList_benguet.add("Buguias");
        arrayList_benguet.add("Itogon");

        arrayList_ifugao=new ArrayList<>();
        arrayList_ifugao.add("Aguinalod");
        arrayList_ifugao.add("Alofonso Lista");
        arrayList_ifugao.add("Asipulo");
        arrayList_ifugao.add("Banaue");
        arrayList_ifugao.add("Hingyon");

        //List of Barangays per Municipality
        //Abra Barangay list
        arrayList_Bangued=new ArrayList<>();
        arrayList_Bangued.add("Agtangao");arrayList_Bangued.add("Angad");arrayList_Bangued.add("Bañacao");arrayList_Bangued.add("Bangbangar");arrayList_Bangued.add("Cabuloan");

        arrayList_Boliney=new ArrayList<>();
        arrayList_Boliney.add("Amti");arrayList_Boliney.add("Bao-yan");arrayList_Boliney.add("Danac East");arrayList_Boliney.add("Dao-angan");arrayList_Boliney.add("Dumugas");

        arrayList_Bucay=new ArrayList<>();
        arrayList_Bucay.add("Abang");arrayList_Bucay.add("Bangbangcag");arrayList_Bucay.add("Banglolao");arrayList_Bucay.add("Bugbog");arrayList_Bucay.add("Calao");

        arrayList_Bucloc=new ArrayList<>();
        arrayList_Bucloc.add("Ducligan");arrayList_Bucloc.add("Labaan");arrayList_Bucloc.add("Lingay");arrayList_Bucloc.add("Lamao");

        arrayList_Daguioman=new ArrayList<>();
        arrayList_Daguioman.add("Ableg");arrayList_Daguioman.add("Cabaruyan");arrayList_Daguioman.add("Pikek");arrayList_Daguioman.add("Tui");

        //Apayao Barangay list
        arrayList_Calanasan=new ArrayList<>();
        arrayList_Calanasan.add("Butao");arrayList_Calanasan.add("Cadaclan");arrayList_Calanasan.add("Langnao");arrayList_Calanasan.add("Lubong");arrayList_Calanasan.add("Naguillaian");

        arrayList_Conner=new ArrayList<>();
        arrayList_Conner.add("Allangigan");arrayList_Conner.add("Buluan");arrayList_Conner.add("Caglayan");arrayList_Conner.add("Calafug");arrayList_Conner.add("Cupis");

        arrayList_Flora=new ArrayList<>();
        arrayList_Flora.add("Allig");arrayList_Flora.add("Anninipan");arrayList_Flora.add("Atok");arrayList_Flora.add("Bagutong");arrayList_Flora.add("Balasi");arrayList_Flora.add("Balluyan");

        arrayList_Kabugao=new ArrayList<>();
        arrayList_Kabugao.add("Badduat");arrayList_Kabugao.add("Baliwanan");arrayList_Kabugao.add("Bulu");arrayList_Kabugao.add("Dagara");arrayList_Kabugao.add("Dibagat");

        arrayList_Luna=new ArrayList<>();
        arrayList_Luna.add("Bacsay");arrayList_Luna.add("Cagapaypayan");arrayList_Luna.add("Dagupan");arrayList_Luna.add("Lappa");arrayList_Luna.add("Marag");

        //Benguet Barangay list
        arrayList_Atok=new ArrayList<>();
        arrayList_Atok.add("Abiang");arrayList_Atok.add("Caliking");arrayList_Atok.add("Cattubo");arrayList_Atok.add("Naguey");arrayList_Atok.add("Paoay");

        arrayList_Bakun=new ArrayList<>();
        arrayList_Bakun.add("Ampusongan");arrayList_Bakun.add("Bagu");arrayList_Bakun.add("Dalipey");arrayList_Bakun.add("Gambang");arrayList_Bakun.add("Kayapa");

        arrayList_Bokod=new ArrayList<>();
        arrayList_Bokod.add("Ambuclao");arrayList_Bokod.add("Bila");arrayList_Bokod.add("Bobok-bisal");arrayList_Bokod.add("Daclan");arrayList_Bokod.add("Ekip");

        arrayList_Buguias=new ArrayList<>();
        arrayList_Buguias.add("Abatan");arrayList_Buguias.add("Amgaleyguey");arrayList_Buguias.add("Amlimay");arrayList_Buguias.add("Baculongan");arrayList_Buguias.add("Bangao");

        arrayList_Itogon=new ArrayList<>();
        arrayList_Itogon.add("Ampucao");arrayList_Itogon.add("Dalupirip");arrayList_Itogon.add("Gumatdang");arrayList_Itogon.add("Loacan");arrayList_Itogon.add("Poblacion");

        //Ifugao Barangay list
        arrayList_ifugao=new ArrayList<>();
        arrayList_ifugao.add("Aguinaldo");arrayList_ifugao.add("Alfonso Lista");arrayList_ifugao.add("Asipulo");arrayList_ifugao.add("Banaue");arrayList_ifugao.add("Hingyon");

        //child(municipality) spinner dependent starts here
        province.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(i==0){
                    arrayAdapter_child=new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_spinner_item,arrayList_abra);
                    municipality.setAdapter(arrayAdapter_child);
                    municipality.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            if(i==0){
                                arrayAdapter_subchild=new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_spinner_item,arrayList_Bangued);
                            }
                            if(i==1){
                                arrayAdapter_subchild=new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_spinner_item,arrayList_Boliney);
                            }
                            if(i==2){
                                arrayAdapter_subchild=new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_spinner_item,arrayList_Bucay);
                            }
                            if(i==3){
                                arrayAdapter_subchild=new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_spinner_item,arrayList_Bucloc);
                            }
                            if(i==4){
                                arrayAdapter_subchild=new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_spinner_item,arrayList_Daguioman);
                            }
                            barangay.setAdapter(arrayAdapter_subchild);
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {

                        }
                    });
                }
                if(i==1){
                    arrayAdapter_child=new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_spinner_item,arrayList_apayao);
                    municipality.setAdapter(arrayAdapter_child);
                    municipality.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            if(i==0){
                                arrayAdapter_subchild=new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_spinner_item,arrayList_Calanasan);
                            }
                            if(i==1){
                                arrayAdapter_subchild=new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_spinner_item,arrayList_Conner);
                            }
                            if(i==2){
                                arrayAdapter_subchild=new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_spinner_item,arrayList_Flora);
                            }
                            if(i==3){
                                arrayAdapter_subchild=new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_spinner_item,arrayList_Kabugao);
                            }
                            if(i==4){
                                arrayAdapter_subchild=new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_spinner_item,arrayList_Luna);
                            }
                            barangay.setAdapter(arrayAdapter_subchild);
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {

                        }
                    });
                }
                if(i==2){
                    arrayAdapter_child=new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_spinner_item,arrayList_benguet);
                    municipality.setAdapter(arrayAdapter_child);
                    municipality.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            if(i==0){
                                arrayAdapter_subchild=new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_spinner_item,arrayList_Atok);
                            }
                            if(i==1){
                                arrayAdapter_subchild=new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_spinner_item,arrayList_Bakun);
                            }
                            if(i==2){
                                arrayAdapter_subchild=new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_spinner_item,arrayList_Bokod);
                            }
                            if(i==3){
                                arrayAdapter_subchild=new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_spinner_item,arrayList_Buguias);
                            }
                            if(i==4){
                                arrayAdapter_subchild=new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_spinner_item,arrayList_Itogon);
                            }
                            barangay.setAdapter(arrayAdapter_subchild);
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {

                        }
                    });
                }
                if(i==3){
                    arrayAdapter_child=new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_spinner_item,arrayList_ifugao);
                    municipality.setAdapter(arrayAdapter_child);
                    municipality.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            if(i==0){
                                arrayAdapter_subchild=new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_spinner_item,arrayList_Aguinaldo);
                            }
                            if(i==1){
                                arrayAdapter_subchild=new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_spinner_item,arrayList_AlfonsoLista);
                            }
                            if(i==2){
                                arrayAdapter_subchild=new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_spinner_item,arrayList_Asipulo);
                            }
                            if(i==3){
                                arrayAdapter_subchild=new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_spinner_item,arrayList_Banaue);
                            }
                            if(i==4){
                                arrayAdapter_subchild=new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_spinner_item,arrayList_Hingyon);
                            }
                            barangay.setAdapter(arrayAdapter_subchild);
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {

                        }
                    });
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        cprovince.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(i==0){
                    arrayAdapter_child=new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_spinner_item,arrayList_abra);
                    cmunicipality.setAdapter(arrayAdapter_child);
                    cmunicipality.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            if(i==0){
                                arrayAdapter_subchild=new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_spinner_item,arrayList_Bangued);
                            }
                            if(i==1){
                                arrayAdapter_subchild=new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_spinner_item,arrayList_Boliney);
                            }
                            if(i==2){
                                arrayAdapter_subchild=new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_spinner_item,arrayList_Bucay);
                            }
                            if(i==3){
                                arrayAdapter_subchild=new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_spinner_item,arrayList_Bucloc);
                            }
                            if(i==4){
                                arrayAdapter_subchild=new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_spinner_item,arrayList_Daguioman);
                            }
                            cbarangay.setAdapter(arrayAdapter_subchild);
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {

                        }
                    });
                }
                if(i==1){
                    arrayAdapter_child=new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_spinner_item,arrayList_apayao);
                    cmunicipality.setAdapter(arrayAdapter_child);
                    cmunicipality.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            if(i==0){
                                arrayAdapter_subchild=new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_spinner_item,arrayList_Calanasan);
                            }
                            if(i==1){
                                arrayAdapter_subchild=new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_spinner_item,arrayList_Conner);
                            }
                            if(i==2){
                                arrayAdapter_subchild=new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_spinner_item,arrayList_Flora);
                            }
                            if(i==3){
                                arrayAdapter_subchild=new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_spinner_item,arrayList_Kabugao);
                            }
                            if(i==4){
                                arrayAdapter_subchild=new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_spinner_item,arrayList_Luna);
                            }
                            cbarangay.setAdapter(arrayAdapter_subchild);
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {

                        }
                    });
                }
                if(i==2){
                    arrayAdapter_child=new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_spinner_item,arrayList_benguet);
                    cmunicipality.setAdapter(arrayAdapter_child);
                    cmunicipality.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            if(i==0){
                                arrayAdapter_subchild=new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_spinner_item,arrayList_Atok);
                            }
                            if(i==1){
                                arrayAdapter_subchild=new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_spinner_item,arrayList_Bakun);
                            }
                            if(i==2){
                                arrayAdapter_subchild=new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_spinner_item,arrayList_Bokod);
                            }
                            if(i==3){
                                arrayAdapter_subchild=new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_spinner_item,arrayList_Buguias);
                            }
                            if(i==4){
                                arrayAdapter_subchild=new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_spinner_item,arrayList_Itogon);
                            }
                            cbarangay.setAdapter(arrayAdapter_subchild);
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {

                        }
                    });
                }
                if(i==3){
                    arrayAdapter_child=new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_spinner_item,arrayList_ifugao);
                    cmunicipality.setAdapter(arrayAdapter_child);
                    cmunicipality.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            if(i==0){
                                arrayAdapter_subchild=new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_spinner_item,arrayList_Aguinaldo);
                            }
                            if(i==1){
                                arrayAdapter_subchild=new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_spinner_item,arrayList_AlfonsoLista);
                            }
                            if(i==2){
                                arrayAdapter_subchild=new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_spinner_item,arrayList_Asipulo);
                            }
                            if(i==3){
                                arrayAdapter_subchild=new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_spinner_item,arrayList_Banaue);
                            }
                            if(i==4){
                                arrayAdapter_subchild=new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_spinner_item,arrayList_Hingyon);
                            }
                            cbarangay.setAdapter(arrayAdapter_subchild);
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {

                        }
                    });
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }


    public void callNextSignupScreen(View view) {
        String _fullname = getIntent().getStringExtra("fullname");
        String _password = getIntent().getStringExtra("password");

        Intent intent = new Intent(getApplicationContext(), SignUp3rd.class);
        _province = province.getSelectedItem().toString().trim();
        _municipality = municipality.getSelectedItem().toString().trim();
        _barangay = barangay.getSelectedItem().toString().trim();
        _cprovince = cprovince.getSelectedItem().toString().trim();
        _cmunicipality = cmunicipality.getSelectedItem().toString().trim();
        _cbarangay = cbarangay.getSelectedItem().toString().trim();
        _origin = (_barangay + " "+ _municipality + " " + _province);
        _current = (_cbarangay + " "+ _cmunicipality + " " + _cprovince);
        _currentp = _cprovince;

        //Pass all fields to the next activity
        intent.putExtra("fullname", _fullname);
        intent.putExtra("password", _password);
        intent.putExtra("origin", _origin);
        intent.putExtra("current", _current);
        intent.putExtra("cprovince", _currentp);

        //Add Shared Animation
        Pair[] pairs = new Pair[4];
        pairs[0] = new Pair<View, String>(next, "transition_next_btn");
        pairs[1] = new Pair<View, String>(login, "transition_login_btn");
        pairs[2] = new Pair<View, String>(titleText, "transition_title_text");
        pairs[3] = new Pair<View, String>(slideText, "transition_slide_text");

        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(SignUp2nd.this, pairs);
        startActivity(intent, options.toBundle());

    }

    public void callLoginFromSignUp(View view) {
        startActivity(new Intent(getApplicationContext(), Login.class));
        finish();
    }

}