package com.edu.fag.zooapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.edu.fag.zooapp.R;
import com.edu.fag.zooapp.models.Animal;

import java.util.List;

public class AnimalAdapter extends BaseAdapter {
    LayoutInflater myInflater;
    List<Animal> animalList;

    public AnimalAdapter(Context context, List<Animal> animalList) {
        this.animalList = animalList;
        myInflater = LayoutInflater.from(context);//Responsável por inflar o Layout
    }

    @Override
    public int getCount() {
        return animalList.size();
    }

    @Override
    public Object getItem(int position) {
        return animalList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
    //Popula a View passada com os parâmetros do Objeto
    @Override
    public View getView(int position, View view, ViewGroup parent) {
        Animal animal = animalList.get(position);//Recupero o Animal da posição passada
        view = myInflater.inflate(R.layout.item_animal, null);//Seto o Layout escolhido na VIEW
        ((TextView) view.findViewById(R.id.tvCodigo)).setText(String.valueOf(animal.getCodigo()));
        ((TextView) view.findViewById(R.id.tvDesc)).setText(animal.getDescricao());
        ((TextView) view.findViewById(R.id.tvCat)).setText(animal.getCategoria().getDescricao());
        return view;
    }
}
