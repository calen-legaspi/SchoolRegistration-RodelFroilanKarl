package beans;

public class StudentBean implements java.io.Serializable {

	private static final long serialVersionUID = -7247709915951816651L;
	
	public String misc_fee;
	public String total_tuition;
	
	public String getMisc_fee() {
		return misc_fee;
	}
	public void setMisc_fee(String misc_fee) {
		this.misc_fee = misc_fee;
	}
	
	public String getTotal_tuition() {
		return total_tuition;
	}
	public void setTotal_tuition(String total_tuition) {
		this.total_tuition = total_tuition;
	}


}
