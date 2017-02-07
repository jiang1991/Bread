package space.xxv.bread;

import android.app.Application;

/**
 * Created by wangjiang on 2017/2/7.
 */

import io.realm.Realm;
import io.realm.RealmConfiguration;


public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Realm.init(this);
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder().build();
        Realm.deleteRealm(realmConfiguration);
        Realm.setDefaultConfiguration(realmConfiguration);
    }
}
