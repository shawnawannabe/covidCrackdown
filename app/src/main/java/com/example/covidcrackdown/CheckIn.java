package com.example.covidcrackdown;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.budiyev.android.codescanner.CodeScanner;
import com.budiyev.android.codescanner.CodeScannerView;
import com.budiyev.android.codescanner.DecodeCallback;
import com.google.zxing.Result;

import org.w3c.dom.Text;


public class CheckIn extends Fragment {
    private CodeScanner mCodeScanner;
    private TextView historyTextView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        final Activity activity = getActivity();
        View root = inflater.inflate(R.layout.fragment_check_in, container, false);

        //qr code scanner
        CodeScannerView scannerView = root.findViewById(R.id.check_in_scanner_view);
        mCodeScanner = new CodeScanner(activity, scannerView);
        mCodeScanner.setDecodeCallback(new DecodeCallback() {
            @Override
            public void onDecoded(@NonNull final Result result) {
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(activity, result.getText(), Toast.LENGTH_SHORT).show();
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
        historyTextView = root.findViewById(R.id.text_view_fragment_check_in_history);
        historyTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openHistoryActivity();
            }
        });

        //adding history
//        Toolbar actionBarToolBar = root.findViewById(R.id.toolbar_checkin);
//        ((AppCompatActivity)getActivity()).setSupportActionBar(actionBarToolBar);
//        actionBarToolBar.setTitle("Check Out");
//        setHasOptionsMenu(true);
//        actionBarToolBar.inflateMenu(R.menu.checkin_toolbar);
//        Menu menu = actionBarToolBar.getMenu();

        return root;
    }
    private void openHistoryActivity(){
        Intent intent = new Intent(getActivity(), History.class);
        startActivity(intent);
    }
//    @Override
//    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
//        menu.clear();
//        inflater.inflate(R.menu.checkin_toolbar, menu);
//        super.onCreateOptionsMenu(menu, inflater);
//    }

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
//            requestPermissionLauncher.launch(
//                    Manifest.permission.CAMERA);

            ActivityCompat.requestPermissions(activity, new String[] {Manifest.permission.CAMERA}, requestCode);
        }
//        TODO: make the permission box pop up https://developer.android.com/training/permissions/requesting
    }
//    private ActivityResultLauncher<String> requestPermissionLauncher =
//            registerForActivityResult(new ActivityResultContracts.RequestPermission(), isGranted -> {
//                if (isGranted) {
//                    Log.d("ACCESS:", "GRANTED!!");
//                } else {
//                    // Explain to the user that the feature is unavailable because the
//                    // features requires a permission that the user has denied. At the
//                    // same time, respect the user's decision. Don't link to system
//                    // settings in an effort to convince the user to change their
//                    // decision.
//                }
//            });
}