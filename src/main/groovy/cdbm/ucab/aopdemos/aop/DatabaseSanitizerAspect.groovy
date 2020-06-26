package cdbm.ucab.aopdemos.aop

import cdbm.ucab.aopdemos.config.AopDbSanitizerConfigurationProperties
import cdbm.ucab.aopdemos.data.UserData
import groovy.transform.CompileStatic
import org.aspectj.lang.JoinPoint
import org.aspectj.lang.annotation.AfterReturning
import org.aspectj.lang.annotation.Aspect
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Aspect
@CompileStatic
@Component
class DatabaseSanitizerAspect {

	private Logger log = LoggerFactory.getLogger(DatabaseSanitizerAspect.class)

	@Autowired
	AopDbSanitizerConfigurationProperties properties

	private String sanitizeEmail(String email) {
		log.info("Sanitizing email data = ${email}")
		String emailAddressCleanedUp = email?.replaceAll("[^A-Za-z0-9]", "")
		return (emailAddressCleanedUp ? "joininglist+${emailAddressCleanedUp}@traffic.com" : null)
	}

	private sanitizeEmail(UserData userDataFields) {
		userDataFields.email = sanitizeEmail(userDataFields.email)
	}

	@AfterReturning(value = "execution(* cdbm.ucab.aopdemos.repository..*.*(..))", returning = "result")
	void sanitizeEventData(JoinPoint joinPoint, Object result) {
		if (properties.sanitizeData) {
			if (result instanceof UserData) {
				UserData userDataFields = result as UserData
				sanitizeEmail(userDataFields)
			}

			if (result instanceof List && !(result as List).isEmpty()) {
				Object firstElement = (result as List)?.get(0)

				if (firstElement instanceof UserData) {
					List<UserData> usersAndEvents = result as List<UserData>
					usersAndEvents.each { UserData userAndEvent ->
						sanitizeEmail(userAndEvent)
					}
				}
			}
		}
	}
}
