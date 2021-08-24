package org.vitalii.utils;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.vitalii.model.SingleRate;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class PdfConvert {

    private List<SingleRate> singleRates;
    private static final String FONT = "D:\\arial.ttf";


    public PdfConvert(List<SingleRate> singleRates) {
        this.singleRates = singleRates;
    }

    public void createPDF() {
        try {
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream("D:\\exchangeRate2.pdf"));

            BaseFont baseFont = BaseFont.createFont(FONT, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
            Font font = new Font(baseFont,8, Font.NORMAL);

            document.open();

            PdfPTable table = new PdfPTable(7);
            addTableHeader(table, font);

            for(SingleRate singleRate : singleRates) {
                addRows(table, font, singleRate);
            }
            document.add(table);
            document.close();
        }catch (Exception e){

        }
    }
    private void addTableHeader(PdfPTable table, Font font){
        Stream.of("Базовая валюта", "Валюта сделки", "Курс продажи НБУ", "Курс покупки НБУ", "Курс продажи ПриватБанка", "Курс покупки ПриватБанка", "Дата")
                .forEach(columnTitle -> {
                    PdfPCell header = new PdfPCell();
                    header.setBackgroundColor(BaseColor.LIGHT_GRAY);
                    header.setBorderWidth(2);
                    header.setPhrase(new Phrase(columnTitle, font));
                    table.addCell(header);
                });
    }

    private void addRows(PdfPTable table, Font font, SingleRate singleRate) {
        List<String> cells = new ArrayList<>();

        cells.add(singleRate.getBaseCurrency());
        cells.add(singleRate.getCurrency());
        cells.add(String.valueOf(singleRate.getSaleRateNB()));
        cells.add(String.valueOf(singleRate.getPurchaseRateNB()));
        cells.add(String.valueOf(singleRate.getSaleRate()));
        cells.add(String.valueOf(singleRate.getPurchaseRate()));

        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        cells.add(sdf.format(singleRate.getExchangeRate().getDate()));

        for(String info : cells){
            PdfPCell cell = new PdfPCell();
            cell.setPhrase(new Phrase(info, font));
            table.addCell(cell);
        }
    }
}



