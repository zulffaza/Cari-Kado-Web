package com.example.carikado.service;

import com.example.carikado.model.Review;
import com.example.carikado.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("reviewService")
public class ReviewService {

    private ReviewRepository mReviewRepository;

    @Autowired
    public ReviewService(ReviewRepository reviewRepository) {
        mReviewRepository = reviewRepository;
    }

    public List<Review> getAllReview() {
        return mReviewRepository.findAll();
    }

    public Review getReviewById(int id) {
        return mReviewRepository.findOne(id);
    }

    public Review addReview(Review review) {
        return mReviewRepository.save(review);
    }
}
