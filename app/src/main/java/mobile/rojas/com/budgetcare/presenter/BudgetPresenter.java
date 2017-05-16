package mobile.rojas.com.budgetcare.presenter;

import android.content.Context;

import mobile.rojas.com.budgetcare.view.IBudgetView;



public class BudgetPresenter implements IBudgetPresenter, IBudgetInteractor.OnListener{
    private Context context;
    private IBudgetView iBudgetView;
    private IBudgetInteractor iBudgetInteractor;

    public BudgetPresenter(Context context, IBudgetView iBudgetView) {
        this.context = context;
        this.iBudgetView = iBudgetView;
        this.iBudgetInteractor = new BudgetInteractor();
    }


    @Override
    public void save(String amount) {
        if (this.iBudgetView != null) {
            this.iBudgetInteractor.onSave(amount,this);
        }
    }

    @Override
    public void onAmount(int message) {
        if (this.iBudgetView != null) {
            this.iBudgetView.setAmount(this.context.getString(message));
        }
    }

    @Override
    public void onSuccess() {
        if ((this.iBudgetView != null)) {
            this.iBudgetView.navigateTo();
        }
    }
}
