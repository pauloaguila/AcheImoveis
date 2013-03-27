package com.trabalho.acheimoveis.model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import android.content.Context;

import com.j256.ormlite.dao.GenericRawResults;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.SelectArg;
import com.j256.ormlite.stmt.Where;
import com.trabalho.acheimoveis.controller.ApplicationControllerWrapper;

public class DataBaseControlModel {

    // Singleton model
    private static DataBaseControlModel instance;

    // Object DB of bible
    private DataBaseControlHelper dbControlHelper;

    private DataBaseControlModel(Context ctx) {
        dbControlHelper = new DataBaseControlHelper(ctx);
    }

    public DataBaseControlHelper getDbControlHelper() {
        return dbControlHelper;
    }

    public void setDbControlHelper(DataBaseControlHelper dbControlHelper) {
        this.dbControlHelper = dbControlHelper;
    }

    static public DataBaseControlModel getInstance(Context ctx) {
        if (null == instance) {
            instance = new DataBaseControlModel(ctx);
        }
        return instance;
    }
/*
    public List<Book> getAllBookLists() {

        List<Book> bookLists = null;

        try {

            bookLists = getDbControlHelper().getModelBookDAO().queryForAll();

        } catch(java.sql.SQLException e) {
            e.printStackTrace();
            bookLists = new ArrayList<Book>();
        }

        return bookLists;
    }

    public void addBook(Book book) {
        try {

            getDbControlHelper().getModelBookDAO().create(book);

        } catch(java.sql.SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Favorite> getAllFavoriteList() {

        List<Favorite> favoriteList = null;

        try {

            // favoriteList =
            // getDbControlHelper().getModelFavoriteDAO().queryForAll();
            favoriteList = getDbControlHelper().getModelFavoriteDAO().queryBuilder()
                .orderBy("verse_id", true)
                .query();

        } catch(java.sql.SQLException e) {
            e.printStackTrace();
            favoriteList = new ArrayList<Favorite>();
        }

        return favoriteList;
    }

    public void addFavorite(Favorite favorite) {
        try {

            Verse verse = favorite.getVerse();
            verse.setFavorite(1);

            getDbControlHelper().getModelVerseDAO().update(verse);

            getDbControlHelper().getModelFavoriteDAO().create(favorite);

        } catch(java.sql.SQLException e) {
            e.printStackTrace();
        }
    }

    public void addFavorite(List<Favorite> favorites) {

        for(Favorite favorite : favorites) {
            addFavorite(favorite);
        }
    }

    public void removeFavorite(Favorite favorite) {

        try {

            Verse verse = getDbControlHelper().getModelVerseDAO().queryForId(
                favorite.getVerse().getId());
            verse.setFavorite(0);
            getDbControlHelper().getModelVerseDAO().update(verse);

            getDbControlHelper().getModelFavoriteDAO().delete(favorite);

        } catch(java.sql.SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeFavorite(List<Favorite> favorites) {

        for(Favorite favorite : favorites) {
            removeFavorite(favorite);
        }

    }

    public List<Verse> getAllVersesLimit(long maxRows, long startRow) {
        List<Verse> verses = null;
        try {
            verses = getDbControlHelper().getModelVerseDAO().queryBuilder()
                .limit(maxRows).offset(startRow).orderBy("id", true)
                .query();
        } catch(SQLException e) {
            e.printStackTrace();
            verses = new ArrayList<Verse>();
        }
        return verses;
    }

    public List<Verse> getAllVersesByBookChapterLimit(int book_id, int chapter,
        long maxRows, long startRow) {
        List<Verse> verses = null;
        try {
            verses = getDbControlHelper().getModelVerseDAO().queryBuilder()
                .limit(maxRows).offset(startRow).orderBy("id", true)
                .where().eq("book_id", book_id).and()
                .like("chapter", chapter).query();
        } catch(SQLException e) {
            e.printStackTrace();
            verses = new ArrayList<Verse>();
        }
        return verses;
    }

    public Verse getVerseByBookChapter(int book_id, int chapter, int verse_number) {
        List<Verse> verses = null;
        try {

            QueryBuilder<Verse, Integer> queryBuilder = getDbControlHelper()
                .getModelVerseDAO().queryBuilder();

            Where<Verse, Integer> where = queryBuilder.where();

            where.eq("book_id", book_id).and().eq("chapter", chapter).and().eq("number", verse_number);

            // verses = getDbControlHelper().getModelVerseDAO().queryBuilder()
            // .orderBy("id", true)
            // .where().eq("book_id", book_id).and()
            // .like("chapter", chapter).and().eq("number", verse_number)
            // .query();

            PreparedQuery<Verse> preparedQuery = queryBuilder.prepare();

            verses = getDbControlHelper().getModelVerseDAO().query(
                preparedQuery);
        } catch(SQLException e) {
            return null;
        }
        return verses.get(0);
    }

    public List<Verse> getVersesByAll(String[] searchWordsSplitSearch, long maxRows, long startRow) {
        List<Verse> verses = null;
        int size = searchWordsSplitSearch.length;
        int x = 0;

        try {
            QueryBuilder<Verse, Integer> queryBuilder = getDbControlHelper()
                .getModelVerseDAO().queryBuilder();
            Where<Verse, Integer> where = queryBuilder.where();
            for(String word : searchWordsSplitSearch) {
                SelectArg selectArg = new SelectArg("%" + word + "%");
                where.like("text", selectArg);
                if (x < (size - 1))
                    where.and();
                x++;
            }
            queryBuilder.limit(maxRows);
            queryBuilder.offset(startRow);
            queryBuilder.orderBy("id", true);
            PreparedQuery<Verse> preparedQuery = queryBuilder.prepare();
            verses = getDbControlHelper().getModelVerseDAO().query(
                preparedQuery);
        } catch(SQLException e) {
            e.printStackTrace();
            verses = new ArrayList<Verse>();
        }
        return verses;
    }

    public List<Verse> getVersesByTestament(String[] searchWordsSplitSearch, Testament testament,
        long maxRows, long startRow) {
        List<Verse> verses = null;
        int size = searchWordsSplitSearch.length;
        int x = 0;

        try {
            QueryBuilder<Verse, Integer> queryBuilderVerse = getDbControlHelper()
                .getModelVerseDAO().queryBuilder();
            Where<Verse, Integer> where = queryBuilderVerse.where();
            QueryBuilder<Book, Integer> queryBuilderBook = getDbControlHelper()
                .getModelBookDAO().queryBuilder();
            Where<Book, Integer> where2 = queryBuilderBook.where();
            where2.eq("testament", testament);
            for(String word : searchWordsSplitSearch) {
                SelectArg selectArg = new SelectArg("%" + word + "%");
                where.like("text", selectArg);
                if (x < (size - 1))
                    where.and();
                x++;
            }
            queryBuilderVerse.limit(maxRows);
            queryBuilderVerse.offset(startRow);
            queryBuilderVerse.orderBy("id", true);
            queryBuilderVerse.leftJoin(queryBuilderBook);
            PreparedQuery<Verse> preparedQuery = queryBuilderVerse.prepare();
            verses = getDbControlHelper().getModelVerseDAO().query(
                preparedQuery);
        } catch(SQLException e) {
            e.printStackTrace();
            verses = new ArrayList<Verse>();
        }
        return verses;
    }

    public List<Verse> getVersesByBooks(String[] searchWordsSplitSearch, List<Integer> books,
        long maxRows, long startRow) {
        List<Verse> verses = null;
        int size = searchWordsSplitSearch.length;
        int x = 0;

        try {
            QueryBuilder<Verse, Integer> queryBuilder = getDbControlHelper()
                .getModelVerseDAO().queryBuilder();
            Where<Verse, Integer> where = queryBuilder.where();
            for(String word : searchWordsSplitSearch) {
                SelectArg selectArg = new SelectArg("%" + word + "%");
                where.like("text", selectArg);
                if (x < (size - 1))
                    where.and();
                x++;
            }
            where.and();
            where.in("book_id", books);
            queryBuilder.limit(maxRows);
            queryBuilder.offset(startRow);
            queryBuilder.orderBy("id", true);
            PreparedQuery<Verse> preparedQuery = queryBuilder.prepare();
            verses = getDbControlHelper().getModelVerseDAO().query(
                preparedQuery);
        } catch(SQLException e) {
            e.printStackTrace();
            verses = new ArrayList<Verse>();
        }
        return verses;
    }

    // Colocar aqui a busca na base conforme as informacoes tratada
    public InfoSearchDTO getResultSearchVoice(InfoSearchDTO tratamento) {

        InfoSearchDTO result = null;

        result.setBook("id_book");
        result.setChapter("1");
        result.setVerse("id_verse");

        return result;
    }

    public Map<String, Integer> getMapOfBookForSearch() {

        Map<String, Integer> map = new HashMap<String, Integer>();

        List<Book> listBook = this.getAllBookLists();

        for(Book book : listBook) {
            map.put(book.getDescription().toLowerCase(), book.getId());
        }

        return map;
    }

    public Verse getVerseOfDayByBooks(ApplicationControllerWrapper application) {
        List<Verse> verses = null;
        int id_verse = 0;

        try {

            Calendar currentDate = Calendar.getInstance();
            Date date = currentDate.getTime();

            // Find the sequential day from a date, essentially resetting time
            // to start of the day
            long startDay = date.getTime() / 1000 / 60 / 60 / 24;
            long endDay = application.getLastDateVerseOfDay() / 1000 / 60 / 60 / 24;

            // Find the difference, duh
            long daysBetween = endDay - startDay;

            if ((daysBetween == 0) && (application.getLastDateVerseOfDay() != 0)) {

                id_verse = application.getLastVerseOfDay();

            } else {

                GenericRawResults<String[]> rawResults = getDbControlHelper().getModelVerseDAO()
                    .queryRaw("SELECT id FROM verse where book_id in (19,20)");

                List<String[]> results = rawResults.getResults();

                int min = 0;
                int max = results.size();

                Random r = new Random();
                int index = r.nextInt(max - min);

                id_verse = Integer.parseInt(results.get(index)[0]);

                application.setLastVerseOfDay(id_verse);
                application.setLastDateVerseOfDay(date.getTime());
            }

            QueryBuilder<Verse, Integer> queryBuilder = getDbControlHelper().getModelVerseDAO().queryBuilder();

            Where<Verse, Integer> where = queryBuilder.where();

            where.eq("id", id_verse);

            PreparedQuery<Verse> preparedQuery = queryBuilder.prepare();

            verses = getDbControlHelper().getModelVerseDAO().query(preparedQuery);

        } catch(SQLException e) {
            return null;
        }

        return verses.get(0);
    }
*/
}
