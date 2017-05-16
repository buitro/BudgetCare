package mobile.rojas.com.budgetcare;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by Diego on 24/4/17.
 */

public class BudgetCareApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Realm.init(this);

        RealmConfiguration configuration = new RealmConfiguration.Builder()
                .name("BudgetCare.realm")
                .build();

        Realm.setDefaultConfiguration(configuration);

    }
}
