package com.example.mutualfundportfolio.repository;

import com.example.mutualfundportfolio.entity.FundPosition;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FundPositionRepository extends JpaRepository<FundPosition, Long> {
    List<FundPosition> findByUserId(Long userId);
}
