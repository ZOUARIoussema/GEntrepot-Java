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
public enum Classe {
     A("A", "A"), B("B", "B"), C("C", "C");
 
   private String code;
   private String text;
 
   private Classe(String code, String text) {
       this.code = code;
       this.text = text;
   }
 
   public String getCode() {
       return code;
   }
 
   public String getText() {
       return text;
   }
 
   public static Classe getByCode(String genderCode) {
       for (Classe g : Classe.values()) {
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
