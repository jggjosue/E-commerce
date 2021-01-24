package com.app.ecommerce.v1.ui;

import android.os.Bundle;
import com.app.ecommerce.R;
import com.app.ecommerce.v1.entities.ListProduct;
import com.app.ecommerce.v1.entities.Product;
import com.app.ecommerce.v1.gateways.SyncAdapter;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView list;
    private AppCompatEditText seeker;
    private AdapterProduct adapterProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListProduct.INSTANCE().init();
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
        list.setLayoutManager(new GridLayoutManager(this, 2));
        adapterProduct = new AdapterProduct(this, ListProduct.INSTANCE().getListProduct());
        list.setAdapter(adapterProduct);
        seeker.setFocusable(true);
        seeker.setFocusableInTouchMode(true);
        seeker.requestFocus();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    public void filter(String text) {
        ArrayList<Product> filtrarLista = new ArrayList<>();
        for(Product product : ListProduct.INSTANCE().getListProduct()) {
            if(product.getTitle().toLowerCase().contains(text.toLowerCase()))
                filtrarLista.add(product);
        }
        adapterProduct.filter(filtrarLista);
    }

}