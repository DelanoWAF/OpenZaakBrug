package nl.haarlem.translations.zdstozgw.config;

import com.google.gson.Gson;
import lombok.Data;
import nl.haarlem.translations.zdstozgw.config.model.Configuratie;
import nl.haarlem.translations.zdstozgw.config.model.Organisatie;
import nl.haarlem.translations.zdstozgw.config.model.Translation;
import nl.haarlem.translations.zdstozgw.config.model.ZaakType;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.lang.invoke.MethodHandles;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;



@Service
@Data
public class ConfigService {

    private static final Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    private Configuratie configuratie;

    public ConfigService() throws Exception {

        Scanner scanner = null;
        try {
            File resource = new ClassPathResource("config.json").getFile();
            scanner = new Scanner(resource);
        } catch (Exception ex){
            log.error("######################################################################################");
            log.error("#####                                                                            #####");
            log.error("##### Unable to load configuration. Make sure 'config.json' is on the classpath  #####");
            log.error("#####                                                                            #####");
            log.error("######################################################################################");
            throw ex;
        }

        try {
            String result = "";
            while (scanner.hasNextLine()){
                result += scanner.nextLine();
            }
            Gson gson = new Gson();
            configuratie = gson.fromJson(result,Configuratie.class);

        } catch (Exception ex){
            throwException();
        }
        validateConfiguration();
    }

    private  void throwException() throws Exception {
        log.error("##########################################################################################");
        log.error("#####                                                                                #####");
        log.error("##### Unable to load configuration. Make sure 'config.json' contains a valid config  #####");
        log.error("#####                                                                                #####");
        log.error("##########################################################################################");
        throw new Exception();
    }

    private void validateConfiguration() throws Exception {
        try {
            configuratie.getOrganisaties().size();
            for(Organisatie organisatie:configuratie.getOrganisaties()){
                organisatie.getGemeenteCode();
                organisatie.getRSIN();
            }
            configuratie.getZaakTypes().size();
            for(ZaakType zaakType:configuratie.getZaakTypes()){
                zaakType.getZaakType();
                zaakType.getCode();
            }
        } catch (Exception ex){
            throwException();
        }

    }

    public Translation getTranslationBySoapActionAndApplicatie(String soapAction, String applicatie){
        List<Translation> translations = this.getConfiguratie().getTranslations().stream()
                .filter(translation -> translation.getSoapaction().equalsIgnoreCase(soapAction) && translation.getRequestcontains()
                        .equalsIgnoreCase(applicatie))
                .collect(Collectors.toList());

        if(translations.size()>1) log.warn("More then 1 translation found for soapAction & applicatie");

        return translations.get(0);
    }
}
