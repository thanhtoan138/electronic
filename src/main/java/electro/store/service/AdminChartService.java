package electro.store.service;


public interface AdminChartService {
	
	String [][] getQuantityChartByMonth();
	
	String [][] getPriceChartByMonth();
	
	String [][] getQuantityChartBrand();

	String [][] getQuantityChartCategory();
}
