package com.example.maestropersonal.adaptadores;

import android.annotation.SuppressLint;
import android.app.Person;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.maestropersonal.R;
import com.example.maestropersonal.ClasesPersonal.VerPersonalActivity;
import com.example.maestropersonal.entidades.Cargos;
import com.example.maestropersonal.entidades.Personales;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LeerPersonalesAdapter extends RecyclerView.Adapter<LeerPersonalesAdapter.PersonalViewHolder> {


    ArrayList<Personales> leerPersonales;
    ArrayList<Personales> leerPersonalesOriginal;


    public LeerPersonalesAdapter(ArrayList<Personales> leerPersonales){
        this.leerPersonales = leerPersonales;
        leerPersonalesOriginal = new ArrayList<>();
        leerPersonalesOriginal.addAll(leerPersonales);

    }


    @NonNull
    @Override
    public PersonalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_item_personal,null,false);
        return new PersonalViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PersonalViewHolder holder, int position) {

        holder.viewIdPersonal.setText(String.valueOf(leerPersonales.get(position).getId()));
        holder.viewNombrePersonal.setText(leerPersonales.get(position).getNombre());
        holder.viewEstadoRegistroPersonal.setText(leerPersonales.get(position).getEstadoRegistro());

    }

    @SuppressLint("NotifyDataSetChanged")
    public void filtrado(String txtBuscar) {
        if (txtBuscar.isEmpty()) {
            leerPersonales.clear();
            leerPersonales.addAll(leerPersonalesOriginal);
        } else {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                List<Personales> colleccionPersonales = leerPersonalesOriginal.stream()
                        .filter(i ->
                                String.valueOf(i.getId()).toLowerCase().contains(txtBuscar.toLowerCase()) ||
                                        i.getNombre().toLowerCase().contains(txtBuscar.toLowerCase()) ||
                                        i.getEstadoRegistro().toLowerCase().contains(txtBuscar.toLowerCase()))
                        .collect(Collectors.toList());
                leerPersonales.clear();
                leerPersonales.addAll(colleccionPersonales);
            } else {
                leerPersonales.clear();
                for (Personales per : leerPersonalesOriginal) {
                    if (String.valueOf(per.getId()).toLowerCase().contains(txtBuscar.toLowerCase()) ||
                            per.getNombre().toLowerCase().contains(txtBuscar.toLowerCase()) ||
                            per.getEstadoRegistro().toLowerCase().contains(txtBuscar.toLowerCase())) {
                        leerPersonales.add(per);
                    }
                }
            }
        }
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return leerPersonales.size();
    }

    public class PersonalViewHolder extends RecyclerView.ViewHolder {
        TextView viewIdPersonal,viewNombrePersonal,viewEstadoRegistroPersonal;
        public PersonalViewHolder(@NonNull View itemView) {
            super(itemView);
            viewIdPersonal = itemView.findViewById(R.id.viewIdPersonal );
            viewNombrePersonal  = itemView.findViewById(R.id.viewNombrePersonal );
            viewEstadoRegistroPersonal  = itemView.findViewById(R.id.viewEstadoRegistroPersonal );



            itemView.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View view){
                    Context context = view.getContext();
                    Intent intent = new Intent(context, VerPersonalActivity.class);
                    intent.putExtra("ID",leerPersonales.get(getAdapterPosition()).getId());
                    context.startActivity(intent);

                }
            });
        }
    }
}
