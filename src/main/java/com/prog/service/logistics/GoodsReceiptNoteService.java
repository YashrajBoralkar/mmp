package com.prog.service.logistics;

import java.util.List;

import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prog.Dao.logistics.GoodsReceiptNoteDAO;
import com.prog.model.erp.GoodsReceiptNote;



@Service
public class GoodsReceiptNoteService {

	@Autowired
	private GoodsReceiptNoteDAO goodsreceiptnotedao;
	
	public int saveGoods(GoodsReceiptNote goodsreceiptnote) {
		String goodsreceiptuid = generateGoodsreceiptuid();
		goodsreceiptnote.setGoodsreceiptuid(goodsreceiptuid);
		return goodsreceiptnotedao.addGoods(goodsreceiptnote);
	}
	private String generateGoodsreceiptuid() {
        int length =4; // Length of the goodsreceiptuid (example: 8 characters)
        String characters = "1234567890";
        Random random = new Random();
        StringBuilder goodsreceiptuid = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
        	goodsreceiptuid.append(characters.charAt(random.nextInt(characters.length())));
        }
        return "GRN" + goodsreceiptuid.toString();
    }

	 public List<Map<String,Object>>getGoodsList(){
			return goodsreceiptnotedao.findall();
			
		}
	 public void deleteGoods(Long id) {
		 goodsreceiptnotedao.deleteGoods(id);
		}
		
		public GoodsReceiptNote getGoodsReceiptbyid(Long id) {
			return goodsreceiptnotedao.getGoodsReceiptbyid(id);
		}
		public void updateGoodsReceiptNote(GoodsReceiptNote goodsreceiptnote) 
		{
			goodsreceiptnotedao.updateGoodsReceiptNote(goodsreceiptnote);
		}
		public List<String> getSalesOrderUIDS(String customeruid) {
			return goodsreceiptnotedao.getSalesOrderUIDS(customeruid);
		}
		public List<String> getQuantity(String salesorderuid) {
			return goodsreceiptnotedao.getQuantity(salesorderuid);
		}
		
		public List<String> getProductUIDsBySalesOrder(String salesorderuid) {
		    return goodsreceiptnotedao.getProductUIDsBySalesOrder(salesorderuid);
		}
		
		public String getProductNameByUID(String productuid) {
		    return goodsreceiptnotedao.getProductNameByUID(productuid);
		}
		
}
