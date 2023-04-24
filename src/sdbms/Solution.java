package sdbms;

import java.util.Scanner;

import customexception.InvalidChoiceException;

public class Solution 
{
	public static void main(String[] args) {
		System.out.println("Welcome to Student Database management System");
		System.out.println("---------------------------------");
		Scanner ip=new Scanner(System.in);
		StudentManagementSystem sms=new StudentManagementSystemImpl();

		while(true)
		{
			System.out.println("1:Add Student\n2:displayStudents");
			System.out.println("3:displaytAllStudents\n4:removeStudents");
			System.out.println("5:removeAllStudents\n6:updateStudents");
			System.out.println("7:countStudents\n8:sortStudent");
			System.out.println("9:getStudentWithHighestMarks");
			System.out.println("10:getStudentWithLowestMarks");
			int choice=ip.nextInt();

			switch(choice)
			{
			case 1:
				sms.addStudents();
				break;

			case 2:
				sms.displayStudents();
				break;
			case 3:
				sms.displaytAllStudents();
				break;
			case 4:
				sms.removeStudents();
				break;

			case 5:
				sms.removeAllStudents();
				break;

			case 6:
				sms.updateStudents();
				break;

			case 7:
				sms.countStudents();
				break;

			case 8:
				sms.sortStudent();
				break;
			case 9:
				sms.getStudentWithHighestMarks();
				break;
			case 10:
				sms.getStudentWithLowestMarks();
				break;


			default:
				try {
					String msg ="invalid Choice, kindly enter valid choice";
					throw new InvalidChoiceException(msg);
				} 
				catch (Exception e) {
					System.out.println(e.getMessage());
				}

			}// end of switch
			System.out.println("-------");

		}// end of while


	}// end of main()

}
















