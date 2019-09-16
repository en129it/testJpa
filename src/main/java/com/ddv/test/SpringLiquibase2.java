package com.ddv.test;

import java.sql.Connection;

import liquibase.CatalogAndSchema;
import liquibase.database.Database;
import liquibase.exception.DatabaseException;
import liquibase.integration.spring.SpringLiquibase;
import liquibase.resource.ResourceAccessor;

public class SpringLiquibase2 extends SpringLiquibase {

    @Override
    protected Database createDatabase(Connection c, ResourceAccessor resourceAccessor) throws DatabaseException {
        Database db = super.createDatabase(c, resourceAccessor);
        debug(db);
        return db;
    }
    
    private void debug(Database db) {
        System.out.println("Get auto commit mode : " + db.getAutoCommitMode());
        System.out.println("Get database product name : " + db.getDatabaseProductName());

        try {
        System.out.println("Get database product version : " + db.getDatabaseProductVersion());
        System.out.println("Get database major version : " + db.getDatabaseMajorVersion());
        System.out.println("Get database minor version : " + db.getDatabaseMinorVersion());
        } catch (Exception ex) {}
        
        System.out.println("Get short name : " + db.getShortName());
        System.out.println("Get default catalog name : " + db.getDefaultCatalogName());
        System.out.println("Get default schema name : " + db.getDefaultSchemaName());
        System.out.println("Get default port : " + db.getDefaultPort());
        System.out.println("Get liquibase catalog name : " + db.getLiquibaseCatalogName());
        System.out.println("Get liquibase schema name : " + db.getLiquibaseSchemaName());
        System.out.println("Get database change log table name : " + db.getDatabaseChangeLogTableName());
        System.out.println("Get database change lock table name : " + db.getDatabaseChangeLogLockTableName());
        System.out.println("Get liquibase tablespace name : " + db.getLiquibaseTablespaceName());
        System.out.println("Support tablespaces ? " + db.supportsTablespaces());
        System.out.println("Supports catalogs ? " + db.supportsCatalogs());
        System.out.println("Supports schema ? " + db.supportsSchemas());

        try {
        System.out.println("Is auto commit ? " + db.isAutoCommit());
        System.out.println("Is safe to run update ? " + db.isSafeToRunUpdate());
        } catch (Exception ex) {}

        System.out.println("Supports foreign key disable ? " + db.supportsForeignKeyDisable());
        System.out.println("IS case sensitive ? " + db.isCaseSensitive());
        
        CatalogAndSchema defaultSchema = db.getDefaultSchema();
        System.out.println("Default schema : catalog name : " + defaultSchema.getCatalogName());
        System.out.println("Default schema : schema name : " + defaultSchema.getSchemaName());
        
        System.out.println("Get output default schema : " + db.getOutputDefaultSchema());
        System.out.println("Get output default catalog : " + db.getOutputDefaultCatalog());
        System.out.println("Support primary key names ? " + db.supportsPrimaryKeyNames());
        System.out.println("Get system schema : " + db.getSystemSchema());
    }
    
}
