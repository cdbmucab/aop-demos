package cdbm.ucab.aopdemos.data

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.databind.PropertyNamingStrategy
import com.fasterxml.jackson.databind.annotation.JsonNaming
import groovy.transform.CompileStatic
import groovy.transform.ToString

@CompileStatic
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@ToString(includeNames = true, excludes = 'email, firstName, lastName')
class UserData {
	Long id
	String email
	String firstName
	String lastName
	String language
	Boolean allowEmailMarketing = false
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss ZZ")
	Date dateCreated
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss ZZ")
	Date lastUpdated
}
