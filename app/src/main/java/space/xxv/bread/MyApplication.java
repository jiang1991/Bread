package space.xxv.bread;

import android.app.Application;

/**
 * Created by wangjiang on 2017/2/7.
 */

import io.realm.Realm;


public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Realm.init(this);
    }
}
