package com.reaktorlabs.sam.logic.util;

import com.reaktorlabs.sam.repository.model.asset.Asset;
import com.reaktorlabs.sam.repository.model.asset.AssetStatusEnum;
import com.reaktorlabs.sam.repository.model.samuser.SamUser;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;
import static org.junit.Assert.*;

public class ExcelWriterTest {

    @Test
    public void testExcelWriter() throws ParseException, IOException {
        Asset testAsset = new Asset();
        SamUser user = new SamUser();
        SimpleDateFormat testDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date testDate = testDateFormat.parse("01-01-2000");
        user.setFirstName("Storage");
        user.setLastName("Public");
        testAsset.setAssetName("Monitor");
        testAsset.setManufacturer("LG");
        testAsset.setModel("IPS624");
        testAsset.setSerialNumber("PGK-634");
        testAsset.setAssetTag("MON-0000");
        testAsset.setStatus(AssetStatusEnum.STORED);
        testAsset.setOwner(user);
        testAsset.setSupportEndDate(testDate);
        
        List<Asset> assets = Arrays.asList(testAsset);
        byte[] excelContent = ExcelWriter.excelWriter(assets);
        String testAssetName = excelReader(excelContent, 1, 0);
        String testManufacturer = excelReader(excelContent, 1, 1);
        String testModel = excelReader(excelContent, 1, 2);
        String testSerialNumber = excelReader(excelContent, 1, 3);
        String testAssetTag = excelReader(excelContent, 1, 4);
        String testStatus = excelReader(excelContent, 1, 5);
        String testOwner = excelReader(excelContent, 1, 6);
        String testSupportEndDate = excelReader(excelContent, 1, 7);
        String testInStock = excelReader(excelContent, 0, 8);
        String convertedTestDate = (String) testAsset.getSupportEndDate().toString();
        
        assertNotNull(excelContent);
        assertEquals("Monitor", testAssetName);
        assertEquals("LG", testManufacturer);
        assertEquals("IPS624", testModel);
        assertEquals("PGK-634", testSerialNumber);
        assertEquals("MON-0000", testAssetTag);
        assertEquals("STORED", testStatus);
        assertEquals("Public Storage", testOwner);
        assertEquals(convertedTestDate, testSupportEndDate);
        assertEquals("In stock", testInStock);
        
    }
    
    private String excelReader(byte[] testContent, int rowNumber, int cellNumber) throws IOException {
        ByteArrayInputStream input = new ByteArrayInputStream(testContent);
         XSSFWorkbook workbook = new XSSFWorkbook(input);
    XSSFSheet worksheet = workbook.getSheetAt(0);
    XSSFRow testRow = worksheet.getRow(rowNumber);
    XSSFCell testCell = testRow.getCell(cellNumber);
    return testCell.getStringCellValue();
    }
}
