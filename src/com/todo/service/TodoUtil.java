package com.todo.service;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.*;

import com.todo.dao.TodoItem;
import com.todo.dao.TodoList;

public class TodoUtil {
	
	public static void createItem(TodoList list) {
		
		String title, desc;
		Scanner sc = new Scanner(System.in);
		
		System.out.println("[항목추가]\n"
				+ "제목 >");
		
		title = sc.next();
		if (list.isDuplicate(title)) {
			System.out.printf("제목이 중복됩니다.");
			return;
		}
		sc.nextLine();
		System.out.println("내용 >");
		desc = sc.nextLine().trim();
		
		TodoItem t = new TodoItem(title, desc);
		list.addItem(t);
		System.out.println("추가되었습니다:)");
		
	}

	public static void deleteItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		String title = sc.next();
		
		System.out.println("[항목 삭제]\n"
				+ "삭제할 항목의 제목을 입력하세요 >");
		
		for (TodoItem item : l.getList()) {
			if (title.equals(item.getTitle())) {
				l.deleteItem(item);
				System.out.println("삭제되었습니다");
				break;
			}
		}
	}


	public static void updateItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("[항목 수정]\n"
				+ "수정할 항목의 제목을 입력하세요 >");
		String title = sc.next().trim();
		if (!l.isDuplicate(title)) {
			System.out.println("없는 제목입니다:)");
			return;
		}

		System.out.println("새 제목 >");
		String new_title = sc.next().trim();
		if (l.isDuplicate(new_title)) {
			System.out.println("제목이 중복됩니다:)");
			return;
		}
		
		sc.nextLine();
		System.out.println("새 내용 >");
		String new_description = sc.next().trim();
		for (TodoItem item : l.getList()) {
			if (item.getTitle().equals(title)) {
				l.deleteItem(item);
				TodoItem t = new TodoItem(new_title, new_description);
				l.addItem(t);
				System.out.println("수정되었습니다:)");
			}
		}

	}

	public static void listAll(TodoList l) {
		for (TodoItem item : l.getList()) {
			System.out.println(item.toString()
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
					
			
			
					String title = st.nextToken();
					String desc = st.nextToken();
				    String current_date = st.nextToken();
				    
				    TodoItem i = new TodoItem(title,desc);
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
