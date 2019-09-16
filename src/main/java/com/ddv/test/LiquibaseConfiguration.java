package com.ddv.test;

import java.lang.reflect.Method;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.data.jpa.EntityManagerFactoryDependsOnPostProcessor;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseDataSource;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.orm.jpa.AbstractEntityManagerFactoryBean;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import liquibase.exception.LiquibaseException;
import liquibase.integration.spring.SpringLiquibase;
import org.springframework.util.ReflectionUtils;

@Configuration
@ConditionalOnClass(SpringLiquibase.class)
@ConditionalOnBean(DataSource.class)
@ConditionalOnProperty(prefix = "liquibase", name = "enabled", matchIfMissing = true)
//@AutoConfigureAfter({ DataSourceAutoConfiguration.class,        HibernateJpaAutoConfiguration.class })
public class LiquibaseConfiguration {

    @Configuration
    @EnableConfigurationProperties(LiquibaseProperties.class)
    @Import(LiquibaseJpaDependencyConfiguration.class)
    public static class LiquibaseAConfiguration {

        private final LiquibaseProperties properties;

        private final ResourceLoader resourceLoader;

        private final DataSource dataSource;

        private final DataSource liquibaseDataSource;

        public LiquibaseAConfiguration(LiquibaseProperties properties,
                ResourceLoader resourceLoader, ObjectProvider<DataSource> dataSource,
                @LiquibaseDataSource ObjectProvider<DataSource> liquibaseDataSource) {
            this.properties = properties;
            this.resourceLoader = resourceLoader;
            this.dataSource = dataSource.getIfUnique();
            this.liquibaseDataSource = liquibaseDataSource.getIfAvailable();
        }

        @PostConstruct
        public void checkChangelogExists() {
            if (this.properties.isCheckChangeLogLocation()) {
                Resource resource = this.resourceLoader
                        .getResource(this.properties.getChangeLog());
            }
        }

        @Bean
        public SpringLiquibase liquibase() {
            SpringLiquibase liquibase = createSpringLiquibase();
            liquibase.setChangeLog(this.properties.getChangeLog());
            liquibase.setContexts(this.properties.getContexts());
            liquibase.setDefaultSchema(this.properties.getDefaultSchema());
            liquibase.setDropFirst(this.properties.isDropFirst());
            liquibase.setShouldRun(this.properties.isEnabled());
            liquibase.setLabels(this.properties.getLabels());
            liquibase.setChangeLogParameters(this.properties.getParameters());
            liquibase.setRollbackFile(this.properties.getRollbackFile());
            return liquibase;
        }

        private SpringLiquibase createSpringLiquibase() {
            DataSource liquibaseDataSource = getDataSource();
            if (liquibaseDataSource != null) {
                SpringLiquibase liquibase = new SpringLiquibase2();
                liquibase.setDataSource(liquibaseDataSource);
                return liquibase;
            }
            SpringLiquibase liquibase = new DataSourceClosingSpringLiquibase();
            liquibase.setDataSource(createNewDataSource());
            return liquibase;
        }

        private DataSource getDataSource() {
            if (this.liquibaseDataSource != null) {
                return this.liquibaseDataSource;
            }
            if (this.properties.getUrl() == null) {
                return this.dataSource;
            }
            return null;
        }

        private DataSource createNewDataSource() {
            return DataSourceBuilder.create().url(this.properties.getUrl())
                    .username(this.properties.getUser())
                    .password(this.properties.getPassword()).build();
        }

    }

    /**
     * Additional configuration to ensure that {@link EntityManagerFactory} beans
     * depend-on the liquibase bean.
     */
    @Configuration
    @ConditionalOnClass(LocalContainerEntityManagerFactoryBean.class)
    @ConditionalOnBean(AbstractEntityManagerFactoryBean.class)
    protected static class LiquibaseJpaDependencyConfiguration
            extends EntityManagerFactoryDependsOnPostProcessor {

        public LiquibaseJpaDependencyConfiguration() {
            super("liquibase");
        }

    }

    /**
     * A custom {@link SpringLiquibase} extension that closes the underlying
     * {@link DataSource} once the database has been migrated.
     */
    private static final class DataSourceClosingSpringLiquibase extends SpringLiquibase {

        @Override
        public void afterPropertiesSet() throws LiquibaseException {
            super.afterPropertiesSet();
            closeDataSource();
        }

        private void closeDataSource() {
            Class<?> dataSourceClass = getDataSource().getClass();
            Method closeMethod = ReflectionUtils.findMethod(dataSourceClass, "close");
            if (closeMethod != null) {
                ReflectionUtils.invokeMethod(closeMethod, getDataSource());
            }
        }

    }

}
