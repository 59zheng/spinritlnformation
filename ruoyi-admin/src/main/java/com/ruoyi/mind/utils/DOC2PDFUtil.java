package com.ruoyi.mind.utils;

import java.io.File;
import java.io.IOException;
import java.net.ConnectException;
import java.util.Date;

import com.artofsolving.jodconverter.DocumentConverter;
import com.artofsolving.jodconverter.openoffice.connection.OpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.connection.SocketOpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.converter.OpenOfficeDocumentConverter;

public class DOC2PDFUtil extends java.lang.Thread  {
    private File inputFile;// 需要转换的文件
    private File outputFile;// 输出的文件

    public DOC2PDFUtil(File inputFile, File outputFile) {
        this.inputFile = inputFile;
        this.outputFile = outputFile;
    }

    public void docToPdf() {
        Date start = new Date();

        OpenOfficeConnection connection = new SocketOpenOfficeConnection(8100);
        try {
            connection.connect();
            DocumentConverter converter = new OpenOfficeDocumentConverter(connection);
            converter.convert(inputFile, outputFile);
        } catch (ConnectException cex) {
            cex.printStackTrace();
        } finally {
            // close the connection
            if (connection != null) {
                connection.disconnect();
                connection = null;
            }
        }
    }

    /**
     * 由于服务是线程不安全的，所以……需要启动线程
     */
    public void run() {
        this.docToPdf();
    }

    public File getInputFile() {
        return inputFile;
    }

    public void setInputFile(File inputFile) {
        this.inputFile = inputFile;
    }

    public File getOutputFile() {
        return outputFile;
    }

    public void setOutputFile(File outputFile) {
        this.outputFile = outputFile;
    }

    /**
     * 测试main方法
     * @param args
     */
    public static void main(String[] args) {
        File inputFile = new File("D:/text/MSlf.docx");
        File outputFile = new File("D:/text/MSlf.pdf");
        DOC2PDFUtil dp=new DOC2PDFUtil(inputFile,outputFile);
        dp.start();
    }
}
