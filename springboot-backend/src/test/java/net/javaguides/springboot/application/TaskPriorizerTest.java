package net.javaguides.springboot.application;

import org.junit.jupiter.api.*;
//import org.junit.jupiter.api.Assertions.*;
//import org.junit.jupiter.api.DisplayName;
//mport org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringExtension;


@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TaskPriorizerTest {

    @Autowired
    private TaskPriorizer taskPriorizer;


	@Test
	public void testEmpty() {
		assertEquals(null, this.taskPriorizer.fn_get_first_task());
	}
}

