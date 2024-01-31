package com.example.maestropersonal.adaptadores;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.maestropersonal.ClasesPaises.LeerPaisActivity;
import com.example.maestropersonal.R;
import com.example.maestropersonal.ClasesPaises.VerPaisActivity;
import com.example.maestropersonal.entidades.Departamentos;
import com.example.maestropersonal.entidades.Paises;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LeerPaisesAdapter extends RecyclerView.Adapter<LeerPaisesAdapter.PaisViewHolder> {


    ArrayList<Paises> leerPaises;
    ArrayList<Paises> leerPaisesOriginal;


    public LeerPaisesAdapter(ArrayList<Paises> leerPaises){
        this.leerPaises = leerPaises;
        leerPaisesOriginal = new ArrayList<>();
        leerPaisesOriginal.addAll(leerPaises);

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

    @SuppressLint("NotifyDataSetChanged")
    public void filtrado(String txtBuscar) {
        if (txtBuscar.isEmpty()) {
            leerPaises.clear();
            leerPaises.addAll(leerPaisesOriginal);
        } else {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                List<Paises> colleccionPaises = leerPaisesOriginal.stream()
                        .filter(i ->
                                String.valueOf(i.getId()).toLowerCase().contains(txtBuscar.toLowerCase()) ||
                                        i.getNombre().toLowerCase().contains(txtBuscar.toLowerCase()) ||
                                        i.getEstadoRegistro().toLowerCase().contains(txtBuscar.toLowerCase()))
                        .collect(Collectors.toList());
                leerPaises.clear();
                leerPaises.addAll(colleccionPaises);
            } else {
                leerPaises.clear();
                for (Paises p : leerPaisesOriginal) {
                    if (String.valueOf(p.getId()).toLowerCase().contains(txtBuscar.toLowerCase()) ||
                            p.getNombre().toLowerCase().contains(txtBuscar.toLowerCase()) ||
                            p.getEstadoRegistro().toLowerCase().contains(txtBuscar.toLowerCase())) {
                        leerPaises.add(p);
                    }
                }
            }
        }
        notifyDataSetChanged();
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
