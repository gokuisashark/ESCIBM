package com.example.fish.escpart1;

import android.content.Context;
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
 * Created by setia on 3/15/2018.
 */

class UserLogin extends AsyncTask<String, Void, String> {

    ProgressBar progressBar;
    Context context;

    UserLogin(Context context) {
        this.context = context;
    }

    UserLogin(Context context, ProgressBar progressBar) {
        this.context = context;
        this.progressBar = progressBar;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
//            TODO: Set up the progress bar
//            progressBar = (ProgressBar) findViewById(R.id.progressBar);
//            progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    protected String doInBackground(String... loginInfo) {
        //creating request handler object
        RequestHandler requestHandler = new RequestHandler();
        Log.i("username", loginInfo[0]);
        Log.i("password", loginInfo[1]);

        //creating request parameters
        HashMap<String, String> params = new HashMap<>();
        params.put("username", loginInfo[0]);
        params.put("password", loginInfo[1]);
        //returing the response
        return requestHandler.sendPostRequest(URLs.URL_LOGIN, params);
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
//            progressBar.setVisibility(View.GONE);


        try {
            //converting response to json object
            Log.i("Vincent",s);
            JSONObject obj = new JSONObject(s);

            //if no error in response
            if (!obj.getBoolean("error")) {
                Toast.makeText(context, obj.getString("message"), Toast.LENGTH_SHORT).show();

                //getting the user from the response
                JSONObject userJson = obj.getJSONObject("user");

                //creating a new user object
                User user = new User(
                        userJson.getString("username"),
                        userJson.getString("email"),
                        userJson.getString("gender")
                );

                //storing the user in shared preferences
                SharedPrefManager.getInstance(context).userLogin(user);

                //starting the profile activity
//                finish();
//                startActivity(new Intent(context, HomePageActivity.class));
            } else {
                Toast.makeText(context, "Invalid username or password", Toast.LENGTH_SHORT).show();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
