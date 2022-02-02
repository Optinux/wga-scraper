package studio.optinux.scraper;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

public class ImageDownloader {

  public static void DownloadImage(String search, String path)
    throws IOException {
    InputStream inputStream = null; //start IO-stuff
    OutputStream outputStream = null; //start IO-stuff
    try {
      URL url = new URL(search); //set URL
      String USER_AGENT =
        "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0.2924.87 Safari/537.36"; //proper User-Agent -> no getting blocked
      URLConnection con = url.openConnection(); //start the connection
      con.setRequestProperty("User-Agent", USER_AGENT); //set User-Agent
      inputStream = con.getInputStream(); //get Data from server
      outputStream = new FileOutputStream(path);
      byte[] buffer = new byte[2048]; //limit to one file per loop, someone on Discord told me to use this lol
      int length; //limit to one file per loop, someone on Discord told me to use this lol
      while ((length = inputStream.read(buffer)) != -1) {
        outputStream.write(buffer, 0, length); //write to Disk
      }
    } catch (Exception ex) {
      System.out.println("Something broke!");
    }
    outputStream.close(); //close the IO stuff
    inputStream.close(); //close the IO stuff
  }

  public static void main(String[] args) throws IOException {
    ImageDownloader id1 = new ImageDownloader();
    //  id1.DownloadImage("https://www.wikimedia.de/wp-content/uploads/2020/12/Wikipedia_mini_globe_handheld-720x405.jpg", "./output/test.jpg");   <- Example for testing
  }
}
