package com.skateboard2wheelchair.validate;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONValidator;
import org.junit.Test;


import static org.junit.Assert.*;

public class JSONValidatorTest {

    @Test
    public void validate_test1() throws Throwable {
        boolean thrown = false;
        try {
            JSONValidator.from("{\"string\":\"a\",\"nums\":[0,-1,10,0.123,1e5,-1e+6,0.1e-7],\"object\":{\"empty\":{},\"list\":[]},\"list\":[\"object\",{\"true\":true,\"false\":false,\"null\":null}]}").validate();
        } catch (JSONException e) {
            thrown = true;
        }
        assertFalse(thrown);
    }
    @Test
    public void validate_test2() throws Throwable {
        boolean thrown = false;
        try {
            JSONValidator.from("{noQuotationMarksError}").validate();
        } catch (JSONException e) {
            thrown = true;
        }
        assertTrue(thrown);
    }
    @Test
    public void validate_test3() throws Throwable {
        boolean thrown = false;
        try {
            JSONValidator.from("{\"colonError\"}").validate();
        } catch (JSONException e) {
            thrown = true;
        }
        assertTrue(thrown);

    }
    @Test
    public void validate_test4() throws Throwable {
        boolean thrown = false;
        try {
            JSONValidator.from("[1}").validate();
        } catch (JSONException e) {
            thrown = true;
        }
        assertTrue(thrown);
    }
    @Test
    public void validate_test5() throws Throwable {
        boolean thrown = false;
        try {
            JSONValidator.from("-a").validate();
        } catch (JSONException e) {
            thrown = true;
        }
        assertTrue(thrown);
    }
    @Test
    public void validate_test6() throws Throwable {
        boolean thrown = false;
        try {
            JSONValidator.from("+1").validate();
        } catch (JSONException e) {
            thrown = true;
        }
        assertTrue(thrown);
    }
    @Test
    public void validate_test7() throws Throwable {
        boolean thrown = false;
        try {
            JSONValidator.from("1ea").validate();
        } catch (JSONException e) {
            thrown = true;
        }
        assertTrue(thrown);
    }
    @Test
    public void validate_test8() throws Throwable {
        boolean thrown = false;
        try {
            JSONValidator.from("trua").validate();
        } catch (JSONException e) {
            thrown = true;
        }
        assertTrue(thrown);
    }


    // Test the empty JSON
    @Test
    public void test_validate_brackets() throws Throwable {
        boolean thrown = false;
        try {
            JSONValidator.from("{}").validate();
        } catch (JSONException e) {
            thrown = true;
        }
        assertFalse(thrown);
    }

    // Test JSON without a fieldName
    @Test
    public void test_validate_noFieldName() throws Throwable {
        boolean thrown = false;
        try {
            JSONValidator.from("{hello}").validate();
        } catch (JSONException e) {
            thrown = true;
        }
        assertTrue(thrown);
    }

    // Test no colon between fieldname and value
    @Test
    public void test_validate_noColon() throws Throwable {
        boolean thrown = false;
        try {
            JSONValidator.from("{\"fieldName\" \"value\"}").validate();
        } catch (JSONException e) {
            thrown = true;
        }
        assertTrue(thrown);
    }

    // Test empty JSON array
    @Test
    public void test_validate_emptyArray() throws Throwable {
        boolean thrown = false;
        try {
            JSONValidator.from("[]").validate();
        } catch (JSONException e) {
            thrown = true;
        }
        assertFalse(thrown);
    }

    // Test JSON array no right square bracket error
    @Test
    public void test_validate_arrayNoRightBracket() throws Throwable {
        boolean thrown = false;
        try {
            JSONValidator.from("[{\"fieldName\": \"value\"}").validate();
        } catch (JSONException e) {
            thrown = true;
        }
        assertTrue(thrown);
    }

    // Test invalid number - a digit followed by a letter
    @Test
    public void test_validate_invalidNumber() throws Throwable {
        boolean thrown = false;
        try {
            JSONValidator.from("9a").validate();
        } catch (JSONException e) {
            thrown = true;
        }
        assertTrue(thrown);
    }

    // Test invalid decimal number
    @Test
    public void test_validate_invalidDecimalNumber() throws Throwable {
        boolean thrown = false;
        try {
            JSONValidator.from("1.fgh").validate();
        } catch (JSONException e) {
            thrown = true;
        }
        assertTrue(thrown);
    }

    // Test invalid negative decimal number
    @Test
    public void test_validate_invalidNegativeNumber() throws Throwable {
        boolean thrown = false;
        try {
            JSONValidator.from("[{\"value\": \"-a\"}").validate();
        } catch (JSONException e) {
            thrown = true;
        }
        assertTrue(thrown);
    }

    // Test invalid exponential number
    @Test
    public void test_validate_invalidExponentialNumber() throws Throwable {
        boolean thrown = false;
        try {
            JSONValidator.from("3Eabsdc").validate();
        } catch (JSONException e) {
            thrown = true;
        }
        assertTrue(thrown);
    }

    // Test multi-digit exponential number
    @Test
    public void test_validate_validExponentialNumber() throws Throwable {
        boolean thrown = false;
        try {
            JSONValidator.from("3e100").validate();
        } catch (JSONException e) {
            thrown = true;
        }
        assertFalse(thrown);
    }

    // Test unicode
    @Test
    public void test_validate_unicode() throws Throwable {
        boolean thrown = false;
        try {
            JSONValidator.from("\"\\u0000\"").validate();
        } catch (JSONException e) {
            thrown = true;
        }
        assertFalse(thrown);
    }

    // Test ‘\’ followed by character other than u
    @Test
    public void test_validate_notUnicode() throws Throwable {
        boolean thrown = false;
        try {
            JSONValidator.from("\"\\t\"").validate();
        } catch (JSONException e) {
            thrown = true;
        }
        assertFalse(thrown);
    }

    // Test “true” keyword error
    @Test
    public void test_validate_startWitht_1() throws Throwable {
        boolean thrown = false;
        try {
            JSONValidator.from("ta").validate();
        } catch (JSONException e) {
            thrown = true;
        }
        assertTrue(thrown);
    }

    @Test
    public void test_validate_startWitht_2() throws Throwable {
        boolean thrown = false;
        try {
            JSONValidator.from("tra").validate();
        } catch (JSONException e) {
            thrown = true;
        }
        assertTrue(thrown);
    }

    @Test
    public void test_validate_startWitht_3() throws Throwable {
        boolean thrown = false;
        try {
            JSONValidator.from("trua").validate();
        } catch (JSONException e) {
            thrown = true;
        }
        assertTrue(thrown);
    }

    @Test
    public void test_validate_startWitht_4() throws Throwable {
        boolean thrown = false;
        try {
            JSONValidator.from("truea").validate();
        } catch (JSONException e) {
            thrown = true;
        }
        assertTrue(thrown);
    }

    // Test “false” keyword error
    @Test
    public void test_validate_startWithf_1() throws Throwable {
        boolean thrown = false;
        try {
            JSONValidator.from("fe").validate();
        } catch (JSONException e) {
            thrown = true;
        }
        assertTrue(thrown);
    }

    @Test
    public void test_validate_startWithf_2() throws Throwable {
        boolean thrown = false;
        try {
            JSONValidator.from("fae").validate();
        } catch (JSONException e) {
            thrown = true;
        }
        assertTrue(thrown);
    }

    @Test
    public void test_validate_startWithf_3() throws Throwable {
        boolean thrown = false;
        try {
            JSONValidator.from("fale").validate();
        } catch (JSONException e) {
            thrown = true;
        }
        assertTrue(thrown);
    }

    @Test
    public void test_validate_startWithf_4() throws Throwable {
        boolean thrown = false;
        try {
            JSONValidator.from("falsa").validate();
        } catch (JSONException e) {
            thrown = true;
        }
        assertTrue(thrown);
    }

    @Test
    public void test_validate_startWithf_5() throws Throwable {
        boolean thrown = false;
        try {
            JSONValidator.from("falsea").validate();
        } catch (JSONException e) {
            thrown = true;
        }
        assertTrue(thrown);
    }

    // Test null keyword error
    @Test
    public void test_validate_startWithn_1() throws Throwable {
        boolean thrown = false;
        try {
            JSONValidator.from("na").validate();
        } catch (JSONException e) {
            thrown = true;
        }
        assertTrue(thrown);
    }

    @Test
    public void test_validate_startWithn_2() throws Throwable {
        boolean thrown = false;
        try {
            JSONValidator.from("nua").validate();
        } catch (JSONException e) {
            thrown = true;
        }
        assertTrue(thrown);
    }

    @Test
    public void test_validate_startWithn_3() throws Throwable {
        boolean thrown = false;
        try {
            JSONValidator.from("nula").validate();
        } catch (JSONException e) {
            thrown = true;
        }
        assertTrue(thrown);
    }

    @Test
    public void test_validate_startWithn_4() throws Throwable {
        boolean thrown = false;
        try {
            JSONValidator.from("nulla").validate();
        } catch (JSONException e) {
            thrown = true;
        }
        assertTrue(thrown);
    }

    // Test invalid start character
    @Test
    public void test_validate_default() throws Throwable {
        boolean thrown = false;
        try {
            JSONValidator.from("a").validate();
        } catch (JSONException e) {
            thrown = true;
        }
        assertTrue(thrown);
    }

    // Test unicode in fieldName
    @Test
    public void test_validate_fieldNameUnicode() throws Throwable {
        boolean thrown = false;
        try {
            JSONValidator.from("{\"\\u1234\": \"value\"}").validate();
        } catch (JSONException e) {
            thrown = true;
        }
        assertFalse(thrown);
    }


    // Test \ not followed by u error in fieldName
    @Test
    public void test_validate_fieldNameNotUnicode() throws Throwable {
        boolean thrown = false;
        try {
            JSONValidator.from("{\"\\t\": \"value\"}").validate();
        } catch (JSONException e) {
            thrown = true;
        }
        assertFalse(thrown);
    }

    // Test JSON end with white space
    @Test
    public void test_validate_endWithWhiteSpace() throws Throwable {
        boolean thrown = false;
        try {
            JSONValidator.from("{}   ").validate();
        } catch (JSONException e) {
            thrown = true;
        }
        assertFalse(thrown);
    }

}