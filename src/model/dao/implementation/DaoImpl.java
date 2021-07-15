package model.dao.implementation;

import model.dao.MysqlFactory;

public class DaoImpl {

	protected MysqlFactory mysqlFactory;
	
	public DaoImpl() {
		this.mysqlFactory = new MysqlFactory();
	}
}
