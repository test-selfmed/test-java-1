package fr.selfmed.test.services;

import org.springframework.stereotype.Service;

@Service
public class ExternalApiSimulatorService {
    public String callApi(String URI) {

        return XmlInputService.getXmlInput();
    }
}