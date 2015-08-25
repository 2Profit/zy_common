package com.zy.common.util;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;

/**
 * 用于转换 ResponseBody 返回Json中 Date 格式
 * 
 * @author pingan
 *
 * @since  2015年6月3日 上午11:39:25
 */

public class CustomDateSerializer extends JsonSerializer<Date> {    
    @Override
    public void serialize(Date value, JsonGenerator gen, SerializerProvider arg2) throws 
        IOException, JsonProcessingException {      

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formattedDate = formatter.format(value);

        gen.writeString(formattedDate);

    }
}
