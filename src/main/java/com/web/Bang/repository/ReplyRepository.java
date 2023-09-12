package com.web.Bang.repository;

import com.web.Bang.model.Reply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ReplyRepository extends JpaRepository<Reply, Integer> {

    @Modifying
    @Query("DELETE FROM Reply r where r.id = :id")
    void deleteByReplyId(@Param("id") int id);

}
