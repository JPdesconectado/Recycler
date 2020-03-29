package com.example.eadrecycler;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class AlbumAdapter extends RecyclerView.Adapter {

    private List<Album> listaAlbuns;
    private int posicaoRemovidoRecentemente;
    private Album albumRemovidoRecentemente;
    private AppCompatActivity activity;

    public AlbumAdapter(AppCompatActivity activity) {

        this.listaAlbuns = new ArrayList<Album>();
        listaAlbuns.add(new Album(R.drawable.abbey_road,"Abbey Road","Rock",1969));
        listaAlbuns.add(new Album(R.drawable.queen_ii,"Queen II","Rock",1974));
        listaAlbuns.add(new Album(R.drawable.london_calling,"London Calling","Rock",1979));
        listaAlbuns.add(new Album(R.drawable.back_in_black,"Back in Black","Rock",1980));
        listaAlbuns.add(new Album(R.drawable.appetite_for_destruction,"Appetite for Destruction","Rock",1987));
        listaAlbuns.add(new Album(R.drawable.stadium_arcadium,"Stadium Arcadium","Rock",2006));
        listaAlbuns.add(new Album(R.drawable.trench,"Trench","Rock",2018));
        this.activity = activity;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item, parent, false);
        AlbumViewHolder viewHolder = new AlbumViewHolder(view);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, int position) {
        AlbumViewHolder viewHolder = (AlbumViewHolder) holder;
        viewHolder.capaImagemView.setImageResource(listaAlbuns.get(position).getImgCapa());
        viewHolder.tituloTextView.setText(listaAlbuns.get(position).getTitulo());
        viewHolder.generoTextView.setText(listaAlbuns.get(position).getGenero());
        viewHolder.anoTextView.setText(String.valueOf(listaAlbuns.get(position).getAno()));
        holder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity.getBaseContext(), EditarAlbumActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("album", listaAlbuns.get(holder.getAdapterPosition()));
                bundle.putInt("position", holder.getAdapterPosition());
                bundle.putInt("request_code",MainActivity.REQUEST_EDITAR_ALBUM);
                intent.putExtras(bundle);
                activity.startActivityForResult(intent, MainActivity.REQUEST_EDITAR_ALBUM);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listaAlbuns.size();
    }

    public void remover(int position){
        posicaoRemovidoRecentemente = position;
        albumRemovidoRecentemente = listaAlbuns.get(position);
        listaAlbuns.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, this.getItemCount());

        Snackbar snackbar = Snackbar.make(activity.findViewById(R.id.constraintLayout), "Item deletado",Snackbar.LENGTH_LONG);
        snackbar.setAction("Desfazer?", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listaAlbuns.add(posicaoRemovidoRecentemente, albumRemovidoRecentemente);
                notifyItemInserted(posicaoRemovidoRecentemente);
            }
        });
        snackbar.show();
    }

    public void inserir(Album album){
        listaAlbuns.add(album);
        notifyItemInserted(getItemCount());
    }

    public void mover(int fromPosition, int toPosition){
        if (fromPosition < toPosition)
            for (int i = fromPosition; i < toPosition; i++)
                Collections.swap(listaAlbuns, i, i+1);
        else
            for (int i = fromPosition; i > toPosition; i--)
                Collections.swap(listaAlbuns, i, i-1);
        notifyItemMoved(fromPosition,toPosition);
    }

    public void editar(Album album, int position){
        listaAlbuns.get(position).setTitulo(album.getTitulo());
        listaAlbuns.get(position).setGenero(album.getGenero());
        listaAlbuns.get(position).setAno(album.getAno());
        notifyItemChanged(position);
    }

    public static class AlbumViewHolder extends RecyclerView.ViewHolder{

        ImageView capaImagemView;
        TextView tituloTextView;
        TextView generoTextView;
        TextView anoTextView;

        public AlbumViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setTag(this);
            capaImagemView = itemView.findViewById(R.id.capaImageView);
            tituloTextView = itemView.findViewById(R.id.tituloTextView);
            generoTextView = itemView.findViewById(R.id.generoTextView);
            anoTextView = itemView.findViewById(R.id.anoTextView);
        }
    }
}
