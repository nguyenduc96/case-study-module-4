package com.group3.repositories.vip;

import com.group3.models.vip.Vip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IVipRepository extends JpaRepository<Vip, Long> {
}
