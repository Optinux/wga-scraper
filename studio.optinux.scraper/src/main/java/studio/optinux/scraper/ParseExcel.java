package studio.optinux.scraper;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.Scanner;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ParseExcel extends ImageDownloader {

  public static String CurrentStringURL; // create variables to pass onto the Downloader
  public static String CurrentStringPATH; // create variables to pass onto the Downloader
  public static CellAddress CellAddress; // create variables to pass onto the Downloader
  public static boolean debugMode; // if(== true){-> no debugLogs}

  public static void main(String[] args) throws IOException {
    System.out.println(
      "db   d8b   db  d888b   .d8b.         .d8888.  .o88b. d8888b.  .d8b.  d8888b. d88888b d8888b."
    );
    System.out.println(
      "88   I8I   88 88' Y8b d8' `8b        88'  YP d8P  Y8 88  `8D d8' `8b 88  `8D 88'     88  `8D"
    );
    System.out.println(
      "88   I8I   88 88      88ooo88        `8bo.   8P      88oobY' 88ooo88 88oodD' 88ooooo 88oobY'"
    );
    System.out.println(
      "Y8   I8I   88 88  ooo 88~~~88 C8888D   `Y8b. 8b      88`8b   88~~~88 88~~~   88~~~~~ 88`8b  "
    );
    System.out.println(
      "`8b d8'8b d8' 88. ~8~ 88   88        db   8D Y8b  d8 88 `88. 88   88 88      88.     88 `88."
    );
    System.out.println(
      " `8b8' `8d8'   Y888P  YP   YP        `8888Y'  `Y88P' 88   YD YP   YP 88      Y88888P 88   YD"
    );
    System.out.println("");
    System.out.println("by Optinux");
    System.out.println("");
    System.out.println("enable debug mode? (true/false)");
    try (Scanner scan1 = new Scanner(System.in)) { // important: close afterwards in order to prevent memory leaks, thank you VS Code!
      debugMode = scan1.nextBoolean();
    }
    System.out.println("");

    Path path = Paths.get("./output/");
    if (Files.exists(path)) {
      System.out.println("directory already exists, skipping!");
    } else { // check if directory already exists -> allow for multiple use
      Files.createDirectory(path); // create directory if == false
      System.out.println("created directory!");
    }
    System.out.println("");
    if (debugMode == true) {
      System.out.println("downloading Images! check ./output! [debugMode]");
    } else {
      System.out.println("downloading Images! check ./output!");
    }

    String excelFilePath = "catalog-edit.xlsx"; // specify the file that is being used
    FileInputStream inputStream = new FileInputStream(new File(excelFilePath)); // start IO-Stuff

    Workbook workbook = new XSSFWorkbook(inputStream); // basically read the .xlsx
    org.apache.poi.ss.usermodel.Sheet firstSheet = workbook.getSheetAt(0);
    Iterator<Row> iterator = firstSheet.iterator();

    while (iterator.hasNext()) { // stop after there is no row left
      canContinue = false; //
      Row nextRow = iterator.next();
      Iterator<Cell> cellIterator = nextRow.cellIterator();

      while (cellIterator.hasNext()) { // stop after there is no cell left
        Cell cell = cellIterator.next(); // iterate through excel sheet cells

        CellAddress = cell.getAddress(); // get cellAdress
        if (debugMode == true) {
          System.out.println(CellAddress); // DEBUG
        }
        String temp = CellAddress.toString(); // convert CellAdress to String

        char firstChar = temp.charAt(0); // take first Character from the newly created String

        if (firstChar == 'A') { // check if its from Column A -> URL
          CurrentStringURL = cell.getStringCellValue();
          if (debugMode == true) {
            System.out.println(CurrentStringURL);
          } //DEBUG
        }

        if (firstChar == 'B') { // check if its from Column B -> PATH
          CurrentStringPATH = cell.getStringCellValue();
          if (debugMode == true) {
            System.out.println(CurrentStringPATH);
          } //DEBUG
        }
      }
      CurrentStringURL = CurrentStringURL.replace("/html/", "/art/"); //Replace the subirectory
      CurrentStringURL = CurrentStringURL.replace("html", "jpg"); // Replace .html w/ .jpg -> direct link to image
      String randomString = RandomStringUtils.randomAlphanumeric(10); // generate random String so that the files dont overwrite each other
      CurrentStringPATH =
        "./output/" +
        "idString=[" +
        randomString +
        "]_" +
        "year=[" +
        CurrentStringPATH +
        "]" +
        ".jpg"; // Convert "number" into usable file path and add a random String.
      // On a Sidenote: Why is the automatic code formatter in VS Code so bad lol, just look at what it did to my CurrenStringPATH
      if (debugMode == true) {
        System.out.println("Updated URL Input: " + CurrentStringURL);
      } // DEBUG
      if (debugMode == true) {
        System.out.println("Updated PATH Input: " + CurrentStringPATH);
      } // DEBUG
      DownloadImage(CurrentStringURL, CurrentStringPATH); // execute ImageDownloader

      while (canContinue == false) { // wait until the image has finished downloading in case something goes wrong in the ImageDownloader
        System.out.println("Waiting for image to download...");
      }
    }
    workbook.close(); // close IO-Stuff
    inputStream.close(); // close IO-Stuff
  }
}
// this took way longer than it should have -Optinux
