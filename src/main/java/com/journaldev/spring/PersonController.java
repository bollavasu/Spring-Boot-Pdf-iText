package com.journaldev.spring;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.net.URL;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;
import com.itextpdf.text.*;

@RestController
public class PersonController {
	
	@RequestMapping("/")
	public String healthCheck() {
		readPdf();
		writePdf();
		setFileAttributes();
		addImageToPdf();
		generatePasswordProtectedPdf();
		addTablesInPdf();
		return "PDF file is Processed Successfully...";
	}
	
	public void readPdf() {
		PdfReader reader;
		
        try {
            reader = new PdfReader("C:/Input/Temp.pdf");

            // pageNumber = 1
            String textFromPage = PdfTextExtractor.getTextFromPage(reader, 1);
            System.out.println(textFromPage);
            reader.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	
	private static void writePdf() {

        Document document = new Document();

        try {

            PdfWriter.getInstance(document, new FileOutputStream(new File("C:/Output/TempOut.pdf")));

            //open
            document.open();

            Paragraph p = new Paragraph();
            p.add("This is my paragraph 1");
            p.setAlignment(Element.ALIGN_CENTER);

            document.add(p);

            Paragraph p2 = new Paragraph();
            p2.add("This is my paragraph 2"); //no alignment

            document.add(p2);

            Font f = new Font();
            f.setStyle(Font.BOLD);
            f.setSize(8);

            document.add(new Paragraph("This is my paragraph 3", f));

            //close
            document.close();

            System.out.println("Done");
         
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
	
	public void setFileAttributes() {
		
		Document document = new Document();
		try
		{
		    PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("C:/Output/SetAttributeExample.pdf"));
		    document.open();
		    document.add(new Paragraph("Some content here"));
		 
		    //Set attributes here
		    document.addAuthor("Lokesh Gupta");
		    document.addCreationDate();
		    document.addCreator("HowToDoInJava.com");
		    document.addTitle("Set Attribute Example");
		    document.addSubject("An example to show how attributes can be added to pdf files.");
		 
		    document.close();
		    writer.close();
		} catch (Exception e)
		{
		    e.printStackTrace();
		}
	}
	
	public void addImageToPdf() {
		Document document = new Document();
		try
		{
		    PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("C:/Output/AddImageExample.pdf"));
		    document.open();
		    document.add(new Paragraph("Image Example"));
		 
		    //Add Image
		    Image image1 = Image.getInstance("temp.png");
		    //Fixed Positioning
		    image1.setAbsolutePosition(100f, 550f);
		    //Scale to new height and new width of image
		    image1.scaleAbsolute(200, 200);
		    //Add to document
		    document.add(image1);
		 
		    String imageUrl = "http://www.eclipse.org/xtend/images/java8_logo.png";
		    Image image2 = Image.getInstance(new URL(imageUrl));
		    document.add(image2);
		 
		    document.close();
		    writer.close();
		} catch (Exception e)
		{
		    e.printStackTrace();
		}
	}
	
	public void generatePasswordProtectedPdf() {
		String USER_PASSWORD = "password";
		String OWNER_PASSWORD = "lokesh";
		try
	    {
	        OutputStream file = new FileOutputStream(new File("C:/Output/PasswordProtected.pdf"));
	        Document document = new Document();
	        PdfWriter writer = PdfWriter.getInstance(document, file);
	 
	        writer.setEncryption(USER_PASSWORD.getBytes(),
	                OWNER_PASSWORD.getBytes(), PdfWriter.ALLOW_PRINTING,
	                PdfWriter.ENCRYPTION_AES_128);
	 
	        document.open();
	        document.add(new Paragraph("Password Protected pdf example !!"));
	        document.close();
	        file.close();
	 
	    } catch (Exception e)
	    {
	        e.printStackTrace();
	    }
	}
	
	public void addTablesInPdf() {
		Document document = new Document();
	    try
	    {
	        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("C:/Output/AddTableExample.pdf"));
	        document.open();
	 
	        PdfPTable table = new PdfPTable(3); // 3 columns.
	        table.setWidthPercentage(100); //Width 100%
	        table.setSpacingBefore(10f); //Space before table
	        table.setSpacingAfter(10f); //Space after table
	 
	        //Set Column widths
	        float[] columnWidths = {1f, 1f, 1f};
	        table.setWidths(columnWidths);
	 
	        PdfPCell cell1 = new PdfPCell(new Paragraph("Cell 1"));
	        cell1.setBorderColor(BaseColor.BLUE);
	        cell1.setPaddingLeft(10);
	        cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
	        cell1.setVerticalAlignment(Element.ALIGN_MIDDLE);
	 
	        PdfPCell cell2 = new PdfPCell(new Paragraph("Cell 2"));
	        cell2.setBorderColor(BaseColor.GREEN);
	        cell2.setPaddingLeft(10);
	        cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
	        cell2.setVerticalAlignment(Element.ALIGN_MIDDLE);
	 
	        PdfPCell cell3 = new PdfPCell(new Paragraph("Cell 3"));
	        cell3.setBorderColor(BaseColor.RED);
	        cell3.setPaddingLeft(10);
	        cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
	        cell3.setVerticalAlignment(Element.ALIGN_MIDDLE);
	 	 
	        table.addCell(cell1);
	        table.addCell(cell2);
	        table.addCell(cell3);
	 
	        document.add(table);
	 
	        document.close();
	        writer.close();
	    } catch (Exception e)
	    {
	        e.printStackTrace();
	    }
	}
}
