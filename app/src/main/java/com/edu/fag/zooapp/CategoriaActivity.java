package com.edu.fag.zooapp;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.edu.fag.zooapp.models.Categoria;

import java.util.List;

public class CategoriaActivity extends AppCompatActivity {

    private EditText etCode, etDesc;
    private Button btSave, btCancel;
    private ArrayAdapter<Categoria> categoriaAdapter;
    private ListView lvCategories;
    private Categoria categoria = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categoria);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        loadComponents();
        loadList();
    }

    private void loadComponents() {
        btSave = findViewById(R.id.btSave);
        btCancel = findViewById(R.id.btCancel);
        etCode = findViewById(R.id.etCode);
        etDesc = findViewById(R.id.etDesc);
        lvCategories = findViewById(R.id.lvCategories);
        loadEvents();
        initCategory();
    }

    private void initCategory() {
        Categoria last = Categoria.last(Categoria.class);
        //Last pega o ultimo registro e Soma um
        int code = last != null ? last.getCodigo()+1 : 1;
        etCode.setText(String.valueOf(code));
    }

    private void loadEvents() {
        btSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save();
            }
        });
        lvCategories.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                categoria = categoriaAdapter.getItem(position);
                btSave.setText(R.string.lbUpdate);
                setFields();
            }
        });


        lvCategories.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                final Categoria catDel = categoriaAdapter.getItem(position);
                AlertDialog.Builder alertConfirmacao = new AlertDialog.Builder(CategoriaActivity.this);
                alertConfirmacao.setTitle("Confirmação Exclusão");
                alertConfirmacao.setMessage("Deseja Realmente excluir o Registro ???");
                alertConfirmacao.setNeutralButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        catDel.delete();
                        loadList();
                    }
                });

                alertConfirmacao.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                alertConfirmacao.show();

                return true;
            }
        });
    }

    private void setFields() {
        etCode.setText(String.valueOf(categoria.getCodigo()));
        etDesc.setText(categoria.getDescricao());
    }
    //android:windowSoftInputMode="stateHidden"
    private void save() {
        if (categoria != null){ // edição
            categoria.setDescricao(etDesc.getText().toString());
            categoria.setCodigo(Integer.parseInt(etCode.getText().toString()));
            categoria.update();
            categoria = null;
            btSave.setText(R.string.lbSave);
        }else {
            categoria = new Categoria();
            categoria.setDescricao(etDesc.getText().toString());
            categoria.setCodigo(Integer.parseInt(etCode.getText().toString()));
            categoria.save();//Metodo responsável por salvar o registro
        }
        loadList();
        clearFields();
    }

    private void clearFields() {
        etDesc.setText("");
        initCategory();
    }

    private void loadList(){
        List<Categoria> categorias = Categoria.listAll(Categoria.class, "codigo desc");
        categoriaAdapter = new ArrayAdapter<>(CategoriaActivity.this, R.layout.support_simple_spinner_dropdown_item,
                categorias);
        lvCategories.setAdapter(categoriaAdapter);
    }

}
