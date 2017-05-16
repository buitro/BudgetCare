package mobile.rojas.com.budgetcare.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import mobile.rojas.com.budgetcare.R;
import mobile.rojas.com.budgetcare.model.Account;
import mobile.rojas.com.budgetcare.model.AccountType;

/**
 * Created by Diego on 28/4/17.
 */

public class ExpandableListAccount extends BaseExpandableListAdapter{
    private Context context;
    private ArrayList<AccountType> list;

    public ExpandableListAccount(Context context, ArrayList<AccountType> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getGroupCount() {
        if (list != null) {
            return list.size();
        } else {
            return 0;
        }

    }

    @Override
    public int getChildrenCount(int groupPosition) {
        if (list != null) {
            if (list.get(groupPosition) != null) {
                return this.list.get(groupPosition).getAccountsTemp().size();
            } else {
                return 0;
            }
        } else {
            return 0;
        }
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this.list.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return this.list.get(groupPosition).getAccountsTemp().get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        AccountType accountType = (AccountType) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.account_list_group, null);
        }

        TextView nameGroup = (TextView) convertView.findViewById(R.id.group_name);
        TextView balanceGroup = (TextView) convertView.findViewById(R.id.group_balance);
        nameGroup.setText(accountType.getName());
        balanceGroup.setText(String.valueOf(accountType.getSum()));
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        Account account = (Account) getChild(groupPosition,childPosition);

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.account_list_item, null);

        }
        TextView nameItem = (TextView) convertView.findViewById(R.id.expenses_item_name);
        TextView balanceItem = (TextView) convertView.findViewById(R.id.expenses_item_balance);
        nameItem.setText(account.getName());
        balanceItem.setText(String.valueOf(account.getSaldo()));
        return convertView;

    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
}
