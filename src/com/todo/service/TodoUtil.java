package com.todo.service;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.sql.PreparedStatement;
import java.util.*;

import com.todo.dao.TodoItem;
import com.todo.dao.TodoList;

public class TodoUtil {
	
	
public static void createItem(TodoList list) {
		
		String title, desc, category, due_date;
		Scanner sc = new Scanner(System.in);
		
		System.out.print("[항목추가]\n"
				+ "제목 >");
		
		title = sc.next();
//		if (list.isDuplicate(title)) {
//			System.out.printf("제목이 중복됩니다.");
//			return;
//		}
		
		
		System.out.printf("카테고리 >");
		category = sc.next();
		sc.nextLine();
		System.out.printf("내용 >");
		desc = sc.nextLine().trim();
		System.out.printf("마감일자(yy/mm/dd) >");
		due_date = sc.nextLine().trim();
		
		TodoItem t = new TodoItem(title, category,desc, due_date);
//		list.addItem(t);
//		System.out.println("추가되었습니다:)");
	
		if(list.addItem(t)>0)
			System.out.println("추가되었습니다:)");
	//	int i=1;
//		
//		for (TodoItem item : list.getList()) {
//			item.setNum(i);
//			i++;
//		}
		
	}

	
public static void findList(TodoList l,String keyword) {
	int count=0;
	for (TodoItem item: l.getList(keyword)) {
		System.out.println(item.toString());
		count++;
	}
	System.out.printf("총 %d개의 항목을 찾았습니다.\n",count);
}

public static void listCateAll(TodoList l) {
	int count =0;
	for(String item: l.getCategories()) {
		System.out.print(item + " ");
		count++;
	}
	System.out.printf("\n총 %d개의 카테고리가 등록되어있습니다.\n",count);
}

public static void findCateList(TodoList l, String cate) {
	int count =0;
	for(TodoItem item: l.getListCategory(cate)) {
		System.out.print(item + " ");
		count++;
	}
	System.out.printf("\n총 %d개개의 항목을 찾았습니다.\n",count);
	
}

	public static void deleteItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		//String title = sc.next();
		
		System.out.printf("[항목 삭제]\n"
				+ "삭제할 항목의 번호를 입력하세요 >");
		
		int num = sc.nextInt();
		
		if(l.deleteItem(num)>0)
			System.out.println("삭제되었습니다.");
		
//		for (TodoItem item : l.getList()) {
//			if (item.getNum()==num) {
//				
//		
//				System.out.println(item.getNum()+ ". "+item.toString());
//			}
//		}
//		
//		System.out.printf("위 항목을 삭제하시겠습니까? (y/n) >");
//		
//		String yes=sc.next();
//		if(yes.equals("y")) {
//
//			for (TodoItem item : l.getList()) {
//				if (item.getNum()==num) {
//					l.deleteItem(item);
//					System.out.println("삭제되었습니다:)");
//					break;
//				}
//			}
//			
//			int i=1;
//			
//			for (TodoItem item : l.getList()) {
//				item.setNum(i);
//				i++;
//				
//		}}else{
//			System.out.println("삭제를 취소합니다:)");
//		}
		
	}


	public static void updateItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		
//		System.out.println("[항목 수정]\n"
//				+ "수정할 항목의 제목을 입력하세요 >");
//		String title = sc.next().trim();
//		if (!l.isDuplicate(title)) {
//			System.out.println("없는 제목입니다:)");
//			return;
//		}

		System.out.printf("[항목 수정]\n"
		+ "수정할 항목의 번호을 입력하세요 >");
		int num = sc.nextInt();
		
//		for (TodoItem item : l.getList()) {
//			if (item.getNum()==num) {
//				
//		
//				System.out.println(item.getNum()+ ". "+item.toString());
//			}
//		}
		
		
//		
		System.out.print("새 제목 >");
		String new_title = sc.next().trim();
//		if (l.isDuplicate(new_title)) {
//			System.out.println("제목이 중복됩니다:)");
//			return;
//		}
		
		
		System.out.printf("새 카테고리 >");
		String new_category = sc.next();
		sc.nextLine();
		System.out.printf("새 내용 >");
		String new_description = sc.nextLine().trim();
		System.out.printf("새 마감일자(yy/mm/dd) >");
		String new_due_date = sc.next();
		
		
		
//		sc.nextLine();
//		System.out.println("새 내용 >");
//		String new_description = sc.next().trim();
		
		TodoItem t = new TodoItem(new_title, new_category,new_description,new_due_date);
		t.setId(num);
		if(l.updateItem(t)>0)
			System.out.println("수정되었습니다:)");
		
//		for (TodoItem item : l.getList()) {
//			if (item.getNum()==num) {
//				
//				l.deleteItem(item);
//				TodoItem t = new TodoItem(new_title, new_category,new_description,new_due_date);
//				l.addItem(t);
//				System.out.println("수정되었습니다:)");
//			}
//		}
//		
		
		
		
//		int i=1;
//		
//		for (TodoItem item : l.getList()) {
//			item.setNum(i);
//			i++;
//		}

	}

	public static void findItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		System.out.printf("찾고자하는 단어를 입력하세요 >");
		String word = sc.next();
		
		int j=0;
		for (TodoItem item : l.getList()) {
			if(item.toString().contains(word)) {
				System.out.println(item.getNum()+ ". "+item.toString());
				j++;
			}
		}
		System.out.println("총 "+j+"개의 항목을 찾았습니다:)");
		
	}
	
//	public static void listAlll(TodoList l) {
//		
////		int i=1;
////		
////		for (TodoItem item : l.getList()) {
////			item.setNum(i);
////			i++;
////		}
////		
//		System.out.println("[전체 목록, 총 " + (i-1)+"개]");
//		for (TodoItem item : l.getList()) {
//			System.out.println(item.getNum()+". "+item.toString()
//					);
//		}
////	
////		System.out.printf("[전체 목록, 총 %d개]\n",l.getCount());
////		for(TodoItem item:l.getOrderedList(orderby,ordering)) {
////			System.out.println(item.toString());
////		}
//	}
//	
	public static void listAll(TodoList l, String orderby, int ordering) {
		
		int i=1;
		
		for (TodoItem item : l.getList()) {
			item.setNum(i);
			i++;
		}
		
//		System.out.println("[전체 목록, 총 " + (i-1)+"개]");
//		for (TodoItem item : l.getList()) {
//			System.out.println(item.toString()
//					);
//		}
//	
		System.out.printf("[전체 목록, 총 %d개]\n",l.getCount());
		for(TodoItem item:l.getOrderedList(orderby,ordering)) {
			System.out.println(item.toString());
		}
	}
	
	
	public static void getlist(TodoList l) {
		
		int i=1;
		
		for (TodoItem item : l.getList()) {
			item.setNum(i);
			i++;
		}
		
		System.out.println("[전체 목록, 총 " + (i-1)+"개]");
		for (TodoItem item : l.getList()) {
			System.out.println(item.getNum()+". "+item.toString()
					);
		}
	}
	public static void getItem(TodoList l) {
		
		int i=1;
		
		for (TodoItem item : l.getList()) {
			item.setNum(i);
			i++;
		}
		
		System.out.println("[전체 목록, 총 " + (i-1)+"개]");
		for (TodoItem item : l.getList()) {
			System.out.println(item.getNum()+". "+item.toString()
					);
		}
	}
	

	public static void loadList(TodoList l, String s) {
		
		try {
			File f = new File(s);
			if(f.createNewFile()) {
				System.out.println("File created: "+f.getName());
				
			}else {
				
				BufferedReader br = new BufferedReader(new FileReader(s));
				
				String oneline;
				while((oneline = br.readLine())!= null) {
					StringTokenizer st = new StringTokenizer(oneline,"##");
					
			
					String category = st.nextToken();
				
					String title = st.nextToken();
					String desc = st.nextToken();
					String due_date = st.nextToken();
				    String current_date = st.nextToken();
				    
				    TodoItem i = new TodoItem(title,category,desc,due_date);
				System.out.println(i.toString());
				}
				br.close();
				System.out.println("로딩 완료");
				
			}
		}
		catch(IOException e){
			System.out.println("An error occured.");
			e.printStackTrace();
		}
			
			
		
	}
	
	public static void saveList(TodoList l, String s) {
		try {
			Writer w = new FileWriter(s);
			
			for (TodoItem item : l.getList()) {
				System.out.println(item.toSaveString());
				w.write(item.toSaveString());
			}
			
			//w.write(l.toString());
			w.close();
			System.out.println("정보 저장");
		
			
			
		}catch (FileNotFoundException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
}
