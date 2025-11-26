package com.spark11e.bot.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

@Component
@ConfigurationProperties(prefix = "spring")
data class DatabaseProperties(
    val datasource: DataSource = DataSource(),
    val jpa: Jpa = Jpa()
) {

    data class DataSource(
        val url: String = "jdbc:sqlite:data/bot_database.sqlite3",
        val driverClassName: String = "org.sqlite.JDBC"
    )

    data class Jpa(
        val hibernate: Hibernate = Hibernate(),
        val databasePlatform: String = "org.hibernate.community.dialect.SQLiteDialect"
    )

    data class Hibernate(
        val ddlAuto: String = "update"
    )
}