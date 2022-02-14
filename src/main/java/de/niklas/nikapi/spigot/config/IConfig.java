package de.niklas.nikapi.spigot.config;

/*
 * Project Plugin-API
 * Created by Niklas S. (Let's play Nik)
 * Created at 02.01.2022 - 13:26Uhr
 */

public interface IConfig {

    void addEntry(String key, Object value);
    void addNestedEntry(String key, Object value);
    void addDefaultEntry(String key, Object value);
    void addNestedDefaultEntry(String key, Object value);
    void save();

    boolean existEntry(String key);
    /*void set(String key, Object value);
    void get(String key);
    void setNested(String path, Object value);
    void getNested(String path);
    void save();*/
}
