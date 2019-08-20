package com.darkarth.spring.boot.batch.demo;

import static org.assertj.core.api.Assertions.assertThat;

import com.darkarth.spring.boot.batch.demo.config.ConfiguracionBatchTest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes={ConfiguracionBatchTest.class})
public class SpringBatchDemoSimpleAplicacionTests {

	@Autowired
	private JobLauncherTestUtils jltu;

	@Test
	public void pruebaDemoSimple() throws Exception {
		JobExecution je = jltu.launchJob();

		assertThat(je.getExitStatus().getExitCode()).isEqualTo(ExitStatus.COMPLETED.getExitCode());

	}

}
