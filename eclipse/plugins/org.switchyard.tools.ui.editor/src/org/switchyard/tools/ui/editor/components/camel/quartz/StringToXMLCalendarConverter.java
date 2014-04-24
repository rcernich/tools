package org.switchyard.tools.ui.editor.components.camel.quartz;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.eclipse.core.databinding.conversion.Converter;

/**
 * Found part of this at 
 * http://tomsondev.bestsolution.at/2009/06/15/galileo-emf-databinding-part-4/.
 * Found part at
 * http://aboutyusata.blogspot.com/2013/10/string-to-gregorian-calendar-in-java.html. 
 */
public class StringToXMLCalendarConverter extends Converter {

    private String _message;

    /**
     * New converter.
     * @param message message when conversion fails
     */
    public StringToXMLCalendarConverter(String message) {
        super(String.class, GregorianCalendar.class);
        this._message = message;
    }

    @Override
    public Object convert(Object fromObject) {
        if (fromObject != null && fromObject.toString().trim().length() == 0) {
            return null;
        }
        String s = fromObject.toString();
        
        XMLGregorianCalendar result = null;
        Date date;
        
        // below line telling that which format we need the date
        SimpleDateFormat simpleDateFormat;
        GregorianCalendar gregorianCalendar;
        try {
            simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
            
            // below line converting our string to date
            date = simpleDateFormat.parse(s);
            
            // here we are getting the object of GregorianCalendar class
            gregorianCalendar =(GregorianCalendar) GregorianCalendar.getInstance();
            
            // by below we are setting the time of date into gregorianCalendar
            gregorianCalendar.setTime(date);
            result = DatatypeFactory.newInstance().newXMLGregorianCalendar(gregorianCalendar);

            return result;
        } catch (Exception ex) {
            throw new RuntimeException(_message);
        }
    }
}
