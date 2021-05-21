
package seatreservation;

import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.3.3
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "CinemaService", targetNamespace = "http://www.iit.bme.hu/soi/hw/SeatReservation", wsdlLocation = "/wsdl/SeatReservation.wsdl")
public class CinemaService
    extends Service
{

    private final static URL CINEMASERVICE_WSDL_LOCATION;
    private final static WebServiceException CINEMASERVICE_EXCEPTION;
    private final static QName CINEMASERVICE_QNAME = new QName("http://www.iit.bme.hu/soi/hw/SeatReservation", "CinemaService");

    static {
        CINEMASERVICE_WSDL_LOCATION = seatreservation.CinemaService.class.getResource("/wsdl/SeatReservation.wsdl");
        WebServiceException e = null;
        if (CINEMASERVICE_WSDL_LOCATION == null) {
            e = new WebServiceException("Cannot find '/wsdl/SeatReservation.wsdl' wsdl. Place the resource correctly in the classpath.");
        }
        CINEMASERVICE_EXCEPTION = e;
    }

    public CinemaService() {
        super(__getWsdlLocation(), CINEMASERVICE_QNAME);
    }

    public CinemaService(WebServiceFeature... features) {
        super(__getWsdlLocation(), CINEMASERVICE_QNAME, features);
    }

    public CinemaService(URL wsdlLocation) {
        super(wsdlLocation, CINEMASERVICE_QNAME);
    }

    public CinemaService(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, CINEMASERVICE_QNAME, features);
    }

    public CinemaService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public CinemaService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns ICinema
     */
    @WebEndpoint(name = "ICinema_HttpSoap11_Port")
    public ICinema getICinemaHttpSoap11Port() {
        return super.getPort(new QName("http://www.iit.bme.hu/soi/hw/SeatReservation", "ICinema_HttpSoap11_Port"), ICinema.class);
    }

    /**
     * 
     * @param features
     *     A list of {&#064;link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the &lt;code&gt;features&lt;/code&gt; parameter will have their default values.
     * @return
     *     returns ICinema
     */
    @WebEndpoint(name = "ICinema_HttpSoap11_Port")
    public ICinema getICinemaHttpSoap11Port(WebServiceFeature... features) {
        return super.getPort(new QName("http://www.iit.bme.hu/soi/hw/SeatReservation", "ICinema_HttpSoap11_Port"), ICinema.class, features);
    }

    private static URL __getWsdlLocation() {
        if (CINEMASERVICE_EXCEPTION!= null) {
            throw CINEMASERVICE_EXCEPTION;
        }
        return CINEMASERVICE_WSDL_LOCATION;
    }

}
