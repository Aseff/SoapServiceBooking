
package seatreservation;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;Java class for SeatStatus.
 * 
 * &lt;p&gt;The following schema fragment specifies the expected content contained within this class.
 * &lt;pre&gt;
 * &amp;lt;simpleType name="SeatStatus"&amp;gt;
 *   &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&amp;gt;
 *     &amp;lt;enumeration value="Free"/&amp;gt;
 *     &amp;lt;enumeration value="Locked"/&amp;gt;
 *     &amp;lt;enumeration value="Reserved"/&amp;gt;
 *     &amp;lt;enumeration value="Sold"/&amp;gt;
 *   &amp;lt;/restriction&amp;gt;
 * &amp;lt;/simpleType&amp;gt;
 * &lt;/pre&gt;
 * 
 */
@XmlType(name = "SeatStatus")
@XmlEnum
public enum SeatStatus {

    @XmlEnumValue("Free")
    FREE("Free"),
    @XmlEnumValue("Locked")
    LOCKED("Locked"),
    @XmlEnumValue("Reserved")
    RESERVED("Reserved"),
    @XmlEnumValue("Sold")
    SOLD("Sold");
    private final String value;

    SeatStatus(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static SeatStatus fromValue(String v) {
        for (SeatStatus c: SeatStatus.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
