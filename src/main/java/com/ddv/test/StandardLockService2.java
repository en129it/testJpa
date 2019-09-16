package com.ddv.test;

import liquibase.exception.DatabaseException;
import liquibase.exception.LockException;
import liquibase.lockservice.StandardLockService;

public class StandardLockService2 extends StandardLockService {

    public StandardLockService2() {
    }
    
    @Override
    public int getPriority() {
        return super.getPriority() + 1;
    }
    
    @Override
    public void waitForLock() throws LockException {
        super.waitForLock();
    }
 
    @Override
    public void init() throws DatabaseException {
        super.init();
    }
}
