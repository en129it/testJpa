package com.ddv.test;

import liquibase.Contexts;
import liquibase.LabelExpression;
import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.exception.LiquibaseException;
import liquibase.lockservice.LockService;
import liquibase.lockservice.LockServiceFactory;
import liquibase.lockservice.StandardLockService;
import liquibase.resource.ResourceAccessor;

public class Liquibase2 extends Liquibase {

    public Liquibase2(String changeLogFile, ResourceAccessor resourceAccessor, Database database) throws LiquibaseException {
        super(changeLogFile, resourceAccessor, database);
    }

 
    @Override
    public void update(Contexts contexts, LabelExpression labelExpression, boolean checkLiquibaseTables) throws LiquibaseException {
/*        
        StandardLockService service = new StandardLockService();
        service.setDatabase(database);
        service.init();
*/
        StandardLockService2 service2 = new StandardLockService2();
        service2.setDatabase(database);
        
        LockServiceFactory.getInstance().register(service2);
        
        super.update(contexts, labelExpression, checkLiquibaseTables);
    }
    
}
