/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gentrepot.utils;


import com.gentrepot.models.OrdreMission;
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
 * @author Rym
 */
public class PDF {
    
    public void GeneratePdf(String filename,OrdreMission o) throws FileNotFoundException, DocumentException, BadElementException, IOException, InterruptedException, SQLException
    {
        Document document=new  Document();
        PdfWriter.getInstance(document, new FileOutputStream(filename+".pdf"));
        document.open();
        document.add(new Paragraph("Vehicule :"+o.getVehicule().getMatricule()+"\n"));
        document.add(new Paragraph("Chauffeur :"+o.getChauffeur().getNom()+"\n"));
        document.add(new Paragraph("Aide chauffeur :"+o.getAideChauffeur().getNom()+"\n"));
        document.add(new Paragraph("Date Creation:"+o.getDateCreation()+"\n"));
          document.add(new Paragraph("Date retour:"+o.getDateRetour()+"\n"));
             document.add(new Paragraph("Date sortie:"+o.getDateSortie()+"\n"));
        document.add(new Paragraph("---------------------------------------------------------------------------------------------------------------------------------- "));
        document.close();
    
}}
