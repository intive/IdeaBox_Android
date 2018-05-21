package intive.ideabox.utility;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.lang.ref.WeakReference;

import io.reactivex.Emitter;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


public class RxBroadcastReceiver implements ObservableOnSubscribe<Intent>, Disposable {
    private final WeakReference<Context> contextWeakReference;

    private Emitter<? super Intent> emitter;
    private IntentFilter intentFilter;
    private BroadcastReceiver broadcastReceiver;

    private RxBroadcastReceiver(Context context, IntentFilter intentFilter) {
        contextWeakReference = new WeakReference<>(context.getApplicationContext());
        this.intentFilter = intentFilter;
        broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                emitter.onNext(intent);
            }
        };
    }

    public static Observable<Intent> create(Context context, IntentFilter intentFilter) {
        return Observable.defer(() -> Observable.create(new RxBroadcastReceiver(context, intentFilter))
                .subscribeOn(Schedulers.io())
        );
    }

    public static boolean isConnection(Context context) {
        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = cm.getActiveNetworkInfo();
        if (info != null && info.isConnected()) {
            boolean wifiConnected = info.getType() == ConnectivityManager.TYPE_WIFI;
            boolean mobileConnected = info.getType() == ConnectivityManager.TYPE_MOBILE;
            return wifiConnected || mobileConnected;

        } else {
            return false;
        }
    }

    @Override
    public void subscribe(ObservableEmitter<Intent> emitter) throws Exception {
        this.emitter = emitter;
        if (contextWeakReference != null && contextWeakReference.get() != null) {
            contextWeakReference.get().registerReceiver(broadcastReceiver, intentFilter);
        }
    }

    @Override
    public void dispose() {
        if (contextWeakReference != null && contextWeakReference.get() != null && broadcastReceiver != null) {
            contextWeakReference.get().unregisterReceiver(broadcastReceiver);

        }
        broadcastReceiver = null;
    }

    @Override
    public boolean isDisposed() {
        return broadcastReceiver == null;
    }
}
