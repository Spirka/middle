package ru.job4j.concurrent.threads;

import java.io.*;

/**
 * Class ParseFile
 *
 * @author Kseniya Dergunova
 * @since 23.05.2020
 */
public class ParseFile {
    private File file;

    public synchronized void setFile(File f) {
        file = f;
    }

    public synchronized File getFile() {
        return file;
    }

    public synchronized String getContent() throws IOException {
        try (InputStream i = new FileInputStream(file)) {
            StringBuilder text = new StringBuilder();
            int data;
            while ((data = i.read()) != -1) {
                text.append((char) data);
            }
            return text.toString();
        }
    }

    public synchronized String getContentWithoutUnicode() throws IOException {
        try (InputStream i = new FileInputStream(file)) {
            StringBuilder text = new StringBuilder();
            int data;
            while ((data = i.read()) > 0) {
                if (data < 0x80) {
                    text.append((char) data);
                }
            }
            return text.toString();
        }
    }

    public synchronized void saveContent(String content) throws IOException {
        try (OutputStream o = new FileOutputStream(file)) {
            o.write(content.getBytes());
        }
    }
}
