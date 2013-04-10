package nl.tudelft.tbm.eeni.owl2java.model.xsd;

import com.hp.hpl.jena.datatypes.xsd.XSDDatatype;
import com.hp.hpl.jena.datatypes.xsd.XSDDateTime;
import com.hp.hpl.jena.datatypes.xsd.XSDDuration;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;


public class XsdMapTestData {

    @SuppressWarnings("unused")
    private static Log log = LogFactory.getLog(XsdUtils.class);

    public static Map<String, String> type2TestValue = new HashMap<String, String>();


    static {
        type2TestValue.put("java.math.BigDecimal", "nl.tudelft.tbm.eeni.owl2java.model.xsd.XsdMapTestData.getBigDecimal");
        type2TestValue.put("java.math.BigInteger", "nl.tudelft.tbm.eeni.owl2java.model.xsd.XsdMapTestData.getBigInteger");
        type2TestValue.put("java.lang.Boolean", "nl.tudelft.tbm.eeni.owl2java.model.xsd.XsdMapTestData.getBoolean");
        type2TestValue.put("java.lang.Byte", "nl.tudelft.tbm.eeni.owl2java.model.xsd.XsdMapTestData.getByte");
        type2TestValue.put("java.lang.Character", "nl.tudelft.tbm.eeni.owl2java.model.xsd.XsdMapTestData.getCharacter");
        type2TestValue.put("java.lang.Double", "nl.tudelft.tbm.eeni.owl2java.model.xsd.XsdMapTestData.getDouble");
        type2TestValue.put("java.lang.Float", "nl.tudelft.tbm.eeni.owl2java.model.xsd.XsdMapTestData.getFloat");
        type2TestValue.put("java.lang.Integer", "nl.tudelft.tbm.eeni.owl2java.model.xsd.XsdMapTestData.getInteger");
        type2TestValue.put("java.lang.Long", "nl.tudelft.tbm.eeni.owl2java.model.xsd.XsdMapTestData.getLong");
        type2TestValue.put("java.lang.Short", "nl.tudelft.tbm.eeni.owl2java.model.xsd.XsdMapTestData.getShort");
        type2TestValue.put("java.lang.String", "nl.tudelft.tbm.eeni.owl2java.model.xsd.XsdMapTestData.getString");
        type2TestValue.put("java.util.Calendar", "nl.tudelft.tbm.eeni.owl2java.model.xsd.XsdMapTestData.getCalendar");
        type2TestValue.put("com.hp.hpl.jena.datatypes.xsd.XSDDuration",
                "nl.tudelft.tbm.eeni.owl2java.model.xsd.XsdMapTestData.getXSDDuration");
        type2TestValue.put("com.hp.hpl.jena.datatypes.xsd.XSDDateTime",
                "nl.tudelft.tbm.eeni.owl2java.model.xsd.XsdMapTestData.getXSDDateTime");
    }

    public static String getMethodName(String javaType) {
        return type2TestValue.get(javaType);
    }

    public static BigDecimal getBigDecimal(String dataTypeUri) {
        return new BigDecimal(12.23);
    }

    public static BigInteger getBigInteger(String dataTypeUri) {
        if (dataTypeUri.equals("http://www.w3.org/2001/XMLSchema#nonPositiveInteger"))
            return new BigInteger("-100");
        if (dataTypeUri.equals("http://www.w3.org/2001/XMLSchema#negativeInteger"))
            return new BigInteger("-100");
        return new BigInteger("100");
    }

    public static Boolean getBoolean(String dataTypeUri) {
        return new Boolean(false);
    }

    public static Byte getByte(String dataTypeUri) {
        return new Byte((byte) 12);
    }

    public static Calendar getCalendar(String dataTypeUri) {
        return Calendar.getInstance();
    }

    public static Character getCharacter(String dataTypeUri) {
        return new Character('c');
    }

    public static Double getDouble(String dataTypeUri) {
        return new Double(23.45);
    }

    public static Float getFloat(String dataTypeUri) {
        return new Float(12.24);
    }

    public static Integer getInteger(String dataTypeUri) {
        return new Integer(12);
    }

    public static Long getLong(String dataTypeUri) {
        return new Long((long) 100);
    }

    public static Short getShort(String dataTypeUri) {
        return new Short((short) 10);
    }

    public static String getString(String dataTypeUri) {
        if (dataTypeUri.equals("http://www.w3.org/2001/XMLSchema#language"))
            return "DE";
        if (dataTypeUri.equals("http://www.w3.org/2001/XMLSchema#base64Binary")) {
            String strg = "myTest";
            return new String(org.apache.commons.codec.binary.Base64.encodeBase64(strg.getBytes()));
        }
        if (dataTypeUri.equals("http://www.w3.org/2001/XMLSchema#hexBinary")) {
            String strg = "myTest";
            return new String(Hex.encodeHex(strg.getBytes()));
        }
        if (dataTypeUri.equals("http://www.w3.org/2001/XMLSchema#ENTITY")) {
            // TODO: no support for Xsd:ENTITY currently
            return "";
        }
        return "testString";
    }

    public static XSDDateTime getXSDDateTime(String dtUri) {
        int hashLocation = dtUri.lastIndexOf("#");
        String dtTypeName = dtUri.substring(hashLocation + 1);

        Calendar cal = Calendar.getInstance();
        XSDDateTime xdatetime = new XSDDateTime(cal);
        XSDDatatype xdatatype = new XSDDatatype(dtTypeName);
        xdatetime.narrowType(xdatatype);
        return xdatetime;
    }

    public static XSDDuration getXSDDuration(String dataTypeUri) {
        int[] dur = {0, 1, 2, 3, 4, 5, 6, 7, 8};
        return new XSDDuration(dur);
    }


}
