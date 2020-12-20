package com.backend.shop.mapper;

import com.backend.shop.pojo.ReplyList;
import com.backend.shop.pojo.AskForGoodPost;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface AskForGoodPostDao {

    List<AskForGoodPost> findAll();
    void add(AskForGoodPost askForGoodPost);
    void delete(@Param("afgPostId") int afgPostId);

    String findNickname(@Param("afgPostId") int afgPostId);
    void deleteRe(@Param("afgPostId") int afgPostId);

    List<ReplyList> findReplies(@Param("afgPostId") int afgPostId);
    int selectCount(@Param("afgPostId") int afgPostId);


}
