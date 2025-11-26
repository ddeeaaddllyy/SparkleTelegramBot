package com.spark11e.bot.config

import jakarta.persistence.EntityManagerFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.jdbc.datasource.DriverManagerDataSource
import org.springframework.orm.jpa.JpaTransactionManager
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter
import javax.sql.DataSource

@Configuration
open class SqliteJpaConfig(
    private val dbProperties: DatabaseProperties
) {


    @Bean
    open fun dataSource(): DataSource {
        val dataSource = DriverManagerDataSource()
        dataSource.setDriverClassName(dbProperties.datasource.driverClassName)
        dataSource.url = dbProperties.datasource.url
        return dataSource
    }

    @Bean
    open fun entityManagerFactory(dataSource: DataSource): LocalContainerEntityManagerFactoryBean {
        val factory = LocalContainerEntityManagerFactoryBean()
        factory.dataSource = dataSource
        factory.jpaVendorAdapter = HibernateJpaVendorAdapter()

        factory.setPackagesToScan("com.spark11e.bot.model")

        val jpaProperties = HashMap<String, Any>()
        jpaProperties["hibernate.dialect"] = dbProperties.jpa.databasePlatform
        jpaProperties["hibernate.hbm2ddl.auto"] = dbProperties.jpa.hibernate.ddlAuto
        jpaProperties["hibernate.show_sql"] = "false"

        factory.setJpaPropertyMap(jpaProperties)
        factory.afterPropertiesSet()
        return factory
    }

    @Bean
    open fun transactionManager(entityManagerFactory: EntityManagerFactory): JpaTransactionManager {
        val transactionManager = JpaTransactionManager()
        transactionManager.entityManagerFactory = entityManagerFactory
        return transactionManager
    }
}