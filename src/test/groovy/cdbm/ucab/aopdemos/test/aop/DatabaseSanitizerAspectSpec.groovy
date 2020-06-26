package cdbm.ucab.aopdemos.test.aop

import cdbm.ucab.aopdemos.aop.DatabaseSanitizerAspect
import cdbm.ucab.aopdemos.config.AopDbSanitizerConfigurationProperties
import cdbm.ucab.aopdemos.data.UserData
import groovy.transform.CompileDynamic
import org.aspectj.lang.JoinPoint
import spock.lang.Specification

@CompileDynamic
class DatabaseSanitizerAspectSpec extends Specification {

	DatabaseSanitizerAspect sanitizerAspect = new DatabaseSanitizerAspect()

	def "test sanitize UserDataFields" () {
		setup:
		sanitizerAspect.properties = new AopDbSanitizerConfigurationProperties(sanitizeData: sanitizeDataEnabled)
		UserData userData = new UserData(email: emailAddress)
		JoinPoint joinPoint = Mock(JoinPoint)
		when:
		sanitizerAspect.sanitizeEventData(joinPoint, userData)

		then:
		userData.email == expectedResult

		where:
		sanitizeDataEnabled	|	emailAddress				|| expectedResult
		false				|	'jhon.doe@traffic.com'		|| 'jhon.doe@traffic.com'
		true				|	null						|| null
		true				|	'jhon.doe@gmail.com'		|| 'joininglist+jhondoegmailcom@traffic.com'
	}

	def "test sanitize List<UserData>" () {
		setup:
		sanitizerAspect.properties = new AopDbSanitizerConfigurationProperties(sanitizeData: sanitizeDataEnabled)
		List<UserData> dataFieldsList = [new UserData(email: emailAddress)]
		JoinPoint joinPoint = Mock(JoinPoint)
		when:
		sanitizerAspect.sanitizeEventData(joinPoint, dataFieldsList)

		then:
		dataFieldsList.get(0).email == expectedResult

		where:
		sanitizeDataEnabled	|	emailAddress				|| expectedResult
		false				|	'jhon.doe@traffic.com'		|| 'jhon.doe@traffic.com'
		true				|	null						|| null
		true				|	'jhon.doe@gmail.com'		|| 'joininglist+jhondoegmailcom@traffic.com'
	}
}
