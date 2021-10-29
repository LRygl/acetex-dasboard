package com.acetex.acetexdasboard.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.acetex.acetexdasboard.Model.Record;

@Repository
public interface RecordRepository extends JpaRepository<Record, Long> {

}
