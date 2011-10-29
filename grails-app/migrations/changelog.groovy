databaseChangeLog = {

	changeSet(author: "amaury (generated)", id: "1319547333648-1") {
		createTable(tableName: "gaiac_file") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "gaiac_filePK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "downloaded", type: "integer") {
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

	changeSet(author: "amaury (generated)", id: "1319547333648-2") {
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

	changeSet(author: "amaury (generated)", id: "1319547333648-3") {
		createTable(tableName: "member_role") {
			column(name: "role_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "member_id", type: "bigint") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "amaury (generated)", id: "1319547333648-4") {
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

	changeSet(author: "amaury (generated)", id: "1319547333648-5") {
		addPrimaryKey(columnNames: "role_id, member_id", constraintName: "member_rolePK", tableName: "member_role")
	}

	changeSet(author: "amaury (generated)", id: "1319547333648-6") {
		createIndex(indexName: "name_unique_1319547333565", tableName: "gaiac_file", unique: "true") {
			column(name: "name")
		}
	}

	changeSet(author: "amaury (generated)", id: "1319547333648-7") {
		createIndex(indexName: "email_unique_1319547333576", tableName: "member", unique: "true") {
			column(name: "email")
		}
	}

	changeSet(author: "amaury (generated)", id: "1319547333648-8") {
		createIndex(indexName: "authority_unique_1319547333590", tableName: "role", unique: "true") {
			column(name: "authority")
		}
	}

	changeSet(author: "amaury (generated)", id: "1319547333648-9") {
		addForeignKeyConstraint(baseColumnNames: "member_id", baseTableName: "member_role", constraintName: "FK527E3EFB25EA19FD", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "member", referencesUniqueColumn: "false")
	}

	changeSet(author: "amaury (generated)", id: "1319547333648-10") {
		addForeignKeyConstraint(baseColumnNames: "role_id", baseTableName: "member_role", constraintName: "FK527E3EFBFEEBC13D", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "role", referencesUniqueColumn: "false")
	}

	changeSet(author: "amaury (generated)", id: "1319818367543-1") {
		createTable(tableName: "download_trace") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "download_tracPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "download_date", type: "datetime") {
				constraints(nullable: "false")
			}

			column(name: "file_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "member_id", type: "bigint") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "amaury (generated)", id: "1319818367543-2") {
		addColumn(tableName: "gaiac_file") {
			column(name: "date_created", type: "datetime") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "amaury (generated)", id: "1319818367543-3") {
		addColumn(tableName: "gaiac_file") {
			column(name: "last_updated", type: "datetime") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "amaury (generated)", id: "1319818367543-4") {
		addColumn(tableName: "gaiac_file") {
			column(name: "size", type: "bigint") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "amaury (generated)", id: "1319818367543-5") {
		createIndex(indexName: "FK3AE896CE25EA19FD", tableName: "download_trace") {
			column(name: "member_id")
		}
	}

	changeSet(author: "amaury (generated)", id: "1319818367543-6") {
		createIndex(indexName: "FK3AE896CE7502DA8", tableName: "download_trace") {
			column(name: "file_id")
		}
	}

	changeSet(author: "amaury (generated)", id: "1319818367543-7") {
		createIndex(indexName: "FK527E3EFBFEEBC13D", tableName: "member_role") {
			column(name: "role_id")
		}
	}

	changeSet(author: "amaury (generated)", id: "1319818367543-8") {
		addForeignKeyConstraint(baseColumnNames: "file_id", baseTableName: "download_trace", constraintName: "FK3AE896CE7502DA8", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "gaiac_file", referencesUniqueColumn: "false")
	}

	changeSet(author: "amaury (generated)", id: "1319818367543-9") {
		addForeignKeyConstraint(baseColumnNames: "member_id", baseTableName: "download_trace", constraintName: "FK3AE896CE25EA19FD", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "member", referencesUniqueColumn: "false")
	}

	changeSet(author: "amaury (generated)", id: "1319818367543-10") {
		addForeignKeyConstraint(baseColumnNames: "member_id", baseTableName: "member_role", constraintName: "FK527E3EFB25EA19FD", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "member", referencesUniqueColumn: "false")
	}

	changeSet(author: "amaury (generated)", id: "1319818367543-11") {
		addForeignKeyConstraint(baseColumnNames: "role_id", baseTableName: "member_role", constraintName: "FK527E3EFBFEEBC13D", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "role", referencesUniqueColumn: "false")
	}

	changeSet(author: "amaury (generated)", id: "1319818367543-12") {
		dropColumn(columnName: "downloaded", tableName: "gaiac_file")
	}
}
