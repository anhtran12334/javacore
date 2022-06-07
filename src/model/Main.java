package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class Main {
	
	public static void main(String[] args) {
		
		// docdulieu
		DataIO.dataRoot = "C:\\Users\\trann\\Downloads\\BaiTapCuoiKHhoa_JavaCore\\QuanLySinhVien\\data";
		DataIO.loadData();
		DataIO.dsSinhVien.size();
		System.out.println(DataIO.dsSinhVien.size());
		Scanner sc = new Scanner(System.in);
		int chon;
		do {
			// in menu
			printMenu1();
			// nhap lua chon
			System.out.println(" Nhap Lua chon : ");
			chon = sc.nextInt();
			switch(chon) {
			case 1 :
				 capNhapDs();
				 break;
			case 2:
				System.out.println(DataIO.dsSinhVien.size());
				DataIO.HienThiBangDiem(DataIO.dsSinhVien,DataIO.dsMonHoc, DataIO.dsDiem);
				break;
			case 3:
				timKiem();
				break;
			default:
				break;
			}
		}while(chon!=0);
	}

	private static void timKiem() {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int ch;
		do {
		System.out.println("┍━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
		System.out.println("│1.Tim kiem theo ten sinh vien  │ ");
		System.out.println("│2.Tim kiem theo ma sinh vien   │");
		System.out.println("│0.thoát           			    │");
		System.out.println("┕━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
		System.out.println("Chon:");
		ch = sc.nextInt();
		switch(ch) {
		case 1 :
			DataIO.searchByName(DataIO.dsSinhVien);
			break;
		case 2: 
			Scanner sc1 = new Scanner(System.in);
			System.out.println("Nhap vao ma sinh vien muon tim");
			String masv = sc1.nextLine();
			DataIO.searchMaSv(masv);
			break;
		default:
			System.out.println("Tro Ve");
			break;
		}
		
		}while(ch!=0);
	}

	private static void capNhapDs() {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		System.out.println("┍━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
		System.out.println("│1.Thêm sinh viên      		 │ ");
		System.out.println("│2.Sửa thông tin sinh viên   │");
		System.out.println("│3.Xóa thông tin sinh viên   │");
		System.out.println("│4.Hiển thị danh sách SV     │");
		System.out.println("│5.Hiển thị danh sách MH     │");
		System.out.println("│0.thoát				     │");
		System.out.println("┕━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
		int ch;
		do {
		System.out.println("chon : ");
		ch = sc.nextInt();
		switch(ch) {
		case 1 :
		
			DataIO.ThemSv(DataIO.dsSinhVien);
			break;
			
		case 2 :
			//DataIO.suaThongtin();
			DataIO.suaThongTinSinhVien();
			break;
		case 3 :
			DataIO.deleteSv(DataIO.dsSinhVien);
			
			break;
		case 4 :
			DataIO.sapxep(DataIO.dsSinhVien);
			DataIO.hienThiSV(DataIO.dsSinhVien);
			break;
		case 5 : 
			DataIO.sapxepmh(DataIO.dsMonHoc);
			DataIO.hienThiMonHoc();
			break;
		default :
			break;
		}
		
		}while(ch!=0);
	}

	private static void printMenu1() {
		// TODO Auto-generated method stub
		System.out.println("┍━━━━━━━━━━━━━━━━━━━━━━┓");
		System.out.println("│1.Cập nhật danh sách  │ ");
		System.out.println("│2.Hiển  thị bảng điểm │");
		System.out.println("│3.tìm kiếm            │");
		System.out.println("│0.thoát               │");
		System.out.println("┕━━━━━━━━━━━━━━━━━━━━━━┛");
		
	}
}

