package ru.job4j.concurrent.filedownload;

/**
 * Class Main
 *
 * @author Kseniya Dergunova
 * @since 05.05.2020
 */
public class Main {
    public static void main(String[] args) {
        FileDownload fileDownload = new FileDownload(args[0], Integer.parseInt(args[1]));
        new Thread(fileDownload).start();
    }
}
