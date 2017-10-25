package com.example.carikado.controller;

import com.example.carikado.model.MyResponse;
import com.example.carikado.model.Role;
import com.example.carikado.model.User;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

@Controller
public class AdminController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AdminController.class);
    private static final String BASE_URL = "http://localhost:8080/api/";

    private ObjectMapper mObjectMapper;
    private RestTemplate mRestTemplate;

    public AdminController() {
        mObjectMapper = new ObjectMapper();
        mRestTemplate = new RestTemplate();
    }

    @GetMapping("/dashboard/admin")
    public String dashboardAdmin(HttpSession httpSession) {
        User user = (User) httpSession.getAttribute("user");

        if (user != null)
            return user.getRole().getName().equals("Admin") ? "admin/index" : "redirect:/dashboard";
        else
            return "redirect:/login";
    }

    @GetMapping("/dashboard/admin/role/{page}")
    public String dashboardAdminRole(@PathVariable Integer page, @RequestParam(required = false) Integer pageSize,
                                     @RequestParam(required = false) Integer sort, HttpSession httpSession,
                                     ModelMap modelMap) {
        String url = BASE_URL + "role";
        User user = (User) httpSession.getAttribute("user");

        if (user != null) {
            boolean isAdmin = user.getRole().getName().equals("Admin");

            if (isAdmin) {
                if (page > 0) {
                    LOGGER.info(page + "");
                    LOGGER.info(pageSize + "");
                    LOGGER.info(sort + "");

                    String pageString = String.valueOf(page);
                    String pageSizeString = pageSize != null ? String.valueOf(pageSize) : null;
                    String sortString = sort != null ? String.valueOf(sort) : null;

                    HashMap<String, String> params = new HashMap<>();

                    params.put("page", pageString);
                    params.put("pageSize", pageSizeString);
                    params.put("sort", sortString);

                    HttpEntity<HashMap> request = new HttpEntity<>(params);

                    ResponseEntity<String> response = mRestTemplate.exchange(url, HttpMethod.POST, request, String.class);

                    LOGGER.info(response.getBody());

//                    ResponseEntity<JsonNode> response = mRestTemplate.exchange(url, HttpMethod.POST, request, JsonNode.class);
                    MyResponse<ArrayList<Role>> myResponse;
                    ArrayList<Role> roles = new ArrayList<>();
//
                    try {
//                        JsonNode jsonNode = response.getBody();
//                        myResponse = mObjectMapper.readValue(mObjectMapper.treeAsTokens(jsonNode),
//                                new TypeReference<MyResponse<ArrayList<Role>>>() {
//                                });
//
                        myResponse = mObjectMapper.readValue(response.getBody(), MyResponse.class);
                        roles = myResponse.getData();
                    } catch (IOException e) {
                        LOGGER.error(e.getMessage());
                    }

                    modelMap.addAttribute("page", pageString);
                    modelMap.addAttribute("pageSize", pageSizeString);
                    modelMap.addAttribute("sort", sortString);
                    modelMap.addAttribute("roles", roles);

                    return "admin/role";
                } else
                    return "redirect:/dashboard/admin/role/1";
            } else
                return "redirect:/dashboard";
        } else
            return "redirect:/login";
    }

    @GetMapping("/dashboard/admin/add/role")
    public String dashboardAdminAddRole(HttpSession httpSession, ModelMap modelMap) {
        User user = (User) httpSession.getAttribute("user");

        if (user != null)
            return user.getRole().getName().equals("Admin") ? "admin/addRole" : "redirect:/dashboard";
        else
            return "redirect:/login";
    }

    @GetMapping("/dashboard/admin/add/role/{roleId}")
    public String dashboardAdminAddRole(@PathVariable(required = false) Integer roleId, HttpSession httpSession,
                                        ModelMap modelMap) {
        String url = BASE_URL + "role";
        User user = (User) httpSession.getAttribute("user");

        if (user != null) {
            boolean isAdmin = user.getRole().getName().equals("Admin");

            if (isAdmin) {
                if (roleId != null) {
                    url += "/" + roleId;

                    ResponseEntity<JsonNode> response = mRestTemplate.exchange(url, HttpMethod.POST, null,
                            JsonNode.class);
                    MyResponse<Role> myResponse;
                    Role role = null;

                    try {
                        JsonNode jsonNode = response.getBody();
                        myResponse = mObjectMapper.readValue(mObjectMapper.treeAsTokens(jsonNode),
                                new TypeReference<MyResponse<Role>>() {
                                });

                        role = myResponse.getData();
                    } catch (IOException e) {
                        LOGGER.error(e.getMessage());
                    }

                    if (role != null) {
                        modelMap.addAttribute("role", role);
                        return "admin/addRole";
                    } else
                        return "redirect:/dashboard/admin/role/1";
                } else
                    return "redirect:/dashboard/admin/role/1";
            } else
                return "redirect:/dashboard";
        } else
            return "redirect:/login";
    }

    @PostMapping("/dashboard/admin/add/role")
    public String dashboardAdminAddRole(@RequestParam(name = "roleId", required = false) Integer roleId,
                                        @RequestParam("roleName") String roleName,
                                        HttpSession httpSession, ModelMap modelMap) {
        String url = BASE_URL + "role/add";
        User user = (User) httpSession.getAttribute("user");

        if (user != null) {
            boolean isAdmin = user.getRole().getName().equals("Admin");

            if (isAdmin) {
                HashMap<String, String> params = new HashMap<>();

                if (roleId != null)
                    params.put("id", String.valueOf(roleId));

                params.put("name", roleName);

                HttpEntity<HashMap> request = new HttpEntity<>(params);

                ResponseEntity<JsonNode> response = mRestTemplate.exchange(url, HttpMethod.POST, request, JsonNode.class);
                MyResponse<Integer> myResponse;
                Integer responseInt = null;

                try {
                    JsonNode jsonNode = response.getBody();
                    myResponse = mObjectMapper.readValue(mObjectMapper.treeAsTokens(jsonNode),
                            new TypeReference<MyResponse<Integer>>() {
                            });

                    responseInt = myResponse.getData();
                } catch (IOException e) {
                    LOGGER.error(e.getMessage());
                }
                //TODO menambahkan pengecekan response

                return "redirect:/dashboard/admin";
            } else
                return "redirect:/dashboard";
        } else
            return "redirect:/login";
    }

    @GetMapping("/dashboard/admin/delete/role/{roleId}")
    public String dashboardAdminDeleteRole(@PathVariable Integer roleId, HttpSession httpSession, ModelMap modelMap) {
        String url = BASE_URL + "role/";
        User user = (User) httpSession.getAttribute("user");

        if (user != null) {
            boolean isAdmin = user.getRole().getName().equals("Admin");

            if (isAdmin) {
                if (roleId != null) {
                    url += roleId;

                    ResponseEntity<JsonNode> response = mRestTemplate.exchange(url, HttpMethod.DELETE, null, JsonNode.class);
                    MyResponse<Integer> myResponse;
                    Integer responseInt = null;

                    try {
                        JsonNode jsonNode = response.getBody();
                        myResponse = mObjectMapper.readValue(mObjectMapper.treeAsTokens(jsonNode),
                                new TypeReference<MyResponse<Integer>>() {
                                });

                        responseInt = myResponse.getData();
                    } catch (IOException e) {
                        LOGGER.error(e.getMessage());
                    }

                    //TODO menambahkan pengecekan response
                }

                return "redirect:/dashboard/admin";
            } else
                return "redirect:/dashboard";
        } else
            return "redirect:/login";
    }
}
