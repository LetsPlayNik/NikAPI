package de.niklas.nikapi.spigot.config;

/*
 * Project Plugin-API
 * Created by Niklas S. (Let's play Nik)
 * Created at 11.02.2022 - 13:26Uhr
 */

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.configuration.serialization.ConfigurationSerializable;

import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

public class Storage<T> {

    protected static Gson gsonInstance;
    private final Class<T> clazz;

    public Storage(Class<T> clazz) {
        this.clazz = clazz;
    }

    HashMap<String, String> data = new HashMap<>();

    static {
        class ConfigurationSerializableGsonTypeAdapter extends TypeAdapter<ConfigurationSerializable> {
            @Override
            public void write(JsonWriter out, ConfigurationSerializable value) throws IOException {
                if (value != null) {
                    YamlConfiguration config = new YamlConfiguration();
                    config.set("/", value);
                    try {
                        String val = config.saveToString();
                        out.value(val);
                    } catch (Exception e) {
                        throw new IOException("Storage Error: ", e);
                    }
                } else {
                    out.nullValue();
                }
            }

            @Override
            public ConfigurationSerializable read(JsonReader in) throws IOException {
                if (in.peek() != JsonToken.NULL) {
                    String val = in.nextString();
                    YamlConfiguration config;
                    try {
                        config = YamlConfiguration.loadConfiguration(new StringReader(val));
                    } catch (RuntimeException e) {
                        throw new IOException("Deserialization Problem!", e);
                    }
                    Object obj = config.get("/");
                    return (ConfigurationSerializable) obj;
                } else {
                    return null;
                }
            }
        }

        class ConfigurationSerializableGsonTypeAdapterFactory implements TypeAdapterFactory {
            private ConfigurationSerializableGsonTypeAdapter typeAdapter;

            public ConfigurationSerializableGsonTypeAdapterFactory(
                    ConfigurationSerializableGsonTypeAdapter typeAdapter) {
                this.typeAdapter = typeAdapter;
            }

            @SuppressWarnings("unchecked")
            public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
                if (!(ConfigurationSerializable.class.isAssignableFrom(type.getRawType())))
                    return null;

                return (TypeAdapter<T>) typeAdapter;
            }
        }

        ConfigurationSerializableGsonTypeAdapter typeAdapter = new ConfigurationSerializableGsonTypeAdapter();

        gsonInstance = new GsonBuilder()
                .registerTypeAdapterFactory(new ConfigurationSerializableGsonTypeAdapterFactory(typeAdapter))
                .registerTypeAdapter(ConfigurationSerializable.class, typeAdapter).create();

    }


    public void setData(String key, T value) {
        data.put(key, gsonInstance.toJson(value));
    }

    T getData(String key) {
        return gsonInstance.fromJson(key, clazz);
    }

    public void output() {
        for (Map.Entry<String, String> entry : data.entrySet()) {
            System.out.println(entry.getKey() + ":" + entry.getValue());
        }
    }
}
