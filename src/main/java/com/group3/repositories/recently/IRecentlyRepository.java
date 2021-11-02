package com.group3.repositories.recently;

import com.group3.models.recently.Recently;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRecentlyRepository extends JpaRepository<Recently, Long> {
}
