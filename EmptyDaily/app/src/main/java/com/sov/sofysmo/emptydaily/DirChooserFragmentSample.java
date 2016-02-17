package com.sov.sofysmo.emptydaily;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import net.rdrei.android.dirchooser.DirectoryChooserConfig;
import net.rdrei.android.dirchooser.DirectoryChooserFragment;


public class DirChooserFragmentSample extends Activity implements DirectoryChooserFragment.OnFragmentInteractionListener {

    private DirectoryChooserFragment mDialog;
    private SharedPreferences.Editor edit;

    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_dirchooser);

        SharedPreferences pref= PreferenceManager.getDefaultSharedPreferences(this);
        edit=pref.edit();

        final DirectoryChooserConfig config = DirectoryChooserConfig.builder()
                .newDirectoryName("New Directory")
                .initialDirectory(pref.getString("path",""))
                .build();
        mDialog = DirectoryChooserFragment.newInstance(config);

        findViewById(R.id.btnChoose)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mDialog.show(getFragmentManager(), null);
                    }
                });
    }

    @Override
    public void onSelectDirectory(@NonNull final String path) {

        edit.putString("path", path);
        edit.apply();
        mDialog.dismiss();
    }

    @Override
    public void onCancelChooser() {
        mDialog.dismiss();
    }
}
