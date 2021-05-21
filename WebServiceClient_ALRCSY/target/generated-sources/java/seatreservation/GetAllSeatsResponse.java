
package seatreservation;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;Java class for GetAllSeatsResponse complex type.
 * 
 * &lt;p&gt;The following schema fragment specifies the expected content contained within this class.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType name="GetAllSeatsResponse"&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="GetAllSeatsResult" type="{http://www.iit.bme.hu/soi/hw/SeatReservation}ArrayOfSeat"/&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/restriction&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GetAllSeatsResponse", propOrder = {
    "getAllSeatsResult"
})
public class GetAllSeatsResponse {

    @XmlElement(name = "GetAllSeatsResult", required = true, nillable = true)
    protected ArrayOfSeat getAllSeatsResult;

    /**
     * Gets the value of the getAllSeatsResult property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfSeat }
     *     
     */
    public ArrayOfSeat getGetAllSeatsResult() {
        return getAllSeatsResult;
    }

    /**
     * Sets the value of the getAllSeatsResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfSeat }
     *     
     */
    public void setGetAllSeatsResult(ArrayOfSeat value) {
        this.getAllSeatsResult = value;
    }

}
