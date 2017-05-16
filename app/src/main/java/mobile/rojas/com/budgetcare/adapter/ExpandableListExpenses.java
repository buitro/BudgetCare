package mobile.rojas.com.budgetcare.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import mobile.rojas.com.budgetcare.R;
import mobile.rojas.com.budgetcare.model.ExpensesCategory;
import mobile.rojas.com.budgetcare.model.Record;

/**
 * Created by Diego on 29/4/17.
 */

public class ExpandableListExpenses extends BaseExpandableListAdapter {
    private Context context;
    private ArrayList<ExpensesCategory> list;
    public ExpandableListExpenses(Context context, ArrayList<ExpensesCategory> list) {
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
                return this.list.get(groupPosition).getExpenses().size();
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
        return this.list.get(groupPosition).getExpenses().get(childPosition);
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
        ExpensesCategory accountType = (ExpensesCategory) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.expenses_list_group, null);
        }

        TextView nameGroup = (TextView) convertView.findViewById(R.id.expenses_group_name);
        TextView balanceGroup = (TextView) convertView.findViewById(R.id.expenses_group_balance);
        nameGroup.setText(accountType.getName());
        balanceGroup.setText(String.valueOf(accountType.getSum()));
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        Record expenses = (Record) getChild(groupPosition,childPosition);

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.expenses_list_item, null);

        }
        TextView nameItem = (TextView) convertView.findViewById(R.id.expenses_item_name);
        TextView balanceItem = (TextView) convertView.findViewById(R.id.expenses_item_balance);
        TextView descriptionItem = (TextView) convertView.findViewById(R.id.expenses_item_description);
        nameItem.setText(expenses.getExpensesSubcategory().getName());
        balanceItem.setText(String.valueOf(expenses.getAmount()));
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("d/M/yyyy");
        descriptionItem.setText( simpleDateFormat.format(expenses.getDate()) + " " +expenses.getDescription());
        return convertView;

    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }
}
