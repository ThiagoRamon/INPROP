package com.inprop.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class FormatoData {
    private Date data;
    private DateFormat dataFormat;

  

          public FormatoData(){
              dataFormat = DateFormat.getInstance();
              data=new Date();
          }

          public String getData_final(Date data,int dias){
              Calendar cal =  Calendar.getInstance();
              cal.setTime(data);
              DateFormat dt = new SimpleDateFormat("yyyy-MM-dd");
              cal.add(cal.DAY_OF_WEEK, dias);
          return dt.format(cal.getTime());
          }

    /**
     * @return the data
     */
    public String getData() {
        return dataFormat.format(data);
    }
   
    /**
     * @param data the data to set
     */
    public void setData(Date data) {
        this.data = data;
    }

    /**
     * @return the dataFormat
     */
    public DateFormat getDataFormat() {
        return dataFormat;
    }

    /**
     * @param dataFormat the dataFormat to set
     */
    public void setDataFormat(DateFormat dataFormat) {
        this.dataFormat = dataFormat;
    }

   public void setFormat(String formato){
   this.dataFormat =new SimpleDateFormat(formato);
   }
   public String  data_atual(){
         DateFormat fd = new  SimpleDateFormat("yyyy-MM-dd");
         Date dt = new Date();
         return fd.format(dt);
   }


}
