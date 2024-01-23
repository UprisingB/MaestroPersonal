package com.example.maestropersonal.adaptadores;

import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.view.menu.MenuView;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

import com.example.maestropersonal.LeerDepartamentoActivity;
import com.example.maestropersonal.MainActivity;
import com.example.maestropersonal.R;
import com.example.maestropersonal.VerDepartamentoActivity;
import com.example.maestropersonal.entidades.Departamentos;

import java.util.ArrayList;

public class LeerDepartamentosAdapter extends RecyclerView.Adapter<LeerDepartamentosAdapter.DepartamentoViewHolder> {


    ArrayList<Departamentos> leerDepartamentos;


    public LeerDepartamentosAdapter(ArrayList<Departamentos> leerDepartamentos){
        this.leerDepartamentos = leerDepartamentos;

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







      /*      USAR BOTON Y SEL
      btnActualizarDepartamento.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
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
            }); */



        }
    }
}
