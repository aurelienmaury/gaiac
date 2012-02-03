databaseChangeLog = {

	changeSet(author: "amaury (generated)", id: "1328119278654-1") {
		createTable(tableName: "download_trace") {
			column(autoIncrement: "true", name: "id", type: "BIGINT") {
				constraints(nullable: "false", primaryKey: "true")
			}

			column(name: "version", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "download_date", type: "DATETIME") {
				constraints(nullable: "false")
			}

			column(name: "file_id", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "member_id", type: "BIGINT") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "amaury (generated)", id: "1328119278654-2") {
		createTable(tableName: "gaiac_file") {
			column(autoIncrement: "true", name: "id", type: "BIGINT") {
				constraints(nullable: "false", primaryKey: "true")
			}

			column(name: "version", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "name", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "path", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "date_created", type: "DATETIME") {
				constraints(nullable: "false")
			}

			column(name: "last_updated", type: "DATETIME") {
				constraints(nullable: "false")
			}

			column(name: "size", type: "BIGINT") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "amaury (generated)", id: "1328119278654-3") {
		createTable(tableName: "member") {
			column(autoIncrement: "true", name: "id", type: "BIGINT") {
				constraints(nullable: "false", primaryKey: "true")
			}

			column(name: "version", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "account_expired", type: "BIT") {
				constraints(nullable: "false")
			}

			column(name: "account_locked", type: "BIT") {
				constraints(nullable: "false")
			}

			column(defaultValueComputed: "CURRENT_TIMESTAMP", name: "date_created", type: "TIMESTAMP") {
				constraints(nullable: "false")
			}

			column(name: "email", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "enabled", type: "BIT") {
				constraints(nullable: "false")
			}

			column(defaultValue: "0000-00-00 00:00:00", name: "last_updated", type: "TIMESTAMP") {
				constraints(nullable: "false")
			}

			column(name: "password", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}

			column(name: "password_expired", type: "BIT") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "amaury (generated)", id: "1328119278654-4") {
		createTable(tableName: "member_role") {
			column(name: "role_id", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "member_id", type: "BIGINT") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "amaury (generated)", id: "1328119278654-5") {
		createTable(tableName: "role") {
			column(autoIncrement: "true", name: "id", type: "BIGINT") {
				constraints(nullable: "false", primaryKey: "true")
			}

			column(name: "version", type: "BIGINT") {
				constraints(nullable: "false")
			}

			column(name: "authority", type: "VARCHAR(255)") {
				constraints(nullable: "false")
			}
		}
	}

	changeSet(author: "amaury (generated)", id: "1328119278654-6") {
		addPrimaryKey(columnNames: "role_id, member_id", tableName: "member_role")
	}

	changeSet(author: "amaury (generated)", id: "1328119278654-7") {
		createIndex(indexName: "FK3AE896CE25EA19FD", tableName: "download_trace", unique: "false") {
			column(name: "member_id")
		}
	}

	changeSet(author: "amaury (generated)", id: "1328119278654-8") {
		createIndex(indexName: "FK3AE896CE7502DA8", tableName: "download_trace", unique: "false") {
			column(name: "file_id")
		}
	}

	changeSet(author: "amaury (generated)", id: "1328119278654-9") {
		createIndex(indexName: "name_unique_1319547333565", tableName: "gaiac_file", unique: "true") {
			column(name: "name")
		}
	}

	changeSet(author: "amaury (generated)", id: "1328119278654-10") {
		createIndex(indexName: "email", tableName: "member", unique: "true") {
			column(name: "email")
		}
	}

	changeSet(author: "amaury (generated)", id: "1328119278654-11") {
		createIndex(indexName: "FK527E3EFB25EA19FD", tableName: "member_role", unique: "false") {
			column(name: "member_id")
		}
	}

	changeSet(author: "amaury (generated)", id: "1328119278654-12") {
		createIndex(indexName: "FK527E3EFBFEEBC13D", tableName: "member_role", unique: "false") {
			column(name: "role_id")
		}
	}

	changeSet(author: "amaury (generated)", id: "1328119278654-13") {
		createIndex(indexName: "authority_unique_1319547333590", tableName: "role", unique: "true") {
			column(name: "authority")
		}
	}
}
