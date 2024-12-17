import com.jasper.manager.ManagerApplication;
import com.jasper.manager.service.SysUserService;
import com.jasper.model.entity.system.SysUser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = {ManagerApplication.class})
public class MyBatisPlusTests {

    @Autowired
    SysUserService sysUserService;


    @Test
    public void insertSysUserTest() {
        SysUser sysUser = SysUser.builder()
                .username("mbptest3")
                .name("测试mbp人员2")
                .description("这是一个测试用例")
                .phone("123456789")
                .status(1).build();
        sysUserService.addSysUser(sysUser);
        // fixme 插入的id有问题，应该是自增的，但是插入了一个奇怪的数字
    }
}
