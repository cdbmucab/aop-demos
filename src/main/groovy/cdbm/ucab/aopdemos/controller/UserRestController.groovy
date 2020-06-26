package cdbm.ucab.aopdemos.controller

import cdbm.ucab.aopdemos.data.UserData
import cdbm.ucab.aopdemos.service.UserService
import groovy.transform.CompileStatic
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@CompileStatic
@RestController
@RequestMapping(value = '/users', produces = 'application/json')
class UserRestController {

	private static final Logger logger = LoggerFactory.getLogger(UserRestController.class)

	@Autowired
	UserService userService

	@GetMapping("/new-and-updated")
	@ResponseBody
	ResponseEntity pushNewUpdatedUsers(
			@RequestParam (name = "numberHoursAgo", defaultValue = "1", required = false) Long numberHoursAgo) {

		logger.info("Processing pushNewUpdatedUsers numberHoursAgo=$numberHoursAgo")

		try {
			List<UserData> userDataList = userService.pushUsersByDateCreatedOrUpdated(numberHoursAgo)
			return ResponseEntity.ok(userDataList)
		} catch (Throwable throwable) {
			return ResponseEntity.badRequest().body(["message": throwable.message])
		}
	}

	@GetMapping("/{id}")
	@ResponseBody
	ResponseEntity pushUserById(@PathVariable (name = "id") Long id) {

		logger.info("Processing pushUserById id=$id")
		try {
			UserData userData= userService.pushUserById(id)
			return ResponseEntity.ok(userData)
		} catch (Throwable throwable) {
			return ResponseEntity.badRequest().body(["message": throwable.message])
		}
	}
}
