package com.prog.service.logistics;

import java.util.List;

import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.prog.Dao.logistics.DeliveryOrderDao;
import com.prog.model.erp.Deliveryorder;


@Service
public class DeliveryOrderService {

    @Autowired
    private DeliveryOrderDao deliveryOrderDao;

    public int saveDeliveryOrder(Deliveryorder deliveryOrder) {
        String deliveryorderuid = generateDeliveryOrderId();
        deliveryOrder.setDeliveryorderuid(deliveryorderuid);
        return deliveryOrderDao.saveDeliveryOrder(deliveryOrder);
    }

    private String generateDeliveryOrderId() {
        int length = 4; 
        String characters = "0123456789";
        Random random = new Random();
        StringBuilder deliveryorderuid = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
        	deliveryorderuid.append(characters.charAt(random.nextInt(characters.length())));
        }
        return "DLO" + deliveryorderuid.toString(); 
    }

    public List<Map<String, Object>> getAllDeliveryOrders() {
        return deliveryOrderDao.fetchAllDeliveryOrders();
    }

    public void deleteDeliveryOrder(Long id) {
        deliveryOrderDao.deleteDeliveryOrderById(id);
    }

    public Deliveryorder getDeliveryOrderById(Long id) {
        return deliveryOrderDao.getDeliveryOrderById(id);
    }

    public void updateDeliveryOrder(Deliveryorder deliveryOrder) {
        deliveryOrderDao.updateDeliveryOrder(deliveryOrder);
    }

	public List<String> getItemid(){
		return deliveryOrderDao.getItemId();		
	}

	 public List<Map<String,Object>> getSalesOrderDetails(String salesorderuid) {
	        return deliveryOrderDao.getSalesOrderDetails(salesorderuid);
	    }

	 public List<Map<String,Object>> getProductsBySalesOrder(String salesorderuid) throws JsonProcessingException {
		    return deliveryOrderDao.getProductsBySalesOrder(salesorderuid);
		}


	    public Map<String,Object> getQuantityDispatched(String salesorderuid, String productuid) {
	        return deliveryOrderDao.getQuantityDispatched(salesorderuid, productuid);
	    }
	    // ✅ NEW METHOD: Get Delivery Date According to Product UID
	    public Map<String, Object> getDeliveryDateByProduct(String salesorderuid, String productuid) {
	        return deliveryOrderDao.getDeliveryDateByProduct(salesorderuid, productuid);
	    }
}