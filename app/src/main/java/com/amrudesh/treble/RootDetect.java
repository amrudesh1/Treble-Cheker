package com.amrudesh.treble;

import android.os.Build;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

public class RootDetect {


    public boolean FindRoot() {
        return TestKeysChecker() || SUBinFinder() || SURuntimeCheck();
    }

    public boolean TestKeysChecker() {
        String keys = Build.TAGS;
        if (keys != null && keys.contains("test-keys")) {
            return true;
        } else {
            return false;
        }
    }
    public boolean SUBinFinder() {
        String[] paths = {"/system/app/Superuser.apk",
                "/system/priv-app/Superuser.apk",
                "/system/priv-app/superuser.apk",
                "/system/app/superuser.apk",
                "/sbin/su",
                "/system/bin/su",
                "/system/xbin/su",
                "/data/local/xbin/su",
                "/data/local/bin/su",
                "/system/sd/xbin/su",
                "/system/bin/failsafe/su",
                "/data/local/su",
                "/su/bin/su"
        };

        int dircount = 0;



        for (String path : paths) {

            if (new File(path).exists()) {
                dircount++;
            }
        }

        if (dircount > 0) {
            return true;

        } else {
            return false;
        }

    }
    public boolean SURuntimeCheck() {
        Process process = null;
        try {
            process = Runtime.getRuntime().exec(new String[]{"/system/xbin/which", "su"});
            BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()));
            if (in.readLine() != null) {
                return true;
            }
            }
            catch (Throwable t) {

        } finally {
            if (process != null) process.destroy();
        }
        return false;
    }
}
