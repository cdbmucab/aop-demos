package cdbm.ucab.aopdemos.test.repository

import org.apache.ibatis.datasource.unpooled.UnpooledDataSource
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.testcontainers.containers.PostgreSQLContainer

import javax.sql.DataSource

@TestConfiguration
class TestContainerConfiguration {

    private PostgreSQLContainer postgreSQLContainer = AopDemoPostgresqlContainer.getInstance()

    private int insertData(UnpooledDataSource dataSource) {
        dataSource.getConnection().createStatement().executeUpdate(
                "INSERT INTO public.\"user\" (id, email, first_name, last_name, allow_email_marketing, date_created, last_updated) " +
                        "VALUES (2, 'test@testcontainer.com', 'Carlos', 'Daniel', true, '2020-06-26 02:44:44.000000', '2020-06-26 02:44:45.000000');"
        )
    }

    private int createSchema(UnpooledDataSource dataSource) {
        dataSource.getConnection().createStatement().executeUpdate(
                "create table \"user\"(" +
                        "id integer," +
                        "email varchar," +
                        "first_name varchar," +
                        "last_name varchar," +
                        "allow_email_marketing boolean," +
                        "date_created timestamp," +
                        "last_updated timestamp" +
                        ");"
        )
    }

    @Bean
    DataSource dataSource() {
        postgreSQLContainer.start()

        UnpooledDataSource dataSource = new UnpooledDataSource(
                AopDemoPostgresqlContainer.DRIVER,
                postgreSQLContainer.getJdbcUrl(),
                AopDemoPostgresqlContainer.USERNAME,
                AopDemoPostgresqlContainer.PASSWORD)

        createSchema(dataSource)

        insertData(dataSource)

        return dataSource
    }
}
