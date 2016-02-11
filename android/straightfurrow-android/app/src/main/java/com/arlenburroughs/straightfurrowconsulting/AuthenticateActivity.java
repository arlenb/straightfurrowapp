package com.arlenburroughs.straightfurrowconsulting;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.arlenburroughs.straightfurrowconsulting.tools.DefaultRequestQueue;
import com.arlenburroughs.straightfurrowconsulting.tools.PasswordHasher;
import com.arlenburroughs.straightfurrowconsulting.tools.Prefs;

import org.json.JSONArray;
import org.json.JSONObject;


public class AuthenticateActivity extends ActionBarActivity{

    Toolbar toolbar;
    EditText user,pass;
    CheckBox stayLoggedInBox;

    public final static String USERID = "current user";
    final String AUTH_URL = "http://straightfurrowconsulting.arlenburroughs.com/auth.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authenticate);

        setUpView();

        boolean autoLogin = Prefs.get(getApplicationContext(), Prefs.AUTO_LOGIN);
        if(autoLogin){
            user.setText(Prefs.getLastUser(getApplicationContext()));
            pass.setText(Prefs.getLastPass(getApplicationContext()));
            attemptLogin(Prefs.getLastUser(getApplicationContext()), Prefs.getLastPass(getApplicationContext()));
        }
        //Bypass login
        //goToMainActivity("3");//TODO comment out this line to actually use log in.
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    private void setUpView(){

        user = (EditText) findViewById(R.id.username_field);
        pass = (EditText) findViewById(R.id.password_field);
        stayLoggedInBox = (CheckBox) findViewById(R.id.stay_logged_in_box);

        // Setup toolbar/actionbar
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setHomeButtonEnabled(true);

        Button loginButton = (Button) findViewById(R.id.login_button);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String passHash = PasswordHasher.hashPassword(pass.getText().toString());
                Prefs.put(getApplicationContext(), Prefs.LAST_USER, user.getText().toString());
                Prefs.put(getApplicationContext(), Prefs.LAST_PASS, passHash);
                attemptLogin(user.getText().toString(), passHash);
            }
        });
        stayLoggedInBox.setChecked(Prefs.get(getApplicationContext(), Prefs.AUTO_LOGIN));
    }

    private void attemptLogin(String attemptedUser, String attemptedPass){

        Log.i("Auth", "U:"+attemptedUser+", P:"+attemptedPass);

        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        if(this.getCurrentFocus()!=null)imm.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(), 0);

        ProgressBar spinner = (ProgressBar) findViewById(R.id.auth_loading);
        spinner.setVisibility(View.VISIBLE);

        DefaultRequestQueue.getDefaultQueue(getApplicationContext()).add(getDataRequest(attemptedUser,attemptedPass));
    }

    private StringRequest getDataRequest(String user, String pass){
        String url = AUTH_URL + "?username="+user+"&pass_hash="+pass;
        Log.d("Data", "URL:" + url);
        StringRequest sr = new StringRequest(url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String data) {
                        finishAuthenticationAttempt(data);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Log.e("Data", "Data Fetch Error: " + volleyError.toString());
                        finishAuthenticationAttempt("");
                    }
                }
        );
        sr.setRetryPolicy(new DefaultRetryPolicy(
                10000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        return sr;
    }

    private void finishAuthenticationAttempt(String response){
        if(response == null){
            authFail(response);
            return;
        }

        boolean success = false;
        int userId = -1;
        try{
            JSONObject jObj = new JSONArray(response).getJSONObject(0);
            userId = jObj.getInt("id");
            success = true;
        }catch(Exception e){e.printStackTrace();}

        Log.i("Auth", "Auth attempt successful?: " + success+", response:"+response);

        if(success){
            Prefs.put(getApplicationContext(),Prefs.AUTO_LOGIN,stayLoggedInBox.isChecked());
            goToMainActivity(userId);
        }
        else authFail(response);
    }

    private void authFail(String response){
        ProgressBar spinner = (ProgressBar) findViewById(R.id.auth_loading);
        spinner.setVisibility(View.INVISIBLE);
        Toast.makeText(getApplicationContext(),"Log In Failed, "+response,Toast.LENGTH_SHORT).show();
    }

    private void goToMainActivity(int authenticatedUser){
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.putExtra(USERID, authenticatedUser);
        startActivity(intent);
        Prefs.put(getApplicationContext(), Prefs.CURR_USR_ID, ""+authenticatedUser);
        finish();
    }
}