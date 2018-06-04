package intive.ideabox.utility;

import android.Manifest;
import android.annotation.SuppressLint;
import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.telephony.TelephonyManager;

import com.google.common.hash.Hashing;

import java.nio.charset.StandardCharsets;
import java.util.Objects;

/**
 * Created by Pc on 06.05.2018.
 */

public class UserDataUtils {
    public static final int MY_PERMISSIONS_REQUEST_READ_CONTACTS = 2;
    public static String imeiUser = "user";
    public static MutableLiveData<Boolean> isPermissionGranted = new MutableLiveData<>();
    public static String getHashedData(String data) {
        return Hashing.sha256()
                .hashString(data, StandardCharsets.UTF_8)
                .toString();

    }

@SuppressLint("HardwareIds")
public static void setUserAndCheckPermissions(FragmentActivity thisActivity){
        if (ContextCompat.checkSelfPermission(thisActivity,
                Manifest.permission.READ_PHONE_STATE)
                != PackageManager.PERMISSION_GRANTED) {

            if (!ActivityCompat.shouldShowRequestPermissionRationale(thisActivity,
                    Manifest.permission.READ_PHONE_STATE)) {
                        ActivityCompat.requestPermissions(thisActivity,
                                new String[]{Manifest.permission.READ_PHONE_STATE},
                                MY_PERMISSIONS_REQUEST_READ_CONTACTS);
                    }
        } else {
            final TelephonyManager ts =
                    (TelephonyManager) thisActivity.getSystemService(Context.TELEPHONY_SERVICE);
            imeiUser = getHashedData(Objects.requireNonNull(ts).getDeviceId());
        }
    }
    public static String getImeiUser(){
        return imeiUser;
    }

}
