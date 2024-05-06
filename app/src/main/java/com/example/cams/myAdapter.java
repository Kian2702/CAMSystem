package com.example.cams;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class myAdapter extends RecyclerView.Adapter<MyViewHolder> {
    private Context context;
    private List<EntryClass> datalist;

    public myAdapter(Context context, List<EntryClass> datalist) {
        this.context = context;
        this.datalist = datalist;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.recDEnc.setText(datalist.get(position).getDatEnc());
        holder.recCom.setText(datalist.get(position).getCom());

        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ReportDetails.class)
                        .putExtra("encNam", datalist.get(holder.getAdapterPosition()).getEncNam())
                        .putExtra("datEnc", datalist.get(holder.getAdapterPosition()).getDatEnc())
                        .putExtra("farNam", datalist.get(holder.getAdapterPosition()).getFarNam())
                        .putExtra("conNum", datalist.get(holder.getAdapterPosition()).getConNum())
                        .putExtra("birDay", datalist.get(holder.getAdapterPosition()).getBirDay())
                        .putExtra("genDer", datalist.get(holder.getAdapterPosition()).getGenDer())
                        .putExtra("farLoc", datalist.get(holder.getAdapterPosition()).getFarLoc())
                        .putExtra("totAre", datalist.get(holder.getAdapterPosition()).getTotAre())
                        .putExtra("com", datalist.get(holder.getAdapterPosition()).getCom())
                        .putExtra("typ", datalist.get(holder.getAdapterPosition()).getTyp())
                        .putExtra("vari", datalist.get(holder.getAdapterPosition()).getVari())
                        .putExtra("plaAre", datalist.get(holder.getAdapterPosition()).getPlaAre())
                        .putExtra("expYie", datalist.get(holder.getAdapterPosition()).getExpYie())
                        .putExtra("actYie", datalist.get(holder.getAdapterPosition()).getActYie())
                        .putExtra("perDam", datalist.get(holder.getAdapterPosition()).getPerDam())
                        .putExtra("datPla", datalist.get(holder.getAdapterPosition()).getDatPla())
                        .putExtra("expHar", datalist.get(holder.getAdapterPosition()).getExpHar())
                        .putExtra("actHar", datalist.get(holder.getAdapterPosition()).getActHar())
                        .putExtra("rem", datalist.get(holder.getAdapterPosition()).getRem())
                        .putExtra("Key", datalist.get(holder.getAdapterPosition()).getKey());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return datalist.size();
    }
}

class MyViewHolder extends RecyclerView.ViewHolder{

    TextView recDEnc, recCom, view;
    CardView Card;
    public MyViewHolder(@NonNull View itemView) {
        super(itemView);

        Card = itemView.findViewById(R.id.recCard);

        recDEnc = itemView.findViewById(R.id.date);
        recCom = itemView.findViewById(R.id.crop);
        view = itemView.findViewById(R.id.view);
    }
}