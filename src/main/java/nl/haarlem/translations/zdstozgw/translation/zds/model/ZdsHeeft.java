package nl.haarlem.translations.zdstozgw.translation.zds.model;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import static nl.haarlem.translations.zdstozgw.translation.zds.model.namespace.Namespace.ZKN;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class ZdsHeeft  extends ZdsObject {
    @XmlElement(namespace = ZKN)
    public ZdsGerelateerde gerelateerde;

    @XmlElement(namespace = ZKN)
    public String datumStatusGezet;

    @XmlElement(namespace = ZKN)
    public String statustoelichting;

    @XmlElement(namespace = ZKN)
    public ZdsRol isGezetDoor;
}
