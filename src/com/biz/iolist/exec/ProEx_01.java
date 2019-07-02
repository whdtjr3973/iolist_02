package com.biz.iolist.exec;

import java.util.Scanner;

import com.biz.iolist.service.ProductService;

public class ProEx_01 {

	public static void main(String[] args) {
		ProductService ps = new ProductService();
		Scanner scan = new Scanner(System.in);
		while(true) {
		ps.view_product();
		System.out.println("===========================");
		System.out.println("1:등록 2:수정 3:삭제 4:종료");
		System.out.println("===========================");
		String strMenu = scan.nextLine();
		int intMenu = Integer.valueOf(strMenu);
		if(intMenu == 1) ps.insertPRO();
		if(intMenu == 2) ps.updatePRO();	 
		if(intMenu == 3) ps.deletePRO();
		if(intMenu == 4) break;
		}
		System.out.println("업무를 종료합니다.");
	}

}
