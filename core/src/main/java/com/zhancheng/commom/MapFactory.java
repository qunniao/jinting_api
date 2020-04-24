package com.zhancheng.commom;

import java.util.HashMap;
import java.util.Map;

/**
 * @author tangchao
 */
public class MapFactory {

    /**
     * 用于创建查询的hashmap
     *
     * @return
     */
    public static Map<String, Object> getHashMap() {
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("is_deleted", "0");
        return hashMap;
    }

}
