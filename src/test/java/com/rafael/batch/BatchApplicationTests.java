package com.rafael.batch;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = BatchApplication.class)
class BatchApplicationTests {

    @Test
    void contextLoads() {
        BatchApplication.main(new String[]{});
    }

}
