
package seatreservation;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;Java class for GetSeatStatusResponse complex type.
 * 
 * &lt;p&gt;The following schema fragment specifies the expected content contained within this class.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType name="GetSeatStatusResponse"&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="GetSeatStatusResult" type="{http://www.iit.bme.hu/soi/hw/SeatReservation}SeatStatus"/&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/restriction&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GetSeatStatusResponse", propOrder = {
    "getSeatStatusResult"
})
public class GetSeatStatusResponse {

    @XmlElement(name = "GetSeatStatusResult", required = true)
    @XmlSchemaType(name = "string")
    protected SeatStatus getSeatStatusResult;

    /**
     * Gets the value of the getSeatStatusResult property.
     * 
     * @return
     *     possible object is
     *     {@link SeatStatus }
     *     
     */
    public SeatStatus getGetSeatStatusResult() {
        return getSeatStatusResult;
    }

    /**
     * Sets the value of the getSeatStatusResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link SeatStatus }
     *     
     */
    public void setGetSeatStatusResult(SeatStatus value) {
        this.getSeatStatusResult = value;
    }

}
