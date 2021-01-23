package com.app.ecommerce.v1.ui;

import android.os.Bundle;
import com.app.ecommerce.R;
import com.app.ecommerce.v1.entities.Product;
import com.app.ecommerce.v1.gateways.SyncAdapter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private AppCompatEditText seeker;
    private RecyclerView list;
    private AdapterProduct adapterProduct;
    private List<Product> listProduct;
    private final int THUMBSIZE = 64;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        Bitmap ThumbImage = ThumbnailUtils.extractThumbnail(BitmapFactory.decodeFile(""), THUMBSIZE, THUMBSIZE);

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                new SyncAdapter(MainActivity.this, true);
            }
        });

        seeker = findViewById(R.id.seeker);
        seeker.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());
            }
        });
        list = findViewById(R.id.list);
        list.setLayoutManager(new GridLayoutManager(this, 1));
        listProduct = new ArrayList<>();
        listProduct.add(new Product("English", "12.50", R.drawable.ic_launcher_background));
        listProduct.add(new Product("English", "125.30", R.drawable.ic_launcher_background));
        listProduct.add(new Product("English", "90.00", R.drawable.ic_launcher_background));
        listProduct.add(new Product("English", "123", R.drawable.ic_launcher_background));
        listProduct.add(new Product("English", "434.90", R.drawable.ic_launcher_background));
        adapterProduct = new AdapterProduct(this, listProduct);
        list.setAdapter(adapterProduct);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    public void filter(String texto) {
        ArrayList<Product> filtrarLista = new ArrayList<>();
        for(Product product : listProduct) {
            if(product.getTitle().toLowerCase().contains(texto.toLowerCase()))
                filtrarLista.add(product);
        }
        adapterProduct.filtrar(filtrarLista);
    }

}