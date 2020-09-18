package com.ruoyi.mind.physical.controller;

import com.deepoove.poi.XWPFTemplate;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;

public class demo
{
    public static void main(String[] args) throws IOException {
        File file = ResourceUtils.getFile("classpath:induced.doc");
        XWPFTemplate template = XWPFTemplate.compile(file).render(
                new HashMap<String, Object>(){{
                    put("title", "Hi, poi-tl Word模板引擎");
                }});
        FileOutputStream out = new FileOutputStream("output.docx");
        template.write(out);
        out.flush();
        out.close();
        template.close();
    }
}
