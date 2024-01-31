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
import com.example.maestropersonal.clasesDepartamentos.VerDepartamentoActivity;
import com.example.maestropersonal.entidades.Departamentos;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LeerDepartamentosAdapter extends RecyclerView.Adapter<LeerDepartamentosAdapter.DepartamentoViewHolder> {


    ArrayList<Departamentos> leerDepartamentos;
    ArrayList<Departamentos> leerDepartamentosOriginal;


    public LeerDepartamentosAdapter(ArrayList<Departamentos> leerDepartamentos){
        this.leerDepartamentos = leerDepartamentos;
        leerDepartamentosOriginal = new ArrayList<>();
        leerDepartamentosOriginal.addAll(leerDepartamentos);



    }


    @NonNull
    @Override
    public DepartamentoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_item_departamento,null,false);
        return new DepartamentoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DepartamentoViewHolder holder, int position) {

        holder.viewIdDepartamento.setText(String.valueOf(leerDepartamentos.get(position).getId()));
        holder.viewNombreDepartamento.setText(leerDepartamentos.get(position).getNombre());
        holder.viewEstadoRegistroDepartamento.setText(leerDepartamentos.get(position).getEstadoRegistro());

    }

    @SuppressLint("NotifyDataSetChanged")
    public void filtrado(String txtBuscar) {
        if (txtBuscar.isEmpty()) {
            leerDepartamentos.clear();
            leerDepartamentos.addAll(leerDepartamentosOriginal);
        } else {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                List<Departamentos> colleccionDepartamentos = leerDepartamentosOriginal.stream()
                        .filter(i ->
                                String.valueOf(i.getId()).toLowerCase().contains(txtBuscar.toLowerCase()) ||
                                        i.getNombre().toLowerCase().contains(txtBuscar.toLowerCase()) ||
                                        i.getEstadoRegistro().toLowerCase().contains(txtBuscar.toLowerCase()))
                        .collect(Collectors.toList());
                leerDepartamentos.clear();
                leerDepartamentos.addAll(colleccionDepartamentos);
            } else {
                leerDepartamentos.clear();
                for (Departamentos d : leerDepartamentosOriginal) {
                    if (String.valueOf(d.getId()).toLowerCase().contains(txtBuscar.toLowerCase()) ||
                            d.getNombre().toLowerCase().contains(txtBuscar.toLowerCase()) ||
                            d.getEstadoRegistro().toLowerCase().contains(txtBuscar.toLowerCase())) {
                        leerDepartamentos.add(d);
                    }
                }
            }
        }
        notifyDataSetChanged();
    }




    @Override
    public int getItemCount() {
        return leerDepartamentos.size();
    }

    public class DepartamentoViewHolder extends RecyclerView.ViewHolder {
        TextView viewIdDepartamento,viewNombreDepartamento,viewEstadoRegistroDepartamento;
        public DepartamentoViewHolder(@NonNull View itemView) {
            super(itemView);
            viewIdDepartamento = itemView.findViewById(R.id.viewIdDepartamento);
            viewNombreDepartamento = itemView.findViewById(R.id.viewNombreDepartamento);
            viewEstadoRegistroDepartamento = itemView.findViewById(R.id.viewEstadoRegistroDepartamento);



            itemView.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View view){
                    Context context = view.getContext();
                    Intent intent = new Intent(context, VerDepartamentoActivity.class);
                    intent.putExtra("ID",leerDepartamentos.get(getAdapterPosition()).getId());
                    context.startActivity(intent);

                }
            });
        }
    }
}
