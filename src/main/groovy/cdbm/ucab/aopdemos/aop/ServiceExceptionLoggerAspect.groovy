package cdbm.ucab.aopdemos.aop

import groovy.transform.CompileStatic
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.jdbc.BadSqlGrammarException
import org.springframework.stereotype.Component

@Aspect
@CompileStatic
@Component
class ServiceExceptionLoggerAspect {

	private Logger log = LoggerFactory.getLogger(ServiceExceptionLoggerAspect.class)

	@Around(value = "execution(* cdbm.ucab.aopdemos.service..*.*(..))")
	Object logApiErrors(ProceedingJoinPoint joinPoint) {
		String methodName = joinPoint.getSignature().getName()
		String className = joinPoint.getTarget().getClass().getSimpleName()
		log.info("Before execute=${methodName} from service=${className}")

		try {
			return joinPoint.proceed()
		} catch (BadSqlGrammarException badSqlGrammarException) {
			log.error("A SQL error was caught after executing=${methodName} from service=${className}", badSqlGrammarException)
			throw new IllegalStateException("Something happened while running the query!")
		} catch (Throwable throwable) {
			log.error("Something unexpected happened when executing=${methodName} from service=${className}", throwable)
			throw new IllegalStateException("Something really rally bad happened!")
		}
	}
}
