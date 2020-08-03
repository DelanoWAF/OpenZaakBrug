package nl.haarlem.translations.zdstozgw.translation.zds.model;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

import static nl.haarlem.translations.zdstozgw.translation.zds.model.namespace.Namespace.ZKN;

@Data
@XmlRootElement(namespace = ZKN, name="zakLk01")
@XmlAccessorType(XmlAccessType.FIELD)
public class ZakLk01CreeerZaak {

    @XmlElement(namespace = ZKN, name="object")
    public List<Object> objects;

    @XmlElement(namespace = ZKN, name="stuurgegevens")
    public Stuurgegevens stuurgegevens;

    @Data
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class Object extends nl.haarlem.translations.zdstozgw.translation.zds.model.Zaak {
        @XmlElement(namespace = ZKN)
        public Rol isVan;
    }

}
