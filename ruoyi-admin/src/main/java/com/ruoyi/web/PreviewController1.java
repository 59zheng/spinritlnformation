/*
package com.ruoyi.web;



import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.mind.utils.Doc2HtmlUtil;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.jodconverter.DocumentConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@Controller
@RequestMapping("/preview")
public class PreviewController1  extends BaseController {
    // 第一步：转换器直接注入



    @RequestMapping("toPdfFile")
    public String toPdfFile() {
        File file = new File("D:\\text\\MSlf.docx");//需要转换的文件
        try {
            File newFile = new File("D:/obj-pdf");//转换之后文件生成的地址
            if (!newFile.exists()) {
                newFile.mkdirs();
            }

            //2，选择流
            InputStream is = null;
            is = new FileInputStream(file);
            Doc2HtmlUtil doc2HtmlUtil = new Doc2HtmlUtil();
            String s = doc2HtmlUtil.file2pdf(is, "D:\\text\\MSlf.docx", "docx");
            //文件转化
            converter.convert(file).to(new File("D:/obj-pdf/hello.pdf")).execute();
            //使用response,将pdf文件以流的方式发送的前段
            ServletOutputStream outputStream = getResponse().getOutputStream();
            InputStream in = new FileInputStream(new File("D:/obj-pdf/hello.pdf"));// 读取文件
            // copy文件
            int i = IOUtils.copy(in, outputStream);
            System.out.println(i);
            in.close();
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "This is to pdf";
    }

    @RequestMapping("cad2pdf")
    public String cad2PdfFile() throws IOException {
        String str = "D:\\常用软件\\DevTools\\adtpcpro\\dp.exe";//安装目录
        String source = "D:\\test.dwg";//dwg源文件
        String out = "D:\\data\\111.pdf";//生成的pdf路径+文件名
        Process pro = Runtime.getRuntime().exec(str+" /InFile "+source+"  /OutFile "+out);
        BufferedReader br = new BufferedReader(new InputStreamReader(pro.getInputStream())); //虽然cmd命令可以直接输出，但是通过IO流技术可以保证对数据进行一个缓冲。
        //InputStream in = pro.getInputStream();
        InputStream in = new FileInputStream(new File(out));// 读取文件
        ServletOutputStream outputStream = getResponse().getOutputStream();
        // copy文件
        int i = IOUtils.copy(in, outputStream);
        System.out.println(i);
        in.close();
        outputStream.close();
        return "This is to pdf";
    }

}
*/
