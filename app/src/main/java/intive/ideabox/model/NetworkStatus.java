package intive.ideabox.model;

import android.databinding.BaseObservable;
import android.databinding.ObservableField;


public class NetworkStatus  extends BaseObservable{

    public  ObservableField<Boolean> isConnected;
    public NetworkStatus(){
        isConnected = new ObservableField<>();
    }
}
