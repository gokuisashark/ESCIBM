package com.example.fish.escpart1;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.fish.sqlJava.RequestHandler;
import com.example.fish.sqlJava.URLs;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * Created by setia on 3/15/2018.
 */

class UserLogin extends AsyncTask<HashMap<String,String>, Void, String> {

    private ProgressBar progressBar;
    private Context context;

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
    protected String doInBackground(HashMap<String,String>...params) {
        //creating request handler object
        RequestHandler requestHandler = new RequestHandler();
        Log.i("username", params[0].get("username"));
        Log.i("password", params[0].get("password"));

        //creating request parameters
        //returing the response
        return requestHandler.sendPostRequest(URLs.URL_LOGIN, params[0]);
    }

    @Override
    protected void onPostExecute(String jsonString) {
        super.onPostExecute(jsonString);
//            progressBar.setVisibility(View.GONE);


        try {
            //converting response to json object
            Log.i("jsonString",jsonString);
            JSONObject obj = new JSONObject(jsonString);

            //if no error in response
            if (!obj.getBoolean("error")) {
                String message = obj.getString("message");
                Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
                if (message.equals("Invalid username or password or bad face")){
                    return;
                }

                //getting the user from the response
//                JSONObject userJson = obj.getJSONObject("user");

                //creating a new user object
//                User user = new User(
//                        userJson.getString("username"),
//                        userJson.getString("email"),
//                        userJson.getString("gender")
//                );

                //storing the user in shared preferences
//                SharedPrefManager.getInstance(context).userLogin(user);

                //starting the profile activity
//                finish();
                Intent intent = new Intent(context, HomePageActivity.class);
                intent.putExtra("jsonString", jsonString);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            } else {
                Toast.makeText(context, "Invalid username, password or face", Toast.LENGTH_SHORT).show();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
