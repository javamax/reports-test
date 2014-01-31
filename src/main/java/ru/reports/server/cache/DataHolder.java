package ru.reports.server.cache;

import java.util.List;

/**
 * Created by maxim on 31.01.14.
 */
public class DataHolder {

    private static DataHolder ourInstance = new DataHolder();

    private List<String[]> data = null;

    public static DataHolder getInstance() {
        return ourInstance;
    }

    private DataHolder() {
    }

    public List<String[]> getData() {
        return data;
    }

    public void setData(List<String[]> data) {
        this.data = data;
    }
}
