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
public enum Email {
      RODRIGUE("R", "rodrigue.guifotuekam@esprit.tn"), GUIFO("G", "rodricguifo@gmail.com");
 
   private String code;
   private String text;
 
   private Email(String code, String text) {
       this.code = code;
       this.text = text;
   }
 
   public String getCode() {
       return code;
   }
 
   public String getText() {
       return text;
   }
 
   public static Email getByCode(String genderCode) {
       for (Email g : Email.values()) {
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
