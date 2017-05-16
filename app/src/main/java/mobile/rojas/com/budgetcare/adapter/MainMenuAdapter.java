package mobile.rojas.com.budgetcare.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import mobile.rojas.com.budgetcare.R;
import mobile.rojas.com.budgetcare.model.Menu;

/**
 * Created by Diego on 28/4/17.
 */
public class MainMenuAdapter extends RecyclerView.Adapter<MainMenuAdapter.MainMenuViewHolder> {
    private ArrayList<Menu> lista;
    private Context context;

    public MainMenuAdapter(ArrayList<Menu> lista, Context context) {
        this.lista = lista;
        this.context = context;
    }

    @Override
    public MainMenuViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v;
        v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_menu_main, parent, false);
        return new MainMenuAdapter.MainMenuViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MainMenuViewHolder holder, int position) {
        Menu menu = lista.get(position);
        holder.title.setText(menu.getName());
        holder.image.setImageResource(menu.getIcon());
        holder.total.setText(menu.getSum().toString());
        holder.menu = menu;
    }

    @Override
    public int getItemCount() {
        if (lista != null) {
            return lista.size();
        } else {
            return 0;
        }

    }

    public class MainMenuViewHolder extends RecyclerView.ViewHolder {
        private TextView title;
        private ImageView image;
        private TextView total;
        public Menu menu;

        public MainMenuViewHolder(final View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.menu_name);
            image = (ImageView) itemView.findViewById(R.id.menu_icon);
            total = (TextView) itemView.findViewById(R.id.menu_total);
            menu = new Menu();
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if(menu.getIntent()!=null){
                        v.getContext().startActivity(menu.getIntent());
                    }

                }
            });
        }

    }
}
