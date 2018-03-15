package com.example.fish.escpart1;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.fish.sqlJava.RequestHandler;
import com.example.fish.sqlJava.SharedPrefManager;
import com.example.fish.sqlJava.URLs;
import com.example.fish.sqlJava.User;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * Created by setia on 3/14/2018.
 */

class RegisterUser extends AsyncTask<HashMap<String,String>, Void, String> {

    private ProgressBar progressBar;
    private Context context;

    public RegisterUser(Context context, ProgressBar progressBar){
        this.context = context;
        this.progressBar = progressBar;
    }

    public RegisterUser(Context context){
        this.context = context;
    }


    @Override
    protected String doInBackground(HashMap<String,String>... inputParams) {
        //creating request handler object
        RequestHandler requestHandler = new RequestHandler();

        //returning the response
        return requestHandler.sendPostRequest(URLs.URL_REGISTER, inputParams[0]);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        //displaying the progress bar while user registers on the server
//            progressBar = (ProgressBar) findViewById(R.id.progressBar);
//            progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        //hiding the progressbar after completion
//            progressBar.setVisibility(View.GONE);

        try {
            //converting response to json object
            JSONObject obj = new JSONObject(s);
            Log.i("Register",s);
            System.out.println(obj);
            //if no error in response
            if (!obj.getBoolean("error")) {
                Toast.makeText(this.context, obj.getString("message"), Toast.LENGTH_SHORT).show();

                //getting the user from the response
                JSONObject userJson = obj.getJSONObject("user");

                //creating a new user object
                User user = new User(
                        userJson.getString("username"),
                        userJson.getString("email"),
                        userJson.getString("gender")
                );

                //storing the user in shared preferences
                SharedPrefManager.getInstance(this.context).userLogin(user);
                Intent intent = new Intent(context, StartUpActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);

                //starting the profile activity
//                finish();
//                    TODO: Make a Profile Activity and start it after registration
//                    this.context.startActivity(new Intent(context, ProfileActivity.class));
            } else {
                Toast.makeText(this.context, "Some error occurred", Toast.LENGTH_SHORT).show();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}

//executing the async task
