package mobile.rojas.com.budgetcare.presenter;



public interface IBudgetInteractor {
    interface OnListener{
        void onAmount(int message);
        void onSuccess();
    }
    void onSave(String amount, OnListener listener);
}
