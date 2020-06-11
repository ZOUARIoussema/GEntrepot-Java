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
import com.itextpdf.text.Font;
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
        document.add(new Paragraph("Le bon d entree correspondant a ce numero de commande d'approvisionnement : "+bb.getCommandeDApprovisionnement()+"\n"));
        document.add(new Paragraph("sa date Creation du bon d entree est le "+bb.getDate()+"\n"));
          document.add(new Paragraph("Sa date d'expiration est le "+bb.getDateExpiration()+"\n"));
             document.add(new Paragraph("sa date de prodution est le"+bb.getDateProduction()+"\n"));
             
             Font f = new Font();
            f.setStyle(Font.BOLD);
            f.setSize(8);
        document.add(new Paragraph("---------------------------------------------------------------------------------------------------------------------------------- "));
        document.close();
    
}}

