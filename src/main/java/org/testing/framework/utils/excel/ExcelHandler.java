package org.testing.framework.utils.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;


/**
 * Created by Ravinder Singh on 03-11-2015.
 */
public class ExcelHandler {

    static Logger logger = LoggerFactory.getLogger(ExcelHandler.class.getName());

    private HSSFWorkbook workbook;
    private HSSFSheet currentSheet;
    private String fileLocation;

    /**
     * Sets up a excel handler with the given file location
     *
     * @param fileLocation The full file location of the document as a String
     * @throws Exception
     */
    public ExcelHandler(String fileLocation) throws Exception {
        this(new File(fileLocation));
    }

    /**
     * Sets up a excel handler with the given file
     *
     * @param file The file to open
     * @throws Exception
     */
    public ExcelHandler(File file) throws Exception {

        FileInputStream fileStream = null;
        this.fileLocation = file.getAbsolutePath();

        try {
            // Create file stream
            fileStream = new FileInputStream(file);

            // Get the workbook instance for XLS file
            workbook = new HSSFWorkbook(fileStream);

        } finally {
            try {
                fileStream.close();
            } catch (IOException e) {
                logger.error(e.getMessage());
            }
        }
    }

    /**
     * This will select the sheet in the document by its name
     *
     * @param sheetNumber The sheet number as an int
     */
    public void setSheet(String sheetName) {
        // Get the sheet number
        currentSheet = workbook.getSheet(sheetName);
    }

    /**
     * This will select the sheet in the document from its location
     *
     * @param sheetNumber The sheet number as an int
     */
    public void setSheet(int sheetNumber) {
        // Get the sheet number
        currentSheet = workbook.getSheetAt(sheetNumber);
    }

    /**
     * This will set the cell value on the currently selected sheet
     *
     * @param rowNumber The row number as an int
     * @param columnNumber	The column number as an int
     * @param value The value to add to the cell as a String
     * @throws Exception If a sheet is not selected
     */
    public void setCellValue(int rowNumber, int columnNumber, String value) throws Exception {
        // If there is a sheet selected, then update the cell value
        if(currentSheet != null) {
            Cell cell = currentSheet.getRow(rowNumber).getCell(columnNumber);
            cell.setCellValue(value);
        } else {
            throw new Exception("No sheet selected, select a sheet first");
        }
    }

    /**
     * This will save the document to the same location as setup in the constructor
     *
     * @throws Exception
     */
    public void saveFile() throws Exception {
        this.saveFile(this.fileLocation);
    }

    /**
     * This will save the document to a given file location
     *
     * @param fileLocation The full file location as a String
     * @throws Exception
     */
    public void saveFile(String fileLocation) throws Exception {
        FileOutputStream outFile = null;
        try {
            outFile = new FileOutputStream(fileLocation);
            workbook.write(outFile);
        } finally {
            try {
                outFile.close();
            } catch (IOException e) {
                logger.error(e.toString());
            }
        }
    }
}

