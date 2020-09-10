package nl.haarlem.translations.zdstozgw.translation.zds.model;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

import static nl.haarlem.translations.zdstozgw.translation.zds.model.namespace.Namespace.STUF;
import static nl.haarlem.translations.zdstozgw.translation.zds.model.namespace.Namespace.ZKN;

import java.util.List;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class ZdsZaakDocumentRelevant extends ZdsZaakDocument {

	@XmlElement(namespace = ZKN, nillable = true)    
	public ZdsIsRelevantVoor isRelevantVoor;

}
