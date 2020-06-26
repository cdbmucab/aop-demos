package cdbm.ucab.aopdemos.test.repository

import cdbm.ucab.aopdemos.data.UserData
import cdbm.ucab.aopdemos.repository.UserDataMapper
import org.junit.Assert
import org.junit.jupiter.api.Test
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase

/**
 * Ejemplo de pruebas con base de datos de prueba emulando BD real
 */
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
@MybatisTest
class UserDataMapperTest {

    // Setup
    @Autowired
    UserDataMapper userDataMapper

    @Test
    void testUserById() {
        // Ejecucion
        UserData userData = userDataMapper.getById(2L)

        // Verification
        Assert.assertEquals(userData.email, "test@test.com")
    }
}
