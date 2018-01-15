package com.example.carikado.controller;

import com.example.carikado.model.MyResponse;
import com.example.carikado.model.Role;
import com.example.carikado.model.User;
import com.example.carikado.model.UserStatus;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

@Controller
public class IndexController {

    private static final Logger LOGGER = LoggerFactory.getLogger(IndexController.class);
    private static final String BASE_URL = "http://madamita.ml:8080/cari-kado/api/";

    private ObjectMapper mObjectMapper;
    private RestTemplate mRestTemplate;

    public IndexController() {
        mObjectMapper = new ObjectMapper();
        mRestTemplate = new RestTemplate();
    }

    @GetMapping("/")
    public String index() {
        return "index/index";
    }

    @GetMapping("/login")
    public String login(@ModelAttribute("message") String message, HttpSession httpSession, ModelMap modelMap) {
        User user = (User) httpSession.getAttribute("user");

        if (user == null) {
            modelMap.addAttribute("message", message);
            return "index/login";
        } else
            return "redirect:/dashboard";
    }

    @GetMapping("/logout")
    public String logout(HttpSession httpSession) {
        User user = (User) httpSession.getAttribute("user");

        if (user != null)
            httpSession.removeAttribute("user");

        return "redirect:/login";
    }

    @PostMapping("/login")
    public String loginPost(@RequestParam("userEmail") String userEmail,
                            @RequestParam("userPassword") String userPassword,
                            HttpSession httpSession,
                            RedirectAttributes redirectAttributes) {
        String url = BASE_URL + "user/verifyuser";

        HashMap<String, String> params = new HashMap<>();

        params.put("userEmail", userEmail);
        params.put("userPassword", userPassword);

        HttpEntity<HashMap> requestParams = new HttpEntity<>(params);

        ResponseEntity<String> response = mRestTemplate.exchange(url, HttpMethod.POST, requestParams, String.class);
        MyResponse<User> myResponse = new MyResponse<>();
        User user = null;

        try {
            myResponse = mObjectMapper.readValue(response.getBody(), new TypeReference<MyResponse<User>>() {});
            user = myResponse.getData();
        } catch (IOException e) {
            myResponse.setMessage("Internal server error");
            LOGGER.error(e.getMessage());
        }

        if (user != null && user.getStatus().equals(UserStatus.ACTIVE)) {
            httpSession.setAttribute("user", user);
            return "redirect:/dashboard";
        }

        String message = user != null ? "User is not allowed to login" : myResponse.getMessage();
        redirectAttributes.addFlashAttribute("message", message);
        return "redirect:/login";
    }

    @GetMapping("/error")
    public String error() {
        return "index/error";
    }

    @GetMapping("/error404")
    public String error404() {
        return "index/404";
    }

    @GetMapping("/error403")
    public String error403() {
        return "index/403";
    }

    @GetMapping("/dashboard")
    public String dashboard(HttpSession httpSession) {
        String url = BASE_URL + "role/all";
        User user = (User) httpSession.getAttribute("user");

        if (user != null) {
            ResponseEntity<String> response = mRestTemplate.exchange(url, HttpMethod.GET, null, String.class);
            MyResponse<ArrayList<Role>> myResponse;
            ArrayList<Role> roles = new ArrayList<>();

            try {
                myResponse = mObjectMapper.readValue(response.getBody(), new TypeReference<MyResponse<ArrayList<Role>>>() {});
                roles = myResponse.getData();
            } catch (IOException e) {
                LOGGER.error(e.getMessage());
            }

            Role userRole = user.getRole();

            boolean isFind = false;

            for (Role role : roles) {
                isFind = userRole.equals(role);

                if (isFind)
                    break;
            }

            return isFind ? "redirect:/dashboard/" + userRole.getName().replaceAll("\\s", "-")
                    .toLowerCase() : "redirect:/error404";
        } else
            return "redirect:/login";
    }
}
