//package com.example.carikado.controller;
//
//import com.example.carikado.model.*;
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.core.type.TypeReference;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.http.*;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.ModelMap;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.client.RestTemplate;
//import org.springframework.web.servlet.mvc.support.RedirectAttributes;
//import org.springframework.web.util.UriComponentsBuilder;
//
//import javax.servlet.http.HttpSession;
//import java.io.IOException;
//import java.util.ArrayList;
//
//@Controller
//public class UserController {
//
//    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
//    private static final String BASE_URL = "http://localhost:8080/api/";
//
//    private RestTemplate mRestTemplate;
//    private ObjectMapper mObjectMapper;
//
//
//    public UserController() {
//        mObjectMapper = new ObjectMapper();
//        mRestTemplate = new RestTemplate();
//    }
//
//    @GetMapping("/dashboard/user/cariKado")
//    public String dashboardUserCariKado(HttpSession httpSession) {
//        User user = (User) httpSession.getAttribute("user");
//            return user.getRole().getName().equals("User") ? "redirect:/dashboard/user/cariKado/1" : "redirect:/dashboard";
//    }
//
//    @GetMapping("/dashboard/user/infoKado")
//    public String dashboardUserInfoKado(HttpSession httpSession) {
//        User user = (User) httpSession.getAttribute("user");
//        return user.getRole().getName().equals("User") ? "redirect:/dashboard/user/infoKado/1" : "redirect:/dashboard";
//    }
//
//    @GetMapping("/dashboard/user/review")
//    public String dashboardUserReview(HttpSession httpSession) {
//        User user = (User) httpSession.getAttribute("user");
//        return user.getRole().getName().equals("User") ? "redirect:/dashboard/user/review/1" : "redirect:/dashboard";
//    }
//
//    @GetMapping("/dashboard/user/bantuan")
//    public String dashboardUserBantuan(HttpSession httpSession) {
//        User user = (User) httpSession.getAttribute("user");
//        return user.getRole().getName().equals("User") ? "redirect:/dashboard/user/bantuan/1" : "redirect:/dashboard";
//    }
//
//
//    @GetMapping("/dashboard/user/cariKado/{page}")
//    public String dashboardUserCariKado  (@PathVariable Integer page,
//                                          @RequestParam(required = false, defaultValue = "10") Integer pageSize,
//                                          @RequestParam(required = false, defaultValue = "1") Integer sort,
//                                          @ModelAttribute("message") String message,
//                                          HttpSession httpSession,
//                                          ModelMap modelMap) {
//        String url = BASE_URL + "cariKado";
//        User user = (User) httpSession.getAttribute("user");
//
//        if (user != null) {
//            boolean isUser = user.getRole().getName().equals("User");
//
//            if (isUser) {
//                if (page < 0)
//                    return "redirect:/dashboard/user/cariKado/1";
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
//                MyResponse<MyPage<ArrayList<CariKado>>> myResponse;
//                MyPage<ArrayList<CariKado>> myPage = new MyPage<>();
//                ArrayList<CariKado> cariKados = new ArrayList<>();
//
//                try {
//                    myResponse = mObjectMapper.readValue(response.getBody(),
//                            new TypeReference<MyResponse<MyPage<ArrayList<InfoKado>>>>() {});
//
//                    myPage = myResponse.getData();
//                    cariKados = myPage.getData();
//                } catch (IOException e) {
//                    LOGGER.error(e.getMessage());
//                }
//
//                modelMap.addAttribute("user", user);
//                modelMap.addAttribute("message", message);
//                modelMap.addAttribute("page", myPage.getPage());
//                modelMap.addAttribute("lastPage", myPage.getLastPage());
//                modelMap.addAttribute("pageSize", myPage.getPageSize());
//                modelMap.addAttribute("sort", myPage.getSort());
//                modelMap.addAttribute("cariKados", cariKados);
//
//                return "user/cariKado";
//            } else
//                return "redirect:/dashboard";
//        } else
//            return "redirect:/login";
//    }
//
//
//    @GetMapping("/dashboard/user/infoKado/{page}")
//    public String dashboardUserInfoKado  (@PathVariable Integer page,
//                                          @RequestParam(required = false, defaultValue = "10") Integer pageSize,
//                                          @RequestParam(required = false, defaultValue = "1") Integer sort,
//                                          @ModelAttribute("message") String message,
//                                          HttpSession httpSession,
//                                          ModelMap modelMap) {
//        String url = BASE_URL + "infoKado";
//        User user = (User) httpSession.getAttribute("user");
//
//        if (user != null) {
//            boolean isUser = user.getRole().getName().equals("User");
//
//            if (isUser) {
//                if (page < 0)
//                    return "redirect:/dashboard/user/infoKado/1";
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
//                MyResponse<MyPage<ArrayList<InfoKado>>> myResponse;
//                MyPage<ArrayList<InfoKado>> myPage = new MyPage<>();
//                ArrayList<InfoKado> infoKados = new ArrayList<>();
//
//                try {
//                    myResponse = mObjectMapper.readValue(response.getBody(),
//                            new TypeReference<MyResponse<MyPage<ArrayList<InfoKado>>>>() {});
//
//                    myPage = myResponse.getData();
//                    infoKados = myPage.getData();
//                } catch (IOException e) {
//                    LOGGER.error(e.getMessage());
//                }
//
//                modelMap.addAttribute("user", user);
//                modelMap.addAttribute("message", message);
//                modelMap.addAttribute("page", myPage.getPage());
//                modelMap.addAttribute("lastPage", myPage.getLastPage());
//                modelMap.addAttribute("pageSize", myPage.getPageSize());
//                modelMap.addAttribute("sort", myPage.getSort());
//                modelMap.addAttribute("infoKados", infoKados);
//
//                return "user/infoKado";
//            } else
//                return "redirect:/dashboard";
//        } else
//            return "redirect:/login";
//    }
//
//
//    @GetMapping("/dashboard/user/review/{page}")
//    public String dashboardUserReview  (@PathVariable Integer page,
//                                          @RequestParam(required = false, defaultValue = "10") Integer pageSize,
//                                          @RequestParam(required = false, defaultValue = "1") Integer sort,
//                                          @ModelAttribute("message") String message,
//                                          HttpSession httpSession,
//                                          ModelMap modelMap) {
//        String url = BASE_URL + "review";
//        User user = (User) httpSession.getAttribute("user");
//
//        if (user != null) {
//            boolean isUser = user.getRole().getName().equals("User");
//
//            if (isUser) {
//                if (page < 0)
//                    return "redirect:/dashboard/user/review/1";
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
//                MyResponse<MyPage<ArrayList<Review>>> myResponse;
//                MyPage<ArrayList<Review>> myPage = new MyPage<>();
//                ArrayList<Review> reviews = new ArrayList<>();
//
//                try {
//                    myResponse = mObjectMapper.readValue(response.getBody(),
//                            new TypeReference<MyResponse<MyPage<ArrayList<Review>>>>() {});
//
//                    myPage = myResponse.getData();
//                    reviews = myPage.getData();
//                } catch (IOException e) {
//                    LOGGER.error(e.getMessage());
//                }
//
//                modelMap.addAttribute("user", user);
//                modelMap.addAttribute("message", message);
//                modelMap.addAttribute("page", myPage.getPage());
//                modelMap.addAttribute("lastPage", myPage.getLastPage());
//                modelMap.addAttribute("pageSize", myPage.getPageSize());
//                modelMap.addAttribute("sort", myPage.getSort());
//                modelMap.addAttribute("reviews", reviews);
//
//                return "user/review";
//            } else
//                return "redirect:/dashboard";
//        } else
//            return "redirect:/login";
//    }
//
//
//    @GetMapping("/dashboard/user/bantuan/{page}")
//    public String dashboardUserBantuan  (@PathVariable Integer page,
//                                        @RequestParam(required = false, defaultValue = "10") Integer pageSize,
//                                        @RequestParam(required = false, defaultValue = "1") Integer sort,
//                                        @ModelAttribute("message") String message,
//                                        HttpSession httpSession,
//                                        ModelMap modelMap) {
//        String url = BASE_URL + "bantuan";
//        User user = (User) httpSession.getAttribute("user");
//
//        if (user != null) {
//            boolean isUser = user.getRole().getName().equals("User");
//
//            if (isUser) {
//                if (page < 0)
//                    return "redirect:/dashboard/user/bantuan/1";
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
//                MyResponse<MyPage<ArrayList<Bantuan>>> myResponse;
//                MyPage<ArrayList<Bantuan>> myPage = new MyPage<>();
//                ArrayList<Bantuan> bantuans = new ArrayList<>();
//
//                try {
//                    myResponse = mObjectMapper.readValue(response.getBody(),
//                            new TypeReference<MyResponse<MyPage<ArrayList<Bantuan>>>>() {});
//
//                    myPage = myResponse.getData();
//                    bantuans = myPage.getData();
//                } catch (IOException e) {
//                    LOGGER.error(e.getMessage());
//                }
//
//                modelMap.addAttribute("user", user);
//                modelMap.addAttribute("message", message);
//                modelMap.addAttribute("page", myPage.getPage());
//                modelMap.addAttribute("lastPage", myPage.getLastPage());
//                modelMap.addAttribute("pageSize", myPage.getPageSize());
//                modelMap.addAttribute("sort", myPage.getSort());
//                modelMap.addAttribute("bantuans", bantuans);
//
//                return "user/bantuan";
//            } else
//                return "redirect:/dashboard";
//        } else
//            return "redirect:/login";
//    }
//
//
//    @GetMapping("/dashboard/user/cariKado/add")
//    public String dashboardUserAddCariKado(@ModelAttribute("message") String message,
//                                             @ModelAttribute("cariKado") GiftInfoCategory cariKado,
//                                             HttpSession httpSession, ModelMap modelMap) {
//        User user = (User) httpSession.getAttribute("user");
//
//        if (user != null) {
//            modelMap.addAttribute("user", user);
//            modelMap.addAttribute("message", message);
//            modelMap.addAttribute("cariKado", cariKado);
//
//            return user.getRole().getName().equals("User") ? "user/addCariKado" : "redirect:/dashboard";
//        } else
//            return "redirect:/login";
//    }
//
//
//    @GetMapping("/dashboard/user/infoKado/add")
//    public String dashboardUserAddInfoKado(@ModelAttribute("message") String message,
//                                           @ModelAttribute("infoKado") GiftInfoCategory infoKado,
//                                           HttpSession httpSession, ModelMap modelMap) {
//        User user = (User) httpSession.getAttribute("user");
//
//        if (user != null) {
//            modelMap.addAttribute("user", user);
//            modelMap.addAttribute("message", message);
//            modelMap.addAttribute("infoKado", infoKado);
//
//            return user.getRole().getName().equals("User") ? "user/addInfoKado" : "redirect:/dashboard";
//        } else
//            return "redirect:/login";
//    }
//
//    @GetMapping("/dashboard/user/review/add")
//    public String dashboardUserAddReview(@ModelAttribute("message") String message,
//                                           @ModelAttribute("review") GiftInfoCategory review,
//                                           HttpSession httpSession, ModelMap modelMap) {
//        User user = (User) httpSession.getAttribute("user");
//
//        if (user != null) {
//            modelMap.addAttribute("user", user);
//            modelMap.addAttribute("message", message);
//            modelMap.addAttribute("review", review);
//
//            return user.getRole().getName().equals("User") ? "user/addReview" : "redirect:/dashboard";
//        } else
//            return "redirect:/login";
//    }
//
//    @GetMapping("/dashboard/user/bantuan/add")
//    public String dashboardUserAddBantuan(@ModelAttribute("message") String message,
//                                         @ModelAttribute("review") GiftInfoCategory bantuan,
//                                         HttpSession httpSession, ModelMap modelMap) {
//        User user = (User) httpSession.getAttribute("user");
//
//        if (user != null) {
//            modelMap.addAttribute("user", user);
//            modelMap.addAttribute("message", message);
//            modelMap.addAttribute("bantuan", bantuan);
//
//            return user.getRole().getName().equals("User") ? "user/addBantuan" : "redirect:/dashboard";
//        } else
//            return "redirect:/login";
//    }
//
//    @GetMapping("/dashboard/user/cariKado/add/{cariKadoId}")
//    public String dashboardUserAddCariKado(@PathVariable(required = false) Integer cariKadoId,
//                                             @ModelAttribute("message") String message,
//                                             HttpSession httpSession,
//                                             ModelMap modelMap) {
//        String url = BASE_URL + "cariKado";
//        User user = (User) httpSession.getAttribute("user");
//
//        if (user != null) {
//            boolean isUser = user.getRole().getName().equals("User");
//
//            if (isUser) {
//                if (cariKadoId != null) {
//                    url += "/" + cariKadoId;
//
//                    ResponseEntity<String> response = mRestTemplate.exchange(url, HttpMethod.GET, null,
//                            String.class);
//                    MyResponse<CariKado> myResponse;
//                    CariKado cariKado = null;
//
//                    try {
//                        myResponse = mObjectMapper.readValue(response.getBody(), new TypeReference<MyResponse<CariKado>>() {});
//                        cariKado = myResponse.getData();
//                    } catch (IOException e) {
//                        LOGGER.error(e.getMessage());
//                    }
//
//                    if (cariKado != null) {
//                        modelMap.addAttribute("user", user);
//                        modelMap.addAttribute("message", message);
//                        modelMap.addAttribute("cariKado", cariKado);
//
//                        return "user/addCariKado";
//                    } else
//                        return "redirect:/dashboard/user/cariKado/1";
//                } else
//                    return "redirect:/dashboard/user/cariKado/1";
//            } else
//                return "redirect:/dashboard";
//        } else
//            return "redirect:/login";
//    }
//
//
//    @GetMapping("/dashboard/user/infoKado/add/{infoKadoId}")
//    public String dashboardUserAddInfoKado(@PathVariable(required = false) Integer infoKadoId,
//                                           @ModelAttribute("message") String message,
//                                           HttpSession httpSession,
//                                           ModelMap modelMap) {
//        String url = BASE_URL + "infoKado";
//        User user = (User) httpSession.getAttribute("user");
//
//        if (user != null) {
//            boolean isUser = user.getRole().getName().equals("User");
//
//            if (isUser) {
//                if (infoKadoId != null) {
//                    url += "/" + infoKadoId;
//
//                    ResponseEntity<String> response = mRestTemplate.exchange(url, HttpMethod.GET, null,
//                            String.class);
//                    MyResponse<InfoKado> myResponse;
//                    InfoKado infoKado = null;
//
//                    try {
//                        myResponse = mObjectMapper.readValue(response.getBody(), new TypeReference<MyResponse<InfoKado>>() {});
//                        infoKado = myResponse.getData();
//                    } catch (IOException e) {
//                        LOGGER.error(e.getMessage());
//                    }
//
//                    if (infoKado != null) {
//                        modelMap.addAttribute("user", user);
//                        modelMap.addAttribute("message", message);
//                        modelMap.addAttribute("infoKado", infoKado);
//
//                        return "user/addInfoKado";
//                    } else
//                        return "redirect:/dashboard/user/infoKado/1";
//                } else
//                    return "redirect:/dashboard/user/infoKado/1";
//            } else
//                return "redirect:/dashboard";
//        } else
//            return "redirect:/login";
//    }
//
//
//    @GetMapping("/dashboard/user/review/add/{reviewId}")
//    public String dashboardUserAddReview(@PathVariable(required = false) Integer reviewId,
//                                           @ModelAttribute("message") String message,
//                                           HttpSession httpSession,
//                                           ModelMap modelMap) {
//        String url = BASE_URL + "review";
//        User user = (User) httpSession.getAttribute("user");
//
//        if (user != null) {
//            boolean isUser = user.getRole().getName().equals("User");
//
//            if (isUser) {
//                if (reviewId != null) {
//                    url += "/" + reviewId;
//
//                    ResponseEntity<String> response = mRestTemplate.exchange(url, HttpMethod.GET, null,
//                            String.class);
//                    MyResponse<Review> myResponse;
//                    Review review = null;
//
//                    try {
//                        myResponse = mObjectMapper.readValue(response.getBody(), new TypeReference<MyResponse<Review>>() {});
//                        review = myResponse.getData();
//                    } catch (IOException e) {
//                        LOGGER.error(e.getMessage());
//                    }
//
//                    if (review != null) {
//                        modelMap.addAttribute("user", user);
//                        modelMap.addAttribute("message", message);
//                        modelMap.addAttribute("review", review);
//
//                        return "user/addReview";
//                    } else
//                        return "redirect:/dashboard/user/review/1";
//                } else
//                    return "redirect:/dashboard/user/review/1";
//            } else
//                return "redirect:/dashboard";
//        } else
//            return "redirect:/login";
//    }
//
//
//    @GetMapping("/dashboard/user/bantuan/add/{bantuanId}")
//    public String dashboardUserAddBantuan(@PathVariable(required = false) Integer bantuanId,
//                                         @ModelAttribute("message") String message,
//                                         HttpSession httpSession,
//                                         ModelMap modelMap) {
//        String url = BASE_URL + "review";
//        User user = (User) httpSession.getAttribute("user");
//
//        if (user != null) {
//            boolean isUser = user.getRole().getName().equals("User");
//
//            if (isUser) {
//                if (bantuanId != null) {
//                    url += "/" + bantuanId;
//
//                    ResponseEntity<String> response = mRestTemplate.exchange(url, HttpMethod.GET, null,
//                            String.class);
//                    MyResponse<Bantuan> myResponse;
//                    Bantuan bantuan = null;
//
//                    try {
//                        myResponse = mObjectMapper.readValue(response.getBody(), new TypeReference<MyResponse<Bantuan>>() {});
//                        bantuan = myResponse.getData();
//                    } catch (IOException e) {
//                        LOGGER.error(e.getMessage());
//                    }
//
//                    if (bantuan != null) {
//                        modelMap.addAttribute("user", user);
//                        modelMap.addAttribute("message", message);
//                        modelMap.addAttribute("bantuan", bantuan);
//
//                        return "user/addBantuan";
//                    } else
//                        return "redirect:/dashboard/user/bantuan/1";
//                } else
//                    return "redirect:/dashboard/user/bantuan/1";
//            } else
//                return "redirect:/dashboard";
//        } else
//            return "redirect:/login";
//    }
//
//
//    @PostMapping("/dashboard/user/cariKado/add")
//    public String dashboardUserAddCariKado(@RequestParam(name = "cariKadoId", required = false) Integer cariKadoId,
//                                             @RequestParam("cariKadoName") String cariKadoTitle,
//                                             HttpSession httpSession,
//                                             RedirectAttributes redirectAttributes) {
//        String url = BASE_URL + "cariKado/add";
//        User user = (User) httpSession.getAttribute("user");
//
//        if (user != null) {
//            boolean isUser = user.getRole().getName().equals("User");
//
//            if (isUser) {
//                boolean isEdit = cariKadoId != null;
//
//                CariKado cariKado = new CariKado();
//                cariKado.setTitle(cariKadoTitle);
//
//                if (isEdit)
//                    cariKado.setId(cariKadoId);
//
//                HttpHeaders httpHeaders = new HttpHeaders();
//                httpHeaders.setContentType(MediaType.APPLICATION_JSON);
//
//                String requestJson = "";
//
//                try {
//                    requestJson = mObjectMapper.writeValueAsString(cariKado);
//                } catch (JsonProcessingException e) {
//                    LOGGER.error(e.getMessage());
//                }
//
//                HttpEntity<String> request = new HttpEntity<>(requestJson, httpHeaders);
//
//                ResponseEntity<String> response = mRestTemplate.exchange(url, HttpMethod.POST, request, String.class);
//                MyResponse<Integer> myResponse = null;
//                Integer responseInt = null;
//                String message;
//
//                try {
//                    myResponse = mObjectMapper.readValue(response.getBody(), new TypeReference<MyResponse<Integer>>() {});
//                    responseInt = myResponse.getData();
//                } catch (IOException e) {
//                    LOGGER.error(e.getMessage());
//                }
//
//                if (responseInt != null && responseInt == 1) {
//                    redirectAttributes.addAttribute("message", myResponse.getMessage());
//                } else {
//                    if (responseInt == null)
//                        message = "Internal server error";
//                    else
//                        message = myResponse.getMessage();
//
//                    cariKado = new CariKado(cariKadoTitle);
//
//                    redirectAttributes.addAttribute("message", message);
//                    redirectAttributes.addAttribute("cariKado", cariKado);
//                }
//
//                String returnString = "redirect:/dashboard/user/cariKado/add";
//
//                if (isEdit)
//                    returnString += "/" + cariKadoId;
//
//                return returnString;
//            } else
//                return "redirect:/dashboard";
//        } else
//            return "redirect:/login";
//    }
//
//
//    @PostMapping("/dashboard/user/infoKado/add")
//    public String dashboardUserAddInfoKado(@RequestParam(name = "infoKadoId", required = false) Integer infoKadoId,
//                                           @RequestParam("infoKadoName") String infoKadoTitle,
//                                           HttpSession httpSession,
//                                           RedirectAttributes redirectAttributes) {
//        String url = BASE_URL + "infoKado/add";
//        User user = (User) httpSession.getAttribute("user");
//
//        if (user != null) {
//            boolean isUser = user.getRole().getName().equals("User");
//
//            if (isUser) {
//                boolean isEdit = infoKadoId != null;
//
//                InfoKado infoKado = new InfoKado();
//                infoKado.setTitle(infoKadoTitle);
//
//                if (isEdit)
//                    infoKado.setId(infoKadoId);
//
//                HttpHeaders httpHeaders = new HttpHeaders();
//                httpHeaders.setContentType(MediaType.APPLICATION_JSON);
//
//                String requestJson = "";
//
//                try {
//                    requestJson = mObjectMapper.writeValueAsString(infoKado);
//                } catch (JsonProcessingException e) {
//                    LOGGER.error(e.getMessage());
//                }
//
//                HttpEntity<String> request = new HttpEntity<>(requestJson, httpHeaders);
//
//                ResponseEntity<String> response = mRestTemplate.exchange(url, HttpMethod.POST, request, String.class);
//                MyResponse<Integer> myResponse = null;
//                Integer responseInt = null;
//                String message;
//
//                try {
//                    myResponse = mObjectMapper.readValue(response.getBody(), new TypeReference<MyResponse<Integer>>() {});
//                    responseInt = myResponse.getData();
//                } catch (IOException e) {
//                    LOGGER.error(e.getMessage());
//                }
//
//                if (responseInt != null && responseInt == 1) {
//                    redirectAttributes.addAttribute("message", myResponse.getMessage());
//                } else {
//                    if (responseInt == null)
//                        message = "Internal server error";
//                    else
//                        message = myResponse.getMessage();
//
//                    infoKado = new InfoKado(infoKadoTitle);
//
//                    redirectAttributes.addAttribute("message", message);
//                    redirectAttributes.addAttribute("infoKado", infoKado);
//                }
//
//                String returnString = "redirect:/dashboard/user/infoKado/add";
//
//                if (isEdit)
//                    returnString += "/" + infoKadoId;
//
//                return returnString;
//            } else
//                return "redirect:/dashboard";
//        } else
//            return "redirect:/login";
//    }
//
//
//    @PostMapping("/dashboard/user/review/add")
//    public String dashboardUserAddReview(@RequestParam(name = "reviewId", required = false) Integer reviewId,
//                                           @RequestParam("reviewName") String reviewTitle,
//                                           HttpSession httpSession,
//                                           RedirectAttributes redirectAttributes) {
//        String url = BASE_URL + "review/add";
//        User user = (User) httpSession.getAttribute("user");
//
//        if (user != null) {
//            boolean isUser = user.getRole().getName().equals("User");
//
//            if (isUser) {
//                boolean isEdit = reviewId != null;
//
//                Review review = new Review();
//                review.setTitle(reviewTitle);
//
//                if (isEdit)
//                    review.setId(reviewId);
//
//                HttpHeaders httpHeaders = new HttpHeaders();
//                httpHeaders.setContentType(MediaType.APPLICATION_JSON);
//
//                String requestJson = "";
//
//                try {
//                    requestJson = mObjectMapper.writeValueAsString(review);
//                } catch (JsonProcessingException e) {
//                    LOGGER.error(e.getMessage());
//                }
//
//                HttpEntity<String> request = new HttpEntity<>(requestJson, httpHeaders);
//
//                ResponseEntity<String> response = mRestTemplate.exchange(url, HttpMethod.POST, request, String.class);
//                MyResponse<Integer> myResponse = null;
//                Integer responseInt = null;
//                String message;
//
//                try {
//                    myResponse = mObjectMapper.readValue(response.getBody(), new TypeReference<MyResponse<Integer>>() {});
//                    responseInt = myResponse.getData();
//                } catch (IOException e) {
//                    LOGGER.error(e.getMessage());
//                }
//
//                if (responseInt != null && responseInt == 1) {
//                    redirectAttributes.addAttribute("message", myResponse.getMessage());
//                } else {
//                    if (responseInt == null)
//                        message = "Internal server error";
//                    else
//                        message = myResponse.getMessage();
//
//                    review = new Review(reviewTitle);
//
//                    redirectAttributes.addAttribute("message", message);
//                    redirectAttributes.addAttribute("review", review);
//                }
//
//                String returnString = "redirect:/dashboard/user/review/add";
//
//                if (isEdit)
//                    returnString += "/" + reviewId;
//
//                return returnString;
//            } else
//                return "redirect:/dashboard";
//        } else
//            return "redirect:/login";
//    }
//
//
//    @PostMapping("/dashboard/user/bantuan/add")
//    public String dashboardUserAddBantuan(@RequestParam(name = "bantuanId", required = false) Integer bantuanId,
//                                         @RequestParam("bantuanName") String bantuanTitle,
//                                         HttpSession httpSession,
//                                         RedirectAttributes redirectAttributes) {
//        String url = BASE_URL + "bantuan/add";
//        User user = (User) httpSession.getAttribute("user");
//
//        if (user != null) {
//            boolean isUser = user.getRole().getName().equals("User");
//
//            if (isUser) {
//                boolean isEdit = bantuanId != null;
//
//                Bantuan bantuan = new Bantuan();
//                bantuan.setTitle(bantuanTitle);
//
//                if (isEdit)
//                    bantuan.setId(bantuanId);
//
//                HttpHeaders httpHeaders = new HttpHeaders();
//                httpHeaders.setContentType(MediaType.APPLICATION_JSON);
//
//                String requestJson = "";
//
//                try {
//                    requestJson = mObjectMapper.writeValueAsString(bantuan);
//                } catch (JsonProcessingException e) {
//                    LOGGER.error(e.getMessage());
//                }
//
//                HttpEntity<String> request = new HttpEntity<>(requestJson, httpHeaders);
//
//                ResponseEntity<String> response = mRestTemplate.exchange(url, HttpMethod.POST, request, String.class);
//                MyResponse<Integer> myResponse = null;
//                Integer responseInt = null;
//                String message;
//
//                try {
//                    myResponse = mObjectMapper.readValue(response.getBody(), new TypeReference<MyResponse<Integer>>() {});
//                    responseInt = myResponse.getData();
//                } catch (IOException e) {
//                    LOGGER.error(e.getMessage());
//                }
//
//                if (responseInt != null && responseInt == 1) {
//                    redirectAttributes.addAttribute("message", myResponse.getMessage());
//                } else {
//                    if (responseInt == null)
//                        message = "Internal server error";
//                    else
//                        message = myResponse.getMessage();
//
//                    bantuan = new Bantuan(bantuanTitle);
//
//                    redirectAttributes.addAttribute("message", message);
//                    redirectAttributes.addAttribute("bantuan", bantuan);
//                }
//
//                String returnString = "redirect:/dashboard/user/bantuan/add";
//
//                if (isEdit)
//                    returnString += "/" + bantuanId;
//
//                return returnString;
//            } else
//                return "redirect:/dashboard";
//        } else
//            return "redirect:/login";
//    }
//
//
//    @GetMapping("/dashboard/user/cariKado/delete/{giftInfoId}")
//    public String dashboardUserDeleteCariKado(@PathVariable Integer cariKadoId,
//                                                HttpSession httpSession,
//                                                RedirectAttributes redirectAttributes) {
//        String url = BASE_URL + "cariKado/";
//        User user = (User) httpSession.getAttribute("user");
//
//        if (user != null) {
//            boolean isUser = user.getRole().getName().equals("User");
//
//            if (isUser) {
//                if (cariKadoId != null) {
//                    url += cariKadoId;
//
//                    ResponseEntity<String> response = mRestTemplate.exchange(url, HttpMethod.DELETE, null, String.class);
//                    MyResponse<Integer> myResponse;
//                    Integer responseInt = null;
//
//                    try {
//                        myResponse = mObjectMapper.readValue(response.getBody(), new TypeReference<MyResponse<Integer>>() {});
//                        responseInt = myResponse.getData();
//                    } catch (IOException e) {
//                        LOGGER.error(e.getMessage());
//                    }
//
//                    String message = responseInt != null && responseInt == 1 ? "CariKado delete success" : "CariKadoId delete failed";
//
//                    redirectAttributes.addAttribute("message", message);
//                    return "redirect:/dashboard/user/cariKado/1";
//                } else
//                    return "redirect:/dashboard/user";
//            } else
//                return "redirect:/dashboard";
//        } else
//            return "redirect:/login";
//    }
//
//
//    @GetMapping("/dashboard/user/infoKado/delete/{infoKadoId}")
//    public String dashboardUserDeleteInfoKado(@PathVariable Integer infoKadoId,
//                                              HttpSession httpSession,
//                                              RedirectAttributes redirectAttributes) {
//        String url = BASE_URL + "infoKado/";
//        User user = (User) httpSession.getAttribute("user");
//
//        if (user != null) {
//            boolean isUser = user.getRole().getName().equals("User");
//
//            if (isUser) {
//                if (infoKadoId != null) {
//                    url += infoKadoId;
//
//                    ResponseEntity<String> response = mRestTemplate.exchange(url, HttpMethod.DELETE, null, String.class);
//                    MyResponse<Integer> myResponse;
//                    Integer responseInt = null;
//
//                    try {
//                        myResponse = mObjectMapper.readValue(response.getBody(), new TypeReference<MyResponse<Integer>>() {});
//                        responseInt = myResponse.getData();
//                    } catch (IOException e) {
//                        LOGGER.error(e.getMessage());
//                    }
//
//                    String message = responseInt != null && responseInt == 1 ? "InfoKado delete success" : "InfoKadoId delete failed";
//
//                    redirectAttributes.addAttribute("message", message);
//                    return "redirect:/dashboard/user/infoKado/1";
//                } else
//                    return "redirect:/dashboard/user";
//            } else
//                return "redirect:/dashboard";
//        } else
//            return "redirect:/login";
//    }
//
//
//    @GetMapping("/dashboard/user/review/delete/{reviewId}")
//    public String dashboardUserDeleteReview(@PathVariable Integer reviewId,
//                                              HttpSession httpSession,
//                                              RedirectAttributes redirectAttributes) {
//        String url = BASE_URL + "review/";
//        User user = (User) httpSession.getAttribute("user");
//
//        if (user != null) {
//            boolean isUser = user.getRole().getName().equals("User");
//
//            if (isUser) {
//                if (reviewId != null) {
//                    url += reviewId;
//
//                    ResponseEntity<String> response = mRestTemplate.exchange(url, HttpMethod.DELETE, null, String.class);
//                    MyResponse<Integer> myResponse;
//                    Integer responseInt = null;
//
//                    try {
//                        myResponse = mObjectMapper.readValue(response.getBody(), new TypeReference<MyResponse<Integer>>() {});
//                        responseInt = myResponse.getData();
//                    } catch (IOException e) {
//                        LOGGER.error(e.getMessage());
//                    }
//
//                    String message = responseInt != null && responseInt == 1 ? "Review delete success" : "ReviewId delete failed";
//
//                    redirectAttributes.addAttribute("message", message);
//                    return "redirect:/dashboard/user/review/1";
//                } else
//                    return "redirect:/dashboard/user";
//            } else
//                return "redirect:/dashboard";
//        } else
//            return "redirect:/login";
//    }
//
//
//    @GetMapping("/dashboard/user/bantuan/delete/{bantuanId}")
//    public String dashboardUserDeleteBantuan(@PathVariable Integer bantuanId,
//                                              HttpSession httpSession,
//                                              RedirectAttributes redirectAttributes) {
//        String url = BASE_URL + "bantuan/";
//        User user = (User) httpSession.getAttribute("user");
//
//        if (user != null) {
//            boolean isUser = user.getRole().getName().equals("User");
//
//            if (isUser) {
//                if (bantuanId != null) {
//                    url += bantuanId;
//
//                    ResponseEntity<String> response = mRestTemplate.exchange(url, HttpMethod.DELETE, null, String.class);
//                    MyResponse<Integer> myResponse;
//                    Integer responseInt = null;
//
//                    try {
//                        myResponse = mObjectMapper.readValue(response.getBody(), new TypeReference<MyResponse<Integer>>() {});
//                        responseInt = myResponse.getData();
//                    } catch (IOException e) {
//                        LOGGER.error(e.getMessage());
//                    }
//
//                    String message = responseInt != null && responseInt == 1 ? "Bantuan delete success" : "BantuanId delete failed";
//
//                    redirectAttributes.addAttribute("message", message);
//                    return "redirect:/dashboard/user/bantuan/1";
//                } else
//                    return "redirect:/dashboard/user";
//            } else
//                return "redirect:/dashboard";
//        } else
//            return "redirect:/login";
//    }
//
//
//    @GetMapping("/dashboard/customerservice")
//    public String dashboardCustomerService(HttpSession httpSession) {
//        User user = (User) httpSession.getAttribute("user");
//
//        if (user != null)
//            return user.getRole().getName().equals("Customer Service") ? "customerservice/index" : "redirect:/dashboard";
//        else
//            return "redirect:/login";
//    }
//
//}
