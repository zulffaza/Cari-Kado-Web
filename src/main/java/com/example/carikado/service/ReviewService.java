package com.example.carikado.service;

import com.example.carikado.model.Review;
import com.example.carikado.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("reviewService")
public class ReviewService {

    private ReviewRepository mReviewRepository;

    @Autowired
    public ReviewService(ReviewRepository reviewRepository) {
        mReviewRepository = reviewRepository;
    }

    public Integer count() {
        return (int) mReviewRepository.count();
    }

    public List<Review> findAll() {
        return mReviewRepository.findAllWithSort();
    }

    public Page<Review> findAllPageable(Pageable pageable) {
        return mReviewRepository.findAll(pageable);
    }

    public Review findReview(Integer id) { return mReviewRepository.findOne(id); }

    public Review addReview(Review review) {
        return mReviewRepository.save(review);
    }

    public void deleteReview(Integer id) { mReviewRepository.delete(id); }
}
