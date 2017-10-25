package com.example.carikado.RESTcontroller;

import com.example.carikado.model.MyResponse;
import com.example.carikado.model.Review;
import com.example.carikado.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class ReviewRESTController {

    private ReviewService mReviewService;

    @Autowired
    public ReviewRESTController(ReviewService reviewService) {
        mReviewService = reviewService;
    }

    @PostMapping("/api/review/add")
    public MyResponse<Integer> addReview(@RequestBody Review review) {
        review.setCreatedAt(new Date());
        review = mReviewService.addReview(review);

        boolean isSuccess = review != null;
        String message = isSuccess ? "Review add success" : "Review add failed";
        int response = isSuccess ? 1 : 0;

        return new MyResponse<>(message, response);
    }
}
