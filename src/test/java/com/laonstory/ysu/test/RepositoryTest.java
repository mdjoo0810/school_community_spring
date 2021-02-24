package com.laonstory.ysu.test;

import com.laonstory.ysu.test.config.TestProfile;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles(TestProfile.TEST)
@Ignore
public class RepositoryTest {
}
