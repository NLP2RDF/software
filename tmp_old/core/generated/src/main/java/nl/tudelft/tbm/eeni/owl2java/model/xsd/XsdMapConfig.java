package nl.tudelft.tbm.eeni.owl2java.model.xsd;

import java.util.HashMap;
import java.util.Map;

public class XsdMapConfig {

    /**
     * Usage / Design: - We map xsd types to java type names; - Then for each java type name the corresponding method to
     * get the value can be derived. This method is used to get the method name, type... from the templates.
     */
    public static Map<String, String> xsd2javaName = new HashMap<String, String>();
    public static Map<String, String> javaName2Method = new HashMap<String, String>();

    static {
        xsd2javaName.put(XsdSchema.xsdENTITY, "java.lang.String");
        xsd2javaName.put(XsdSchema.xsdID, "java.lang.String");
        xsd2javaName.put(XsdSchema.xsdIDREF, "java.lang.String");
        xsd2javaName.put(XsdSchema.xsdNCName, "java.lang.String");
        xsd2javaName.put(XsdSchema.xsdNMTOKEN, "java.lang.String");
        xsd2javaName.put(XsdSchema.xsdNOTATION, "java.lang.String");
        xsd2javaName.put(XsdSchema.xsdName, "java.lang.String");
        xsd2javaName.put(XsdSchema.xsdQName, "java.lang.String");
        xsd2javaName.put(XsdSchema.xsdanyURI, "java.lang.String");
        xsd2javaName.put(XsdSchema.xsdbase64Binary, "java.lang.String");
        xsd2javaName.put(XsdSchema.xsdboolean, "java.lang.Boolean");
        xsd2javaName.put(XsdSchema.xsdbyte, "java.lang.Byte");
        xsd2javaName.put(XsdSchema.xsddate, "java.util.Calendar");
        xsd2javaName.put(XsdSchema.xsddateTime, "java.util.Calendar");
        xsd2javaName.put(XsdSchema.xsddecimal, "java.math.BigDecimal");
        xsd2javaName.put(XsdSchema.xsddouble, "java.lang.Double");
        xsd2javaName.put(XsdSchema.xsdduration, "com.hp.hpl.jena.datatypes.xsd.XSDDuration");
        xsd2javaName.put(XsdSchema.xsdfloat, "java.lang.Float");
        xsd2javaName.put(XsdSchema.xsdgDay, "com.hp.hpl.jena.datatypes.xsd.XSDDateTime");
        xsd2javaName.put(XsdSchema.xsdgMonth, "com.hp.hpl.jena.datatypes.xsd.XSDDateTime");
        xsd2javaName.put(XsdSchema.xsdgMonthDay, "com.hp.hpl.jena.datatypes.xsd.XSDDateTime");
        xsd2javaName.put(XsdSchema.xsdgYear, "com.hp.hpl.jena.datatypes.xsd.XSDDateTime");
        xsd2javaName.put(XsdSchema.xsdgYearMonth, "com.hp.hpl.jena.datatypes.xsd.XSDDateTime");
        xsd2javaName.put(XsdSchema.xsdhexBinary, "java.lang.String");
        xsd2javaName.put(XsdSchema.xsdint, "java.lang.Integer");
        xsd2javaName.put(XsdSchema.xsdinteger, "java.math.BigInteger");
        xsd2javaName.put(XsdSchema.xsdlanguage, "java.lang.String");
        xsd2javaName.put(XsdSchema.xsdlong, "java.lang.Long");
        xsd2javaName.put(XsdSchema.xsdnegativeInteger, "java.math.BigInteger");
        xsd2javaName.put(XsdSchema.xsdnonNegativeInteger, "java.math.BigInteger");
        xsd2javaName.put(XsdSchema.xsdnonPositiveInteger, "java.math.BigInteger");
        xsd2javaName.put(XsdSchema.xsdnormalizedString, "java.lang.String");
        xsd2javaName.put(XsdSchema.xsdpositiveInteger, "java.math.BigInteger");
        xsd2javaName.put(XsdSchema.xsdshort, "java.lang.Short");
        xsd2javaName.put(XsdSchema.xsdstring, "java.lang.String");
        xsd2javaName.put(XsdSchema.xsdtime, "java.util.Calendar");
        xsd2javaName.put(XsdSchema.xsdtoken, "java.lang.String");
        xsd2javaName.put(XsdSchema.xsdunsignedByte, "java.lang.Short");
        xsd2javaName.put(XsdSchema.xsdunsignedInt, "java.lang.Integer");
        xsd2javaName.put(XsdSchema.xsdunsignedLong, "java.lang.Long");
        xsd2javaName.put(XsdSchema.xsdunsignedShort, "java.lang.Integer");
        xsd2javaName.put(XsdSchema.xsdLiteral, "java.lang.String");
    }

    static {
        javaName2Method.put("java.math.BigDecimal", "nl.tudelft.tbm.eeni.owl2java.model.xsd.XsdUtils.getBigDecimal");
        javaName2Method.put("java.math.BigInteger", "nl.tudelft.tbm.eeni.owl2java.model.xsd.XsdUtils.getBigInteger");
        javaName2Method.put("java.lang.Boolean", "nl.tudelft.tbm.eeni.owl2java.model.xsd.XsdUtils.getBoolean");
        javaName2Method.put("java.lang.Byte", "nl.tudelft.tbm.eeni.owl2java.model.xsd.XsdUtils.getByte");
        javaName2Method.put("java.lang.Character", "nl.tudelft.tbm.eeni.owl2java.model.xsd.XsdUtils.getCharacter");
        javaName2Method.put("java.lang.Double", "nl.tudelft.tbm.eeni.owl2java.model.xsd.XsdUtils.getDouble");
        javaName2Method.put("java.lang.Float", "nl.tudelft.tbm.eeni.owl2java.model.xsd.XsdUtils.getFloat");
        javaName2Method.put("java.lang.Integer", "nl.tudelft.tbm.eeni.owl2java.model.xsd.XsdUtils.getInteger");
        javaName2Method.put("java.lang.Long", "nl.tudelft.tbm.eeni.owl2java.model.xsd.XsdUtils.getLong");
        javaName2Method.put("java.lang.Short", "nl.tudelft.tbm.eeni.owl2java.model.xsd.XsdUtils.getShort");
        javaName2Method.put("java.lang.String", "nl.tudelft.tbm.eeni.owl2java.model.xsd.XsdUtils.getString");
        javaName2Method.put("java.util.Calendar", "nl.tudelft.tbm.eeni.owl2java.model.xsd.XsdUtils.getCalendar");
        javaName2Method.put("com.hp.hpl.jena.datatypes.xsd.XSDDuration",
                "nl.tudelft.tbm.eeni.owl2java.model.xsd.XsdUtils.getXSDDuration");
        javaName2Method.put("com.hp.hpl.jena.datatypes.xsd.XSDDateTime",
                "nl.tudelft.tbm.eeni.owl2java.model.xsd.XsdUtils.getXSDDateTime");
    }

}
