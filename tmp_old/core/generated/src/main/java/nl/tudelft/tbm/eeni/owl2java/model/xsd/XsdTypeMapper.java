package nl.tudelft.tbm.eeni.owl2java.model.xsd;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class XsdTypeMapper {

    @SuppressWarnings("unused")
    private static Log log = LogFactory.getLog(XsdTypeMapper.class);


    public static String getJavaClassName(String xsdUri) {
        return XsdMapConfig.xsd2javaName.get(xsdUri);
    }

    public static String getAccessMethod(String xsdUri) {
        String javaClass = XsdMapConfig.xsd2javaName.get(xsdUri);
        return XsdMapConfig.javaName2Method.get(javaClass);
    }

}
