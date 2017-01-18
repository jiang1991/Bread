package space.xxv.bread;

import android.app.Application;

import org.greenrobot.greendao.database.Database;
import space.xxv.bread.DaoMaster.DevOpenHelper;

/**
 * Created by wangjiang on 2017/1/18.
 */

public class App extends Application {
    public static final boolean ENCRYPTED = true;

    private DaoSession daoSession;

    @Override
    public void onCreate() {
        super.onCreate();

        DevOpenHelper helper = new DevOpenHelper(this, ENCRYPTED ? "notes-encrypted" : "notes");
        Database db = ENCRYPTED ? helper.getEncryptedWritableDb("secret") : helper.getWritableDb();
        daoSession = new DaoMaster(db).newSession();
    }

    public DaoSession getDaoSession() {
        return daoSession;
    }
}
