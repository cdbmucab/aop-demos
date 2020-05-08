package cdbm.ucab.aopdemos.config

import groovy.transform.CompileStatic
import org.springframework.boot.context.properties.ConfigurationProperties

@CompileStatic
@ConfigurationProperties(prefix ="aopdemos")
class AopDbSanitizerConfigurationProperties {
	boolean sanitizeData
}
