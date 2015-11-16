package com.offsec.nethunter;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

public class KaliLauncherFragment extends Fragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";
    private static final String TAG = "KaliLauncherFragment";
    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */


    public KaliLauncherFragment() {

    }

    public static KaliLauncherFragment newInstance(int sectionNumber) {
        KaliLauncherFragment fragment = new KaliLauncherFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.kali_launcher, container, false);
        addClickListener(R.id.button_start_kali, new View.OnClickListener() {
            public void onClick(View v) {
                intentClickListener(new BootKali("").GET_KALI_SHELL_CMD());
            }
        }, rootView);
        /**
         * Launch Kali menu
         */
        addClickListener(R.id.button_start_kalimenu, new View.OnClickListener() {
            public void onClick(View v) {
                intentClickListener(new BootKali("kalimenu").GET_TERM_CMD());
            }
        }, rootView);
        /**
         * Update Kali chroot
         */
        addClickListener(R.id.update_kali_chroot, new View.OnClickListener() {
            public void onClick(View v) {
                intentClickListener("bootkali update");
            }
        }, rootView);
        /**
         * Launch Wifite
         */
        addClickListener(R.id.button_launch_wifite, new View.OnClickListener() {
            public void onClick(View v) {
                intentClickListener("bootkali wifite");
            }
        }, rootView);
        /**
         * Turn off external wifi
         */
        addClickListener(R.id.turn_off_external_wifi, new View.OnClickListener() {
            public void onClick(View v) {
                intentClickListener("bootkali wifi-disable");
            }
        }, rootView);
        /**
         * Turn off external wifi
         */
        addClickListener(R.id.kali_dumpmifare, new View.OnClickListener() {
            public void onClick(View v) {
                intentClickListener("bootkali dumpmifare");
            }
        }, rootView);

        return rootView;
    }

    public void getTerminalApp() {
        try {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=jackpal.androidterm")));
        } catch (android.content.ActivityNotFoundException anfe2) {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=jackpal.androidterm")));
        }
    }

    private void addClickListener(int buttonId, View.OnClickListener onClickListener, View rootView) {
        rootView.findViewById(buttonId).setOnClickListener(onClickListener);
    }

    private void intentClickListener(final String command) {
        try {
            Intent intent =
                    new Intent("jackpal.androidterm.RUN_SCRIPT");
            intent.addCategory(Intent.CATEGORY_DEFAULT);

            intent.putExtra("jackpal.androidterm.iInitialCommand", command);
            startActivity(intent);
        } catch (Exception e) {
            Toast.makeText(getActivity().getApplicationContext(), getString(R.string.toast_install_terminal), Toast.LENGTH_SHORT).show();
            getTerminalApp();
        }
    }

}
