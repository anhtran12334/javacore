package model;

public class MonHoc {
	String maMh,tenMh;
	double hsMh;

	public String getMaMh() {
		return maMh;
	}

	public void setMaMh(String maMh) {
		this.maMh = maMh;
	}

	public String getTenMh() {
		return tenMh;
	}

	public void setTenMh(String tenMh) {
		this.tenMh = tenMh;
	}

	

	public double getHsMh() {
		return hsMh;
	}

	public void setHsMh(double hsMh) {
		this.hsMh = hsMh;
	}
	public MonHoc(String maMh, String tenMh, double hsMh) {
		this.maMh = maMh;
		this.tenMh = tenMh;
		this.hsMh = hsMh;
	}
	public  void GetTenMonHoc(String MaMh) {
		if(DataIO.dsMonHoc.equals(MaMh)) {
			MaMh = getMaMh();
		}
	}
}
