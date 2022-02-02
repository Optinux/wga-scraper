package studio.optinux.scraper;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ParseExcel extends ImageDownloader {

  public static String CurrentStringURL; // create variables to pass onto the Downloader
  public static String CurrentStringPATH; // create variables to pass onto the Downloader
  public static CellAddress CellAddress; // create variables to pass onto the Downloader

  public static void main(String[] args) throws IOException {
    String excelFilePath = "catalog-edit.xlsx"; // specify the file that is being used
    FileInputStream inputStream = new FileInputStream(new File(excelFilePath)); // start IO-Stuff

    Workbook workbook = new XSSFWorkbook(inputStream); // basically read the .xlsx
    org.apache.poi.ss.usermodel.Sheet firstSheet = workbook.getSheetAt(0);
    Iterator<Row> iterator = firstSheet.iterator();

    while (iterator.hasNext()) { // stop after there is no row left
      Row nextRow = iterator.next();
      Iterator<Cell> cellIterator = nextRow.cellIterator();

      while (cellIterator.hasNext()) { // stop after there is no cell left
        Cell cell = cellIterator.next(); // iterate through excel sheet cells

        CellAddress = cell.getAddress(); // get cellAdress
        System.out.println(CellAddress); // DEBUG

        String temp = CellAddress; // TODO: get this to work

        char firstChar = temp.charAt(0); // take first Character from the newly created String

        if (firstChar == 'A') { // check if its from Column A -> URL
          CurrentStringURL = cell.getStringCellValue();
          System.out.println(CurrentStringURL);
        }

        if (firstChar == 'B') { // check if its from Column B -> PATH
          CurrentStringPATH = cell.getStringCellValue();
          System.out.println(CurrentStringPATH);
        }
      }
      CurrentStringURL = CurrentStringURL.replace("html", "jpg"); // Replace .html w/ .jpg -> ImageDownloader
      CurrentStringPATH = "./output/" + CurrentStringPATH + ".jpg"; // Convert "number" into usable file path
      System.out.println("Updated URL Input: " + CurrentStringURL); // DEBUG
      System.out.println("Updated PATH Input: " + CurrentStringPATH); // DEBUG
      DownloadImage(CurrentStringURL, CurrentStringPATH); // execute ImageDownloader
    }
    workbook.close();
    inputStream.close();
  }
}
// this took way longer than it should have -Optinux
