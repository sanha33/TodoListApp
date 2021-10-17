package com.todo.menu;
public class Menu {

    public static void displaymenu()
    {
        System.out.println();
        System.out.println("1. Add a new item ( add )");
        System.out.println("2. Delete an existing item ( del )");
        System.out.println("3. Update an item  ( edit )");
        System.out.println("4. Complete check items ( comp )");
        System.out.println("5. uncomplete check items ( uncomp )");
        System.out.println("6. List all items ( ls )");
        System.out.println("7. List all categories ( ls_cate )");
        System.out.println("8. List all completed check items ( ls_comp )");
        System.out.println("9. List all completed check items ( ls_uncomp )");
        System.out.println("10. sort the list by name ( ls_name )");
        System.out.println("11. sort the list by name ( ls_name_desc )");
        System.out.println("12. sort the list by date ( ls_date )");
        System.out.println("13. Find an items ( find )");
        System.out.println("14. Find an category ( find_cate )");
        System.out.println("15. exit (Or press escape key to exit)");
        //System.out.println("Enter your choice >");
    }
    public static void prompt() {
    	System.out.print("Command >");
    }
}
