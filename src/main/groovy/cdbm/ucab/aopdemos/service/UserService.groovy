package cdbm.ucab.aopdemos.service

import cdbm.ucab.aopdbsanitizer.config.AopDbSanitizerConfigurationProperties
import cdbm.ucab.aopdbsanitizer.data.UserData
import cdbm.ucab.aopdbsanitizer.repository.UserDataMapper
import groovy.transform.CompileStatic
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
@CompileStatic
class UserService {

	private Logger log = LoggerFactory.getLogger(UserService.class)

	@Autowired
	UserDataMapper userDataMapper

	@Autowired
	AopDbSanitizerConfigurationProperties iterableConfigurationProperties

	List<UserData> pushUsersByDateCreatedOrUpdated(Long numHoursAgoSinceUpdated) {
		List<UserData> users = userDataMapper.getUpToDateUserData(numHoursAgoSinceUpdated)
		log.info("Fetching [${users.size()}] new and updated users numHoursAgoSinceUpdated=${numHoursAgoSinceUpdated}")
		return users
	}

	UserData pushUserById(Long userId) {
		UserData userData = userDataMapper.getById(userId)
		log.info("Fetched [${userData}]")
		return userData
	}
}
