package sdbms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import customexception.StudentNotFoundException;
import customsorting.SortStudentByMarks;
import customsorting.SortStudentByAge;
import customsorting.SortStudentById;
import customsorting.SortStudentByName;
 
public class StudentManagementSystemImpl implements StudentManagementSystem
{
	Scanner scan=new Scanner(System.in);
	Map<String, Student> db = new LinkedHashMap<String,Student>();


	@Override
	public void addStudents() {
		System.out.println("Enter the Student Age:");
		int age=scan.nextInt();
		System.out.println("Enter the Student Name:");
		String name=scan.next();
		System.out.println("Enter the Student Marks:");
		int marks=scan.nextInt();

		Student std=new Student(age, name, marks);
		db.put(std.getId(),std);
		System.out.println("Student Record Inserted Successfully");
		System.out.println("Student Id is:"+std.getId());
	}

	@Override
	public void displayStudents() 
	{
		System.out.println("Enter Student Id:");
		String id=scan.next(); //String name = scan.next().toUpperCase();
		id = id.toUpperCase();

		if(db.containsKey(id)) // checking id is present or not 
		{
			Student std=db.get(id);  //getting the Student object 
			System.out.println("Id: "+std.getId());  
			System.out.println("Age: "+std.getAge());
			System.out.println("Name: "+std.getName());
			System.out.println("Marks: "+std.getMarks());
			//System.out.println(std); astoString() is overriden
		}

		else {
			try {
				String message= "Student with the id "+id+" is not Found!";
				throw new StudentNotFoundException(message);
			} 
			catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}

	@Override
	public void displaytAllStudents() 
	{
		if(db.size()!=0)
		{
			System.out.println("Student Details are as follows");
			System.out.println("---------------------------");
			Set<String> keys=db.keySet();//JSP101 JSP102 JSP103
			for(String key:keys)
			{
				Student std=db.get(key);
				System.out.println(std); //System.out.println(db.get(key));
			}
		}
		else {
			try {
				String message= "Student DataBase is Empty, Nothing to Display";
				throw new StudentNotFoundException(message);
			} 
			catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}

	}

	@Override
	public void removeStudents() 
	{
		System.out.println("enter student id ");
		String id=scan.next().toUpperCase();

		if(db.containsKey(id)) {
			System.out.println("Student Record Found!");
			System.out.println(db.get(id));
			db.remove(id);
			System.out.println("Student Record Deleted Successfully!");
		}
		else {
			try {
				String message="Student with id "+id+" is not found!";
				throw new StudentNotFoundException(message);	
			}
			catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}

	@Override
	public void removeAllStudents() 
	{
		if(db.size()!=0)
		{
			System.out.println("Student Records Available: "+db.size());
			db.clear();
			System.out.println("All Student Records Deleted Successfully!");
			System.out.println("Student Records Available:"+db.size());
		}
		else {
			try {
				String message= "Student DataBase is Empty, Nothing to Delete";
				throw new StudentNotFoundException(message);
			} 
			catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}

	@Override
	public void updateStudents()
	{
		System.out.println("Enter Student Id ");
		String id=scan.next().toUpperCase();

		if(db.containsKey(id)) {
			Student std= db.get(id);

			System.out.println("1:Update Age\n2:Update Name\n3:update Marks");
			System.out.println("Enter the choice");
			int choice= scan.nextInt();

			switch(choice) {
			case 1:
				System.out.println("Enter Age:");
				int age=scan.nextInt();
				std.setAge(age); // std.setAge(scan.nextInt());
				break;

			case 2:
				System.out.println("Enter Name:");
				String name=scan.next();
				std.setName(name); // std.setName(scan.next());
				break;

			case 3:
				System.out.println("Enter Marks:");
				int marks=scan.nextInt();
				std.setMarks(marks); // std.setMarks(scan.nextInt());
				break;

			default :
				try {
					String message= "Student Choice, kindly enter valid choice!";
					throw new StudentNotFoundException(message);
				} 
				catch (Exception e) {
					System.out.println(e.getMessage());
				}
			}
		}
		else {
			try {
				String message= "Student with the id:"+id+" is not found!";
				throw new StudentNotFoundException(message);
			} 

			catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}

	
	@Override
	public void countStudents() 
	{
		System.out.println("No Of Student Records: "+db.size());
	}

	@Override
	public void sortStudent() 
	{
		Set<String> keys = db.keySet();
		List<Student> list = new ArrayList<Student>();
		for(String key:keys)
		{
			list.add(db.get(key));
		}

		System.out.println("1:Sort by Id\n2:Sort By Age");
		System.out.println("3:Sort by Name\n4:Sort By Marks");
		System.out.println("Enter choice");
		int choice=scan.nextInt();

		switch(choice)
		{
		case 1:
			Collections.sort(list, new SortStudentById());
			display(list);
			break;

		case 2:
			Collections.sort(list, new SortStudentByAge());
			display(list);
			break;

		case 3:
			Collections.sort(list, new SortStudentByName());
			display(list);
			break;

		case 4:
			Collections.sort(list, new SortStudentByMarks());
			display(list);
			break;

		default:
			try {
				String message= "Student Choice, kindly enter valid choice!";
				throw new StudentNotFoundException(message);
			} 
			catch (Exception e) {
				System.out.println(e.getMessage());
			}

		}
	}

	private static void display(List<Student> list) {
		for(Student s: list)
		{
			System.out.println(s);
		}
	}

	@Override
	public void getStudentWithHighestMarks() {
		if(db.size() <=2) {
		Set<String> keys = db.keySet();
		List<Student> list = new ArrayList<Student>();
		for(String key:keys)
		{
			list.add(db.get(key));
		}
		Collections.sort(list, new SortStudentByMarks());
		System.out.println(list.get(list.size()-1)); // getting student object
	}
		else {
			try {
				String message= "No Sufficient Student Records to Compare";
				throw new StudentNotFoundException(message);
			} 
			catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}

	@Override
	public void getStudentWithLowestMarks() {
		if(db.size() >=2) {
		Set<String> keys = db.keySet();
		List<Student> list = new ArrayList<Student>();
		for(String key:keys)
		{
			list.add(db.get(key));
		}
		Collections.sort(list, new SortStudentByMarks());
		System.out.println(list.get(0)); // getting student object
	}
		else {
			try {
				String message= "No Sufficient Student Records to Compare";
				throw new StudentNotFoundException(message);
			} 
			catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}

}
