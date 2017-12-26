package com.example.carikado.controller;

import com.example.carikado.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpSession;

@Controller
public class CustomerServiceController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerServiceController.class);
    private static final String BASE_URL = "http://localhost:8080/api/";

    private RestTemplate mRestTemplate;

    public CustomerServiceController() {
        mRestTemplate = new RestTemplate();
    }

    @GetMapping("/dashboard/customer-service")
    public String dashboardCustomerService(HttpSession httpSession) {
        User user = (User) httpSession.getAttribute("user");

        if (user != null)
            return user.getRole().getName().equals("Customer Service") ? "customer-service/index" : "redirect:/dashboard";
        else
            return "redirect:/login";
    }
}
