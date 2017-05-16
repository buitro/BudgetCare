package mobile.rojas.com.budgetcare.view;


import java.util.ArrayList;

import io.realm.RealmResults;
import mobile.rojas.com.budgetcare.model.Menu;

/**
 * Created by Diego on 23/4/17.
 */
public interface IMainView {

    void setAdapterMenu(ArrayList<Menu> list);
}
