/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gentrepot.utils;

import com.gentrepot.models.BonEntree;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;

/**
 *
 * @author Mohamed
 */
public class PdfB {
   public void GeneratePdf(String filename,BonEntree bb) throws FileNotFoundException, DocumentException, BadElementException, IOException, InterruptedException, SQLException
    {
        Document document=new  Document();
        PdfWriter.getInstance(document, new FileOutputStream(filename+".pdf"));
        document.open();
        
        document.add(new Paragraph("Date Creation:"+bb.getDate()+"\n"));
          document.add(new Paragraph("Date retour:"+bb.getDateExpiration()+"\n"));
             document.add(new Paragraph("Date sortie:"+bb.getDateProduction()+"\n"));
        document.add(new Paragraph("---------------------------------------------------------------------------------------------------------------------------------- "));
        document.close();
    
}}

