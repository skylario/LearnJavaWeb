package io.skylar.utils;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

/**
 * 使用类加载器读取和写入xml文件
 */
public class XMLUtils {
    private static String xmlPath;
    static {
        xmlPath = XMLUtils.class.getClassLoader().getResource("users.xml").getPath();
    }

    public static Document getDocument() throws DocumentException {
        SAXReader saxReader = new SAXReader();
        Document document = saxReader.read(xmlPath);
        return document;
    }

    public static void write2Xml(Document document) throws IOException {
        OutputFormat format = OutputFormat.createPrettyPrint();
        format.setEncoding("UTF-8");
        XMLWriter writer = new XMLWriter(new FileOutputStream(xmlPath), format );
        writer.write( document );
        writer.close();
    }
}
