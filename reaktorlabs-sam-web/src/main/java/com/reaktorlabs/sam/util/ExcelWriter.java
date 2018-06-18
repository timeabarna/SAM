package com.reaktorlabs.sam.util;

import com.reaktorlabs.repository.model.Asset;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelWriter {

    public static void excelWriter(List<Asset> assets) {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("SAM_export");

        int rowNum = 1;
        int colNum = 0;
        Row row = sheet.createRow(0);
        Cell cell;
        List<String> header = new LinkedList<>(Arrays.asList("Asset name", "Manufacturer", "Model", "Serial number",
                                                "Asset tag", "Status", "Owner", "End of Support","In stock"));
        
        for (int i = 0; i < 9; i++) {
            cell = row.createCell(i);
            cell.setCellValue(header.get(i));
        }
  
        
        for (Asset asset : assets) {
            row = sheet.createRow(rowNum++);
        cell = row.createCell(colNum++);
            cell.setCellValue(asset.getAssetName());
            cell = row.createCell((colNum++));
            cell.setCellValue(asset.getManufacturer());
            cell = row.createCell((colNum++));
            cell.setCellValue(asset.getModel());
            cell = row.createCell((colNum++));
            cell.setCellValue(asset.getSerialNumber());
            cell = row.createCell((colNum++));
            cell.setCellValue(asset.getAssetTag());
            cell = row.createCell((colNum++));
            cell.setCellValue(asset.getStatus().toString());
            cell = row.createCell((colNum++));
            cell.setCellValue(asset.getOwner().getLastName()
                                + " " + asset.getOwner().getFirstName());
            cell = row.createCell((colNum++));
            //cell.setCellValue((String) asset.getSupportEndDate().toString());
            cell.setCellValue("date");
            colNum = 0; 
        }
        
        for (int i = 0; i < 9; i++) {
            sheet.autoSizeColumn(i);
        }
        
        try {
            FileOutputStream outputStream = new FileOutputStream("C:\\Users\\User\\Documents\\Course\\Sam\\simplifiedassetmanagement\\sam.xlsx");
            workbook.write(outputStream);
            workbook.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
