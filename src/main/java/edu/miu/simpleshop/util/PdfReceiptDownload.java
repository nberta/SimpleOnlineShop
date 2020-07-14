package edu.miu.simpleshop.util;

import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import edu.miu.simpleshop.domain.Order;
import edu.miu.simpleshop.domain.OrderLine;
import org.slf4j.Logger;
import com.itextpdf.text.*;
import org.slf4j.LoggerFactory;

import java.awt.*;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

public class PdfReceiptDownload {
    private static Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.BOLD);
    private static Font titleFont = new Font(Font.FontFamily.TIMES_ROMAN, 16, Font.BOLD);
    private static final Logger logger = LoggerFactory.getLogger(PdfReceiptDownload.class);

    public static ByteArrayInputStream Report(Order order) {
        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
            PdfPTable table = new PdfPTable(6);
            table.setWidthPercentage(100);
            table.setWidths(new int[]{3, 1, 1, 1, 1, 1});

            Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
            PdfPCell hcell;
            hcell = new PdfPCell(new Phrase("Product", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);

            hcell = new PdfPCell(new Phrase("Price", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);

            hcell = new PdfPCell(new Phrase("Quantity", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);

//            hcell = new PdfPCell(new Phrase("Point payment", headFont));
//            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
//            table.addCell(hcell);
//            hcell = new PdfPCell(new Phrase("Cash payment", headFont));
//            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
//            table.addCell(hcell);

            hcell = new PdfPCell(new Phrase("Total", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);
            int totalQuantity = 0;
            float totalSum = 0;
            float totalPointSum = 0;
            float totalCashSum = 0;
            for (OrderLine orderLine : order.getOrderLines()) {
                PdfPCell cell;
                cell = new PdfPCell(new Phrase(orderLine.getProduct().toString()));
                cell.setPaddingLeft(5);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);
                cell = new PdfPCell(new Phrase(String.valueOf(orderLine.getProduct().getPrice())));
                cell.setPaddingLeft(5);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                table.addCell(cell);
                int quantity = orderLine.getQuantity();
                totalQuantity += quantity;
                cell = new PdfPCell(new Phrase(String.valueOf(quantity)));
                cell.setPaddingLeft(5);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                table.addCell(cell);

//                float cash = orderLine.getCashPayment();
//                totalCashSum += cash;
//                cell = new PdfPCell(new Phrase(String.valueOf(cash)));
//                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
//                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
//                cell.setPaddingRight(5);
//                table.addCell(cell);

                Double total = order.getTotalCost();
                totalSum += total;
                cell = new PdfPCell(new Phrase(String.valueOf(total)));
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setPaddingRight(5);
                table.addCell(cell);
            }

            headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
            hcell = new PdfPCell(new Phrase("Total sum", headFont));
            hcell.setColspan(2);
            hcell.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(hcell);

            hcell = new PdfPCell(new Phrase(totalQuantity + "", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            table.addCell(hcell);

//            hcell = new PdfPCell(new Phrase(totalPointSum + "", headFont));
//            hcell.setHorizontalAlignment(Element.ALIGN_RIGHT);
//            table.addCell(hcell);
//
//            hcell = new PdfPCell(new Phrase(totalCashSum + "", headFont));
//            hcell.setHorizontalAlignment(Element.ALIGN_RIGHT);
//            table.addCell(hcell);

            hcell = new PdfPCell(new Phrase(totalSum + "", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            table.addCell(hcell);

            PdfWriter.getInstance(document, out);
            document.open();
            Anchor anchor = new Anchor("Recipt #" + order.getId(), catFont);
            Paragraph paragraph = new Paragraph();
            paragraph.add(new Paragraph("Seller: " + order.getOrderLines()));

            paragraph.add(new Paragraph("Shipping address: " + order.getShippingAddress()));

            Paragraph title = new Paragraph("MIU Online shopping", titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);
            document.add(anchor);
            document.add(paragraph);
            document.add(Chunk.NEWLINE);
            document.add(table);

            document.close();
        } catch (DocumentException ex) {

            logger.error("Error occurred: {0}", ex);
        }

        return new ByteArrayInputStream(out.toByteArray());

    }

}
