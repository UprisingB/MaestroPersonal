package com.example.maestropersonal.adaptadores;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.maestropersonal.R;
import com.example.maestropersonal.ClasesPaises.VerPaisActivity;
import com.example.maestropersonal.entidades.Paises;

import java.util.ArrayList;

public class LeerPaisesAdapter extends RecyclerView.Adapter<LeerPaisesAdapter.PaisViewHolder> {


    ArrayList<Paises> leerPaises;


    public LeerPaisesAdapter(ArrayList<Paises> leerPaises){
        this.leerPaises = leerPaises;

    }


    @NonNull
    @Override
    public PaisViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_item_pais,null,false);
        return new PaisViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PaisViewHolder holder, int position) {

        holder.viewIdPais.setText(String.valueOf(leerPaises.get(position).getId()));
        holder.viewNombrePais.setText(leerPaises.get(position).getNombre());
        holder.viewEstadoRegistroPais.setText(leerPaises.get(position).getEstadoRegistro());

    }

    @Override
    public int getItemCount() {
        return leerPaises.size();
    }

    public class PaisViewHolder extends RecyclerView.ViewHolder {
        TextView viewIdPais,viewNombrePais,viewEstadoRegistroPais;
        public PaisViewHolder(@NonNull View itemView) {
            super(itemView);
            viewIdPais = itemView.findViewById(R.id.viewIdPais );
            viewNombrePais = itemView.findViewById(R.id.viewNombrePais);
            viewEstadoRegistroPais = itemView.findViewById(R.id.viewEstadoRegistroPais);



            itemView.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View view){
                    Context context = view.getContext();
                    Intent intent = new Intent(context, VerPaisActivity.class);
                    intent.putExtra("ID",leerPaises.get(getAdapterPosition()).getId());
                    context.startActivity(intent);

                }
            });
        }
    }
}
