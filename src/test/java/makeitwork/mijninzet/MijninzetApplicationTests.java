package makeitwork.mijninzet;

import makeitwork.mijninzet.model.Task;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


import static junit.framework.TestCase.assertTrue;
import static org.junit.Assume.assumeTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MijninzetApplicationTests {

	@Test
	public void contextLoads() {
	}


//	@Test
//	public void testEquals(){
//		TaskInlezen.tasks();
//	}
}
