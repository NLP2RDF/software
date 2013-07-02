package org.nlp2rdf.core;

import com.hp.hpl.jena.datatypes.xsd.XSDDatatype;
import com.hp.hpl.jena.datatypes.xsd.XSDDateTime;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;

import java.util.Calendar;

/**
 * User: hellmann
 * Date: 28.06.13
 */
public class ISOTime {


    public static XSDDatatype getXSDDatetime() {
        return (new XSDDateTime(Calendar.getInstance())).getNarrowedDatatype();
    }

    public static String xsddatetime(long date) {
        /*******************************************************************
         * Finally, we succeed in creating ISO conform dateTime strings!
         *******************************************************************/
        DateTimeFormatter formatter = ISODateTimeFormat.dateTimeNoMillis();
        //DateTime dt = new DateTime();
        //return dt.toString(formatter);
        return formatter.print(date);
    }
}
