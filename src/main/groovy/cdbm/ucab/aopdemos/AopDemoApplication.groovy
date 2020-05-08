package cdbm.ucab.aopdemos

import cdbm.ucab.aopdemos.config.AopDbSanitizerConfigurationProperties
import groovy.transform.CompileStatic
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.scheduling.annotation.EnableScheduling

@CompileStatic
@SpringBootApplication
@EnableScheduling
@EnableConfigurationProperties([AopDbSanitizerConfigurationProperties.class])
class AopDemoApplication {

	static void main(String[] args) {
		SpringApplication.run(AopDemoApplication, args)
	}
}
