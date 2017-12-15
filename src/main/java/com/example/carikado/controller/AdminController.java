package com.example.carikado.controller;

import com.example.carikado.model.MyResponse;
import com.example.carikado.model.Role;
import com.example.carikado.model.User;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;

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
            //return user.getCountry().getName().equals("Admin") ? "admin/index" : "redirect:/dashboard";
        else
            return "redirect:/login";
    }

    @GetMapping("/dashboard/admin/role")
    public String dashboardAdminRole(HttpSession httpSession) {
        User user = (User) httpSession.getAttribute("user");

        if (user != null)
            return user.getRole().getName().equals("Admin") ? "redirect:/dashboard/admin/role/1" : "redirect:/dashboard";
        else
            return "redirect:/login";
    }

//    @GetMapping("/dashboard/admin/country")
//    public String dashboardAdminCountry(HttpSession httpSession) {
//        User user = (User) httpSession.getAttribute("user");
//
//        if (user != null)
//            return user.getCountry().getName().equals("Admin") ? "redirect:/dashboard/admin/country/1" : "redirect:/dashboard";
//        else
//            return "redirect:/login";
//    }

    @GetMapping("/dashboard/admin/role/{page}")
    public String dashboardAdminRole(@PathVariable Integer page,
                                     @RequestParam(required = false, defaultValue = "10") Integer pageSize,
                                     @RequestParam(required = false, defaultValue = "1") Integer sort,
                                     @ModelAttribute("message") String message,
                                     HttpSession httpSession,
                                     ModelMap modelMap) {
        String url = BASE_URL + "role";
        User user = (User) httpSession.getAttribute("user");

        if (user != null) {
            boolean isAdmin = user.getRole().getName().equals("Admin");

            if (isAdmin) {
                if (page < 0)
                    page = 1;

                UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url);

                builder.queryParam("page", page);
                builder.queryParam("pageSize", pageSize);
                builder.queryParam("sort", sort);

                ResponseEntity<String> response = mRestTemplate.exchange(builder.buildAndExpand().toUriString(),
                        HttpMethod.GET, null, String.class);

                ResponseEntity<String> responseCount = mRestTemplate.exchange(BASE_URL + "role/count/all",
                        HttpMethod.GET, null, String.class);

                MyResponse<ArrayList<Role>> myResponse;
                MyResponse<Integer> myResponseCount;

                ArrayList<Role> roles = new ArrayList<>();
                Integer count = null;

                try {
                    myResponse = mObjectMapper.readValue(response.getBody(), new TypeReference<MyResponse<ArrayList<Role>>>() {});
                    myResponseCount = mObjectMapper.readValue(responseCount.getBody(), new TypeReference<MyResponse<Integer>>() {});

                    roles = myResponse.getData();
                    count = myResponseCount.getData();
                    count = (int) Math.ceil((double) count / 10);
                } catch (IOException e) {
                    LOGGER.error(e.getMessage());
                }

                modelMap.addAttribute("user", user);
                modelMap.addAttribute("message", message);
                modelMap.addAttribute("page", page);
                modelMap.addAttribute("lastPage", count);
                modelMap.addAttribute("pageSize", pageSize);
                modelMap.addAttribute("sort", sort);
                modelMap.addAttribute("roles", roles);

                return "admin/role";
            } else
                return "redirect:/dashboard";
        } else
            return "redirect:/login";
    }

//    @GetMapping("/dashboard/admin/country/{page}")
//    public String dashboardAdminCountry(@PathVariable Integer page,
//                                     @RequestParam(required = false, defaultValue = "10") Integer pageSize,
//                                     @RequestParam(required = false, defaultValue = "1") Integer sort,
//                                     @ModelAttribute("message") String message,
//                                     HttpSession httpSession,
//                                     ModelMap modelMap) {
//        String url = BASE_URL + "country";
//        User user = (User) httpSession.getAttribute("user");
//
//        if (user != null) {
//            boolean isAdmin = user.getCountry().getName().equals("Admin");
//
//            if (isAdmin) {
//                if (page < 0)
//                    page = 1;
//
//                UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url);
//
//                builder.queryParam("page", page);
//                builder.queryParam("pageSize", pageSize);
//                builder.queryParam("sort", sort);
//
//                ResponseEntity<String> response = mRestTemplate.exchange(builder.buildAndExpand().toUriString(),
//                        HttpMethod.GET, null, String.class);
//
//                ResponseEntity<String> responseCount = mRestTemplate.exchange(BASE_URL + "country/count/all",
//                        HttpMethod.GET, null, String.class);
//
//                MyResponse<ArrayList<Country>> myResponse;
//                MyResponse<Integer> myResponseCount;
//
//                ArrayList<Country> countries = new ArrayList<>();
//                Integer count = null;
//
//                try {
//                    myResponse = mObjectMapper.readValue(response.getBody(), new TypeReference<MyResponse<ArrayList<Country>>>() {});
//                    myResponseCount = mObjectMapper.readValue(responseCount.getBody(), new TypeReference<MyResponse<Integer>>() {});
//
//                    countries = myResponse.getData();
//                    count = myResponseCount.getData();
//                    count = (int) Math.ceil((double) count / 10);
//                } catch (IOException e) {
//                    LOGGER.error(e.getMessage());
//                }
//
//                modelMap.addAttribute("user", user);
//                modelMap.addAttribute("message", message);
//                modelMap.addAttribute("page", page);
//                modelMap.addAttribute("lastPage", count);
//                modelMap.addAttribute("pageSize", pageSize);
//                modelMap.addAttribute("sort", sort);
//                modelMap.addAttribute("roles", roles);
//
//                return "admin/country";
//            } else
//                return "redirect:/dashboard";
//        } else
//            return "redirect:/login";
//    }

    @GetMapping("/dashboard/admin/user/{page}")
    public String dashboardAdminUser(@PathVariable Integer page,
                                     @RequestParam(required = false) Integer pageSize,
                                     @RequestParam(required = false) Integer sort,
                                     @ModelAttribute("message") String message,
                                     HttpSession httpSession,
                                     ModelMap modelMap) {
        String url = BASE_URL + "user";
        User user = (User) httpSession.getAttribute("user");

        if (user != null) {
            boolean isAdmin = user.getRole().getName().equals("Admin");

            if (isAdmin) {
                if (page > 0) {
                    String pageString = String.valueOf(page);
                    String pageSizeString = pageSize != null ? String.valueOf(pageSize) : null;
                    String sortString = sort != null ? String.valueOf(sort) : null;

                    UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url);

                    builder.queryParam("page", pageString);
                    builder.queryParam("pageSize", pageSizeString);
                    builder.queryParam("sort", sortString);

                    ResponseEntity<String> response = mRestTemplate.exchange(builder.buildAndExpand().toUriString(),
                            HttpMethod.GET, null, String.class);

                    MyResponse<ArrayList<User>> myResponse;
                    ArrayList<User> users = new ArrayList<>();

                    try {
                        myResponse = mObjectMapper.readValue(response.getBody(), MyResponse.class);
                        users = myResponse.getData();
                    } catch (IOException e) {
                        LOGGER.error(e.getMessage());
                    }

                    modelMap.addAttribute("message", message);
                    modelMap.addAttribute("page", pageString);
                    modelMap.addAttribute("pageSize", pageSizeString);
                    modelMap.addAttribute("sort", sortString);
                    modelMap.addAttribute("users", users);

                    // TODO make read user layout

                    return "admin/role";
                } else
                    return "redirect:/dashboard/admin/user/1";
            } else
                return "redirect:/dashboard";
        } else
            return "redirect:/login";
    }

    @GetMapping("/dashboard/admin/role/add")
    public String dashboardAdminAddRole(@ModelAttribute("message") String message,
                                        @ModelAttribute("role") Role role,
                                        HttpSession httpSession, ModelMap modelMap) {
        User user = (User) httpSession.getAttribute("user");

        if (user != null) {
            modelMap.addAttribute("user", user);
            modelMap.addAttribute("message", message);
            modelMap.addAttribute("role", role);

            return user.getRole().getName().equals("Admin") ? "admin/addRole" : "redirect:/dashboard";
        } else
            return "redirect:/login";
    }

//    @GetMapping("/dashboard/admin/country/add")
//    public String dashboardAdminAddCountry(@ModelAttribute("message") String message,
//                                        @ModelAttribute("country") Country country,
//                                        HttpSession httpSession, ModelMap modelMap) {
//        User user = (User) httpSession.getAttribute("user");
//
//        if (user != null) {
//            modelMap.addAttribute("user", user);
//            modelMap.addAttribute("message", message);
//            modelMap.addAttribute("country", country);
//
//            return user.getCountry().getName().equals("Admin") ? "admin/addCountry" : "redirect:/dashboard";
//        } else
//            return "redirect:/login";
//    }

    @GetMapping("/dashboard/admin/user/add")
    public String dashboardAdminAddUser(@ModelAttribute("message") String message,
                                        @ModelAttribute("user") User lastUser,
                                        HttpSession httpSession, ModelMap modelMap) {
        User user = (User) httpSession.getAttribute("user");

        // TODO make add user layout

        if (user != null) {
            modelMap.addAttribute("user", user);
            modelMap.addAttribute("message", message);
            modelMap.addAttribute("lastUser", lastUser);

            return user.getRole().getName().equals("Admin") ? "admin/addRole" : "redirect:/dashboard";
        } else
            return "redirect:/login";
    }

    @GetMapping("/dashboard/admin/role/add/{roleId}")
    public String dashboardAdminAddRole(@PathVariable(required = false) Integer roleId,
                                        @ModelAttribute("message") String message,
                                        HttpSession httpSession,
                                        ModelMap modelMap) {
        String url = BASE_URL + "role";
        User user = (User) httpSession.getAttribute("user");

        if (user != null) {
            boolean isAdmin = user.getRole().getName().equals("Admin");

            if (isAdmin) {
                if (roleId != null) {
                    url += "/" + roleId;

                    ResponseEntity<String> response = mRestTemplate.exchange(url, HttpMethod.GET, null,
                            String.class);
                    MyResponse<Role> myResponse;
                    Role role = null;

                    try {
                        myResponse = mObjectMapper.readValue(response.getBody(), new TypeReference<MyResponse<Role>>() {});
                        role = myResponse.getData();
                    } catch (IOException e) {
                        LOGGER.error(e.getMessage());
                    }

                    if (role != null) {
                        modelMap.addAttribute("user", user);
                        modelMap.addAttribute("message", message);
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

//    @GetMapping("/dashboard/admin/country/add/{countryId}")
//    public String dashboardAdminAddCountry(@PathVariable(required = false) Integer countryId,
//                                        @ModelAttribute("message") String message,
//                                        HttpSession httpSession,
//                                        ModelMap modelMap) {
//        String url = BASE_URL + "country";
//        User user = (User) httpSession.getAttribute("user");
//
//        if (user != null) {
//            boolean isAdmin = user.getCountry().getName().equals("Admin");
//
//            if (isAdmin) {
//                if (roleId != null) {
//                    url += "/" + countryId;
//
//                    ResponseEntity<String> response = mRestTemplate.exchange(url, HttpMethod.GET, null,
//                            String.class);
//                    MyResponse<Country> myResponse;
//                    Country country = null;
//
//                    try {
//                        myResponse = mObjectMapper.readValue(response.getBody(), new TypeReference<MyResponse<Country>>() {});
//                        country = myResponse.getData();
//                    } catch (IOException e) {
//                        LOGGER.error(e.getMessage());
//                    }
//
//                    if (country != null) {
//                        modelMap.addAttribute("user", user);
//                        modelMap.addAttribute("message", message);
//                        modelMap.addAttribute("country", country);
//
//                        return "admin/addCountry";
//                    } else
//                        return "redirect:/dashboard/admin/country/1";
//                } else
//                    return "redirect:/dashboard/admin/country/1";
//            } else
//                return "redirect:/dashboard";
//        } else
//            return "redirect:/login";
//    }

    @GetMapping("/dashboard/admin/user/add/{userId}")
    public String dashboardAdminAddUser(@PathVariable(required = false) Integer userId,
                                        @ModelAttribute("message") String message,
                                        HttpSession httpSession,
                                        ModelMap modelMap) {
        String url = BASE_URL + "user";
        User user = (User) httpSession.getAttribute("user");

        if (user != null) {
            boolean isAdmin = user.getRole().getName().equals("Admin");

            if (isAdmin) {
                if (userId != null) {
                    url += "/" + userId;

                    ResponseEntity<String> response = mRestTemplate.exchange(url, HttpMethod.GET, null,
                            String.class);
                    MyResponse<User> myResponse;
                    user = null;

                    try {
                        myResponse = mObjectMapper.readValue(response.getBody(), MyResponse.class);
                        user = myResponse.getData();
                    } catch (IOException e) {
                        LOGGER.error(e.getMessage());
                    }

                    if (user != null) {
                        // TODO make add user layout

                        modelMap.addAttribute("message", message);
                        modelMap.addAttribute("user", user);
                        return "admin/addRole";
                    } else
                        return "redirect:/dashboard/admin/user/1";
                } else
                    return "redirect:/dashboard/admin/user/1";
            } else
                return "redirect:/dashboard";
        } else
            return "redirect:/login";
    }

    @PostMapping("/dashboard/admin/role/add")
    public String dashboardAdminAddRole(@RequestParam(name = "roleId", required = false) Integer roleId,
                                        @RequestParam("roleName") String roleName,
                                        HttpSession httpSession,
                                        RedirectAttributes redirectAttributes) {
        String url = BASE_URL + "role/add";
        User user = (User) httpSession.getAttribute("user");

        if (user != null) {
            boolean isAdmin = user.getRole().getName().equals("Admin");

            if (isAdmin) {
                HashMap<String, String> params = new HashMap<>();

                boolean isEdit = roleId != null;

                if (isEdit)
                    params.put("id", String.valueOf(roleId));

                params.put("name", roleName);

                HttpEntity<HashMap> request = new HttpEntity<>(params);

                ResponseEntity<String> response = mRestTemplate.exchange(url, HttpMethod.POST, request, String.class);
                MyResponse<Integer> myResponse;
                Integer responseInt = null;
                String message = isEdit ? "Edit " : "Add ";

                try {
                    myResponse = mObjectMapper.readValue(response.getBody(), new TypeReference<MyResponse<Integer>>() {});
                    responseInt = myResponse.getData();
                } catch (IOException e) {
                    LOGGER.error(e.getMessage());
                }

                if (responseInt != null && responseInt == 1) {
                    message += "role success";

                    redirectAttributes.addAttribute("message", message);
                } else {
                    if (responseInt == null)
                        message = "Internal server error";
                    else
                        message += "role failed";

                    Role role = new Role(roleName);

                    redirectAttributes.addAttribute("message", message);
                    redirectAttributes.addAttribute("role", role);
                }

                String returnString = "redirect:/dashboard/admin/role/add";

                if (isEdit)
                    returnString += "/" + roleId;

                return returnString;
            } else
                return "redirect:/dashboard";
        } else
            return "redirect:/login";
    }

//    @PostMapping("/dashboard/admin/country/add")
//    public String dashboardAdminAddCountry(@RequestParam(name = "countryId", required = false) Integer countryId,
//                                        @RequestParam("countryName") String countryName,
//                                        HttpSession httpSession,
//                                        RedirectAttributes redirectAttributes) {
//        String url = BASE_URL + "country/add";
//        User user = (User) httpSession.getAttribute("user");
//
//        if (user != null) {
//            boolean isAdmin = user.getCountry().getName().equals("Admin");
//
//            if (isAdmin) {
//                HashMap<String, String> params = new HashMap<>();
//
//                boolean isEdit = countryId != null;
//
//                if (isEdit)
//                    params.put("id", String.valueOf(countryId));
//
//                params.put("name", countryName);
//
//                HttpEntity<HashMap> request = new HttpEntity<>(params);
//
//                ResponseEntity<String> response = mRestTemplate.exchange(url, HttpMethod.POST, request, String.class);
//                MyResponse<Integer> myResponse;
//                Integer responseInt = null;
//                String message = isEdit ? "Edit " : "Add ";
//
//                try {
//                    myResponse = mObjectMapper.readValue(response.getBody(), new TypeReference<MyResponse<Integer>>() {});
//                    responseInt = myResponse.getData();
//                } catch (IOException e) {
//                    LOGGER.error(e.getMessage());
//                }
//
//                if (responseInt != null && responseInt == 1) {
//                    message += "country success";
//
//                    redirectAttributes.addAttribute("message", message);
//                } else {
//                    if (responseInt == null)
//                        message = "Internal server error";
//                    else
//                        message += "country failed";
//
//                    Country country = new Country(countryName);
//
//                    redirectAttributes.addAttribute("message", message);
//                    redirectAttributes.addAttribute("country", country);
//                }
//
//                String returnString = "redirect:/dashboard/admin/country/add";
//
//                if (isEdit)
//                    returnString += "/" + countryId;
//
//                return returnString;
//            } else
//                return "redirect:/dashboard";
//        } else
//            return "redirect:/login";
//    }

    @PostMapping("/dashboard/admin/user/add")
    public String dashboardAdminAddUser(@RequestParam(name = "userId", required = false) Integer userId,
                                        @RequestParam("userName") String userName,
                                        HttpSession httpSession,
                                        RedirectAttributes redirectAttributes) {
        String url = BASE_URL + "user/add";
        User user = (User) httpSession.getAttribute("user");

        if (user != null) {
            boolean isAdmin = user.getRole().getName().equals("Admin");

            if (isAdmin) {
                HashMap<String, String> params = new HashMap<>();

                if (userId != null)
                    params.put("id", String.valueOf(userId));

                params.put("name", userName);

                HttpEntity<HashMap> request = new HttpEntity<>(params);

                ResponseEntity<String> response = mRestTemplate.exchange(url, HttpMethod.POST, request, String.class);
                MyResponse<Integer> myResponse;
                Integer responseInt = null;
                String message;

                try {
                    myResponse = mObjectMapper.readValue(response.getBody(), MyResponse.class);
                    responseInt = myResponse.getData();
                } catch (IOException e) {
                    LOGGER.error(e.getMessage());
                }

                if (responseInt != null && responseInt == 1) {
                    message = "Add user success";
                    redirectAttributes.addAttribute("message", message);
                } else {
                    message = responseInt == null ? "Internal server error" : "Add user failed";

                    redirectAttributes.addAttribute("message", message);
                    redirectAttributes.addAttribute("user", user);
                }

                return "redirect:/dashboard/admin/user/add";
            } else
                return "redirect:/dashboard";
        } else
            return "redirect:/login";
    }

    @GetMapping("/dashboard/admin/role/delete/{roleId}")
    public String dashboardAdminDeleteRole(@PathVariable Integer roleId,
                                           HttpSession httpSession,
                                           RedirectAttributes redirectAttributes) {
        String url = BASE_URL + "role/";
        User user = (User) httpSession.getAttribute("user");

        if (user != null) {
            boolean isAdmin = user.getRole().getName().equals("Admin");

            if (isAdmin) {
                if (roleId != null) {
                    url += roleId;

                    ResponseEntity<String> response = mRestTemplate.exchange(url, HttpMethod.DELETE, null, String.class);
                    MyResponse<Integer> myResponse;
                    Integer responseInt = null;

                    try {
                        myResponse = mObjectMapper.readValue(response.getBody(), MyResponse.class);
                        responseInt = myResponse.getData();
                    } catch (IOException e) {
                        LOGGER.error(e.getMessage());
                    }

                    String message = responseInt != null && responseInt == 1 ? "Role delete success" : "Role delete failed";

                    redirectAttributes.addAttribute("message", message);
                    return "redirect:/dashboard/admin/role/1";
                } else
                    return "redirect:/dashboard/admin";
            } else
                return "redirect:/dashboard";
        } else
            return "redirect:/login";
    }

//    @GetMapping("/dashboard/admin/country/delete/{countryId}")
//    public String dashboardAdminDeleteCountry(@PathVariable Integer countryId,
//                                           HttpSession httpSession,
//                                           RedirectAttributes redirectAttributes) {
//        String url = BASE_URL + "country/";
//        User user = (User) httpSession.getAttribute("user");
//
//        if (user != null) {
//            boolean isAdmin = user.getCountry().getName().equals("Admin");
//
//            if (isAdmin) {
//                if (countryId != null) {
//                    url += countryId;
//
//                    ResponseEntity<String> response = mRestTemplate.exchange(url, HttpMethod.DELETE, null, String.class);
//                    MyResponse<Integer> myResponse;
//                    Integer responseInt = null;
//
//                    try {
//                        myResponse = mObjectMapper.readValue(response.getBody(), MyResponse.class);
//                        responseInt = myResponse.getData();
//                    } catch (IOException e) {
//                        LOGGER.error(e.getMessage());
//                    }
//
//                    String message = responseInt != null && responseInt == 1 ? "Country delete success" : "Country delete failed";
//
//                    redirectAttributes.addAttribute("message", message);
//                    return "redirect:/dashboard/admin/country/1";
//                } else
//                    return "redirect:/dashboard/admin";
//            } else
//                return "redirect:/dashboard";
//        } else
//            return "redirect:/login";
//    }

    @GetMapping("/dashboard/admin/user/delete/{userId}")
    public String dashboardAdminDeleteUser(@PathVariable Integer userId,
                                           HttpSession httpSession,
                                           RedirectAttributes redirectAttributes) {
        String url = BASE_URL + "user/";
        User user = (User) httpSession.getAttribute("user");

        if (user != null) {
            boolean isAdmin = user.getRole().getName().equals("Admin");

            if (isAdmin) {
                if (userId != null) {
                    url += userId;

                    ResponseEntity<String> response = mRestTemplate.exchange(url, HttpMethod.DELETE, null, String.class);
                    MyResponse<Integer> myResponse;
                    Integer responseInt = null;

                    try {
                        myResponse = mObjectMapper.readValue(response.getBody(), MyResponse.class);
                        responseInt = myResponse.getData();
                    } catch (IOException e) {
                        LOGGER.error(e.getMessage());
                    }

                    String message = responseInt != null && responseInt == 1 ? "User delete success" : "User delete failed";

                    redirectAttributes.addAttribute("message", message);
                    return "redirect:/dashboard/admin/user/1";
                } else
                    return "redirect:/dashboard/admin";
            } else
                return "redirect:/dashboard";
        } else
            return "redirect:/login";
    }
}
