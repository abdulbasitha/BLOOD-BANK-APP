package techzia.bloodbank.app;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;

import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.transition.TransitionManager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;

import techzia.bloodbank.R;

public class NeedBlood extends AppCompatActivity {


    //SignInWithGoogle

    TextView email;
    GoogleSignInClient mGoogleSignInClient;


    private static final int REQUEST_CALL = 1;
    LinearLayout first_layout, second_layout, third_layout, fourth_Layout;
    Spinner Spinner1;
    EditText Loc;
    Button Search;
    String Blood, Location;
    //String[] Domain;
    TextView Error,noresult;
    ListView listView ;
    String url;
    String namesub;
    String number;
    TextView User_name,User_Blood,User_Location,User_Phone,Year_Text;
    JSONObject ob;
    JSONArray arr;
    ArrayList<String> name,phno,bloodgp,place_info,st_year;
    ArrayList<String> NameShowList;
    Button Callnow;
    String values[]  = {};
    int Slid;
    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_need_blood);

        first_layout = (LinearLayout) findViewById(R.id.first_need);
        second_layout = (LinearLayout) findViewById(R.id.Loading);
        third_layout = (LinearLayout) findViewById(R.id.List);
        fourth_Layout = (LinearLayout) findViewById(R.id.View);

        first_layout.setVisibility(View.VISIBLE);



        //SignInWithGoogle


        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);



        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
        if (acct != null) {
            //String personName = acct.getDisplayName();
            String personEmail = acct.getEmail();
             //Domain = personEmail.split("@");
            //String personId = acct.getId();
            //Uri personPhoto = acct.getPhotoUrl();


            //email.setText(Domain[1]);
            //id.setText(personId);
            //Glide.with(this).load(String.valueOf(personPhoto)).apply(RequestOptions.circleCropTransform()).into(image);
        }


        //ends SignInWithGoogle

        Slid=1;
        final ViewGroup transitionsContainerfirst = (ViewGroup) findViewById(R.id.first_need);
        TransitionManager.beginDelayedTransition(transitionsContainerfirst);

        Spinner1 = (Spinner) transitionsContainerfirst.findViewById(R.id.Bloodtype);
        Loc = (EditText) transitionsContainerfirst.findViewById(R.id.loca) ;
        Search = (Button) transitionsContainerfirst.findViewById(R.id.search) ;
        Error = (TextView)  transitionsContainerfirst.findViewById(R.id.ErrorBlood);


        String c[] = {"*Blood Group","A+","A-","B+","B-","AB+","AB-","O+","O-"};

        ArrayAdapter<String> a = new ArrayAdapter<String>(this, R.layout.spinner, c);
        Spinner1.setAdapter(a);




        Search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Location = Loc.getText().toString();
                //Location = Loc.getSelectedItem().toString();
                Blood = Spinner1.getSelectedItem().toString();


                listView = (ListView) findViewById(R.id.list);

                if(Blood.equalsIgnoreCase("*Blood Group")){
                    Error.setVisibility(TextView.VISIBLE);
                }else {
                    Error.setVisibility(TextView.GONE);

                    if(Blood=="A+")
                        Blood="A%2B";
                    else if(Blood=="B+")
                        Blood="B%2B";
                    else if(Blood=="AB+")
                        Blood="AB%2B";
                    else if(Blood=="O+")
                        Blood="O%2B";


                    url="fetch.php?gp="+Blood+"&place="+Location+"";
                    Log.i("URL", url);


                    new Loads().execute();
                    second_layout.setVisibility(View.VISIBLE);
                    first_layout.setVisibility(View.GONE);




                    noresult= findViewById(R.id.noresult);

                    NameShowList = new ArrayList<>(Arrays.asList(values));





                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(),
                                android.R.layout.simple_list_item_1, android.R.id.text1, NameShowList);
                        listView.setAdapter(adapter);
                                Slid=2;






                        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                                // ListView Clicked item index
                                final int itemPosition = position;

                                // ListView Clicked item value
                                String itemValue = (String) listView.getItemAtPosition(position);

                                // Show Alert


                                fourth_Layout.setVisibility(View.VISIBLE);
                                third_layout.setVisibility(View.GONE);
                                Slid=3;
                                User_Blood = (TextView) findViewById(R.id.bloodgroup_profile);
                                User_name = (TextView) findViewById(R.id.name_profile);
                                User_Location =(TextView) findViewById(R.id.place_profile);
                                User_Phone =(TextView) findViewById(R.id.phone_profile);
                                Year_Text = (TextView) findViewById(R.id.year_text);
                                Callnow  =(Button) findViewById(R.id.Callnow);
                                User_Blood.setText((bloodgp.get(itemPosition)).toUpperCase());
                                User_name.setText((name.get(itemPosition)).toUpperCase());
                                User_Location.setText((place_info.get(itemPosition)).toUpperCase());
                                User_Phone.setText(("+91 " + phno.get(itemPosition)));


                                Callnow.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {

                                        makePhoneCall(phno.get(itemPosition));

                                    }
                                });
                            }


                        });





                }
            }
        });

       }

    @Override
    public void onBackPressed() {
        if(Slid ==1){
        Intent Main= new Intent(getApplicationContext(),MainActivity.class);
        startActivity(Main);
            finish();
        }
        else if(Slid ==2)
        {
            third_layout.setVisibility(View.GONE);
            first_layout.setVisibility(View.VISIBLE);

            Slid =1;

        }
        else if(Slid ==3){
            fourth_Layout.setVisibility(View.GONE);
            third_layout.setVisibility(View.VISIBLE);

            Slid =2;

        }


    }

// print the data in listView
    private class Loads extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            String res = getData();
            return res;

        }
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);



            if(s.equalsIgnoreCase("failure"))
            {
                Toast.makeText(getApplicationContext(),"No Results Found",Toast.LENGTH_LONG).show();
                third_layout.setVisibility(View.VISIBLE);
                second_layout.setVisibility(View.GONE);
                Slid=3;
                listView.setVisibility(ListView.GONE);
                noresult.setVisibility(TextView.VISIBLE);
            }
            else {
             /*   t1.setText(name);
                t2.setText(email);
                t3.setText(age);
                t4.setText(gender);
                t5.setText(lang);
                t6.setText(country);*/



                third_layout.setVisibility(View.VISIBLE);
                second_layout.setVisibility(View.GONE);
                listView.setVisibility(ListView.VISIBLE);
                noresult.setVisibility(TextView.GONE);
                Slid=3;

                for(int i=0;i<name.size();i++)
                        NameShowList.add((name.get(i)).toUpperCase());


            }
        }
    }

// data from ikthss.tk/App/fetch.php
    public String getData()
    {
        String ret = "na";
        JsonAct ja =new JsonAct();
        String result = ja.setJsonVal(url);
        try
        {
             arr=new JSONArray(result);
            String s = arr.getString(0).trim();
            Log.v("Exception", "********"+s);
            if (!s.equalsIgnoreCase("failure"))
            {

                name = new ArrayList<String>();
                phno = new ArrayList<String>();
                bloodgp = new ArrayList<String>();
                place_info = new ArrayList<String>();
                //st_year = new ArrayList<String>();
                    int i=0;
               do {
                    ob = arr.getJSONObject(i);
                     namesub = ob.getString("name");
                     name.add(namesub);
                     phno.add(ob.getString("phno"));
                     bloodgp.add(ob.getString("gp"));
                     place_info.add(ob.getString("place"));
                   i++;
                }
                while(namesub!=null);
                ret = "ok";
            }


            else
            {
                ret =  "failure";
            }
        } catch (JSONException e) {
            ret = e.toString();
        }
        return ret;
    }

    // make a call after Permission
    private void makePhoneCall(String phone) {

            number=phone;
            if (ContextCompat.checkSelfPermission(NeedBlood.this,
                    Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(NeedBlood.this,
                        new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL);
            } else {
                String dial = "tel:" + number;
                startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dial)));
            }

        }

// for Permission
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CALL) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                makePhoneCall(number);
            } else {
                Toast.makeText(this, "Permission DENIED", Toast.LENGTH_SHORT).show();
            }
        }
    }


    //SIgnInWithGoogle
    private void signOut() {
        mGoogleSignInClient.signOut()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(NeedBlood.this, "Signed Out Successfully", Toast.LENGTH_LONG).show();
                        finish();
                    }
                });
    }

}


