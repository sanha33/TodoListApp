//package com.todo;
//
//import java.util.Scanner;
//
//import com.todo.dao.TodoList;
//import com.todo.menu.Menu;
//import com.todo.service.TodoUtil;
//
//public class TodoMain {
//	
//	public static void start() {
//	
//		Scanner sc = new Scanner(System.in);
//		TodoList l = new TodoList();
//		boolean isList = false;
//		boolean quit = false;
//		TodoUtil.loadList(l,"todolist.txt");
//		Menu.displaymenu();
//		do {
//			isList = false;
//
//			Menu.prompt();
//			String choice = sc.next();
//			switch (choice) {
//
//			case "add":
//				TodoUtil.addItem(l);
//				break;
//			
//			case "del":
//				TodoUtil.deleteItem(l);
//				break;
//				
//			case "edit":
//				TodoUtil.updateItem(l);
//				break;
//				
//			case "ls":
//				TodoUtil.getlist(l);
//				TodoUtil.getItem(l);
//				break;
//
//			case "ls_name_asc":
//				System.out.println("제목순으로 정렬하였습니다.");
//				l.sortByName();
//				isList = true;
//				break;
//
//			case "ls_name_desc":
//				System.out.println("제목역순으로 정렬하였습니다.");
//				l.sortByName();
//				l.reverseList();
//				isList = true;
//				break;
//				
//			case "ls_date":
//				System.out.println("날짜순으로 정렬하였습니다.");
//				l.sortByDate();
//				isList = true;
//				break;
//			
//			case "find":
//				TodoUtil.findItem(l);
//				break;
//				
//			case "help":
//				Menu.displaymenu();
//				break;
//				
//			case "exit":
//				quit = true;
//				break;
//
//			default:
//				System.out.println("정확한 명령어를 입력하세요 (도움이 필요하면 help)");
//			//	System.out.println("please enter one of the above mentioned command");
//				break;
//			}
//			
//			if(isList) l.listAll();
//		} while (!quit);
//		TodoUtil.saveList(l,"todolist.txt");
//	}
//}

package com.todo;

import java.util.Scanner;

import com.todo.dao.TodoList;
import com.todo.menu.Menu;
import com.todo.service.TodoUtil;

public class TodoMain {
	
	public static void start() {
	
		Scanner sc = new Scanner(System.in);
		TodoList l = new TodoList();
		
		boolean isList = false;
		boolean quit = false;
		//TodoUtil.loadList(l,"todolist.txt");
		//l.importData("todolist.txt");
		Menu.displaymenu();
		do {
			isList = false;

			Menu.prompt();
			String choice = sc.next();
			switch (choice) {

			case "add":
				TodoUtil.createItem(l);
				break;
			
			case "del":
				TodoUtil.deleteItem(l);
				break;
				
			case "edit":
				TodoUtil.updateItem(l);
				break;
				
			case "ls":
				TodoUtil.listAll(l,"title",1);
				break;

			case "ls_cate":
				TodoUtil.listCateAll(l);
				break;
				
			case "find_cate":
				String cate = sc.nextLine().trim();
				TodoUtil.findCateList(l,cate);
				break;
		
			case "ls_name":
				System.out.println("제목순으로 정렬하였습니다.");
				TodoUtil.listAll(l,"title",1);
				//l.sortByName();
			//	isList = true;
				break;

			case "ls_name_desc":
				System.out.println("제목역순으로 정렬하였습니다.");
				TodoUtil.listAll(l,"title",0);
//				l.sortByName();
//				l.reverseList();
//				isList = true;
				break;
				
			case "ls_date":
				System.out.println("날짜순으로 정렬하였습니다.");
				TodoUtil.listAll(l,"due_date",1);
//				l.sortByDate();
//				isList = true;
				break;
				
			case "ls_date_desc":
				System.out.println("날짜역순으로 정렬하였습니다.");
				TodoUtil.listAll(l,"due_date",0);
//				l.sortByDate();
//				isList = true;
				break;
				
			case "find":
				String keyword = sc.nextLine().trim();
				TodoUtil.findList(l,keyword);
				break;
				
			case "help":
				Menu.displaymenu();
				break;
				
			case "exit":
				quit = true;
				break;

			default:
				System.out.println("정확한 명령어를 입력하세요 (도움이 필요하면 help)");
			//	System.out.println("please enter one of the above mentioned command");
				break;
			}
			
			if(isList) l.listAll();
		} while (!quit);
	//	TodoUtil.saveList(l,"todolist.txt");
	}
}
