package org.jsp.userapp.controller;

import java.util.List;
import java.util.Scanner;

import org.jsp.userapp.dao.UserDao;
import org.jsp.userapp.dto.User;

public class UserController {

	static Scanner sc = new Scanner(System.in);
	static UserDao dao = new UserDao();
	static 	User u = new User();
	
   
	public static void main(String[] args) {
		
	 
		while(true) {

		System.out.println("1.Save User\n2.Update User\n" + "3.Find User By Id\n4.Verify User By Phone and password\n"
				+ "5.Verify User by Email and password\n6.Verify User by Id and password\n"
				+ "7.Find User By Name\n8.Find user By Age\n" + "9.Find User By Gender\n10.Delete User\n"
				+ "11.Find Names By Age\n12.exit");
		System.out.println("Enter which Operation You Want?");
		int op = sc.nextInt();
		switch (op) {
		case 1: 
			save();
			break;
		case 2:
			update();
			break;
		case 3:
			findUserById();
			break;
		case 4:
			verifyUserByPhone();
			break;
		case 5:
			verifyUserByEmail();
			break;
		case 6:
			verifyUserById();
			break;
		case 7:
			findByName();
			break;
		case 8:
			findByAge();
			break;
		case 9:
			findByGender();
			break;
		case 10:
			delete();
			break;
		case 11:
			findNameByAge();
			break;
		case 12:
			System.exit(0);
			break;
		default:
			System.err.println("Invalid Choice");
		}
		}
		
	}
	
	public static void save() {
		System.out.println("Enter the Name,phone,email,age,gender and password to save user");
		
		u.setName(sc.next());
		u.setPhone(sc.nextLong());
		u.setEmail(sc.next());
		u.setAge(sc.nextInt());
		u.setGender(sc.next());
		u.setPassword(sc.next());
		u = dao.saveUser(u);
		System.out.println("User saved with Id: "+u.getId());
	}
	
	public static void update() {
		System.out.println("Enter the user Id to update");
		int id=sc.nextInt(); 
		System.out.println("Enter the name,phone,email,age,gender and password to update user");
	
		u.setId(id);
		u.setName(sc.next());
		u.setPhone(sc.nextLong());
		u.setEmail(sc.next());
		u.setAge(sc.nextInt());
		u.setGender(sc.next());
		u.setPassword(sc.next());
		u=dao.updateUser(u);
		if(u!=null)
			System.out.println("User updated with Id:"+u.getId());
		else
			System.err.println("Cannot Update User as Entered Id is invalid");
	}
	
	public static void verifyUserByPhone() {
		System.out.println("Enter the Phone Number and Password to verify");
		long phone = sc.nextLong();
		String password =sc.next();

		u = dao.verifyUser(phone,password);
		if(u!=null) {
			System.out.println("Verification Succesfull");
			System.out.println("User id:"+u.getId());
		}
	}
	
	
	public static void verifyUserByEmail() {
		System.out.println("Enter the Email id and password to verify");
		String email = sc.next();
		String password=sc.next();
		
		u = dao.verifyUser(email, password);
		if(u!=null) {
			System.out.println("Verification succesfull");
			System.out.println("User Id:"+u.getId());
			System.out.println("User Name:"+u.getName());
			System.out.println("Phone Number:"+u.getPhone());
			System.out.println("Age:"+u.getAge());
			System.out.println("Email Id:"+u.getEmail());
			System.out.println("Gender:"+u.getGender());
			
		}else {
			System.err.println("Invalid email id or password");
		}
	}
	
	public static void verifyUserById() {
		System.out.println("Enter the User id and password to verify");
		int id = sc.nextInt();
		String password = sc.next();
	
		u = dao.verifyUser(id, password);
		if(u!=null) {
			System.out.println("Verification Succesfull");
			System.out.println("User Id:"+u.getId());
			System.out.println("User Name:"+u.getName());
			System.out.println("Phone Number:"+u.getPhone());
			System.out.println("Age:"+u.getAge());
			System.out.println("Email Id:"+u.getEmail());
			System.out.println("gender:"+u.getGender());
		}else {
			System.err.println("Invalid user id or password");
		}
	}
	
	public static void findUserById() {
		System.out.println("Enter the User Id to display details");
		int id = sc.nextInt();
	
		u=dao.findUserById(id);
		if(u!=null) {
			System.out.println("Verification succesfull");
			System.out.println("User Id:"+u.getId());
			System.out.println("User Name:"+u.getName());
			System.out.println("Phone Number:"+u.getPhone());
			System.out.println("Age:"+u.getAge());
			System.out.println("Email Id:"+u.getEmail());
			System.out.println("gender:"+u.getGender());
		}else {
			System.err.println("Invalid Id");
		}
	}
	
	public static void findByName() {
		System.out.println("Enter the name to display details");
		String name = sc.next();
		List<User> users = dao.findByName(name);
		if(users.size()>0) {
			for(User u:users) {
				System.out.println("User Id:"+u.getId());
				System.out.println("User Name:"+u.getName());
				System.out.println("Phone Number:"+u.getPhone());
				System.out.println("Age:"+u.getAge());
				System.out.println("Email Id:"+u.getEmail());
				System.out.println("gender:"+u.getGender());
				System.out.println("----------------------------");
				
			}
		}else {
			System.err.println("No user with the entered name");
		}
	}
	
	public static void findByAge() {
		System.out.println("Enter the age to display details");
		int age = sc.nextInt();
		List<User> users = dao.findByAge(age);
		if(users.size()>0) {
			for(User u: users) {
				System.out.println("User Id:"+u.getId());
				System.out.println("User Name:"+u.getName());
				System.out.println("Phone Number:"+u.getPhone());
				System.out.println("Age:"+u.getAge());
				System.out.println("Email Id:"+u.getEmail());
				System.out.println("gender:"+u.getGender());
			}
		}else {
			System.err.println("No user with the entered age");
		}
	}
	
	public static void findByGender() {
		System.out.println("Enter the gender to display details");
		String gender = sc.next();
		List<User> users = dao.findByGender(gender);
		if(users.size()>0) {
			for(User u: users) {
				System.out.println("User Id:"+u.getId());
				System.out.println("User Name:"+u.getName());
				System.out.println("Phone Number:"+u.getPhone());
				System.out.println("Age:"+u.getAge());
				System.out.println("Email Id:"+u.getEmail());
				System.out.println("gender:"+u.getGender());
			}
		}else {
			System.err.println("No user with the entered gender");
		}
	}
	
	public static void delete() {
		System.out.println("Enter the suer Id to delete");
		int id = sc.nextInt();
		boolean deleted = dao.deleteUser(id);
		if(deleted) {
			System.out.println("User deleted");
		}else {
			System.err.println("Cannot delete user as id is ivalid");
		}
	}
	
	public static void findNameByAge()
	{
		System.out.println("Enter the age to display User names");
		int age = sc.nextInt();
		List<String> names = dao.findNameByAge(age);
		if(names.size()>0) {
			for(String name: names) {
				System.out.println(name);
			}
		}else {
			System.err.println("No User with the age:"+age);
		}
	}
	

}
