package cpr.castelao.aplicacinbasica.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import cpr.castelao.aplicacinbasica.R;
import cpr.castelao.aplicacinbasica.listeners.ListAdapterListener;
import cpr.castelao.aplicacinbasica.listeners.PlatoAdapterListener;
import cpr.castelao.aplicacinbasica.model.Persona;
import cpr.castelao.aplicacinbasica.model.realm.Plato;
import cpr.castelao.aplicacinbasica.model.realm.TipoAlimento;

public class PlatoAdapter extends RecyclerView.Adapter<PlatoAdapter.PlatoHolder> {

    private static final int layout = R.layout.adapter_plato_item;
    private final PlatoAdapterListener listener;
    private final Activity act;
    private List<Plato> items = new ArrayList<Plato>();

    public PlatoAdapter(Activity act, List<Plato> items, PlatoAdapterListener listener) {
        this.act = act;
        this.items = items;
        this.listener = listener;
    }

    @NonNull
    @Override
    public PlatoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(layout, parent, false);

        return new PlatoHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull PlatoHolder holder, int position) {
        final Plato item = items.get(position);

        String texto = "";
        for (TipoAlimento tipo:  item.getAlimentos()){
            texto = texto + "\n"+tipo.getNombre();
        }

        holder.txt.setText(texto);

        holder.linRoot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (listener != null) {
                    listener.click(item);
                }
            }
        });

    }


    @Override
    public int getItemCount() {
        return items.size();
    }

    public class PlatoHolder extends RecyclerView.ViewHolder {
        public TextView txt;
        public ImageView icon;

        public LinearLayout linRoot;

        void initView(View v){
            txt = v.findViewById(R.id.plato_item_txt);
            icon = v.findViewById(R.id.plato_item_ico);
            linRoot = v.findViewById(R.id.plato_item_root_lin);
        }

        public PlatoHolder(View v) {
            super(v);
            initView(v);
        }
    }
}
