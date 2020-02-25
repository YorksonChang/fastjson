package com.skateboard2wheelchair.MapSerializer;

import com.alibaba.fastjson.serializer.*;
import junit.framework.TestCase;
import org.junit.Assert;

import java.util.LinkedHashMap;
import java.util.Map;

public class MapSerializerTest extends TestCase {

    public void test_5() throws Exception {
        PropertyFilter filter = new PropertyFilter() {

            public boolean apply(Object source, String name, Object value) {
                if ("name".equals(name)) {
                    return false;
                }
                return true;
            }
        };

        SerializeWriter out = new SerializeWriter();
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        map.put("TOP", "value");
        map.put("bytes", new byte[] { 1, 2 });
        map.put("name", "number");

        MapSerializer mapSerializer = new MapSerializer();
        mapSerializer.getPropertyFilters().add(filter);
        mapSerializer.write(new JSONSerializer(out), map, null, null, 0);

        String text = out.toString();
        Assert.assertEquals("{\"TOP\":\"value\",\"bytes\":\"AQI=\"}", text);
    }

    public void test_6() throws Exception {
        PropertyFilter filter = new PropertyFilter() {

            public boolean apply(Object source, String name, Object value) {
                if ("1".equals(name)) {
                    return false;
                }
                return true;
            }
        };

        SerializeWriter out = new SerializeWriter();
        Map<Integer, Object> map = new LinkedHashMap<Integer, Object>();
        map.put(3, "value");
        map.put(2, new byte[] { 1, 2 });
        map.put(1, "number");

        MapSerializer mapSerializer = new MapSerializer();
        mapSerializer.getPropertyFilters().add(filter);
        mapSerializer.write(new JSONSerializer(out), map, null, null, 0);

        String text = out.toString();
        System.out.println(text);
        Assert.assertEquals("{3:\"value\",2:\"AQI=\"}", text);
    }
}
