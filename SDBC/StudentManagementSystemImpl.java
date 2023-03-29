package sdbms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import customexception.InvalidChoiceException;
import customexception.StudentNotFoundException;
import customsorting.SortStudentByAge;
import customsorting.SortStudentById;
import customsorting.SortStudentByMarks;
import customsorting.SortStudentByName;

class StudentManagementSystemImpl implements StudentManagementSystem{
	Scanner sc=new Scanner(System.in);
	Map<String,Student> db=new LinkedHashMap<String,Student>();


	private void display(List<Student> list) {
		for(Student s:list) {
			System.out.println(s);
		}
	}

	@Override
	public void addStudent() {
		//Accept age,name,marks
		//Create student instance
		//add student instance into db-> map ->put() -> key is id, value is Student object
		//print id


		System.out.println("Enter Student Age: ");
		int age=sc.nextInt();
		System.out.println("Enter Student Name: ");
		String name=sc.next();	
		System.out.println("Enter Student Marks: ");
		int marks=sc.nextInt();
		Student s=new Student(age, name, marks);
		db.put(s.getId(), s);
		System.out.println("Student record inserted succesfully.");
		System.out.println("Student Id: "+s.getId());

	}

	@Override
	public void displayStudent() {
		//Accept student id -> toUpperCase()
		//check if Id is present or not -> containsKey()
		//if the id is present -> get the value (student object)
		//getAge(),getName()..getMarks()
		//else -> customexception -> StudentNotFountException
		//invoke it using throw keyword
		System.out.println("Enter Student Id: \n");
		String id=sc.next().toUpperCase();
		if(db.containsKey(id)) {
			Student std=db.get(id);
			System.out.println("Id: "+std.getId());
			System.out.println("Age: "+std.getAge());
			System.out.println("Name: "+std.getName());
			System.out.println("Marks: "+std.getMarks());
		}
		else {
			try {
				String msg="Student with the ID: "+id+" is not found";
				throw new StudentNotFoundException(msg);
			}
			catch (Exception e) {
				System.err.println(e.getMessage());
			}
		}
	}

	@Override
	public void displayAllStudents() {
		//Map (db) into set with the help of keySet()
		//for each loop traverse keys
		//db.get(key) -> value(student object) -> toString() -> print
		if(db.size()!=0) {
			System.out.println("Student details are as follows: \n--------------------------------");
			Set<String> keys=db.keySet();
			for(String key:keys) {
				Student std=db.get(key);
				System.out.println(std); //System.out.println(db.get(std));
			}
		}
		else {
			try {
				String msg="Student DataBase is Empty , Nothing to Display";
				throw new StudentNotFoundException(msg);
			}
			catch (Exception e) {
				System.err.println(e.getMessage());
			}
		}
	}

	@Override
	public void removeStudent() {
		//Accept Id & toUpperCase
		//if id is present ->  db.remove(id);
		//else -> StudentNotFoundException->handle
		System.out.println("Enter Student Id: \n");
		String id=sc.next().toUpperCase();
		if(db.containsKey(id)) {
			System.out.println("Student Record Found!!");
			System.out.println(db.get(id));
			db.remove(id);
			System.out.println("Student Record Deleted successfully!! ");
		}
		else {
			try {
				String msg="Student with ID :"+id+" is not found!!";
				throw new StudentNotFoundException(msg);
			}
			catch (Exception e) {
				System.err.println(e.getMessage());
			}
		}
	}

	@Override
	public void removeAllStudent() {
		if(db.size()!=0) {
			System.out.println("Students Records Available: "+db.size());
			db.clear();
			System.out.println("All Students records are deleted successfully!");
			System.out.println("Students Records Available: "+db.size());
		}
		else {
			try {
				String msg="Student DataBase is Empty , Nothing to Delete";
				throw new StudentNotFoundException(msg);
			}
			catch (Exception e) {
				System.err.println(e.getMessage());
			}
		}
	}

	@Override
	public void updateStudent() {
		//Accept Id & convert into upper case
		//if id is present -> get the value(Student object)
		//display 1:Update age 2: Update name.....
		//switch :1:setAge() 2:setName() 3: default IvalidChoiceException
		//else StudentNotFoundException
		System.out.println("Enter Student Id: ");
		String id=sc.next().toUpperCase();
		if(db.containsKey(id)) {
			Student std=db.get(id);
			System.out.println("Student Record Found!!");
			System.out.println(db.get(id));
			System.out.println("Enter Your Choice\n1:Update Age\n2:Update Name\n3:Update Marks");
			int ch=sc.nextInt();
			switch(ch) {
			case 1:
				System.out.println("Enter Age: ");
				int age=sc.nextInt();
				std.setAge(age);//std.setAge(sc.nextInt())
				break;

			case 2:
				System.out.println("Enter Name: ");
				String name=sc.next();
				std.setName(name);//std.setName(sc.next())
				break;

			case 3:
				System.out.println("Enter Marks: ");
				int mark=sc.nextInt();
				std.setMarks(mark);//std.setMarks(sc.nextInt())
				break;

			default:
				try {
					String msg="Invalid chioce, Kindly enter valid choice!!";
					throw new InvalidChoiceException(msg);
				}
				catch (Exception e) {
					System.err.println(e.getMessage());
				}
			}

		}
		else {
			try {
				String msg="Student with ID :"+id+" is not found!!";
				throw new StudentNotFoundException(msg);
			}
			catch (Exception e) {
				System.err.println(e.getMessage());
			}
		}
	}

	@Override
	public void countStudents() {
		System.out.println("Number of students records: "+db.size());
	}

	@Override
	public void sortStudents() {
		//Map into Set -> keySet() ->keys
		//List list=new AL; -> Student Object -> Generics
		//for each loop -> Traverse keys -> get the value(Student Object )& add it to al

		//display 1: 2: 3: 4:
		//switch -> Collections.sort(list,new SortStudentById()); ->Traverse
		if(db.size()>=2) {
			Set<String> keys=db.keySet();
			List<Student >list= new ArrayList<Student>();
			for(String key:keys) {
				list.add(db.get(key));
			}
			System.out.println("SORTING STUDENTS");
			System.out.println("Enter Sorting choice:");
			System.out.println("1:Sort By Id\n2:Sort By Name\n3:Sort By Age\n4:Sort By Marks\n");
			int ch=sc.nextInt();
			switch(ch) {
			case 1:
				System.out.println("Student Sorted Based On ID");
				Collections.sort(list,new SortStudentById());
				display(list);
				break;

			case 2:
				System.out.println("Student Sorted Based On Name");
				Collections.sort(list,new SortStudentByName());
				for(Student s:list)
					System.out.println(s);
				break;

			case 3:
				System.out.println("Student Sorted Based On Age");
				Collections.sort(list,new SortStudentByAge());
				display(list);
				break;

			case 4:
				System.out.println("Student Sorted Based On Marks");
				Collections.sort(list,new SortStudentByMarks());
				display(list);
				break;

			default:
				try {
					String msg="Invalid chioce, Kindly enter valid choice!!";
					throw new InvalidChoiceException(msg);
				}
				catch (Exception e) {
					System.err.println(e.getMessage());
				}
			}
		}
		else {
			try {
				String msg="No Sufficient Student Records to Sort";
				throw new StudentNotFoundException(msg);
			}
			catch (Exception e) {
				System.err.println(e.getMessage());
			}
		}
	}

	@Override
	public void getStudentWithHighestMarks() {
		if(db.size()>=2) {
			Set<String> keys=db.keySet();
			List<Student >list= new ArrayList<Student>();
			for(String key:keys) {
				list.add(db.get(key));
			}
			Collections.sort(list,new SortStudentByMarks());
			System.out.println("Student with Highest Marks");
			System.out.println(list.get(list.size()-1));
		}
		else {
			try {
				String msg="No Sufficient Student Records to Compare";
				throw new StudentNotFoundException(msg);
			}
			catch (Exception e) {
				System.err.println(e.getMessage());
			}
		}
	}

	@Override
	public void getStudentWithLowestMarks() {
		if(db.size()>=2) {
			Set<String> keys=db.keySet();
			List<Student >list= new ArrayList<Student>();
			for(String key:keys) {
				list.add(db.get(key));
			}
			Collections.sort(list,new SortStudentByMarks());
			System.out.println("Student with Lowest Marks");
			System.out.println(list.get(0));
		}
		else {
			try {
				String msg="No Sufficient Student Records to Compare";
				throw new StudentNotFoundException(msg);
			}
			catch (Exception e) {
				System.err.println(e.getMessage());
			}
		}
	}


}
