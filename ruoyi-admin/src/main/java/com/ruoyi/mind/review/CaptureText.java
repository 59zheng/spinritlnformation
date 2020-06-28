package com.ruoyi.mind.review;

import com.ruoyi.common.config.Global;
import com.ruoyi.common.config.ServerConfig;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.file.FileUploadUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

@Controller
public class CaptureText {


    @Autowired
    private ServerConfig serverConfig;

    public static Thread thread1;
    public static Thread thread2;
    public static Thread thread;
    public static BufferedImage close;
    public static AjaxResult ajax;
    public static CaptureScreen captureScreen;

    @GetMapping("/common/uploadScreen")
    @ResponseBody
    public AjaxResult openScreenshots(String name) throws InterruptedException {
        long start = System.currentTimeMillis();
        System.out.println("start = " + start);
        captureScreen = new CaptureScreen();


        thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                    synchronized (thread2) {
                        try {
                            thread2.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    close = captureScreen.close();
                    System.out.println(close);

                    // 上传文件路径
                    String filePath = Global.getUploadPath();
                    // 上传并返回新文件名称
                    String fileName = null;
                    try {
                        InputStream inputStream = bufferedImageToInputStream(close);
                        String name2 = name + ".jpg";
                        MultipartFile multipartFile = new MockMultipartFile(name2, name2, "", inputStream);
                        fileName = FileUploadUtils.upload(filePath, multipartFile);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    String url = "http://127.0.0.1:9989/" + fileName;
                    ajax = AjaxResult.success();
                    ajax.put("fileName",fileName);
                    ajax.put("url", url);
                }
            }
        });
        thread2.start();

        thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                captureScreen.open();
            }
        });
        thread1.start();

        try {
            thread2.join();//注意这里
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        重置get值
        captureScreen.clean();

        long end = System.currentTimeMillis();
        System.out.println("end = " + end);
        System.out.println("end - Start:" + (end - start));
        return ajax;
    }

    public InputStream bufferedImageToInputStream(BufferedImage image) {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        try {
            ImageIO.write(image, "png", os);
            InputStream input = new ByteArrayInputStream(os.toByteArray());
            return input;
        } catch (IOException e) {
            return null;
        }
    }



}
