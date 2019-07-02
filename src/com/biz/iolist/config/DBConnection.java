package com.biz.iolist.config;

import java.io.IOException;
import java.io.Reader;

import javax.xml.transform.Source;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class DBConnection {
	
	private static SqlSessionFactory sqlSessionFactory = null;
	
	static {
		String configFile = "com/biz/iolist/config/dbconfig.xml";
		
		try {
			Reader configResource = Resources.getResourceAsReader(configFile);
			if(sqlSessionFactory == null) {
				sqlSessionFactory = new SqlSessionFactoryBuilder().build(configResource);
			}
				
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static  SqlSessionFactory getsqlSessionFactory() {
		return sqlSessionFactory;
	}

	
}
