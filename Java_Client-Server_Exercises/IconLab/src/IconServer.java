import java.io.*;


public class IconServer {
    public static void main(String[] args) throws IOException {
        new IconThread().start();
    }
}