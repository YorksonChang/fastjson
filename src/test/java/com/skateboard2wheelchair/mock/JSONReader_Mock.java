package com.skateboard2wheelchair.mock;

//import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InOrder;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;


import org.junit.Assert;

import com.alibaba.fastjson.JSONReader;
import com.alibaba.fastjson.parser.DefaultJSONParser;




public class JSONReader_Mock {
    private DefaultJSONParser parser_mock;
    @Spy
    private Integer integer = 123;

    @Spy
    private String string = "ABC";

    @Spy
    private long long_int = 123333333;

    @Before
    public void setup() {
        //  Mocks are being created.
        parser_mock = mock(DefaultJSONParser.class);
    }

    @Test
    public void test_int() throws Exception {
        when(parser_mock.parse()).thenReturn(integer);
        JSONReader reader = new JSONReader(parser_mock);
        Assert.assertEquals(new Integer(123), reader.readInteger());
        verify(parser_mock, times(1)).parse();
        reader.close();
    }

    @Test
    public void test_str() throws Exception {
        when(parser_mock.parse()).thenReturn(string);
        JSONReader reader = new JSONReader(parser_mock);
        Assert.assertEquals("ABC", reader.readString());
        verify(parser_mock, times(1)).parse();
        reader.close();
    }

    @Test
    public void test_long() throws Exception {
        when(parser_mock.parse()).thenReturn(long_int);
        JSONReader reader = new JSONReader(parser_mock);
        Assert.assertEquals(new Long(123333333), reader.readLong());
        verify(parser_mock, times(1)).parse();
        reader.close();
    }
}
