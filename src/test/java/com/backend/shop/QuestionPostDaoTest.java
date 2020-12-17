//package com.backend.shop;
//
//import com.backend.shop.util.MybatisUtils;
//import com.backend.shop.mapper.QuestionPostDao;
//import com.backend.shop.pojo.QuestionPost;
//import java.util.List;
//import org.apache.ibatis.session.SqlSession;
//import org.junit.Test;
//
//public class QuestionPostDaoTest {
//    @Test
//    public void test() {
//
//        //获取sqlSession
//        SqlSession sqlSession= MybatisUtils.getSqlSession();
//
//        //1
//        QuestionPostDao questionPostDao=sqlSession.getMapper(QuestionPostDao.class);
//        List<QuestionPost> questionPostList=questionPostDao.getQuestionPostList();
//
//        //2
////        List<User> userList=sqlSession.selectList("com.test.dao.UserDao.getUserList");
//
//        //输出
//        for (QuestionPost questionPost : questionPostList) {
//            System.out.println(questionPost);
//        }
//
//        //关闭sqlSession
//        sqlSession.close();
//
//
//
//    }
//}
