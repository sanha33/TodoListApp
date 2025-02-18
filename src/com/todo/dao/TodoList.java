package com.todo.dao;


import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

import com.todo.service.TodoSortByDate;
import com.todo.service.TodoSortByName;

import com.todo.service.DbConnect;

public class TodoList {
	
	Connection conn;
	
	public TodoList() {
		this.conn = DbConnect.getConnection();
	}
	
	private List<TodoItem> list;
//
//	public TodoList() {
//		this.list = new ArrayList<TodoItem>();
//	}

	public int addItem(TodoItem t) {
		String sql = "insert into list (title, memo, category, current_date, due_date, is_completed, with_who, place)"+" values (?,?,?,?,?,?,?,?);";
		PreparedStatement pstmt;
		int count=0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,t.getTitle());
			pstmt.setString(2,t.getDesc());
			pstmt.setString(3,t.getCategory());
			pstmt.setString(4,t.getCurrent_date());
			pstmt.setString(5,t.getDue_date());
			pstmt.setInt(6, t.getIs_completed());
			pstmt.setString(7, t.getWith_who());
			pstmt.setString(8, t.getPlace());
			count = pstmt.executeUpdate();
			pstmt.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return count;
	}
	
	public int updateItem(TodoItem t) {
		String sql = "update list set title=?, memo=?, category=? , current_date=?, due_date=?, is_completed=?, with_who=?,place=?"+" where id = ?;";
		PreparedStatement pstmt;
		
		int count =0;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,t.getTitle());
			pstmt.setString(2,t.getDesc());
			pstmt.setString(3,t.getCategory());
			pstmt.setString(4,t.getCurrent_date());
			pstmt.setString(5,t.getDue_date());
			pstmt.setInt(6,t.getIs_completed());
			pstmt.setString(7, t.getWith_who());
			pstmt.setString(8, t.getPlace());
			pstmt.setInt(9, t.getId());
			count= pstmt.executeUpdate();
			pstmt.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return count;
	}

	public int deleteItem(int index) {
		//list.remove(t);
	String sql = "delete from list where id=?;";
	PreparedStatement pstmt;
	int count = 0;
	try {
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, index);
		count = pstmt.executeUpdate();
		pstmt.close();
	}catch (SQLException e) {
		e.printStackTrace();
	}
	return count;
	}

	void editItem(TodoItem t, TodoItem updated) {
		int index = list.indexOf(t);
		list.remove(index);
		list.add(updated);
	}

//	public int completeItem(int index) {
//		
//	}
	public ArrayList<TodoItem> getList() {
		ArrayList<TodoItem> list = new ArrayList<TodoItem>();
		Statement stmt;
		try {
			stmt = conn.createStatement();
			String sql = "SELECT * FROM list";
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				int id = rs.getInt("id");
				String category = rs.getString("category");
				String title = rs.getString("title");
				String description  = rs.getString("memo");
				String due_date = rs.getString("due_date");
				String current_date = rs.getString("current_date");
				int com = rs.getInt("is_completed");
				String with_who = rs.getString("with_who");
				String place = rs.getString("place");
				TodoItem t = new TodoItem(title, description, category, due_date,com,with_who,place);
				t.setId(id);
				t.setCurrent_date(current_date);
				list.add(t);
			}
			stmt.close();
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
		
		
	}

	public int getCount() {
		Statement stmt;
		int count=0;
		try {
			stmt = conn.createStatement();
			String sql = "SELECT count(id) FROM list;";
			ResultSet rs = stmt.executeQuery(sql);
			rs.next();
			count = rs.getInt("count(id)");
			stmt.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return count;
	}
	
public ArrayList<TodoItem> getList(String keyword){
		ArrayList<TodoItem> list = new ArrayList<TodoItem>();
		PreparedStatement pstmt;
		keyword = "%"+keyword+"%";
	
		try {
			String sql = "SELECT * FROM list WHERE title like ? or memo like ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,keyword);
			pstmt.setString(2,keyword);
			ResultSet rs = pstmt.executeQuery(sql);
			while(rs.next()) {
				int id = rs.getInt("id");
				String category = rs.getString("category");
				String title = rs.getString("title");
				String description  = rs.getString("memo");
				String due_date = rs.getString("due_date");
				String current_date = rs.getString("current_date");
				int com = rs.getInt("is_completed");
				String with_who = rs.getString("with_who");
				String place = rs.getString("place");
				TodoItem t = new TodoItem(title, description, category, due_date,com,with_who,place);
				t.setId(id);
				t.setCurrent_date(current_date);
				list.add(t);
				pstmt.close();
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return list;
		
	}
	
	public void sortByName() {
		Collections.sort(list, new TodoSortByName());

	}

	public void listAll() {
		System.out.println("\n"
				+ "inside list_All method\n");
		for (TodoItem myitem : list) {
			System.out.println(myitem.getTitle() + myitem.getDesc());
		}
	}
	
	public ArrayList<String> getCategories(){
		ArrayList<String> list = new ArrayList<String>();
		Statement stmt;
		try {
			stmt = conn.createStatement();
			String sql = "SELECT DISTINCT category FROM list";
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				//int id = rs.getInt("id");
				String category = rs.getString("category");
			//	String title = rs.getString("title");
				//String description  = rs.getString("memo");
				//String due_date = rs.getString("due_date");
				//String current_date = rs.getString("current_date");
				//TodoItem t = new TodoItem(title, description, category, due_date);
			//	t.setId(id);
			//	t.setCurrent_date(current_date);
				list.add(category);
			}
			stmt.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	public ArrayList<TodoItem> getListCategory(String keyword){
		ArrayList<TodoItem> list = new ArrayList<TodoItem>();
		PreparedStatement pstmt;
		try {
			
			String sql = "SELECT * FROM list WHERE category = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,keyword);
			ResultSet rs = pstmt.executeQuery(sql);
			while(rs.next()) {
				int id = rs.getInt("id");
				String category = rs.getString("category");
				String title = rs.getString("title");
				String description  = rs.getString("memo");
				String due_date = rs.getString("due_date");
				String current_date = rs.getString("current_date");
				int com = rs.getInt("is_completed");
				String with_who = rs.getString("with_who");
				String place = rs.getString("place");
				TodoItem t = new TodoItem(title, description, category, due_date,com,with_who,place);
				t.setId(id);
				t.setCurrent_date(current_date);
				list.add(t);
		}
			pstmt.close();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public ArrayList<TodoItem> getOrderedList(String orderby,int ordering){
		ArrayList<TodoItem> list = new ArrayList<TodoItem>();
		Statement stmt;
		try {
			
			stmt = conn.createStatement();
			String sql = "SELECT * FROM list ORDER BY "+orderby;
			if(ordering ==0)
				sql+=" desc";
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				int id = rs.getInt("id");
				String category = rs.getString("category");
				String title = rs.getString("title");
				String description  = rs.getString("memo");
				String due_date = rs.getString("due_date");
				String current_date = rs.getString("current_date");
				int com = rs.getInt("is_completed");
				String with_who = rs.getString("with_who");
				String place = rs.getString("place");
				TodoItem t = new TodoItem(title, description, category, due_date,com,with_who,place);
				t.setId(id);
				t.setCurrent_date(current_date);
				list.add(t);
		}
			stmt.close();
	
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	
	public void reverseList() {
		Collections.reverse(list);
	}

	public void sortByDate() {
		Collections.sort(list, new TodoSortByDate());
	}

	public int indexOf(TodoItem t) {
		return list.indexOf(t);
	}

	public Boolean isDuplicate(String title) {
		for (TodoItem item : list) {
			if (title.equals(item.getTitle())) return true;
		}
		return false;
	}
	
	public void importData(String filename) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(filename));
			String line;
			String sql = "insert into list(title, memo, category, current_date,due_date)"+" values (?,?,?,?,?);";
			int records = 0;
			while((line = br.readLine())!= null ) {
				StringTokenizer st = new StringTokenizer(line,"##");
				String category = st.nextToken();
				String title = st.nextToken();
				String description = st.nextToken();
				String due_date = st.nextToken();
				String current_date = st.nextToken();
				
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, title);
				pstmt.setString(2, description);
				pstmt.setString(3, category);
				pstmt.setString(4, current_date);
				pstmt.setString(5, due_date);
				int count = pstmt.executeUpdate();
				if(count>0) records++;
				pstmt.close();
			}
			System.out.println(records+" records read!!");
			br.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
