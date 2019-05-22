package com.hyjz.hnovel.ireader.model.gen;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.hyjz.hnovel.ireader.model.bean.BookRecordBean;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "BOOK_RECORD_BEAN".
*/
public class BookRecordBeanDao extends AbstractDao<BookRecordBean, String> {

    public static final String TABLENAME = "BOOK_RECORD_BEAN";

    /**
     * Properties of entity BookRecordBean.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property BookId = new Property(0, String.class, "bookId", true, "BOOK_ID");
        public final static Property Chapter = new Property(1, int.class, "chapter", false, "CHAPTER");
        public final static Property PagePos = new Property(2, int.class, "pagePos", false, "PAGE_POS");
    }


    public BookRecordBeanDao(DaoConfig config) {
        super(config);
    }
    
    public BookRecordBeanDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"BOOK_RECORD_BEAN\" (" + //
                "\"BOOK_ID\" TEXT PRIMARY KEY NOT NULL ," + // 0: bookId
                "\"CHAPTER\" INTEGER NOT NULL ," + // 1: chapter
                "\"PAGE_POS\" INTEGER NOT NULL );"); // 2: pagePos
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"BOOK_RECORD_BEAN\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, BookRecordBean entity) {
        stmt.clearBindings();
 
        String bookId = entity.getBookId();
        if (bookId != null) {
            stmt.bindString(1, bookId);
        }
        stmt.bindLong(2, entity.getChapter());
        stmt.bindLong(3, entity.getPagePos());
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, BookRecordBean entity) {
        stmt.clearBindings();
 
        String bookId = entity.getBookId();
        if (bookId != null) {
            stmt.bindString(1, bookId);
        }
        stmt.bindLong(2, entity.getChapter());
        stmt.bindLong(3, entity.getPagePos());
    }

    @Override
    public String readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0);
    }    

    @Override
    public BookRecordBean readEntity(Cursor cursor, int offset) {
        BookRecordBean entity = new BookRecordBean( //
            cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0), // bookId
            cursor.getInt(offset + 1), // chapter
            cursor.getInt(offset + 2) // pagePos
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, BookRecordBean entity, int offset) {
        entity.setBookId(cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0));
        entity.setChapter(cursor.getInt(offset + 1));
        entity.setPagePos(cursor.getInt(offset + 2));
     }
    
    @Override
    protected final String updateKeyAfterInsert(BookRecordBean entity, long rowId) {
        return entity.getBookId();
    }
    
    @Override
    public String getKey(BookRecordBean entity) {
        if(entity != null) {
            return entity.getBookId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(BookRecordBean entity) {
        return entity.getBookId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
