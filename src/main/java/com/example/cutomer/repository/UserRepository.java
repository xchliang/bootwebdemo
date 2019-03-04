package com.example.cutomer.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.example.cutomer.model.User;

public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {
	/*
	 * 自定义的简单查询就是根据方法名来自动生成SQL，
	 * 主要的语法是findXXBy,readAXXBy,queryXXBy,countXXBy, getXXBy后面跟属性名称
	 */
	//自动按userName查询
	public User findByUserName(String userName);
	//and条件
	public User getByUserNameAndEmail(String userName,String email);
	//OR条件
	public User queryByUserNameOrEmail(String userName,String email);
	//like
	public List<User> queryByUserNameLike(String userName);
	/*
	 * （1）不能用select *，只能用别名；
	 * （2）?1表示第一个参数，?2：第二个参数
	 * （3）方法名不重要，因为已指定JPQL语句，不会再按方法名生成sql
	 */
	@Query("select u from User u where userName=?1 and email=?2")
	public List<User> getUsers(String userName,String email);
	//使用@Param注解指定参数名，不能与?+数字形式混合使用
	@Query(value="SELECT u FROM User u WHERE userName=:username AND email=:email "
			+ "ORDER BY userName ASC,level DESC")
	public List<User> getUsers2(@Param("username")String name,@Param("email")String email);
	
	//nativeQuery=true，使用原生sql查询，用实体类接收返回结果
	@Query(value="SELECT * FROM user u WHERE u.user_name=:username AND email=:email "
			+ "ORDER BY user_name ASC,level DESC", 
			nativeQuery=true)
	public List<User> getUsersBySql(@Param("username")String name,@Param("email")String email);
	
	//deleteByXXX按条件删除，也可以需要用@Query，配合@Modifying使用，返回值类型只能为void/int/Integer
	@Transactional
	//@Modifying
	//@Query("delete from User where userName=?1")
	int deleteByUserName(String userName);

	//返回受影响的数据条数
	@Transactional
	@Modifying
    @Query("update User u set u.lockStatus = :lockStatus where u.userName = :userName")
    int updateByUserName(@Param("lockStatus")Integer lockStatus,@Param("userName")String userName);
		
	//返回受影响的数据条数
	@Transactional
	@Modifying
	@Query("update User u set u.lockStatus = :lockStatus where id = :uid")
	int updateUser(@Param("lockStatus")Integer lockStatus, @Param("uid")Long uid);
	
	
}
