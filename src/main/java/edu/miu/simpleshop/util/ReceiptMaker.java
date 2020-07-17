package edu.miu.simpleshop.util;

import com.lowagie.text.DocumentException;
import edu.miu.simpleshop.domain.Order;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.*;

public class ReceiptMaker {


    public static void prepareOrderReceipt(Order order) {
        Thread newThread = new Thread(() -> {
            makeReceipt(order);
        });
        newThread.start();
    }

    private static void makeReceipt(Order order) {
        try {
            String html = parseTemplateForOrderDetails(order);
            generatePdfFromHtml(html, order);
        } catch (DocumentException | IOException e) {
            e.printStackTrace();
        }
    }

    private static void generatePdfFromHtml(String html, Order order) throws DocumentException, IOException {
        String outputFolder = "src\\main\\resources\\static\\documents\\receipts\\order-receipt"
                + order.getBillingInfo().getOrderNumber() + ".pdf";
        OutputStream outputStream = new FileOutputStream(outputFolder);

        ITextRenderer renderer = new ITextRenderer();
        renderer.setDocumentFromString(html);
        renderer.layout();
        renderer.createPDF(outputStream);

        outputStream.close();
    }

    private static String parseTemplateForOrderDetails(Order order) {
        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode(TemplateMode.HTML);

        TemplateEngine templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);

        Context context = new Context();
        context.setVariable("order", order);

        return templateEngine.process("templates/order/order_receipt_template", context);
    }
}
