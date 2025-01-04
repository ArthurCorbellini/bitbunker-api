package com.artcorb.bitbunker.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.artcorb.bitbunker.models.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

}
