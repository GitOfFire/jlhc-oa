package com.jlhc.common.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;

import java.text.SimpleDateFormat;
import java.util.Date;

@Configuration
public class FormatConfiguration {
    @Bean
    public Converter<String, Date> addNewConvert() {
        return new Converter<String, Date>(){
            @Override
            public Date convert(String source) {
                System.out.println("...............我顶用了............");
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date date = null;
                try {
                    date = sdf.parse((String) source);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return date;
            }
        };
    }
}
