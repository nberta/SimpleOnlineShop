package edu.miu.simpleshop.service.impl;

import edu.miu.simpleshop.domain.Follow;
import edu.miu.simpleshop.domain.Seller;
import edu.miu.simpleshop.repository.FollowRepository;
import edu.miu.simpleshop.service.FollowService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

    @Service
    @Transactional
    public class FollowServiceImpl implements FollowService {

        private FollowRepository followRepository;

        @Override
        public List<Follow> findAll() {
            return (List<Follow>) followRepository.findAll();
        }

        @Override
        public Follow save(Follow follow) {
            return followRepository.save(follow);
        }

        @Override
        public Follow find(Long id) {
            return followRepository.findById(id).get();
        }

        @Override
        public Follow put(Follow follow) { return followRepository.save(follow); }

        @Override
        public void delete(Follow follower) {
            followRepository.delete(follower);
        }

        @Override
        public List<Seller> findAllBySeller(Long Id) {
            return followRepository.findAllBySeller(Id);
        }

        @Override
        public Follow findFollowByBuyerAndSeller(Long sellerId, Long buyerId) {
            return followRepository.findFollowByBuyerAndSeller(sellerId, buyerId);
        }
    }


