/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gentrepot.models;

/**
 *
 * @author guiforodrigue
 */
public enum Libelle {
    LAPTOP("L", "laptop"), IMPRIMANTE("I", "Imprimante"), PROJECTEUR("P", "Projecteur");
 
   private String code;
   private String text;
 
   private Libelle(String code, String text) {
       this.code = code;
       this.text = text;
   }
 
   public String getCode() {
       return code;
   }
 
   public String getText() {
       return text;
   }
 
   public static Libelle getByCode(String genderCode) {
       for (Libelle g : Libelle.values()) {
           if (g.code.equals(genderCode)) {
               return g;
           }
       }
       return null;
   }
 
   @Override
   public String toString() {
       return this.text;
   }
}
