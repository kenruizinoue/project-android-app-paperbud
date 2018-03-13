package com.apps.yecotec.paperbud.data.room;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.db.SupportSQLiteOpenHelper;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.DatabaseConfiguration;
import android.arch.persistence.room.InvalidationTracker;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.migration.Migration;
import android.content.Context;

import com.apps.yecotec.paperbud.data.room.daos.NewsDao;
import com.apps.yecotec.paperbud.data.room.entities.News;

/**
 * Created by kenruizinoue on 12/1/17.
 */

@Database(entities = {News.class}, version = 2)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase INSTANCE;

    public abstract NewsDao newsDao();

    static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
// Since we didn't alter the table, there's nothing else to do here.
        }
    };

    public static AppDatabase getAppDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE =
                    Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "database-news")
                            // allow queries on the main thread.
                            // Don't do this on a real app! See PersistenceBasicSample for an example.
                            .allowMainThreadQueries()
                            .addMigrations(MIGRATION_1_2)
                            .build();
        }
        return INSTANCE;
    }

    public static AppDatabase createTestDB(Context context) {
        RoomDatabase.Builder<AppDatabase> db;
        db = Room.inMemoryDatabaseBuilder(context.getApplicationContext(), AppDatabase.class);

        return db.build();
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }
}
