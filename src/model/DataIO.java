package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

//import com.sun.tools.javac.util.StringUtils;

public class DataIO {
	public static ArrayList<SinhVien> dsSinhVien = new ArrayList<SinhVien>();
	public static ArrayList<MonHoc> dsMonHoc = new ArrayList<MonHoc>();
	public static ArrayList<Diem> dsDiem = new ArrayList<Diem>();
	public static String dataRoot = "";
	
	public static final String fileSinhVien = "sinhvien.txt";
	public static final String fileDiem = "diem.txt";
	public static final String fileMonHoc = "monhoc.txt";
	public static void loadData() {
		// load ds sv
		loadSinhVien();
		// load ds mh
		loadMonHoc();
		//load ds diem
		loadDiem();
	}
	private static void loadSinhVien() {
		FileReader freader = null;
		BufferedReader buffer = null;
		File file = new File(dataRoot+"/"+fileSinhVien);
		try {
			freader =new FileReader(file);
			buffer = new BufferedReader(freader);
			String line;
			while((line = buffer.readLine()) != null){
				if(line.startsWith("#")) continue;
				String parts[] = line.split(";");
				String ma = parts[0];
				String hoDem = parts[1];
				String ten = parts[2];
				String ngaySinh = parts[3];
				String gioiTinh = parts[4];
				SinhVien s = new SinhVien(ma ,hoDem,ten ,ngaySinh,gioiTinh);
				dsSinhVien.add(s);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(buffer !=null) buffer.close();
				if(freader != null) freader.close();
			}catch(Exception e ) {
				e.printStackTrace();
			}
		}
	}
	private static void loadMonHoc() {
		FileReader freader = null;
		BufferedReader buffer = null;
		File file = new File(dataRoot+"/"+fileMonHoc);
		try {
			freader =new FileReader(file);
			buffer = new BufferedReader(freader);
			String line;
			while((line = buffer.readLine()) != null){
				if(line.startsWith("#")) continue;
				String parts[] = line.split(";");
				String ma = parts[0];
				String tenMh = parts[1];
				String hsMh  = parts[2];
				
				MonHoc s = new MonHoc(ma ,tenMh, Double.parseDouble(hsMh));
				dsMonHoc.add(s);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(buffer !=null) buffer.close();
				if(freader != null) freader.close();
			}catch(Exception e ) {
				e.printStackTrace();
			}
		}
	}
	private static void loadDiem() {
		FileReader freader = null;
		BufferedReader buffer = null;
		File file = new File(dataRoot+"/"+fileDiem);
		try {
			freader =new FileReader(file);
			buffer = new BufferedReader(freader);
			String line;
			while((line = buffer.readLine()) != null){
				if(line.startsWith("#")) continue;
				String parts[] = line.split(";");
				String maSv = parts[0];
				String maMh = parts[1];
				String diem  = parts[2];
				
				Diem s = new Diem(maSv ,maMh, Double.parseDouble(diem));
				dsDiem.add(s);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(buffer !=null) buffer.close();
				if(freader != null) freader.close();
			}catch(Exception e ) {
				e.printStackTrace();
			}
		}
	}
	public static  void ThemSv(ArrayList<SinhVien> list) {
		
		
		Scanner sc = new Scanner(System.in);
		String tt;
		do {
			System.out.println("Nhap thong tin sinh vien theo dang\r\n"
					+ "[ho dem];[ten];[ngay sinh(dd/MM/yyyy)];[gioi tinh(Nam/Nu)]\r\n"
					+ "Vi du:Nguyen Van;A;14/06/1996;Nam\r\n"
					+ "Nhap exit de quay lai");
			System.out.println("Nhap : ");
			tt = sc.nextLine();
			if (tt.equals("exit"))
				continue;
			String[] parts = tt.split(";");
			String hoDem = parts[0];
			String ten = parts[1];
			String ngaySinh = parts[2];
			String gioiTinh = parts[3];
			int size = list.size();
			String maSv = list.get(size-1).getMaSv();
			String ms = maSv.substring(2);
			int newMs = Integer.parseInt(ms)+1;
			String newMSo = Integer.toString(newMs);
			String newMaSv = "SV0"+ newMSo;
			//System.out.println(newMaSv);
				System.out.println("Thêm vào mảng thành công : ");
				System.out.println("["+ newMaSv +"\t"+ hoDem +" "+ten +"\t"+ ngaySinh+"\t"+gioiTinh+"]" );
				SinhVien sv1 = new SinhVien(newMaSv,hoDem,ten,ngaySinh,gioiTinh);
				dsSinhVien.add(sv1);
		FileWriter fwrite =null;
		 BufferedWriter wbuffer =null;
		 try {
				File file = new File(dataRoot+"/"+fileSinhVien);

				fwrite = new FileWriter(file);
				wbuffer = new BufferedWriter(fwrite);
				wbuffer.write("# ma sv; ho dem; ten; ngay sinh; gioi tinh");
			for (SinhVien sv : list) {
				String line = sv.getMaSv()+ ";" +sv.getHoDem()+ ";" +sv.getTen() +";" +sv.getNgaySinh()+ ";" + sv.getGioiTinh();
				wbuffer.newLine();
				wbuffer.write(line);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(wbuffer !=null)wbuffer.close();
				if(fwrite !=null)fwrite.close();
			}catch(Exception e ) {
				e.printStackTrace();
			}
		}
		}while(!tt.equals("exit"));
	
	}
	public static void suaThongTinSinhVien()
	{
		Scanner sc = new Scanner(System.in);
		System.out.println("Nhap vao ma sua : ");
		String maSua = sc.nextLine();
		for (int i = 0; i < dsSinhVien.size(); i++) {
			SinhVien sinhVien = dsSinhVien.get(i);
			if(sinhVien.getMaSv().equals(maSua))
			{
				System.out.println("Tim thay sinh vien");
				System.out.format("[ %s %s %s %s %s]\n", sinhVien.getMaSv(), sinhVien.getHoDem(), sinhVien.getTen(), sinhVien.getNgaySinh(), sinhVien.getGioiTinh());
				System.out.println();
				System.out.println("Nhap lai thong tin theo dang");
				System.out.println("[ho dem];[ten];[ngay sinh];[gioi tinh]");
				System.out.println("Neu khong muon sua muc nao thi bo trong muc do");
				System.out.println("Vi du: Nguyen Van;A;;Nam");
				System.out.println("Nhap: ");
				String sv = sc.nextLine();
				String[] parts = sv.split(";");
				String hoDem = null, ten = null, gt = null, ns = null;
				if(parts.length >= 1)
					hoDem = parts[0];
				if(parts.length >= 2)
					ten = parts[1];
				if(parts.length >= 3)
					ns = parts[2];
				if(parts.length >= 4)
					gt = parts[3];
				if(hoDem != null && !hoDem.isEmpty())
					sinhVien.setHoDem(hoDem);
				if(ten != null && !ten.isEmpty())
					sinhVien.setTen(ten);
				if(gt != null && gt.isEmpty())
					sinhVien.setGioiTinh(gt);
				if(ns != null && ns.isEmpty())
					sinhVien.setNgaySinh(ns);
				System.out.println("Sua thanh cong");
				break;
			}
			if(i == dsSinhVien.size() - 1)
				System.out.println("Khong tim thay sinh vien");
		}
		File file = new File(DataIO.dataRoot + "/" + DataIO.fileSinhVien);
		FileWriter writer = null;
		BufferedWriter buffer = null;
		try {
			writer = new FileWriter(file);
			buffer = new BufferedWriter(writer);
			buffer.write("# ma sv; ho dem; ten; ngay sinh; gioi tinh");
			for (SinhVien s : dsSinhVien) {
				String sv = s.getMaSv() + ";" + s.getHoDem() + ";" + s.getTen() + ";" + s.getNgaySinh() + ";" + s.getGioiTinh();
				buffer.newLine();
				buffer.write(sv);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally
		{
			
			try {
				if(buffer != null)
					buffer.close();
				if(writer != null)
					writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	public static void deleteSv(ArrayList<SinhVien> listSv) {
		System.out.println("Nhap ma sinh vien muon xoa:");
		Scanner sc = new Scanner(System.in);
		String ma = sc.nextLine();
		SinhVien sv = null;
		for (SinhVien s : listSv) {
			if (s.getMaSv().equals(ma)) {
				sv = s;
				break;
			}
		}
		if (sv != null) {
			boolean coDiem = false;
			for (Diem d : DataIO.dsDiem) {
				if (d.getMaSv().equals(sv.getMaSv())) {
					coDiem = true;
					break;
				}
			}
			if (!coDiem) {
				System.out.println("Tim thay:");
				System.out.println("["+sv.getMaSv()+"\t"+sv.getHoDem() +"\t"+ sv.getTen()+"\t"+sv.getNgaySinh()+"\t"+sv.getGioiTinh()+ "]");
				System.out.println("\nBan co chac chan muon xoa(c/k)?");
				String select = sc.nextLine();
				if (select.equals("c")) {
					listSv.remove(sv);
					System.out.println("Xoa thanh cong!");
				}
			} else {
				System.out.println("Tim thay:");
				System.out.println("["+sv.getMaSv()+"\t"+sv.getHoDem() +"\t"+ sv.getTen()+"\t"+sv.getNgaySinh()+"\t"+sv.getGioiTinh()+ "]");
				System.out.println("\nSinh vien nay da co diem, khong the xoa");
			}
		} else {
			System.out.println("\nKhong tim thay sinh vien");
		}
		FileWriter fwrite =null;
		 BufferedWriter wbuffer =null;
		 try {
				File file = new File(dataRoot+"/"+fileSinhVien);

				fwrite = new FileWriter(file);
				wbuffer = new BufferedWriter(fwrite);
				wbuffer.write("# ma sv; ho dem; ten; ngay sinh; gioi tinh");
			for (SinhVien sv1 : dsSinhVien) {
				String line = sv1.getMaSv()+ ";" +sv1.getHoDem()+ ";" +sv1.getTen() +";" +sv1.getNgaySinh()+ ";" + sv.getGioiTinh();
				wbuffer.newLine();
				wbuffer.write(line);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(wbuffer !=null)wbuffer.close();
				if(fwrite !=null)fwrite.close();
			}catch(Exception e ) {
				e.printStackTrace();
			}
		}
	}
	public static void hienThiSV(ArrayList<SinhVien> list) {
		int trang=1;
		Scanner sc= new Scanner(System.in); 
		int ch;
		int tongTrang;
		if(list.size()%30!=0) {
		tongTrang = list.size()/30 +1;
		}
		else {
			tongTrang = list.size()/30;
		}
		do {
		System.out.println("----------------------Danh Sach Sinh Vien-------------------------------");
		System.out.println("----------------------trang : "+ (trang)+"/"+tongTrang+"-------------------------");
		
		 
		System.out.println("┏━━━━━━━━━━┳━━━━━━━━━━━━━━━━━━━━━━┳━━━━━━━━━━━━━━┳━━━━━━━━━━━━━┳━━━━━━━━━━┓");
		System.out.format("┃ %-9s┃ %-21s┃ %-13s┃ %-9s  ┃ %-9s┃\n","Ma","Ho Dem","Ten","Ngay sinh","Gioi Tinh");
		System.out.println("┣━━━━━━━━━━╋━━━━━━━━━━━━━━━━━━━━━━╋━━━━━━━━━━━━━━╋━━━━━━━━━━━━━╋━━━━━━━━━━┫");
		for(int i=(trang-1)*30;i<(trang)*30;i++) {
			if(i>list.size()-1) {
				break;
			}
		SinhVien sv =  list.get(i);
		
		System.out.format("┃ %-9s┃ %-21s┃ %-13s┃ %-9s  ┃ %-9s┃\n",sv.getMaSv(),sv.getHoDem(),sv.getTen(),sv.getNgaySinh(),sv.getGioiTinh());
		}
		
		System.out.println("┗━━━━━━━━━━┻━━━━━━━━━━━━━━━━━━━━━━┻━━━━━━━━━━━━━━┻━━━━━━━━━━━━━┻━━━━━━━━━━┛");
		
		
			System.out.println("1.Xem trang tiep theo \t 2.Tro lai trang truoc \t 3.Den trang cuoi ");
			System.out.println("4.Xem trang dau tein \t 5.Xem trang cu the \t 0.Tro ve menu truoc ");
			System.out.println("Chon :");
			ch = sc.nextInt();
			
			switch(ch) {
			case 1:
				trang++;
				System.out.println(trang);
				
				break;
			case 2 :
				trang--;
				
				break;
			case 3 : 
				trang = tongTrang;
				break;
			case 4:
				trang = 1;
				break;
			case 5:
				System.out.println("Nhap vao trang muon xem");
				int tr = sc.nextInt();
				
				trang = tr;
				break;
			default:
				break;
			}
		}while(ch!=0);
	}
		
	public static void sapxep(ArrayList<SinhVien> list) {
		Comparator<SinhVien> com = new Comparator<SinhVien>() {

			@Override
			public int compare(SinhVien o1, SinhVien o2) {
				return o1.getTen().compareTo(o2.getTen());
			}
		};
		list.sort(com);
		
	}
	public static void hienThiMonHoc() {
		int trang=1;
		Scanner sc= new Scanner(System.in); 
		int ch;
		int tongTrang;
		if(dsMonHoc.size()%30!=0) {
		tongTrang = dsMonHoc.size()/30 +1;
		}
		else {
			tongTrang = dsMonHoc.size()/30;
		}
		
		System.out.println("----------------------Danh Sach Mon Hoc-------------------------------");
		System.out.println("-----------------trang : "+ (trang)+"/"+tongTrang+"-------------------------");
		
		 
		System.out.println("┏━━━━━━━━━━┳━━━━━━━━━━━━━━━━━━━━━━━━━┳━━━━━━━━━━━━━━┓");
		System.out.format("┃ %-9s┃ %-24s┃ %-13s┃\n","Ma","Ten Mon","He so Mon");
		System.out.println("┣━━━━━━━━━━╋━━━━━━━━━━━━━━━━━━━━━━━━━╋━━━━━━━━━━━━━━┫");
		for(int i=(trang-1)*30;i<(trang)*30;i++) {
			if(i>dsMonHoc.size()-1) {
				break;
			}
		MonHoc mh =  dsMonHoc.get(i);
		
		System.out.format("┃ %-9s┃ %-24s┃ %-13s┃\n",mh.getMaMh(),mh.getTenMh(),mh.getHsMh());
		}
		
		System.out.println("┗━━━━━━━━━━┻━━━━━━━━━━━━━━━━━━━━━━━━━┻━━━━━━━━━━━━━━┛");	
	}
	public static void sapxepmh(ArrayList<MonHoc> list) {
		Comparator<MonHoc> com = new Comparator<MonHoc>() {

			@Override
			public int compare(MonHoc o1, MonHoc o2) {
				return o1.getTenMh().compareTo(o2.getTenMh());
			}
		};
		list.sort(com);
		
	}	
	public static void HienThiBangDiem(ArrayList<SinhVien> listSV,ArrayList<MonHoc> listMh,ArrayList<Diem>listD) {
		String maSV;
		int trang =1;
		Scanner sc= new Scanner(System.in); 
		int ch;
		int tongTrang;
		if(listSV.size()%30!=0) {
		tongTrang = listSV.size()/30 +1;
		}
		else {
			tongTrang = listSV.size()/30;
		}
		do {
		System.out.println("----------------------Bang Diem-------------------------------");
		System.out.println("-----------------trang : "+ trang+"/"+tongTrang+"-------------------------");
		for(int i=(trang-1)*30;i<(trang)*30;i++) {
			if(i>listSV.size()-1) {
				break;
			}
			SinhVien sv = listSV.get(i);
			String maSv = listSV.get(i).getMaSv();
			maSV = sv.getMaSv();
			ArrayList<MonHoc> monhoc = new ArrayList<MonHoc>();
			ArrayList<Diem> bDiem = new ArrayList<Diem>();
			for (Diem diem : listD) {
				if(diem.getMaSv().equals(maSV)) {
					bDiem.add(diem);
				}
			}
			for (Diem diem : bDiem) {	
				for (MonHoc mh : listMh) {
					if(mh.getMaMh().equals(diem.getMaMh())) {
						monhoc.add(mh);
					}
				}
			}
			double diem = 0.0;
			double hs = 0.0;
			double dtk = 0.0;
			if(monhoc.size() != 0)
			{
				for(int t = 0; t < monhoc.size(); t++)
				{
					diem += bDiem.get(t).getDiem() * monhoc.get(t).getHsMh();
					hs += monhoc.get(t).getHsMh();
				}
				dtk = diem/hs;
			}
			System.out.println("┍━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
			System.out.format("┃%-7s %33s ┃\n",sv.getMaSv(),sv.getHoDem()+" "+sv.getTen());
			System.out.format("┃DTK : %36.2f┃\n",dtk);
			System.out.println("┣━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┫");
			if(monhoc.size()!=0) {
				for(int t=0;t<monhoc.size();t++) {
					System.out.format("┃ %s %27s  %6s  ┃\n",monhoc.get(t).getMaMh(),monhoc.get(t).getTenMh(),bDiem.get(t).getDiem());
				}
			}
			else {
				System.out.format("┃%42s┃\n","Khong co diem");
			}
			System.out.println("┕━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
			}
			System.out.println("1.Xem trang tiep theo \t 2.Tro lai trang truoc \t 3.Den trang cuoi ");
			System.out.println("4.Xem trang dau tein \t 5.Xem trang cu the \t 0.Tro ve menu truoc ");
			System.out.println("Chon :");
			ch = sc.nextInt();
			switch(ch) {
			case 1:
				trang++;
				break;
			case 2 :
				trang--;
			
				break;
			case 3 : 
				trang = tongTrang;
				break;
			case 4:
				trang = 1;
				break;
			case 5:
				System.out.println("Nhap vao trang muon xem");
				int tr = sc.nextInt();
				trang = tr;
				break;
			default:
				break;
			}
		}while(ch!=0);
}
public static void searchByName(ArrayList<SinhVien> listSv) {
	System.out.println("Nhap ten sinh vien:");
	Scanner sc = new Scanner(System.in);
	String line = sc.nextLine();
	ArrayList<SinhVien> dsKetQua = new ArrayList<SinhVien>();
	for (SinhVien s : listSv) {
		String name = s.getTen().toLowerCase();
		String key = line.toLowerCase();
		
		if (name.equals(key)) {
			dsKetQua.add(s);
		}
	}
	if(dsKetQua.size() > 0) {
		
		hienThiSV(dsKetQua);
	}else {
		System.out.println("Khong tim thay sinh vien ["+line+"]");
	}
}

public static void searchMaSv(String masv) {
	
	ArrayList<SinhVien> dsSV = new ArrayList<SinhVien>();
	for (SinhVien sinhVien : dsSinhVien) {
		if(sinhVien.getMaSv().equals(masv)) {
			dsSV.add(sinhVien);
		}
	}
	if(dsSV.size() >0) {
		HienThiBangDiem(dsSV,dsMonHoc,dsDiem);
	}
	else {
		System.out.println("Khong Tim thay [" + masv+"]");
	}
}
}


