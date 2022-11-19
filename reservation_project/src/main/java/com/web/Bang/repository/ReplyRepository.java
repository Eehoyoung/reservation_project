package com.web.Bang.repository;

import com.web.Bang.model.Reply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface ReplyRepository extends JpaRepository<Reply, Integer> {

    @Modifying
    @Query(value = "DELETE FROM reply WHERE id = ?", nativeQuery = true)
    void deleteByReplyId(int id);

}
