package com.example.carikado.RESTcontroller;

import com.example.carikado.model.MyPage;
import com.example.carikado.model.MyResponse;
import com.example.carikado.model.Review;
import com.example.carikado.service.ReviewService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
public class ReviewRESTController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReviewRESTController.class);
    private static final String[] PROPERTIES = {
            "createdAt"
    };
    private static final String[] DIRECTION = {
            "asc",
            "desc"
    };

    private ReviewService mReviewService;

    @Autowired
    public ReviewRESTController(ReviewService reviewService) {
        mReviewService = reviewService;
    }

    @GetMapping("/api/review/count/all")
    public MyResponse<Integer> countReviews() {
        String message = "Count reviews success";
        Integer count = mReviewService.count();
        return new MyResponse<>(message, count);
    }

    @GetMapping("/api/review/all")
    public MyResponse<List> findReviews() {
        String message = "Find reviews success";
        ArrayList<Review> reviews = (ArrayList<Review>) mReviewService.findAll();
        return new MyResponse<>(message, reviews);
    }

    @GetMapping("/api/review")
    public MyResponse<MyPage<List>> findReviews(@RequestParam(required = false, defaultValue = "0") Integer page,
                                              @RequestParam(required = false, defaultValue = "10") Integer pageSize,
                                              @RequestParam(required = false) Integer sort) {
        ArrayList<String> properties = new ArrayList<>();
        List<Review> reviews;
        String message;
        Sort.Direction direction;

        if (page > 0)
            page -= 1;

        if (page < 0)
            page = 0;

        int propertiesIndex = 0;
        int directionIndex = 0;

        if (sort != null && sort >= 1 && sort <= 2)
            directionIndex = sort - 1;

        direction = Sort.Direction.fromString(DIRECTION[directionIndex]);
        properties.add(PROPERTIES[propertiesIndex]);

        Sort sortOrder = new Sort(direction, properties);
        PageRequest pageRequest = new PageRequest(page, pageSize, sortOrder);
        Page<Review> reviewPage = mReviewService.findAllPageable(pageRequest);

        reviews = reviewPage.getContent();
        message = "Find review success";

        MyPage<List> myPage = new MyPage<>();

        myPage.setPage(++page);
        myPage.setLastPage(reviewPage.getTotalPages() == 0 ? 1 : reviewPage.getTotalPages());
        myPage.setPageSize(pageSize);
        myPage.setSort(sort == null ? 1 : sort);
        myPage.setTotalElement(reviewPage.getTotalElements());
        myPage.setData(reviews);

        return new MyResponse<>(message, myPage);
    }

    @GetMapping("/api/review/{reviewId}")
    public MyResponse<Review> findReview(@PathVariable Integer reviewId) {
        Review review = null;
        String message = "Review not found";

        if (reviewId != null && reviewId >= 0) {
            review = mReviewService.findReview(reviewId);

            if (review != null)
                message = "Find review success";
        }

        return new MyResponse<>(message, review);
    }

    @PostMapping("/api/review/add")
    public MyResponse<Integer> addReview(@RequestBody Review review) {
        String message;
        int response;

        try {
            review.setCreatedAt(new Date());
            review = mReviewService.addReview(review);

            boolean isSuccess = review != null;
            message = isSuccess ? "Review add success" : "Review add failed";
            response = isSuccess ? 1 : 0;
        } catch (DataIntegrityViolationException e) {
            message = "Review add failed - Review already exists";
            response = 0;
        } catch (Exception e) {
            message = "Review add failed - Internal Server Error";
            response = 0;
        }

        return new MyResponse<>(message, response);
    }

    @DeleteMapping("/api/review/{reviewId}")
    public MyResponse<Integer> deleteReview(@PathVariable Integer reviewId) {
        String message;
        Integer response;

        if (reviewId != null && reviewId >= 0) {
            try {
                mReviewService.deleteReview(reviewId);
                response = 1;
            } catch (EmptyResultDataAccessException e) {
                LOGGER.error(e.getMessage());
                response = 0;
            } catch (DataIntegrityViolationException e) {
                LOGGER.error(e.getMessage());
                response = 0;
            }

            boolean isDeleted = response == 1;
            message = isDeleted ? "Delete review success" : "Delete review failed";
            response = isDeleted ? 1 : 0;
        } else {
            message = "Review not found";
            response = 0;
        }

        return new MyResponse<>(message, response);
    }
}
