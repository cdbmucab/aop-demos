package cdbm.ucab.aopdemos.test.repository

import org.testcontainers.containers.PostgreSQLContainer

class AopDemoPostgresqlContainer extends PostgreSQLContainer<AopDemoPostgresqlContainer> {

    public static final String IMAGE_VERSION = "postgres:11.1"
    public static final String DB_NAME = "test_container";
    public static final String USERNAME = "sa";
    public static final String PASSWORD = "sa";
    public static final String DRIVER = "org.postgresql.Driver";
    public static AopDemoPostgresqlContainer container

    private AopDemoPostgresqlContainer() {
        super(IMAGE_VERSION)
    }

    static AopDemoPostgresqlContainer getInstance() {
        if (container == null) {
            container = new AopDemoPostgresqlContainer()
            container.withDatabaseName(DB_NAME)
            container.withUsername(USERNAME)
            container.withPassword(PASSWORD)
        }
        return container
    }

    @Override
    void start() {
        super.start()
    }

    @Override
    void stop() {
    }

    @Override
    void close() {
        super.close()
    }
}
