package org.switchyard.tools.ui.editor.components.camel.quartz;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

import org.eclipse.core.databinding.conversion.Converter;

/**
 * Found part of this at 
 * http://tomsondev.bestsolution.at/2009/06/15/galileo-emf-databinding-part-4/.
 * Found part at
 * http://www.coderanch.com/t/412082/java/java/Convert-Calendar-String. 
 */
public class XMLCalendarToStringConverter extends Converter {

    private String _message;

    /**
     * New converter.
     * @param message message when conversion fails
     */
    public XMLCalendarToStringConverter(String message) {
        super(GregorianCalendar.class, String.class);
        this._message = message;
    }

    @Override
    public Object convert(Object fromObject) {
        if (fromObject == null) {
            return null;
        }
        final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        GregorianCalendar calendar = (GregorianCalendar) fromObject;
        try {
            Date thisDate = calendar.getTime();
            return sdf.format(thisDate);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        throw new RuntimeException(_message);
    }
}
