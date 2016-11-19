package androidkejar.appkontak.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import androidkejar.appkontak.R;
import androidkejar.appkontak.database.RealmDB;
import androidkejar.appkontak.model.Kontak;

public class SaveActivity extends AppCompatActivity {
    private EditText edtNama,edtNumber;
    private int id;

    public static void start(Context context, int id) {
        Intent intent = new Intent(context, SaveActivity.class);
        intent.putExtra(SaveActivity.class.getSimpleName(), id);
        context.startActivity(intent);
    }
        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save);

            ActionBar actionBar = getSupportActionBar();
            if (actionBar != null) {
                actionBar.setDisplayHomeAsUpEnabled(true);
            }

            edtNama = (EditText) findViewById(R.id.edt_nama);
            edtNumber=(EditText)findViewById(R.id.edt_telp);

            id = getIntent().getExtras().getInt(SaveActivity.class.getSimpleName());

            if(id != 0){
                // get by id
                Kontak kontak = new RealmDB(this).getById(Kontak.class, id);
                edtNama.setText(String.valueOf(kontak.getNama()));
                edtNumber.setText(String.valueOf(kontak.getNumber()));
            }
    }
    // add kontak
    public void addKontak(String edtNama,String edtNumber) {
        Kontak kontak = new Kontak();
        kontak.setId((int) (System.currentTimeMillis()) / 1000);
        kontak.setNama(edtNama);
        kontak.setNumber(edtNumber);
        new RealmDB(this).add(kontak);
    }

    // update note
    public void updateKontak(int id, String edtNama ,String edtNumber){
        Kontak kontak = new Kontak();
        kontak.setId(id);
        kontak.setNama(edtNama);
        kontak.setNumber(edtNumber);
        new RealmDB(this).update(kontak);
    }

    // delete note
    public void deleteKontak(int id) {
        Toast.makeText(SaveActivity.this,"Data Berhasil Di Hapus",Toast.LENGTH_LONG).show();
        new RealmDB(this).delete(Kontak.class, id);
    }


    private void createOrUpdate(){

        if(!TextUtils.isEmpty(edtNama.getText().toString())&&!TextUtils.isEmpty(edtNumber.getText().toString())) {
            if (id == 0) {
                addKontak(edtNama.getText().toString(),edtNumber.getText().toString());
            } else {
                updateKontak(id, edtNama.getText().toString(),edtNumber.getText().toString());
            }
        }else{
            Toast.makeText(SaveActivity.this," Data Kontak Harus Di isi",Toast.LENGTH_LONG).show();
        }

        }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_save, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        switch (item.getItemId()) {
            case android.R.id.home:
                createOrUpdate();
                finish();
                return true;
            case R.id.save:
                createOrUpdate();
                finish();
                return true;
            case R.id.delete:
                if(id != 0) deleteKontak(id);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
}
