package com.merive.securepass.fragments;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.merive.securepass.R;

public class UpdateFragment extends DialogFragment {

    TextView version;
    ImageView download;

    public UpdateFragment() {
    }

    public static UpdateFragment newInstance(String oldVersion, String newVersion) {
        UpdateFragment frag = new UpdateFragment();
        Bundle args = new Bundle();
        args.putString("oldVersion", oldVersion);
        args.putString("newVersion", newVersion);
        frag.setArguments(args);
        return frag;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        return inflater.inflate(R.layout.update_fragment, container);
    }


    @SuppressLint("SetTextI18n")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        version = view.findViewById(R.id.version);
        version.setText("Download: " + getArguments().getString("oldVersion") + " → " +
                getArguments().getString("newVersion"));

        /* OnClick Download */
        download = view.findViewById(R.id.download);
        download.setOnClickListener(v -> {
            clickDownload();
        });
    }

    public void clickDownload() {
        dismiss();
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://merive.herokuapp.com/SecurePass"));
        startActivity(browserIntent);
    }
}
