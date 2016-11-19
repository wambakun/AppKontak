package androidkejar.appkontak;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import androidkejar.appkontak.activity.SaveActivity;
import androidkejar.appkontak.adapter.KontakListAdapter;
import androidkejar.appkontak.database.RealmDB;
import androidkejar.appkontak.listener.RecyclerItemClickListener;
import androidkejar.appkontak.model.Kontak;
import androidkejar.appkontak.util.DividerItemDecoration;
import io.realm.RealmResults;
import io.realm.Sort;

public class MainActivity extends AppCompatActivity implements RecyclerItemClickListener {
    private RecyclerView rvKontak;
    private Button btnAdd;

    private KontakListAdapter kontakListAdapter;
    private LinearLayoutManager linearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rvKontak = (RecyclerView) findViewById(R.id.rv_kontak);
        btnAdd = (Button) findViewById(R.id.btn_tambah);

        kontakListAdapter = new KontakListAdapter(this);
        linearLayoutManager = new LinearLayoutManager(this);

        rvKontak.setLayoutManager(linearLayoutManager);
        rvKontak.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        rvKontak.setAdapter(kontakListAdapter);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SaveActivity.start(MainActivity.this, 0);
            }
        });
    }
    @Override
    protected void onResume() {
        super.onResume();
        loadData();
    }
    private void loadData(){
        if(retrieve() != null)
            kontakListAdapter.setKontakList(retrieve());
    }
    public RealmResults<Kontak> retrieve() {
        RealmResults<Kontak> result = (RealmResults<Kontak>) new RealmDB(this).getAllData(Kontak.class);
        result.sort("number", Sort.DESCENDING);
        return result;
    }


    @Override
    public void itemClick(int position, View view) {
        SaveActivity.start(this, kontakListAdapter.getItem(position).getId());
    }
}
