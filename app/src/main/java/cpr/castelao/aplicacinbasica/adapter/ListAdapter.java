package cpr.castelao.aplicacinbasica.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
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
import cpr.castelao.aplicacinbasica.model.Persona;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.MyViewHolder> {

    private static final int layout = R.layout.adapter_list_item;
    private final ListAdapterListener listener;
    private final Activity act;
    private List<Persona> items = new ArrayList<Persona>();

    public ListAdapter(Activity act, List<Persona> items, ListAdapterListener listener) {
        this.act = act;
        this.items = items;
        this.listener = listener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(layout, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final Persona item = items.get(position);

        holder.txtTitle.setText(item.name);
        holder.txtSubtitle.setText(item.trabajo);

        holder.linRoot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (listener != null) {
                    listener.click(item);
                }
            }
        });

        if (holder.image != null){
            Glide.with(act)
                    .load(item.imagen)
                    .centerCrop()
                    .into(holder.image);
        }

    }


    @Override
    public int getItemCount() {
        return items.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView txtTitle, txtSubtitle;
        public ImageView image;

        public LinearLayout linRoot, linContent;

        void initView(View v){
            txtTitle =  v.findViewById(R.id.adapter_list_item_title_txt);
            txtSubtitle = v.findViewById(R.id.adapter_list_item_subtitle_txt);
            image = v.findViewById(R.id.adapter_list_item_img);
            linRoot = v.findViewById(R.id.adapter_list_item_root_lin);
            linContent = v.findViewById(R.id.adapter_list_item_content_lin);
        }

        public MyViewHolder(View v) {
            super(v);
            initView(v);
        }
    }
}
