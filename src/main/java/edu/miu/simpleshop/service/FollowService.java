package edu.miu.simpleshop.service;



import edu.miu.simpleshop.domain.Follow;
import edu.miu.simpleshop.domain.Seller;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface FollowService {
    public List<Follow> findAll();
    public Follow save(Follow follow);
    public void delete(Follow follower);
    public List<Seller> getAllBySeller(Long Id);
  //  public List<Seller> findAllByProductAndReviewStatus(Follow.FollowStatus followerStatus, Long id);
    public Follow getFollowByBuyerAndSeller(Long sellerId, Long buyerId);

}
