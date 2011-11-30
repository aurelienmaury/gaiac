databaseChangeLog = {

	changeSet(author: "amaury (generated)", id: "1322584278279-1") {
		createTable(tableName: "category") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "categoryPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "description", type: "varchar(1024)") {
				constraints(nullable: "false")
			}

			column(name: "name", type: "varchar(255)") {
				constraints(nullable: "false", unique: "true")
			}
		}
	}

	changeSet(author: "amaury (generated)", id: "1322584278279-2") {
		createTable(tableName: "download_trace") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "download_tracPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "date_created", type: "timestamp") {
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

	changeSet(author: "amaury (generated)", id: "1322584278279-3") {
		createTable(tableName: "gaiac_file") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "gaiac_filePK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "date_created", type: "timestamp") {
				constraints(nullable: "false")
			}

			column(name: "download_number", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "last_updated", type: "timestamp") {
				constraints(nullable: "false")
			}

			column(name: "name", type: "varchar(255)") {
				constraints(nullable: "false", unique: "true")
			}

			column(name: "path", type: "varchar(1024)") {
				constraints(nullable: "false")
			}

			column(name: "size", type: "bigint") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "amaury (generated)", id: "1322584278279-4") {
		createTable(tableName: "gaiac_file_category") {
			column(name: "gaiac_file_categories_id", type: "bigint")

			column(name: "category_id", type: "bigint")
		}
	}

	changeSet(author: "amaury (generated)", id: "1322584278279-5") {
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

			column(name: "email", type: "varchar(100)") {
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

	changeSet(author: "amaury (generated)", id: "1322584278279-6") {
		createTable(tableName: "member_role") {
			column(name: "role_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "member_id", type: "bigint") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "amaury (generated)", id: "1322584278279-7") {
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

	changeSet(author: "amaury (generated)", id: "1322584278279-8") {
		addPrimaryKey(columnNames: "role_id, member_id", constraintName: "member_rolePK", tableName: "member_role")
	}

	changeSet(author: "amaury (generated)", id: "1322584278279-9") {
		createIndex(indexName: "name_unique_1322584278186", tableName: "category", unique: "true") {
			column(name: "name")
		}
	}

	changeSet(author: "amaury (generated)", id: "1322584278279-10") {
		createIndex(indexName: "name_unique_1322584278204", tableName: "gaiac_file", unique: "true") {
			column(name: "name")
		}
	}

	changeSet(author: "amaury (generated)", id: "1322584278279-11") {
		createIndex(indexName: "email_unique_1322584278217", tableName: "member", unique: "true") {
			column(name: "email")
		}
	}

	changeSet(author: "amaury (generated)", id: "1322584278279-12") {
		createIndex(indexName: "authority_unique_1322584278221", tableName: "role", unique: "true") {
			column(name: "authority")
		}
	}

	changeSet(author: "amaury (generated)", id: "1322584278279-13") {
		addForeignKeyConstraint(baseColumnNames: "file_id", baseTableName: "download_trace", constraintName: "FK3AE896CE7502DA8", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "gaiac_file", referencesUniqueColumn: "false")
	}

	changeSet(author: "amaury (generated)", id: "1322584278279-14") {
		addForeignKeyConstraint(baseColumnNames: "member_id", baseTableName: "download_trace", constraintName: "FK3AE896CE25EA19FD", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "member", referencesUniqueColumn: "false")
	}

	changeSet(author: "amaury (generated)", id: "1322584278279-15") {
		addForeignKeyConstraint(baseColumnNames: "category_id", baseTableName: "gaiac_file_category", constraintName: "FKC3DB6FD386B2BABD", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "category", referencesUniqueColumn: "false")
	}

	changeSet(author: "amaury (generated)", id: "1322584278279-16") {
		addForeignKeyConstraint(baseColumnNames: "gaiac_file_categories_id", baseTableName: "gaiac_file_category", constraintName: "FKC3DB6FD363BE6353", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "gaiac_file", referencesUniqueColumn: "false")
	}

	changeSet(author: "amaury (generated)", id: "1322584278279-17") {
		addForeignKeyConstraint(baseColumnNames: "member_id", baseTableName: "member_role", constraintName: "FK527E3EFB25EA19FD", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "member", referencesUniqueColumn: "false")
	}

	changeSet(author: "amaury (generated)", id: "1322584278279-18") {
		addForeignKeyConstraint(baseColumnNames: "role_id", baseTableName: "member_role", constraintName: "FK527E3EFBFEEBC13D", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "role", referencesUniqueColumn: "false")
	}
}
