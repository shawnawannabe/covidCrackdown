package com.example.covidcrackdown.fragments;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.budiyev.android.codescanner.CodeScanner;
import com.budiyev.android.codescanner.CodeScannerView;
import com.budiyev.android.codescanner.DecodeCallback;
import com.example.covidcrackdown.activities.HistoryActivity;
import com.example.covidcrackdown.models.MySingleton;
import com.example.covidcrackdown.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.zxing.Result;

import org.json.JSONObject;

import static android.content.ContentValues.TAG;


public class CheckInFragment extends Fragment {
    private CodeScanner mCodeScanner;
    private TextView historyTextView;
    private FirebaseAuth mAuth;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        final Activity activity = getActivity();
        View view = inflater.inflate(R.layout.fragment_check_in, container, false);

        mAuth = FirebaseAuth.getInstance();
        //qr code scanner
        CodeScannerView scannerView = view.findViewById(R.id.check_in_scanner_view);
        mCodeScanner = new CodeScanner(activity, scannerView);
        mCodeScanner.setDecodeCallback(new DecodeCallback() {
            @Override
            public void onDecoded(@NonNull final Result result) {
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //Toast.makeText(activity, result.getText(), Toast.LENGTH_SHORT).show();

                        //get json from qrcode
                        String url =  result.getText();
                        String uid = mAuth.getCurrentUser().getUid();
                        String post_url = "https://android-2c0a7-default-rtdb.firebaseio.com/users/"+ uid + "/location.json";

                        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                                    @Override
                                    public void onResponse(JSONObject response) {
                                        Log.d(TAG, "Response received");
                                        Toast.makeText(activity, "CHECK IN SUCCESSFULLY", Toast.LENGTH_SHORT).show();
                                        postData(post_url, response);
                                    }
                                }, new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {
                                        Log.d(TAG, "onResponse: " + error.getMessage());
                                    }
                                });
                        // Access the RequestQueue through your singleton class.
                        MySingleton.getInstance(getActivity()).addToRequestQueue(jsonObjectRequest);
                    }
                });
            }
        });
        scannerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCodeScanner.startPreview();
            }
        });
        setupPermission((Context) getActivity(), (Activity) getActivity());

        //open history
        historyTextView = view.findViewById(R.id.text_view_fragment_check_in_history);
        historyTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openHistoryActivity();
            }
        });
        return view;
    }

    public void postData(String url, JSONObject data){
        JsonObjectRequest jsonobj = new JsonObjectRequest(Request.Method.POST, url, data,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG, "onResponse: response received");
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e(TAG, "onErrorResponse: ", error.getCause());
                    }
                }
        ){

        };
        MySingleton.getInstance(getActivity()).addToRequestQueue(jsonobj);
    }

    private void openHistoryActivity(){
        Intent intent = new Intent(getActivity(), HistoryActivity.class);
        startActivity(intent);
    }

    @Override
    public void onResume() {
        super.onResume();
        mCodeScanner.startPreview();
    }

    @Override
    public void onPause() {
        mCodeScanner.releaseResources();
        super.onPause();
    }

    private static final int requestCode = 0;

    public void setupPermission(Context context, Activity activity){
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA)
                == PackageManager.PERMISSION_DENIED) {
            Log.d("CAMERA ACCESS GRANTED:", "succeed");
        }else{
            ActivityCompat.requestPermissions(activity, new String[] {Manifest.permission.CAMERA}, requestCode);
        }
    }
}