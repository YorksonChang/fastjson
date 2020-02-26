package com.skateboard2wheelchair.JSONWriter;

import com.alibaba.fastjson.NewJSONWriter;
import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.SerializeWriter;
import org.junit.Assert;
import org.junit.Test;

import java.io.StringWriter;

import static org.mockito.Mockito.*;

public class NewJSONWriterTest {

    @Test
    public void test_writer1() throws Exception {
        StringWriter out = new StringWriter();
        StringWriter spyOut = spy(out);
        SerializeWriter outWriter = new SerializeWriter(spyOut);
        SerializeWriter spyOutWriter = spy(outWriter);
        JSONSerializer outSerializer = new JSONSerializer(spyOutWriter);
        JSONSerializer spyOutSerializer = spy(outSerializer);
        NewJSONWriter writer = new NewJSONWriter(spyOutWriter, spyOutSerializer);

        writer.startObject();

        writer.writeKey("id");
        writer.writeValue(1);

        writer.writeKey("name");
        writer.writeValue("Fish");

        writer.endObject();

        writer.close();

        Assert.assertEquals("{\"id\":1,\"name\":\"Fish\"}", spyOut.toString());
    }


}