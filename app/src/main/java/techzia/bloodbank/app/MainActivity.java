package techzia.bloodbank.app;



import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.TransitionManager;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import techzia.bloodbank.R;

public class MainActivity extends AppCompatActivity {
    //SignInWithGoogle
    ImageView image;
    TextView email;
    GoogleSignInClient mGoogleSignInClient;



    // Place - Class

     LinearLayout first_layout, second_layout, third_layout, fourth_Layout, fifth_Layout, sixth_Layout, seventh_Layout;

    //First Layout Button
        Button signin, Create_accoount,admin;
        String Go=null;
        ProgressBar LoadBar;

    //second Layout
        ImageButton phonenumber;
        EditText inputPhonenumber;
        TextView Errorplace;

    //third layout
        ProgressBar progressBar;

    //fourth layout
        EditText place;
        ImageButton placeNext;
        TextView phone_error;

    //fifth layout
        EditText userName;
        TextView NameError,Active;
        ImageButton usernext;

    //sixth Layout
        Button groupA, groupB, gruopAB, groupO, groupContinue,groupAn, groupBn, gruopABn, groupOn;
        String BloodeGroup,number,UserName,UserPlace,url;
        TextView BloodError;
        int Slid=1;

    //seventh Layout
        TextView UserNameFinal;
        Button Request;

    //Age
        String ageNew;
        EditText age;
        ImageButton ageNext;
        TextView errorAge;
        String year, UserAge;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //SignInWithGoogle


        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        image = (ImageView) findViewById(R.id.imageView);
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


               AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setMessage("Are you sure want to Logout?");
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        signOut();
                    }
                });
                AlertDialog alertDialog=builder.create();
                alertDialog.show();
            }
        });

        //email = (TextView) findViewById(R.id.textEmail);
        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
        if (acct != null) {
            //String personName = acct.getDisplayName();
            //String personEmail = acct.getEmail();
            //String personId = acct.getId();
           Uri personPhoto = acct.getPhotoUrl();


            //email.setText(personEmail);
            //id.setText(personId);
            Glide.with(this).load(String.valueOf(personPhoto)).apply(RequestOptions.circleCropTransform()).into(image);
        }


        //ends SignInWithGoogle


        first_layout = (LinearLayout) findViewById(R.id.first_layout);
        second_layout = (LinearLayout) findViewById(R.id.second_layout);
        third_layout = (LinearLayout) findViewById(R.id.third_layout);
        fourth_Layout = (LinearLayout) findViewById(R.id.fourth_layout);
        fifth_Layout = (LinearLayout) findViewById(R.id.five_layout);
        sixth_Layout = (LinearLayout) findViewById(R.id.sixth_layout);
        seventh_Layout = (LinearLayout) findViewById(R.id.seventh_layout);



        first_layout.setVisibility(View.VISIBLE);


        final ViewGroup transitionsContainerfirst = (ViewGroup) findViewById(R.id.first_layout);
        TransitionManager.beginDelayedTransition(transitionsContainerfirst);

        final ViewGroup transitionsContainersecond = (ViewGroup) findViewById(R.id.second_layout);
        TransitionManager.beginDelayedTransition(transitionsContainersecond);

        final ViewGroup transitionsContainerforth = (ViewGroup) findViewById(R.id.fourth_layout);
        TransitionManager.beginDelayedTransition(transitionsContainerfirst);

        final ViewGroup transitionsContainerfifth = (ViewGroup) findViewById(R.id.five_layout);
        TransitionManager.beginDelayedTransition(transitionsContainerfifth);

        signin = (Button) transitionsContainerfirst.findViewById(R.id.signin);
        Create_accoount = (Button) transitionsContainerfirst.findViewById(R.id.create_account);
        admin=(Button) findViewById(R.id.admin);//admin-Button
        inputPhonenumber = (EditText) transitionsContainersecond.findViewById(R.id.edittext_phone);
        phone_error = (TextView) transitionsContainersecond.findViewById(R.id.mobile);
        phonenumber = (ImageButton) transitionsContainersecond.findViewById(R.id.input_phoneNumber);



        place = (EditText) transitionsContainerforth.findViewById(R.id.place);
        Errorplace = (TextView) transitionsContainerforth.findViewById(R.id.Errorplace);
        placeNext = (ImageButton) transitionsContainerforth.findViewById(R.id.placenext);
        userName = (EditText) transitionsContainerfifth.findViewById(R.id.userName);
        NameError = (TextView) transitionsContainerfifth.findViewById(R.id.Errorname);
        usernext = (ImageButton) transitionsContainerfifth.findViewById(R.id.usernamenext);

        //age
        age = (EditText) findViewById(R.id.age);
        ageNew = age.getText().toString();
        ageNext = findViewById(R.id.ageNext);
        errorAge = findViewById(R.id.errorAge);

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent Need= new Intent(getApplicationContext(),NeedBlood.class);
               startActivity(Need);
               finish();

            }
        });


        Create_accoount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                first_layout.setVisibility(View.GONE);
                second_layout.setVisibility(View.VISIBLE);
                Slid++;

            }

        });


        admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "SetUp Admin Activity if you need", Toast.LENGTH_SHORT).show();
            }
        });


        phonenumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                number = inputPhonenumber.getText().toString();
                String PhoneE=phone_error.getText().toString();

                if(number.isEmpty() || number.length() !=10){
                    Toast.makeText(MainActivity.this,"Invalid Phone Number", Toast.LENGTH_SHORT).show();
                    phone_error.setTextColor(getResources().getColor(R.color.ErrorColor));
                    phone_error.setText("Looks like your phone number may be incorrect.\nPlease try entering your full number");

                }
                else
                {
                    phone_error.setTextColor(getResources().getColor(R.color.Normal));
                    phone_error.setText("Please enter your mobile number");

                    second_layout.setVisibility(View.GONE);
                    third_layout.setVisibility(View.VISIBLE);
//                    fourth_Layout.setVisibility(View.VISIBLE);
                    Slid++;

                    inputPhonenumber.requestFocus();

                    InputMethodManager inputManager = (InputMethodManager)
                            getSystemService(Context.INPUT_METHOD_SERVICE);

                    inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                            InputMethodManager.HIDE_NOT_ALWAYS);
                    third_layout.requestFocus();

//                    place.requestFocus();

                }

            }

        }

        );

//
        ageNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserAge = age.getText().toString();
                if(UserAge.isEmpty())
                {
                    errorAge.setTextColor(getResources().getColor(R.color.ErrorColor));
                    errorAge.setText("Please Enter Your Age.");
                }
                else{

                    errorAge.setTextColor(getResources().getColor(R.color.Normal));
                    errorAge.setText("Please Enter Your Age Below");

                    third_layout.setVisibility(View.GONE);
                    fourth_Layout.setVisibility(View.VISIBLE);
                    Slid++;
                    fourth_Layout.requestFocus();
                    place.requestFocus();
                    InputMethodManager manager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    manager.toggleSoftInput(InputMethodManager.SHOW_FORCED,0);
                }


            }
        });



        placeNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserPlace = place.getText().toString();

                if(UserPlace.isEmpty())
                {
                   Errorplace.setTextColor(getResources().getColor(R.color.ErrorColor));
                    Errorplace.setText("Please Enter Your Class.");
                }
                else {
                    Errorplace.setTextColor(getResources().getColor(R.color.Normal));
                    Errorplace.setText("Enter Your Class below");

                    fourth_Layout.setVisibility(View.GONE);
                    fifth_Layout.setVisibility(View.VISIBLE);

                    Slid++;


                    fifth_Layout.requestFocus();
                    userName.requestFocus();
                }

            }
        });

        usernext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserName = userName.getText().toString();

              //  Toast.makeText(MainActivity.this,"You click on Request Button",Toast.LENGTH_LONG).show();
                userName.requestFocus();
                if(UserName.isEmpty() ){


                    NameError.setTextColor(getResources().getColor(R.color.ErrorColor));
                    NameError.setText("Please Enter Your Name.");
                }
                else
                {
                    NameError.setTextColor(getResources().getColor(R.color.Normal));
                    NameError.setText("Enter your name below");

                    InputMethodManager inputManager = (InputMethodManager)
                            getSystemService(Context.INPUT_METHOD_SERVICE);

                    inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                            InputMethodManager.HIDE_NOT_ALWAYS);
                   fifth_Layout.setVisibility(View.GONE);
                   sixth_Layout.setVisibility(View.VISIBLE);
                   Slid++;
                }



            }
        });

        final ViewGroup transitionsContainersixth = (ViewGroup) findViewById(R.id.sixth_layout);
        TransitionManager.beginDelayedTransition(transitionsContainersixth);
        groupA = (Button) findViewById(R.id.groupA);
        groupB = (Button) findViewById(R.id.groupB);
        gruopAB = (Button) findViewById(R.id.groupAB);
        groupO = (Button) findViewById(R.id.groupO);
        groupAn = (Button) findViewById(R.id.groupAn);
        groupBn = (Button) findViewById(R.id.groupBn);
        gruopABn = (Button) findViewById(R.id.groupABn);
        groupOn = (Button) findViewById(R.id.groupOn);
        groupContinue = (Button) findViewById(R.id.groupContinue);
        BloodError =(TextView) transitionsContainersixth.findViewById(R.id.ErrorBlood);


        groupA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                groupA.setBackground(getDrawable(R.drawable.create_account_background));
                groupA.setTextColor(getResources().getColor(R.color.WHiTE));
                groupB.setBackground(getDrawable(R.color.WHiTE));
                groupB.setTextColor(getResources().getColor(R.color.Normal));
                groupB.setBackground(getDrawable(R.drawable.blood_group_button_background));
                groupO.setBackground(getDrawable(R.color.WHiTE));
                groupO.setTextColor(getResources().getColor(R.color.Normal));
                groupO.setBackground(getDrawable(R.drawable.blood_group_button_background));
                gruopAB.setBackground(getDrawable(R.color.WHiTE));
                gruopAB.setTextColor(getResources().getColor(R.color.Normal));
                gruopAB.setBackground(getDrawable(R.drawable.blood_group_button_background));


                groupAn.setBackground(getDrawable(R.color.WHiTE));
                groupAn.setTextColor(getResources().getColor(R.color.Normal));
                groupAn.setBackground(getDrawable(R.drawable.blood_group_button_background));

                groupBn.setBackground(getDrawable(R.color.WHiTE));
                groupBn.setTextColor(getResources().getColor(R.color.Normal));
                groupBn.setBackground(getDrawable(R.drawable.blood_group_button_background));
                groupOn.setBackground(getDrawable(R.color.WHiTE));
                groupOn.setTextColor(getResources().getColor(R.color.Normal));
                groupOn.setBackground(getDrawable(R.drawable.blood_group_button_background));
                gruopABn.setBackground(getDrawable(R.color.WHiTE));
                gruopABn.setTextColor(getResources().getColor(R.color.Normal));
                gruopABn.setBackground(getDrawable(R.drawable.blood_group_button_background));
                BloodeGroup = "A%2B";
            }
        });
        groupB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                groupB.setBackground(getDrawable(R.drawable.create_account_background));
                groupB.setTextColor(getResources().getColor(R.color.WHiTE));
                groupA.setBackground(getDrawable(R.color.WHiTE));
                groupA.setTextColor(getResources().getColor(R.color.Normal));
                groupA.setBackground(getDrawable(R.drawable.blood_group_button_background));
                groupO.setBackground(getDrawable(R.color.WHiTE));
                groupO.setTextColor(getResources().getColor(R.color.Normal));
                groupO.setBackground(getDrawable(R.drawable.blood_group_button_background));
                gruopAB.setBackground(getDrawable(R.color.WHiTE));
                gruopAB.setTextColor(getResources().getColor(R.color.Normal));
                gruopAB.setBackground(getDrawable(R.drawable.blood_group_button_background));


                groupAn.setBackground(getDrawable(R.color.WHiTE));
                groupAn.setTextColor(getResources().getColor(R.color.Normal));
                groupAn.setBackground(getDrawable(R.drawable.blood_group_button_background));

                groupBn.setBackground(getDrawable(R.color.WHiTE));
                groupBn.setTextColor(getResources().getColor(R.color.Normal));
                groupBn.setBackground(getDrawable(R.drawable.blood_group_button_background));
                groupOn.setBackground(getDrawable(R.color.WHiTE));
                groupOn.setTextColor(getResources().getColor(R.color.Normal));
                groupOn.setBackground(getDrawable(R.drawable.blood_group_button_background));
                gruopABn.setBackground(getDrawable(R.color.WHiTE));
                gruopABn.setTextColor(getResources().getColor(R.color.Normal));
                gruopABn.setBackground(getDrawable(R.drawable.blood_group_button_background));

                BloodeGroup = "B%2B";
            }
        });

        gruopAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gruopAB.setBackground(getDrawable(R.drawable.create_account_background));
                gruopAB.setTextColor(getResources().getColor(R.color.WHiTE));

                groupA.setBackground(getDrawable(R.color.WHiTE));
                groupA.setTextColor(getResources().getColor(R.color.Normal));
                groupA.setBackground(getDrawable(R.drawable.blood_group_button_background));

                groupB.setBackground(getDrawable(R.color.WHiTE));
                groupB.setTextColor(getResources().getColor(R.color.Normal));
                groupB.setBackground(getDrawable(R.drawable.blood_group_button_background));
                groupO.setBackground(getDrawable(R.color.WHiTE));
                groupO.setTextColor(getResources().getColor(R.color.Normal));
                groupO.setBackground(getDrawable(R.drawable.blood_group_button_background));



                groupAn.setBackground(getDrawable(R.color.WHiTE));
                groupAn.setTextColor(getResources().getColor(R.color.Normal));
                groupAn.setBackground(getDrawable(R.drawable.blood_group_button_background));

                groupBn.setBackground(getDrawable(R.color.WHiTE));
                groupBn.setTextColor(getResources().getColor(R.color.Normal));
                groupBn.setBackground(getDrawable(R.drawable.blood_group_button_background));
                groupOn.setBackground(getDrawable(R.color.WHiTE));
                groupOn.setTextColor(getResources().getColor(R.color.Normal));
                groupOn.setBackground(getDrawable(R.drawable.blood_group_button_background));
                gruopABn.setBackground(getDrawable(R.color.WHiTE));
                gruopABn.setTextColor(getResources().getColor(R.color.Normal));
                gruopABn.setBackground(getDrawable(R.drawable.blood_group_button_background));
                BloodeGroup = "AB%2B";
            }
        });

        groupO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                groupO.setBackground(getDrawable(R.drawable.create_account_background));
                groupO.setTextColor(getResources().getColor(R.color.WHiTE));
                groupA.setBackground(getDrawable(R.color.WHiTE));
                groupA.setTextColor(getResources().getColor(R.color.Normal));
                groupA.setBackground(getDrawable(R.drawable.blood_group_button_background));

                groupB.setBackground(getDrawable(R.color.WHiTE));
                groupB.setTextColor(getResources().getColor(R.color.Normal));
                groupB.setBackground(getDrawable(R.drawable.blood_group_button_background));
                gruopAB.setBackground(getDrawable(R.color.WHiTE));
                gruopAB.setTextColor(getResources().getColor(R.color.Normal));
                gruopAB.setBackground(getDrawable(R.drawable.blood_group_button_background));

                groupAn.setBackground(getDrawable(R.color.WHiTE));
                groupAn.setTextColor(getResources().getColor(R.color.Normal));
                groupAn.setBackground(getDrawable(R.drawable.blood_group_button_background));

                groupBn.setBackground(getDrawable(R.color.WHiTE));
                groupBn.setTextColor(getResources().getColor(R.color.Normal));
                groupBn.setBackground(getDrawable(R.drawable.blood_group_button_background));
                groupOn.setBackground(getDrawable(R.color.WHiTE));
                groupOn.setTextColor(getResources().getColor(R.color.Normal));
                groupOn.setBackground(getDrawable(R.drawable.blood_group_button_background));
                gruopABn.setBackground(getDrawable(R.color.WHiTE));
                gruopABn.setTextColor(getResources().getColor(R.color.Normal));
                gruopABn.setBackground(getDrawable(R.drawable.blood_group_button_background));
                BloodeGroup = "O%2B";
            }
        });



        groupAn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                groupAn.setBackground(getDrawable(R.drawable.create_account_background));
                groupAn.setTextColor(getResources().getColor(R.color.WHiTE));

                groupA.setBackground(getDrawable(R.color.WHiTE));
                groupA.setTextColor(getResources().getColor(R.color.Normal));
                groupA.setBackground(getDrawable(R.drawable.blood_group_button_background));

                groupB.setBackground(getDrawable(R.color.WHiTE));
                groupB.setTextColor(getResources().getColor(R.color.Normal));
                groupB.setBackground(getDrawable(R.drawable.blood_group_button_background));
                gruopAB.setBackground(getDrawable(R.color.WHiTE));
                gruopAB.setTextColor(getResources().getColor(R.color.Normal));
                gruopAB.setBackground(getDrawable(R.drawable.blood_group_button_background));
                groupO.setBackground(getDrawable(R.color.WHiTE));
                groupO.setTextColor(getResources().getColor(R.color.Normal));
                groupO.setBackground(getDrawable(R.drawable.blood_group_button_background));

                groupBn.setBackground(getDrawable(R.color.WHiTE));
                groupBn.setTextColor(getResources().getColor(R.color.Normal));
                groupBn.setBackground(getDrawable(R.drawable.blood_group_button_background));
                groupOn.setBackground(getDrawable(R.color.WHiTE));
                groupOn.setTextColor(getResources().getColor(R.color.Normal));
                groupOn.setBackground(getDrawable(R.drawable.blood_group_button_background));
                gruopABn.setBackground(getDrawable(R.color.WHiTE));
                gruopABn.setTextColor(getResources().getColor(R.color.Normal));
                gruopABn.setBackground(getDrawable(R.drawable.blood_group_button_background));
                BloodeGroup = "A-";
            }
        });


        groupBn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                groupBn.setBackground(getDrawable(R.drawable.create_account_background));
                groupBn.setTextColor(getResources().getColor(R.color.WHiTE));
                groupA.setBackground(getDrawable(R.color.WHiTE));
                groupA.setTextColor(getResources().getColor(R.color.Normal));
                groupA.setBackground(getDrawable(R.drawable.blood_group_button_background));

                groupB.setBackground(getDrawable(R.color.WHiTE));
                groupB.setTextColor(getResources().getColor(R.color.Normal));
                groupB.setBackground(getDrawable(R.drawable.blood_group_button_background));
                gruopAB.setBackground(getDrawable(R.color.WHiTE));
                gruopAB.setTextColor(getResources().getColor(R.color.Normal));
                gruopAB.setBackground(getDrawable(R.drawable.blood_group_button_background));
                groupO.setBackground(getDrawable(R.color.WHiTE));
                groupO.setTextColor(getResources().getColor(R.color.Normal));
                groupO.setBackground(getDrawable(R.drawable.blood_group_button_background));


                groupAn.setBackground(getDrawable(R.color.WHiTE));
                groupAn.setTextColor(getResources().getColor(R.color.Normal));
                groupAn.setBackground(getDrawable(R.drawable.blood_group_button_background));


                groupOn.setBackground(getDrawable(R.color.WHiTE));
                groupOn.setTextColor(getResources().getColor(R.color.Normal));
                groupOn.setBackground(getDrawable(R.drawable.blood_group_button_background));
                gruopABn.setBackground(getDrawable(R.color.WHiTE));
                gruopABn.setTextColor(getResources().getColor(R.color.Normal));
                gruopABn.setBackground(getDrawable(R.drawable.blood_group_button_background));



                BloodeGroup = "B-";
            }
        });

        gruopABn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gruopABn.setBackground(getDrawable(R.drawable.create_account_background));
                gruopABn.setTextColor(getResources().getColor(R.color.WHiTE));

                groupA.setBackground(getDrawable(R.color.WHiTE));
                groupA.setTextColor(getResources().getColor(R.color.Normal));
                groupA.setBackground(getDrawable(R.drawable.blood_group_button_background));

                groupB.setBackground(getDrawable(R.color.WHiTE));
                groupB.setTextColor(getResources().getColor(R.color.Normal));
                groupB.setBackground(getDrawable(R.drawable.blood_group_button_background));
                gruopAB.setBackground(getDrawable(R.color.WHiTE));
                gruopAB.setTextColor(getResources().getColor(R.color.Normal));
                gruopAB.setBackground(getDrawable(R.drawable.blood_group_button_background));
                groupO.setBackground(getDrawable(R.color.WHiTE));
                groupO.setTextColor(getResources().getColor(R.color.Normal));
                groupO.setBackground(getDrawable(R.drawable.blood_group_button_background));



                groupBn.setBackground(getDrawable(R.color.WHiTE));
                groupBn.setTextColor(getResources().getColor(R.color.Normal));
                groupBn.setBackground(getDrawable(R.drawable.blood_group_button_background));
                groupOn.setBackground(getDrawable(R.color.WHiTE));
                groupOn.setTextColor(getResources().getColor(R.color.Normal));
                groupOn.setBackground(getDrawable(R.drawable.blood_group_button_background));

                BloodeGroup = "AB-";
            }
        });

        groupOn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                groupOn.setBackground(getDrawable(R.drawable.create_account_background));
                groupOn.setTextColor(getResources().getColor(R.color.WHiTE));

                groupA.setBackground(getDrawable(R.color.WHiTE));
                groupA.setTextColor(getResources().getColor(R.color.Normal));
                groupA.setBackground(getDrawable(R.drawable.blood_group_button_background));

                groupB.setBackground(getDrawable(R.color.WHiTE));
                groupB.setTextColor(getResources().getColor(R.color.Normal));
                groupB.setBackground(getDrawable(R.drawable.blood_group_button_background));
                gruopAB.setBackground(getDrawable(R.color.WHiTE));
                gruopAB.setTextColor(getResources().getColor(R.color.Normal));
                gruopAB.setBackground(getDrawable(R.drawable.blood_group_button_background));
                groupO.setBackground(getDrawable(R.color.WHiTE));
                groupO.setTextColor(getResources().getColor(R.color.Normal));
                groupO.setBackground(getDrawable(R.drawable.blood_group_button_background));


                groupAn.setBackground(getDrawable(R.color.WHiTE));
                groupAn.setTextColor(getResources().getColor(R.color.Normal));
                groupAn.setBackground(getDrawable(R.drawable.blood_group_button_background));

                groupBn.setBackground(getDrawable(R.color.WHiTE));
                groupBn.setTextColor(getResources().getColor(R.color.Normal));
                groupBn.setBackground(getDrawable(R.drawable.blood_group_button_background));
                gruopABn.setBackground(getDrawable(R.color.WHiTE));
                gruopABn.setTextColor(getResources().getColor(R.color.Normal));
                gruopABn.setBackground(getDrawable(R.drawable.blood_group_button_background));
                BloodeGroup = "O-";
            }
        });

            groupContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(BloodeGroup == null ){
                    BloodError.setVisibility(TextView.VISIBLE);
                    BloodError.setTextColor(getResources().getColor(R.color.ErrorColor));



                }
                else{
                    BloodError.setVisibility(TextView.GONE);



                sixth_Layout.setVisibility(View.GONE);
                seventh_Layout.setVisibility(View.VISIBLE);
                Slid++;

                }
            }
        });





        Request = (Button) findViewById(R.id.request);
        LoadBar = (ProgressBar) findViewById(R.id.Load);
        Active = (TextView) findViewById(R.id.active);
        Request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(Go==null) {

                    url = "be-a-part.php?name=" + UserName + "&phno=" + number + "&age=" + ageNew + "&gp=" + BloodeGroup + "&place=" + UserPlace + "";
                    url = url.replace(" ", "%20");

                    new Loads().execute();
                    Request.setVisibility(Button.GONE);
                    LoadBar.setVisibility(Button.VISIBLE);
                }
                else {
                    Intent Need= new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(Need);
                    finish();
                }





            }


        });

    }
    public void onBackPressed() {
     if(Slid==1) {

         final AlertDialog.Builder builder = new AlertDialog.Builder(new ContextThemeWrapper(this, R.style.AlertDialogCustom));
         builder.setMessage("Are you sure want to exit?");
         builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
             @Override
             public void onClick(DialogInterface dialogInterface, int i) {
                 dialogInterface.cancel();
             }
         });
         builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
             @Override
             public void onClick(DialogInterface dialogInterface, int i) {
                 moveTaskToBack(true);
                 finish();
             }
         });
         AlertDialog alertDialog=builder.create();
         alertDialog.show();
     }
     else if(Slid == 100){
         Intent Need= new Intent(getApplicationContext(),MainActivity.class);
         startActivity(Need);
         finish();
     }
     else if(Slid==2){
         first_layout.setVisibility(View.VISIBLE);
         second_layout.setVisibility(View.GONE);
         Slid--;
     }
     else if(Slid==3) {
         second_layout.setVisibility(View.VISIBLE);
         third_layout.setVisibility(View.GONE);
         Slid--;
     }
     else if(Slid==4)
     {
         third_layout.setVisibility(View.VISIBLE);
         fourth_Layout.setVisibility(View.GONE);
         Slid--;
     }
     else if(Slid==5){
         fourth_Layout.setVisibility(View.VISIBLE);
         fifth_Layout.setVisibility(View.GONE);
         Slid--;
     }
     else if(Slid==6){
         fifth_Layout.setVisibility(View.VISIBLE);
         sixth_Layout.setVisibility(View.GONE);
         Slid--;
     }
     else if(Slid==7){
         sixth_Layout.setVisibility(View.VISIBLE);
         seventh_Layout.setVisibility(View.GONE);
         Slid--;
     }
    }


    // json class

    private class Loads extends AsyncTask<String, Void, String>
    {
        @Override
        protected String doInBackground(String... strings) {
            String res = getData();
            return res;

        }
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if(s.equalsIgnoreCase("successfully"))
            {
                Go = "Success";
                Toast.makeText(getApplicationContext(),"Registration Success",Toast.LENGTH_LONG).show();
                Active.setText("Thank You For Your Active Participation.");
                Request.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.button_success));
                Slid=100;
                Request.setVisibility(Button.VISIBLE);
                LoadBar.setVisibility(Button.GONE);
                Request.setText("registration success");

            }
            else if(s.equalsIgnoreCase("ALRDREG"))
            {
                Go = "Success";
                Slid=100;
                Toast.makeText(getApplicationContext(),"Already Registered",Toast.LENGTH_LONG).show();
                Active.setText("Thank You For Your Active Participation ");
                Request.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.button_warnning));
                Request.setVisibility(Button.VISIBLE);
                LoadBar.setVisibility(Button.GONE);
                Request.setText("Already Registered");
            }
            else {
                Toast.makeText(getApplicationContext(),"Registration Failed",Toast.LENGTH_LONG).show();
                Request.setVisibility(Button.VISIBLE);
                LoadBar.setVisibility(Button.GONE);
                Request.setText("Retry");

            }

        }
    }


    private String getData()
    {

        String ret="failure";
        JsonAct ja =new JsonAct();
        String result = ja.setJsonVal(url);
        try
        {
            JSONArray arr=new JSONArray(result);
            String s = arr.getString(0).trim();
            Log.v("Exception", "********"+s);
            if (s.equalsIgnoreCase("successfully"))
            {

                ret =  "successfully";

            }
            else if(s.equalsIgnoreCase("ALRDREG"))
            {
                ret =  "ALRDREG";
            }
            else
            {
                JSONObject ob=arr.getJSONObject(0);

                ret = ob.getString("id");
                ret =  "failure";
            }
        } catch (JSONException e) {
            ret = e.toString();
        }
        return ret;

    }


    //SIgnInWithGoogle
    private void signOut() {
        mGoogleSignInClient.signOut()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Intent homeIntent = new Intent(MainActivity.this, SignInWithGoogle.class);
                        startActivity(homeIntent);
                        finish();
                    }
                });
    }








}
