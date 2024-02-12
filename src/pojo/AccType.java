package pojo;

public enum AccType {
		
		SAVINGS(5000.0), CURRENT(10000.0);
		private double minBalance;
		
		AccType(double minBalance) {
			this.minBalance=minBalance;
		}

		public double getMinBalance() {
			return minBalance;
		}

}
