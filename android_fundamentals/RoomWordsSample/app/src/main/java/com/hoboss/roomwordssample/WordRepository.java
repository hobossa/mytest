package com.hoboss.roomwordssample;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class WordRepository {
    private WordDao wordDao;
    private LiveData<List<Word>> allWords;

    WordRepository(Application application) {
        WordRoomDatabase db = WordRoomDatabase.getDatabase(application);
        wordDao = db.wordDao();
        allWords = wordDao.getAllWords();
    }

    public LiveData<List<Word>> getAllWords() {
        return allWords;
    }

    public void insert (Word word) {
        new InsertAsyncTask(wordDao).execute(word);
    }

    public void deleteAll()  {
        new DeleteAllWordsAsyncTask(wordDao).execute();
    }

    public void deleteWord(Word word) {
        new DeleteWordAsyncTask(wordDao).execute(word);
    }

    private static class InsertAsyncTask extends AsyncTask<Word, Void, Void> {

        private WordDao asyncTaskDao;

        InsertAsyncTask(WordDao dao) {
            asyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Word... params) {
            asyncTaskDao.insert(params[0]);
            return null;
        }
    }

    private static class DeleteAllWordsAsyncTask extends AsyncTask<Void, Void, Void> {
        private WordDao asyncTaskDao;

        DeleteAllWordsAsyncTask(WordDao dao) {
            asyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Void... voids) {
            asyncTaskDao.deleteAll();
            return null;
        }
    }

    private static  class DeleteWordAsyncTask extends AsyncTask<Word, Void, Void> {
        private WordDao asyncTaskDao;

        DeleteWordAsyncTask(WordDao dao) {
            asyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Word... params) {
            asyncTaskDao.deleteWord(params[0]);
            return null;
        }
    }
}
