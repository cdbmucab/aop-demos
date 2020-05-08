package cdbm.ucab.aopdemos.repository

import cdbm.ucab.aopdbsanitizer.data.UserData
import groovy.transform.CompileStatic
import org.apache.ibatis.annotations.Mapper

@CompileStatic
@Mapper
interface UserDataMapper {
	UserData getById(long userId)
	List<UserData> getUpToDateUserData(Long numHoursAgoSinceUpdated)
}