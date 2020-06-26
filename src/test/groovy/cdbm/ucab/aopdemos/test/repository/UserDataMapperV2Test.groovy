package cdbm.ucab.aopdemos.test.repository

import cdbm.ucab.aopdemos.data.UserData
import cdbm.ucab.aopdemos.repository.UserDataMapper
import org.junit.Assert
import org.junit.jupiter.api.Test
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.context.annotation.Import
import org.springframework.test.context.ActiveProfiles

/**
 * Ejemplo de pruebas con base de datos de prueba con Testcontainers
 */
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
@MybatisTest
@ActiveProfiles(value = ["test"])
@Import(TestContainerConfiguration.class)
class UserDataMapperV2Test {

    // Setup
    @Autowired
    UserDataMapper userDataMapper

    @Test
    void testUserById() {
        // Ejecucion
        UserData userData = userDataMapper.getById(2L)

        // Verification
        Assert.assertEquals(userData.email, "test@testcontainer.com")
    }
}
