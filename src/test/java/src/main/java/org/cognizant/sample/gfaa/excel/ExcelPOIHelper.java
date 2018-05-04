package org.cognizant.sample.gfaa.excel;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.FillPatternType;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;

public class ExcelPOIHelper {

	List<Record> list = new ArrayList<Record>(); 
    public void readExcel(String fileLocation, String apiFileLocation) throws IOException {

        Map<Integer, List<String>> data = new HashMap<>();
        FileInputStream file = new FileInputStream(new File(fileLocation));
        FileInputStream apiFile = new FileInputStream(new File(apiFileLocation));
        Workbook workbook = new XSSFWorkbook(file);
        Workbook apiWorkbook = new XSSFWorkbook(apiFile);
        Sheet sheet = workbook.getSheetAt(0);
        Sheet apiSheet = apiWorkbook.getSheetAt(0);
        int i = 0;
        
		for (int rowNumber = 1; rowNumber <= sheet.getLastRowNum(); rowNumber++) {
			Row row = sheet.getRow(rowNumber);
			Record record = new Record();
			record.setRptField(row.getCell(0).toString());
			record.setIoField(row.getCell(1).toString());

			for (int apiRowNumber = 1; apiRowNumber < sheet.getLastRowNum(); apiRowNumber++) {
				Row apiRow = apiSheet.getRow(apiRowNumber);
				String val = apiRow.getCell(2).toString();
				if (val != null && val.equals(row.getCell(1).toString())) {
					record.setiHubApiField(apiRow.getCell(0).toString());
					break;
				}
			}
			
			list.add(record);

		}
		
		for(Record r : list){
			System.out.println(r);
		}
           
        if (workbook != null){
            workbook.close();
        }
       
    }

    public void writeExcel() throws IOException {
        Workbook workbook = new XSSFWorkbook();

        try {
            Sheet sheet = workbook.createSheet("Report");
            sheet.setColumnWidth(0, 6000);
            sheet.setColumnWidth(1, 4000);
            sheet.setColumnWidth(1, 6000);

            Row header = sheet.createRow(0);

            CellStyle headerStyle = workbook.createCellStyle();

            headerStyle.setFillForegroundColor(IndexedColors.LIGHT_BLUE.getIndex());
            headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

            XSSFFont font = ((XSSFWorkbook) workbook).createFont();
            font.setFontName("Arial");
            font.setFontHeightInPoints((short) 16);
            font.setBold(true);
            headerStyle.setFont(font);

            Cell headerCell = header.createCell(0);
            headerCell.setCellValue("Report Field");
            headerCell.setCellStyle(headerStyle);

            headerCell = header.createCell(1);
            headerCell.setCellValue("IO Field");
            headerCell.setCellStyle(headerStyle);
            
            headerCell = header.createCell(2);
            headerCell.setCellValue("API Field");
            headerCell.setCellStyle(headerStyle);

            CellStyle style = workbook.createCellStyle();
            style.setWrapText(true);
            int index = 0;
            for(Record r : list){
            	Row row = sheet.createRow(++index);
            	Cell cell = row.createCell(0);
                cell.setCellValue(r.getRptField());
                cell.setCellStyle(style);
                
                cell = row.createCell(1);
                cell.setCellValue(r.getIoField());
                cell.setCellStyle(style);
                
                cell = row.createCell(2);
                cell.setCellValue(r.getiHubApiField());
                cell.setCellStyle(style);
            }
            /*Row row = sheet.createRow(2);
            Cell cell = row.createCell(0);
            cell.setCellValue("John Smith");
            cell.setCellStyle(style);

            cell = row.createCell(1);
            cell.setCellValue(20);
            cell.setCellStyle(style);*/

            File currDir = new File(".");
            String path = currDir.getAbsolutePath();
            String fileLocation = "F:\\report.xlsx";

            FileOutputStream outputStream = new FileOutputStream(fileLocation);
            workbook.write(outputStream);
        } finally {
            if (workbook != null) {
               
                    workbook.close();
               
            }
        }
    }
    
    
    public static void main(String args[]) throws IOException{
    	ExcelPOIHelper excelPOIHelper = new ExcelPOIHelper();
    	excelPOIHelper.readExcel("F:\\extract.xlsx", "F:\\api_mapping.xlsx");
    	excelPOIHelper.writeExcel();
    }

}




class Record{
	private String rptField;
	private String ioField;
	private String iHubApiField;
	public String getRptField() {
		return rptField;
	}
	public void setRptField(String rptField) {
		this.rptField = rptField;
	}
	public String getIoField() {
		return ioField;
	}
	@Override
	public String toString() {
		return "Record [rptField=" + rptField + ", ioField=" + ioField + ", iHubApiField=" + iHubApiField + "]";
	}
	public void setIoField(String ioField) {
		this.ioField = ioField;
	}
	public String getiHubApiField() {
		return iHubApiField;
	}
	public void setiHubApiField(String iHubApiField) {
		this.iHubApiField = iHubApiField;
	}
	
	
}