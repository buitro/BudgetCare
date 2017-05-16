package mobile.rojas.com.budgetcare.presenter;

import java.util.ArrayList;

import mobile.rojas.com.budgetcare.model.Menu;

public interface IMainPresenter {
    void loadAdapterMenu(ArrayList<Menu> list);
    ArrayList<Menu> getMenus();
}
