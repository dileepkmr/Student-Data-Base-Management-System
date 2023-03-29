package sdbms;

import java.util.Scanner;
import customexception.InvalidChoiceException;

class Solution {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		StudentManagementSystem sms=new StudentManagementSystemImpl();//upcasting
		System.err.println("Welcome to Student Database Management System");
		System.out.println("---------------------------------------------");
		while(true) {
			System.out.println("Enter your choice\n\n1:Add Student\n2:Display Student\n3:Display all Students\n4:Remove Student\n5:Remove all Students\n6:Update Student\n7:Count Students\n8:Sort Students\n9:Get Student With Highest Marks\n10:Get Student With Lowest Marks\n11:Exit\n");
			int ch=sc.nextInt();
			switch(ch) {
			case 1:sms.addStudent();
			break;

			case 2:sms.displayStudent();
			break;

			case 3:sms.displayAllStudents();
			break;

			case 4:sms.removeStudent();
			break;

			case 5:sms.removeAllStudent();
			break;

			case 6:sms.updateStudent();
			break;

			case 7:sms.countStudents();
			break;

			case 8:sms.sortStudents();
			break;

			case 9:sms.getStudentWithHighestMarks();
			break;

			case 10:sms.getStudentWithLowestMarks();
			break;

			case 11:System.out.println("Thank You!!!...");
			System.exit(0);	

			default:
				try {
					String msg="Invalid chioce, Kindly enter valid choice!!";
					throw new InvalidChoiceException(msg);
				}
				catch (Exception e) {
					System.err.println(e.getMessage());
				}

			}
			System.out.println("---------------------------------------------");
		}
	}
}
