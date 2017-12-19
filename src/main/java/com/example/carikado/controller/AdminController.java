package com.example.carikado.controller;

import com.example.carikado.model.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
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

    @GetMapping("/dashboard/admin/role")
    public String dashboardAdminRole(HttpSession httpSession) {
        User user = (User) httpSession.getAttribute("user");

        if (user != null)
            return user.getRole().getName().equals("Admin") ? "redirect:/dashboard/admin/role/1" : "redirect:/dashboard";
        else
            return "redirect:/login";
    }

    @GetMapping("/dashboard/admin/country")
    public String dashboardAdminCountry(HttpSession httpSession) {
        User user = (User) httpSession.getAttribute("user");

        if (user != null)
            return user.getRole().getName().equals("Admin") ? "redirect:/dashboard/admin/country/1" : "redirect:/dashboard";
        else
            return "redirect:/login";
    }

   @GetMapping("/dashboard/admin/province")
    public String dashboardAdminProvince(HttpSession httpSession) {
        User user = (User) httpSession.getAttribute("user");

        if (user != null)
            return user.getRole().getName().equals("Admin") ? "redirect:/dashboard/admin/province/1" : "redirect:/dashboard";
        else
            return "redirect:/login";
    }

   @GetMapping("/dashboard/admin/city")
    public String dashboardAdminCity(HttpSession httpSession) {
        User user = (User) httpSession.getAttribute("user");

        if (user != null)
            return user.getRole().getName().equals("Admin") ? "redirect:/dashboard/admin/city/1" : "redirect:/dashboard";
        else
            return "redirect:/login";
    }

    @GetMapping("/dashboard/admin/district")
    public String dashboardAdminDistrict(HttpSession httpSession) {
        User user = (User) httpSession.getAttribute("user");

        if (user != null)
            return user.getRole().getName().equals("Admin") ? "redirect:/dashboard/admin/district/1" : "redirect:/dashboard";
        else
            return "redirect:/login";
    }

    @GetMapping("/dashboard/admin/sub-district")
    public String dashboardAdminSubDistrict(HttpSession httpSession) {
        User user = (User) httpSession.getAttribute("user");

        if (user != null)
            return user.getRole().getName().equals("Admin") ? "redirect:/dashboard/admin/sub-district/1" : "redirect:/dashboard";
        else
            return "redirect:/login";
    }

    @GetMapping("/dashboard/admin/user")
    public String dashboardAdminUser(HttpSession httpSession) {
        User user = (User) httpSession.getAttribute("user");

        if (user != null)
            return user.getRole().getName().equals("Admin") ? "redirect:/dashboard/admin/user/1" : "redirect:/dashboard";
        else
            return "redirect:/login";
    }

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
                    return "redirect:/dashboard/admin/role/1";

                UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url);

                builder.queryParam("page", page);
                builder.queryParam("pageSize", pageSize);
                builder.queryParam("sort", sort);

                ResponseEntity<String> response = mRestTemplate.exchange(builder.buildAndExpand().toUriString(),
                        HttpMethod.GET, null, String.class);

                MyResponse<MyPage<ArrayList<Role>>> myResponse;
                MyPage<ArrayList<Role>> myPage = new MyPage<>();
                ArrayList<Role> roles = new ArrayList<>();

                try {
                    myResponse = mObjectMapper.readValue(response.getBody(),
                            new TypeReference<MyResponse<MyPage<ArrayList<Role>>>>() {});

                    myPage = myResponse.getData();
                    roles = myPage.getData();
                } catch (IOException e) {
                    LOGGER.error(e.getMessage());
                }

                modelMap.addAttribute("user", user);
                modelMap.addAttribute("message", message);
                modelMap.addAttribute("page", myPage.getPage());
                modelMap.addAttribute("lastPage", myPage.getLastPage());
                modelMap.addAttribute("pageSize", myPage.getPageSize());
                modelMap.addAttribute("sort", myPage.getSort());
                modelMap.addAttribute("roles", roles);

                return "admin/role";
            } else
                return "redirect:/dashboard";
        } else
            return "redirect:/login";
    }

    @GetMapping("/dashboard/admin/country/{page}")
    public String dashboardAdminCountry(@PathVariable Integer page,
                                     @RequestParam(required = false, defaultValue = "10") Integer pageSize,
                                     @RequestParam(required = false, defaultValue = "1") Integer sort,
                                     @ModelAttribute("message") String message,
                                     HttpSession httpSession,
                                     ModelMap modelMap) {
        String url = BASE_URL + "country";
        User user = (User) httpSession.getAttribute("user");

        if (user != null) {
            boolean isAdmin = user.getRole().getName().equals("Admin");

            if (isAdmin) {
                if (page < 0)
                    return "redirect:/dashboard/admin/country/1";

                UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url);

                builder.queryParam("page", page);
                builder.queryParam("pageSize", pageSize);
                builder.queryParam("sort", sort);

                ResponseEntity<String> response = mRestTemplate.exchange(builder.buildAndExpand().toUriString(),
                        HttpMethod.GET, null, String.class);

                MyResponse<MyPage<ArrayList<Country>>> myResponse;
                MyPage<ArrayList<Country>> myPage = new MyPage<>();
                ArrayList<Country> countries = new ArrayList<>();

                try {
                    myResponse = mObjectMapper.readValue(response.getBody(),
                            new TypeReference<MyResponse<MyPage<ArrayList<Country>>>>() {});

                    myPage = myResponse.getData();
                    countries = myPage.getData();
                } catch (IOException e) {
                    LOGGER.error(e.getMessage());
                }

                modelMap.addAttribute("user", user);
                modelMap.addAttribute("message", message);
                modelMap.addAttribute("page", myPage.getPage());
                modelMap.addAttribute("lastPage", myPage.getLastPage());
                modelMap.addAttribute("pageSize", myPage.getPageSize());
                modelMap.addAttribute("sort", myPage.getSort());
                modelMap.addAttribute("countries", countries);

                return "admin/country";
            } else
                return "redirect:/dashboard";
        } else
            return "redirect:/login";
    }

    @GetMapping("/dashboard/admin/province/{page}")
    public String dashboardAdminProvince(@PathVariable Integer page,
                                         @RequestParam(required = false, defaultValue = "10") Integer pageSize,
                                         @RequestParam(required = false, defaultValue = "1") Integer sort,
                                         @ModelAttribute("message") String message,
                                         HttpSession httpSession,
                                         ModelMap modelMap) {
        String url = BASE_URL + "province";
        User user = (User) httpSession.getAttribute("user");

        if (user != null) {
            boolean isAdmin = user.getRole().getName().equals("Admin");

            if (isAdmin) {
                if (page < 0)
                    return "redirect:/dashboard/admin/province/1";

                UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url);

                builder.queryParam("page", page);
                builder.queryParam("pageSize", pageSize);
                builder.queryParam("sort", sort);

                ResponseEntity<String> response = mRestTemplate.exchange(builder.buildAndExpand().toUriString(),
                        HttpMethod.GET, null, String.class);

                MyResponse<MyPage<ArrayList<Province>>> myResponse;
                MyPage<ArrayList<Province>> myPage = new MyPage<>();
                ArrayList<Province> provinces = new ArrayList<>();

                try {
                    myResponse = mObjectMapper.readValue(response.getBody(),
                            new TypeReference<MyResponse<MyPage<ArrayList<Province>>>>() {});

                    myPage = myResponse.getData();
                    provinces = myPage.getData();
                } catch (IOException e) {
                    LOGGER.error(e.getMessage());
                }

                modelMap.addAttribute("user", user);
                modelMap.addAttribute("message", message);
                modelMap.addAttribute("page", myPage.getPage());
                modelMap.addAttribute("lastPage", myPage.getLastPage());
                modelMap.addAttribute("pageSize", myPage.getPageSize());
                modelMap.addAttribute("sort", myPage.getSort());
                modelMap.addAttribute("provinces", provinces);

                return "admin/province";
            } else
                return "redirect:/dashboard";
        } else
            return "redirect:/login";
    }

    @GetMapping("/dashboard/admin/city/{page}")
    public String dashboardAdminCity(@PathVariable Integer page,
                                         @RequestParam(required = false, defaultValue = "10") Integer pageSize,
                                         @RequestParam(required = false, defaultValue = "1") Integer sort,
                                         @ModelAttribute("message") String message,
                                         HttpSession httpSession,
                                         ModelMap modelMap) {
        String url = BASE_URL + "city";
        User user = (User) httpSession.getAttribute("user");

        if (user != null) {
            boolean isAdmin = user.getRole().getName().equals("Admin");

            if (isAdmin) {
                if (page < 0)
                    return "redirect:/dashboard/admin/city/1";

                UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url);

                builder.queryParam("page", page);
                builder.queryParam("pageSize", pageSize);
                builder.queryParam("sort", sort);

                ResponseEntity<String> response = mRestTemplate.exchange(builder.buildAndExpand().toUriString(),
                        HttpMethod.GET, null, String.class);

                MyResponse<MyPage<ArrayList<City>>> myResponse;
                MyPage<ArrayList<City>> myPage = new MyPage<>();
                ArrayList<City> cities = new ArrayList<>();

                try {
                    myResponse = mObjectMapper.readValue(response.getBody(),
                            new TypeReference<MyResponse<MyPage<ArrayList<City>>>>() {});

                    myPage = myResponse.getData();
                    cities = myPage.getData();
                } catch (IOException e) {
                    LOGGER.error(e.getMessage());
                }

                modelMap.addAttribute("user", user);
                modelMap.addAttribute("message", message);
                modelMap.addAttribute("page", myPage.getPage());
                modelMap.addAttribute("lastPage", myPage.getLastPage());
                modelMap.addAttribute("pageSize", myPage.getPageSize());
                modelMap.addAttribute("sort", myPage.getSort());
                modelMap.addAttribute("cities", cities);

                return "admin/city";
            } else
                return "redirect:/dashboard";
        } else
            return "redirect:/login";
    }

    @GetMapping("/dashboard/admin/district/{page}")
    public String dashboardAdminDistrict(@PathVariable Integer page,
                                     @RequestParam(required = false, defaultValue = "10") Integer pageSize,
                                     @RequestParam(required = false, defaultValue = "1") Integer sort,
                                     @ModelAttribute("message") String message,
                                     HttpSession httpSession,
                                     ModelMap modelMap) {
        String url = BASE_URL + "district";
        User user = (User) httpSession.getAttribute("user");

        if (user != null) {
            boolean isAdmin = user.getRole().getName().equals("Admin");

            if (isAdmin) {
                if (page < 0)
                    return "redirect:/dashboard/admin/district/1";

                UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url);

                builder.queryParam("page", page);
                builder.queryParam("pageSize", pageSize);
                builder.queryParam("sort", sort);

                ResponseEntity<String> response = mRestTemplate.exchange(builder.buildAndExpand().toUriString(),
                        HttpMethod.GET, null, String.class);

                MyResponse<MyPage<ArrayList<District>>> myResponse;
                MyPage<ArrayList<District>> myPage = new MyPage<>();
                ArrayList<District> districts = new ArrayList<>();

                try {
                    myResponse = mObjectMapper.readValue(response.getBody(),
                            new TypeReference<MyResponse<MyPage<ArrayList<District>>>>() {});

                    myPage = myResponse.getData();
                    districts = myPage.getData();
                } catch (IOException e) {
                    LOGGER.error(e.getMessage());
                }

                modelMap.addAttribute("user", user);
                modelMap.addAttribute("message", message);
                modelMap.addAttribute("page", myPage.getPage());
                modelMap.addAttribute("lastPage", myPage.getLastPage());
                modelMap.addAttribute("pageSize", myPage.getPageSize());
                modelMap.addAttribute("sort", myPage.getSort());
                modelMap.addAttribute("districts", districts);

                return "admin/district";
            } else
                return "redirect:/dashboard";
        } else
            return "redirect:/login";
    }

    @GetMapping("/dashboard/admin/sub-district/{page}")
    public String dashboardAdminSubDistrict(@PathVariable Integer page,
                                         @RequestParam(required = false, defaultValue = "10") Integer pageSize,
                                         @RequestParam(required = false, defaultValue = "1") Integer sort,
                                         @ModelAttribute("message") String message,
                                         HttpSession httpSession,
                                         ModelMap modelMap) {
        String url = BASE_URL + "sub-district";
        User user = (User) httpSession.getAttribute("user");

        if (user != null) {
            boolean isAdmin = user.getRole().getName().equals("Admin");

            if (isAdmin) {
                if (page < 0)
                    return "redirect:/dashboard/admin/sub-district/1";

                UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url);

                builder.queryParam("page", page);
                builder.queryParam("pageSize", pageSize);
                builder.queryParam("sort", sort);

                ResponseEntity<String> response = mRestTemplate.exchange(builder.buildAndExpand().toUriString(),
                        HttpMethod.GET, null, String.class);

                MyResponse<MyPage<ArrayList<SubDistrict>>> myResponse;
                MyPage<ArrayList<SubDistrict>> myPage = new MyPage<>();
                ArrayList<SubDistrict> subDistricts = new ArrayList<>();

                try {
                    myResponse = mObjectMapper.readValue(response.getBody(),
                            new TypeReference<MyResponse<MyPage<ArrayList<SubDistrict>>>>() {});

                    myPage = myResponse.getData();
                    subDistricts = myPage.getData();
                } catch (IOException e) {
                    LOGGER.error(e.getMessage());
                }

                modelMap.addAttribute("user", user);
                modelMap.addAttribute("message", message);
                modelMap.addAttribute("page", myPage.getPage());
                modelMap.addAttribute("lastPage", myPage.getLastPage());
                modelMap.addAttribute("pageSize", myPage.getPageSize());
                modelMap.addAttribute("sort", myPage.getSort());
                modelMap.addAttribute("subDistricts", subDistricts);

                return "admin/subDistrict";
            } else
                return "redirect:/dashboard";
        } else
            return "redirect:/login";
    }

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
                if (page < 0)
                    return "redirect:/dashboard/admin/user/1";

                UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url);

                builder.queryParam("page", page);
                builder.queryParam("pageSize", pageSize);
                builder.queryParam("sort", sort);

                ResponseEntity<String> response = mRestTemplate.exchange(builder.buildAndExpand().toUriString(),
                        HttpMethod.GET, null, String.class);

                MyResponse<MyPage<ArrayList<User>>> myResponse;
                MyPage<ArrayList<User>> myPage = new MyPage<>();
                ArrayList<User> users = new ArrayList<>();

                try {
                    myResponse = mObjectMapper.readValue(response.getBody(),
                            new TypeReference<MyResponse<MyPage<ArrayList<User>>>>() {});

                    myPage = myResponse.getData();
                    users = myPage.getData();
                } catch (IOException e) {
                    LOGGER.error(e.getMessage());
                }

                modelMap.addAttribute("user", user);
                modelMap.addAttribute("message", message);
                modelMap.addAttribute("page", myPage.getPage());
                modelMap.addAttribute("lastPage", myPage.getLastPage());
                modelMap.addAttribute("pageSize", myPage.getPageSize());
                modelMap.addAttribute("sort", myPage.getSort());
                modelMap.addAttribute("users", users);

                return "admin/user";
            } else
                return "redirect:/dashboard";
        } else
            return "redirect:/login";
    }

    @GetMapping("/dashboard/admin/review/{page}")
    public String dashboardAdminReview(@PathVariable Integer page,
                                     @RequestParam(required = false) Integer pageSize,
                                     @RequestParam(required = false) Integer sort,
                                     @ModelAttribute("message") String message,
                                     HttpSession httpSession,
                                     ModelMap modelMap) {
        String url = BASE_URL + "review";
        User user = (User) httpSession.getAttribute("user");

        if (user != null) {
            boolean isAdmin = user.getRole().getName().equals("Admin");

            if (isAdmin) {
                if (page < 0)
                    return "redirect:/dashboard/admin/review/1";

                UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url);

                builder.queryParam("page", page);
                builder.queryParam("pageSize", pageSize);
                builder.queryParam("sort", sort);

                ResponseEntity<String> response = mRestTemplate.exchange(builder.buildAndExpand().toUriString(),
                        HttpMethod.GET, null, String.class);

                MyResponse<MyPage<ArrayList<Review>>> myResponse;
                MyPage<ArrayList<Review>> myPage = new MyPage<>();
                ArrayList<Review> reviews = new ArrayList<>();

                try {
                    myResponse = mObjectMapper.readValue(response.getBody(),
                            new TypeReference<MyResponse<MyPage<ArrayList<Review>>>>() {});

                    myPage = myResponse.getData();
                    reviews = myPage.getData();
                } catch (IOException e) {
                    LOGGER.error(e.getMessage());
                }

                modelMap.addAttribute("user", user);
                modelMap.addAttribute("message", message);
                modelMap.addAttribute("page", myPage.getPage());
                modelMap.addAttribute("lastPage", myPage.getLastPage());
                modelMap.addAttribute("pageSize", myPage.getPageSize());
                modelMap.addAttribute("sort", myPage.getSort());
                modelMap.addAttribute("reviews", reviews);

                return "admin/review";
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

    @GetMapping("/dashboard/admin/country/add")
    public String dashboardAdminAddCountry(@ModelAttribute("message") String message,
                                        @ModelAttribute("country") Country country,
                                        HttpSession httpSession, ModelMap modelMap) {
        User user = (User) httpSession.getAttribute("user");

        if (user != null) {
            modelMap.addAttribute("user", user);
            modelMap.addAttribute("message", message);
            modelMap.addAttribute("country", country);

            return user.getRole().getName().equals("Admin") ? "admin/addCountry" : "redirect:/dashboard";
        } else
            return "redirect:/login";
    }

    @GetMapping("/dashboard/admin/province/add")
    public String dashboardAdminAddProvince(@ModelAttribute("message") String message,
                                           @ModelAttribute("province") Province province,
                                           HttpSession httpSession, ModelMap modelMap) {
        String url = BASE_URL + "country/all";
        User user = (User) httpSession.getAttribute("user");

        if (user != null) {
            ResponseEntity<String> response = mRestTemplate.exchange(url, HttpMethod.GET, null, String.class);

            MyResponse<ArrayList<Country>> myResponse;
            ArrayList<Country> countries = new ArrayList<>();

            try {
                myResponse = mObjectMapper.readValue(response.getBody(), new TypeReference<MyResponse<ArrayList<Country>>>() {});
                countries = myResponse.getData();
            } catch (IOException e) {
                LOGGER.error(e.getMessage());
                message = "Internal server error";
            }

            modelMap.addAttribute("user", user);
            modelMap.addAttribute("message", message);
            modelMap.addAttribute("countries", countries);
            modelMap.addAttribute("province", province);

            return user.getRole().getName().equals("Admin") ? "admin/addProvince" : "redirect:/dashboard";
        } else
            return "redirect:/login";
    }

    @GetMapping("/dashboard/admin/city/add")
    public String dashboardAdminAddCity(@ModelAttribute("message") String message,
                                        @ModelAttribute("city") City city,
                                        HttpSession httpSession, ModelMap modelMap) {
        String url = BASE_URL + "province/all";
        User user = (User) httpSession.getAttribute("user");

        if (user != null) {
            ResponseEntity<String> response = mRestTemplate.exchange(url, HttpMethod.GET, null, String.class);

            MyResponse<ArrayList<Province>> myResponse;
            ArrayList<Province> provinces = new ArrayList<>();

            try {
                myResponse = mObjectMapper.readValue(response.getBody(), new TypeReference<MyResponse<ArrayList<Province>>>() {});
                provinces = myResponse.getData();
            } catch (IOException e) {
                LOGGER.error(e.getMessage());
                message = "Internal server error";
            }

            modelMap.addAttribute("user", user);
            modelMap.addAttribute("message", message);
            modelMap.addAttribute("provinces", provinces);
            modelMap.addAttribute("city", city);

            return user.getRole().getName().equals("Admin") ? "admin/addCity" : "redirect:/dashboard";
        } else
            return "redirect:/login";
    }

    @GetMapping("/dashboard/admin/district/add")
    public String dashboardAdminAddDistrict(@ModelAttribute("message") String message,
                                        @ModelAttribute("district") District district,
                                        HttpSession httpSession, ModelMap modelMap) {
        String url = BASE_URL + "city/all";
        User user = (User) httpSession.getAttribute("user");

        if (user != null) {
            ResponseEntity<String> response = mRestTemplate.exchange(url, HttpMethod.GET, null, String.class);

            MyResponse<ArrayList<City>> myResponse;
            ArrayList<City> cities = new ArrayList<>();

            try {
                myResponse = mObjectMapper.readValue(response.getBody(), new TypeReference<MyResponse<ArrayList<City>>>() {});
                cities = myResponse.getData();
            } catch (IOException e) {
                LOGGER.error(e.getMessage());
                message = "Internal server error";
            }

            modelMap.addAttribute("user", user);
            modelMap.addAttribute("message", message);
            modelMap.addAttribute("cities", cities);
            modelMap.addAttribute("district", district);

            return user.getRole().getName().equals("Admin") ? "admin/addDistrict" : "redirect:/dashboard";
        } else
            return "redirect:/login";
    }

    @GetMapping("/dashboard/admin/sub-district/add")
    public String dashboardAdminAddSubDistrict(@ModelAttribute("message") String message,
                                            @ModelAttribute("subDistrict") SubDistrict subDistrict,
                                            HttpSession httpSession, ModelMap modelMap) {
        String url = BASE_URL + "district/all";
        User user = (User) httpSession.getAttribute("user");

        if (user != null) {
            ResponseEntity<String> response = mRestTemplate.exchange(url, HttpMethod.GET, null, String.class);

            MyResponse<ArrayList<District>> myResponse;
            ArrayList<District> districts = new ArrayList<>();

            try {
                myResponse = mObjectMapper.readValue(response.getBody(), new TypeReference<MyResponse<ArrayList<District>>>() {});
                districts = myResponse.getData();
            } catch (IOException e) {
                LOGGER.error(e.getMessage());
                message = "Internal server error";
            }

            modelMap.addAttribute("user", user);
            modelMap.addAttribute("message", message);
            modelMap.addAttribute("districts", districts);
            modelMap.addAttribute("subDistrict", subDistrict);

            return user.getRole().getName().equals("Admin") ? "admin/addSubDistrict" : "redirect:/dashboard";
        } else
            return "redirect:/login";
    }

    @GetMapping("/dashboard/admin/user/add")
    public String dashboardAdminAddUser(@ModelAttribute("message") String message,
                                        @ModelAttribute("user") User userModel,
                                        HttpSession httpSession, ModelMap modelMap) {
        String url;
        ResponseEntity<String> response;

        User user = (User) httpSession.getAttribute("user");

        if (user != null) {
            url = BASE_URL + "country/all";
            response = mRestTemplate.exchange(url, HttpMethod.GET, null, String.class);

            MyResponse<ArrayList<Country>> myResponseCountry;
            ArrayList<Country> countries = new ArrayList<>();

            try {
                myResponseCountry = mObjectMapper.readValue(response.getBody(), new TypeReference<MyResponse<ArrayList<Country>>>() {});
                countries = myResponseCountry.getData();
            } catch (IOException e) {
                LOGGER.error(e.getMessage());
                message = "Internal server error";
            }

            url = BASE_URL + "province/all";
            response = mRestTemplate.exchange(url, HttpMethod.GET, null, String.class);

            MyResponse<ArrayList<Province>> myResponseProvince;
            ArrayList<Province> provinces = new ArrayList<>();

            try {
                myResponseProvince = mObjectMapper.readValue(response.getBody(), new TypeReference<MyResponse<ArrayList<Province>>>() {});
                provinces = myResponseProvince.getData();
            } catch (IOException e) {
                LOGGER.error(e.getMessage());
                message = "Internal server error";
            }

            url = BASE_URL + "city/all";
            response = mRestTemplate.exchange(url, HttpMethod.GET, null, String.class);

            MyResponse<ArrayList<City>> myResponseCity;
            ArrayList<City> cities = new ArrayList<>();

            try {
                myResponseCity = mObjectMapper.readValue(response.getBody(), new TypeReference<MyResponse<ArrayList<City>>>() {});
                cities = myResponseCity.getData();
            } catch (IOException e) {
                LOGGER.error(e.getMessage());
                message = "Internal server error";
            }

            url = BASE_URL + "district/all";
            response = mRestTemplate.exchange(url, HttpMethod.GET, null, String.class);

            MyResponse<ArrayList<District>> myResponseDistrict;
            ArrayList<District> districts = new ArrayList<>();

            try {
                myResponseDistrict = mObjectMapper.readValue(response.getBody(), new TypeReference<MyResponse<ArrayList<District>>>() {});
                districts = myResponseDistrict.getData();
            } catch (IOException e) {
                LOGGER.error(e.getMessage());
                message = "Internal server error";
            }

            url = BASE_URL + "sub-district/all";
            response = mRestTemplate.exchange(url, HttpMethod.GET, null, String.class);

            MyResponse<ArrayList<SubDistrict>> myResponseSubDistrict;
            ArrayList<SubDistrict> subDistricts = new ArrayList<>();

            try {
                myResponseSubDistrict = mObjectMapper.readValue(response.getBody(), new TypeReference<MyResponse<ArrayList<SubDistrict>>>() {});
                subDistricts = myResponseSubDistrict.getData();
            } catch (IOException e) {
                LOGGER.error(e.getMessage());
                message = "Internal server error";
            }

            url = BASE_URL + "role/all";
            response = mRestTemplate.exchange(url, HttpMethod.GET, null, String.class);

            MyResponse<ArrayList<Role>> myResponseRole;
            ArrayList<Role> roles = new ArrayList<>();

            try {
                myResponseRole = mObjectMapper.readValue(response.getBody(), new TypeReference<MyResponse<ArrayList<Role>>>() {});
                roles = myResponseRole.getData();
            } catch (IOException e) {
                LOGGER.error(e.getMessage());
                message = "Internal server error";
            }

            ArrayList<UserStatus> userStatuses = new ArrayList<>(Arrays.asList(UserStatus.values()));

            modelMap.addAttribute("user", user);
            modelMap.addAttribute("message", message);
            modelMap.addAttribute("countries", countries);
            modelMap.addAttribute("provinces", provinces);
            modelMap.addAttribute("cities", cities);
            modelMap.addAttribute("districts", districts);
            modelMap.addAttribute("subDistricts", subDistricts);
            modelMap.addAttribute("roles", roles);
            modelMap.addAttribute("userStatuses", userStatuses);
            modelMap.addAttribute("userModel", userModel);

            return user.getRole().getName().equals("Admin") ? "admin/addUser" : "redirect:/dashboard";
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

    @GetMapping("/dashboard/admin/country/add/{countryId}")
    public String dashboardAdminAddCountry(@PathVariable(required = false) Integer countryId,
                                        @ModelAttribute("message") String message,
                                        HttpSession httpSession,
                                        ModelMap modelMap) {
        String url = BASE_URL + "country";
        User user = (User) httpSession.getAttribute("user");

        if (user != null) {
            boolean isAdmin = user.getRole().getName().equals("Admin");

            if (isAdmin) {
                if (countryId != null) {
                    url += "/" + countryId;

                    ResponseEntity<String> response = mRestTemplate.exchange(url, HttpMethod.GET, null,
                            String.class);
                    MyResponse<Country> myResponse;
                    Country country = null;

                    try {
                        myResponse = mObjectMapper.readValue(response.getBody(), new TypeReference<MyResponse<Country>>() {});
                        country = myResponse.getData();
                    } catch (IOException e) {
                        LOGGER.error(e.getMessage());
                    }

                    if (country != null) {
                        modelMap.addAttribute("user", user);
                        modelMap.addAttribute("message", message);
                        modelMap.addAttribute("country", country);

                        return "admin/addCountry";
                    } else
                        return "redirect:/dashboard/admin/country/1";
                } else
                    return "redirect:/dashboard/admin/country/1";
            } else
                return "redirect:/dashboard";
        } else
            return "redirect:/login";
    }

    @GetMapping("/dashboard/admin/province/add/{provinceId}")
    public String dashboardAdminAddProvince(@PathVariable(required = false) Integer provinceId,
                                        @ModelAttribute("message") String message,
                                        HttpSession httpSession,
                                        ModelMap modelMap) {
        String url = BASE_URL + "province";
        User user = (User) httpSession.getAttribute("user");

        if (user != null) {
            boolean isAdmin = user.getRole().getName().equals("Admin");

            if (isAdmin) {
                if (provinceId != null) {
                    url += "/" + provinceId;

                    ResponseEntity<String> response = mRestTemplate.exchange(url, HttpMethod.GET, null, String.class);
                    MyResponse<Province> myResponse;
                    Province province = null;

                    try {
                        myResponse = mObjectMapper.readValue(response.getBody(), new TypeReference<MyResponse<Province>>() {});
                        province = myResponse.getData();
                    } catch (IOException e) {
                        LOGGER.error(e.getMessage());
                    }

                    if (province != null) {
                        url = BASE_URL + "country/all";

                        response = mRestTemplate.exchange(url, HttpMethod.GET, null, String.class);

                        MyResponse<ArrayList<Country>> myResponseCountry;
                        ArrayList<Country> countries = new ArrayList<>();

                        try {
                            myResponseCountry = mObjectMapper.readValue(response.getBody(), new TypeReference<MyResponse<ArrayList<Country>>>() {});
                            countries = myResponseCountry.getData();
                        } catch (IOException e) {
                            LOGGER.error(e.getMessage());
                            message = "Internal server error";
                        }

                        modelMap.addAttribute("user", user);
                        modelMap.addAttribute("message", message);
                        modelMap.addAttribute("countries", countries);
                        modelMap.addAttribute("province", province);

                        return "admin/addProvince";
                    } else
                        return "redirect:/dashboard/admin/province/1";
                } else
                    return "redirect:/dashboard/admin/province/1";
            } else
                return "redirect:/dashboard";
        } else
            return "redirect:/login";
    }

    @GetMapping("/dashboard/admin/city/add/{cityId}")
    public String dashboardAdminAddCity(@PathVariable(required = false) Integer cityId,
                                        @ModelAttribute("message") String message,
                                        HttpSession httpSession,
                                        ModelMap modelMap) {
        String url = BASE_URL + "city";
        User user = (User) httpSession.getAttribute("user");

        if (user != null) {
            boolean isAdmin = user.getRole().getName().equals("Admin");

            if (isAdmin) {
                if (cityId != null) {
                    url += "/" + cityId;

                    ResponseEntity<String> response = mRestTemplate.exchange(url, HttpMethod.GET, null,
                            String.class);
                    MyResponse<City> myResponse;
                    City city = null;

                    try {
                        myResponse = mObjectMapper.readValue(response.getBody(), new TypeReference<MyResponse<City>>() {});
                        city = myResponse.getData();
                    } catch (IOException e) {
                        LOGGER.error(e.getMessage());
                    }

                    if (city != null) {
                        url = BASE_URL + "province/all";

                        response = mRestTemplate.exchange(url, HttpMethod.GET, null, String.class);

                        MyResponse<ArrayList<Province>> myResponseProvince;
                        ArrayList<Province> provinces = new ArrayList<>();

                        try {
                            myResponseProvince = mObjectMapper.readValue(response.getBody(), new TypeReference<MyResponse<ArrayList<Province>>>() {});
                            provinces = myResponseProvince.getData();
                        } catch (IOException e) {
                            LOGGER.error(e.getMessage());
                            message = "Internal server error";
                        }

                        modelMap.addAttribute("user", user);
                        modelMap.addAttribute("message", message);
                        modelMap.addAttribute("provinces", provinces);
                        modelMap.addAttribute("city", city);

                        return "admin/addCity";
                    } else
                        return "redirect:/dashboard/admin/city/1";
                } else
                    return "redirect:/dashboard/admin/city/1";
            } else
                return "redirect:/dashboard";
        } else
            return "redirect:/login";
    }

    @GetMapping("/dashboard/admin/district/add/{districtId}")
    public String dashboardAdminAddDistrict(@PathVariable(required = false) Integer districtId,
                                        @ModelAttribute("message") String message,
                                        HttpSession httpSession,
                                        ModelMap modelMap) {
        String url = BASE_URL + "district";
        User user = (User) httpSession.getAttribute("user");

        if (user != null) {
            boolean isAdmin = user.getRole().getName().equals("Admin");

            if (isAdmin) {
                if (districtId != null) {
                    url += "/" + districtId;

                    ResponseEntity<String> response = mRestTemplate.exchange(url, HttpMethod.GET, null,
                            String.class);
                    MyResponse<District> myResponse;
                    District district = null;

                    try {
                        myResponse = mObjectMapper.readValue(response.getBody(), new TypeReference<MyResponse<District>>() {});
                        district = myResponse.getData();
                    } catch (IOException e) {
                        LOGGER.error(e.getMessage());
                    }

                    if (district != null) {
                        url = BASE_URL + "city/all";

                        response = mRestTemplate.exchange(url, HttpMethod.GET, null, String.class);

                        MyResponse<ArrayList<City>> myResponseCity;
                        ArrayList<City> cities = new ArrayList<>();

                        try {
                            myResponseCity = mObjectMapper.readValue(response.getBody(), new TypeReference<MyResponse<ArrayList<City>>>() {});
                            cities = myResponseCity.getData();
                        } catch (IOException e) {
                            LOGGER.error(e.getMessage());
                            message = "Internal server error";
                        }

                        modelMap.addAttribute("user", user);
                        modelMap.addAttribute("message", message);
                        modelMap.addAttribute("cities", cities);
                        modelMap.addAttribute("district", district);

                        return "admin/addDistrict";
                    } else
                        return "redirect:/dashboard/admin/district/1";
                } else
                    return "redirect:/dashboard/admin/district/1";
            } else
                return "redirect:/dashboard";
        } else
            return "redirect:/login";
    }

    @GetMapping("/dashboard/admin/sub-district/add/{subDistrictId}")
    public String dashboardAdminAddSubDistrict(@PathVariable(required = false) String subDistrictId,
                                            @ModelAttribute("message") String message,
                                            HttpSession httpSession,
                                            ModelMap modelMap) {
        String url = BASE_URL + "sub-district";
        User user = (User) httpSession.getAttribute("user");

        if (user != null) {
            boolean isAdmin = user.getRole().getName().equals("Admin");

            if (isAdmin) {
                if (subDistrictId != null) {
                    url += "/" + subDistrictId;

                    ResponseEntity<String> response = mRestTemplate.exchange(url, HttpMethod.GET, null,
                            String.class);
                    MyResponse<SubDistrict> myResponse;
                    SubDistrict subDistrict = null;

                    try {
                        myResponse = mObjectMapper.readValue(response.getBody(), new TypeReference<MyResponse<SubDistrict>>() {});
                        subDistrict = myResponse.getData();
                    } catch (IOException e) {
                        LOGGER.error(e.getMessage());
                    }

                    if (subDistrict != null) {
                        url = BASE_URL + "district/all";

                        response = mRestTemplate.exchange(url, HttpMethod.GET, null, String.class);

                        MyResponse<ArrayList<District>> myResponseDistrict;
                        ArrayList<District> districts = new ArrayList<>();

                        try {
                            myResponseDistrict = mObjectMapper.readValue(response.getBody(), new TypeReference<MyResponse<ArrayList<District>>>() {});
                            districts = myResponseDistrict.getData();
                        } catch (IOException e) {
                            LOGGER.error(e.getMessage());
                            message = "Internal server error";
                        }

                        modelMap.addAttribute("user", user);
                        modelMap.addAttribute("message", message);
                        modelMap.addAttribute("districts", districts);
                        modelMap.addAttribute("subDistrict", subDistrict);

                        return "admin/addSubDistrict";
                    } else
                        return "redirect:/dashboard/admin/subDistrict/1";
                } else
                    return "redirect:/dashboard/admin/subDistrict/1";
            } else
                return "redirect:/dashboard";
        } else
            return "redirect:/login";
    }

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
                    User userModel = null;

                    try {
                        myResponse = mObjectMapper.readValue(response.getBody(), new TypeReference<MyResponse<User>>() {});
                        userModel = myResponse.getData();
                    } catch (IOException e) {
                        LOGGER.error(e.getMessage());
                    }

                    if (userModel != null) {
                        url = BASE_URL + "country/all";
                        response = mRestTemplate.exchange(url, HttpMethod.GET, null, String.class);

                        MyResponse<ArrayList<Country>> myResponseCountry;
                        ArrayList<Country> countries = new ArrayList<>();

                        try {
                            myResponseCountry = mObjectMapper.readValue(response.getBody(), new TypeReference<MyResponse<ArrayList<Country>>>() {});
                            countries = myResponseCountry.getData();
                        } catch (IOException e) {
                            LOGGER.error(e.getMessage());
                            message = "Internal server error";
                        }

                        url = BASE_URL + "province/all";
                        response = mRestTemplate.exchange(url, HttpMethod.GET, null, String.class);

                        MyResponse<ArrayList<Province>> myResponseProvince;
                        ArrayList<Province> provinces = new ArrayList<>();

                        try {
                            myResponseProvince = mObjectMapper.readValue(response.getBody(), new TypeReference<MyResponse<ArrayList<Province>>>() {});
                            provinces = myResponseProvince.getData();
                        } catch (IOException e) {
                            LOGGER.error(e.getMessage());
                            message = "Internal server error";
                        }

                        url = BASE_URL + "city/all";
                        response = mRestTemplate.exchange(url, HttpMethod.GET, null, String.class);

                        MyResponse<ArrayList<City>> myResponseCity;
                        ArrayList<City> cities = new ArrayList<>();

                        try {
                            myResponseCity = mObjectMapper.readValue(response.getBody(), new TypeReference<MyResponse<ArrayList<City>>>() {});
                            cities = myResponseCity.getData();
                        } catch (IOException e) {
                            LOGGER.error(e.getMessage());
                            message = "Internal server error";
                        }

                        url = BASE_URL + "district/all";
                        response = mRestTemplate.exchange(url, HttpMethod.GET, null, String.class);

                        MyResponse<ArrayList<District>> myResponseDistrict;
                        ArrayList<District> districts = new ArrayList<>();

                        try {
                            myResponseDistrict = mObjectMapper.readValue(response.getBody(), new TypeReference<MyResponse<ArrayList<District>>>() {});
                            districts = myResponseDistrict.getData();
                        } catch (IOException e) {
                            LOGGER.error(e.getMessage());
                            message = "Internal server error";
                        }

                        url = BASE_URL + "sub-district/all";
                        response = mRestTemplate.exchange(url, HttpMethod.GET, null, String.class);

                        MyResponse<ArrayList<SubDistrict>> myResponseSubDistrict;
                        ArrayList<SubDistrict> subDistricts = new ArrayList<>();

                        try {
                            myResponseSubDistrict = mObjectMapper.readValue(response.getBody(), new TypeReference<MyResponse<ArrayList<SubDistrict>>>() {});
                            subDistricts = myResponseSubDistrict.getData();
                        } catch (IOException e) {
                            LOGGER.error(e.getMessage());
                            message = "Internal server error";
                        }

                        url = BASE_URL + "role/all";
                        response = mRestTemplate.exchange(url, HttpMethod.GET, null, String.class);

                        MyResponse<ArrayList<Role>> myResponseRole;
                        ArrayList<Role> roles = new ArrayList<>();

                        try {
                            myResponseRole = mObjectMapper.readValue(response.getBody(), new TypeReference<MyResponse<ArrayList<Role>>>() {});
                            roles = myResponseRole.getData();
                        } catch (IOException e) {
                            LOGGER.error(e.getMessage());
                            message = "Internal server error";
                        }

                        ArrayList<UserStatus> userStatuses = new ArrayList<>(Arrays.asList(UserStatus.values()));

                        modelMap.addAttribute("user", user);
                        modelMap.addAttribute("message", message);
                        modelMap.addAttribute("countries", countries);
                        modelMap.addAttribute("provinces", provinces);
                        modelMap.addAttribute("cities", cities);
                        modelMap.addAttribute("districts", districts);
                        modelMap.addAttribute("subDistricts", subDistricts);
                        modelMap.addAttribute("roles", roles);
                        modelMap.addAttribute("userStatuses", userStatuses);
                        modelMap.addAttribute("userModel", userModel);

                        return "admin/addUser";
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
                boolean isEdit = roleId != null;

                Role role = new Role();
                role.setName(roleName);

                if (isEdit)
                    role.setId(roleId);

                HttpHeaders httpHeaders = new HttpHeaders();
                httpHeaders.setContentType(MediaType.APPLICATION_JSON);

                String requestJson = "";

                try {
                    requestJson = mObjectMapper.writeValueAsString(role);
                } catch (JsonProcessingException e) {
                    LOGGER.error(e.getMessage());
                }

                HttpEntity<String> request = new HttpEntity<>(requestJson, httpHeaders);

                ResponseEntity<String> response = mRestTemplate.exchange(url, HttpMethod.POST, request, String.class);
                MyResponse<Integer> myResponse = null;
                Integer responseInt = null;
                String message;

                try {
                    myResponse = mObjectMapper.readValue(response.getBody(), new TypeReference<MyResponse<Integer>>() {});
                    responseInt = myResponse.getData();
                } catch (IOException e) {
                    LOGGER.error(e.getMessage());
                }

                if (responseInt != null && responseInt == 1) {
                    redirectAttributes.addAttribute("message", myResponse.getMessage());
                } else {
                    if (responseInt == null)
                        message = "Internal server error";
                    else
                        message = myResponse.getMessage();

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

    @PostMapping("/dashboard/admin/country/add")
    public String dashboardAdminAddCountry(@RequestParam(name = "countryId", required = false) Integer countryId,
                                        @RequestParam("countryName") String countryName,
                                        HttpSession httpSession,
                                        RedirectAttributes redirectAttributes) {
        String url = BASE_URL + "country/add";
        User user = (User) httpSession.getAttribute("user");

        if (user != null) {
            boolean isAdmin = user.getRole().getName().equals("Admin");

            if (isAdmin) {
                boolean isEdit = countryId != null;

                Country country = new Country();
                country.setName(countryName);

                if (isEdit)
                    country.setId(countryId);

                HttpHeaders httpHeaders = new HttpHeaders();
                httpHeaders.setContentType(MediaType.APPLICATION_JSON);

                String requestJson = "";

                try {
                    requestJson = mObjectMapper.writeValueAsString(country);
                } catch (JsonProcessingException e) {
                    LOGGER.error(e.getMessage());
                }

                HttpEntity<String> request = new HttpEntity<>(requestJson, httpHeaders);

                ResponseEntity<String> response = mRestTemplate.exchange(url, HttpMethod.POST, request, String.class);
                MyResponse<Integer> myResponse = null;
                Integer responseInt = null;
                String message;

                try {
                    myResponse = mObjectMapper.readValue(response.getBody(), new TypeReference<MyResponse<Integer>>() {});
                    responseInt = myResponse.getData();
                } catch (IOException e) {
                    LOGGER.error(e.getMessage());
                }

                if (responseInt != null && responseInt == 1) {
                    redirectAttributes.addAttribute("message", myResponse.getMessage());
                } else {
                    if (responseInt == null)
                        message = "Internal server error";
                    else
                        message = myResponse.getMessage();

                    redirectAttributes.addAttribute("message", message);
                    redirectAttributes.addAttribute("country", country);
                }

                String returnString = "redirect:/dashboard/admin/country/add";

                if (isEdit)
                    returnString += "/" + countryId;

                return returnString;
            } else
                return "redirect:/dashboard";
        } else
            return "redirect:/login";
    }

    @PostMapping("/dashboard/admin/province/add")
    public String dashboardAdminAddProvince(@RequestParam(name = "provinceId", required = false) Integer provinceId,
                                        @RequestParam("countryId") Integer countryId,
                                        @RequestParam("provinceName") String provinceName,
                                        HttpSession httpSession,
                                        RedirectAttributes redirectAttributes) {
        String url = BASE_URL + "province/add";
        User user = (User) httpSession.getAttribute("user");

        if (user != null) {
            boolean isAdmin = user.getRole().getName().equals("Admin");

            if (isAdmin) {
                boolean isEdit = provinceId != null;

                Province province = new Province();
                Country country = new Country();
                country.setId(countryId);

                province.setName(provinceName);
                province.setCountry(country);

                if (isEdit)
                    province.setId(provinceId);

                HttpHeaders httpHeaders = new HttpHeaders();
                httpHeaders.setContentType(MediaType.APPLICATION_JSON);

                String requestJson = "";

                try {
                    requestJson = mObjectMapper.writeValueAsString(province);
                } catch (JsonProcessingException e) {
                    LOGGER.error(e.getMessage());
                }

                HttpEntity<String> request = new HttpEntity<>(requestJson, httpHeaders);

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
                    message += "province success";

                    redirectAttributes.addAttribute("message", message);
                } else {
                    if (responseInt == null)
                        message = "Internal server error";
                    else
                        message += "province failed";

                    redirectAttributes.addAttribute("message", message);
                    redirectAttributes.addAttribute("province", province);
                }

                String returnString = "redirect:/dashboard/admin/province/add";

                if (isEdit)
                    returnString += "/" + provinceId;

                return returnString;
            } else
                return "redirect:/dashboard";
        } else
            return "redirect:/login";
    }

    @PostMapping("/dashboard/admin/city/add")
    public String dashboardAdminAddCity(@RequestParam(name = "cityId", required = false) Integer cityId,
                                        @RequestParam("provinceId") Integer provinceId,
                                        @RequestParam("cityName") String cityName,
                                        HttpSession httpSession,
                                        RedirectAttributes redirectAttributes) {
        String url = BASE_URL + "city/add";
        User user = (User) httpSession.getAttribute("user");

        if (user != null) {
            boolean isAdmin = user.getRole().getName().equals("Admin");

            if (isAdmin) {
                boolean isEdit = cityId != null;

                City city = new City();
                Province province = new Province();
                province.setId(provinceId);

                city.setName(cityName);
                city.setProvince(province);

                if (isEdit)
                    city.setId(cityId);

                HttpHeaders httpHeaders = new HttpHeaders();
                httpHeaders.setContentType(MediaType.APPLICATION_JSON);

                String requestJson = "";

                try {
                    requestJson = mObjectMapper.writeValueAsString(city);
                } catch (JsonProcessingException e) {
                    LOGGER.error(e.getMessage());
                }

                HttpEntity<String> request = new HttpEntity<>(requestJson, httpHeaders);

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
                    message += "city success";

                    redirectAttributes.addAttribute("message", message);
                } else {
                    if (responseInt == null)
                        message = "Internal server error";
                    else
                        message += "city failed";

                    redirectAttributes.addAttribute("message", message);
                    redirectAttributes.addAttribute("city", city);
                }

                String returnString = "redirect:/dashboard/admin/city/add";

                if (isEdit)
                    returnString += "/" + cityId;

                return returnString;
            } else
                return "redirect:/dashboard";
        } else
            return "redirect:/login";
    }

    @PostMapping("/dashboard/admin/district/add")
    public String dashboardAdminAddDistrict(@RequestParam(name = "districtId", required = false) Integer districtId,
                                        @RequestParam("cityId") Integer cityId,
                                        @RequestParam("districtName") String districtName,
                                        HttpSession httpSession,
                                        RedirectAttributes redirectAttributes) {
        String url = BASE_URL + "district/add";
        User user = (User) httpSession.getAttribute("user");

        if (user != null) {
            boolean isAdmin = user.getRole().getName().equals("Admin");

            if (isAdmin) {
                boolean isEdit = districtId != null;

                District district = new District();
                City city = new City();
                city.setId(cityId);

                district.setName(districtName);
                district.setCity(city);

                if (isEdit)
                    district.setId(districtId);

                HttpHeaders httpHeaders = new HttpHeaders();
                httpHeaders.setContentType(MediaType.APPLICATION_JSON);

                String requestJson = "";

                try {
                    requestJson = mObjectMapper.writeValueAsString(district);
                } catch (JsonProcessingException e) {
                    LOGGER.error(e.getMessage());
                }

                HttpEntity<String> request = new HttpEntity<>(requestJson, httpHeaders);

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
                    message += "district success";

                    redirectAttributes.addAttribute("message", message);
                } else {
                    if (responseInt == null)
                        message = "Internal server error";
                    else
                        message += "district failed";

                    redirectAttributes.addAttribute("message", message);
                    redirectAttributes.addAttribute("district", district);
                }

                String returnString = "redirect:/dashboard/admin/district/add";

                if (isEdit)
                    returnString += "/" + districtId;

                return returnString;
            } else
                return "redirect:/dashboard";
        } else
            return "redirect:/login";
    }


    @PostMapping("/dashboard/admin/sub-district/add")
    public String dashboardAdminAddSubDistrict(@RequestParam(name = "subDistrictId", required = false) Long subDistrictId,
                                            @RequestParam("districtId") Integer districtId,
                                            @RequestParam("subDistrictName") String subDistrictName,
                                            HttpSession httpSession,
                                            RedirectAttributes redirectAttributes) {
        String url = BASE_URL + "sub-district/add";
        User user = (User) httpSession.getAttribute("user");

        if (user != null) {
            boolean isAdmin = user.getRole().getName().equals("Admin");

            if (isAdmin) {
                boolean isEdit = subDistrictId != null;

                SubDistrict subDistrict = new SubDistrict();
                District district = new District();
                district.setId(districtId);

                subDistrict.setName(subDistrictName);
                subDistrict.setDistrict(district);

                if (isEdit)
                    subDistrict.setId(subDistrictId);

                HttpHeaders httpHeaders = new HttpHeaders();
                httpHeaders.setContentType(MediaType.APPLICATION_JSON);

                String requestJson = "";

                try {
                    requestJson = mObjectMapper.writeValueAsString(subDistrict);
                } catch (JsonProcessingException e) {
                    LOGGER.error(e.getMessage());
                }

                HttpEntity<String> request = new HttpEntity<>(requestJson, httpHeaders);

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
                    message += "sub district success";

                    redirectAttributes.addAttribute("message", message);
                } else {
                    if (responseInt == null)
                        message = "Internal server error";
                    else
                        message += "sub district failed";

                    redirectAttributes.addAttribute("message", message);
                    redirectAttributes.addAttribute("subDistrict", subDistrict);
                }

                String returnString = "redirect:/dashboard/admin/sub-district/add";

                if (isEdit)
                    returnString += "/" + subDistrictId;

                return returnString;
            } else
                return "redirect:/dashboard";
        } else
            return "redirect:/login";
    }

    @PostMapping("/dashboard/admin/user/add")
    public String dashboardAdminAddUser(@RequestParam(name = "userId", required = false) Integer userId,
                                        @RequestParam("userFirstName") String userFirstName,
                                        @RequestParam("userMiddleName") String userMiddleName,
                                        @RequestParam("userLastName") String userLastName,
                                        @RequestParam("userAddressCountryId") Integer userAddressCountryId,
                                        @RequestParam("userAddressProvinceId") Integer userAddressProvinceId,
                                        @RequestParam("userAddressCityId") Integer userAddressCityId,
                                        @RequestParam("userAddressDistrictId") Integer userAddressDistrictId,
                                        @RequestParam("userAddressSubDistrictId") String userAddressSubDistrictId,
                                        @RequestParam("userAddressStreet") String userAddressStreet,
                                        @RequestParam("userAddressHamlet") Integer userAddressHamlet,
                                        @RequestParam("userAddressNeighbourhood") Integer userAddressNeighbourhood,
                                        @RequestParam("userEmail") String userEmail,
                                        @RequestParam("userPassword") String userPassword,
                                        @RequestParam("userPhone") String userPhone,
                                        @RequestParam("userRoleId") Integer userRoleId,
                                        @RequestParam("userStatus") String userStatus,
                                        HttpSession httpSession,
                                        RedirectAttributes redirectAttributes) {
        String url = BASE_URL + "user/add";
        User user = (User) httpSession.getAttribute("user");

        if (user != null) {
            boolean isAdmin = user.getRole().getName().equals("Admin");

            if (isAdmin) {
                boolean isEdit = userId != null;

                User userModel = new User();

                Role role = new Role();
                role.setId(userRoleId);

                UserName userName = new UserName();
                userName.setFirstName(userFirstName);
                userName.setMiddleName(userMiddleName);
                userName.setLastName(userLastName);

                Country country = new Country();
                country.setId(userAddressCountryId);
                country.setProvinces(null);

                Province province = new Province();
                province.setId(userAddressProvinceId);
                province.setCountry(country);
                province.setCities(null);

                City city = new City();
                city.setId(userAddressCityId);
                city.setProvince(province);
                city.setDistricts(null);

                District district = new District();
                district.setId(userAddressDistrictId);
                district.setCity(city);
                district.setSubDistricts(null);

                SubDistrict subDistrict = new SubDistrict();
                subDistrict.setId(Long.valueOf(userAddressSubDistrictId));
                subDistrict.setDistrict(district);

                UserAddress userAddress = new UserAddress();
                userAddress.setStreet(userAddressStreet);
                userAddress.setHamlet(userAddressHamlet);
                userAddress.setNeighbourhood(userAddressNeighbourhood);
                userAddress.setCountry(country);
                userAddress.setProvince(province);
                userAddress.setCity(city);
                userAddress.setDistrict(district);
                userAddress.setSubDistrict(subDistrict);

                userModel.setCreatedAt(new Date());
                userModel.setEmail(userEmail);
                userModel.setPhone(userPhone);
                userModel.setStatus(UserStatus.valueOf(userStatus));
                userModel.setRole(role);
                userModel.setUserAddress(userAddress);
                userModel.setUserName(userName);

                if (isEdit)
                    userModel.setId(userId);
                else
                    userModel.setPassword(userPassword);

                HttpHeaders httpHeaders = new HttpHeaders();
                httpHeaders.setContentType(MediaType.APPLICATION_JSON);

                String requestJson = "";

                try {
                    requestJson = mObjectMapper.writeValueAsString(userModel);
                } catch (JsonProcessingException e) {
                    LOGGER.error(e.getMessage());
                }

                HttpEntity<String> request = new HttpEntity<>(requestJson, httpHeaders);

                ResponseEntity<String> response = mRestTemplate.exchange(url, HttpMethod.POST, request, String.class);
                MyResponse<Integer> myResponse = null;
                Integer responseInt = null;
                String message;

                try {
                    myResponse = mObjectMapper.readValue(response.getBody(), new TypeReference<MyResponse<Integer>>() {});
                    responseInt = myResponse.getData();
                } catch (IOException e) {
                    LOGGER.error(e.getMessage());
                }

                if (responseInt != null && responseInt == 1) {
                    redirectAttributes.addAttribute("message", myResponse.getMessage());
                } else {
                    if (responseInt == null)
                        message = "Internal server error";
                    else
                        message = myResponse.getMessage();

                    redirectAttributes.addAttribute("message", message);
                    redirectAttributes.addAttribute("user", userModel);
                }

                String returnString = "redirect:/dashboard/admin/user/add";

                if (isEdit)
                    returnString += "/" + userId;

                return returnString;
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
                        myResponse = mObjectMapper.readValue(response.getBody(), new TypeReference<MyResponse<Integer>>() {});
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

    @GetMapping("/dashboard/admin/country/delete/{countryId}")
    public String dashboardAdminDeleteCountry(@PathVariable Integer countryId,
                                           HttpSession httpSession,
                                           RedirectAttributes redirectAttributes) {
        String url = BASE_URL + "country/";
        User user = (User) httpSession.getAttribute("user");

        if (user != null) {
            boolean isAdmin = user.getRole().getName().equals("Admin");

            if (isAdmin) {
                if (countryId != null) {
                    url += countryId;

                    ResponseEntity<String> response = mRestTemplate.exchange(url, HttpMethod.DELETE, null, String.class);
                    MyResponse<Integer> myResponse;
                    Integer responseInt = null;

                    try {
                        myResponse = mObjectMapper.readValue(response.getBody(), new TypeReference<MyResponse<Integer>>() {});
                        responseInt = myResponse.getData();
                    } catch (IOException e) {
                        LOGGER.error(e.getMessage());
                    }

                    String message = responseInt != null && responseInt == 1 ? "Country delete success" : "Country delete failed";

                    redirectAttributes.addAttribute("message", message);
                    return "redirect:/dashboard/admin/country/1";
                } else
                    return "redirect:/dashboard/admin";
            } else
                return "redirect:/dashboard";
        } else
            return "redirect:/login";
    }

    @GetMapping("/dashboard/admin/province/delete/{provinceId}")
    public String dashboardAdminDeleteProvince(@PathVariable Integer provinceId,
                                           HttpSession httpSession,
                                           RedirectAttributes redirectAttributes) {
        String url = BASE_URL + "province/";
        User user = (User) httpSession.getAttribute("user");

        if (user != null) {
            boolean isAdmin = user.getRole().getName().equals("Admin");

            if (isAdmin) {
                if (provinceId != null) {
                    url += provinceId;

                    ResponseEntity<String> response = mRestTemplate.exchange(url, HttpMethod.DELETE, null, String.class);
                    MyResponse<Integer> myResponse;
                    Integer responseInt = null;

                    try {
                        myResponse = mObjectMapper.readValue(response.getBody(), new TypeReference<MyResponse<Integer>>() {});
                        responseInt = myResponse.getData();
                    } catch (IOException e) {
                        LOGGER.error(e.getMessage());
                    }

                    String message = responseInt != null && responseInt == 1 ? "Province delete success" : "Province delete failed";

                    redirectAttributes.addAttribute("message", message);
                    return "redirect:/dashboard/admin/province/1";
                } else
                    return "redirect:/dashboard/admin";
            } else
                return "redirect:/dashboard";
        } else
            return "redirect:/login";
    }

    @GetMapping("/dashboard/admin/city/delete/{cityId}")
    public String dashboardAdminDeleteCity(@PathVariable Integer cityId,
                                           HttpSession httpSession,
                                           RedirectAttributes redirectAttributes) {
        String url = BASE_URL + "city/";
        User user = (User) httpSession.getAttribute("user");

        if (user != null) {
            boolean isAdmin = user.getRole().getName().equals("Admin");

            if (isAdmin) {
                if (cityId != null) {
                    url += cityId;

                    ResponseEntity<String> response = mRestTemplate.exchange(url, HttpMethod.DELETE, null, String.class);
                    MyResponse<Integer> myResponse;
                    Integer responseInt = null;

                    try {
                        myResponse = mObjectMapper.readValue(response.getBody(), new TypeReference<MyResponse<Integer>>() {});
                        responseInt = myResponse.getData();
                    } catch (IOException e) {
                        LOGGER.error(e.getMessage());
                    }

                    String message = responseInt != null && responseInt == 1 ? "City delete success" : "City delete failed";

                    redirectAttributes.addAttribute("message", message);
                    return "redirect:/dashboard/admin/city/1";
                } else
                    return "redirect:/dashboard/admin";
            } else
                return "redirect:/dashboard";
        } else
            return "redirect:/login";
    }

    @GetMapping("/dashboard/admin/district/delete/{districtId}")
    public String dashboardAdminDeleteDistrict(@PathVariable Integer districtId,
                                           HttpSession httpSession,
                                           RedirectAttributes redirectAttributes) {
        String url = BASE_URL + "district/";
        User user = (User) httpSession.getAttribute("user");

        if (user != null) {
            boolean isAdmin = user.getRole().getName().equals("Admin");

            if (isAdmin) {
                if (districtId != null) {
                    url += districtId;

                    ResponseEntity<String> response = mRestTemplate.exchange(url, HttpMethod.DELETE, null, String.class);
                    MyResponse<Integer> myResponse;
                    Integer responseInt = null;

                    try {
                        myResponse = mObjectMapper.readValue(response.getBody(), new TypeReference<MyResponse<Integer>>() {});
                        responseInt = myResponse.getData();
                    } catch (IOException e) {
                        LOGGER.error(e.getMessage());
                    }

                    String message = responseInt != null && responseInt == 1 ? "District delete success" : "District delete failed";

                    redirectAttributes.addAttribute("message", message);
                    return "redirect:/dashboard/admin/district/1";
                } else
                    return "redirect:/dashboard/admin";
            } else
                return "redirect:/dashboard";
        } else
            return "redirect:/login";
    }


    @GetMapping("/dashboard/admin/sub-district/delete/{subDistrictId}")
    public String dashboardAdminDeleteSubDistrict(@PathVariable String subDistrictId,
                                               HttpSession httpSession,
                                               RedirectAttributes redirectAttributes) {
        String url = BASE_URL + "sub-district/";
        User user = (User) httpSession.getAttribute("user");

        if (user != null) {
            boolean isAdmin = user.getRole().getName().equals("Admin");

            if (isAdmin) {
                if (subDistrictId != null) {
                    url += subDistrictId;

                    ResponseEntity<String> response = mRestTemplate.exchange(url, HttpMethod.DELETE, null, String.class);
                    MyResponse<Integer> myResponse;
                    Integer responseInt = null;

                    try {
                        myResponse = mObjectMapper.readValue(response.getBody(), new TypeReference<MyResponse<Integer>>() {});
                        responseInt = myResponse.getData();
                    } catch (IOException e) {
                        LOGGER.error(e.getMessage());
                    }

                    String message = responseInt != null && responseInt == 1 ? "Sub-District delete success" : "Sub-District delete failed";

                    redirectAttributes.addAttribute("message", message);
                    return "redirect:/dashboard/admin/sub-district/1";
                } else
                    return "redirect:/dashboard/admin";
            } else
                return "redirect:/dashboard";
        } else
            return "redirect:/login";
    }
}
