package com.vacuum.app.metquiz.Utils;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.arch.persistence.room.migration.Migration;

import com.vacuum.app.metquiz.MainActivity;
import com.vacuum.app.metquiz.Model.Product;

/**
 * Created by gonzalo on 7/14/17
 */

@Database(entities = {Product.class}, version = 2)
@TypeConverters({DateTypeConverter.class})
public abstract class MyDatabase extends RoomDatabase {
    public abstract ProductDao productDao();

    public static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE product "
                    + " ADD COLUMN total_correct_answers INTEGER");

            // enable flag to force update products
            MainActivity.get().setForceUpdate(true);
        }
    };
}
