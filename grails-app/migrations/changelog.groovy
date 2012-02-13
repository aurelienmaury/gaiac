databaseChangeLog = {

	changeSet(author: "amaury (generated)", id: "1329097229080-1") {
		createTable(tableName: "category") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "categoryPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "description", type: "longtext")

			column(name: "name", type: "varchar(255)") {
				constraints(nullable: "false", unique: "true")
			}
		}
	}

	changeSet(author: "amaury (generated)", id: "1329097229080-2") {
		createTable(tableName: "category_gaiac_files") {
			column(name: "category_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "gaiac_file_id", type: "bigint") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "amaury (generated)", id: "1329097229080-3") {
		createTable(tableName: "download_trace") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "download_tracPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "date_created", type: "datetime") {
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

	changeSet(author: "amaury (generated)", id: "1329097229080-4") {
		createTable(tableName: "gaiac_file") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "gaiac_filePK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "date_created", type: "datetime") {
				constraints(nullable: "false")
			}

			column(name: "download_number", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "exturl", type: "longtext")

			column(name: "last_updated", type: "datetime") {
				constraints(nullable: "false")
			}

			column(name: "name", type: "varchar(255)") {
				constraints(nullable: "false", unique: "true")
			}

			column(name: "path", type: "longtext") {
				constraints(nullable: "false")
			}

			column(name: "size", type: "bigint") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "amaury (generated)", id: "1329097229080-5") {
		createTable(tableName: "member") {
			column(autoIncrement: "true", name: "id", type: "bigint") {
				constraints(nullable: "false", primaryKey: "true", primaryKeyName: "memberPK")
			}

			column(name: "version", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "account_expired", type: "bit") {
				constraints(nullable: "false")
			}

			column(name: "account_locked", type: "bit") {
				constraints(nullable: "false")
			}

			column(name: "date_created", type: "datetime") {
				constraints(nullable: "false")
			}

			column(name: "email", type: "varchar(100)") {
				constraints(nullable: "false", unique: "true")
			}

			column(name: "enabled", type: "bit") {
				constraints(nullable: "false")
			}

			column(name: "last_updated", type: "datetime") {
				constraints(nullable: "false")
			}

			column(name: "password", type: "varchar(255)") {
				constraints(nullable: "false")
			}

			column(name: "password_expired", type: "bit") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "amaury (generated)", id: "1329097229080-6") {
		createTable(tableName: "member_role") {
			column(name: "role_id", type: "bigint") {
				constraints(nullable: "false")
			}

			column(name: "member_id", type: "bigint") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "amaury (generated)", id: "1329097229080-7") {
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

	changeSet(author: "amaury (generated)", id: "1329097229080-8") {
		addPrimaryKey(columnNames: "category_id, gaiac_file_id", tableName: "category_gaiac_files")
	}

	changeSet(author: "amaury (generated)", id: "1329097229080-9") {
		addPrimaryKey(columnNames: "role_id, member_id", constraintName: "member_rolePK", tableName: "member_role")
	}

	changeSet(author: "amaury (generated)", id: "1329097229080-10") {
		createIndex(indexName: "name_unique_1329097228951", tableName: "category", unique: "true") {
			column(name: "name")
		}
	}

	changeSet(author: "amaury (generated)", id: "1329097229080-11") {
		createIndex(indexName: "FKAE4E6286CFF369A", tableName: "category_gaiac_files") {
			column(name: "gaiac_file_id")
		}
	}

	changeSet(author: "amaury (generated)", id: "1329097229080-12") {
		createIndex(indexName: "FKAE4E62886B2BABD", tableName: "category_gaiac_files") {
			column(name: "category_id")
		}
	}

	changeSet(author: "amaury (generated)", id: "1329097229080-13") {
		createIndex(indexName: "FK3AE896CE25EA19FD", tableName: "download_trace") {
			column(name: "member_id")
		}
	}

	changeSet(author: "amaury (generated)", id: "1329097229080-14") {
		createIndex(indexName: "FK3AE896CE7502DA8", tableName: "download_trace") {
			column(name: "file_id")
		}
	}

	changeSet(author: "amaury (generated)", id: "1329097229080-15") {
		createIndex(indexName: "name_unique_1329097228987", tableName: "gaiac_file", unique: "true") {
			column(name: "name")
		}
	}

	changeSet(author: "amaury (generated)", id: "1329097229080-16") {
		createIndex(indexName: "email_unique_1329097228994", tableName: "member", unique: "true") {
			column(name: "email")
		}
	}

	changeSet(author: "amaury (generated)", id: "1329097229080-17") {
		createIndex(indexName: "FK527E3EFB25EA19FD", tableName: "member_role") {
			column(name: "member_id")
		}
	}

	changeSet(author: "amaury (generated)", id: "1329097229080-18") {
		createIndex(indexName: "FK527E3EFBFEEBC13D", tableName: "member_role") {
			column(name: "role_id")
		}
	}

	changeSet(author: "amaury (generated)", id: "1329097229080-19") {
		createIndex(indexName: "authority_unique_1329097229001", tableName: "role", unique: "true") {
			column(name: "authority")
		}
	}

	changeSet(author: "amaury (generated)", id: "1329097229080-20") {
		addForeignKeyConstraint(baseColumnNames: "category_id", baseTableName: "category_gaiac_files", constraintName: "FKAE4E62886B2BABD", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "category", referencesUniqueColumn: "false")
	}

	changeSet(author: "amaury (generated)", id: "1329097229080-21") {
		addForeignKeyConstraint(baseColumnNames: "gaiac_file_id", baseTableName: "category_gaiac_files", constraintName: "FKAE4E6286CFF369A", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "gaiac_file", referencesUniqueColumn: "false")
	}

	changeSet(author: "amaury (generated)", id: "1329097229080-22") {
		addForeignKeyConstraint(baseColumnNames: "file_id", baseTableName: "download_trace", constraintName: "FK3AE896CE7502DA8", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "gaiac_file", referencesUniqueColumn: "false")
	}

	changeSet(author: "amaury (generated)", id: "1329097229080-23") {
		addForeignKeyConstraint(baseColumnNames: "member_id", baseTableName: "download_trace", constraintName: "FK3AE896CE25EA19FD", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "member", referencesUniqueColumn: "false")
	}

	changeSet(author: "amaury (generated)", id: "1329097229080-24") {
		addForeignKeyConstraint(baseColumnNames: "member_id", baseTableName: "member_role", constraintName: "FK527E3EFB25EA19FD", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "member", referencesUniqueColumn: "false")
	}

	changeSet(author: "amaury (generated)", id: "1329097229080-25") {
		addForeignKeyConstraint(baseColumnNames: "role_id", baseTableName: "member_role", constraintName: "FK527E3EFBFEEBC13D", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "role", referencesUniqueColumn: "false")
	}
}
