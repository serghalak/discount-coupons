package com.exadel.sandbox.service.statistics;

import com.exadel.sandbox.dto.response.statistics.StatisticsResponse;
import com.exadel.sandbox.service.StatisticsService;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class StatisticsReportToExcel {
    private String[] sheetNames = {"Order Statistics", "Saved Statistics", "Viewed Statistics"};
    private int rowNum = 0;
    Map<Integer, List<StatisticsResponse>> data;
    @Autowired
    private StatisticsService statisticsService;

    public ByteArrayOutputStream  export(LocalDate dateBegin, LocalDate dateEnd) throws Exception {
        Workbook workbook = new HSSFWorkbook();
        ByteArrayOutputStream baos = new ByteArrayOutputStream(10000);

        data = getData(dateBegin, dateEnd);
        Sheet sheet = null;

        for (String sheetName : sheetNames) {
            sheet = workbook.createSheet(sheetName);
        }

        int numberOfSheets = workbook.getNumberOfSheets();

        for (int i = 0; i < numberOfSheets; i++) {
            sheet = workbook.getSheet(sheetNames[i]);
              Row row = sheet.createRow(rowNum);
            row.createCell(0).setCellValue("Event Id");
            row.createCell(1).setCellValue("Event description");
            row.createCell(2).setCellValue("Vendor           ");
            row.createCell(3).setCellValue("Total count");
            sheet.autoSizeColumn(0);
            sheet.autoSizeColumn(1);
            sheet.autoSizeColumn(2);
            sheet.autoSizeColumn(3);
        }

        for (int i = 0; i < numberOfSheets; i++) {
            sheet = workbook.getSheet(sheetNames[i]);
            List<StatisticsResponse> list = data.get(i);
            for (int j = 0; j < list.size(); j++) {
                Row row = sheet.createRow(j + 1);
                row.createCell(0).setCellValue(list.get(j).getEventId());
                row.createCell(1).setCellValue(list.get(j).getEventDescription());
                row.createCell(2).setCellValue(list.get(j).getVendorName());
                row.createCell(3).setCellValue(list.get(j).getCount());
            }
        }

        workbook.write(baos);
        return baos;
    }

    private Map<Integer, List<StatisticsResponse>> getData(LocalDate dateBegin, LocalDate dateEnd) {
        Map<Integer, List<StatisticsResponse>> dats = new HashMap<>();
        dats.put(0, statisticsService.getAllEventsFromUserOrderForPeriod(dateBegin, dateEnd, 100));
        dats.put(1, statisticsService.getAllEventsFromUserSavedForPeriod(dateBegin, dateEnd, 100));
        dats.put(2, statisticsService.getAllEventsFromViewedForPeriod(dateBegin, dateEnd, 100));
        return dats;
    }
}
