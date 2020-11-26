import com.parking.pojo.User;
import com.parking.service.UserService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyTest {
    @Test
    public void test(){
        ApplicationContext context=new ClassPathXmlApplicationContext("applicationContext.xml");
        UserService userServiceImpl=(UserService) context.getBean("UserServiceImpl");
        for (User user : userServiceImpl.queryAllUser()) {
            System.out.println(user);

        }
    }

}
