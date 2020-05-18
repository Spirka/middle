package ru.job4j.concurrent.filedownload;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

/**
 * Class FileDownload
 *
 * @author Kseniya Dergunova
 * @since 05.05.2020
 */
public class FileDownload implements Runnable {

    private static final Logger LOG = LogManager.getLogger(FileDownload.class);
    private final String fileURL;
    private final int speed;

    public FileDownload(String fileURL, int speed) {
        this.fileURL = fileURL;
        this.speed = speed;
    }

    @Override
    public void run() {
        download(this.fileURL, this.speed);
    }

    private void download(String fileURL, int speed) {
        LOG.info("Зашли в download");
        System.out.println(fileURL + " " + speed);
        try (BufferedInputStream inputStream = new BufferedInputStream(new URL(this.fileURL).openStream());
             FileOutputStream out = new FileOutputStream("/tmp/pom_tmp.xml")) {
            LOG.info("Начинаем скачивать");
            byte[] data = new byte[1024];
            int bytesRead;
            System.out.println("Загрузка началась...");
            while ((bytesRead = inputStream.read(data, 0, 1024)) != -1) {
                out.write(data, 0, bytesRead);
                int delay = 0;
                double sec = (double) 1024 / speed;
                if (sec > 1) {
                    delay = 1024 / speed;
                }
                System.out.println("Ограничение скорости...");
                try {
                    Thread.sleep(delay);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            LOG.info("Загрузка завершена!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
