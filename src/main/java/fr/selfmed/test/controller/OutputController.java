package fr.selfmed.test.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import fr.selfmed.test.entity.SelfMedBean;
import fr.selfmed.test.services.ExternalApiSimulatorService;
import org.json.XML;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class OutputController {
    private final ExternalApiSimulatorService externalApiSimulatorService;

    @Autowired
    public OutputController(ExternalApiSimulatorService externalApiSimulatorService) {
        this.externalApiSimulatorService = externalApiSimulatorService;
    }

    @GetMapping("/api/output/json")
    private String getAsJsonFormat() {
        String xmlInput = externalApiSimulatorService.callApi("api/input/xml");

       return XML.toJSONObject(xmlInput).get("root").toString();
    }

    @GetMapping("/api/output/selfmed")
    public String getAsSelfmedFormat() throws IOException {
        String xmlInput = externalApiSimulatorService.callApi("api/input/xml");

        ObjectMapper objectMapper = new XmlMapper();
        ObjectReader objectReader = objectMapper.reader().forType(new TypeReference<List<SelfMedBean>>() {
        });

        List<SelfMedBean> selfMedBeans = objectReader.readValue(xmlInput);
        return selfMedBeans.stream().map(SelfMedBean::toString).collect(Collectors.joining("<br>"));
    }
}