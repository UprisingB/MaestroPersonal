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
import com.example.maestropersonal.R;
import com.example.maestropersonal.clasesCargos.VerCargoActivity;
import com.example.maestropersonal.entidades.Cargos;
import com.example.maestropersonal.entidades.Paises;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LeerCargosAdapter extends RecyclerView.Adapter<LeerCargosAdapter.CargoViewHolder> {


    ArrayList<Cargos> leerCargos;
    ArrayList<Cargos> leerCargosOriginal;


    public LeerCargosAdapter(ArrayList<Cargos> leerCargos){
        this.leerCargos = leerCargos;
        leerCargosOriginal = new ArrayList<>();
        leerCargosOriginal.addAll(leerCargos);

    }


    @NonNull
    @Override
    public CargoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_item_cargo,null,false);
        return new CargoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CargoViewHolder holder, int position) {

        holder.viewIdCargo.setText(String.valueOf(leerCargos.get(position).getId()));
        holder.viewNombreCargo.setText(leerCargos.get(position).getNombre());
        holder.viewEstadoRegistroCargo.setText(leerCargos.get(position).getEstadoRegistro());

    }


    @SuppressLint("NotifyDataSetChanged")
    public void filtrado(String txtBuscar) {
        if (txtBuscar.isEmpty()) {
            leerCargos.clear();
            leerCargos.addAll(leerCargosOriginal);
        } else {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                List<Cargos> colleccionCargos = leerCargosOriginal.stream()
                        .filter(i ->
                                String.valueOf(i.getId()).toLowerCase().contains(txtBuscar.toLowerCase()) ||
                                        i.getNombre().toLowerCase().contains(txtBuscar.toLowerCase()) ||
                                        i.getEstadoRegistro().toLowerCase().contains(txtBuscar.toLowerCase()))
                        .collect(Collectors.toList());
                leerCargos.clear();
                leerCargos.addAll(colleccionCargos);
            } else {
                leerCargos.clear();
                for (Cargos c : leerCargosOriginal) {
                    if (String.valueOf(c.getId()).toLowerCase().contains(txtBuscar.toLowerCase()) ||
                            c.getNombre().toLowerCase().contains(txtBuscar.toLowerCase()) ||
                            c.getEstadoRegistro().toLowerCase().contains(txtBuscar.toLowerCase())) {
                        leerCargos.add(c);
                    }
                }
            }
        }
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return leerCargos.size();
    }

    public class CargoViewHolder extends RecyclerView.ViewHolder {
        TextView viewIdCargo,viewNombreCargo,viewEstadoRegistroCargo;
        public CargoViewHolder(@NonNull View itemView) {
            super(itemView);
            viewIdCargo = itemView.findViewById(R.id.viewIdCargo );
            viewNombreCargo  = itemView.findViewById(R.id.viewNombreCargo );
            viewEstadoRegistroCargo  = itemView.findViewById(R.id.viewEstadoRegistroCargo );



            itemView.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View view){
                    Context context = view.getContext();
                    Intent intent = new Intent(context, VerCargoActivity.class);
                    intent.putExtra("ID",leerCargos.get(getAdapterPosition()).getId());
                    context.startActivity(intent);

                }
            });
        }
    }
}
