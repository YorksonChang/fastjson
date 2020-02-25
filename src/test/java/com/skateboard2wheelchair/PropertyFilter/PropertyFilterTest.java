package com.skateboard2wheelchair.PropertyFilter;

import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.PropertyFilter;
import com.alibaba.fastjson.serializer.SerializeWriter;
import junit.framework.TestCase;
import org.junit.Assert;

import java.util.HashMap;
import java.util.Map;

public class PropertyFilterTest extends TestCase {

    public void test_5() throws Exception {
        PropertyFilter filter = new PropertyFilter() {

            public boolean apply(Object source, String name, Object value) {
                if ("1".equals(name)) {
                    return false;
                }
                return true;
            }
        };

        SerializeWriter out = new SerializeWriter();
        JSONSerializer serializer = new JSONSerializer(out);
        serializer.getPropertyFilters().add(filter);

        Map<Integer, Object> map = new HashMap<Integer, Object>();
        map.put(0, 3);
        map.put(1, "chennp2008");
        serializer.write(map);

        String text = out.toString();
        System.out.println(text);
        Assert.assertEquals("{\"0\":3}", text);
    }
}

