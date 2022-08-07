package org.coooooding.MagicTower.util;

import java.util.HashMap;

public class GlobalMap {

    private static HashMap<String, Object> globalMap = new HashMap<>();

    public static HashMap getInstance() {
        return globalMap;
    }

    public static void setValue(String key, Object value){
        globalMap.put(key, value);
    }

    public static Object getByKey(String key){
        if(globalMap.containsKey(key)){
            return globalMap.get(key);
        }else {
            return null;
        }
    }
}
