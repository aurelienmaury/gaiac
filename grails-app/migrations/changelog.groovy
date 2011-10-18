databaseChangeLog = {

	changeSet(author: "amaury (generated)", id: "1318950147185-1") {
		createTable(tableName: "gaiac_file") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "gaiac_filePK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "name", type: "varchar(255)") {
				constraints(nullable: "false", unique: "true")
			}

			column(name: "path", type: "varchar(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "amaury (generated)", id: "1318950147185-2") {
		createTable(tableName: "member") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "memberPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "account_expired", type: "boolean") {
				constraints(nullable: "false")
			}

			column(name: "account_locked", type: "boolean") {
				constraints(nullable: "false")
			}

			column(name: "date_created", type: "timestamp") {
				constraints(nullable: "false")
			}

			column(name: "email", type: "varchar(255)") {
				constraints(nullable: "false", unique: "true")
			}

			column(name: "enabled", type: "boolean") {
				constraints(nullable: "false")
			}

			column(name: "last_updated", type: "timestamp") {
				constraints(nullable: "false")
			}

			column(name: "password", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "password_expired", type: "boolean") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "amaury (generated)", id: "1318950147185-3") {
		createTable(tableName: "member_role") {
			column(name: "role_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "member_id", type: "bigint") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "amaury (generated)", id: "1318950147185-4") {
		createTable(tableName: "role") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "rolePK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "authority", type: "varchar(255)") {
				constraints(nullable: "false", unique: "true")
			}
		}
	}

	changeSet(author: "amaury (generated)", id: "1318950147185-5") {
		addPrimaryKey(columnNames: "role_id, member_id", constraintName: "member_rolePK", tableName: "member_role")
	}

	changeSet(author: "amaury (generated)", id: "1318950147185-6") {
		createIndex(indexName: "name_unique_1318950147105", tableName: "gaiac_file", unique: "true") {
			column(name: "name")
		}
	}

	changeSet(author: "amaury (generated)", id: "1318950147185-7") {
		createIndex(indexName: "email_unique_1318950147115", tableName: "member", unique: "true") {
			column(name: "email")
		}
	}

	changeSet(author: "amaury (generated)", id: "1318950147185-8") {
		createIndex(indexName: "authority_unique_1318950147130", tableName: "role", unique: "true") {
			column(name: "authority")
		}
	}

	changeSet(author: "amaury (generated)", id: "1318950147185-9") {
		addForeignKeyConstraint(baseColumnNames: "member_id", baseTableName: "member_role", constraintName: "FK527E3EFB25EA19FD", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "member", referencesUniqueColumn: "false")
	}

	changeSet(author: "amaury (generated)", id: "1318950147185-10") {
		addForeignKeyConstraint(baseColumnNames: "role_id", baseTableName: "member_role", constraintName: "FK527E3EFBFEEBC13D", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "role", referencesUniqueColumn: "false")
	}
}
