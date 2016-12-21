package com.cqg.mvcapp.dao;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.cqg.mvcapp.db.JDBCUtils;

public class DAO<T> {
	private QueryRunner queryRunner=new QueryRunner();
	
	private Class<T> clazz;
	
	public DAO(){
		Type superClass=getClass().getGenericSuperclass();
		if(superClass instanceof ParameterizedType){
			ParameterizedType parameterizedType=(ParameterizedType)superClass;
			Type[] typeArgs=parameterizedType.getActualTypeArguments();
			if(typeArgs!=null && typeArgs.length>0){
				if(typeArgs[0] instanceof Class){
					clazz=(Class<T>)typeArgs[0];
				}
			}
		}
	}
	/**
	 * 返回某一个字段对应的值
	 * @param sql
	 * @param atgs
	 * @return
	 */
	public <E> E getForValue(String sql,Object...args){
		Connection connection=null;
		try {
			connection=JDBCUtils.getConnection();
			return (E) queryRunner.query(connection, sql, new ScalarHandler(), args);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			JDBCUtils.close(connection);
		}
		return null;
	}
	/**
	 * 返回T对应的List
	 * @param sql
	 * @param atgs
	 * @return
	 */
	public List<T> getForList(String sql,Object...args){
		Connection connection=null;
		try {
			connection=JDBCUtils.getConnection();
			return queryRunner.query(connection, sql, new BeanListHandler<>(clazz), args);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			JDBCUtils.close(connection);
		}
		return null;
	}
	/**
	 * 返回T的一个实体类对象
	 * @param sql
	 * @param atgs
	 * @return
	 */
	
	public T get(String sql,Object...args){
		Connection connection=null;
		try {
			connection=JDBCUtils.getConnection();
			return queryRunner.query(connection, sql,new BeanHandler<>(clazz), args);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			JDBCUtils.close(connection);
		}
		return null;
	}
	
	/*
	 *封装insert，delete，update方法 
	 */
	public void  update(String sql,Object...args){
		Connection connection=null;
		try {
			connection=JDBCUtils.getConnection();
			queryRunner.update(connection, sql, args);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			JDBCUtils.close(connection);
		}
	}
}
