package androidkejar.appkontak.application;

import android.app.Application;

import androidkejar.appkontak.database.RealmDB;
import io.realm.DynamicRealm;
import io.realm.RealmMigration;
import io.realm.RealmSchema;

/**
 * Created by Kirito on 19/11/2016.
 */

public class AppKontak extends Application {
    public void onCreate() {
        super.onCreate();

        RealmDB realmDB = new RealmDB(this);
        realmDB.setMigration(new DataMigration());
    }

    private class DataMigration implements RealmMigration {
        @Override
        public void migrate(DynamicRealm realm, long oldVersion, long newVersion) {

            //Mengambil schema
            RealmSchema schema = realm.getSchema();

            //membuat schema baru jika versi 0
            if (oldVersion == 0) {
                schema.create("Kontak")
                        .addField("id", int.class)

                        .addField("nama", String.class)
                        .addField("number", String.class);
                oldVersion++;
            }

        }
    }
}
