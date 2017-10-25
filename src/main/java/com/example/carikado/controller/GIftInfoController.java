package com.example.carikado.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

@Controller
public class GIftInfoController {

    private static final Logger LOGGER = LoggerFactory.getLogger(GIftInfoController.class);
    private static final String BASE_URL = "http://localhost:8080/api/";

    private RestTemplate mRestTemplate;

    public GIftInfoController() {
        mRestTemplate = new RestTemplate();
    }

    @GetMapping("/giftinfo/{page}")
    public String giftInfo(@PathVariable String page, @RequestParam(required = false) String sort, ModelMap modelMap) {
        Integer pageInt = 0;
        Integer sortInt = null;
        String message = null;

        boolean isValid = true;

        try {
            pageInt = Integer.parseInt(page);
            sortInt = sort != null ? Integer.parseInt(sort) : null;
        } catch (NumberFormatException e) {
            isValid = false;
            message = "Error parsing parameter";

            LOGGER.error(e.getMessage());
        }


        modelMap.addAttribute("message", message);

        return "index/giftinfo";
    }
}
