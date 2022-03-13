package com.zepto.irctc.repository;

import com.zepto.irctc.model.Coache;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;

@Repository
public interface CoacheRepository extends JpaRepository<Coache, Integer> {
    @Query("Select c from Coache c where c.id in :list and c.isAvailable = false")
    public List<Coache> findBookedCoachesInList( List<Integer> list);
}
