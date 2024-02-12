package pojo;

public class KYC {
		private int kycId;
		private String city;
		private String pincode;
		private String aadharNo;
		
		
		
		public KYC(String city, String pincode, String aadharNo) {
			this.city = city;
			this.pincode = pincode;
			this.aadharNo = aadharNo;
		}

		
		public int getKycId() {
			return kycId;
		}

		public void setKycId(int kycId) {
			this.kycId = kycId;
		}
		public String getCity() {
			return city;
		}

		public void setCity(String city) {
			this.city = city;
		}

		public String getPincode() {
			return pincode;
		}

		public void setPincode(String pincode) {
			this.pincode = pincode;
		}

		public String getAadharNo() {
			return aadharNo;
		}

		public void setAadharNo(String aadharNo) {
			this.aadharNo = aadharNo;
		}
		
		public KYC() {
			// TODO Auto-generated constructor stub
		}


		@Override
		public String toString() {
			return "KYC [kycId=" + kycId + ", city=" + city + ", pincode=" + pincode + ", aadharNo=" + aadharNo + "]";
		}
		
		
}
