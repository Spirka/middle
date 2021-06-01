package ru.job4j.concurrent.filedownload;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
@Slf4j
public class FileDownload implements Runnable {

//    private static final Logger log = LoggerFactory.getLogger(FileDownload.class);
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
        log.info("Зашли в download");
        System.out.println(fileURL + " " + speed);
        try (BufferedInputStream inputStream = new BufferedInputStream(new URL(this.fileURL).openStream());
             FileOutputStream out = new FileOutputStream("/tmp/pom_tmp.xml")) {
            log.info("Начинаем скачивать");
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
            log.info("Загрузка завершена!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
