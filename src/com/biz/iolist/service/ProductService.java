package com.biz.iolist.service;

import java.util.List;
import java.util.Scanner;

import org.apache.ibatis.session.SqlSession;

import com.biz.iolist.config.DBConnection;
import com.biz.iolist.dao.ProductDao;
import com.biz.iolist.model.ProductVO;

/*
 * 상품정보의 등록, 수정,삭제 method
 */
public class ProductService {
	SqlSession sqlSession = null;
	ProductDao proDao = null;
	Scanner scan = null;

	public ProductService() {
		sqlSession = DBConnection.getsqlSessionFactory().openSession(true);
		proDao = (ProductDao) sqlSession.getMapper(ProductDao.class);
		scan = new Scanner(System.in);

	}

	public void view_product() {
		System.out.print("=====================================\n");
		System.out.print("\t\t상품정보\n");
		System.out.print("-------------------------------------\n");
		System.out.print("상품코드\t상품명\t\t매입단가\t매출단가\n");
		List<ProductVO> proList = proDao.selectAll();
		for (ProductVO vo : proList) {
			System.out.printf("%s\t\t%s\t%5d\t\t%d\n", vo.getP_code(), vo.getP_name(), vo.getP_iprice(), vo.getP_oprice());
		}

	}

	public boolean insertPRO() {
		System.out.println("======================");
		System.out.println("상품등록");
		System.out.println("----------------------");
		ProductVO vo = new ProductVO();

		System.out.print("상품코드 >> ");
		String strPcode = scan.nextLine();
		System.out.print("상품명 >> ");
		String strName = scan.nextLine();

		int intIn = 0;
		while (true) {
			System.out.print("매입단가 >> ");
			String strIn = scan.nextLine();

			try {
				intIn = Integer.valueOf(strIn);
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("매입단가는 숫자만 입력해주세요");
				continue;
			}
			break;
		}

		int intOut = 0;
		while (true) {
			System.out.println("매출단가 >> ");
			String strOut = scan.nextLine();
			try {
				intOut = Integer.valueOf(strOut);
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("매출단가는 숫자만 입력해주세요");
				continue;
			}
			break;
		}

		vo.setP_code(strPcode);
		vo.setP_name(strName);
		vo.setP_iprice(intIn);
		vo.setP_oprice(intOut);

		if (proDao.insert(vo) > 0)
			return true;
		else
			return false;

	}

	public boolean updatePRO() {
		System.out.println("======================\t");
		System.out.println("\t상품변경");
		System.out.println("----------------------\t");
		System.out.print("변경할 상품코드 >>");
		String strPcode = scan.nextLine();
		ProductVO vo = proDao.findByCode(strPcode);
		if (vo == null) {
			System.out.println("상품코드가 없습니다.");
			return false;
		}
		System.out.printf("상품명 (%s) >>", vo.getP_name());
		String strName = scan.nextLine();
		if (strName.isEmpty())
			vo.getP_name();

		System.out.printf("매입단가 (%s)>>", vo.getP_iprice());
		String strIn = scan.nextLine();
		int intIn = 0;
		if (strIn.isEmpty())
			intIn = vo.getP_iprice();
		else
			intIn = Integer.valueOf(strIn);

		System.out.printf("매출단가 (%s)>>", vo.getP_oprice());
		String strOut = scan.nextLine();
		int intOut = 0;
		if (strOut.isEmpty())
			intOut = vo.getP_oprice();
		else
			intOut = Integer.valueOf(strOut);

		vo.setP_name(strName);
		vo.setP_iprice(intIn);
		vo.setP_oprice(intOut);
		if (proDao.update(vo) > 0)
			return true;
		else
			return false;

	}

	public boolean deletePRO() {
		System.out.println("=======================");
		System.out.println("상품을 제거합니다.");
		System.out.println("-----------------------");
		System.out.print("상품코드입력 >>");
		String strPcode = scan.nextLine();
		ProductVO vo = proDao.findByCode(strPcode);
		System.out.println(vo);
		System.out.print("정말 삭제하시겠습니까?(YES)");
		String confirm = scan.nextLine();
		if(confirm.equals("YES")) {
			System.out.println("삭제를 완료했습니다.");
			proDao.delete(strPcode);
			return true;
		}
		else return false;
	}

}
