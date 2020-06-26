package cdbm.ucab.aopdemos.service

import cdbm.ucab.aopdemos.config.AopDbSanitizerConfigurationProperties
import cdbm.ucab.aopdemos.data.UserData
import cdbm.ucab.aopdemos.repository.UserDataMapper
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
		if (!userId || userId < 0) {
			throw new IllegalArgumentException("User id must be a valid value")
		}

		UserData userData = userDataMapper.getById(userId)
		log.info("Fetched [${userData}]")
		return userData
	}
}
