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

@Controller
public class AuthorController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthorController.class);
    private static final String BASE_URL = "http://localhost:8080/api/";

    private RestTemplate mRestTemplate;
    private ObjectMapper mObjectMapper;

    public AuthorController() {
        mObjectMapper = new ObjectMapper();
        mRestTemplate = new RestTemplate();
    }

    @GetMapping("/dashboard/author")
    public String dashboardAuthor(HttpSession httpSession) {

        User user = (User) httpSession.getAttribute("user");

        if (user != null)
            return user.getRole().getName().equals("Author") ? "author/index" : "redirect:/dashboard";
        else
            return "redirect:/login";
    }

    @GetMapping("/dashboard/author/gift-info-category")
    public String dashboardAuthorGiftInfoCategory(HttpSession httpSession) {
        User user = (User) httpSession.getAttribute("user");

        if (user != null)
            return user.getRole().getName().equals("Author") ? "redirect:/dashboard/author/gift-info-category/1" : "redirect:/dashboard";
        else
            return "redirect:/login";
    }

    @GetMapping("/dashboard/author/gift-info")
    public String dashboardAuthorGiftInfo(HttpSession httpSession) {
        User user = (User) httpSession.getAttribute("user");

        if (user != null)
            return user.getRole().getName().equals("Author") ? "redirect:/dashboard/author/gift-info/1" : "redirect:/dashboard";
        else
            return "redirect:/login";
    }

    @GetMapping("/dashboard/author/gift-info-category/{page}")
    public String dashboardAuthorGiftInfoCategory(@PathVariable Integer page,
                                                  @RequestParam(required = false, defaultValue = "10") Integer pageSize,
                                                  @RequestParam(required = false, defaultValue = "1") Integer sort,
                                                  @ModelAttribute("message") String message,
                                                  HttpSession httpSession,
                                                  ModelMap modelMap) {
        String url = BASE_URL + "gift-info-category";
        User user = (User) httpSession.getAttribute("user");

        if (user != null) {
            boolean isAuthor = user.getRole().getName().equals("Author");

            if (isAuthor) {
                if (page < 0)
                    return "redirect:/dashboard/author/gift-info-category/1";

                UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url);

                builder.queryParam("page", page);
                builder.queryParam("pageSize", pageSize);
                builder.queryParam("sort", sort);

                ResponseEntity<String> response = mRestTemplate.exchange(builder.buildAndExpand().toUriString(),
                        HttpMethod.GET, null, String.class);

                MyResponse<MyPage<ArrayList<GiftInfoCategory>>> myResponse;
                MyPage<ArrayList<GiftInfoCategory>> myPage = new MyPage<>();
                ArrayList<GiftInfoCategory> giftInfoCategories = new ArrayList<>();

                try {
                    myResponse = mObjectMapper.readValue(response.getBody(),
                            new TypeReference<MyResponse<MyPage<ArrayList<GiftInfoCategory>>>>() {});

                    myPage = myResponse.getData();
                    giftInfoCategories = myPage.getData();
                } catch (IOException e) {
                    LOGGER.error(e.getMessage());
                }

                modelMap.addAttribute("user", user);
                modelMap.addAttribute("message", message);
                modelMap.addAttribute("page", myPage.getPage());
                modelMap.addAttribute("lastPage", myPage.getLastPage());
                modelMap.addAttribute("pageSize", myPage.getPageSize());
                modelMap.addAttribute("sort", myPage.getSort());
                modelMap.addAttribute("giftInfoCategories", giftInfoCategories);

                return "author/gift-info-category";
            } else
                return "redirect:/dashboard";
        } else
            return "redirect:/login";
    }

    @GetMapping("/dashboard/author/gift-info/{page}")
    public String dashboardAuthorGiftInfo(@PathVariable Integer page,
                                                  @RequestParam(required = false, defaultValue = "10") Integer pageSize,
                                                  @RequestParam(required = false, defaultValue = "1") Integer sort,
                                                  @ModelAttribute("message") String message,
                                                  HttpSession httpSession,
                                                  ModelMap modelMap) {
        String url = BASE_URL + "gift-info";
        User user = (User) httpSession.getAttribute("user");

        if (user != null) {
            boolean isAuthor = user.getRole().getName().equals("Author");

            if (isAuthor) {
                if (page < 0)
                    return "redirect:/dashboard/author/gift-info/1";

                UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url);

                builder.queryParam("page", page);
                builder.queryParam("pageSize", pageSize);
                builder.queryParam("sort", sort);

                ResponseEntity<String> response = mRestTemplate.exchange(builder.buildAndExpand().toUriString(),
                        HttpMethod.GET, null, String.class);

                MyResponse<MyPage<ArrayList<GiftInfo>>> myResponse;
                MyPage<ArrayList<GiftInfo>> myPage = new MyPage<>();
                ArrayList<GiftInfo> giftInfos = new ArrayList<>();

                try {
                    myResponse = mObjectMapper.readValue(response.getBody(),
                            new TypeReference<MyResponse<MyPage<ArrayList<GiftInfo>>>>() {});

                    myPage = myResponse.getData();
                    giftInfos = myPage.getData();
                } catch (IOException e) {
                    LOGGER.error(e.getMessage());
                }

                modelMap.addAttribute("user", user);
                modelMap.addAttribute("message", message);
                modelMap.addAttribute("page", myPage.getPage());
                modelMap.addAttribute("lastPage", myPage.getLastPage());
                modelMap.addAttribute("pageSize", myPage.getPageSize());
                modelMap.addAttribute("sort", myPage.getSort());
                modelMap.addAttribute("giftInfos", giftInfos);

                return "author/gift-info";
            } else
                return "redirect:/dashboard";
        } else
            return "redirect:/login";
    }


    @GetMapping("/dashboard/author/gift-info-category/add")
    public String dashboardAuthorAddGiftInfoCategory(@ModelAttribute("message") String message,
                                                     @ModelAttribute("giftInfoCategory") GiftInfoCategory giftInfoCategory,
                                                     HttpSession httpSession, ModelMap modelMap) {
        User user = (User) httpSession.getAttribute("user");

        if (user != null) {
            modelMap.addAttribute("user", user);
            modelMap.addAttribute("message", message);
            modelMap.addAttribute("giftInfoCategory", giftInfoCategory);

            return user.getRole().getName().equals("Author") ? "author/addGiftInfoCategory" : "redirect:/dashboard";
        } else
            return "redirect:/login";
    }

    @GetMapping("/dashboard/author/gift-info/add")
    public String dashboardAuthorAddGiftInfo(@ModelAttribute("message") String message,
                                                     @ModelAttribute("giftInfo") GiftInfoCategory giftInfo,
                                                     HttpSession httpSession, ModelMap modelMap) {
        User user = (User) httpSession.getAttribute("user");

        if (user != null) {
            modelMap.addAttribute("user", user);
            modelMap.addAttribute("message", message);
            modelMap.addAttribute("giftInfo", giftInfo);

            return user.getRole().getName().equals("Author") ? "author/addGiftInfo" : "redirect:/dashboard";
        } else
            return "redirect:/login";
    }

    @GetMapping("/dashboard/author/gift-info-category/add/{giftInfoCategoryId}")
    public String dashboardAuthorAddGiftInfoCategory(@PathVariable(required = false) Integer giftInfoCategoryId,
                                                    @ModelAttribute("message") String message,
                                                    HttpSession httpSession,
                                                    ModelMap modelMap) {
        String url = BASE_URL + "gift-info-category";
        User user = (User) httpSession.getAttribute("user");

        if (user != null) {
            boolean isAuthor = user.getRole().getName().equals("Author");

            if (isAuthor) {
                if (giftInfoCategoryId != null) {
                    url += "/" + giftInfoCategoryId;

                    ResponseEntity<String> response = mRestTemplate.exchange(url, HttpMethod.GET, null,
                            String.class);
                    MyResponse<GiftInfoCategory> myResponse;
                    GiftInfoCategory giftInfoCategory = null;

                    try {
                        myResponse = mObjectMapper.readValue(response.getBody(), new TypeReference<MyResponse<GiftInfoCategory>>() {});
                        giftInfoCategory = myResponse.getData();
                    } catch (IOException e) {
                        LOGGER.error(e.getMessage());
                    }

                    if (giftInfoCategory != null) {
                        modelMap.addAttribute("user", user);
                        modelMap.addAttribute("message", message);
                        modelMap.addAttribute("giftInfoCategory", giftInfoCategory);

                        return "author/addGiftInfoCategory";
                    } else
                        return "redirect:/dashboard/author/gift-info-category/1";
                } else
                    return "redirect:/dashboard/author/gift-info-category/1";
            } else
                return "redirect:/dashboard";
        } else
            return "redirect:/login";
    }

    @GetMapping("/dashboard/author/gift-info/add/{giftInfoId}")
    public String dashboardAuthorAddGiftInfo(@PathVariable(required = false) Integer giftInfoId,
                                                     @ModelAttribute("message") String message,
                                                     HttpSession httpSession,
                                                     ModelMap modelMap) {
        String url = BASE_URL + "gift-info";
        User user = (User) httpSession.getAttribute("user");

        if (user != null) {
            boolean isAuthor = user.getRole().getName().equals("Author");

            if (isAuthor) {
                if (giftInfoId != null) {
                    url += "/" + giftInfoId;

                    ResponseEntity<String> response = mRestTemplate.exchange(url, HttpMethod.GET, null,
                            String.class);
                    MyResponse<GiftInfo> myResponse;
                    GiftInfo giftInfo = null;

                    try {
                        myResponse = mObjectMapper.readValue(response.getBody(), new TypeReference<MyResponse<GiftInfo>>() {});
                        giftInfo = myResponse.getData();
                    } catch (IOException e) {
                        LOGGER.error(e.getMessage());
                    }

                    if (giftInfo != null) {
                        modelMap.addAttribute("user", user);
                        modelMap.addAttribute("message", message);
                        modelMap.addAttribute("giftInfo", giftInfo);

                        return "author/addGiftInfo";
                    } else
                        return "redirect:/dashboard/author/gift-info/1";
                } else
                    return "redirect:/dashboard/author/gift-info/1";
            } else
                return "redirect:/dashboard";
        } else
            return "redirect:/login";
    }


    @PostMapping("/dashboard/author/gift-info-category/add")
    public String dashboardAuthorAddGiftInfoCategory(@RequestParam(name = "giftInfoCategoryId", required = false) Integer giftInfoCategoryId,
                                        @RequestParam("giftInfoCategoryName") String giftInfoCategoryName,
                                        HttpSession httpSession,
                                        RedirectAttributes redirectAttributes) {
        String url = BASE_URL + "gift-info-category/add";
        User user = (User) httpSession.getAttribute("user");

        if (user != null) {
            boolean isAuthor = user.getRole().getName().equals("Author");

            if (isAuthor) {
                boolean isEdit = giftInfoCategoryId != null;

                GiftInfoCategory giftInfoCategory = new GiftInfoCategory();
                giftInfoCategory.setName(giftInfoCategoryName);

                if (isEdit)
                    giftInfoCategory.setId(giftInfoCategoryId);

                HttpHeaders httpHeaders = new HttpHeaders();
                httpHeaders.setContentType(MediaType.APPLICATION_JSON);

                String requestJson = "";

                try {
                    requestJson = mObjectMapper.writeValueAsString(giftInfoCategory);
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

                    giftInfoCategory = new GiftInfoCategory(giftInfoCategoryName);

                    redirectAttributes.addAttribute("message", message);
                    redirectAttributes.addAttribute("giftInfoCategory", giftInfoCategory);
                }

                String returnString = "redirect:/dashboard/author/gift-info-category/add";

                if (isEdit)
                    returnString += "/" + giftInfoCategoryId;

                return returnString;
            } else
                return "redirect:/dashboard";
        } else
            return "redirect:/login";
    }

    @PostMapping("/dashboard/author/gift-info/add")
    public String dashboardAuthorAddGiftInfo(@RequestParam(name = "giftInfoId", required = false) Integer giftInfoId,
                                                     @RequestParam("giftInfoName") String giftInfoTitle,
                                                     HttpSession httpSession,
                                                     RedirectAttributes redirectAttributes) {
        String url = BASE_URL + "gift-info/add";
        User user = (User) httpSession.getAttribute("user");

        if (user != null) {
            boolean isAuthor = user.getRole().getName().equals("Author");

            if (isAuthor) {
                boolean isEdit = giftInfoId != null;

                GiftInfo giftInfo = new GiftInfo();
                giftInfo.setTitle(giftInfoTitle);

                if (isEdit)
                    giftInfo.setId(giftInfoId);

                HttpHeaders httpHeaders = new HttpHeaders();
                httpHeaders.setContentType(MediaType.APPLICATION_JSON);

                String requestJson = "";

                try {
                    requestJson = mObjectMapper.writeValueAsString(giftInfo);
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

                    giftInfo = new GiftInfo(giftInfoTitle);

                    redirectAttributes.addAttribute("message", message);
                    redirectAttributes.addAttribute("giftInfo", giftInfo);
                }

                String returnString = "redirect:/dashboard/author/gift-info/add";

                if (isEdit)
                    returnString += "/" + giftInfoId;

                return returnString;
            } else
                return "redirect:/dashboard";
        } else
            return "redirect:/login";
    }

    @GetMapping("/dashboard/author/gift-info-category/delete/{giftInfoCategoryId}")
    public String dashboardAuthorDeleteGiftInfoCategory(@PathVariable Integer giftInfoCategoryId,
                                           HttpSession httpSession,
                                           RedirectAttributes redirectAttributes) {
        String url = BASE_URL + "gift-info-category/";
        User user = (User) httpSession.getAttribute("user");

        if (user != null) {
            boolean isAuthor = user.getRole().getName().equals("Author");

            if (isAuthor) {
                if (giftInfoCategoryId != null) {
                    url += giftInfoCategoryId;

                    ResponseEntity<String> response = mRestTemplate.exchange(url, HttpMethod.DELETE, null, String.class);
                    MyResponse<Integer> myResponse;
                    Integer responseInt = null;

                    try {
                        myResponse = mObjectMapper.readValue(response.getBody(), new TypeReference<MyResponse<Integer>>() {});
                        responseInt = myResponse.getData();
                    } catch (IOException e) {
                        LOGGER.error(e.getMessage());
                    }

                    String message = responseInt != null && responseInt == 1 ? "GiftInfoCategory delete success" : "GiftInfoCategoryId delete failed";

                    redirectAttributes.addAttribute("message", message);
                    return "redirect:/dashboard/author/gift-info-category/1";
                } else
                    return "redirect:/dashboard/author";
            } else
                return "redirect:/dashboard";
        } else
            return "redirect:/login";
    }

    @GetMapping("/dashboard/author/gift-info/delete/{giftInfoId}")
    public String dashboardAuthorDeleteGiftInfo(@PathVariable Integer giftInfoId,
                                                        HttpSession httpSession,
                                                        RedirectAttributes redirectAttributes) {
        String url = BASE_URL + "gift-info/";
        User user = (User) httpSession.getAttribute("user");

        if (user != null) {
            boolean isAuthor = user.getRole().getName().equals("Author");

            if (isAuthor) {
                if (giftInfoId != null) {
                    url += giftInfoId;

                    ResponseEntity<String> response = mRestTemplate.exchange(url, HttpMethod.DELETE, null, String.class);
                    MyResponse<Integer> myResponse;
                    Integer responseInt = null;

                    try {
                        myResponse = mObjectMapper.readValue(response.getBody(), new TypeReference<MyResponse<Integer>>() {});
                        responseInt = myResponse.getData();
                    } catch (IOException e) {
                        LOGGER.error(e.getMessage());
                    }

                    String message = responseInt != null && responseInt == 1 ? "GiftInfo delete success" : "GiftInfoId delete failed";

                    redirectAttributes.addAttribute("message", message);
                    return "redirect:/dashboard/author/gift-info/1";
                } else
                    return "redirect:/dashboard/author";
            } else
                return "redirect:/dashboard";
        } else
            return "redirect:/login";
    }
}
